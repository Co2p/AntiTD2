import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Alexander Nyström(dv15anm) on 30/11/2016.
 */
public class DOMParser {

    private DocumentBuilder builder;
    private XPath path;
    private boolean error = false;
    private  String errorMessage;

    public int getLevelCount() {
        return levelCount;
    }

    private int levelCount;
    private ArrayList<String> levelName = new ArrayList<>();
    private ArrayList<Double> credits = new ArrayList<>();
    private ArrayList<Integer> unitsToWin = new ArrayList<>();
    private ArrayList<Integer> towerSpawnRate = new ArrayList<>();
    private ArrayList<Integer> timeLimit = new ArrayList<>();
    private ArrayList<String> className = new ArrayList<>();
    private ArrayList<String> classPath = new ArrayList<>();
    private ArrayList<String[]> map = new ArrayList<>();

    /**
     * Setup the parser
     */
    public DOMParser(){
        DocumentBuilderFactory dbfactory
                = DocumentBuilderFactory.newInstance();
        try {
            builder = dbfactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            error = true;
            errorMessage = "Error when creating the document builder. Could" +
                    " not parse the xml.";
        }
        XPathFactory xpfactory = XPathFactory.newInstance();
        path = xpfactory.newXPath();

    }

    /**
     * Will parse the given xml by loading it by its url.
     * @param fileName file name for the xml
     */
    public void parseFile(String fileName) {
        URL url = getClass().getResource(fileName);
        Document doc = null;
        try {
            doc = builder.parse(url.openStream());
        } catch (SAXException | IOException | IllegalArgumentException e) {
            error = true;
            errorMessage = e.getCause().toString();
        }

        if(doc != null) {
            try {
                levelCount = Integer.parseInt(path.evaluate("count(/levellist/level)",doc));
                for (int i = 0; i < levelCount;i++) {
                    levelName.add(i, path.evaluate("/levellist/level["+(i+1)+"]/@name",doc));
                    credits.add(i,Double.parseDouble(path.evaluate("/levellist/level["+(i+1)+"]/rules[1]/credits",doc)));
                    unitsToWin.add(i,Integer.parseInt(path.evaluate("/levellist/level["+(i+1)+"]/rules[1]/unitstowin",doc)));
                    towerSpawnRate.add(i,Integer.parseInt(path.evaluate("/levellist/level["+(i+1)+"]/rules[1]/towerspawnrate",doc)));
                    timeLimit.add(i,Integer.parseInt(path.evaluate("/levellist/level["+(i+1)+"]/rules[1]/timelimit",doc)));
                    className.add(i, path.evaluate("/levellist/level["+(i+1)+"]/zone[1]/tile[1]/@className",doc));
                    classPath.add(i,path.evaluate("/levellist/level["+(i+1)+"]/zone[1]/tile[1]",doc));
                    int rowCount = Integer.parseInt(path.evaluate("count(/levellist/level["+(i+1)+"]/map/*)",doc));
                    String[] str = new String[8];
                    for(int j = 0; j < rowCount; j++) {
                        str[j] = path.evaluate("/levellist/level["+(i+1)+"]/map[1]/row["+(j+1)+"]",doc);
                    }
                    map.add(i,str);
                }

            } catch (XPathExpressionException e) {
                error = true;
                errorMessage = e.getCause().toString();
            }

        }

    }

    /**
     * Returns the name of the level gathered from the xml.
     * @return the name.
     */
    public ArrayList<String> getLevelName() {
        return levelName;
    }

    /**
     * Returns the start amount of credits.
     * @return credits count.
     */
    public ArrayList<Double> getCredits() {
        return credits;
    }

    /**
     * Returns the required amount of units to win this level
     * @return the unit count.
     */
    public ArrayList<Integer> getUnitsToWin() {
        return unitsToWin;
    }

    /**
     * Returns the spawn rate for the towers.
     * @return the tower spawn rate
     */
    public ArrayList<Integer> getTowerSpawnRate() {
        return towerSpawnRate;
    }

    /**
     * Returns the time limit for this level
     * @return the time limit
     */
    public ArrayList<Integer> getTimeLimit() {
        return timeLimit;
    }

    /**
     * Returns an arraylist containing class names for the zone classes
     * @return list containing the class names
     */
    public ArrayList<String> getClassName() {
        return className;
    }

    /**
     * Returns an arraylist containing the classpaths to the zone classes
     * @return list containing the classpaths
     */
    public ArrayList<String> getClassPath() {
        return classPath;
    }

    /**
     * Returns an arraylist containing the maps for the levels
     * @return list containing maps
     */
    public ArrayList<String[]> getMap() {
        return map;
    }

    /**
     *
     * @return true if an error occurred
     */
    public boolean isError() {
        return error;
    }

    /**
     *
     * @return The error message generated by an error
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
