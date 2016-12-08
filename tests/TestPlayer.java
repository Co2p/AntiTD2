import main.java.Player;
import main.java.Results;
import org.junit.Test;
import static junit.framework.TestCase.*;


public class TestPlayer {

    @Test
    public void TestCreatePlayerNoName(){
        Player p = new Player();
        assertEquals(p.getName(), "Unknown");
    }

    @Test
    public void TestCreatePlayerWithName(){
        Player p = new Player("main.java.Player");
        assertEquals(p.getName(), "main.java.Player");
    }

    @Test
    public void TestCreatePlayerWithNameCahanged(){
        Player p = new Player("main.java.Player");
        assertNotSame(p.getName(), "Unknown");
    }

    @Test
    public void TestSetPlayerNoName(){
        Player p = new Player();
        p.setName("main.java.Player");
        assertEquals(p.getName(), "main.java.Player");
    }

    @Test
    public void TestSetPlayerNoNameChanged(){
        Player p = new Player();
        p.setName("main.java.Player");
        assertNotSame(p.getName(), "Unknown");
    }

    @Test
    public void TestSetNewPlayerName(){
        Player p = new Player("main.java.Player");
        p.setName("NewName");
        assertEquals(p.getName(), "NewName");
    }

    @Test
    public void TestPlayerResults(){
        Player p = new Player();
        Results r = new Results();
        p.setResult(r);
        assertEquals(p.getResult().getTime(),0);
        assertEquals(p.getResult().getCreditsUsed(),0);
        assertEquals(p.getResult().getTotalTrooperCount(),0);
        assertNotNull(p.getResult());
    }
}
