package entry;

import entry.DAO;
import entry.User;

import java.util.*;

public class UsersList implements DAO<User> {
    private final HashMap<Integer, User> storage = new HashMap<>();

    @Override
    public User get(int id) {
        return storage.get(id);
    }

    @Override
    public void put(User user) {
        storage.put(user.getId(), user);
    }

    @Override
    public Collection<User> all() {
        return storage.values();
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
    }

    @Override
    public boolean isEmpty() {
        return storage.isEmpty();
    }




}
