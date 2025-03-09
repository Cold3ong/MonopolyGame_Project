import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class GoTest {

    private Go go;
    private Player player;

    @Before
    public void setUp() {
        go = new Go("Go", 1);
        player = new Player(1, "TestPlayer", "Go");
    }

    @Test
    public void testTriggerGo() {
        player.setMoney(1000);
        go.Trigger_Go(player);
        assertEquals(2500, player.getMoney()); // Player should gain $1500
    }
}