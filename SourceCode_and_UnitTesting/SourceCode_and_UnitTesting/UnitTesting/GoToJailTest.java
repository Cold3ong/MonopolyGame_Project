import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class GoToJailTest {

    private GoToJail goToJail;
    private Player player;

    @Before
    public void setUp() {
        goToJail = new GoToJail("GoToJail", 1);
        player = new Player(1, "TestPlayer", "Go");
    }

    @Test
    public void testTriggerGoJail() {
        goToJail.Trigger_GoJail(player);
        assertEquals(3, player.getJailStop()); // Player should be in jail for 3 turns
        assertEquals("InJail", player.getCurrentPos());
    }
}