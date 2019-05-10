package niostudy;

import org.jboss.netty.channel.*;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 16:01 2018/8/30
 * @Modified By:
 */
public class HelloHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("Receive message from Client.....");

        String clientData = (String) e.getMessage();
        System.out.println("The message receive from Client is : " + clientData);

        Channel ch = e.getChannel();
        String responseMessage = "success";
        ctx.getChannel().write(responseMessage);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("已经建立起连接");
        super.channelConnected(ctx, e);
    }
}
