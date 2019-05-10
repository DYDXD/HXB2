package listenertest.more;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:36 2019/3/25
 * @Modified By:
 */
public class MySource implements EventSource {

    private List<EventListener> listeners = new ArrayList<EventListener>();

    private int value;

    @Override
    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void notifyListener() {
        for (EventListener listener : listeners) {
            MyEvent event = new MyEvent();
            event.setSource(this);
            event.setWhen(new Date());
            event.setMessage("setValue " + value);
            listener.handleEvent(event);
            System.out.println("--"+new Date().getTime()+this.getClass().getName());
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        notifyListener();
    }


    public void testEventListener() {
        MySource source = new MySource();
        source.addListener(new MyListener());
        source.setValue(100);
    }
}
