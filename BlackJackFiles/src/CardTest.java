import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
/**
 * The test class for SampleClass.
* @author Super Bash Bros
* @version 1.0
 */
public class CardTest {
    /** Constructor. */
    public CardTest() { }

    /**
     * Set up the test fixture.
     * Called before every test case method.
     */
    @BeforeEach
    protected void setUp() {}

    /**
     * Tear down the test fixture.
     * Called after every test case method.
     */
    @AfterEach
    protected void tearDown() { }

    /**
     * Test the can create card rank
     * String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
     * String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
     **/
    @Test
    public void testRank() {
        Card card_1 = new Card("2", "Hearts");
        Card card_2 = new Card("A", "Clubs");
        assertEquals("2", card_1.rank);
        assertEquals("A", card_2.rank);

    }

     /**
     * Test the can create card suit
     **/
     @Test
     public void testSuit() {
        Card card_1 = new Card("2", "Hearts");
        Card card_2 = new Card("A", "Clubs");

        assertEquals("Hearts", card_1.suit);
        assertEquals("Clubs", card_2.suit);
    }
}
