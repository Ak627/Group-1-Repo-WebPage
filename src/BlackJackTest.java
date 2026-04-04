import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

// Refer to BlackJackTest.md

public class BlackJackTest {
  // Deck Tests
  
  // 1a
  @Test
  public void testDrawReturnsCard() {
    Deck deck = new Deck();
    Card drawn = deck.draw();
    assertNotNull(drawn);
  }

  //1b
  @Test
  public void testNoDuplicateDraws() {
    Deck deck = new Deck();
    List<String> seen = new ArrayList<>();
    for (int i = 0; i < 52; i++) {
      String card = deck.draw().DisplayCard();
      assertFalse(seen.contains(card));
      seen.add(card);
    }
  }

  // 4a
  @Test
  public void testShuffleChangesOrder() {
    Deck deck = new Deck();
    List<String> before = new ArrayList<>();
    for (int i = 0; i < 52; i++) {
      before.add(deck.draw().DisplayCard());
    }
    deck.reset();
    deck.shuffle();
    List<String> after = new ArrayList<>();
    for (int i = 0; i < 52; i++) {
      after.add(deck.draw().DisplayCard());
    }
    assertFalse(before.equals(after));
  }

  //4b
  @Test
  public void testShuffleMaintainsDeckSize() {
    Deck deck = new Deck();
    deck.shuffle();
    assertEquals(52, deck.getSize());
  }
  
  // Card Tests

  // 2a
  @Test
  public void testDisplayCard() {
    Card card = new Card("A", "Hearts");
    assertEquals("A of Hearts", card.DisplayCard());
  }

  // 3a
  @Test
  public void testFaceCardValue() {
    assertEquals(10, new Card("J", "Hearts").value());
    assertEquals(10, new Card("Q", "Hearts").value());
    assertEquals(10, new Card("K", "Hearts").value());
  }

  //3b
  @Test
  public void testNumberCardValue() {
    assertEquals(7, new Card("7", "Hearts").value());
  }

  // 3c
  @Test
  public void testAceDefaultValue() {
    Card ace = new Card ("A", "Hearts");
    assertEquals(11, ace.value());
  }
}
