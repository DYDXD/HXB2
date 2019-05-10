package faceplus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 14:50 2019/4/15
 * @Modified By:
 */
public class Test {

    public static void main(String[] args) {
        String url = "http://172.16.52.240:9090/images/";
        try {
            HttpRequest.downLoadFromUrl(url, "0b565884e9b468d963167fab33533f6c.jpg", "C:\\Users\\35325\\Desktop\\人脸\\download");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
