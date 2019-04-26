package npe.optional;

import npe.old.Address;
import npe.old.User;

import java.util.Optional;

public class Compared {
    //----------------------------------------------------例 1----------------------------------------------------------
    //以前写法
    public String getCity(User user) throws Exception{
        if(user != null){
            if(user.getAddress() != null){
                Address address = user.getAddress();
                if(address.getCity() != null){
                    return address.getCity();
                }
            }
        }
        throw new Exception("取值错误");
    }

    //JAVA8的写法
    public String getCity1(User user) throws Exception{
        return Optional.ofNullable(user)
                .map(u->u.getAddress())
                .map(a->a.getCity())
                .orElseThrow(()->new Exception("取值错误"));
    }

    //--------------------------------------------例2------------------------------------------------------------------

    public void doSomething(User user){

    }
    //以前写法
    public void oldType(User user){
        if(user != null){
            doSomething(user);
        }
    }
    //JAVA8写法
    public void newType(User user){
        Optional.ofNullable(user).ifPresent(u->{doSomething(u);});
    }

//--------------------------------------------例3------------------------------------------------------------------

    //以前写法
    public User getUser(User user) {
        if(user != null){
            String name = user.getName();
            if("dl".equals(name)){
                return user;
            }
        }else {
            user = new User();
            user.setName("dl");
            return user;
        }
        return null;
    }
    //java8写法
    public User getUser1(User user){
        return Optional.ofNullable(user).filter(u->"dl".equals(u.getName())).orElseGet(()->{
            User user1 = new User();
            user1.setName("dl");
            return user1;
        });
    }
}

