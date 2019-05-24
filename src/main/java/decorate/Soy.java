package decorate;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:42 2019/5/22
 * @Modified By:
 */
public class Soy extends CondimentDecorator {
    private Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Soy";
    }

    @Override
    public Double cost() {
        return beverage.cost() + 0.3;
    }
}
