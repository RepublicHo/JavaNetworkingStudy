package IOStudy;

/**
 * @Author Anthony Z.
 * @Date 26/7/2022
 * @Description:
 */
public class Person {
    private String name;
    private int salary;
    private int age;
    private String sex;
    private String area;

    public Person(String name, int salary, int age, String sex, String area){
        this.age = age;
        this.area = area;
        this.name = name;
        this.salary = salary;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getArea() {
        return area;
    }

    public String getSex() {
        return sex;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
