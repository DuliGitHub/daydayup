package lambada;

import lambada.entity.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Author: Damon
 * @Date: 2020/5/23 12:36
 * 方法引用：若lambda体中的
 *
 * 注意：lambda 中调用方法的参数类型和返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
 */

public class TestMethodRef {

    @Test
    public void test(){
        Consumer<String> con = x-> System.out.println(x);

        PrintStream out = System.out;
        Consumer<String> con1 = out :: println;

        Consumer<String> con2 = System.out::println;
        con2.accept("sfdsfsd");
    }

    @Test
    public void test2(){
        Employee emp = new Employee(1,"abc",18,29);
        Supplier<String> sup = ()-> emp.getName();
        String str = sup.get();
        System.out.println(str);

        Supplier<String> supplier = emp::getName;
        System.out.println(supplier.get());

    }

    @Test
    public void test3(){

        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        int compare = com.compare(1, 2);
        System.out.println(compare);

        Comparator<Integer> com1 = Integer::compare;
        System.out.println(com1.compare(3,2));
    }

    @Test
    public void test4(){
        //第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，可以使用ClassName::method
        BiPredicate<String,String> bp = (x,y)->x.equals(y);
        BiPredicate<String,String> bp1 = String::equals;
    }

    @Test
    public void test5(){
        Supplier<Employee> sup = ()-> new Employee();
        Supplier<Employee> sup1 = Employee::new;
        Employee employee = sup1.get();
        System.out.println(employee);

        //参数跟函数式接口的参数相同
        Function<Integer,Employee> fun2 = Employee::new;
        System.out.println(fun2.apply(11));

    }

    @Test
    public void test6(){
        Function<Integer,String[]> fun = (x) ->new String[x];
        String[] apply = fun.apply(20);
        System.out.println(apply.length);

        Function<Integer,String[]> fun1 = String[]::new;
        System.out.println(fun1.apply(2).length);
    }
}
