package dynamic;

import dynamic.impl.UserManagerImpl;
import dynamic.proxy.LogHandler;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class Client {
    public static void main(String[] args) throws Exception {


        byte[] bytes = ProxyGenerator.generateProxyClass("$ProxyDL",new Class[]{UserManager.class});
        FileOutputStream outputStream = new FileOutputStream("D:/rainbow/many/proxy/target/classes/$ProxyDL.class");

        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();

//        LogHandler logHandler = new LogHandler();
//
//        UserManager userManager = (UserManager) logHandler.newProxyInstance(new UserManagerImpl());
//        //UserManager userManager=new UserManagerImpl();
//        userManager.addUser("1111", "鸣人");
    }
}
