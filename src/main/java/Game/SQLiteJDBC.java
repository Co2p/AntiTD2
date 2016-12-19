package Game;

import java.sql.*;
import java.util.ArrayList;

/**
 * Connects to the database or creates a new one if its the first time the user
 * starts the game. There´s methods for inserting data into the database and
 * selecting it.
 *
 * Created by Daniel on 2016-12-17.
 */
public class SQLiteJDBC {

    private Connection c = null;
    private Statement stmt = null;
    private int i = 0;

    /**
     * Creates a new database locally. If a database already exists
     * (the user has started the program atleast once) this constructor
     * wont create a new one.
     *
     * The name of the database is highscore.db
     *
     * dv15anm@cs.umu.se Alexander Nyström id13dsm@cs.umu.se Daniel Sjöström
     */
    public SQLiteJDBC() {
        boolean exists = false;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:highscore.db");
            ResultSet resultSet = c.getMetaData().getTables(null,
                    null, null,
                    new String[] {"TABLE"});
            while (resultSet.next()) {
                String databaseName = resultSet.getString(
                        "TABLE_NAME");
                if (databaseName.equals("HIGHSCORE")) {
                    exists = true;
                }
            }
            resultSet.close();
            if (!exists) {
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

    /**
     * Inserts the parameters into the database.
     * @param name Name of the player
     * @param level Name of the level
     * @param credits Number of credits after the level has been completed
     * @param time The time it took for the player to finish the level
     *
     * id13dsm@cs.umu.se Daniel Sjöström
     */
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
            String sql =    "INSERT INTO HIGHSCORE " +
                    "(ID,NAME,LEVEL,CREDITS,TIME) " +
                            "VALUES (" + i +
                            ", '" + name +
                            "', '" + level +
                            "', " + credits +
                            ", '" + time +
                            "' );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all the highscores from a specific level.
     * @param levelName The name of the level
     * @return players List of all the players that has completed that level.
     *
     * dv15anm@cs.umu.se Alexander Nyström id13dsm@cs.umu.se Daniel Sjöström
     */
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
