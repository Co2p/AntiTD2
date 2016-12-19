package Game;

//import sun.rmi.rmic.iiop.ClassPathLoader;
//import sun.tools.java.ClassPath;
//
//import java.io.File;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLClassLoader;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Created by Daniel on 2016-12-17.
 */
public class SQLiteJDBC {

    private Connection c = null;
    private Statement stmt = null;
    private int i = 0;

    public SQLiteJDBC() {
        boolean excists = false;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:highscore.db");
            ResultSet resultSet = c.getMetaData().getTables(null,
                    null, null,
                    new String[] {"TABLE"});
            while (resultSet.next()) {
                String databaseName = resultSet.getString("TABLE_NAME");
                if (databaseName.equals("HIGHSCORE")) {
                    excists = true;
                }
            }
            resultSet.close();
            if (!excists) {
                stmt = c.createStatement();
                String sql = "CREATE TABLE HIGHSCORE " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           TEXT    NOT NULL, " +
                        " LEVEL            TEXT     NOT NULL, " +
                        " CREDITS        INT, " +
                        " TIME         TEXT)";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO create a unique ID for every post
    public void postToDb(String name, String level, int credits, String time) {
        try {
            c = DriverManager.getConnection("jdbc:sqlite:highscore.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM HIGHSCORE;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                i = id;
            }
            i++;
            String sql =    "INSERT INTO HIGHSCORE (ID,NAME,LEVEL,CREDITS,TIME) " +
                            "VALUES (" + i +
                            ", '" + name +
                            "', '" + level +
                            "', " + credits +
                            ", '" + time +
                            "' );";
            stmt.executeUpdate(sql);

//            getFromDb("Introlevel");
            stmt.close();
            c.commit();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Primarily used for test purpose
    public ArrayList<Player> getFromDb(String levelName) {
        ArrayList<Player> players = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HIGHSCORE WHERE LEVEL = ?";
            c = DriverManager.getConnection("jdbc:sqlite:highscore.db");
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1,levelName);
            ResultSet rs = pstmt.executeQuery();
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                String level  = rs.getString("level");
                int  credits = rs.getInt("credits");
                String time = rs.getString("time");
                Player player = new Player(name);
                Results results = new Results();
                results.setCreditsUsed(credits);
                results.setLevelName(level);
                results.setStringTime(time);
                player.setResults(results);
                players.add(player);
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }



}
