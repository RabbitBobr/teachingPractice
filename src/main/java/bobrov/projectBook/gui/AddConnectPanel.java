package bobrov.projectBook.gui;

import bobrov.projectBook.connectionBD.ConnectionBD;

/**
 * Created by пк on 28.07.2016.
 */
public class AddConnectPanel {

    public static ConnectionBD setConnect() {
        return new ConnectionBD("jdbc:mysql://" +
                "127.0.0.1:3306/mybookcollections",
                "Rabbik", "Dtytnfhbq");
    }
}
