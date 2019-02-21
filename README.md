# Mallcloud 

[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/shenzhuan/mallcloud)
[![Total lines](https://tokei.rs/b1/github/zscat777/Mallcloud?category=lines)](https://github.com/shenzhuan/mallcloud)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?label=license)](https://github.com/shenzhuan/mallcloud/blob/master/LICENSE)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1237f7a17da0481bad1ad1fe0f93b7ea)](https://app.codacy.com/app/zscat777/Mallcloud?utm_source=github.com&utm_medium=referral&utm_content=zscat777/Mallcloud&utm_campaign=Badge_Grade_Dashboard)

- [配套前端地址](https://github.com/shenzhuan/mallcloud-UI-NEW)
- [在线文档](http://118.190.154.85:8080)
- [演示地址](http://118.190.154.85)

## 前言

'Mallcloud' 是一整套的微服务企业级解决方案。是微服务敏捷开发的代表。

## 项目介绍

[Spring Cloud](https://projects.spring.io/spring-cloud/) 是一个微服务框架，相比 Dubbo 等 RPC 框架, Spring Cloud 提供更全面的分布式系统解决方案。Spring 
Cloud 对微服务基础框架 Netflix 的多个开源组件进行了封装，同时又实现了和云端平台以及和 Spring Boot 开发框架的集成。 Spring 
Cloud 为微服务架构开发涉及的统一认证，配置管理，服务治理，熔断机制，动态路由等提供了一种简单的开发方式。

Spring Cloud 本身已经封装得足够简单，也够丰富。也许正是因为这种简单而丰富，使得想要使用它的团队望而却步。学习成本太高，历史包袱太重，维护成本太高等等一系列原因。

*Mallcloud* 就是为了解决这一问题而诞生的。 *Mallcloud* 整合了 Spring Cloud 的**配置中心**、**注册中心**、**服务网关**，提供了一系列starter组件，
同时提供**服务治理**、**服务监控**、**OAuth2 权限认证**，支持**服务降级/熔断**、**基于标签(x-label)
的路由**、**服务权重**，前端采用**vue+elementUI**，可以很好的解决技术转向 Spring Cloud 的一系列问题，努力打造全方位的微服务敏捷开发解决方案。

*Mallcloud* 提供了基于 Docker Compose 的部署方式。配置文件统一放置在docs目录中，运行脚本案例在根目录中查找。

### 主要实现功能

* Spring Cloud Eureka 注册中心
* Spring Cloud Config Server 配置中心，统一管理配置信息
* Spring Cloud Zuul Gateway 统一微服务网关配置，支持动态路由配置
* 基于 spring-boot-starter-actuator 的自实现的服务治理。包括日志、变量、映射等情况。
* 基于 Hystrix 的聚合监控页面，包括单个服务和服务多个实例的监控。
* 基于 Spring Security OAuth2 的权限认证系统。采用JWT RSA非对称加密的形式进行 token 加密解密。
* 支持基于权重以及基于标签的服务路由，支持动态配置服务权重及标签信息。通过控制用户标签以及动态路由的配置，满足各种各样请求策略。
* 基于角色的RBAC权限控制(用户、部门、角色、菜单、日志、字典、动态路由、oauth2 客户端)，支持按钮级别的权限控制以及数据权限控制（DataScope）。
* 基于Zipkin的调用链追踪（如果生产上使用，还需要把监控内容持久化比，如采用ElasticSearch，把监控内容从发送http请求切换到MQ等改造）。

### 分支版本

* Branch 1.5.12：基于 Spring Boot 1.5.12.RELEASE + Spring Cloud Edgware.SR4，是Mallcloud最初的版本。
* Branch 2.x：基于 Spring Boot 2.0.5.RELEASE + Spring Cloud Finchley.SR1 是当前维护的版本。
* Master 分支已经改为从2.x merge代码，今后更新的中心也会放在2.x分支上。

### 整体架构

![架构图](https://github.com/shenzhuan/mallcloud/blob/master/mallcloud-docs/files/mallcloud%E6%9E%B6%E6%9E%84%E5%9B%BE.jpg)

### 项目目录结构

```
├── mallcloud-cloud  --微服务相关组件
│   ├── cloud-admin --服务治理相关
│   ├── cloud-config  --服务配置中心
│   ├── cloud-monitor --服务监控
│   ├── cloud-registry  --服务注册中心
├── mallcloud-common-starter --自定义spring boot starter
│   ├── mallcloud-common-spring-boot-starter --公共依赖模块(全局异常、常量、通用类)
│   ├── mallcloud-log-spring-boot-starter --通用logback-spring、自定义banner
│   ├── mallcloud-redis-spring-boot-starter --通用redis配置
│   ├── mallcloud-ribbon-spring-boot-starter --基于ribbon的服务治理扩展
│   ├── mallcloud-swagger2-spring-boot-starter --自定义封装swagger2配置
├── mallcloud-demo --demo案例
├── mallcloud-docs --文档、截图、docker文件、初始化脚本
├── mallcloud-gateway --微服务网关
├── mallcloud-oauth2 --认证中心
│   ├── authentication-server --统一认证服务
│   ├── oauth2-config --oauth2抽象封装
│   ├── sso-demo1 --sso案例
│   ├── sso-demo1 --sso案例
├── mallcloud-rbac --基于角色的权限控制服务

```

### 项目截图

![login](https://github.com/shenzhuan/mallcloud/blob/master/mallcloud-docs/files/login.png)
![menu](https://github.com/shenzhuan/mallcloud/blob/master/mallcloud-docs/files/menu.png)
![route](https://github.com/shenzhuan/mallcloud/blob/master/mallcloud-docs/files/route.png)
![monitor](https://github.com/shenzhuan/mallcloud/blob/master/mallcloud-docs/files/monitor.png)
![servers](https://github.com/shenzhuan/mallcloud/blob/master/mallcloud-docs/files/servers.png)
![api](https://github.com/shenzhuan/mallcloud/blob/master/mallcloud-docs/files/api.png)
![zipkin](https://github.com/shenzhuan/mallcloud/blob/master/mallcloud-docs/files/zipkin.png)

### 后端环境

* JDK1.8+
* Spring Boot 1.5.12
* Spring Cloud Edgware.SR4
* Maven 3.0+
* Redis 3.0+
* MySQL 5.7
* IDEA

### 前端

Mallcloud 前端基于开源项目 [D2Admin](https://github.com/d2-projects/d2-admin) 构建。

D2Admin 中文文档：[D2Admin Document](https://d2-projects.github.io/d2-admin-doc/zh/)。

<a href="https://github.com/d2-projects/d2-admin" target="_blank"><img src="https://raw.githubusercontent.com/FairyEver/d2-admin/master/doc/image/d2-admin@2x.png" width="200"></a>

### 链接推荐

- [Spring Boot 1.5.12.RELEASE](https://docs.spring.io/spring-boot/docs/1.5.12.RELEASE/reference/htmlsingle) 官方文档 
- [Spring Cloud Edgware.SR4](http://cloud.spring.io/spring-cloud-static/Dalston.SR4/multi/multi_spring-cloud.html) 官方文档
- [Spring Security OAuth2](http://projects.spring.io/spring-security-oauth/docs/oauth2.html) 开发者指南

### 资源下载

- JDK8 [http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html "JDK8")
- Maven [http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi "Maven")
- Redis [https://redis.io/download](https://redis.io/download "Redis")
- ActiveMQ [http://activemq.apache.org/download-archives.html](http://activemq.apache.org/download-archives.html "ActiveMQ")
- ZooKeeper [http://www.apache.org/dyn/closer.cgi/zookeeper/](http://www.apache.org/dyn/closer.cgi/zookeeper/ "ZooKeeper")
- Elastic Stack [https://www.elastic.co/downloads](https://www.elastic.co/downloads "Elastic Stack")
- Nginx [http://nginx.org/en/download.html](http://nginx.org/en/download.html "Nginx")
- Jenkins [http://updates.jenkins-ci.org/download/war/](http://updates.jenkins-ci.org/download/war/ "Jenkins")

### 友情链接

- [管理系统前端模板 D2Admin](https://github.com/d2-projects/d2-admin)

## LICENSE

[MIT](LICENSE "MIT")


