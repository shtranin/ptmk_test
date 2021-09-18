import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HeadClass {
    private static void createTable(DBManager manager){
        String query = "CREATE TABLE if not exists my_table (name VARCHAR, birth DATE, sex CHAR);";
        Statement statement = manager.getStatement();
        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Table created");
    }
    private static void insertNewPerson(String name, String birth, char sex,DBManager manager){
      Person person = new Person(name,birth,sex);
      person.save(manager);
        System.out.println("Person inserted");
    }
    private static void selectPerson(DBManager manager) throws SQLException {
        Statement statement = manager.getStatement();
        String query  = "SELECT DISTINCT ON (name, birth) name,birth,sex from my_table;";
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        while(rs.next()){

            Person person = Person.loadPersonFromResultSet(rs);
            System.out.print(person);
            System.out.println(" " + person.getAge());
        }
    }
    private static void fillingTable(DBManager manager){
        RandomPersonFactory factory = RandomPersonFactory.getFactory();
        for (int i = 0; i < 1000000; i++) {
            factory.getRandomPerson().save(manager);
        }

        for (int i = 0; i < 100; i++) {
            new Person("Ffff Ffff Ffff","2000-01-01",'M').save(manager);
        }
        System.out.println("Table filled");


    }
    private static void selectMalesWithFNames(DBManager manager) throws SQLException {
       long startTime = System.nanoTime();
        Statement statement = manager.getStatement();
        String query = "SELECT * FROM my_table WHERE name LIKE 'F%' AND sex = 'M';";
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        while(rs.next()){

            Person person = Person.loadPersonFromResultSet(rs);
            System.out.print(person);
            System.out.println(" " + person.getAge());
        }
        long endTime = System.nanoTime();
        System.out.println("Duraction time - " + (endTime - startTime)/1000000 + " milliseconds");
    }
    private static void dropTable(DBManager manager){
        Statement statement = manager.getStatement();
        String query = "DROP TABLE my_table;";
        try {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("bd dropped");
    }




    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBManager manager = new DBManager();

        switch(args[0]){
            case  "1" :
                createTable(manager);
                break;
            case "2" :
                String name = args[1] + " " + args[2] + " " + args[3];
                String date = args[4];
                char sex = args[5].charAt(0);
                insertNewPerson(name,date,sex,manager);
                break;
            case "3" :
                selectPerson(manager);
                break;
            case "4" :
                fillingTable(manager);
                break;
            case "5" :
                selectMalesWithFNames(manager);
                break;
            case "6" :
                dropTable(manager);
                break;
        }


    }
}
