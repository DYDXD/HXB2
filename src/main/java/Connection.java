/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 14:59 2018/9/5
 * @Modified By:
 */

public class Connection implements AutoCloseable {
    public void sendData() throws Exception {
        throw new Exception("send data");
    }

    @Override
    public void close() throws Exception {
        throw new Exception("close");
    }
}

