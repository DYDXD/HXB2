package listenertest.more;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:30 2019/3/25
 * @Modified By:
 */
public interface EventListener {

    /**
     * 事件触发
     * @param event
     */
    void handleEvent(Event event);
}
