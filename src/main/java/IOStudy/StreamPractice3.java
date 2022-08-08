package IOStudy;

import java.util.*;

/**
 * @Author Anthony Z.
 * @Date 26/7/2022
 * @Description: max/min/count
 */
public class StreamPractice3 {
    // use max/min/count
    static void findLongestInStringList(){
        List<String> list = Arrays.asList("ab", "cdef", "hij");
        Optional<String> longest = list.stream().max(Comparator.comparing(String::length));
        System.out.println(longest);
    }

    static void findLargestInIntegerString(){
        List<Integer> list = Arrays.asList(2, 4, 7, 9);

        // 自然排序
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        // 自定义排序
        Optional<Integer> max2 = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(max.get());
        System.out.println(max2.get());
    }

    static void getPersonWhoGetHighestSalary(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Tom", 123, 23, "male", "CA"));
        personList.add(new Person("Jacky", 234, 24, "female", "DC"));
        personList.add(new Person("Zhang", 2342, 34, "male", "Washington"));

        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println(max.get().getSalary());
    }

    public static void main(String[] args) {
        findLongestInStringList();
        getPersonWhoGetHighestSalary();
    }
}
