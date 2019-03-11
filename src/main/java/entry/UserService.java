package entry;

import entry.DAO;
import entry.User;

import java.util.Collection;
import java.util.HashMap;

public class UserService {
    private DAO<User> users;
    private final HashMap<String, String> storage = new HashMap<>();

    public UserService(DAO<User> users) {
        this.users = users;
    }

    public void putUser(User user) {
        users.put(user);
    }

    public void putUser(String name, String surname, String login, String password, int id) {
        User user = new User(name, surname, login, password, id);
        users.put(user);
    }

    public void deleteUser(int id) {
        users.delete(id);
    }

    public Collection<User> all() {
        return users.all();
    }

    public User getUser(int id) {
        return users.get(id);
    }
    public boolean isEmpty(){
        return users.isEmpty();
    }
    public boolean checkUserPass(String login, String password){
        User user = users.get(login.hashCode());
        return user.check(password);
    }


}
