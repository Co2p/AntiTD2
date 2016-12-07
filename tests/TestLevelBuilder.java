import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alexander Nyström(dv15anm) on 01/12/2016.
 */
public class TestLevelBuilder {

    @Test
    public void TestLevelBuilder(){

    }

    @Test
    public void TestBuildLevel() {
        LevelBuilder lb = new LevelBuilder("fileName");
        Assert.assertEquals(Level.class, lb.buildLevel(1).getClass());
    }

    @Test
    public void TestGetFileName() {
        LevelBuilder lb = new LevelBuilder("fileName");
        Assert.assertEquals("fileName", lb.getFileName());
    }

}
