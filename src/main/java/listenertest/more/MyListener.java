package listenertest.more;

import java.util.Date;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:35 2019/3/25
 * @Modified By:
 */
public class MyListener implements EventListener {

    @Override
    public void handleEvent(Event event) {
        System.out.println("--"+new Date().getTime()+this.getClass().getName());
        event.callback();
    }
}
