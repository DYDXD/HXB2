package niostudy;


import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 15:30 2018/8/30
 * @Modified By:
 */
public class server {
    public static void main(String[] args) {
        //服务类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //boss线程监听端口
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();
        //设置niosocket工厂
        serverBootstrap.setFactory(new NioServerSocketChannelFactory(boss,worker));
        serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("encoder",new StringEncoder());
                pipeline.addLast("helloHandler",new HelloHandler());
                return pipeline;
            }
        });
        serverBootstrap.bind(new InetSocketAddress(10101));

        System.out.println("start");


    }
}
