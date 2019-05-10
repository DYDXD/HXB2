package utils;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 10:37 2018/10/18
 * @Modified By:
 */
public class CheckCodeUtil {
    public byte calculateMacByte(byte[] bytes) {
        //FIXME 补充实现
        byte[] bytes1 = new byte[1];
        for (int i = 5; i < bytes.length - 1; i++) {
            Integer i1 = bytes[i] ^ bytes[i + 1];
            bytes[i + 1] = i1.byteValue();
            bytes1[0] = bytes[i + 1];
        }
        return bytes1[0];
    }
}
