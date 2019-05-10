package http;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;

import java.util.HashMap;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 9:15 2019/5/6
 * @Modified By:
 */
public class HttpStudy {

    public static void main(String[] args) {
        arronLong();


    }

    private static void arronLong() {
        Object o = new Object();
        String s1 = new String();

        HttpConfig httpConfig = HttpConfig.custom();
        httpConfig.url("");
        httpConfig.map(new HashMap<String, Object>());
        try {
            httpConfig.client(HCB.custom().timeout(1000).ssl().build());
            String post = HttpClientUtil.post(httpConfig);
            String s = HttpClientUtil.get(httpConfig);
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }
    }


}
