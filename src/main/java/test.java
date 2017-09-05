/**
 * Created by Viarus on 17/9/5.
 */
public class test {
    public static void main(String[] args) throws Exception {
        main checkUrl = new main();
        String[] urlWList = {"joychou.com", "joychou.me"};
        Boolean ret = checkUrl.checkUrlWlist("http://test.joychou.me", urlWList);
        System.out.println(ret);
    }
}
