package optional;

import lambada.entity.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * @Author: Damon
 * @Date: 2020/5/24 18:01
 */
public class TestOptional {
    @Test
    public void test(){
        Optional<Employee> optional = Optional.empty();
        System.out.println(optional.get());
    }
}
