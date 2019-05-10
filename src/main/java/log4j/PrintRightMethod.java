package log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 17:35 2019/4/10
 * @Modified By:
 */
public class PrintRightMethod {
    private static Logger logger = LoggerFactory.getLogger(PrintRightMethod.class);
    public static void main(String[] args) {
        try {
            int i=1;
            int y=0;
            int x;
            x=i/y;


        }catch (Exception e){
            logger.warn("error",e);
            logger.warn("error={},{}",e,e);
            logger.warn("error={}",e.toString());



        }





    }
}
