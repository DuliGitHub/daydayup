package lambada;

import lambada.entity.Employee;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * @Author: Damon
 * @Date: 2020/5/22 23:11
 */

public class TestLambda {

    @Test
    public void test6(){
        op(111L,222L,(x,y)->x+y);
        op(111L,222L,(x,y)->x/y);

    }
    //对两个Long型数据进行处理
    public void op(Long l1,Long l2,MyFunction2<Long,Long> mf){
        System.out.println(mf.getValue(l1,l2));
    }

    @Test
    public void test5(){
        String s1 = strHandler("\t\t\t\t damon good rich", (s) -> s.trim());
        System.out.println(s1);
        System.out.println(strHandler("aaaaa",e-> e.toUpperCase()));
    }
    //用于处理字符串
    public String strHandler(String str,MyFunction mf){
        return mf.getValue(str);
    }
    @Test
    public void test4(){
        //调用Collections.sort()方法，通过定制排序比较两个Employee（先按年龄比，年龄相同按姓名比），
        // 使用lambda作为参数传递
        List<Employee> emps = Arrays.asList(
                new Employee(101,"张三",29,4543),
                new Employee(102,"james",23,4543),
                new Employee(103,"tom",29,4543),
                new Employee(104,"allen",12,4543),
                new Employee(105,"damon",22,888888888)
        );
        Collections.sort(emps,(e1,e2)->{
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            }else {
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        emps.stream().forEach(e-> System.out.println(e));
    }

    @Test
    public void test1(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    @Test
    public void test2(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //有参数 ，无返回值,我参数，无返回值，有一个参数，无返回值
    @Test
    public void test3(){
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("hello lambda");

        Runnable r = ()-> System.out.println("无参数，无返回值");
        r.run();

        Comparator<Integer> com = (x,y)->{
            System.out.println("多个语句，加大括号");
            return Integer.compare(x,y);
        };
        int compare = com.compare(4, 5);
        System.out.println(compare);


        //只有一条语句，大括号和return都可以不写
        Comparator<Integer> com1= (x,y)->Integer.compare(x,y);
        int compare1 = com1.compare(3, 2);
        System.out.println(compare1);
        //lambda表达式的参数列表的数据类型可以省略不写，因为jvm编译器可以通过上下文推断数据类型，即“类型推断”

    }

}
