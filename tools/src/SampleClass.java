/**
 * Sample class.
 * @author Dr. Jody Paul
 * @version Demonstration
 */
public class SampleClass {
    private boolean condition = false;
    private static final int SMALL_MAX = 10;
    private static final int ANSWER = 42;

    /** Constructor. */
    public SampleClass() {
        this.condition = false;
    }

    /**
     * A basic "hello world" main method.
     * @param args command-line parameters; ignored
     */
    public static void main(String[] args) {
        System.out.println("Hello from SampleMain.main!");
    }

    /**
     * Increment parameter value by 42.
     * @param any any int value
     * @return the value of the parameter plus 42
     */
    public int fortyTwo(int any) {
        return any + ANSWER;
    }

    /**
     * Simple binary toggle.
     * @return current condition after toggling
     */
    public boolean toggle() {
        return (condition = !condition);
    }

    /**
     * Repeated concatenation.
     * @return result of building a string in a loop
     */
    public String concatInLoop() {
        String builder = "[";
        for (int i = 0; i < SMALL_MAX; i++) {
            builder += " " + i;
        }
        builder += " ]";
        return builder;
    }

    /**
     * Strange code method.
     * @return sum of numbers up to parameter
     */
    public int strangeMethod(int parameter) {
        int j = 0;
        for (int i = 1; i < ANSWER; i++) {
            j = i + 1;
        }
        return j;
    }

    /**
     * Example of deeply embedded loops.
     */
    public void embeddedLoops() {
        int howmany = 0;
        for (int x = 0; x < SMALL_MAX; x++) {
            for (int i = 0; i < SMALL_MAX; i++) {
                for (int j = i; j < SMALL_MAX; j++) {
                    for (int k = 0; k < 8; k++) {
                        if (0 == k % 2) {
                            howmany++;
                        } else {
                            if (0 == j % 2) {
                                if (1 == i % 2) {
                                    if (0 == x % 2) {
                                        howmany--;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
