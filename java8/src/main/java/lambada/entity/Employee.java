package lambada.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: Damon
 * @Date: 2020/5/22 23:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    private int id;
    private String name;
    private int age;
    private int salary;
    private Status status;

    public Employee(int id) {
        this.id = id;
    }

    public Employee(int id, String name, int age, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public enum Status{
        FREE,
        BUSY,
        VOCATION;
    }
}
