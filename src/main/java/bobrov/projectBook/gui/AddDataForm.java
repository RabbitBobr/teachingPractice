package bobrov.projectBook.gui;

import bobrov.projectBook.Book;
import bobrov.projectBook.connectionBD.ConnectToBD;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

/**
 * Created by Rabbik on 29.07.2016.
 */
public class AddDataForm {


    public static void addNewData(final ConnectToBD connect, final TableModel model) {
        final JFrame form = new JFrame("Добавить книгу");
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
        dataField.setDocument(new PlainDocument(){
            String chars = "0123456789.";
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if(chars.indexOf(str)!=-1){
                    super.insertString( offs, str, a);
                }
            }
        });
        final JTextField linkField = new JTextField();

        final JCheckBox statusCheckBox = new JCheckBox("Книга прочитана");

        JButton addDataButton = new JButton("Добавить книгу");
        addDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect.addDate(new Book(bookNAmeField.getText(), authorField.getText(),
                        (dataField.getText().isEmpty()?2016:Integer.parseInt(dataField.getText())),
                        temaField.getText(), statusCheckBox.isSelected(), linkField.getText()));
                bookNAmeField.setText("");
                authorField.setText("");
                temaField.setText("");
                dataField.setText("");
                linkField.setText("");
                statusCheckBox.setSelected(false);
                model.setRowCount(connect.readDate());
                model.fireTableDataChanged();
                form.setVisible(true);
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
        form.add(addDataButton, new GridBagConstraints(1, 5, 1,1,1,1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2, 2, 2, 2), 0, 0));


        form.setVisible(true);
        form.pack();
    }
}
