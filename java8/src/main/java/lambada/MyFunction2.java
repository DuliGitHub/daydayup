package lambada;

/**
 * @Author: Damon
 * @Date: 2020/5/23 10:06
 */
@FunctionalInterface
public interface MyFunction2<T,R> {
    R getValue(T t1,T t2);
}
