import org.junit.Assert;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;


/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class TestLevelBuilder {

    @Test
    public void TestLevelBuilder(){

    }

    @Test
    public void TestBuildLevel() {

    }

    @Test
    public void TestGetFileName() {
        LevelBuilder lb = new LevelBuilder("fileName");
        Assert.assertEquals("fileName", lb.getFileName());
    }

    @Test
    public void TestStringArrayToString() {
        LevelBuilder lb = new LevelBuilder();
        String[] sArr = new String[10];
        for (int i = 0; i < 10; i++) {
            sArr[i] = "" + i;
        }
        Assert.assertEquals("0123456789", lb.stringArrayToString(sArr));
    }
}
