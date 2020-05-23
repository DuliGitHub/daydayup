package lambada;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Author: Damon
 * @Date: 2020/5/23 10:22
 */
public class TestLambda3 {

    // Predicate<T>: 断言型接口
    //    - boolean test(T t);
    //需求：将满足条件的字符串放入集合中
    @Test
    public void pre(){
        List<String> list = Arrays.asList("damon","jam","lambda","james","allen");
        List<String> list1 = filterStr(list, (s) -> s.length() > 3);
        list1.stream().forEach(e-> System.out.println(e));

    }

    public List<String> filterStr(List<String> list, Predicate<String> predicate){
        List<String> stringList = new ArrayList<>();
        for(String str : list){
            if(predicate.test(str)){
                stringList.add(str);
            }
        }
    return stringList;
    }


    //===============================================
    //Function<T,R>: 函数型接口
    //用于处理字符串
    @Test
    public void func(){
        String aaaaa = strHandler("aaaaa", (x) -> x.toUpperCase());
        System.out.println(aaaaa);

        String aa = strHandler("aaaaa", (x) -> x.substring(2,5));
        System.out.println(aa);


    }
    public String strHandler(String str, Function<String,String> function){
        return function.apply(str);
    }



//===============================================
    //- Supplier<T>: 供给型接口
    //    - T get();
    //产生一些整数，并放入集合中
    @Test
    public void sup(){
        List<Integer> numList = getNumList(100, () -> (int) (Math.random() * 100));
        for(Integer num : numList){
            System.out.println(num);
        }
    }

    public List<Integer> getNumList(int num, Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i < num;i++){
            list.add(sup.get());
        }
        return list;
    }
    //=====================================


    //Consumer<T> 消费型接口：
    @Test
    public void c(){
        happy(1000D,m-> System.out.println("大保健消费:"+m+"元"));
    }

    public void happy(double money, Consumer<Double> con){
        con.accept(money);
    }
}
