package com.hlx.webserver.controller;

import com.hlx.webserver.constant.UserValidation;
import com.hlx.webserver.model.ApiResponse;
import com.hlx.webserver.model.User;
import com.hlx.webserver.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.util.Map;

/**
 * @description: 用户控制
 * @author: hlx 2018-08-14
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    private StringRedisTemplate template;

    private RedisOperationsSessionRepository sessionRepository;

    @Autowired
    public UserController(UserService userService, StringRedisTemplate template,
                          RedisOperationsSessionRepository sessionRepository) {
        this.userService = userService;
        this.template = template;
        this.sessionRepository = sessionRepository;
    }

    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name = "user", value = "用户实体", dataType = "User")
    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        boolean loginSuccess = userService.login(user);
        if (loginSuccess) {
            apiResponse.setText("login success");
            logger.info("用户:" + user.getName() + "->>登录成功!");
            HttpSession session = request.getSession(false);
            String userId = String.valueOf(user.getId());
            // session复用标志
            boolean reuseSuccess = false;
            // 请求中没有session或者session已经失效
            if (session == null || sessionRepository.findById(session.getId()) == null) {
                // 通过user.id进一步获取session.id
                String sessionId = (String) template.opsForHash().get("sessionMap", userId);
                // 查找历史session,检测是否过期
                if (sessionId != null) {
                    Session redisSession = sessionRepository.findById(sessionId);
                    if (redisSession != null) {
                        Instant creationTime = redisSession.getCreationTime();
                        Instant now = Instant.now();
                        // 还有1小时的有效期,则复用sessionId
                        if (now.getEpochSecond() - creationTime.getEpochSecond() <= 3600) {
                            response.setHeader("S-TOKEN", redisSession.getId());
                            reuseSuccess = true;
                            logger.info("session reuse success");
                        }
                    }
                }
            }
            // 如果复用失败,则重新创建,并加入sessionMap
            if (!reuseSuccess) {
                HttpSession newSession = request.getSession(true);
                newSession.setAttribute("userId", userId);
                template.opsForHash().put("sessionMap", userId, newSession.getId());
                logger.info("new session");
            }
        } else {
            apiResponse.setStatus(404);
            apiResponse.setText("login error");
            logger.info("用户:" + user.getName() + "->>登录失败!");
        }
        return apiResponse;
    }

    @ApiOperation(value = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String"),
            @ApiImplicitParam(name = "captcha", value = "邮箱验证码", dataType = "String")
    })
    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody Map<String,String> body) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        User user = new User(body.get("name"), body.get("password"), body.get("email"));
        String captcha = body.get("captcha");
        UserValidation registerResult = userService.register(user, captcha);
        apiResponse.setStatus(registerResult.getCode());
        apiResponse.setText(registerResult.getMsg());
        return apiResponse;
    }

    @ApiOperation(value = "获取邮箱验证码", notes = "绑定邮箱")
    @GetMapping("/mailCaptcha")
    public ApiResponse<String> mailCaptcha(@ApiParam(name = "email", value = "邮箱") @RequestParam("email") String email) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        System.out.println(email);
        UserValidation captchaResult = userService.getEmailCaptcha(email);
        apiResponse.setText(captchaResult.getMsg());
        apiResponse.setStatus(captchaResult.getCode());
        return apiResponse;
    }


}
