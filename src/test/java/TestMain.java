import com.diyiliu.util.JacksonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: TestMain
 * Author: DIYILIU
 * Update: 2018-02-25 16:22
 */
public class TestMain {


    @Test
    public void test(){

        Date date = new Date(1519543854000l);

        System.out.println(date);
    }


    @Test
    public void test1(){

        List list = new ArrayList();

        List l = new ArrayList();
        l.add("BIG");

        list.add(l);

        System.out.println(JacksonUtil.toJson(list));
    }

    @Test
    public void test2(){

        String str = "https://ssc.cdflzy.cn/lotteryweb/Login?code=09e7f02d-8347-4a2d-ad79-277186e09fcd&homeUrl=http%3A%2F%2Fczj909%2Ecom%2FcaiZhiJiaCPLoginWeb%2Fapp%2Fhome%3Fl%3D0&ptn=0&LOBBY=0&lotteryPage=2&mob=0";

        int index = str.indexOf("code=");
        str = str.substring(index);
        index = str.indexOf("&");
        str = str.substring(5, index);

        System.out.println(str);
    }

    @Test
    public void test3(){
        String str = "01 02 03 04 05";

        System.out.println(str.split(" ").length);

        double d = 9.900;
        System.out.println(d);
    }
}
