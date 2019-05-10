package reactorserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 15:39 2018/8/17
 * @Modified By:
 */
public abstract class Reactor extends Thread {

    protected final int port;
    protected final ServerSocketChannel serverSocketChannel;
    protected final boolean isMainReactor;
    protected final boolean useMultipleReactors;
    protected final long timeout;
    protected  Selector selector;

    public Reactor(int port, ServerSocketChannel serverSocketChannel, boolean isMainReactor, boolean useMultipleReactors, long timeout) {
        this.port = port;
        this.serverSocketChannel = serverSocketChannel;
        this.isMainReactor = isMainReactor;
        this.useMultipleReactors = useMultipleReactors;
        this.timeout = timeout;

    }


    /*java NIO框架*/
    private void init() throws IOException {
        selector = Selector.open();

        if (isMainReactor){
            /*套接字绑定监听端口*/
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            /*设置信道为非阻塞*/
            serverSocketChannel.configureBlocking(false);
            SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            key.attach(newAcceptor(selector));

        }else {

        }
    }
    public abstract Acceptor newAcceptor(Selector selector);

    private void dispatcher(SelectionKey key){
        Runnable r = (Runnable) key.attachment();
        if (r!=null){
            r.run();
        }

    }
    @Override
    public void run() {
        try {
            init();
            while (!Thread.interrupted()){
                if (selector.select(timeout)>0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        dispatcher(key);
                        iterator.remove();
                    }
                }
            }





        } catch (IOException e) {


        }


    }
}
