package Basics;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.Buffer;

/**
 * @Author Anthony Z.
 * @Date 27/7/2022
 * @Description:
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        String result = exeCmd("ping baidu.com", null);
        System.out.println(result);
    }

    public static String exeCmd(String cmd, File dir) throws Exception{
        StringBuilder result = new StringBuilder();
        Process process = null;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedError = null;

        try{
            // 执行命令, 返回一个子进程对象（命令在子进程中执行）
            process = Runtime.getRuntime().exec(cmd, null, dir);

            // 方法阻塞, 等待命令执行完成（成功会返回0）
            process.waitFor();

            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            bufferedError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line).append('\n');
            }
            while ((line = bufferedError.readLine()) != null) {
                result.append(line).append('\n');
            }

        }finally {
            closeStream(bufferedReader);
            closeStream(bufferedError);

            if(process != null){
                process.destroy();
            }
        }
        return result.toString();
    }

    private static void closeStream(Closeable stream){
        if(stream != null){
            try{
                stream.close();
            }catch (Exception e){

            }
        }
    }
}
