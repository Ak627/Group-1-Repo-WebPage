import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
/**
 * The test class for SampleClass.
* @author Super Bash Bros
* @version 1.0
 */
public class DeskTest extends junit.framework.TestCase {
    /** Constructor. 
    public DeskTest() {}
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
    protected void tearDown() {}
    
    /**
     * Test Size of deck is 52
     */
    @Test
    public void testDeskSize() {
        Deck deck_1 = new Deck();
        assertEquals(52, deck_1.getSize());
    }

    /**
     * First Card is Ace of hearts. 
    
    @Test
    public void testFirstCard() {
        Deck deck_1 = new Deck();
        assertEquals("A", deck_1.getCard(0).getRank());
       
        assertEquals("Hearts", deck_1.getCard(0).getSuit());
    }
    */

}
