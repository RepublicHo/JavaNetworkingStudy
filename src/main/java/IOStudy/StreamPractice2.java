package IOStudy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Anthony Z.
 * @Date 26/7/2022
 * @Description: filter筛选
 */
public class StreamPractice2 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Tom", 8900, 23, "male", "NY"));
        personList.add(new Person("Jack", 10000, 34, "female", "China"));

        List<String> filterList1 = personList.stream().filter(x -> x.getSalary()>1000)
                .map(Person::getName).collect(Collectors.toList());
        System.out.println(filterList1);

        List<String> filterList2 = personList.stream().filter(x -> x.getName().equals("Tom"))
                .map(Person::getArea).collect(Collectors.toList());
        System.out.println(filterList2);

    }
}
