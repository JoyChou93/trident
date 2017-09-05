# Trident (三叉戟)

> Java Code Security Component （JAVA代码安全组件）

## URL白名单验证

### 验证逻辑

1. 取URL一级域名
2. 判断是否在域名白名单列表内

### 验证代码

合法URL返回true，非法URL返回false。

```java
security checkUrl = new security();
String[] urlWList = {"joychou.com", "joychou.me"};
Boolean ret = checkUrl.checkUrlWlist("http://test.joychou.me", urlWList);
```

## SSRF

JAVA默认dns请求会有30s的缓存，所以默认不存在dns rebind问题。除非重新设置ttl为0。

### 验证逻辑

1. 取URL的IP
2. 判断IP是否是内网IP
3. 判断是否是内网IP
4. 请求URL
5. 如果有跳转，取出跳转URL，执行1

### 验证代码

如果是内网IP，返回false，表示checkSSRF不通过，否则返回true，即合法返回true。
URL只支持HTTP协议。

```java
security checkUrl = new security();
ret = checkUrl.checkSSRF("http://127.0.0.1");
```
