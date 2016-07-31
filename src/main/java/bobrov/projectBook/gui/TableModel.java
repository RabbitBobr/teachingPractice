package bobrov.projectBook.gui;

import bobrov.projectBook.Book;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by пк on 28.07.2016.
 */
public class TableModel extends AbstractTableModel {
    private final int columnCount = 7;
    private ArrayList<Book> row;

    public TableModel(ArrayList<Book> rowCount) {

        this.row = rowCount;
    }

    public ArrayList<Book> getRow() {
        return row;
    }

    @Override
    public int getRowCount() {
        return row.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    public void setRowCount(ArrayList<Book> rowCount) {
        this.row = rowCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = row.get(rowIndex);
        switch (columnIndex) {
            case 0: return book.getIdBook();
            case 1: return book.getBookName();
            case 2: return book.getAuthor();
            case 3: return book.getData();
            case 4: return book.getTema();
            case 5: return book.isStatus();
            case 6: return book.getLink();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "#id";
            case 1: return "Название книги";
            case 2: return "Автор";
            case 3: return "Год издания";
            case 4: return "Тема";
            case 5: return "Статус прочтения";
            case 6: return "Ссылка/Описание";
        }
        return "";
    }

    public Book getBook (int id) {
        for (Book b : row)
            if (b.getIdBook() == id)
                return b;
        return null;
    }


}
