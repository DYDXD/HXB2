package listenertest;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 10:27 2019/3/25
 * @Modified By:
 */
public class TestListener {

    public static void main(String[] args) {
        Person person = new Person();
        person.registerListener(new MyPersonListener());
        person.run();
        person.eat();
    }
}

//实现监听器接口中的方法
class MyPersonListener implements PersonListener {

    @Override
    public void dorun(Event even) {
        Person person = even.getPerson();
        //拿到事件源
        System.out.println("预备跑");
    }

    @Override
    public void doeat(Event even) {
        System.out.println("喝口水");
    }


}
