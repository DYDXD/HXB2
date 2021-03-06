package listenertest.more;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:35 2019/3/25
 * @Modified By:
 */
public class MyEvent implements Event {

    private Log log = LogFactory.getLog(getClass());

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private Object source;

    private Date when;

    private String message;

    @Override
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    @Override
    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void callback() {
        log.info(toString()+"--"+new Date().getTime()+this.getClass().getName());
    }

    @Override
    public String toString() {
        return "source: " + getSource() + ", message: " + getMessage() + ", when: " + sdf.format(getWhen());
    }
}
