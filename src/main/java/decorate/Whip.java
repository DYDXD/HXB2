package decorate;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 10:20 2019/5/22
 * @Modified By:
 */
public class Whip extends CondimentDecorator {
    private Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public Double cost() {
        return beverage.cost() + .1;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }
}
