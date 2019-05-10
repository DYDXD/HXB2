package utils;

import java.lang.reflect.Field;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 17:08 2018/10/17
 * @Modified By:
 */
public class CheckUtil {

    public static boolean validateEntryObject(Object object) {
        boolean b = true;
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object o = field.get(object);
                if (o == null) {
                    b = false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return b;
    }
}
