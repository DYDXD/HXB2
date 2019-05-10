package listenertest.more;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:31 2019/3/25
 * @Modified By:
 */
public interface Event extends Serializable {

    Object getSource();

    Date getWhen();

    String getMessage();

    /**
     * 事件回调方法
     */
    void callback();
}
