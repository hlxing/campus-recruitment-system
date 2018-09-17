# 项目介绍

简单的校招系统，用于校园招聘信息的整理、发布，（由乐兴大佬带菜鸡黄鱼和美工小钱一起完成~

# 总体项目结构

```
campus-recruitment-system
├── pages-server -- 页面服务提供模块
├── pages -- vue-cli前端项目
└── web-server -- spring-boot后端项目
``` 

## 后端项目

### 组织结构

```
web-server/src/main
├── java/com/hlx/webserver
|    ├── config -- 项目配置
|    ├── constant -- 公共常量
|    ├── controller -- 控制层
|    ├── exception -- 公共异常
|    ├── model -- 模型
|    ├── service -- 服务层
|    ├── util -- 工具包
|    └── WebServerApplication.java -- 项目启动主类
└──  resources
     ├── mapper -- dao映射
     ├── application.yml -- 项目配置
     └── logback-spring.xml -- 日志配置
```

### 技术栈
技术 | 名称 |官网
---|---|---
Spring Boot | 主体框架 | https://spring.io/projects/spring-boot
MyBatis | ORM框架 | http://www.mybatis.org/mybatis-3/zh/index.html
Devtools | 热部署插件 | https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html
Lombok | 语法糖工具 | https://projectlombok.org/
Druid | 阿里数据库连接池 | http://druid.io/
Pagehelper | MyBatis分页插件 | https://pagehelper.github.io/
Jasypt | 配置文件加密工具 | https://github.com/ulisesbocchio/jasypt-spring-boot
Swagger2 | Api生成及测试工具 | http://swagger.io/
Commons-Codec | 加密工具 | https://commons.apache.org/proper/commons-codec/
Spring-Session | 分布式会话管理 | https://spring.io/projects/spring-session
Shiro | 权限管理 | https://shiro.apache.org/
Modelmapper | 模型转换工具 | http://modelmapper.org/
Redis | NoSQL数据库 | https://redis.io/
Maven | 项目管理 | http://maven.apache.org/

# 备注

2018.9.17: 该项目最终将会落地某学校,作为学校的校招系统使用  
项目的基础架构基本完成,由于某些原因近期停止开发  
对于本项目的各种问题及疑问,欢迎提ISSUES,有空将会一一解答