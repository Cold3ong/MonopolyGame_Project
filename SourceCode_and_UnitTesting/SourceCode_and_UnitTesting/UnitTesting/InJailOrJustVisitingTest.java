import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class InJailOrJustVisitingTest {

    private InJailOrJustVisiting inJail;

    @Before
    public void setUp() {
        inJail = new InJailOrJustVisiting("InJailOrJustVisiting", 1);
    }

    @Test
    public void testInJailType() {
        assertEquals("InJailOrJustVisiting", inJail.getType());
    }

    @Test
    public void testInJailPosition() {
        assertEquals(1, inJail.getPosition());
    }
}