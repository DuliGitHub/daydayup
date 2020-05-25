package lambada;

import lambada.entity.Employee;
import lambada.entity.Employee.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author: Damon
 * @Date: 2020/5/24 13:19
 */
public class TestStreamAPI3 {
    List<Employee> emps = Arrays.asList(
            new Employee(101,"张三",29,543, Status.BUSY),
            new Employee(102,"james",23,443, Status.VOCATION),
            new Employee(103,"tom",29,14543, Status.FREE),
            new Employee(104,"allen",12,3543, Status.VOCATION),
            new Employee(105,"damon",22,888888888, Status.FREE)
    );


    /**
     * 查找与匹配
     * allMatch-检查是否匹配所有元素
     * anyMatch-检查是否是至少匹配一个元素
     * noneMatch-检查是否没有匹配所有元素
     * findFirst-返回第一个元素
     * findAny-返回当前流中的任意元素
     * count-返回流中元素的总个数
     * max-返回流中最大值
     * min-返回流中最小值
     */

    @Test
    public void test(){
        boolean b = emps.stream()
                .allMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println("allMatch: " + b);

        boolean b1 = emps.stream()
                .anyMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println("anyMatch: " + b1);

        boolean b2 = emps.stream()
                .noneMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println("noneMatch: " + b2);

        Optional<Employee> first = emps.stream()
                .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println("findFirst: " + first.get().getSalary());

        Optional<Employee> any = emps.stream()
                .filter(e -> e.getStatus().equals(Status.BUSY))
                .findAny();
        System.out.println("findAny: " + any.get());

        long count = emps.stream()
                .count();
        System.out.println(count);
        Optional<Employee> max = emps.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println("max: " + max);

        Optional<Integer> min = emps.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println("min: " + min.get());
    }

    /**
     * 归约
     * reduce(T identity,BinaryOperator) /reduce(BinaryOperator)--可以将流中的元素反复结合起来，得到一个值
     */

    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list.stream()
                .reduce(0, (e1, e2) -> e1 + e2);
        System.out.println(reduce);
        System.out.println("-------------------------------");
        Optional<Integer> reduce1 = emps.stream()
                .map(Employee::getAge)
                .reduce(Integer::sum);
        System.out.println(reduce1.get());
    }

    /**
     * 收集
     * collect -将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素汇总的方法
     */

    @Test
    public void test3(){
        List<String> collect = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("------------------------------");

        Set<String> collect1 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        collect1.forEach(System.out::println);
        System.out.println("------------------------------");
        HashSet<String> collect2 = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        collect2.forEach(System.out::println);
        System.out.println("------------------------------");
    }

    @Test
    public void test4(){
        //总数
        Long collect = emps.stream()
                .collect(Collectors.counting());
        System.out.println(collect);

        System.out.println("------------------------------");

        //平均值
        Double collect1 = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getAge));
        System.out.println(collect1);
    }

    //分组
    @Test
    public void group(){
        Map<Status, List<Employee>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        System.out.println(collect);
    }
    //多级分组,先按状态分，再按年龄分
    @Test
    public void g1(){
        Map<Status, Map<String, List<Employee>>> collect = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,
                        Collectors.groupingBy(e -> {
                            if (((Employee) e).getAge() < 33) {
                                return "青年";
                            } else {
                                return "壮年";
                            }
                        })));
        System.out.println(collect);
    }

    //分区，true的一个区，false的一个区
    @Test
    public void test8(){
        Map<Boolean, List<Employee>> collect = emps.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 4000));
        System.out.println(collect);
    }

    @Test
    public void test9(){
        DoubleSummaryStatistics collect = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect.getMax());
        System.out.println(collect.getAverage());
        System.out.println(collect.getMin());
    }

    @Test
    public void test10(){
        String collect = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println(collect);
    }

    //计算个数
    @Test
    public void test11(){
        Optional<Integer> reduce = emps.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(reduce.get());
    }

}
