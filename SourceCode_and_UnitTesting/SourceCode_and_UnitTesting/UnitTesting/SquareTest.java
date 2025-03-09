import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class SquareTest {

    private Property property;

    @Before
    public void setUp() {
        property = new Property("Test Property", 1, 100, 10, "blue");
    }

    @Test
    public void testSquareType() {
        assertNotNull(property.getType());
        assertEquals("Property", property.getType());
    }

    @Test
    public void testSetType() {
        property.setType("New Type");
        assertEquals("New Type", property.getType());
    }

    @Test
    public void testSquarePosition() {
        assertEquals(1, property.getPosition());
    }

    @Test
    public void testSetPosition() {
        property.setPosition(5);
        assertEquals(5, property.getPosition());
    }

    @Test
    public void testPlayerArrayInitialization() {
        assertNotNull(property.getPlayerHere());
        assertEquals(6, property.getPlayerHere().length);
        for (int player : property.getPlayerHere()) {
            assertEquals(0, player);
        }
    }

    @Test
    public void testSetPlayerHere() {
        property.setPlayerHere(1);
        assertEquals(1, property.getPlayerHere()[0]);
    }

    
}