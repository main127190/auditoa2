# Spring Shiro学习系统

## 简介
基于springmvc、spring、mybatis-plus、shiro、easyui、Log4j2简单实用的权限系统。

界面基于EasyUI，图标采用较为开放的`Foundation Icon`(MIT协议)。

## 技术和功能
> Spring-cache

> Spring-data-redis

> Spring-Task

> Shiro

> Spring-cache-shiro

> maven profile多环境配置

> 权限管理

> 角色管理

> 用户管理

> 部门管理

> 登陆日志

> 图标管理

## 运行环境
`jdk7 + tomcat7`或以上！

采用`maven profile`配置线下`dev`和线上`production`环境，默认读取`src\main\conf\dev`开发环境下的配置文件。

线上环境使用`mvn`run、打包时添加`-Pproduction`变量则会使用`src/main/conf/production`目录下的配置文件。

线上`production`请注意添加一份`cofing/application.properties`配置文件（由于开源，使用`.gitignore`进行了屏蔽）


