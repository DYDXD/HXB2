package listenertest.more;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:30 2019/3/25
 * @Modified By:
 */
public interface EventSource {

    /**
     * 增加监听器
     * @param listener
     */
    void addListener(EventListener listener);

    /**
     * 通知监听器
     */
    void notifyListener();
}
