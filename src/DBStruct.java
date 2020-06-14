/*
created by author <student@exam.com>
Структуры данных из БД
 */
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBStruct {
    static class service {
        int id;
        String Title;
        Double Cost;
        int DurationInSeconds;
        String Description;
        Double Discount;
        String MainImagePath;
        service(ResultSet rs){
            try {
                id = rs.getInt("ID");
                Title = rs.getString("Title");
                Cost = rs.getDouble("Cost");
                DurationInSeconds = rs.getInt("DurationInSeconds");
                Description = rs.getString("Description");
                Discount = rs.getDouble("Discount");
                MainImagePath = rs.getString("MainImagePath");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
