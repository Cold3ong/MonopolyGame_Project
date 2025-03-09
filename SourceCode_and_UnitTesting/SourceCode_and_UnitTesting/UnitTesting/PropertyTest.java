import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;


public class PropertyTest {

    private Property property;

    @Before
    public void setUp() {
        // Initialize a Property object before each test
        property = new Property("Test Property", 1, 100, 10, "blue");
    }

    @Test
    public void testPropertyInitialization() {
        assertNotNull(property);
        assertEquals("Test Property", property.getName());
        assertEquals(1, property.getPosition());
        assertEquals(100, property.getPrice());
        assertEquals(10, property.getRent());
        assertEquals("blue", property.getColor());
        assertEquals(0, property.getOwner()); // Owner should be 0 initially
    }

    @Test
    public void testSetName() {
        property.setName("New Property");
        assertEquals("New Property", property.getName());
    }

    @Test
    public void testSetPrice() {
        property.setPrice(200);
        assertEquals(200, property.getPrice());
    }

    @Test
    public void testSetRent() {
        property.setRent(20);
        assertEquals(20, property.getRent());
    }

    @Test
    public void testSetColor() {
        property.setColor("red");
        assertEquals("red", property.getColor());
    }

    @Test
    public void testSetOwner() {
        property.setOwner(1);
        assertEquals(1, property.getOwner());
    }
}