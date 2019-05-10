package utils;


import org.apache.commons.codec.binary.Hex;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:58 2019/2/1
 * @Modified By:
 */
public class MD5Util {


    public static String encryptSHA1(String str) {
        return encrypt(str.getBytes(Constant.CHARSET), "SHA1");
    }

    public static String encrypt(String str) {
        return encrypt(str.getBytes(Constant.CHARSET), "MD5");
    }

    public static String encrypt(byte[] bytes) {
        return encrypt(bytes, "MD5");
    }

    public static String encrypt(byte[] bytes, String algorithm) {
        String encryptStr = "";
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(bytes);
            byte b[] = md.digest();
            StringBuilder buf = new StringBuilder();
            for (int offset = 0, i; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            encryptStr = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptStr;
    }

    /**
     * Get MD5 of one file:hex string,test OK!
     *
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ex) {
                    try {
                        in.close();
                    } catch (Exception ex2) {

                    }
                }
            }
        }
        return Hex.encodeHexString(digest.digest());
        // // 可能为负数，前置0会被去掉
        // BigInteger bigInt = new BigInteger(1, digest.digest());
        // return bigInt.toString(16);
    }

    /***
     * Get MD5 of one file！test ok!
     *
     * @param filepath
     * @return
     */
    public static String getFileMD5(String filepath) {
        File file = new File(filepath);
        return getFileMD5(file);
    }
}
