package utils;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:55 2019/2/1
 * @Modified By:
 */
public class EncodeUtil {

    protected static final char[] HEX = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
            'D', 'E', 'F'};

    protected static final char[] BINARY = new char[]{'0', '1'};

    // 传入参数为只有01的字符串
    public static byte[] binary(String binaryStr) {
        // 长度不是8倍数的话，无法知道在左边或右边补零，会引起歧义，导致结果不正确
        //		if (binaryStr.length() % 8 != 0) {
        //			throw new IllegalArgumentException("传入的参数长度必须是8的倍数");
        //		}
        StringBuffer accum = new StringBuffer();
        for (int i = 0; i < binaryStr.length(); i += 4) {
            String temp = binaryStr.substring(i, i + 4);
            int value = 0;
            for (int j = 0; j < 4; j++) {
                if (temp.charAt(j) == '1') {
                    value += Math.pow(2, 3 - j);// 计算值
                }
            }
            accum.append(HEX[value]);
        }
        return bcd(accum.toString());
    }

    public static String hex(final byte[] bts, int offset, int length) {//
        if (offset < 0 || length < 0 || bts.length < offset + length - 1) {
            throw new IllegalArgumentException("参数非法：offset:" + offset + ",length:" + length + ",字节长度：" + bts.length);
        }
        byte[] returnBt = new byte[length];
        System.arraycopy(returnBt, 0, bts, offset, length);
        return hex(returnBt);
    }

    // public static String hex(byte[] bParam,int length){
    // byte[] target = new byte[length];
    // System.arraycopy(target, 0, bParam, 0, length);
    // return hex(bParam);
    // }

    /**
     * 将byte数组转化为String类型的十六进制编码格式 本方法实现的思路是： 1）每位byte数组转换为2位的十六进制数
     * 2）将字节左移4位取得高四位字节数值，获取对应的char类型数组编码 3）将字节与0x0F按位与，从而获取第四位的字节，同样获取编码
     */
    public static String hex(byte[] bParam) {
        StringBuilder accum = new StringBuilder();
        for (byte bt : bParam) {
            accum.append(HEX[bt >> 4 & 0x0F]);// &0x0F的目的是为了转换负数
            accum.append(HEX[bt & 0x0F]);
        }
        return accum.toString();
    }

    public static byte[] bcd(int code, int len) {
        return EncodeUtil.bcd(String.valueOf(code), len);
    }

    public static String binary(byte[] bts) {
        // if (bts == null) {
        StringBuffer accum = new StringBuffer();
        for (byte bt : bts) {
            accum.append(binary(bt));
        }
        return accum.toString();
    }

    // 本方法修改于Integer.toBinaryString
    // 参数的每个字节都会转化为8位2进制字符串，如1会转换为00000001
    private static String binary(byte bt) {
        int num = bt & 0xFF;
        char[] arrayOfChar = new char[8];
        int i = 8;
        for (int times = 0; times < 8; times++) {
            arrayOfChar[(--i)] = BINARY[(num & 0x01)];
            num >>>= 1;
        }
        return new String(arrayOfChar);
    }

    /**
     * 压缩BCD编码(8421码)为一个4位表示一个10进制数，即每个字节表示两个数
     * 本方法中的code为10进制数（本方法支持16进制数编码，每两位编为1字节）
     */
    public static byte[] bcd(String code) {
        // 控制byte数组的大小
        int len = code.length() % 2 == 0 ? code.length() / 2 : code.length() / 2 + 1;
        return bcd(code, len);
    }

    /**
     * 非压缩BCD编码(8421码)为一个8位表示一个10进制数 本方法中的code为10进制数）
     *
     * @param code 十进制数
     * @param len  位数
     * @return
     */
    public static byte[] bcd2(int code, int len) {
        return EncodeUtil.bcd2(String.valueOf(code), len);
    }

    /**
     * 非压缩BCD编码(8421码)为一个8位表示一个10进制数 本方法中的code为10进制数）
     *
     * @param code 十进制数
     */
    public static byte[] bcd2(String code) {
        // 控制byte数组的大小
        int len = code.length();
        return bcd2(code, len);
    }

    public static String fromBcd2(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(bytes[i] & 0x0F);
        }
        return sb.toString();
    }

    public static String fromBcdInt(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int val = (((bytes[i] & 0xF0) >> 4) & 0x0F);
            int val2 = (bytes[i] & 0x0F);
            sb.append(val).append(val2);
        }
        return sb.toString();
    }

    public static String fromBcd(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String val = Integer.toHexString(((bytes[i] & 0xF0) >> 4) & 0x0F);
            String val2 = Integer.toHexString(bytes[i] & 0x0F);
            sb.append(val).append(val2);
        }
        return sb.toString();
    }

    /**
     * 非压缩bcd实现
     *
     * @param code
     * @param length
     * @return
     */
    public static byte[] bcd2(String code, int length) {
        if (length < 0) {
            throw new IllegalArgumentException("参数length不能小于0,length:" + length);
        } else if (length == 0) {
            return new byte[0];
        }
        byte[] bt = new byte[length];
        if (code.length() < length) {
            code = addBlankLeft(code, length - code.length(), "0");
        }

        // //处理奇数位的值
        // if(code.length()%2!=0){
        // //将第一位数直接放入第一个字节中
        // //@see @Character.digit
        // //这里有个常识，(byte)int会转化为对应的int值字节，比如5会被转为0b00000101
        // bt[0] = (byte)(Character.digit(code.charAt(0),16));
        // point++;
        // }
        // 指示当前位置
        for (int point = 0; point < code.length(); point++) {
            bt[point] = (byte) (code.charAt(point));
        }
        return bt;
    }

    /**
     * @param code
     * @param length
     * @return
     */
    public static byte[] bcd(String code, int length) {
        if (length < 0) {
            throw new IllegalArgumentException("参数length不能小于0,length:" + length);
        } else if (length == 0) {
            return new byte[0];
        }
        byte[] bt = new byte[length];
        // 指示当前位置
        int point = 0;
        if (code.length() < 2 * length) {
            code = addBlankRight(code, 2 * length - code.length(), "0");
        }

        // //处理奇数位的值
        // if(code.length()%2!=0){
        // //将第一位数直接放入第一个字节中
        // //@see @Character.digit
        // //这里有个常识，(byte)int会转化为对应的int值字节，比如5会被转为0b00000101
        // bt[0] = (byte)(Character.digit(code.charAt(0),16));
        // point++;
        // }

        // 每两位合并为一个字节
        for (; point < code.length(); point += 2) {
            // (point+1)/2计算当前指向的值
            // Character.digit将对应的Char转为数字，如'8'-> 8
            // <<4左移四位：即为→_→（右边）的数字让开位置
            if (point + 2 > code.length()) {
                bt[(point + 1) / 2] = (byte) (Character.digit(code.charAt(point), 16) << 4 | Character.digit(0, 16));
            } else {
                bt[(point + 1) / 2] = (byte) (Character.digit(code.charAt(point), 16) << 4 | Character
                        .digit(code.charAt(point + 1), 16));
            }
        }
        return bt;
    }

    public static String addBlankLeft(String origStr, int length, String fill) {
        if (length <= 0) {
            return origStr;
        }
        StringBuffer accum = new StringBuffer();
        for (int i = 0; i < length; i++) {
            accum.append(fill);
        }
        accum.append(origStr);
        return accum.toString();
    }

    public static String addBlankRight(String origStr, int length, String fill) {
        if (length <= 0) {
            return origStr;
        }
        StringBuffer accum = new StringBuffer(origStr);
        for (int i = 0; i < length; i++) {
            accum.append(fill);
        }
        return accum.toString();
    }

    protected static int computeLength(byte[] lenBts) {
        if (lenBts.length != 2) {
            throw new IllegalArgumentException("字节长度不正确，预期值为2，实际值为：" + lenBts.length);
        }
        // int size = ((lenbuf[0] & 0xff) << 8) | (lenbuf[1] & 0xff);//普通的长度编码
        return (lenBts[0] & 0xff) * 256 + (lenBts[1] & 0xff);
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
