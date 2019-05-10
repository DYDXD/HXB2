import utils.CheckUtil;

import java.beans.Introspector;
import java.lang.reflect.Field;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 9:47 2018/10/17
 * @Modified By:
 */
public class Check {
    String name;
    String id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public static void main(String[] args) {

    }

    public static byte calculateMacByte(byte[] bytes) {
        //FIXME 补充实现
        byte[] res = new byte[1];
        for (int i = 5; i < bytes.length - 1; i++) {
            Integer result = bytes[i] ^ bytes[i + 1];
            bytes[i + 1] = result.byteValue();
            res[0] = bytes[i + 1];
        }
        return res[0];
    }


}
