package decorate;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 9:44 2019/5/22
 * @Modified By:
 */
//饮料
public abstract class Beverage {
    String description="Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract Double cost();
}
