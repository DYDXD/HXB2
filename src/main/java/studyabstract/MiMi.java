package studyabstract;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 16:16 2019/3/22
 * @Modified By:
 */
public class MiMi extends Mockeys {

    public MiMi() {
    }


    @Override
    void sayHello() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        MiMi miMi = new MiMi();

        miMi.loveBanane();
    }
}
