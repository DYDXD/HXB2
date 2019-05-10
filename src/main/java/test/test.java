package test;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 16:14 2019/4/28
 * @Modified By:
 */
public class test {
    public static void main(String[] args) {
        String s="GPS\n|datatime\n|sn|lng\n|lat\n|direction\n|stationOrder\n|inOrOut\n|routeId\n|speed\n|angle\n|distance2Next\n";
        String[] split = s.split("\\n\\|");
        System.out.println(split[12]);
    }
}
