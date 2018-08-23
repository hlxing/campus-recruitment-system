package com.hlx.webserver.service.impl;

import com.hlx.webserver.constant.EmailTemplate;
import com.hlx.webserver.constant.UserValidation;
import com.hlx.webserver.dao.UserDao;
import com.hlx.webserver.model.dto.LoginDTO;
import com.hlx.webserver.model.dto.RegisterDTO;
import com.hlx.webserver.model.po.User;
import com.hlx.webserver.service.EmailService;
import com.hlx.webserver.service.UserService;
import com.hlx.webserver.util.RandomUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @description: 用户服务实现
 * @author: hlx 2018-08-14
 **/
@Service
public class UserServiceImpl implements UserService{

    private UserDao userDao;

    private StringRedisTemplate template;

    private EmailService emailService;

    // 用户名的正则表达式:3-12位,大小写字母,数字及汉字组合
    private static final String NAME_PATTERN = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{3,12}$";

    // 密码的正则表达式:6-12位,大小写字母及数字组合
    private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{6,12}$";

    // 邮箱的正则表达式
    private static final String EMAIL_PATTERN = "^([A-Za-z0-9_\\-.])+@([A-Za-z0-9_\\-.])+\\.([A-Za-z]{2,4})$";

    @Autowired
    public UserServiceImpl(UserDao userDao,StringRedisTemplate template,EmailService emailService) {
        this.userDao = userDao;
        this.template = template;
        this.emailService = emailService;
    }

    /**
     *
     * 登录成功后返回userId
     */
    @Override
    public Integer login(LoginDTO loginDTO) {
        User rightUser = userDao.getByName(loginDTO.getName());
        String encryptedPass = DigestUtils.sha1Hex(loginDTO.getPassword());
        if (rightUser != null && rightUser.getPassword().equals(encryptedPass)) {
            return  rightUser.getId();
        }
        return null;
    }

    @Override
    public UserValidation register(RegisterDTO registerDTO) {
        // 解析DTO
        String userName = registerDTO.getName();
        String userPass = registerDTO.getPassword();
        String userEmail = registerDTO.getEmail();
        String emailCaptcha = registerDTO.getEmailCaptcha();
        // 非法输入
        if (!Pattern.matches(NAME_PATTERN, userName)
                && !userName.contains("@")) {
            return UserValidation.NAME_INVALID;
        } else if (!Pattern.matches(PASSWORD_PATTERN, userPass)) {
            return UserValidation.PASSWORD_INVALID;
        } else if (!Pattern.matches(EMAIL_PATTERN, userPass)) {
            return UserValidation.EMAIL_INVALID;
        }
        // 邮箱以及用户名是否存在
        if (userDao.getByName(userName) != null) {
            return UserValidation.NAME_EXIST;
        } else if (userDao.getByEmail(userEmail) != null) {
            return UserValidation.EMAIL_EXIST;
        }
        //从redis读取'邮箱认证',包含count(验证码验证次数),captcha(验证码)
        //'邮箱认证'位于 "emailAuth:" + 验证的邮箱
        String authAddress = "emailAuth:" + userEmail;
        Map<Object, Object> emailAuth =  template.opsForHash().entries(authAddress);
        // 过期检测
        if (emailAuth.isEmpty()) {
            return UserValidation.EMAIL_CAPTCHA_EXPIRE;
        }
        String rightCaptcha = (String) emailAuth.get("captcha");
        if (emailCaptcha.equals(rightCaptcha)) {
            return UserValidation.SUCCESS;
        }else{
            //再次自增,并判断验证次数是否大于3,是则销毁
            template.opsForHash().increment(authAddress, "count", 1);
            String count = (String) emailAuth.get("count");
            if (Integer.valueOf(count) >= 3) {
                template.delete(authAddress);
            }
            return UserValidation.EMAIL_CAPTCHA_INVALID;
        }
    }

    @Override
    public UserValidation getEmailCaptcha(String email) {
        // 非法输入
        if (!Pattern.matches(EMAIL_PATTERN, email)) {
            return UserValidation.EMAIL_INVALID;
        }
        // 邮箱是否存在
        if (userDao.getByEmail(email) != null) {
            return UserValidation.EMAIL_EXIST;
        }
        // '邮箱认证'位于 "emailAuth:" + 验证的邮箱
        String authAddress = "emailAuth:" + email;
        String captcha = RandomUtil.build(4);
        // 存放验证次数(count),验证码(captcha)
        Map<String, String> emailAuth = new HashMap<>();
        emailAuth.put("count", "0");
        emailAuth.put("captcha", captcha);
        template.opsForHash().putAll(authAddress, emailAuth);
        // 验证码5分钟内有效/key的过期时间为5分钟
        template.expire(authAddress, 5, TimeUnit.MINUTES);
        // 发送验证码
        String text = EmailTemplate.SIMPLE.replaceAll("#emailCaptcha", captcha);
        emailService.sendMessage(email,"欢迎来到【校招系统】，请验证您的邮箱",text);
        return UserValidation.SUCCESS;
    }

    @Override
    public String getSessionIdByUserId(Integer userId) {
        if (userId != null && userId > 0) {
            return userDao.getSessionIdByUserId(userId);
        }
        return null;
    }

    @Override
    public void updateSessionIdByUserId(Integer userId, String sessionId) {
        userDao.updateSessionIdByUserId(userId, sessionId);
    }
}
