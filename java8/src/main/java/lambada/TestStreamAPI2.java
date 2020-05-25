package lambada;

import lambada.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: Damon
 * @Date: 2020/5/23 17:19
 */
public class TestStreamAPI2 {
    List<Employee> emps = Arrays.asList(
            new Employee(101,"张三",29,4543),
            new Employee(102,"james",23,4543),
            new Employee(103,"tom",29,4543),
            new Employee(104,"allen",12,4543),
            new Employee(105,"damon",22,888888888)
    );
    /**
     * 映射
     * map-接收lambda，将元素转换成为其他形式，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     *  flatMap-接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test5(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream()
                .map(str->str.toUpperCase())
                .forEach(System.out::println);

        emps.stream()
                .map(Employee::getAge)
                .forEach(System.out::println);

        System.out.println("--------------------------------");

        Stream<Stream<Character>> streamStream = list.stream()
                .map(TestStreamAPI2::filterCharacter);//{{a,a,a,},{b,b,b,},{c,c,c}}
        streamStream.forEach(x->{
            x.forEach(System.out::println);
        });
        System.out.println("------------flatMap--------------------");
        Stream<Character> characterStream = list.stream()
                .flatMap(TestStreamAPI2::filterCharacter);
        characterStream.forEach(System.out::println);


    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for(Character ch : str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }


    /**
     * filter
     * limit
     * skip
     * distinct 通过流所生成元素的hashCode() 和 equals() 去除重复元素
     * 内部迭代
     * 需要终止操作
     */

    @Test
    public void test1(){

        Stream<Employee> employeeStream = emps.stream().filter(x -> x.getAge() > 23);

        emps.stream().filter(e->{
            System.out.println("短路！！！");
            return e.getAge() > 22;
        })
                .limit(2)
                .forEach(System.out::println);

    }
}
