import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.lang.reflect.Field;
import java.util.Random;

public class ChanceTest {
    private Chance chance;
    private Player player;
    private Random random;

    @Before
    public void setUp() throws Exception {
        chance = new Chance("Chance", 0);
        player = new Player(1, "Player 1", "Go");
        player.setMoney(1500); 
        random = mock(Random.class);

        // Use reflection to set the private 'random' field in the Chance class
        Field randomField = Chance.class.getDeclaredField("random");
        randomField.setAccessible(true); // Allow access to the private field
        randomField.set(chance, random); // Set the mocked Random instance
    }

    @Test
    public void testTriggerChanceGain() {
        when(random.nextBoolean()).thenReturn(true); // Force gain condition
        when(random.nextInt(20)).thenReturn(10); // Force a gain of $100

        chance.Trigger_Chance(player);

        // Verify that the player gained the expected amount
        assertEquals("Player should gain $100", 1600, player.getMoney());
    }

    @Test
    public void testTriggerChanceLoss() {
        when(random.nextBoolean()).thenReturn(false); // Force loss condition
        when(random.nextInt(30)).thenReturn(5); // Force a loss of $50

        chance.Trigger_Chance(player);

        // Verify that the player lost the expected amount
        assertEquals("Player should lose $50", 1450, player.getMoney());
    }

    @Test
    public void testTriggerChanceGameOver() {
        when(random.nextBoolean()).thenReturn(false); // Force loss condition
        when(random.nextInt(30)).thenReturn(15); // Force a loss of $150

        // Set the player's money to $100
        player.setMoney(100);
        chance.Trigger_Chance(player);

        // Verify that the player's money is now $0 and game is over
        assertTrue("Player should be game over", player.getGameOverStatus());
        assertEquals("Player's money should be $0", 0, player.getMoney());
    }
}