import java.sql.*;
import java.util.Properties;

public class Main
{
    // The JDBC Connector Class.
    private static final String dbClassName = "com.mysql.jdbc.Driver";

    // Connection string. mydatabase is the database the program
    // is connecting to. You can include user and password after this
    // by adding (say) ?user=craig&password=craig. Not recommended!

    private static final String CONNECTION =
            "jdbc:mysql://193.191.177.84:3306/projektweek";

    public static void main(String[] args) throws
            ClassNotFoundException,SQLException
    {
        System.out.println(dbClassName);
        // Class.forName(xxx) loads the jdbc classes and
        // creates a drivermanager class factory
        Class.forName(dbClassName);

        // Properties for user and password. Here the user and password are both 'craig'
        Properties p = new Properties();
        p.put("user","dirk");
        p.put("password","aklE15z@e");
        p.put("ssl", "false");

        // Now try to connect
        Connection c = DriverManager.getConnection(CONNECTION,p);

        System.out.println("It works !");
        c.close();
    }
}
