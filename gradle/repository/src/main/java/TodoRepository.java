import java.util.HashMap;
import java.util.Map;
public class TodoRepository {
    private static Map<String,TodoItem> itemMap = new HashMap<>();

    public void save(TodoItem todoItem){
        System.out.println("save: " + todoItem);
    }
}
