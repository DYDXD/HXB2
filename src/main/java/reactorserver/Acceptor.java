package reactorserver;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 15:39 2018/8/17
 * @Modified By:
 */
public abstract class Acceptor extends Thread {

    protected final Selector selector;
    protected final ServerSocketChannel serverSocketChannel;
    protected final boolean useMultipleReactors;

    public Acceptor(Selector selector, ServerSocketChannel serverSocketChannel, boolean useMultipleReactors) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
        this.useMultipleReactors = useMultipleReactors;
    }

    @Override
    public void run() {
        try {
            SocketChannel clientChannel = serverSocketChannel.accept();
            handler(selector,clientChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public abstract void handler(Selector selector, SocketChannel clientChannel);
}
