/*
created by author <student@exam.com>
Класс работы с БД
 */
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    Connection cnt;
    Database(){
        try {
            cnt = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student?serverTimezone=UTC","root","");
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null,"Ошибка подключения к Базе данных","Ошибка!",JOptionPane.ERROR_MESSAGE);
            throwables.printStackTrace();
        }
    }
    ResultSet query(String sql){
        try {
            return cnt.createStatement().executeQuery(sql);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    boolean sql(String sql) throws SQLException {
        System.out.println(sql);
        return cnt.createStatement().execute(sql);
    }

}
