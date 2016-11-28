import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Alexander Nyström(dv15anm) on 24/11/2016.
 */
public class TestResults {

    private Results results;

    @Before
    public void createResults() {
        results = new Results();
    }
    @Test
    public void shouldCreateResult() {
        assertNotNull(results);
    }

    @Test
    public void shouldReturnTime() {
        assertEquals(0,results.getTime());
    }

    @Test
    public void shouldSetTime() {
        results.setTime(5);
        assertEquals(5,results.getTime());
    }

    @Test
    public void shouldReturnCreditsUsed() {
        assertEquals(0,results.getCreditsUsed());
    }

    @Test
    public void shouldSetCreditsUsed() {
        results.setCreditsUsed(35);
        assertEquals(35,results.getCreditsUsed());
    }

    @Test
    public void shouldReturnTotalTroops() {
        assertEquals(0,results.getTotalTrooperCount());
    }

    @Test
    public void shouldSetTotalTrooperCount() {
        results.setTotalTrooperCount(22);
        assertEquals(22,results.getTotalTrooperCount());
    }

}
