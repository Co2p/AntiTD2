package helpers;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Alexander Nyström(dv15anm) on 30/11/2016.
 */
public class LevelParser {

    private DocumentBuilder parser;
    private XPath path;
    private boolean error = false;
    private  ErrorMessages errorMessage;

    private int levelCount;
    private ArrayList<String> levelName = new ArrayList<>();
    private ArrayList<Long> credits = new ArrayList<>();
    private ArrayList<Integer> unitsToWin = new ArrayList<>();
    private ArrayList<Integer> towerSpawnRate = new ArrayList<>();
    private ArrayList<Integer> timeLimit = new ArrayList<>();
    private ArrayList<String> className = new ArrayList<>();
    private ArrayList<String> classPath = new ArrayList<>();
    private ArrayList<String[]> map = new ArrayList<>();
    private ArrayList<Integer> columns = new ArrayList<>();
    private ArrayList<Integer> rows = new ArrayList<>();

    /**
     * Setup the parser
     */
    public LevelParser(String schemaFile, ErrorMessages errorMessage){
        this.errorMessage = errorMessage;
        String schemaLang = "http://www.w3.org/2001/XMLSchema";
        SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaLang);
        try {
            Schema schema = schemaFactory.newSchema(
                                new StreamSource(schemaFile));
            DocumentBuilderFactory dbfactory
                    = DocumentBuilderFactory.newInstance();
            dbfactory.setSchema(schema);
            parser = dbfactory.newDocumentBuilder();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            error = true;
            errorMessage.setParseConfigException("Error when creating the" +
                    " document parser. Could not parse the xml.");
        }
        parser.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                error = true;
                errorMessage.setSaxParsWarning("At line: " + exception.getLineNumber() +
                        "column: " + exception.getColumnNumber() +
                        "Following error was found " +
                        exception.getMessage());
            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                error = true;
                errorMessage.setSaxParsError("At line: " + exception.getLineNumber() +
                        " column: " + exception.getColumnNumber() +
                        " Following error was found " +
                        exception.getMessage());
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                error = true;
                errorMessage.setSaxParsFatalError("At line: " + exception.getLineNumber() +
                        "column: " + exception.getColumnNumber() +
                        "Following error was found " +
                        exception.getMessage());
            }
        });
        XPathFactory xpfactory = XPathFactory.newInstance();
        path = xpfactory.newXPath();

    }

    /**
     * Will parse the given xml by loading it by its url.
     * @param fileName file name for the xml
     */
    public void parseFile(String fileName) {
        Document doc = null;
        try {

            URL url = ClassLoader.getSystemClassLoader().getResource(fileName);
            if(url != null) {
                File f = new File(url.toURI());
                if(f.exists() && !f.isDirectory()) {
                    doc = parser.parse(url.openStream());
                } else {
                    error = true;
                    errorMessage.setFileError("Could not find file: "
                                                + fileName);
                }
            } else {
                error = true;
                errorMessage.setFileError("Could not find file: " + fileName);
            }

        } catch (SAXException | IOException | IllegalArgumentException |
                    NullPointerException | URISyntaxException e) {
            error = true;
            errorMessage.setParsError(e.getMessage());
        }

        if(doc != null) {
            try {
                levelCount = Integer.parseInt(path.evaluate(
                        "count(/levellist/level)",doc));

                for (int i = 0; i < levelCount;i++) {
                    levelName.add(i, path.evaluate(
                            "/levellist/level["+(i+1)+"]/@name",doc));

                    credits.add(i,Long.parseLong(path.evaluate(
                            "/levellist/level["+(i+1)+"]/rules[1]" +
                                    "/credits",doc)));

                    unitsToWin.add(i,Integer.parseInt(path.evaluate(
                            "/levellist/level["+(i+1)+"]/rules[1]" +
                                    "/unitstowin",doc)));

                    towerSpawnRate.add(i,Integer.parseInt(path.evaluate(
                            "/levellist/level["+(i+1)+"]/rules[1]" +
                                    "/towerspawnrate",doc)));

                    timeLimit.add(i,Integer.parseInt(path.evaluate(
                            "/levellist/level["+(i+1)+"]/rules[1]" +
                                    "/timelimit",doc)));

                    columns.add(i,Integer.parseInt(path.evaluate(
                            "/levellist/level["+(i+1)+"]/map[1]" +
                                    "/size[1]/column",doc)));

                    rows.add(i,Integer.parseInt(path.evaluate(
                            "/levellist/level["+(i+1)+"]" +
                                    "/map[1]/size[1]/row",doc)));

                    className.add(i, path.evaluate("/levellist/level["+
                                            (i+1)+"]/tile[1]",doc));

                    int rowCount = Integer.parseInt(path.evaluate("" +
                            "count(/levellist/level["+(i+1)+"]/map/*)",doc));
                    String [] str = new String[rowCount];
                    for(int j = 0; j < rowCount; j++) {
                        str[j] = path.evaluate("/levellist/level["+
                                        (i+1)+"]/map[1]/row["+(j+1)+"]",doc);
                    }
                    map.add(i,str);
                }

            } catch (XPathExpressionException e) {
                error = true;
                errorMessage.setXpathException(e.getMessage());
            } catch (NumberFormatException e) {
                error = true;
                errorMessage.setNumberFormat(e.getMessage());
            }

        }

    }



    /**
     * Returns an arraylist containing the maps for the levels
     * @return list containing maps
     */
    public ArrayList<String[]> getMap() {
        return map;
    }

    /**
     * Returns the name of the level gathered from the xml.
     * @return the name.
     */
    public ArrayList<String> getLevelName() {
        return levelName;
    }

    /**
     * Returns the squareStart amount of credits.
     * @return credits count.
     */
    public ArrayList<Long> getCredits() {
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
    public ErrorMessages getErrorMessage() {
        return errorMessage;
    }

    /**
     *
     * @return The number of levels found in the xml
     */
    public int getLevelCount() {
        return levelCount;
    }

    public ArrayList<Integer> getColumns() {
        return columns;
    }

    public ArrayList<Integer> getRows() {
        return rows;
    }

}
