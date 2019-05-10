import com.arronlong.httpclientutil.exception.HttpProcessException;
import utils.MobileAUtils;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 15:43 2018/10/14
 * @Modified By:
 */
public class MobileTest {
    public static void main(String[] args) {
        a();

    }

    public static String a() {
        try {
            String area = MobileAUtils.findArea("13215975752");
            System.out.println(area);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
