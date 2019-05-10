package test;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 9:43 2018/11/22
 * @Modified By:
 */
public class Btest {
	public static void main(String[] args) {
		String s = "BATCH.01240604.20181121";
		String[] split = s.split("\\.");
		System.out.println(split[2]);

	}
}
