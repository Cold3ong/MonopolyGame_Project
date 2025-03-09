import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;


public class BoardTest {

    private Board board;
    
    @Before
    public void setUp() {
        board = new Board();
        board.createBoard(); // Ensure the board is created before tests
    }

    @Test
    public void testPrintBoard(){
        String filename = "save/board.json";
        board.loadBoard(filename);
        board.printBoard();
    }
    
    @Test
    public void testSquareListSize() {
        assertEquals(20, board.getSquareList().size());
    }

    @Test
    public void testCreateBoard() {
        board.createBoard();
        assertNotNull(board.getSquareList());
        assertFalse(board.getSquareList().isEmpty());
    }

    @Test
    public void testLoadBoard() {
        String filename = "save/board.json"; 
        board.loadBoard(filename);
        assertEquals(20, board.getSquareList().size());
    }

}