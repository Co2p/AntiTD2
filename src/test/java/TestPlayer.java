import Game.Player;
import Game.Results;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;


public class TestPlayer {

    @Test
    public void TestCreatePlayerNoName(){
        Player p = new Player();
        assertEquals(p.getName(), "Unknown");
    }

    @Test
    public void TestCreatePlayerWithName(){
        Player p = new Player("Game.Player");
        assertEquals(p.getName(), "Game.Player");
    }

    @Test
    public void TestCreatePlayerWithNameCahanged(){
        Player p = new Player("Game.Player");
        assertNotSame(p.getName(), "Unknown");
    }

    @Test
    public void TestSetPlayerNoName(){
        Player p = new Player();
        p.setName("Game.Player");
        assertEquals(p.getName(), "Game.Player");
    }

    @Test
    public void TestSetPlayerNoNameChanged(){
        Player p = new Player();
        p.setName("Game.Player");
        assertNotSame(p.getName(), "Unknown");
    }

    @Test
    public void TestSetNewPlayerName(){
        Player p = new Player("Game.Player");
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
