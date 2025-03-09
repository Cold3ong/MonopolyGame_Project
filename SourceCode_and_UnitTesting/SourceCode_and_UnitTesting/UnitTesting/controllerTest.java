import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class controllerTest {

    private controller gameController;

    @Before
    public void setUp() {
        gameController = new controller();
    }

    @Test
    public void testGenerateRandomName() {
        String name = controller.generateRandomName();
        assertEquals(5, name.length()); 
    }

}