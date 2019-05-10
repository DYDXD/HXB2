import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpCookies;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import org.apache.http.client.HttpClient;
import utils.MobileAUtils;


import java.text.MessageFormat;


/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 15:24 2018/9/6
 * @Modified By:
 */
public class Server {

    public static void main(String[] args) {

        try {
            String area = MobileAUtils.findArea("13215975752");
            System.out.println(area);

        } catch (HttpProcessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
