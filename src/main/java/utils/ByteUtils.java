//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package utils;

import org.apache.commons.lang.StringUtils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class ByteUtils {
    public static final Charset CHARSET_GB2312 = Charset.forName("GB2312");
    public static final Charset CHARSET_UTF_8 = Charset.forName("utf-8");
    public static final String ZERO_STRING = "0";

    public ByteUtils() {
    }

    public static byte[] longToByte(long number) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(number);
        return buffer.array();
    }

    public static long byteToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getLong();
    }

    public static byte[] intToByte(int number) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(number);
        return buffer.array();
    }

    public static int byteToInt(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getInt();
    }

    public static long byteToUnsignedInt(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.put(bytes);
        buffer.flip();
        return (long)buffer.getInt() & -1L;
    }

    public static byte[] shortToByte(short number) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort(number);
        return buffer.array();
    }

    public static short byteToShort(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getShort();
    }

    public static int byteBufferToUnsignedShort(ByteBuffer byteBuffer) {
        return byteBuffer.getShort() & '\uffff';
    }

    public static int byteToUnsignedShort(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getShort() & '\uffff';
    }

    public static short byteToUnsignedByte(byte abyte) {
        return (short)(abyte & 255);
    }

    public static byte[] intToByte4(int i) {
        byte[] targets = new byte[]{(byte)(i >> 24 & 255), (byte)(i >> 16 & 255), (byte)(i >> 8 & 255), (byte)(i & 255)};
        return targets;
    }

    public static byte[] longToByte8(long lo) {
        byte[] targets = new byte[8];

        for(int i = 0; i < 8; ++i) {
            int offset = (targets.length - 1 - i) * 8;
            targets[i] = (byte)((int)(lo >> offset & 255L));
        }

        return targets;
    }

    public static byte[] longToByte6(long lo) {
        byte[] targets = new byte[6];

        for(int i = 0; i < 6; ++i) {
            int offset = (targets.length - 1 - i) * 8;
            targets[i] = (byte)((int)(lo >> offset & 255L));
        }

        return targets;
    }

    public static byte[] longToByte4(long lo) {
        return intToByte4((int)lo);
    }

    public static byte[] unsignedShortToByte2(int s) {
        byte[] targets = new byte[]{(byte)(s >> 8 & 255), (byte)(s & 255)};
        return targets;
    }

    public static int byteToInt(byte b) {
        return b & 255;
    }

    public static int byte2ToUnsignedShort(byte[] bytes) {
        return byte2ToUnsignedShort(bytes, 0);
    }

    public static int byte2ToUnsignedShort(byte[] bytes, int off) {
        int high = bytes[off];
        int low = bytes[off + 1];
        return high << 8 & '\uff00' | low & 255;
    }

    public static short byte2Short(byte[] b) {
        return (short)((b[0] & 255) << 8 | b[1] & 255);
    }

    public static int byte4ToInt(byte[] bytes, int off) {
        int b0 = bytes[off] & 255;
        int b1 = bytes[off + 1] & 255;
        int b2 = bytes[off + 2] & 255;
        int b3 = bytes[off + 3] & 255;
        return b0 << 24 | b1 << 16 | b2 << 8 | b3;
    }

    public static int byte4ToInt(byte[] bytes) {
        return byte4ToInt(bytes, 0);
    }

    public static long byte4ToUnsignedInt(byte[] bytes) {
        return byte4ToUnsignedInt(bytes, 0);
    }

    public static long byte4ToUnsignedInt(byte[] bytes, int off) {
        long b0 = (long)(bytes[off] & 255);
        long b1 = (long)(bytes[off + 1] & 255);
        long b2 = (long)(bytes[off + 2] & 255);
        long b3 = (long)(bytes[off + 3] & 255);
        return b0 << 24 | b1 << 16 | b2 << 8 | b3;
    }

    public static long byte6ToLong(byte[] bytes) {
        return byte6ToLong(bytes, 0);
    }

    public static long byte6ToLong(byte[] bytes, int offset) {
        long l0 = (long)(bytes[offset] & 255);
        long l1 = (long)(bytes[offset + 1] & 255);
        long l2 = (long)(bytes[offset + 2] & 255);
        long l3 = (long)(bytes[offset + 3] & 255);
        long l4 = (long)(bytes[offset + 4] & 255);
        long l5 = (long)(bytes[offset + 5] & 255);
        return l0 << 40 | l1 << 32 | l2 << 24 | l3 << 16 | l4 << 8 | l5;
    }

    public static long byte8ToLong(byte[] bytes) {
        long l0 = (long)(bytes[0] & 255);
        long l1 = (long)(bytes[1] & 255);
        long l2 = (long)(bytes[2] & 255);
        long l3 = (long)(bytes[3] & 255);
        long l4 = (long)(bytes[4] & 255);
        long l5 = (long)(bytes[5] & 255);
        long l6 = (long)(bytes[6] & 255);
        long l7 = (long)(bytes[7] & 255);
        return l0 << 56 | l1 << 48 | l2 << 40 | l3 << 32 | l4 << 24 | l5 << 16 | l6 << 8 | l7;
    }

    public static byte[] toByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            throw new IllegalArgumentException("this string must not be empty");
        } else {
            hexString = hexString.toLowerCase();
            if (hexString.length() % 2 != 0) {
                hexString = "0" + hexString;
            }

            byte[] byteArray = new byte[hexString.length() / 2];
            int k = 0;

            for(int i = 0; i < byteArray.length; ++i) {
                byte high = (byte)(Character.digit(hexString.charAt(k), 16) & 255);
                byte low = (byte)(Character.digit(hexString.charAt(k + 1), 16) & 255);
                byteArray[i] = (byte)(high << 4 | low);
                k += 2;
            }

            return byteArray;
        }
    }

    public static byte[] toByteArray(String hexString, int len) {
        if (StringUtils.isEmpty(hexString)) {
            return new byte[len];
        } else {
            byte[] resByteArray = new byte[len];
            byte[] byteArray = toByteArray(hexString);
            int arrayLen = len;
            if (byteArray.length < len) {
                arrayLen = byteArray.length;
            }

            System.arraycopy(byteArray, 0, resByteArray, 0, arrayLen);
            return resByteArray;
        }
    }

    public static byte[] decToByteArray(String decString) {
        return toByteArray(String.format("%02x", Integer.parseInt(decString, 10)));
    }

    public static String toHexString(byte[] byteArray) {
        if (byteArray != null && byteArray.length >= 1) {
            StringBuilder hexString = new StringBuilder();

            for(int i = 0; i < byteArray.length; ++i) {
                if ((byteArray[i] & 255) < 16) {
                    hexString.append("0");
                }

                hexString.append(Integer.toHexString(255 & byteArray[i]));
            }

            return hexString.toString().toLowerCase();
        } else {
            throw new IllegalArgumentException("this byteArray must not be null or empty");
        }
    }

    public static String toDecString(byte[] byteArray) {
        String hexString = toHexString(byteArray);
        return Integer.toString(Integer.parseInt(hexString, 16), 10);
    }

    public static byte[] getBytes(String str) {
        return StringUtils.isEmpty(str) ? new byte[0] : str.getBytes(Charset.forName("utf-8"));
    }

    public static String getString(byte[] bytes) {
        if (null != bytes && bytes.length != 0) {
            String resStr = new String(bytes, CHARSET_UTF_8);
            return resStr.trim();
        } else {
            return "";
        }
    }

    public static String getString(byte[] bytes, Charset charset) {
        if (null != bytes && bytes.length != 0 && charset != null) {
            String resStr = new String(bytes, charset);
            return resStr.trim();
        } else {
            return "";
        }
    }


    public static byte[] getPhoneByte11(String phone) {
        byte[] phoneBytes = new byte[11];
        phoneBytes[0] = (byte)phone.length();
        if (phone.length() % 2 != 0) {
            phone = phone + "0";
        }

        byte[] phoneHexBytes = toByteArray(phone);
        System.arraycopy(phoneHexBytes, 0, phoneBytes, 1, phoneHexBytes.length);
        return phoneBytes;
    }

    public static byte[] getStringBytes(String data, int length, Charset charset) {
        byte[] dataBytes = new byte[length];
        if (data == null) {
            data = "";
        }

        if (data.length() < length) {
            data = StringUtils.leftPad(data, length, "0");
        }

        byte[] dataStrBytes = data.getBytes(charset);
        if (dataStrBytes.length < length) {
            length = dataStrBytes.length;
        }

        System.arraycopy(dataStrBytes, 0, dataBytes, 0, length);
        return dataBytes;
    }

    public static byte[] getStringBytes(String data, int length) {
        return getStringBytes(data, length, CHARSET_GB2312);
    }

}
