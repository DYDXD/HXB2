package studyabstract;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 16:14 2019/3/22
 * @Modified By:
 */
public abstract class Mockeys implements Mockey{

    @Override
    public void loveBanane() {
        System.out.println("banana");
        this.sayHello();
    }

    abstract void sayHello();
}
