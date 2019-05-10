package shangtangtest;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 21:03 2019/4/17
 * @Modified By:
 */
public class Test5 {
    public static void main(String[] args) {

        OutputStream down = null;
        HttpConfig custom = HttpConfig.custom();
        try {
            down = HttpClientUtil.down(custom);
        } catch (HttpProcessException e) {
            e.printStackTrace();
        }
        byte[] b = new byte[111];
        try {
            down.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
