package decorate;

/**
 * @Author:XiongbinHuang
 * @Description:黑咖啡
 * @Date:Created in 11:25 2019/5/22
 * @Modified By:
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description="HouseBlend Coffee";
    }

    @Override
    public Double cost() {
        return 0.89;
    }
}
