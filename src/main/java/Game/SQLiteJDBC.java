package Game;

import sun.rmi.rmic.iiop.ClassPathLoader;
import sun.tools.java.ClassPath;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;

/**
 * Created by Daniel on 2016-12-17.
 */
public class SQLiteJDBC {

    public SQLiteJDBC() {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");

            stmt = c.createStatement();
            String sql =    "CREATE TABLE HIGHSCORE " +
                            "(ID INT PRIMARY    KEY     NOT NULL," +
                            "NAME               TEXT    NOT NULL," +
                            "LEVEL              TEXT    NOT NULL," +
                            "CREDITS            INT," +
                            "TIME               INT)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            System.out.println("Database updated");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
