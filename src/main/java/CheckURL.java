/**
 * date: 2017.09.05
 * author: JoyChou (https://joychou.org)
 * mail : joychou@joychou.org
 */


import java.net.URL;
import com.google.common.net.InternetDomainName;


public class CheckURL {

    /**
     * 检测传入的URL是否在白名单的域名里
     * url：需要检测的URL
     * urlWList: 一级域名的域名列表，比如String[] urlWList = {"joychou.com", "joychou.me"};
     * 返回值：合法URL返回true，非法URL返回false
     */
    public static Boolean checkUrlWlist(String url, String[] urlWList) {
        try {
            URL u = new URL(url);
            // 只允许http和https的协议
            if (!u.getProtocol().startsWith("http") && !u.getProtocol().startsWith("https")) {
                return  false;
            }
            // 获取域名，并转为小写
            String host = u.getHost().toLowerCase();
            // 获取一级域名
            String rootDomain = InternetDomainName.from(host).topPrivateDomain().toString();

            for (String whiteUrl: urlWList){
                if (rootDomain.equals(whiteUrl)) {
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }





    public static void main(String[] args) throws Exception {

    }
}
