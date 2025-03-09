import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayerTest {

    private Player player;
    @Before
    public void setUp() {
        player = new Player(1, "TestPlayer", "Go");
    }

    @Test
    public void testInitialMoney() {
        assertEquals(1500, player.getMoney());
    }

    @Test
    public void testSetName() {
        player.setName("leon");
        assertEquals("leon", player.getName());
    }

    @Test
    public void testSetMoney() {
        player.setMoney(1000);
        assertEquals(1000, player.getMoney());
    }

    @Test
    public void testAddProperty() {
        player.addProperty("Park Place");
        assertTrue(player.getPropertyArrayList().contains("Park Place"));
    }

    @Test
    public void testCurrentPosition() {
        player.setCurrentPos("Free Parking");
        assertEquals("Free Parking", player.getCurrentPos());
    }

    @Test
    public void testCurrentPositionNumber() {
        player.setCurrentPosNo(1);
        assertEquals(1, player.getCurrentPosNo());
    }

    @Test
    public void testPlayerNumber() {
        player.setPlayerNum(3);
        assertEquals(3, player.getPlayerNum());
    }

    @Test
    public void testJailStop() {
        player.setJailStop(3);
        assertEquals(3, player.getJailStop());
    }

    @Test
    public void testGameOverStatus() {
        player.setGameOverStatus(true);
        assertTrue(player.getGameOverStatus());
    }
}