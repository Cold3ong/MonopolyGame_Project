import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class dicetest {

    @Test
    public void testGetResult() {
        Dice dice = new Dice();
        int result = dice.getResult();
        assertTrue("Result should be between 2 and 8", result >= 2 && result <= 8);
    }

    @Test
    public void testThrowFirstDice() {
        Dice dice = new Dice();
        int roll = dice.throwFirstDice();
        assertTrue("First dice roll should be between 1 and 4", roll >= 1 && roll <= 4);
    }

    @Test
    public void testThrowSecondDice() {
        Dice dice = new Dice();
        int roll = dice.throwSecondDice();
        assertTrue("Second dice roll should be between 1 and 4", roll >= 1 && roll <= 4);
    }
}