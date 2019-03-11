package entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String surname;
    private String login;
    private String password;
    private int id;
    private List<String> history = new ArrayList<>();
    public User(){

    }
    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public User(String name, String surname, String login, String password, int id) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.id = login.hashCode();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public boolean check(String password) {
        return password.equals(this.password);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }
    public void addResultToHistory(String string){
        history.add(string);
    }
    public String printHistory(){
        StringBuilder sb = new StringBuilder();
        for (String h:history){
            sb.append(h + "<br>");
        }
        return sb.toString();
    }

}
