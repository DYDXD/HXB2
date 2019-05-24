package decorate;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 10:20 2019/5/22
 * @Modified By:
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description="Dark Roast Coffee";
    }

    @Override
    public Double cost() {
        return 1.2;
    }
}
