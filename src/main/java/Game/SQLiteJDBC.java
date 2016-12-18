package Game;

//import sun.rmi.rmic.iiop.ClassPathLoader;
//import sun.tools.java.ClassPath;
//
//import java.io.File;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLClassLoader;
import java.sql.*;

/**
 * Created by Daniel on 2016-12-17.
 */
public class SQLiteJDBC {

    private Connection c = null;
    private Statement stmt = null;
    private int i = 0;

    public SQLiteJDBC() {

        try {
            Class.forName("org.sqlite.JDBC");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //TODO create a unique ID for every post
    public void postToDb(String name, String level, int credits, int time) {
        i++;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql =    "INSERT INTO HIGHSCORE (ID,NAME,LEVEL,CREDITS,TIME) " +
                            "VALUES (" + i +
                            ", '" + name +
                            "', '" + level +
                            "', " + credits +
                            ", " + time +
                            " );";
            stmt.executeUpdate(sql);

            getFromDb();
            stmt.close();
            c.commit();
            c.close();
            System.out.println("Posted into DB");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Primarily used for test purpose
    public void getFromDb() {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM HIGHSCORE;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                int level  = rs.getInt("level");
                String  credits = rs.getString("credits");
                float time = rs.getFloat("time");
                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "LEVEL = " + level );
                System.out.println( "CREDITS = " + credits );
                System.out.println( "TIME = " + time );
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
