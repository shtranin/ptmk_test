import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

    private String name;
    private Date birth;
    private char sex;


    public Person(String name,String date, char sex){
        this.name = name;
        this.sex = sex;


        try {
            birth = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public int getAge(){
        Date currentDate = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birth));
        int d2 = Integer.parseInt(formatter.format(currentDate));
        int age = (d2 - d1) / 10000;
        return age;
    }
    public void save(DBManager manager){
        Statement statement = manager.getStatement();
        String query = "INSERT INTO my_table VALUES ('" + name + "', '" + birth + "', '" + sex + "');";
        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public static Person loadPersonFromResultSet(ResultSet rs) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String name = rs.getString(1);
        String date = formatter.format(rs.getDate(2));
        char sex = rs.getString(3).charAt(0);
        return new Person(name,date,sex);

    }


    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return name + " " + formatter.format(birth) + " " + sex;
    }
}
