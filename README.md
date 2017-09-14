# Trident（三叉戟）

> Java Code Security Component （JAVA代码安全组件）

目前支持的功能如下：

1. URL白名单验证 （已完成）
2. checkSSRF （已完成）
3. checkReferer （未做）
4. csrfToken （未做）
5. xssEncode （未做）
6. getRealIP （已完成）

## URL白名单验证

验证逻辑

1. 取URL一级域名
2. 判断是否在域名白名单列表内

验证代码

合法URL返回true，非法URL返回false。

```java
// URL白名单组件测试
checkURL urlCheck = new checkURL();
String[] urlWList = {"joychou.com", "joychou.me"};
Boolean ret = urlCheck.checkUrlWlist("http://test.joychou.org", urlWList);
System.out.println(ret);

```

## checkSSRF


验证逻辑

1. 取URL的Host
2. 取Host的IP
3. 判断是否是内网IP，是内网IP直接return，不再往下执行
4. 请求URL
5. 如果有跳转，取出跳转URL，执行第1步

验证代码

如果是内网IP，返回false，表示checkSSRF不通过，否则返回true，即合法返回true。
URL只支持HTTP协议。

```java
// SSRF组件测试
SSRF check = new SSRF();
String url = "http://dns_rebind.joychou.me";
ret = check.checkSSRF(url);
if (ret){
    String con = Request.Get(url).execute().returnContent().toString();
    System.out.println(con);
}
else {
    System.out.println("Bad boy. The url is illegal");
}
```

绕过姿势


以上代码在设置TTL为0的情况，可以用DNS Rebinding绕过。

但是，只要Java不设置TTL为0，该代码逻辑上不存在被绕过风险。

## 获取真实IP


用这份代码，必须保证，前面Proxy有把真实IP放到X-Real-IP头。

