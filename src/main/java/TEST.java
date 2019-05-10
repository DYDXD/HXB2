import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.runners.parameterized.BlockJUnit4ClassRunnerWithParameters;
import org.springframework.util.Assert;
import utils.ByteUtils;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 19:37 2018/8/22
 * @Modified By:
 */
public class TEST {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\35325\\Desktop\\房租申报材料\\test.jpg");
        try {
            //读取流
            FileInputStream fis = new FileInputStream(file);
            //写出流
            OutputStream fos = new FileOutputStream("C:\\Users\\35325\\Desktop\\房租申报材料\\户口2.jpg");
            //开辟一个缓存数组，可以被重复使用（或者一个足够大的数组）文件大小应该尽量大于缓存数组
            byte[] bytes = new byte[10000];
            int len = 0;
            while ((len = fis.read(bytes)) != -1) {
                System.out.println(len);
                fos.write(bytes, 0, len);
            }
            fos.close();
            fis.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static String getString() {
        String a = "122345656";
        int length = a.length();
        System.out.println(length);

        byte[] bytes = new byte[]{0b01111111, 0};

        //>> 4 & 15;
        System.out.println(0b11111111);
        int i = (bytes[0] & 240);

        System.out.println(i);
        return a;
    }

    public void newInst(String task) {
        Thread thread = new Thread();
        thread.run();

    }


}



















