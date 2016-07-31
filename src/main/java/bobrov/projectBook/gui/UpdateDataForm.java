package bobrov.projectBook.gui;

import bobrov.projectBook.Book;
import bobrov.projectBook.connectionBD.ConnectToBD;
import bobrov.projectBook.exceptions.ErrorFormatDateException;
import bobrov.projectBook.exceptions.NotDateException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rabbik on 31.07.2016.
 */
public class UpdateDataForm {
    private static int countClic = 0;
    public static void udateData(final ConnectToBD connect, final TableModel model, final int[] id) {
        final Book[] book = new Book[id.length];
        for (int i=0; i<book.length; i++)
            book[i] = model.getBook(id[i]);

        final JFrame form = new JFrame("Изменить данные о книге");
        form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        form.setLocationRelativeTo(null);
        form.setSize(300, 500);
        form.setLayout(new GridBagLayout());

        JLabel bookNameLabel = new JLabel("Название книги*");
        JLabel authorLabel = new JLabel("Автор");
        JLabel temaLabel = new JLabel("Тема*");
        JLabel dataLabel = new JLabel("Дата издания");
        JLabel linkLabel = new JLabel("Ссылка");

        final JTextField bookNAmeField = new JTextField(45);
        final JTextField authorField = new JTextField(45);
        final JTextField temaField = new JTextField(45);
        final JTextField dataField = new JTextField(4);


        final JTextField linkField = new JTextField();

        final JCheckBox statusCheckBox = new JCheckBox("Книга прочитана");

        JButton updateDataButton = new JButton("Сохранить данные");
        updateDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (bookNAmeField.getText().isEmpty() || temaField.getText().isEmpty())
                        throw new NotDateException();
                    try {
                        Integer.parseInt(dataField.getText());
                    } catch (Exception ex) {
                        throw new ErrorFormatDateException();
                    }
                    if (!book[countClic].getBookName().equals(bookNAmeField.getText()))
                        connect.updateDate(book[countClic].getIdBook(), "bookName", bookNAmeField.getText());
                    if (!book[countClic].getAuthor().equals(authorField.getText()))
                        connect.updateDate(book[countClic].getIdBook(), "author", authorField.getText().isEmpty() ? "author unknown" : authorField.getText());
                    if (!book[countClic].getTema().equals(temaField.getText()))
                        connect.updateDate(book[countClic].getIdBook(), "Tema", temaField.getText());
                    if (book[countClic].getData() != (dataField.getText().isEmpty() ? 1900 : Integer.parseInt(dataField.getText())))
                        connect.updateDate(book[countClic].getIdBook(), "Data", dataField.getText().isEmpty() ? 1900 : Integer.parseInt(dataField.getText()));
                    if (!book[countClic].getLink().equals(linkField.getText()))
                        connect.updateDate(book[countClic].getIdBook(), "link", linkField.getText());
                    if (statusCheckBox.isSelected() != book[countClic].isStatus())
                        connect.updateDate(book[countClic].getIdBook(), "status", statusCheckBox.isSelected());
                    countClic++;
                    if (book.length == countClic) {
                        model.setRowCount(connect.readDate());
                        model.fireTableDataChanged();
                        countClic = 0;
                        form.dispose();
                    }
                    else {
                        bookNAmeField.setText(book[countClic].getBookName());
                        authorField.setText(book[countClic].getAuthor());
                        temaField.setText(book[countClic].getTema());
                        dataField.setText(String.valueOf(book[countClic].getData()));
                        linkField.setText(book[countClic].getLink());
                        statusCheckBox.setSelected(book[countClic].isStatus());

                        statusCheckBox.setSelected(false);
                        form.setVisible(true);
                        model.setRowCount(connect.readDate());
                        model.fireTableDataChanged();
                    }
                  } catch (ErrorFormatDateException ex) {
                    ex.messageForm("Поле Год издания может содержать только цифры");
                }
                catch (NotDateException ex) {
                    ex.messageForm("Тема и Название книги не могут быть пустыми");
                }

            }
        });

        form.add(bookNameLabel, new GridBagConstraints(0, 0, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(bookNAmeField, new GridBagConstraints(1, 0, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(authorLabel, new GridBagConstraints(0, 1, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(authorField, new GridBagConstraints(1, 1, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(dataLabel, new GridBagConstraints(0, 2, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(dataField, new GridBagConstraints(1, 2, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(temaLabel, new GridBagConstraints(0, 3, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(temaField, new GridBagConstraints(1, 3, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(linkLabel, new GridBagConstraints(0, 4, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(linkField, new GridBagConstraints(1, 4, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(statusCheckBox, new GridBagConstraints(0, 5, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));
        form.add(updateDataButton, new GridBagConstraints(1, 5, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));

            bookNAmeField.setText(book[countClic].getBookName());
            authorField.setText(book[countClic].getAuthor());
            temaField.setText(book[countClic].getTema());
            dataField.setText(String.valueOf(String.valueOf(book[countClic].getData()).toCharArray()));
            linkField.setText(book[countClic].getLink());
            statusCheckBox.setSelected(book[countClic].isStatus());

            form.setVisible(true);
            form.pack();



    }
}
