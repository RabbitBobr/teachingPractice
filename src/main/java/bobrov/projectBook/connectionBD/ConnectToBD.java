package bobrov.projectBook.connectionBD;

import bobrov.projectBook.Book;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Statement;

import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;
import java.util.ArrayList;

/**
 * Created by пк on 28.07.2016.
 */
public class ConnectToBD {
        private Driver driver;
        private Statement statement;
        private Connection connection;

        public ConnectToBD(ConnectionBD connect) {


            try  {
                this.driver = new FabricMySQLDriver();
                this.connection = DriverManager.getConnection(connect.getURL(), connect.getUSERNAME(), connect.getUSERPASSWORD());
                this.statement = connection.createStatement();

            }catch (SQLException e) {

            }
        }

    public ArrayList<Book> readDate() {
        ArrayList<Book> arr = new ArrayList<>();
        try {
            ResultSet setBook = statement.executeQuery("SELECT * FROM mybookcollections.book");
            while(setBook.next()){
                arr.add(new Book(setBook.getInt(1), setBook.getString(2), setBook.getString(3), setBook.getString(5), (setBook.getInt(4)==0)?false:true));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public void updateDate(Book book, String pole, Object update) {

            try {
                if (update instanceof Boolean)
                    statement.execute("UPDATE mybookcollections.book SET " + pole + "=" + ((Boolean)update?1:0) + " " +
                            "WHERE id="+ book.getId());
                else
                    statement.execute("UPDATE mybookcollections.book SET " + pole + "=" + ((String)update) + " " +
                            "WHERE id="+ book.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void addDate(Book book) {
        int status = book.isStatus()?1:0;
        try {
            statement.execute("INSERT INTO mybookcollections.book(author, bookName, status, topic) VALUES " +
                                " (" + book.getAuthor() + ", " + book.getBookName() + ", " + status + ", " + book.getTema() + ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void endConnect() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    }

