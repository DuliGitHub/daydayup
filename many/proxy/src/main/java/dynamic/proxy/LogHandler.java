package dynamic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ━━━━━━如来保佑━━━━━
 * @author Ledev4
 * @date 2018/6/23 18:07 星期六
 */

//动态代理类只能代理接口（不支持抽象类），代理类都需要实现InvocationHandler类，
// 实现invoke方法。该invoke方法就是调用被代理接口的所有方法时需要调用的，
// 该invoke方法返回的值是被代理接口的一个实现类
public class LogHandler implements InvocationHandler {

    //目标对象
    private Object targetObject;

    //绑定关系，也就是关联到哪个接口（与具体的实现类绑定）的哪些方法将被调用时，执行invoke方法.
    public Object newProxyInstance(Object targetObject) {
        this.targetObject = targetObject;

        //该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
        //第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
        //第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
        //第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
        //根据传入的目标返回一个代理对象

        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

    /**
     * 被代理对象targetObject通过参数传递进来，我们通过targetObject.getClass().getClassLoader()获取ClassLoader对象，
     * 然后通过targetObject.getClass().getInterfaces()获取它实现的所有接口，然后将targetObject包装到实现了InvocationHandler接口的LogHandler对象中。
     * 通过newProxyInstance函数我们就获得了一个动态代理对象。
     **/

    //关联的这个实现类的方法被调用时将被执行
    /*InvocationHandler接口的方法，proxy表示代理，method表示原对象被调用的方法，args表示方法的参数*/
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start-->>");
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        Object ret = null;
        try {
            /*原对象方法调用前处理日志信息*/
            System.out.println("satrt-->>");

            //调用目标方法
            ret = method.invoke(targetObject, args);
            /*原对象方法调用后处理日志信息*/
            System.out.println("success-->>");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error-->>");
            throw e;
        }
        return ret;
    }
}
