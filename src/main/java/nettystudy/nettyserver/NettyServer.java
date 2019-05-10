package nettystudy.nettyserver;


/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 10:48 2019/3/15
 * @Modified By:
 */

/*
public class NettyServer {
    private Integer port;
    private String address;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public NettyServer() {
    }

    public NettyServer(Integer port, String address) {
        this.port = port;
        this.address = address;
    }

    public void init() {
        //第一步 一般先定义好线程池管理器
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        //第二步 定义好bootstrap (ServerBootStrap)
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //第三步 设置使用的Channel类型为NioSocketChannel
        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new MyChannelInitializer());
        ChannelFuture bind = serverBootstrap.bind(address, port);
        try {
            bind.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //可值得信赖的finally 用于关闭事件线程池
            shutdown();
        }


    }

    public void shutdown() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }


    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
*/
