package decorate;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 16:58 2019/5/22
 * @Modified By:
 */
public class Mochas extends CondimentDecorator {
    Beverage beverage;

    public Mochas(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",mocha";
    }

    @Override
    public Double cost() {
        return beverage.cost() + 0.2;
    }
}
