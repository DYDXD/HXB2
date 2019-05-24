package jdk;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 9:27 2019/5/20
 * @Modified By:
 */
public class JdkStudy {
    private static int j;

    public static void main(String[] args) {
        T();
    }



    static void T(){
        int i;
        System.out.println(j);
        //局部变量必须赋值，这是一种规范，因为运行的先后顺序是确定的。同时
        System.out.println(1);
    }

    static void ts(){
        try {
            Class.forName("").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
















}
