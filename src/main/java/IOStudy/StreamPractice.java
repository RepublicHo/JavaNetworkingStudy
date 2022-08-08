package IOStudy;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Author Anthony Z.
 * @Date 26/7/2022
 * @Description:
 */
public class StreamPractice {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 3, 4, 225, 1223, 234, 123);

        // 遍历输出符合条件的元素
        list.stream().filter(x -> x>4).forEach(System.out::println);
        // parallelStream
        list.stream().parallel().filter(x->x>4).forEach(System.out::println);

        // 匹配第一个 for stream
        Optional<Integer> findFirst = list.stream().filter(x->x>6).findFirst();
        // 匹配任意 for parallelStream
        Optional<Integer> findAny = list.parallelStream().filter(x -> x>6).findAny();
        // 是否包含符合特定条件的元素
        boolean ifMatch = list.stream().anyMatch(x -> x<6);

        System.out.println(findFirst.get());
        System.out.println(findAny.get());
        System.out.println(ifMatch);
    }
}
