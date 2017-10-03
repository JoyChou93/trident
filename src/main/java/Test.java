/**
 * Author: JoyChou <https://joychou.org>
 * Mail: joychou@joychou.org
 * Date: 2017.09.05
 */
import org.apache.http.client.fluent.Request;

public class Test {
    public static void main(String[] args) throws Exception {

        // URL白名单组件测试
        CheckURL urlCheck = new CheckURL();
        String[] urlWList = {"joychou.com", "joychou.me"};
        Boolean ret = urlCheck.checkUrlWlist("http://test.joychou.org", urlWList);
        System.out.println(ret);

        // SSRF组件测试
        SSRF check = new SSRF();
        String url = "http://127.0.0.1.xip.io";
        ret = check.checkSSRF(url);
        if (ret){
            String con = Request.Get(url).execute().returnContent().toString();
            System.out.println(con);
        }
        else {
            System.out.println("Bad boy. The url is illegal");
        }

        // 获取客户端IP测试


    }
}
