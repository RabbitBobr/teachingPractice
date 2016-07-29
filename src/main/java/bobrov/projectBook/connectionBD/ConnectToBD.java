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
//Считывает все данные из БД и возвращает список с ними
    public ArrayList<Book> readDate() {
        ArrayList<Book> arr = new ArrayList<>();
        try {
            ResultSet setBook = statement.executeQuery("SELECT * FROM mybookcollections.book");
            while(setBook.next()){

                    arr.add(new Book(setBook.getInt(1), setBook.getString(2), setBook.getString(3), setBook.getInt(4),
                            setBook.getString(5),((setBook.getInt(6) == 1) ? true : false ), setBook.getString(7)));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }
//Вносит необходимые изменения в БД
    public void updateDate(int id, String pole, Object update) {

            try {
                if (update instanceof Boolean)
                    statement.execute("UPDATE mybookcollections.book SET " + pole + "=" + ((Boolean)update?1:0) + " " +
                            "WHERE idBook="+ id);
                else
                    statement.execute("UPDATE mybookcollections.book SET " + pole + "=" + ((String)update) + " " +
                            "WHERE idBook="+ id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
//Добавляет новую строку в БД
    public void addDate(Book book) {

        try {
            statement.execute("INSERT INTO mybookcollections.book(author, bookName, status, Tema, Data, link) VALUES " +
                                " ('" + book.getAuthor() + "', '" + book.getBookName() + "', " + (book.isStatus() ? 1 : 0) + ", '" + book.getTema() + "', " + book.getData() +" , '" + book.getLink() + "')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//Удаляет строку из БД
    public void deleteDate(int[] id) {

        try {
            for (int i=0; i<id.length; i++)
                statement.execute("DELETE FROM mybookcollections.book WHERE idBook=" + id[i] + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//Завершает соединение с БД
    public void endConnect() {
        try {
            statement.close();
            connection.close();
            System.out.println("EndConnect");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//Меняет статус на прочитано
    public void statusTrue(int[] id) {
        for (int i : id)
            updateDate(i, "status", true);
    }




    }

