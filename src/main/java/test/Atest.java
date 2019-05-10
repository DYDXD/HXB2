package test;

import org.apache.commons.lang.time.FastDateFormat;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 15:11 2018/11/20
 * @Modified By:
 */
public class Atest {

	public static void main(String[] args) {


		Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
		ca.setTime(new Date()); //设置时间为当前时间
		ca.add(Calendar.DATE, -1); //天数减1
		Date yesterday = ca.getTime(); //结果
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String format = sdf.format(yesterday);
		System.out.println(format);
	}
}
