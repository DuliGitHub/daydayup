package npe.old;

import java.util.Optional;

public class Main {

    public static User createUser(){
        User user = new User();
        user.setName("haha");
        return user;
    }
    public static void main(String[] args) {

//---------------------------------------------------------------------------------------------------------------------------------------
        User user = new User();
//        String pro = user.getAddress().getProvince();//这种写法，在user为null时，是有可能报NullPointerException异常的
        // 为了解决这个问题，于是采用下面的写法
        if(user != null){
            Address address = user.getAddress();
            if(address != null){
                String province = address.getProvince();
            }
        }
        //这种写法是比较丑陋的，为了避免上述丑陋的写法，让丑陋的设计变得优雅。JAVA8提供了Optional类来优化这种写法


        //Optional 源码
//        Optional.of();
//        Optional.empty();
//        Optional.ofNullable();

//---------------------------------------------------------------------------------------------------------------------------------------
            //orElse(T other)，orElseGet(Supplier<? extends T> other)和orElseThrow(Supplier<? extends X> exceptionSupplier)
        //orElse和orElseGet的用法如下所示，相当于value值为null时，给予一个默认值
        User user1;
        User user2 = new User("kk");
        user1 = Optional.ofNullable(user2).orElse(createUser());
        System.out.println(user1.toString());
//        user1 = null;
        user1 = Optional.ofNullable(user1).orElseGet(() -> createUser());
        System.out.println(user1.toString());
        //这两个函数的区别：当user值不为null时，orElse函数依然会执行createUser()方法，而orElseGet函数并不会执行createUser()方法

        try{
//            user1 = null;
            Optional.ofNullable(user1).orElseThrow(()->new Exception("用户不存在"));//value值为null时,直接抛一个异常出去
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
