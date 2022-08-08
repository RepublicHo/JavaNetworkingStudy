package IOStreamVideoStudy;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author Anthony Z.
 * @Date 30/7/2022
 * @Description:
 */
public class File2{

    public void testFileReader() {

        FileReader fr = null;
        try{
            // 1. 实例化File类的对象
            File file = new File("hello.txt");
            // 2. 提供具体的流, FileReader流的实例化
            fr = new FileReader(file);
            // 3. 读入的操作
            // read(char[] cbuf): 返回每次读入的cbuf数组中的字符的个数/
            // 如果到达文件末尾，返回-1
            char[] cbuf = new char[5];
            int len;

            while((len = fr.read(cbuf)) != -1){
                // right 注意len是什么 不是cbuf.length
                for(int i=0; i<len; i++){
                    System.out.print(cbuf[i]);
                }
                // Alternatives: 不是new String(cbuf)
//                String str = new String(cbuf, 0, len);
//                System.out.println(str);
            }
        }catch (IOException e){
            System.out.println("Hello");
            e.printStackTrace();
        }finally {

            // 4.流的资源关闭操作
            // 如果在方法那边直接写throws Exception
            // 可能造成这个未关闭
            if(fr != null){
                try{
                    fr.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 从内存中写出数据到硬盘的文件里
     */
    public void testFileWriter(){

    }

    public static void main(String[] args) {
        File2 file2 = new File2();
        file2.testFileReader();
    }
}

