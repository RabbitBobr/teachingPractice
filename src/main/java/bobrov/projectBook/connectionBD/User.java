package bobrov.projectBook.connectionBD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by пк on 27.07.2016.
 */
public class User {
    private String login;
    private String password;
    private List<ConnectionBD> connects;

    public User(String login, String password, List<ConnectionBD> connects) {
        this.login = login;
        this.password = password;
        this.connects = connects;
    }
    public User(String login, String password, ConnectionBD connect) {
        this.login = login;
        this.password = password;
        this.connects = new ArrayList<ConnectionBD>();
        this.connects.add(connect);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", connects=" + connects +
                '}';
    }
}
