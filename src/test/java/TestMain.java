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
}
