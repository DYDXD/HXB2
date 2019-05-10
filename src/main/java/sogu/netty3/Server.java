package sogu.netty3;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 10:34 2019/4/26
 * @Modified By:
 */
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {
    private Integer port=8787;

    public void start(){
        ChannelFactory factory = new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),//boss线程池
                Executors.newCachedThreadPool(),//worker线程池
                8//worker线程数
        );
        ServerBootstrap bootstrap = new ServerBootstrap(factory);
        /**
         * 对于每一个连接channel, server都会调用PipelineFactory为该连接创建一个ChannelPipline
         */
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("handler", new ServerLogicHandler());
                return pipeline;
            }
        });

        Channel channel = bootstrap.bind(new InetSocketAddress(port));

        System.out.println("server start success!");
    }

    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        server.start();
    }
}
