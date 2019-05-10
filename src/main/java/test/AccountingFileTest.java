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
 * @Date:Created in 15:06 2019/2/1
 * @Modified By:
 */
public class AccountingFileTest {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\35325\\Desktop\\1534927109479\\Accounting.98000000.20190128");
        byte[] bytes = resolveFile(file);
        getFile(bytes, "C:\\Users\\35325\\Desktop\\1534927109479", "Accounting.00000000.20190128");


    }


    public static byte[] resolveFile(File file) {
        byte[] bytes = null;
        byte[] bytesTotal = null;
        try {
            bytes = Files.toByteArray(file);
            bytesTotal = new byte[bytes.length];
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            byte[] byteHeader = new byte[30];
            //对账文件结构
            byte[] byteFileType = new byte[1];
            byte[] byteFileCreateTime = new byte[7];
            byte[] byteFileVersion = new byte[1];
            bis.read(byteFileType);
            bis.read(byteFileCreateTime);
            bis.read(byteFileVersion);
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
            System.arraycopy(byteSettleDate, 0, byteHeader, byteHeader.length - 6,
                    byteSettleDate.length);
            byte[] byteSettleFileRecordNum = new byte[2];
            bis.read(byteSettleFileRecordNum);
            System.arraycopy(byteSettleFileRecordNum, 0, byteHeader, byteHeader.length - 2,
                    byteSettleFileRecordNum.length);

            //对账数据文件结构
            byte[] byteAccountingFile = new byte[bytes.length - 46];
            int idx = 0;
            for (int i = 0; i < (bytes.length - 46) / 62; i++) {
                byte[] byteTicketIssuer = new byte[1];
                byte[] byteTransType = new byte[1];
                byte[] byteTransNum = new byte[4];
                byte[] byteNormalTransNum = new byte[4];
                byte[] byteNormalTransAmount = new byte[4];
                byte[] byteDubiousTransNum = new byte[4];
                byte[] byteDubiousTransAmount = new byte[4];
                byte[] byteRejectTransNum = new byte[4];
                byte[] byteRejectTransAmount = new byte[4];
                byte[] byteAdjustTransNum = new byte[4];
                byte[] byteAdjustTransAmount = new byte[4];
                byte[] byteOtherTransNum1 = new byte[4];
                byte[] byteOtherTransAmount1 = new byte[4];
                byte[] byteOtherTransNum2 = new byte[4];
                byte[] byteOtherTransAmount2 = new byte[4];
                byte[] byteOtherTransNum3 = new byte[4];
                byte[] byteOtherTransAmount3 = new byte[4];

                bis.read(byteTicketIssuer);
                bis.read(byteTransType);
                bis.read(byteTransNum);
                bis.read(byteNormalTransNum);
                bis.read(byteNormalTransAmount);
                bis.read(byteDubiousTransNum);
                bis.read(byteDubiousTransAmount);
                bis.read(byteRejectTransNum);
                bis.read(byteRejectTransAmount);
                bis.read(byteAdjustTransNum);
                bis.read(byteAdjustTransAmount);
                bis.read(byteOtherTransNum1);
                bis.read(byteOtherTransAmount1);
                bis.read(byteOtherTransNum2);
                bis.read(byteOtherTransAmount2);
                bis.read(byteOtherTransNum3);
                bis.read(byteOtherTransAmount3);

                System.arraycopy(byteTicketIssuer, 0, byteAccountingFile, idx,
                        byteTicketIssuer.length);
                idx += 1;
                System.arraycopy(byteTransType, 0, byteAccountingFile, idx,
                        byteTransType.length);
                idx += 1;
                System.arraycopy(byteTransNum, 0, byteAccountingFile, idx,
                        byteTransNum.length);
                idx += 4;
                System.arraycopy(byteNormalTransNum, 0, byteAccountingFile, idx,
                        byteNormalTransNum.length);
                idx += 4;
                System.arraycopy(byteNormalTransAmount, 0, byteAccountingFile, idx,
                        byteNormalTransAmount.length);
                idx += 4;
                System.arraycopy(byteDubiousTransNum, 0, byteAccountingFile, idx,
                        byteDubiousTransNum.length);
                idx += 4;
                System.arraycopy(byteDubiousTransAmount, 0, byteAccountingFile, idx,
                        byteDubiousTransAmount.length);
                idx += 4;
                System.arraycopy(byteRejectTransNum, 0, byteAccountingFile, idx,
                        byteRejectTransNum.length);
                idx += 4;
                System.arraycopy(byteRejectTransAmount, 0, byteAccountingFile, idx,
                        byteRejectTransAmount.length);
                idx += 4;
                System.arraycopy(byteAdjustTransNum, 0, byteAccountingFile, idx,
                        byteAdjustTransNum.length);
                idx += 4;
                System.arraycopy(byteAdjustTransAmount, 0, byteAccountingFile, idx,
                        byteAdjustTransAmount.length);
                idx += 4;
                System.arraycopy(byteOtherTransNum1, 0, byteAccountingFile, idx,
                        byteOtherTransNum1.length);
                idx += 4;
                System.arraycopy(byteOtherTransAmount1, 0, byteAccountingFile,
                        idx, byteOtherTransAmount1.length);
                idx += 4;
                System.arraycopy(byteOtherTransNum2, 0, byteAccountingFile,
                        idx, byteOtherTransNum2.length);
                idx += 4;
                System.arraycopy(byteOtherTransAmount2, 0, byteAccountingFile,
                        idx, byteOtherTransAmount2.length);
                idx += 4;
                System.arraycopy(byteOtherTransNum3, 0, byteAccountingFile,
                        idx, byteOtherTransNum3.length);
                idx += 4;
                System.arraycopy(byteOtherTransAmount3, 0, byteAccountingFile,
                        idx, byteOtherTransAmount3.length);
                idx += 4;
            }
            //审计文件校验码
            byte[] byteMD5 = new byte[16];
            byte[] bytesMD5 = new byte[bytes.length - 16];
            System.arraycopy(byteHeader, 0, bytesMD5, 0, byteHeader.length);
            System.arraycopy(byteAccountingFile, 0, bytesMD5, byteHeader.length, byteAccountingFile.length);
            String checkCodeMd5 = MD5Util.encrypt(bytesMD5).toUpperCase();
            byteMD5 = ByteUtil.toByteArray(checkCodeMd5);

            System.arraycopy(byteHeader, 0, bytesTotal, 0, byteHeader.length);
            System.arraycopy(byteAccountingFile, 0, bytesTotal, byteHeader.length, byteAccountingFile.length);
            System.arraycopy(byteMD5, 0, bytesTotal, byteHeader.length + byteAccountingFile.length, byteMD5.length);
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
