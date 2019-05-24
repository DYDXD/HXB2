package decorate;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:50 2019/5/22
 * @Modified By:
 */
public class StarbuzzCoffee {

    public static void main(String[] args) {
        Beverage espresso = new Espresso();
        System.out.println(espresso.getDescription() + "$" + espresso.cost());

        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mochas(beverage2);
        beverage2 = new Mochas(beverage2);
        beverage2 = new Whip(beverage2);

        System.out.println(beverage2.getDescription() + "$" + beverage2.cost());

        Beverage beverage3 = new HouseBlend();
        beverage3 = new Soy(beverage3);
        //beverage3 = new decorate.Mocha(beverage3);
        beverage3 = new Whip(beverage3);
        beverage3 = new Whip(beverage3);
        System.out.println(beverage3.getDescription() + " $" + beverage3.cost());

        Beverage beverage4 = new HouseBlend();
        beverage4 = new Mocha(beverage4);
        System.out.println(beverage4.getDescription() + " $" + beverage4.cost());
        beverage4 = new Mocha(beverage4);
        System.out.println(beverage4.getDescription() + " $" + beverage4.cost());

    }
}
