package IOStudy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author Anthony Z.
 * @Date 26/7/2022
 * @Description:
 * Stream 是顺序流，由主线程按顺序对流执行操作
 * parallelStream是并行流，内部是多线程（前提：流中的数据处理
 * 没有顺序要求
 */
public class Stream1 {
    public static void main(String[] args) {
        // 1. 通过java.util.Collection.stream()方法用集合创建流
        List<String> list = Arrays.asList("a", "b", "c");
        // 2. util.Arrays.stream(T[] array) 方法用数组创建流
        Stream<String> stream = list.stream();
        Stream<String> parallelStream = list.parallelStream();
        // 3. 使用Stream的静态方法
        // (1) of()
        // (2) iterate()
        // (3) generate()
        int[] array = new int[]{1, 3, 5, 6, 8};
        IntStream stream1 = Arrays.stream(array);

        Stream<Integer> stream2 = Stream.of(1, 2, 3);

        Stream<Integer> stream3 = Stream.iterate(0, (x)->x+3).limit(4);
        stream3.forEach(System.out::println);
        Stream<Double> stream4 = Stream.generate(Math::random).limit(3);
        stream4.forEach(System.out::println);

    }


}
