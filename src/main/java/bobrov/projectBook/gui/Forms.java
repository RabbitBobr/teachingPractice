package bobrov.projectBook.gui;

import bobrov.projectBook.Book;
import bobrov.projectBook.connectionBD.ConnectToBD;
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

    public static void start() {
        JFrame form = new JFrame("Учебные материалы");
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setExtendedState(JFrame.MAXIMIZED_BOTH);
        form.setLayout(new BorderLayout());

        final ConnectToBD connect = new ConnectToBD(AddConnectPanel.setConnect());
        TableModel model = new TableModel(connect.readDate());
        JTable table = new JTable(model);
        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setPreferredSize(new Dimension(400, 400));

        JPanel panelUg = new JPanel();
        panelUg.setBackground(Color.CYAN);
        JButton addDateButton = new JButton("Добавить данные");
        addDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect.addDate(new Book("автор", "книга", "текст", false));
            }
        });
        panelUg.setLayout(new FlowLayout());
        panelUg.add(addDateButton);

        form.add(scrollTable, BorderLayout.CENTER);
        form.add(panelUg, BorderLayout.SOUTH);

        form.setVisible(true);
    }
}
