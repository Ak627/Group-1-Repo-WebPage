import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
/**
 * The test class for SampleClass.
 * @author Dr. Jody Paul
 * @version Demonstration
 */
public class SampleClassTest extends junit.framework.TestCase {
    /** The answer to the ultimate question. */
    private static final int ANSWER = 42;
    
    /** Constructor. */
    public SampleClassTest() { }

    /**
     * Set up the test fixture.
     * Called before every test case method.
     */
    @BeforeEach
    protected void setUp() { }

    /**
     * Tear down the test fixture.
     * Called after every test case method.
     */
    @AfterEach
    protected void tearDown() { }

    /**
     * Test the toggle() method.
     */
    @Test
    public void testToggle() {
        SampleClass sc = new SampleClass();
        assertTrue(sc.toggle());
        assertFalse(sc.toggle());
        assertTrue(sc.toggle());
    }

    /**
     * Simple tests of fortyTwo method.
     * Includes one invalid test.
     */
    @Test
    public void testFortyTwo() {
        SampleClass sm = new SampleClass();
        assertEquals(ANSWER, sm.fortyTwo(0));
        assertEquals(0, sm.fortyTwo(-ANSWER));
        assertEquals(ANSWER - 1, sm.fortyTwo(0));
        assertEquals(1, sm.fortyTwo(-41));
    }

    /**
     * Tests of the strangeMethod.
     */
    @Test
    public void testStrangeMethod() {
        SampleClass sm = new SampleClass();
        assertTrue(0 < sm.strangeMethod(1));
        assertTrue(0 < sm.strangeMethod(-100));
    }
}
