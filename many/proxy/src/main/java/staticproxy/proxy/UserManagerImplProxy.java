package staticproxy.proxy;

import staticproxy.UserManager;

/**
 * ━━━━━━如来保佑━━━━━━
 * ━━━━━━如来保佑━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━永无BUG━━━━━━
 *
 * @author Ledev4
 * @date 2018/6/23 17:46 星期六
 */


public class UserManagerImplProxy implements UserManager {

    // 目标对象
    private UserManager userManager;

    // 通过构造方法传入目标对象
    public UserManagerImplProxy(UserManager userManager) {
        this.userManager = userManager;
    }

    public void addUser(String userId, String userName) {
        try {
            //添加打印日志的功能
            //开始添加用户
            System.out.println("start-->addUser()");
            userManager.addUser(userId, userName);
            //添加用户成功
            System.out.println("success-->addUser()");
        } catch (Exception e) {
            //添加用户失败
            System.out.println("error-->addUser()");
        }
    }


    public void delUser(String userId) {
        userManager.delUser(userId);
    }

    public String findUser(String userId) {
        userManager.findUser(userId);
        return "张三";
    }

    public void modifyUser(String userId, String userName) {
        userManager.modifyUser(userId, userName);
    }
}
