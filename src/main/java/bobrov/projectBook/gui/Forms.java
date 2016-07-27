package bobrov.projectBook.gui;

import bobrov.projectBook.connectionBD.ConnectionBD;
import bobrov.projectBook.connectionBD.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by пк on 27.07.2016.
 */
public class Forms {
    private static ConnectionBD connect;
    private static User user;
    private static boolean flag = false;
    public static void addConnect() {
        flag = true;
        final JFrame frame = new JFrame("Add new connect to BD");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        JLabel urlLabel = new JLabel("URL: ");
        JLabel nameLabel = new JLabel("User Name: ");
        JLabel passLabel = new JLabel("Password: ");

        final JTextField nameField = new JTextField();
        final JTextField urlField = new JTextField();
        final JTextField passField = new JTextField();

        JButton addButton = new JButton("Add Connect");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect = new ConnectionBD(urlField.getText(),nameField.getText(), passField.getText());
                flag = false;

                frame.dispose();

            }
        });

        frame.add(urlLabel, new GridBagConstraints(0,0,1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2,2,2,2), 0,0));
        frame.add(urlField, new GridBagConstraints(1,0,1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2,2,2,2), 0,0));
        frame.add(nameLabel, new GridBagConstraints(0,1,1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2,2,2,2), 0,0));
        frame.add(nameField, new GridBagConstraints(1,1,1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2,2,2,2), 0,0));
        frame.add(passLabel, new GridBagConstraints(0,2,1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2,2,2,2), 0,0));
        frame.add(passField, new GridBagConstraints(1,2,1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2,2,2,2), 0,0));
        frame.add(addButton, new GridBagConstraints(0,3,2,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2,2,2,2), 0,0));
        frame.setVisible(true);
        frame.pack();

    }
    public static void addUser() {

        final JFrame frame = new JFrame("Add new User");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("User Name: ");
        JLabel passLabel = new JLabel("Password: ");

        final JTextField nameField = new JTextField();
        final JPasswordField passField = new JPasswordField();

        JButton addButton = new JButton("Add User");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = new User(nameField.getText(), passField.getText(), getConnect());
                frame.dispose();
            }
        });
        frame.add(nameLabel, new GridBagConstraints(0,0,1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0,0));
        frame.add(nameField, new GridBagConstraints(1,0,1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0,0));
        frame.add(passLabel, new GridBagConstraints(0,1,1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0,0));
        frame.add(passField, new GridBagConstraints(1,1,1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0,0));
        frame.add(addButton, new GridBagConstraints(0,2,1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1,1,1,1), 0,0));

        addConnect();
        while(flag){};
        frame.setVisible(true);
        frame.pack();
    }

    public static ConnectionBD getConnect() {
        while(flag){};

        return connect;
    }

    public static User getUser() {
        while(user == null){}
        return user;

    }
}
