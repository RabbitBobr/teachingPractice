package bobrov.projectBook.gui;

import bobrov.projectBook.Book;
import bobrov.projectBook.connectionBD.ConnectToBD;
import bobrov.projectBook.connectionBD.ConnectionBD;
import bobrov.projectBook.connectionBD.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * Created by пк on 27.07.2016.
 */
public class Forms {
    private static ConnectionBD connect;

    public static void start() {
        final JFrame form = new JFrame("Учебные материалы");
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setExtendedState(JFrame.MAXIMIZED_BOTH);
        form.setLayout(new BorderLayout());

        final ConnectToBD connect = new ConnectToBD(AddConnectPanel.setConnect());
        final TableModel model = new TableModel(connect.readDate());
        final JTable table = new JTable(model);
        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setPreferredSize(new Dimension(400, 400));

        JPanel panelUg = new JPanel();
        panelUg.setBackground(Color.CYAN);
        JButton addDateButton = new JButton("Добавить данные");
        addDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect.addDate(new Book("книга", "автор", 2016, "Тема", true, "link"));
                model.setRowCount(connect.readDate());
                model.fireTableDataChanged();
            }
        });
        JButton delDateButton = new JButton("Удалить выделенные строки");
        delDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] numer = table.getSelectedRows();
                int[] id  = new int[numer.length];
                for (int i = 0; i<numer.length; i++) {
                    id[i] = model.getRow().get(numer[i]).getIdBook();
                }

                connect.deleteDate(id);
                model.setRowCount(connect.readDate());
                model.fireTableDataChanged();
            }
        });
        JButton updateButton = new JButton("Изменить данные");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] numer = table.getSelectedRows();
                int[] id  = new int[numer.length];
                for (int i = 0; i<numer.length; i++) {
                    id[i] = model.getRow().get(numer[i]).getIdBook();
                }

            }
        });
        JButton stateButton = new JButton("Книга прочитана");

        panelUg.setLayout(new FlowLayout());
        panelUg.add(addDateButton);
        panelUg.add(delDateButton);

        form.add(scrollTable, BorderLayout.CENTER);
        form.add(panelUg, BorderLayout.SOUTH);

        form.setVisible(true);
    }


}
