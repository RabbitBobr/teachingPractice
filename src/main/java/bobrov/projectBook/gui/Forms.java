package bobrov.projectBook.gui;


import bobrov.projectBook.Book;
import bobrov.projectBook.connectionBD.ConnectToBD;


import bobrov.projectBook.connectionBD.ConnectionBD;
import bobrov.projectBook.exceptions.NotDateException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.TreeSet;

/**
 * Created by пк on 27.07.2016.
 */
public class Forms {

    private static ConnectToBD connect;

    public Forms(ConnectionBD connectMySQL) throws SQLException {
        connect = new ConnectToBD(connectMySQL);
    }

    public void start() {
 //Создание основной формы
        final JFrame form = new JFrame("Учебные материалы");
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setExtendedState(JFrame.MAXIMIZED_BOTH);
        form.setLayout(new BorderLayout());
 //Подключение к БД
        //final ConnectToBD connect = new ConnectToBD();
        final TableModel model = new TableModel(connect.readDate());
        final JTable table = new JTable(model);
       table.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                   Desktop desktop = Desktop.getDesktop();
                   try {

                       URI url = new URI(model.getValueAt(table.getSelectedRow(), 6).toString());
                       desktop.browse(url);
                   } catch (URISyntaxException ex) {
                       new NotDateException().messageForm("Ссылка указана не верно");
                   } catch (IOException ex) {
                       new NotDateException().messageForm("Ссылка указана не верно");
                   }
               }
           }
       });
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
//Нижняя панель с кнопками (устанавливается ниже таблицы)
        JPanel panelUg = new JPanel();
        panelUg.setBackground(Color.CYAN);
//Создание кнопки добавления в БД
        JButton addDateButton = new JButton("Добавить данные");
        addDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDataForm.addNewData(connect, model);



            }
        });
//Создание кнопки удаления из БД
        JButton delDateButton = new JButton("Удалить выделенные строки");
        delDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                connect.deleteDate(getID(table.getSelectedRows(), model));
                model.setRowCount(connect.readDate());
                model.fireTableDataChanged();
            }
        });
//Создание кнопки изменения данных в БД
        JButton updateButton = new JButton("Изменить данные");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int[] id = getID(table.getSelectedRows(), model);
                    if (id.length == 0)
                        throw new NotDateException();
                        UpdateDataForm.udateData(connect, model, id);
                } catch (NotDateException ex) {
                    ex.messageForm("Не выбраны строки для изменения");
                }

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

//Установка компонет на Южную форму
        panelUg.setLayout(new FlowLayout());
        panelUg.add(addDateButton);
        panelUg.add(delDateButton);
        panelUg.add(stateButton);
        panelUg.add(updateButton);
//Создание и настройка Поля выборки поиска
        final JCheckBox nameBookBox = new JCheckBox("Название книги");
        final JCheckBox authorBox = new JCheckBox("Автор");
        final JCheckBox temaBox = new JCheckBox("Тема");
        final JCheckBox trueStatusBox = new JCheckBox("Все прочитанные");
        final JCheckBox falseStatusBox = new JCheckBox("Все не прочитанные");
        final JTextField textSearchField = new JTextField();
        textSearchField.setPreferredSize(new Dimension(180, 20));
        JButton searchButton = new JButton("Поиск");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeSet<Integer> list = new TreeSet<Integer>();

                if (textSearchField.getText().isEmpty())
                    for (Book b : connect.readDate())
                        list.add(b.getIdBook());
                else {
                    if (nameBookBox.isSelected()) {
                        for (Book b : connect.readDate())
                            if (b.getBookName().equals(textSearchField.getText()))
                                list.add(b.getIdBook());
                    }
                    if (authorBox.isSelected()) {
                        for (Book b : connect.readDate())
                            if (b.getAuthor().equals(textSearchField.getText()))
                                list.add(b.getIdBook());
                    }
                    if (temaBox.isSelected()) {
                        for (Book b : connect.readDate())
                            if (b.getTema().equals(textSearchField.getText()))
                                list.add(b.getIdBook());
                    }
                }
                    TreeSet<Integer> copyList = new TreeSet<Integer>(list);
                    if (trueStatusBox.isSelected() && !falseStatusBox.isSelected()) {
                        for (Integer id : copyList)
                            if (model.getBook(id).isStatus() == false)
                                list.remove(id);
                    } else if (!trueStatusBox.isSelected() && falseStatusBox.isSelected()) {
                        for (Integer id : copyList)
                            if (model.getBook(id).isStatus() == true)
                                list.remove(id);
                    }
                    if (trueStatusBox.isSelected() && falseStatusBox.isSelected())
                        model.setRowCount(connect.readDate());
                else {
                        ArrayList<Book> arr = new ArrayList<Book>();
                        for (Integer id : list)
                            arr.add(model.getBook(id));
                        model.setRowCount(arr);
                    }
                model.fireTableDataChanged();

            }
        });

        JButton sbrosButton = new JButton("Сбросить фильтр");
        sbrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameBookBox.setSelected(false);
                authorBox.setSelected(false);
                temaBox.setSelected(false);
                trueStatusBox.setSelected(false);
                falseStatusBox.setSelected(false);
                textSearchField.setText("");
                model.setRowCount(connect.readDate());
                model.fireTableDataChanged();
            }
        });
//Создание и установка компонент на восточную панель
        JPanel panelRight = new JPanel();
        panelRight.setBackground(Color.YELLOW);
        panelRight.setLayout(new FlowLayout());
        panelRight.setPreferredSize(new Dimension(200, 20));
        panelRight.add(textSearchField);
        panelRight.add(nameBookBox);
        panelRight.add(authorBox);
        panelRight.add(temaBox);
        panelRight.add(trueStatusBox);
        panelRight.add(falseStatusBox);
        panelRight.add(searchButton);
        panelRight.add(sbrosButton);



        form.add(scrollTable, BorderLayout.CENTER);
        form.add(panelUg, BorderLayout.SOUTH);
        form.add(panelRight, BorderLayout.EAST);

        form.setVisible(true);
    }
// возвращает список idBook по порядковым номера в таблице
    public static int[] getID(int[] numer, TableModel model) {

        int[] id  = new int[numer.length];
        for (int i = 0; i<numer.length; i++) {
            id[i] = model.getRow().get(numer[i]).getIdBook();
        }
        return id;
    }
}
