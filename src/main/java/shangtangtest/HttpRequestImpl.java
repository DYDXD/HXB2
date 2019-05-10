package shangtangtest;

import java.io.*;
import java.net.Socket;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 16:45 2019/4/3
 * @Modified By:
 */


public class HttpRequestImpl {
    private final static String encoding = "GBK";

    public static void main(String[] args) throws IOException {
        //访问百度
        Socket socket = new Socket("www.baidu.com", 80);
        //使用字符输出流 写
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
        //按照HTTP协议，构造请求
        StringBuffer sb = new StringBuffer();
        String content = "address";
        //请求行
        sb.append("POST www.baidu.com:80 HTTP/1.1\r\n");
        //请求头
        //报文头属性告诉服务端 客户端接受什么类型的响应。
        sb.append("Accept:*/*\r\n");
        sb.append("Accept-Language:zh-cn\r\n");
        sb.append("User-Agent:javaSocket/").append(System.getProperty("java.version")).append("\r\n");
        //远程服务器的域名
        sb.append("Host:www.baidu.com\r\n");
        sb.append("Connection:close\r\n");
        sb.append("Content-Type:text/html;" + "charset=" + encoding + "\r\n");
        sb.append("Content-Length:" + content.length() + "\r\n");
        sb.append("\r\n");
        sb.append(content + "\r\n");
        sb.append("\r\n");
        System.out.println("发送请求");
        System.out.println(sb.toString());
        outputStreamWriter.write(sb.toString());
        outputStreamWriter.flush();

        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, encoding);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = "";
        int i = 0;
        while ((str = bufferedReader.readLine()) != null) {
            /*fout.write((str + "\r\n").getBytes());
            fout.flush();*/
            System.out.println((i++) + ":" + str);
        }
        inputStream.close();
        /* fout.close();*/
        outputStreamWriter.close();
        socket.close();
        System.out.println("==============");

    }


}
