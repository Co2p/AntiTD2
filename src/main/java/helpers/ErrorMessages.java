package helpers;

/**
 * Created by Alexander Nyström(dv15anm) on 09/12/2016.
 */
public class ErrorMessages {
    private String saxException;
    private String parseConfigException;
    private String saxParsWarning;
    private String saxParsError;
    private String saxParsFatalError;
    private String xpathException;
    private String numberFormat;
    private String fileError;
    private String parsError;
    private int size;

    public ErrorMessages() {

    }

    public String getSaxException() {
        return saxException;
    }

    public void setSaxException(String saxException) {
        size++;
        this.saxException = saxException;
    }

    public String getParseConfigException() {
        return parseConfigException;
    }

    public void setParseConfigException(String parseConfigException) {
        size++;
        this.parseConfigException = parseConfigException;
    }

    public String getSaxParsWarning() {
        return saxParsWarning;
    }

    public void setSaxParsWarning(String saxParsWarning) {
        size++;
        this.saxParsWarning = saxParsWarning;
    }

    public String getSaxParsError() {
        return saxParsError;
    }

    public void setSaxParsError(String saxParsError) {
        size++;
        this.saxParsError = saxParsError;
    }

    public String getSaxParsFatalError() {
        return saxParsFatalError;
    }

    public void setSaxParsFatalError(String saxParsFatalError) {
        size++;
        this.saxParsFatalError = saxParsFatalError;
    }

    public String getXpathException() {
        return xpathException;
    }

    public void setXpathException(String xpathException) {
        size++;
        this.xpathException = xpathException;
    }

    public String getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(String numberFormat) {
        size++;
        this.numberFormat = numberFormat;
    }

    public String getFileError() {
        return fileError;
    }

    public void setFileError(String fileError) {
        size++;
        this.fileError = fileError;
    }

    public String getParsError() {
        return parsError;
    }

    public void setParsError(String parsError) {
        size++;
        this.parsError = parsError;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "ErrorMessages{" +
                "'" + saxException + '\'' +
                "\n '" + parseConfigException + '\'' +
                "\n '" + saxParsWarning + '\'' +
                "\n '" + saxParsError + '\'' +
                "\n '" + saxParsFatalError + '\'' +
                "\n '" + xpathException + '\'' +
                "\n '" + numberFormat + '\'' +
                "\n '" + fileError + '\'' +
                "\n '" + parsError + '\'' +
                '}';
    }
}
