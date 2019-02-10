# 在线考试系统 Java 后台

> 基于 spring cloud 构建

1. 注册中心 consul
2. 数据库 mysql/mariadb
3. 消息队列 rabbitmq
4. 缓存 redis

## config

> 统一配置管理

依赖 consul 注册自身服务

## gateway

> 微服务网关

依赖 consul 注册自身服务

## base

> 公共模块

除 config 和 gateway，其他服务需要依赖此模块

- 公共依赖，pom 文件
- 公共 aop，分页
- 公共 dto 文件
- 公共 mq，logSender
- 公共 feign 客户端
- 公共常量，错误代码
- 全局异常拦截
- 公共 security 配置，jwt 拦截器
- 公共 typeHandler，list、map
- 公共 utils，json、jwt

## user

> 用户微服务

1. 需要 consul 注册自身服务
2. 需要 mysql/mariadb 作为数据库
3. 需要 rabbitmq 作为消息队列

- 登录认证
- 在线注册
- 短信验证
- 个人信息
- 钱包
- 用户管理
- 等级管理
- 钱包管理

## exam

考试微服务

1. 需要 consul 注册自身服务
2. 需要 mysql/mariadb 作为数据库
3. 需要 rabbitmq 作为消息队列
4. 需要 redis 作为缓存

- 试卷生成
- 考试结果提交
- 异步打分
- 次数限制
- 缓存试卷
- 试卷管理
- 题目管理

## store

商城微服务

1. 需要 consul 注册自身服务
2. 需要 mysql/mariadb 作为数据库
3. 需要 rabbitmq 作为消息队列

- 购物
- 商品管理
- 订单管理
- 充值（支付宝、微信、有赞）
- 异步处理交易

## support

辅助微服务

1. 需要 consul 注册自身服务
2. 需要 mysql/mariadb 作为数据库
3. 需要 rabbitmq 作为消息队列

- 工单管理
- 公告管理
- 日志推送钉钉