package test;

import com.google.common.io.Files;
import utils.ByteUtil;
import utils.EncodeUtil;
import utils.MD5Util;

import java.io.*;
import java.nio.charset.Charset;


/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 11:46 2019/2/1
 * @Modified By:
 */
public class FTPauditFileTest {


    public static void main(String[] args) {
        File file = new File("C:\\Users\\35325\\Desktop\\1534927109479\\FTP.98000000.20181226");
        byte[] bytes = resolveFile(file);
        getFile(bytes, "C:\\Users\\35325\\Desktop\\1534927109479", "FTP.00000000.20181226");

    }


    public static byte[] resolveFile(File file) {
        byte[] bytes = null;
        byte[] bytesTotal = null;

        //文件改数组--改写数组内容
        //文件转数组
        try {
            bytes = Files.toByteArray(file);
            bytesTotal = new byte[bytes.length];
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            byte[] byteHeader = new byte[28];
            //审计文件结构
            byte[] byteFileType = new byte[1];
            byte[] byteFileCreateTime = new byte[7];
            byte[] byteFileVersion = new byte[1];
            bis.read(byteFileType);
            bis.read(byteFileCreateTime);
            bis.read(byteFileVersion);
            //4
            byte[] byteTransNodeCode = new byte[4];
            bis.read(byteTransNodeCode);
            byteTransNodeCode = EncodeUtil.bcd("98000000");
            byte[] byteRecordNum = new byte[4];
            byte[] byteFileCloseTime = new byte[7];
            bis.read(byteRecordNum);
            bis.read(byteFileCloseTime);
            System.arraycopy(byteFileType, 0, byteHeader, 0, byteFileType.length);
            System.arraycopy(byteFileCreateTime, 0, byteHeader, byteFileType.length, byteFileCreateTime.length);
            System.arraycopy(byteFileVersion, 0, byteHeader, byteFileType.length + byteFileCreateTime.length,
                    byteFileVersion.length);
            System.arraycopy(byteTransNodeCode, 0, byteHeader,
                    byteFileType.length + byteFileCreateTime.length + byteFileVersion.length, byteTransNodeCode.length);
            System.arraycopy(byteRecordNum, 0, byteHeader,
                    byteFileType.length + byteFileCreateTime.length + byteFileVersion.length + byteTransNodeCode.length,
                    byteRecordNum.length);
            System.arraycopy(byteFileCloseTime, 0, byteHeader,
                    byteFileType.length + byteFileCreateTime.length + byteFileVersion.length + byteTransNodeCode.length
                            + byteRecordNum.length, byteFileCloseTime.length);
            byte[] byteSettleDate = new byte[4];
            bis.read(byteSettleDate);
            System.arraycopy(byteSettleDate, 0, byteHeader, byteHeader.length - byteSettleDate.length,
                    byteSettleDate.length);
            //审计数据文件格式
            byte[] byteFtp = new byte[bytes.length - 44];
            int idx = 0;
            for (int i = 0; i < (bytes.length - 44) / 60; i++) {
                byte[] byteDataFileType = new byte[1];
                byte[] byteFileName = new byte[50];
                byte[] byteSize = new byte[4];
                byte[] byteFileRecordNum = new byte[4];
                byte[] byteSettleStatus = new byte[1];
                bis.read(byteDataFileType);
                bis.read(byteFileName);
                bis.read(byteSize);
                bis.read(byteFileRecordNum);
                bis.read(byteSettleStatus);
                //文件名称需要取出来修改--看看转换后是啥
                String gb2312 = ByteUtil.getString(byteFileName, Charset.forName("GB2312"));
                String fileName = gb2312.replaceAll("98000000", "00000000");
                byte[] fileNameSpace = new byte[]{0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20,
                        0x20, 0x20, 0x20, 0x20, 0x20, 0x20,
                        0x20};
                System.arraycopy(fileName.getBytes("GB2312"), 0, byteFileName, 0, fileName.getBytes("GB2312").length);
                System.arraycopy(fileNameSpace, 0, byteFileName, fileName.getBytes("GB2312").length,
                        50 - fileName.getBytes("GB2312").length);
                //用空格替换null----00换成20
                System.arraycopy(byteDataFileType, 0, byteFtp, idx,
                        byteDataFileType.length);
                idx += byteDataFileType.length;
                System.arraycopy(byteFileName, 0, byteFtp, idx,
                        byteFileName.length);
                idx += byteFileName.length;
                System.arraycopy(byteSize, 0, byteFtp,
                        idx, byteSize.length);
                idx += byteSize.length;
                System.arraycopy(byteFileRecordNum, 0, byteFtp,
                        idx, byteFileRecordNum.length);
                idx += byteFileRecordNum.length;
                System.arraycopy(byteSettleStatus, 0, byteFtp,
                        idx, byteSettleStatus.length);
                idx += byteSettleStatus.length;
            }
            //审计文件校验码
            byte[] byteMD5 = new byte[16];
            byte[] bytesMD5 = new byte[bytes.length - 16];
            System.arraycopy(byteHeader, 0, bytesMD5, 0, byteHeader.length);
            System.arraycopy(byteFtp, 0, bytesMD5, byteHeader.length, byteFtp.length);
            String checkCodeMd5 = MD5Util.encrypt(bytesMD5).toUpperCase();
            byteMD5 = ByteUtil.toByteArray(checkCodeMd5);

            System.arraycopy(byteHeader, 0, bytesTotal, 0, byteHeader.length);
            System.arraycopy(byteFtp, 0, bytesTotal, byteHeader.length, byteFtp.length);
            System.arraycopy(byteMD5, 0, bytesTotal, byteHeader.length + byteFtp.length, byteMD5.length);
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytesTotal;
    }

    /**
     * 根据byte数组，生成文件
     */
    public static void getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


}
