package com.hlx.webserver.service.impl;

import com.hlx.webserver.constant.EmailTemplate;
import com.hlx.webserver.constant.UserValidation;
import com.hlx.webserver.dao.UserDao;
import com.hlx.webserver.model.dto.req.LoginReqDTO;
import com.hlx.webserver.model.dto.req.RegisterReqDTO;
import com.hlx.webserver.model.po.Captcha;
import com.hlx.webserver.model.po.User;
import com.hlx.webserver.service.EmailService;
import com.hlx.webserver.service.UserService;
import com.hlx.webserver.util.AESUtil;
import com.hlx.webserver.util.CaptchaUtil;
import com.hlx.webserver.util.IpUtil;
import com.hlx.webserver.util.RandomUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Instant;
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

    private StringRedisTemplate redisTemplate;

    private EmailService emailService;

    // Spring-Session-Redis操作DAO,管理控制session
    private RedisOperationsSessionRepository sessionRepository;

    // 用户名的正则表达式:3-12位,大小写字母,数字及汉字组合
    private static final String NAME_PATTERN = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{3,12}$";

    // 密码的正则表达式:6-12位,大小写字母及数字组合
    private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{6,12}$";

    // 邮箱的正则表达式
    private static final String EMAIL_PATTERN = "^([A-Za-z0-9_\\-.])+@([A-Za-z0-9_\\-.])+\\.([A-Za-z]{2,4})$";

    // 数据库session-AES-密钥
    private static final String SESSION_PWD = "8X1V2EoXH79CZ3zS";

    @Autowired
    public UserServiceImpl(UserDao userDao,StringRedisTemplate redisTemplate,EmailService emailService,
                           RedisOperationsSessionRepository sessionRepository) {
        this.userDao = userDao;
        this.redisTemplate = redisTemplate;
        this.emailService = emailService;
        this.sessionRepository = sessionRepository;
    }

    /**
     *
     * @param loginDTO 登录业务的数据传输对象
     * @param request http请求,用于管理session
     * @return  bool,是否登录成功
     */
    @Override
    public boolean login(LoginReqDTO loginDTO, HttpServletRequest request) {
        User rightUser = userDao.getByName(loginDTO.getName());
        String captchaAddress = "captcha:" +
                IpUtil.getAddress(request).replaceAll(":", "-");
        String rightCaptcha = (String) redisTemplate.opsForHash().get(captchaAddress, "captcha");
        String encryptedPass = DigestUtils.sha1Hex(loginDTO.getPassword());
        // 账号密码检测
        boolean userTest = rightUser != null && rightUser.getPassword().equals(encryptedPass);
        // 验证码检测
        boolean captchaTest = rightCaptcha != null && loginDTO.getCaptcha().equals(rightCaptcha);
        // 释放验证码(一次一验)
        redisTemplate.delete(captchaAddress);
        if (userTest && captchaTest) {
            // 清除已有的sessionID,保证同一时间一处登录
            Integer userId = rightUser.getId();
            String sessionId = userDao.getSessionIdByUserId(userId);
            if (sessionId != null) {
                // 解密AES-Session
                sessionId = AESUtil.decrypt(sessionId, SESSION_PWD);
                sessionRepository.deleteById(sessionId);
            }
            // 自动创建一个新的session,并重新加密保存
            HttpSession newSession = request.getSession(true);
            String encryptSessionId = AESUtil.encrypt(newSession.getId(), SESSION_PWD);
            userDao.updateSessionIdByUserId(userId,encryptSessionId);
            return true;
        }

        return false;
    }

    @Override
    public UserValidation register(RegisterReqDTO registerDTO) {
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
        } else if (!Pattern.matches(EMAIL_PATTERN, userEmail)) {
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
        Map<Object, Object> emailAuth =  redisTemplate.opsForHash().entries(authAddress);
        // 过期检测
        if (emailAuth.isEmpty()) {
            return UserValidation.EMAIL_CAPTCHA_EXPIRE;
        }
        String rightCaptcha = (String) emailAuth.get("captcha");
        if (emailCaptcha.equals(rightCaptcha)) {
            // 加密
            userPass = DigestUtils.sha1Hex(userPass);
            User user = new User(userName, userEmail, userPass);
            userDao.save(user);
            return UserValidation.SUCCESS;
        }else{
            //再次自增,并判断验证次数是否大于3,是则销毁
            redisTemplate.opsForHash().increment(authAddress, "count", 1);
            String count = (String) emailAuth.get("count");
            if (Integer.valueOf(count) >= 3) {
                redisTemplate.delete(authAddress);
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
        Instant now = Instant.now();
        // 判断距离上次认证的时间是否大于30s
        String createTimeStr = (String) redisTemplate.opsForHash().get(authAddress, "createTime");
        if (createTimeStr != null) {
            Long beginSeconds = Long.valueOf(createTimeStr);
            Long endSeconds = now.getEpochSecond();
            if (endSeconds - beginSeconds <= 30) {
                return UserValidation.EMAIL_CAPTCHA_EXPIRE;
            }
        }
        String captcha = RandomUtil.build(4);
        // 存放验证次数(count),验证码(captcha),创建时间(createTime)
        Map<String, String> emailAuth = new HashMap<>();
        emailAuth.put("count", "0");
        emailAuth.put("captcha", captcha);
        emailAuth.put("createTime", String.valueOf(now.getEpochSecond()));
        //emailAuth.put("createTime", instant.);
        redisTemplate.opsForHash().putAll(authAddress, emailAuth);
        // 验证码5分钟内有效/key的过期时间为5分钟
        redisTemplate.expire(authAddress, 5, TimeUnit.MINUTES);
        // 发送验证码
        String text = EmailTemplate.SIMPLE.replaceAll("#emailCaptcha", captcha);
        emailService.sendMessage(email,"欢迎来到【校招系统】，请验证您的邮箱",text);
        return UserValidation.SUCCESS;
    }

    /**
     * @param request http请求，用于获取sessionId
     * @return bool, 注销是否成功
     */
    @Override
    public boolean logout(HttpServletRequest request) {
        // 获取已有的session,没有则不自动创建(create:false)
        HttpSession session = request.getSession(false);
        if (session != null) {
            sessionRepository.deleteById(session.getId());
            return true;
        }
        return false;
    }

    /**
     * 获取验证码
     * @param ip 请求的ip地址
     * @param outputStream  请求的响应流
     */
    @Override
    public void getCaptcha(String ip, ServletOutputStream outputStream) {
        //'验证码'位于 "captcha:" + ip地址
        String captchaAddress = "captcha:" + ip;
        Captcha captcha = CaptchaUtil.newInstance();
        redisTemplate.opsForHash().put(captchaAddress, "captcha", captcha.getResult());
        // 10分钟有效期,自动删除,防止内存泄露
        redisTemplate.expire(captchaAddress, 10, TimeUnit.MINUTES);
        BufferedImage image = captcha.getBufferedImage();
        try {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
