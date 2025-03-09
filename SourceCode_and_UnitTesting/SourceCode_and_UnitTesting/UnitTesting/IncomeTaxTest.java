import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class IncomeTaxTest {

    private IncomeTax incomeTax;
    private Player player;

    @Before
    public void setUp() {
        incomeTax = new IncomeTax("Income Tax", 1);
        player = new Player(1, "TestPlayer", "Go");
        player.setMoney(1000); // Set initial money
    }

    @Test
    public void testTriggerIncomeTax() {
        incomeTax.Trigger_incomeTax(player);
        assertEquals(900, player.getMoney()); // Player should pay 10% tax
    }
    
    @Test
    public void testTriggerIncomeTaxGameOver() {
        player.setMoney(0); // Set money low enough to trigger game over
        incomeTax.Trigger_incomeTax(player);
        assertTrue(player.getGameOverStatus());
        assertEquals(0, player.getMoney());
    }
}