package com.hlx.webserver.config.shiro;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: shiro配置类
 * @author: hlx 2018-08-19
 **/
@Configuration
public class ShiroConfig {

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(AuthService authService) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //设置自定义认证服务
        manager.setRealm(authService);
        //使用容器的session(之后被spring-session拦截)
        manager.setSessionManager(new ServletContainerSessionManager());
        //关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        return manager;
    }

//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
//        //哪些请求可以匿名访问
//        chain.addPathDefinition("/user/login", "anon");
//        //除了以上的请求外，其它请求都需要登录
//        chain.addPathDefinition("/**", "authc");
//        return chain;
//    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为authFilter
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("authFilter", new AuthFilter());
        factoryBean.setFilters(filterMap);

        factoryBean.setSecurityManager(securityManager);
        Map<String, String> filterRuleMap = new HashMap<>();
        // 所有请求通过authFilter
        filterRuleMap.put("/user/login", "anon");
        filterRuleMap.put("/connect/test", "anon");
        filterRuleMap.put("/**", "authFilter");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        // 解决无法正常映射请求BUG
        creator.setUsePrefix(true);
        return creator;
    }

}
