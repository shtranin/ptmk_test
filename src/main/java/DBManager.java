import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

    final String DB = "jdbc:postgresql://localhost:5432/postgres";
    final String USER = "postgres";
    final String PW = "1234";
    Connection connection = null;


    public DBManager() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DB,
                    USER, PW);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Statement getStatement() {
        Statement statement = null;
        try {
            statement =  connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statement;
    }

}
