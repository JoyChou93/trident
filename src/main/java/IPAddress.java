import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * date: 2017.09.14
 * author: JoyChou (https://joychou.org)
 * mail : joychou@joychou.org
 */

public class IPAddress {
    /**
     * 从Header里的X-Real-IP获取IP地址，如果为空，取Remote_Addr
     * @param request
     * @return
     */
    public static String getRealIP(HttpServletRequest request){
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(ip)) {
            return ip;
        }else {
            String remoteAddr = request.getRemoteAddr();
            if (StringUtils.isNotBlank(remoteAddr)) {
                return remoteAddr;
            }
        }
        return "";
    }


}
