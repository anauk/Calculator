import java.io.*;

public class UserDataBase {
    public User getByUserLoginAndPasword(String login, String password) throws IOException {
        FileReader fr = new FileReader(new File("./users.txt"));
        try {

            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine())!= null) {
                String[] users = line.split(",");
                if (users[0].equals(login) && users[1].equals(password)) {
                    User user = new User();
                    user.setLogin(users[0]);
                    user.setPassword(users[1]);
                    user.setName(users[2]);
                    return user;
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
