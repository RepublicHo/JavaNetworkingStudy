package ByteBufferStudy;

import java.nio.ByteBuffer;

/**
 * @Author Anthony Z.
 * @Date 8/8/2022
 * @Description:
 * ByteBuffer 分配
 *
 *
 */
public class Test2 {
    public static void main(String[] args) {
        // JAVA堆内存，读写效率较低，受到垃圾回收的影响
        System.out.println(ByteBuffer.allocate(16).getClass());
        // 直接内存，读写效率高（少一层拷贝）
        System.out.println(ByteBuffer.allocateDirect(16).getClass());

    }
}
