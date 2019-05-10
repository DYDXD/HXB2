package listenertest.more;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:37 2019/3/25
 * @Modified By:
 */
public class Test {
    public static void main(String[] args) {
        MySource source = new MySource();
        source.addListener(new MyListener());
        source.setValue(100);
    }
}
