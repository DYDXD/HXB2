package test;

import utils.ByteUtil;

import java.awt.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 10:05 2018/11/23
 * @Modified By:
 */
public class A {
    static String a = "1010";
    static String b = "1011";
    byte[] k = {0x11, 0x10, 0x1, 0x11};

    public static void main(String[] args) {
        byte[] bytes1 = new byte[]{(byte)0xB0};
        byte[] bytes = ByteUtil.getStringBytes("0x93",1);
        System.out.println(bytes1[0]);
        //long l = Long.parseLong("E04F3B05812FC48F", 16);
        long l = Long.parseUnsignedLong("E04F3B05812FC48F", 16);
        System.out.println(l);
        //long a = new BigInteger("E04F3B05812FC48F", 16).longValue();
        System.out.println(a);




		/*byte[] k={0x03,0xE5,0x17,0x78,0xB6,0x40,0x10,0x00};
		String a ="280656359001690100";
		Long b =0x03E51778B6401000;

		bytesToLong(k,0,true);
		String a = "C:/despot/APP.98020000.20190128";
		String[] split = a.split("\\/");
		String s = a.replaceAll("98020000", "98000000");
		System.out.println(s);*/
    }

    protected String testA() {
        System.out.println("A");
        return "A";
    }

    public static long bytesToLong(byte[] input, int offset, boolean littleEndian) {
        ByteBuffer buffer = ByteBuffer.wrap(input, offset, 8);
        if (littleEndian) {
            buffer.order(ByteOrder.LITTLE_ENDIAN);
        }
        return buffer.getLong();
    }

    //给定一个数组 返回第一个中心索引坐标
    public static int pivotIndex(int[] nums) {
        int sumLeft = 0;
        int sumRight = 0;
        int sumAll = 0;
        for (int num : nums) {
            sumAll = num + sumAll;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                sumLeft = 0;
            } else {
                sumLeft = sumLeft + nums[i - 1];
            }
            sumRight = sumAll - sumLeft - nums[i];
            if (sumLeft == sumRight) {
                return i;
            }

        }

        return -1;
    }


    public static String addBinary(String a, String b) {
        Byte aLong = Byte.valueOf(a);
        Byte bLong = Byte.valueOf(b);


        return aLong.toString();

    }




}
