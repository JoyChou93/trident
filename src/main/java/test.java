/**
 * Author: JoyChou
 * Mail: viarus#qq.com
 * Date: 2017.09.05
 */

public class test {
    public static void main(String[] args) throws Exception {
        security checkUrl = new security();
        String[] urlWList = {"joychou.com", "joychou.me"};
        Boolean ret = checkUrl.checkUrlWlist("http://test.joychou.me", urlWList);
        System.out.println(ret);
    }
}
