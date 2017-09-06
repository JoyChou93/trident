# Trident（三叉戟）

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
String url = "http://dns_rebind.joychou.me";
ret = checkUrl.checkSSRF(url);
if (ret){
    # curl url
}
else {
    System.out.println("Bad boy. The url is illegal");
}
```

### 绕过姿势


以上代码在设置TTL为0的情况，可以用以下方法绕过 :

1. DNS Rebind（手动设置JAVA的TTL为0）
2. 域名解析2个A记录地址（分别为外网和内网）

也就是说，只要Java不设置TTL为0，该代码逻辑上不存在被绕过风险。