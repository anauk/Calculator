import java.util.HashMap;
import java.util.Map;

public class AccountUsers {
    private Map<String, User> users = new HashMap<>();
    private Map<String, User> sessions = new HashMap<>();

    public boolean addUser(String login, User user){
        if(users.containsKey(login)){
            return false;
        }
        users.put(login, user);
        return true;
    }
    public void addSessions(String sessionId, User user){
        sessions.put(sessionId, user);
    }

    public User getUser(String login) {
        return users.get(login);
    }

    public User getSessions(String sessionId) {
        return sessions.get(sessionId);
    }
}
