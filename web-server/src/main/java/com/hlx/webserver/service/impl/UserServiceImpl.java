package com.hlx.webserver.service.impl;

import com.hlx.webserver.constant.EmailTemplate;
import com.hlx.webserver.constant.UserErrorEnum;
import com.hlx.webserver.dao.UserDao;
import com.hlx.webserver.exception.ApiException;
import com.hlx.webserver.model.dto.UserLoginDTO;
import com.hlx.webserver.model.dto.UserRegisterDTO;
import com.hlx.webserver.model.po.Captcha;
import com.hlx.webserver.model.po.User;
import com.hlx.webserver.service.EmailService;
import com.hlx.webserver.service.UserService;
import com.hlx.webserver.util.AESUtil;
import com.hlx.webserver.util.CaptchaUtil;
import com.hlx.webserver.util.IpUtil;
import com.hlx.webserver.util.RandomUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    // 数据库session-AES-密钥
    private static final String SESSION_PWD = "8X1V2EoXH79CZ3zS";

    @Autowired
    public UserServiceImpl(UserDao userDao,StringRedisTemplate redisTemplate,EmailService emailService,
                           RedisOperationsSessionRepository sessionRepository,ModelMapper modelMapper) {
        this.userDao = userDao;
        this.redisTemplate = redisTemplate;
        this.emailService = emailService;
        this.sessionRepository = sessionRepository;
        this.modelMapper = modelMapper;
    }

    /**
     *
     * @param loginDTO 登录业务的数据传输对象
     * @param request http请求,用于管理session
     */
    @Override
    public void login(UserLoginDTO loginDTO, HttpServletRequest request) {
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
        if (!userTest) {
            throw new ApiException(UserErrorEnum.PASSWORD_INVALID);
        } else if (!captchaTest) {
            throw new ApiException(UserErrorEnum.CAPTCHA_INVALID);
        }else{
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
            logger.info("login success");
        }
    }

    @Override
    public void register(UserRegisterDTO registerDTO) {
        // 解析DTO
        String userName = registerDTO.getName();
        String userPass = registerDTO.getPassword();
        String userEmail = registerDTO.getEmail();
        String emailCaptcha = registerDTO.getEmailCaptcha();
        // 邮箱以及用户名是否存在
        if (userDao.getByName(userName) != null) {
            throw new ApiException(UserErrorEnum.NAME_EXIST);
        } else if (userDao.getByEmail(userEmail) != null) {
            throw new ApiException(UserErrorEnum.EMAIL_EXIST);
        }
        //从redis读取'邮箱认证',包含count(验证码验证次数),captcha(验证码)
        //'邮箱认证'位于 "emailAuth:" + 验证的邮箱
        String authAddress = "emailAuth:" + userEmail;
        Map<Object, Object> emailAuth =  redisTemplate.opsForHash().entries(authAddress);
        // 过期检测
        if (emailAuth.isEmpty()) {
            throw new ApiException(UserErrorEnum.EMAIL_CAPTCHA_EXPIRE);
        }
        String rightCaptcha = (String) emailAuth.get("captcha");
        if (emailCaptcha.equals(rightCaptcha)) {
            // 加密
            String encryptPass = DigestUtils.sha1Hex(userPass);
            // DTO转PO,并更新为加密密码
            User user = modelMapper.map(registerDTO,User.class);
            user.setPassword(encryptPass);
            user.setCreateTime(System.currentTimeMillis());
            userDao.save(user);
        }else{
            //再次自增,并判断验证次数是否大于3,是则销毁
            redisTemplate.opsForHash().increment(authAddress, "count", 1);
            String count = (String) emailAuth.get("count");
            if (Integer.valueOf(count) >= 3) {
                redisTemplate.delete(authAddress);
                throw new ApiException(UserErrorEnum.EMAIL_CAPTCHA_EXPIRE);
            }
            throw new ApiException(UserErrorEnum.EMAIL_CAPTCHA_INVALID);
        }
    }

    @Override
    public void getEmailCaptcha(String email) {
        // 非法输入
//        if (!Pattern.matches(EMAIL_PATTERN, email)) {
//            throw new ApiException(UserErrorEnum.PARAM_INVALID);
//        }
        // 邮箱是否存在
        if (userDao.getByEmail(email) != null) {
            throw new ApiException(UserErrorEnum.EMAIL_EXIST);
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
                throw new ApiException(UserErrorEnum.EMAIL_CAPTCHA_BUSY);
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
    }

    /**
     * @param request http请求，用于获取sessionId
     */
    @Override
    public void logout(HttpServletRequest request) {
        // 获取已有的session,没有则不自动创建(create:false)
        HttpSession session = request.getSession(false);
        if (session != null) {
            sessionRepository.deleteById(session.getId());
            logger.info("注销成功");
        }
        logger.info("注销失败");
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
