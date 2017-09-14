import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

/**
 * Author: JoyChou
 * Mail: viarus#qq.com
 * Date: 2017.09.05
 */

public class SSRF {
    /**
     * check SSRF (判断逻辑为判断URL的IP是否是内网IP)
     * 如果是内网IP，返回false，表示checkSSRF不通过。否则返回true。即合法返回true
     * URL只支持HTTP协议
     * 设置了访问超时时间为3s
     */
    public static Boolean checkSSRF(String url) {

        HttpURLConnection connection;
        String finalUrl = url;
        try {
            do {
                // 判断当前请求的URL是否是内网ip
                Boolean bRet = isInnerIpFromUrl(finalUrl);
                if (bRet) {
                    return false;
                }

                connection = (HttpURLConnection) new URL(finalUrl).openConnection();
                connection.setInstanceFollowRedirects(false);
                connection.setUseCaches(false); // 设置为false，手动处理跳转，可以拿到每个跳转的URL
                connection.setConnectTimeout(3*1000); // 设置连接超时时间为3s
                //connection.setRequestMethod("GET");
                connection.connect(); // send dns request
                int responseCode = connection.getResponseCode(); // 发起网络请求
                if (responseCode >= 300 && responseCode <=307 && responseCode != 304 && responseCode != 306) {
                    String redirectedUrl = connection.getHeaderField("Location");
                    if (null == redirectedUrl)
                        break;
                    finalUrl = redirectedUrl;
                    // System.out.println("redirected url: " + finalUrl);
                } else
                    break;
            } while (connection.getResponseCode() != HttpURLConnection.HTTP_OK);
            connection.disconnect();
        } catch (Exception e) {
            return true;
        }
        return true;
    }



    /**
     * 判断一个URL的IP是否是内网IP
     * 如果是内网IP，返回true
     * 非内网IP，返回false
     */
    public static boolean isInnerIpFromUrl(String url) throws Exception {
        String domain = getUrlDomain(url);
        if (domain.equals("")) {
            return true; // 异常URL当成内网IP等非法URL处理
        }

        String ip = DomainToIP(domain);
        if(ip.equals("")){
            return true; // 如果域名转换为IP异常，则认为是非法URL
        }
        return isInnerIp(ip);
    }


    /**
     * 内网IP：
     * 10.0.0.1 - 10.255.255.254       (10.0.0.0/8)
     * 192.168.0.1 - 192.168.255.254   (192.168.0.0/16)
     * 127.0.0.1 - 127.255.255.254     (127.0.0.0/8)
     * 172.16.0.1 - 172.31.255.254     (172.16.0.0/12)
     */
    public static boolean isInnerIp(String strIP) throws IOException {
        try{
            String[] ipArr = strIP.split("\\.");
            if (ipArr.length != 4){
                return false;
            }

            int ip_split1 = Integer.parseInt(ipArr[1]);

            return (ipArr[0].equals("10") ||
                    ipArr[0].equals("127") ||
                    (ipArr[0].equals("172") && ip_split1 >= 16 && ip_split1 <=31) ||
                    (ipArr[0].equals("192") && ipArr[1].equals("168")));
        }catch (Exception e) {
            return false;
        }

    }

    /**
     * 域名转换为IP
     * 会将各种进制的ip转为正常ip
     * 167772161转换为10.0.0.1
     * 127.0.0.1.xip.io转换为127.0.0.1
     */
    public static String DomainToIP(String domain) throws IOException{
        try {
            InetAddress IpAddress = InetAddress.getByName(domain); //  send dns request
            return IpAddress.getHostAddress();
        }
        catch (Exception e) {
            return "";
        }
    }

    /**
     * 从URL中获取域名
     * 限制为http/https协议
     */
    public static String getUrlDomain(String url) throws IOException{
        try {
            URL u = new URL(url);
            if (!u.getProtocol().startsWith("http") && !u.getProtocol().startsWith("https")) {
                throw new IOException("Protocol error: " + u.getProtocol());
            }
            return u.getHost();
        } catch (Exception e) {
            return "";
        }

    }
}
