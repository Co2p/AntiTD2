import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import helpers.DOMParser;
/**
 * Created by Alexander Nyström(dv15anm) on 30/11/2016.
 */

//public class ParserTest {
//    public static void main (String[] args) {
//        DOMParser parser = null;
//        parser = new DOMParser("src/main/resources/xml/levelSchema.xml");
//        parser.parseFile("xml/test.xml");
//        if (parser.isError()) {
//            System.out.println(parser.getErrorMessage());
//        }
//        ArrayList<String> levelName = parser.getLevelName();
//        ArrayList<Long> credits = parser.getCredits();
//        ArrayList<Integer> units = parser.getUnitsToWin();
//        ArrayList<Integer> mainr = parser.getTowerSpawnRate();
//        ArrayList<Integer> time = parser.getTimeLimit();
//        ArrayList<String> className = parser.getClassName();
//        ArrayList<String> classPath = parser.getClassPath();
//        ArrayList<ArrayList<String>> maps = parser.getMap();
//        ArrayList<Integer> columns = parser.getColumns();
//        ArrayList<Integer> rows = parser.getRows();
//        for (int i = 0; i < parser.getLevelCount(); i++) {
//            System.out.println("Name of the level is: " + levelName.get(i));
//            System.out.println("Rules: ");
//            System.out.println("Credits: " + credits.get(i));
//            System.out.println("UnitsToWin: " + units.get(i));
//            System.out.println("TowerSpawnRate: " + mainr.get(i));
//            System.out.println("TimeLimit: " + time.get(i));
//            System.out.println("Zone: ");
//            System.out.println("Name of class: " + className.get(i));
//            System.out.println("classPath: " + classPath.get(i));
//            System.out.println("Size of map: ");
//            System.out.println("Rows: " + columns.get(i));
//            System.out.println("Columns: " + rows.get(i));
//            System.out.println("map: ");
//            ArrayList<String> str = maps.get(i);
//            for (String strr : str) {
//                System.out.println(strr);
//            }
//        }
//    }
//=======
//public class ParserTest {
//    public static void main (String[] args) {
//        DOMParser parser = null;
//        parser = new DOMParser("src/main/res/xml/levelSchema");
//        parser.parseFile("src/main/res/xml/test.xml");
//        if (parser.isError()) {
//            System.out.println(parser.getErrorMessage());
//        }
//        ArrayList<String> levelName = parser.getLevelName();
//        ArrayList<Long> credits = parser.getCredits();
//        ArrayList<Integer> units = parser.getUnitsToWin();
//        ArrayList<Integer> towerSpawnRate = parser.getTowerSpawnRate();
//        ArrayList<Integer> time = parser.getTimeLimit();
//        ArrayList<String> className = parser.getClassName();
//        ArrayList<String> classPath = parser.getClassPath();
//        ArrayList<String[]> maps = parser.getMap();
//        for (int i = 0; i < parser.getLevelCount(); i++) {
//            System.out.println("Name of the level is: " + levelName.get(i));
//            System.out.println("Rules: ");
//            System.out.println("Credits: " + credits.get(i));
//            System.out.println("UnitsToWin: " + units.get(i));
//            System.out.println("TowerSpawnRate: " + towerSpawnRate.get(i));
//            System.out.println("TimeLimit: " + time.get(i));
//            System.out.println("Zone: ");
//            System.out.println("Name of class: " + className.get(i));
//            System.out.println("classPath: " + classPath.get(i));
//            System.out.println("map: ");
//            String[] str = maps.get(i);
//            for (String strr : str) {
//                System.out.println(strr);
//            }
//
//        }
//   }
//>>>>>>> master:tests/ParserTest.java

//        System.out.println("Name of the level is: "+parser.getLevelName());
//        System.out.println("Rules: ");
//        System.out.println("Credits: "+parser.getCredits());
//        System.out.println("UnitsToWin: "+parser.getUnitsToWin());
//        System.out.println("TowerSpawnRate: "+parser.getTowerSpawnRate());
//        System.out.println("TimeLimit: "+parser.getTimeLimit());
//        System.out.println("Zone: ");
//        System.out.println("Name of class: "+parser.getClassName());
//        System.out.println("classPath: "+ parser.getClassPath());
//        System.out.println("map: ");
//        String[] map = parser.getMap();
//        for (String str: map) {
//            System.out.println(str);
//}

