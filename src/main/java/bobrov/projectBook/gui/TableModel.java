package bobrov.projectBook.gui;

import bobrov.projectBook.Book;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by пк on 28.07.2016.
 */
public class TableModel extends AbstractTableModel {
    private final int columnCount = 5;
    private ArrayList<Book> rowCount;

    public TableModel(ArrayList<Book> rowCount) {

        this.rowCount = rowCount;
    }

    @Override
    public int getRowCount() {
        return rowCount.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "#id";
            case 1: return "Автор";
            case 2: return "Название книги";
            case 3: return "Статус прочтения";
            case 4: return "Тема";
        }
        return "";
    }

    public void addDate(Book book) {
        rowCount.add(book);
    }
}
