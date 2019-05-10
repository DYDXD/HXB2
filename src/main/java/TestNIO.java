import java.io.*;
import java.nio.ByteBuffer;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 16:00 2019/1/3
 * @Modified By:
 */
public class TestNIO {
    private static String path = "C:\\Users\\35325\\Desktop\\1534927109479\\01.01.20181228000000.zip";

    public static void main(String[] args) {
        try {
            System.out.println(getBytes(path,512,0));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static byte[] cacheTest() {

        int size = 1460454;

        byte[] bytes = new byte[0];
        try {
            bytes = getBytes(path, size, 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ByteBuffer allocate = ByteBuffer.allocate(size);
        ByteBuffer put = allocate.put(bytes);
        byte[] ret = new byte[512];
        put.flip();
        put.get(ret, 1458176, 512);
        return ret;
    }


    private static byte[] getBytes(String filePath, int size, int startPosition) throws FileNotFoundException {
        File file1 = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file1);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(file1);


        byte[] b = new byte[512];
        try {


            while (( bufferedInputStream.read(b, startPosition, size)) != -1) {
            }
            System.out.println(2);
        bufferedInputStream.close();
            System.out.println(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;


       /* byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(size);
            byte[] b = new byte[size];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;*/
    }


}
