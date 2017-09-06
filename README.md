# Trident (三叉戟)

> Java Code Security Component （JAVA代码安全组件）

目前支持的功能如下：

1. URL白名单验证
2. checkSSRF


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

## checkSSRF

JAVA默认DNS请求会有30s的缓存，所以默认不存在DNS Rebind问题。除非重新设置TTL为0。

如果有大佬能绕过，麻烦提个ISSUE或者PR。

我自己测试，以下方法均没绕过。但是，用DNS Rebind方法在调试的时候，均可以测试成功，所以我怀疑设置TTL为0未成功。

- DNS Rebind（手动设置JAVA的TTL为0）
- 域名解析2个A记录地址（外网+内网）



### 验证逻辑

1. 取URL的Host
2. 取Host的IP
3. 判断是否是内网IP，是内网IP直接return，不再往下执行
4. 请求URL
5. 如果有跳转，取出跳转URL，执行第1步

### 验证代码

如果是内网IP，返回false，表示checkSSRF不通过，否则返回true，即合法返回true。
URL只支持HTTP协议。

```java
security checkUrl = new security();
ret = checkUrl.checkSSRF("http://127.0.0.1");
```
