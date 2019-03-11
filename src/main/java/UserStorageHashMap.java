import java.util.HashMap;

public class UserStorageHashMap implements UserStorage {
    private final HashMap<String, String> storage = new HashMap<>();

    @Override
    public void register(String name, String password) {
        storage.put(name, password);
    }

    @Override
    public boolean check(String name, String password) {
        return storage.get(name).equals(password);
    }
}
