package nettystudy;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 14:44 2019/4/11
 * @Modified By:
 */
/*public class NettyTest {

    //开启一个主线程运行方法
    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer(8080, "127.0.0.1");
        nettyServer.init();


    }

    private static void aa() {
        try {
            FileChannel fileChannel = new RandomAccessFile("C:\\Users\\35325\\Desktop\\123456789\\2.jpg", "rw").getChannel();
            FileChannel fileChannelB = new RandomAccessFile("C:\\Users\\35325\\Desktop\\123456789\\6.jpg", "rw").getChannel();
            fileChannel.isOpen();
            fileChannelB.isOpen();
            ByteBuffer allocate = ByteBuffer.allocate(100);
            fileChannel.read(allocate);
            allocate.flip();

            fileChannelB.write(allocate);
            fileChannel.force(true);
            allocate.clear();
            fileChannelB.close();
            fileChannel.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}*/
