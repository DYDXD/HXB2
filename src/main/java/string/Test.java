package string;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 10:34 2019/5/10
 * @Modified By:
 */
public class Test {
    public static void main(String[] args) {
        A a1 = new A();
        B b1 = new B();
        System.out.println(a1.a==b1.b);//验证驻留字符串--hashTable的存在
        // --由于spring是工厂模式都是单例实例，多线程操作下，容易产生多个线程操作一个引用所指向的对象（由于对象不是final）所以对象可以被修改 导致实例多状态 产生各种读写数据异常
        //通过锁，通过对象锁来保证线程安全
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = (d + 2).intern();
        System.out.println(c);
        System.out.println(e);
        System.out.println((a == c));//驻留字符串速度>堆
        System.out.println((a == e));//堆中的对象加入到驻留字符串
        System.out.println((c == e));//intern方法的使用
        e = (d + 2);
        System.out.println((a == e));//堆中字符串地址与驻留字符串是不同区域
    }
    static class A{
        private String a="1";//private修饰的成员变量相当于final修饰的成员变量=常量（基本数据类型和string）

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }
    static class B{
        private String b="1";

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

}
