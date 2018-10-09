package demo;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Simple {
    public static void main(String[] args) {


        //jdk接口都有个 @FunctionalInterface的注解，但是我们在实际编程中并没有加这个注解也可以实现lambda表达式
        Print(string -> {
            System.out.println(string);
            return 0;
        });
    }

    interface ITest {
        int test(String string);
    }

    static void Print(ITest test) {
        test.test("hello world");
    }


    private static void method1() {
        /**
         * Runnable有个注解 @FunctionalInterface，它是jdk8才引入，它的含义是函数接口。它是lambda表达式的协议注解，这个注解非常重要
         * **/
        new Thread(() -> System.out.println("hello world")).start();
        //   ()-> System.out.println("hello world")
    }

    private static void method2() {
        List<String> list = new ArrayList<>();
        Collections.sort(list, ((o1, o2) -> {
            if (o1.equals(o2)) {
                return 1;
            }
            return -1;
        }));
    }
}
