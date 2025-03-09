import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class FreeParkingTest {

    private FreeParking freeParking;

    @Before
    public void setUp() {
        freeParking = new FreeParking("FreeParking", 1);
    }

    @Test
    public void testFreeParkingType() {
        assertEquals("FreeParking", freeParking.getType());
    }

    @Test
    public void testFreeParkingPosition() {
        assertEquals(1, freeParking.getPosition());
    }

    
}