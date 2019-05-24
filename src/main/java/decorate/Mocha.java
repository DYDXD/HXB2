package decorate;

import decorate.Beverage;
import decorate.CondimentDecorator;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 10:20 2019/5/22
 * @Modified By:
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.description + ", Mocha";

    }

    @Override
    public Double cost() {
        return beverage.cost() + 0.2;
    }
}
