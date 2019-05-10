package shangtangtest;

import java.io.*;
import java.net.Socket;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 16:28 2019/4/9
 * @Modified By:
 */
public class HHHTest {

    public static void main(String[] args) {
        /*File file = new File("C:\\Users\\35325\\Desktop\\123456789\\2.jpg");

        try {
            OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\35325\\Desktop\\123456789\\3.jpg"));
            FileInputStream fileInputStream = new FileInputStream(file);
            //缓冲数组
            byte[] bytes = new byte[1024*1024];
            //读取文件到数组
            fileInputStream.read(bytes);
            //写入数组
            outputStream.write(bytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            Socket socket = new Socket("127.0.0.1", 8379);
            inputStream = new DataInputStream(socket.getInputStream());
            byte[] bytes = new byte[1024 * 1024];
            inputStream.read(bytes);
            System.out.println(bytes.length);

            outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.write(bytes);
            outputStream.flush();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
