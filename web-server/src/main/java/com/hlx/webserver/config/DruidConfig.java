package com.hlx.webserver.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

/**
 * @description: Druid配置类
 * @author: hlx 2018-08-14
 **/
@Configuration
public class DruidConfig {

    private String password;

    @Value("${duridMonitor.password}")
    public void setPassword(String password) {
        this.password = password;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<>(
                new StatViewServlet(), "/druid/*");
        // IP白名单
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单
        servletRegistrationBean.addInitParameter("deny", "192.168.1.100");
        // 管理员
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", password);
        // 是否能重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        // druid过滤器 忽略静态资源
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
