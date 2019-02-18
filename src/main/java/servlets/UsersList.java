package servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsersList {
    private static UsersList instance = new UsersList();
    private List<User> users;
    public static UsersList getInstance(){
        return instance;
    }
    private UsersList(){
        users = new ArrayList<>();
    }
    public void add(User user){
        users.add(user);
    }
    public List list(){
        return users.stream()
                .map(u->u.getLogin())
                .collect(Collectors.toList());
    }

    public void saveUser(User user) {
        boolean is = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(user)) {
                users.set(i, user);
                is = true;
                System.out.println(list());
                break;
            }
        }
        if (!is) {
            users.add(user);
        }
    }


   /* Map<Integer, User> users = new HashMap<>();

    public UsersList(Map<Integer, User> users) {
        this.users = users;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }*/
}
