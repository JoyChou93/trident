import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * date: 17/9/14.
 * author: JoyChou(https://joychou.org)
 */

public class IPAddress {
    /**
     * 从Header里的X-Real-IP获取IP地址，如果为空，取Remote_Addr
     * @param request
     * @return
     */
    public static String getIPFromRealIPHeader(HttpServletRequest request){
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank("ip")) {
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
