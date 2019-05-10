package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 10:40 2019/4/23
 * @Modified By:
 */
public class C {
    public static void main(String[] args) {
        String s="[{\"userId\":20190424,\"avatar\":\"df865aeff6561b8c743ba021f9b1f5d3.jpg\"}]";
        String result = StringEscapeUtils.unescapeJava(s);
        JSONArray array = JSON.parseArray(result);
        for (Object o : array) {
            JSONObject object = (JSONObject) o;
            String userId = object.getString("userId");
            String avatar = object.getString("avatar");
        }
    }
}
