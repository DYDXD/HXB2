import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:45 2019/3/15
 * @Modified By:
 */
public class Start {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring/application.xml");
        classPathXmlApplicationContext.registerShutdownHook();
    }
}
