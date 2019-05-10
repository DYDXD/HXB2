package test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 10:11 2019/4/11
 * @Modified By:
 */
public class Intern {
    public static void main(String[] args) {
        Set<String> objects = new TreeSet<>();
        objects.add("1");
        objects.add(new String("1"));
        for (String object : objects) {
            System.out.println(object);
        }
        String h = new String("12") + new String("3");
        String h1 = new String("1") + new String("23");

        String h2 = "123";
        String h3 = h.intern();
        String h4 = h1.intern();


        System.out.println(h == h1); // false
        System.out.println(h3 == h4); // true
        System.out.println(h == h3); // true
        System.out.println(h3 == h2); // true
        System.out.println(h4 == h2); // true
    }
}
