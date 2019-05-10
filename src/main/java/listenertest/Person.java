package listenertest;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 9:50 2019/3/25
 * @Modified By:
 */
/*事件源*/
public class Person {
    /*首先定义一个私有的，空的监听器对象，用来接收传递进来的事件监听器（相当于一个全局变量）*/
    private PersonListener personListener;

    /*将传递进来的事件监听器保存到全局变量中*/
    public void registerListener(PersonListener personListener) {
        this.personListener = personListener;
    }

    public void run() {
        if (personListener != null) {
            Event event = new Event(this);
            this.personListener.dorun(event);
        }
        System.out.println("人具有跑的方法");
    }

    public void eat() {
        if (personListener != null) {
            Event event = new Event(this);
            this.personListener.doeat(event);
        }
        System.out.println("人具有吃的方法");
    }


}
