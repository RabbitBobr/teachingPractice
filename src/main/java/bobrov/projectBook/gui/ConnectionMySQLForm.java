package bobrov.projectBook.gui;

import bobrov.projectBook.connectionBD.ConnectionBD;
import bobrov.projectBook.exceptions.ErrorFormatDateException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Rabbik on 01.08.2016.
 */
public class ConnectionMySQLForm {
    public static void start() {
        final JFrame form = new JFrame("Соединение с MySQL");

        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setLocationRelativeTo(null);
        form.setLayout(new GridBagLayout());

        JLabel urlLabel = new JLabel("URL: ");
        JLabel userNAmeLabel = new JLabel("USER NAME: ");
        JLabel passLabel = new JLabel("PASSWORD: ");

        final JTextField urlField = new JTextField("jdbc:mysql://127.0.0.1:3306/mybookcollections");
        urlField.setPreferredSize(new Dimension(250, 20));
        final JTextField userNameField = new JTextField("root");
        final JTextField passField = new JTextField("root");

        JButton connectButton = new JButton("Соединение");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (urlField.getText().isEmpty() || userNameField.getText().isEmpty() || passField.getText().isEmpty())
                        throw new ErrorFormatDateException();
                        Forms forms = new Forms(new ConnectionBD(urlField.getText(), userNameField.getText(), passField.getText()));
                        forms.start();
                        form.dispose();
                }catch (ErrorFormatDateException ex) {
                    ex.messageForm("Все поля должны быть заполнены");
                } catch (SQLException ex) {
                    //new ErrorFormatDateException().messageForm("Сщединение не установленно.");
                    ex.printStackTrace();
                }
            }
        });

        form.add(urlLabel, new GridBagConstraints(0, 0, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0)); //Peredaem component i nastroennii object
        form.add(urlField, new GridBagConstraints(1, 0, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(userNAmeLabel, new GridBagConstraints(0, 1, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(userNameField, new GridBagConstraints(1, 1, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(passLabel, new GridBagConstraints(0, 2, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(passField, new GridBagConstraints(1, 2, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(connectButton, new GridBagConstraints(0, 3, 2,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

        form.setVisible(true);
        form.pack();

    }
}
