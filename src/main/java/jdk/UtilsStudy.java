package jdk;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 15:48 2019/5/20
 * @Modified By:
 */
public class UtilsStudy {
    //array-->list
    static <E> List convert2List(E[] e) {
        List<E> es = Arrays.asList(e);

        return es;
    }

    //array-->set
    static <E> Set convert2Set(E[] e) {
        List<E> es = Arrays.asList(e);
        Set<E> es1 = new HashSet<>(es);
        return es1;
    }

    public static void main(String[] args) {
        Integer[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        String[] strs = {"0", "0", "1", "1", "1", "2", "2", "3", "3", "4"};
        List list = convert2List(nums);
        Set set = convert2Set(nums);
        System.out.println("list size=" + list.size() + ",set size =" + set.size());
        List list2 = convert2List(strs);
        Set set2 = convert2Set(strs);
        System.out.println("list size=" + list2.size() + ",set size =" + set2.size());

    }


}
