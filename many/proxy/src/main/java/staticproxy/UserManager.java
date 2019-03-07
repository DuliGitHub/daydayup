package staticproxy;


public interface UserManager {

    void addUser(String userId, String userName);

    void delUser(String userId);

    String findUser(String userId);

    void modifyUser(String userId, String userName);
}
