package staticproxy;

import staticproxy.impl.UserManagerImpl;
import staticproxy.proxy.UserManagerImplProxy;


//每个代理类只能为一个接口服务，这样程序开发中必然会产生许多的代理类
//一个代理只能代理一种类型，而且是在编译器就已经确定被代理的对象
public class Client {
    public static void main(String[] args) {
        UserManager userManager = new UserManagerImplProxy(new UserManagerImpl());
        userManager.addUser("1", "路飞");
    }
}
