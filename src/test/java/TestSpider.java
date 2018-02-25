import com.diyiliu.util.JacksonUtil;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: TestSpider
 * Author: DIYILIU
 * Update: 2018-02-22 13:48
 */
public class TestSpider {


    public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.32 Safari/537.36";

    public static String COOKIE_SESSION = "JSESSIONID=ydEOLarjxKmNBQMf+hi+fxxJ.undefined";

    public void test(String cookie) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://diyiliu.cc/operate/deploy";

        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList();
        cookies.add(cookie);
        headers.put(HttpHeaders.COOKIE, cookies);

        headers.add(HttpHeaders.USER_AGENT, USER_AGENT);

        HttpEntity<String> requestEntity = new HttpEntity(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        System.out.println(responseEntity.getBody());
    }

    @Test
    public void testPost() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/hi";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap paramMap = new LinkedMultiValueMap();
        paramMap.add("username", "admin");
        paramMap.add("password", "123456");

        HttpEntity<MultiValueMap> requestEntity = new HttpEntity(paramMap, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        System.out.println(responseEntity.getBody());
    }

    @Test
    public void testPost2() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://diyiliu.cc/login";

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.USER_AGENT, USER_AGENT);

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap paramMap = new LinkedMultiValueMap();
        paramMap.add("username", "admin");
        paramMap.add("password", "572772828");

        HttpEntity<MultiValueMap> requestEntity = new HttpEntity(paramMap, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        System.out.println(responseEntity.getStatusCodeValue());

        HttpHeaders respHeaders = responseEntity.getHeaders();
        List<String> cookies = respHeaders.get(HttpHeaders.SET_COOKIE);
        test(cookies.get(0));
    }

    @Test
    public void testCheckCode() throws Exception{
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://czj909.com/caiZhiJiaCPLoginWeb/app/home";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.USER_AGENT, USER_AGENT);
        headers.setContentType(MediaType.TEXT_HTML);

        HttpEntity<String> requestEntity = new HttpEntity(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        System.out.println(responseEntity.getStatusCodeValue());

        HttpHeaders respHeaders = responseEntity.getHeaders();
        List<String> cookies = respHeaders.get(HttpHeaders.SET_COOKIE);
        String cookie = cookies.get(0).split(";")[0];

        System.out.println(cookie);

        COOKIE_SESSION = cookie;

        url = "http://czj909.com/caiZhiJiaCPLoginWeb/app/checkCode/image";
        headers = new HttpHeaders();

        cookies = new ArrayList();
        cookies.add(cookie);
        headers.put(HttpHeaders.COOKIE, cookies);

        headers.add(HttpHeaders.USER_AGENT, USER_AGENT);
        headers.setContentType(MediaType.IMAGE_JPEG);

        requestEntity = new HttpEntity(null, headers);
        ResponseEntity<byte[]> responseEntity2 = restTemplate.exchange(url, HttpMethod.GET, requestEntity, byte[].class);

        byte[] in = responseEntity2.getBody();

        File img = new File("C:\\Users\\DIYILIU\\Desktop\\临时\\img.jpeg");

        FileCopyUtils.copy(in, img);
    }


    @Test
    public void testLogin() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://czj909.com/caiZhiJiaCPLoginWeb/app/loginVerification?5943.037352414396";

        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList();
        cookies.add(COOKIE_SESSION);

        headers.put(HttpHeaders.COOKIE, cookies);
        headers.add(HttpHeaders.USER_AGENT, USER_AGENT);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map paramMap = new HashMap();
        paramMap.put("txtLoginUsername", "qinyupei");
        paramMap.put("txtLoginPassword", "ws84207ws");
        paramMap.put("txtLoginCaptcha", "7337");

        HttpEntity<String> requestEntity = new HttpEntity(paramMap, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        System.out.println(responseEntity.getBody());
    }

/*
    @Test
    public void testLottery() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://czj909.com/caiZhiJiaCPLoginWeb/app/lottery";

        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList();
        cookies.add("JSESSIONID=qDgIkuUot4qwl0Gqm1T0ngce.undefined");

        headers.put(HttpHeaders.COOKIE, cookies);
        headers.add(HttpHeaders.USER_AGENT, USER_AGENT);
        headers.setContentType(MediaType.TEXT_HTML);


        HttpEntity<String> requestEntity = new HttpEntity(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        System.out.println(responseEntity.getBody());
    }
*/


    @Test
    public void testPlayHold() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://czj909.com/caiZhiJiaCPLoginWeb/app/playHoldem?6693.2924244659";

        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList();
        cookies.add(COOKIE_SESSION);

        headers.put(HttpHeaders.COOKIE, cookies);
        headers.add(HttpHeaders.USER_AGENT, USER_AGENT);
        headers.setContentType(MediaType.APPLICATION_JSON);


        Map paramMap = new HashMap();
        paramMap.put("product", "LOTTERY_IG");
        paramMap.put("type", "2");
        paramMap.put("line", "0");

        HttpEntity<String> requestEntity = new HttpEntity(paramMap, headers);
        ResponseEntity<HashMap> responseEntity = restTemplate.postForEntity(url, requestEntity, HashMap.class);

        Map respMap = responseEntity.getBody();
        String link = (String) respMap.get("link");

        System.out.println(link);

        System.out.println(respMap.get("success"));

        headers.setContentType(MediaType.TEXT_HTML);


        requestEntity = new HttpEntity(null, headers);
        ResponseEntity<String> responseEntity2 = restTemplate.exchange(link, HttpMethod.GET, requestEntity, String.class);

        System.out.println(responseEntity2.getStatusCodeValue());
    }


    @Test
    public void testPost4() {
        /*
         * sessionId = $('#sessionId').val();
         * */

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://ssc.cdflzy.cn/lotteryweb/WebClientAgent";
        //url = "http://localhost:8082/hi";

        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList();
        cookies.add("JSESSIONID=kkqhZtNrqpB0SwRpHyl3-XtcIsfTJNXvSdqjl2EZ.cash-sscweb3");

        headers.put(HttpHeaders.COOKIE, cookies);
        headers.add(HttpHeaders.USER_AGENT, USER_AGENT);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        List list = new ArrayList();
        List l = new ArrayList();
        l.add("BIG");
        l.add("BALL_1");
        l.add("1");
        l.add(1.98);

        list.add(l);

        MultiValueMap paramMap = new LinkedMultiValueMap();
        paramMap.add("command", "BET");
        paramMap.add("sessionId", "57173f45-a908-45b7-b692-2166a2301f8c");
        paramMap.add("oddsAdapt", "true");

        paramMap.add("bets", JacksonUtil.toJson(list));
        paramMap.add("gameType", "XYFT");


        paramMap.add("timestamps", String.valueOf(System.currentTimeMillis()));
        paramMap.add("hasPlayerInfo", "true");

        HttpEntity<MultiValueMap> requestEntity = new HttpEntity(paramMap, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        System.out.println(responseEntity.getBody());
    }

}
