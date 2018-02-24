import com.diyiliu.util.JacksonUtil;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
    public void testPost3() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://czj909.com/caiZhiJiaCPLoginWeb/app/loginVerification?5943.037352414396";
        url = "http://localhost:8082/hi";

        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList();
        cookies.add("JSESSIONID=tvWncGRvHuZo7tOH3-2sG7fE.undefined");

        headers.put(HttpHeaders.COOKIE, cookies);
        headers.add(HttpHeaders.USER_AGENT, USER_AGENT);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);


        Map paramMap = new HashMap();
        paramMap.put("txtLoginUsername", "qinyupei");
        paramMap.put("txtLoginPassword", "ws84207ws");
        paramMap.put("txtLoginCaptcha", "5134");

        HttpEntity<String> requestEntity = new HttpEntity(paramMap, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        System.out.println(responseEntity.getBody());
    }

}
