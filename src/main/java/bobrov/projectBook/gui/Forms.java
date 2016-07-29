package bobrov.projectBook.gui;

import bobrov.projectBook.Book;
import bobrov.projectBook.connectionBD.ConnectToBD;
import bobrov.projectBook.connectionBD.ConnectionBD;
import bobrov.projectBook.connectionBD.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by пк on 27.07.2016.
 */
public class Forms {
    private static ConnectionBD connect;

    public static void start() {
 //Создание основной формы
        final JFrame form = new JFrame("Учебные материалы");
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setExtendedState(JFrame.MAXIMIZED_BOTH);
        form.setLayout(new BorderLayout());
 //Подключение к БД
        final ConnectToBD connect = new ConnectToBD(AddConnectPanel.setConnect());
        final TableModel model = new TableModel(connect.readDate());
        final JTable table = new JTable(model);
 //Описание действий при закрытии основной формы
        form.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                connect.endConnect();
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

//Создание таблицы для отображения данных из БД
        JScrollPane scrollTable = new JScrollPane(table);
        scrollTable.setPreferredSize(new Dimension(400, 400));
//Нижняя панель с кнопками (устанавливается ниже таблицы
        JPanel panelUg = new JPanel();
        panelUg.setBackground(Color.CYAN);
        JButton addDateButton = new JButton("Добавить данные");
        addDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect.addDate(new Book("книга", "автор", 2016, "Тема", false, "link"));
                model.setRowCount(connect.readDate());
                model.fireTableDataChanged();
            }
        });
        JButton delDateButton = new JButton("Удалить выделенные строки");
        delDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                connect.deleteDate(getID(table.getSelectedRows(), model));
                model.setRowCount(connect.readDate());
                model.fireTableDataChanged();
            }
        });
        JButton updateButton = new JButton("Изменить данные");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] id = getID(table.getSelectedRows(), model);

            }
        });
        JButton stateButton = new JButton("Книга прочитана");
        stateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect.statusTrue(getID(table.getSelectedRows(), model));
                model.setRowCount(connect.readDate());
                model.fireTableDataChanged();
            }
        });

        panelUg.setLayout(new FlowLayout());
        panelUg.add(addDateButton);
        panelUg.add(delDateButton);
        panelUg.add(stateButton);

        JPanel panelRight = new JPanel();



        form.add(scrollTable, BorderLayout.CENTER);
        form.add(panelUg, BorderLayout.SOUTH);

        form.setVisible(true);
    }

    public static int[] getID(int[] numer, TableModel model) {

        int[] id  = new int[numer.length];
        for (int i = 0; i<numer.length; i++) {
            id[i] = model.getRow().get(numer[i]).getIdBook();
        }
        return id;
    }
}
