package dynamic;

import dynamic.impl.UserManagerImpl;
import dynamic.proxy.LogHandler;



public class Client {
    public static void main(String[] args) {

        LogHandler logHandler = new LogHandler();

        UserManager userManager = (UserManager) logHandler.newProxyInstance(new UserManagerImpl());
        //UserManager userManager=new UserManagerImpl();
        userManager.addUser("1111", "鸣人");
    }
}
