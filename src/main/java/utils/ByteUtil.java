package utils;

import org.apache.commons.lang.StringUtils;

import java.nio.charset.Charset;

import static utils.ByteUtils.ZERO_STRING;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 14:57 2018/10/18
 * @Modified By:
 */
public class ByteUtil {
    public static final Charset GB2312_CHARSET = Charset.forName("GB2312");
    public static final String ZERO_STRING = "0";
    //Java做位运算时候，都扩展成32位
    public static Short byteToShort(byte[] bytes) {
        //由于我们采用大端模式，低地址存放高位，所以[0]对于我们而言是低地址，[1]对于我们是高地址
        //做数组转换的时候，是高位向左移动，所以是s1
        short s1 = (short) (bytes[0] & 0xff);
        short s2 = (short) (bytes[1] & 0xff);
        s1 <<= 8;
        return (short) (s1 | s2);
    }

    /**
     * 16进制的字符串表示转成字节数组
     *
     * @param hexString 16进制格式的字符串
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString))
            throw new IllegalArgumentException("this string must not be empty");

        hexString = hexString.toLowerCase();
        if (hexString.length() % 2 != 0)
            hexString = "0" + hexString;
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {// 因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    /**
     * 字节数组按指定编码转字符串
     *
     * @param bytes
     * @return
     */
    public static String getString(byte[] bytes, Charset charset) {
        if ((null == bytes) || bytes.length == 0 || charset == null) {
            return "";
        }
        String resStr = new String(bytes, charset);
        return resStr.trim();
    }

    public static Integer byteToInt(byte[] bytes) {

        int i1 = bytes[0];
        int i2 = bytes[1];
        int i3 = bytes[2];
        int i4 = bytes[3];
        i1 <<= 24;
        i2 <<= 16;
        i3 <<= 8;

        return (i1 | i2 | i3 | i4);
    }

    public static void main(String[] args) {
        byte[] bytes = {0b00000001, 0b01111111};
        Short aShort = byteToShort(bytes);
        System.out.println(aShort);
        short i1 = ByteUtils.byteToShort(bytes);
        System.out.println(i1);
        short i2 = (short) ByteUtils.byteToUnsignedShort(bytes);
        System.out.println(i2);
        byte[] bytes1 = {0b00000000, 0b00000011, 0b00110011, 0b01000100};
        Integer integer = byteToInt(bytes1);
        int i = ByteUtils.byteToInt(bytes1);
        System.out.println(integer);
        System.out.println(i);
        System.out.println('\uffff');
    }

    public static byte[] getBytes(String str) {
        if (StringUtils.isEmpty(str)) {
            return new byte[0];
        }
        return str.getBytes(Charset.forName("utf-8"));
    }

    public static byte[] getStringBytes(String data, int length) {
        byte[] dataBytes = new byte[length];
        if (data == null) {
            data = StringUtils.EMPTY;
        }
        if (data.length() < length) {
            data = StringUtils.leftPad(data, length, ZERO_STRING);
        }
        byte[] dataStrBytes = data.getBytes(GB2312_CHARSET);
        if (dataStrBytes.length < length) {
            length = dataStrBytes.length;
        }
        System.arraycopy(dataStrBytes, 0, dataBytes, 0, length);
        return dataBytes;
    }

}
