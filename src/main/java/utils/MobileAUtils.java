package utils;


import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpCookies;
import com.arronlong.httpclientutil.common.HttpHeader;


import org.apache.http.client.HttpClient;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author:XiongbinHuang
 * @Description //1.正则
 * //2.厦门号码本地验证--针对优化
 * //3.非厦门号码，本地保存
 * //4.多节点实现
 * //5.适配
 * @Date:Created in 11:55 2018/10/10
 * @Modified By:
 */
public final class MobileAUtils {
    //这边只做厦门的 其他的之后的添加应该用Map实现吧
    private static Set<String> xmSet = new HashSet<>();

    private static Map<String, String> otherMap = new HashMap<>();

    //1.正则--确保输入手机号为正确格式的手机号
    private static boolean regex(String mobileNo) {
        String mobileRegex = "^((13[0-9])|(15[0-9])|（18[0-9]）)\\d{8}";
        boolean matches = mobileNo.matches(mobileRegex);
        return matches;
    }

    //2.厦门号码本地验证
    //问题1：没全读取。。。
    private static boolean xmFind(String mobileNo) throws Exception {
        //读取resource数据
        File file = new File("C:\\Users\\35325\\Desktop\\HXB\\src\\main\\resources\\xmPhoneNo");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (!(bufferedReader.readLine() == null)) {
            String[] split = bufferedReader.readLine().split(" ");
            for (int i = 0; i < split.length; i++) {
                xmSet.add(split[i].trim());
            }
        }
        //本地验证
        if (xmSet.contains(mobileNo.substring(0, 7))) {
            return true;
        } else {
            return false;
        }


    }

    //暴露的统一接口
    public static String findArea(String mobileNo) throws Exception {
        if (!regex(mobileNo)) {
            return "请输入正确的手机号码";
        }
        if (xmFind(mobileNo)) {
            return "中国，福建，厦门";
        }
        if (otherMap.containsKey(mobileNo.substring(0, 7))){
            return otherMap.get(mobileNo.substring(0, 7));
        }
        // 设置header信息
        org.apache.http.Header[] headers = HttpHeader.custom().userAgent(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36")
                .accept("application/json, text/javascript, */*; q=0.01").acceptLanguage("zh-CN,zh;q=0.9")
                .contentType("text/html; charset=utf-8")
                .acceptEncoding("gzip, deflate").build();
        HttpClient client = HCB.custom().timeout(10000).ssl().build();//采用默认方式（绕过证书验证）

        HttpCookies cookies = HttpCookies.custom();
        HttpConfig httpConfig = HttpConfig.custom();
        httpConfig.client(client).headers(headers).context(cookies.getContext()).inenc("utf-8");
        //节点1
        String apiUrl3 = "http://api.k780.com:88/?app=phone.get&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&phone={0}";
        httpConfig.url(MessageFormat.format(apiUrl3, mobileNo));
        System.out.println(httpConfig.toString());
        String result = HttpClientUtil.get(httpConfig);
        JSONObject object = JSONObject.parseObject(result);
        JSONObject result3 = (JSONObject) object.get("result");
        Object att = result3.get("att");
        if (att != null) {
            otherMap.put(mobileNo.substring(0, 7), att.toString());
            return att.toString();
        }
        //节点2 接口返回格式失败。。失败的接口2
        String apiUrl = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel={0}";
        httpConfig.url(MessageFormat.format(apiUrl, mobileNo));
        String result1 = HttpClientUtil.get(httpConfig);
        System.out.println(result1);
        JSONObject object1 = JSONObject.parseObject(result1);
        JSONObject result11 = (JSONObject) object1.get("result");
        Object taobao = result11.get("att");
        if (taobao != null) {
            otherMap.put(mobileNo.substring(0, 7), taobao.toString());
            return taobao.toString();
        }
        //节点3
        String apiUrl2 = "https://chongzhi.jd.com/json/order/search_searchPhone.action?mobile={0}";
        httpConfig.url(MessageFormat.format(apiUrl2, mobileNo));
        String result2 = HttpClientUtil.get(httpConfig);
        System.out.println(result2);
        JSONObject object2 = JSONObject.parseObject(result2);
        String jd = (String) object2.get("areaName");
        if (jd != null) {
            otherMap.put(mobileNo.substring(0, 7), "中国,"+jd.toString()+",xx");
            return jd.toString();
        }
        return "error：服务繁忙";
    }

}
