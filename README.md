# Trident (三叉戟)

> Java Code Security Component （JAVA代码安全组件）

## URL白名单验证

验证代码

```java
security checkUrl = new security();
String[] urlWList = {"joychou.com", "joychou.me"};
Boolean ret = checkUrl.checkUrlWlist("http://test.joychou.me", urlWList);
System.out.println(ret);

```