# micro service

## netflix zuul
* [x] 1.动态路由
* [x] 2.权限校验
* [x] 3.权限认证，token合法性校验，灰度验证时部分流量引导之类

## netflix ribbon-loadbalancer
* [x] 1.负载均衡[随机，轮训，hash,权重等]
* [x] 2.容错
* [x] 3.多协议（HTTP，TCP，UDP）支持异步和反应模型
* [x] 4.缓存和批处理

- LoadBalanceClient
- IRule


## 基于RestTemplate实现自定义的拦截器，对统一请求做预处理
* [x] 1.接口ClientHttpRequestInterceptor,利用RestTemplate.setInterceptors() ,类似于dubbo的filter

## netflix eureka 注册发现

* [x] 1.eureka如何跟eureka通信? http请求(不是netty)
* [x] 2.服务如何发现
* [x] 3.服务如何注册
* [x] 4.服务之间心跳维系