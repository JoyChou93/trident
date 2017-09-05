/**
 * Author: JoyChou
 * Mail: viarus#qq.com
 * Date: 2017.09.05
 */

public class test {
    public static void main(String[] args) throws Exception {

        // URL白名单组件测试
        security checkUrl = new security();
        String[] urlWList = {"joychou.com", "joychou.me"};
        Boolean ret = checkUrl.checkUrlWlist("http://test.joychou.org", urlWList);
        System.out.println(ret);

        // SSRF组件测试

        ret = checkUrl.checkSSRF("http://127.0.0.1");
        System.out.println(ret);
    }
}
