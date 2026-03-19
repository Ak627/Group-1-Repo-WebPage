import java.util.stream.IntStream;
import java.util.concurrent.atomic.AtomicInteger; // For use inside lambda

/**
 * General utility class for experimentation.
 *
 * @author Dr. Jody Paul
 * @author CS3250 Fall 2025
 * @version 20251105
 */
public class Util {
  /**
   * Hidden constructor for utility class
   */
  private Util() { }

  /**
   * A Fibonacci sequence generator.
   *
   * @param  index  the index into the Fibonacci sequence
   * @return the Fibonacci number corresponding to the parameter
   */
  public static long fibon(int index) {
    if (index <= 0) return 0;
    if (index == 1) return 1;
    return fibon(index - 1) + fibon(index - 2);
  }

  /**
   * Identify repeated values in a sequence of integers,
   * where the repeats occur with a specified number of
   * intervening elements (given by a parameter).
   * Sends messages to System.out to show incremental processing
   * and detection of repeated value at specified distance.
   * @param numberStream the input stream whose size is indeterminate
   * @param interval the number of intervening elements between repeats
   * @return the number of repeated values identified
   */
  public static int detectRepeats(IntStream numberStream, int interval) {

    AtomicInteger counter = new AtomicInteger(0);

    numberStream.forEach(num -> {
      System.out.println("STATUS:: Processing: " + num);
      //
      // Insert code to determine if current num is repeat at specified interval.
      {   // If so, report and increment counter.
        System.out.println("IDENTIFIED:: Repeat after " + interval + " numbers: " + num);
        counter.incrementAndGet();
        //
      }
      // 
      // Insert any additional contextual code needed.
      //
      });
  
    return counter.get();
  }
}
