/**
 * Author: JoyChou
 * Mail: viarus#qq.com
 * Date: 2017.09.05
 */
import org.apache.http.client.fluent.Request;

public class test {
    public static void main(String[] args) throws Exception {

        // URL白名单组件测试
        security checkUrl = new security();
        String[] urlWList = {"joychou.com", "joychou.me"};
        Boolean ret = checkUrl.checkUrlWlist("http://test.joychou.org", urlWList);
        System.out.println(ret);

        // SSRF组件测试
        String url = "http://dns_rebind.joychou.me";
        ret = checkUrl.checkSSRF(url);
        if (ret){
            String con = Request.Get(url).execute().returnContent().toString();
            System.out.println(con);
        }
        else {
            System.out.println("Bad boy. The url is illegal");
        }
    }
}
