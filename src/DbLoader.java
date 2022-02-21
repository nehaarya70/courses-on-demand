
import java.sql.*;

public class DbLoader 
{

    public ResultSet getResultSet(String query) {
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vod", "root", "superman");
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultSet;

    }
}
