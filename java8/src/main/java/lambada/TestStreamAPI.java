package lambada;

import lambada.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: Damon
 * @Date: 2020/5/23 17:04
 */
public class TestStreamAPI {
    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //2
        Employee[] emps = new Employee[2];
        Stream<Employee> stream1 = Arrays.stream(emps);

        //3
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");
        //4创建无限流 迭代
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        iterate.limit(10).forEach(System.out::println);
//        iterate.forEach(System.out::println);

        //4
        Stream.generate(()->Math.random()).limit(22).forEach(System.out::println);

    }
}
