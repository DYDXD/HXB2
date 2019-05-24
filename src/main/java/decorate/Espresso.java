package decorate;

/**
 * @Author:XiongbinHuang
 * @Description:浓咖啡
 * @Date:Created in 11:21 2019/5/22
 * @Modified By:
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public Double cost() {
        return 1.99;
    }
}
