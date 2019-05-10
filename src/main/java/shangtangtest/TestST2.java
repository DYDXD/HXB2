package shangtangtest;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpCookies;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.google.common.collect.Maps;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 17:02 2019/4/1
 * @Modified By:
 */
public class TestST2 {
    public final static String template = "&app_key={}&timestamp={}&sign={}";
    public final static String method = "";


    public static void main(String[] args) {
        // 设置header信息
        Map<String, Object> map = new HashMap<>();
        map.put("avatarFile", new File(""));
        map.put("force", 0);
        int[] group = {};
        map.put("groups", group);
        map.put("icNumber", "");
        map.put("jobNumber", "");
        map.put("mobile", "");
        map.put("name", "");
        map.put("remark", "");
        map.put("app_key", "");
        map.put("sign", "");
        map.put("timestamp", "");
        addUser(map);


    }


    public static void addUser(Map<String, Object> map) {
        String uri="https://host:port/api/v1/user?";
        String template = "&app_key=&timestamp=&sign=";
        try {
            Header[] headers = HttpHeader.custom().userAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36")
                    .accept("application/json, text/javascript, */*; q=0.01")
                    .acceptLanguage("zh-CN,zh;q=0.9")
                    .contentType("image/jpeg; charset=utf-8")
                    .acceptEncoding("gzip, deflate").build();
            HttpClient client = null;//采用默认方式（绕过证书验证）

            client = HCB.custom().timeout(10000).ssl().build();

            HttpCookies cookies = HttpCookies.custom();
            HttpConfig httpConfig = HttpConfig.custom();
            httpConfig.client(client).headers(headers).context(cookies.getContext()).inenc("utf-8");
            httpConfig.url(uri);
            httpConfig.map(map);
            System.out.println(httpConfig.toString());
            String result = HttpClientUtil.post(httpConfig);
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }


    }


}
