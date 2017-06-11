# one-piece
> 微服务化的最佳实践： SpringCloud全家桶、各类高性能框架、DDD等。
> 名称取自《海贼王》，希望本项目成为微服化时代（大航海时代）的大宝藏。


## ms-common: 微服务中的通用服务

### ms-config: 配置服务
- spring-cloud-config-server：配置的服务端
- spring-cloud-starter-bus-amqp: 使配置可以动态更新
- spring-cloud-starter-config: 配置的消费端，如blog/blog-service-post


### ms-discovery-eureka: 基于eureka的服务注册与发现
* spring-cloud-starter-eureka-server
* spring-cloud-starter-eureka：eureka服务的消费端，如blog/blog-service-post

### ms-hystrix-dashboard: 服务间调用信息监控
* spring-cloud-starter-hystrix-dashboard
* 数据采集点中，要使用spring-cloud-starter-hystrix

### ms-zipkin-server: zipkin服务，服务性能指标收集，发现性能问题
* zipkin-server
* zipkin-autoconfigure-ui
* spring-cloud-starter-zipkin：zipkin服务调用端，如blog/blog-service-post

### ms-image: 图片服务
* express-formidable：图片上传
* mongodb：图片存储

---------

## auth: 各类认证和授权中心解决方案

### auth-jwt: 为单页应用提供简单的认证服务
基于jwt+httpHeader来进行认证服务，取代传统的基于session+cookie的认证方式。
其中使用了auth0和spring-security中的相关包。

### auth-oauth2-jwt：微服化中的认证和授权服务示例
* authserver：认证和授权中心，提供登录和授权功能
    * 登录方式有：用户名密码、facebook、github等
    * 采用jwt取代session方式，可以减轻用户中心的压力
* resource：经认证授权中心保护的资源服务示例
    * Greeting：展示最基本的功能
    * ReadWriteFoo：展示读写权限控制
    * Tokens：展示如何提供jwt中的信息，最重要的是用户信息
* ui：一个简单的angular单页应用，整合authserver和resource

----------

## blog：多人博客的微服务示例
* ms-common中的相关服务
* blog-web：gateway服务
    * 采用Spring-security表单验证，各具体服务中没有任何安全保障（必须放在内网中）
    * 前端采用handlebars和semanticUI
* blog-service-user
    * 用户服务，mysql存储
* blog-service-post
    * 博客服务，mongodb存储
* blog-service-comment
    * 评论服务，mongodb存储


--------------------

## coroutine：高性能之协程
当阻塞不可避免时，可以采用阻塞比线程更轻量fiber（纤程），来提高性能。
这样可以避免线程开销，同时保留熟悉的编程方式。

### guasar
java中的开源纤程库，提供比线程更细粒度的调度单位。


## nio：高性能之非阻塞编程
要提高基于http的微服务的整体性能，必须解决服务间调用的阻塞问题，否则响应时间和吞吐量都将受到严重的影响。

### netty
Netty为著名的非阻塞网络框架，示例中包含《Netty权威指南第二版》中源码。
* bio: 基于JDK的时间服务器阻塞版本
* pio：基于JDK的时间服务器线程池版本（伪异步）
* nio：基于JDK的时间服务器的非阻塞版本
* aio：基于JDK的时间服务器的异步版本
* basic：基于Netty的时间服务器的非阻塞版本
* frame：展示Netty中如何通过编解码器，解决TCP拆包和粘包问题。
* codec：Netty中的各类高效的编码与解码。
* protocol：tcp, udp, http, websocket等协议示例，以及自定义的netty协议示例。

## reactive：高性能之响应式编程
采用流式和函数式（声明式）的方式来写代码（类似于node）。
整合netty，websocket，sse（Server sent event）等非阻塞框架协议，并配合MQ来实现完全地无阻塞化（整个服务调用链路）。
java中的代表有：rxjava、reactor

### reactor-webflux
最新的Spring5中的webflux使用示例（采用SpringMvc方式）

### reactor-spring-function
最新的Spring5中的webflux使用示例（采用纯函数式）

### rxjava
rxjava使用示例，包含spring-cloud-stream-reactive示例。


----------

## ddd：领域驱动设计
微服务化中一个非常关键的问题是服务如何划分。
同样重要的，如何让业务更好地体现在代码中，并保证读写操作实现的高效。
。。。

相关术语：
* DCI：对象的Data数据, 对象使用的Context场景, 对象的Interaction交互行为三者简称
* CQRS：读写分离架构
* EDA：事件驱动架构

