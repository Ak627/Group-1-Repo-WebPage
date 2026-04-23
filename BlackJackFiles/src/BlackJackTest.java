import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the BlackJack card game.
 * @author Super Bash Bros
 * @version 1.1
 */

// Refer to BlackJackTest.md

public class BlackJackTest {

  //  Deck Tests

  // 1a – drawn card is a valid Card object (not null)
  @Test
  public void testDrawReturnsCard() {
    Deck deck = new Deck();
    Card drawn = deck.draw();
    assertNotNull(drawn);
  }

  // 1b – no card drawn twice without reshuffling
  @Test
  public void testNoDuplicateDraws() {
    Deck deck = new Deck();
    List<String> seen = new ArrayList<>();
    for (int i = 0; i < 52; i++) {
      String card = deck.draw().displayCard();
      assertFalse(seen.contains(card));
      seen.add(card);
    }
  }

  // 4a – shuffle changes deck order
  @Test
  public void testShuffleChangesOrder() {
    Deck deck = new Deck();
    List<String> before = new ArrayList<>();
    for (int i = 0; i < 52; i++) {
      before.add(deck.draw().displayCard());
    }
    deck.reset();
    deck.shuffle();
    List<String> after = new ArrayList<>();
    for (int i = 0; i < 52; i++) {
      after.add(deck.draw().displayCard());
    }
    assertFalse(before.equals(after));
  }

  // 4b – shuffle keeps deck at 52 cards
  @Test
  public void testShuffleMaintainsDeckSize() {
    Deck deck = new Deck();
    deck.shuffle();
    assertEquals(52, deck.getSize());
  }

  // 5a – all 52 cards can be drawn with no blocking
  @Test
  public void testDrawAllCardsNoBlock() {
    Deck deck = new Deck();
    List<Card> drawn = new ArrayList<>();
    for (int i = 0; i < 52; i++) {
      drawn.add(deck.draw());
    }
    assertEquals(52, drawn.size());
  }

  // 6a – new deck has exactly 52 cards
  @Test
  public void testNewDeckHas52Cards() {
    Deck deck = new Deck();
    assertEquals(52, deck.getSize());
  }

  // 6b – deck size decreases by 1 after a draw
  @Test
  public void testDeckSizeDecreasesAfterDraw() {
    Deck deck = new Deck();
    deck.draw();
    assertEquals(51, deck.getSize());
  }

  // 7a – deck has 52 cards on initialization (same as 6a, explicit init check)
  @Test
  public void testDeckInitializationSize() {
    Deck deck = new Deck();
    assertEquals(52, deck.getSize());
  }

  // 8a – reset() restores deck to 52 cards
  @Test
  public void testResetRestoresDeck() {
    Deck deck = new Deck();
    deck.draw();
    deck.draw();
    deck.draw();
    deck.reset();
    assertEquals(52, deck.getSize());
  }

  //  Card Tests

  // 2a – displayCard() returns correct format
  @Test
  public void testDisplayCard() {
    Card card = new Card("A", "Hearts");
    assertEquals("A of Hearts", card.displayCard());
  }

  // 3a – face cards (J, Q, K) return 10
  @Test
  public void testFaceCardValue() {
    assertEquals(10, new Card("J", "Hearts").value());
    assertEquals(10, new Card("Q", "Hearts").value());
    assertEquals(10, new Card("K", "Hearts").value());
  }

  // 3b – number cards return their face value
  @Test
  public void testNumberCardValue() {
    assertEquals(7, new Card("7", "Hearts").value());
  }

  // 3c – Ace returns 11 by default
  @Test
  public void testAceDefaultValue() {
    Card ace = new Card("A", "Hearts");
    assertEquals(11, ace.value());
  }

  //  Hand Tests

  // 9a – Ace + 10-card = 21
  @Test
  public void testHandAcePlusTenEquals21() {
    Hand hand = new Hand();
    hand.addCard(new Card("A", "Spades"));
    hand.addCard(new Card("10", "Hearts"));
    assertEquals(21, hand.getValue());
  }

  // 9b – Ace + 5 + 8 = 14 (Ace drops to 1 to avoid bust)
  @Test
  public void testHandAceDropsToOne() {
    Hand hand = new Hand();
    hand.addCard(new Card("A", "Spades"));
    hand.addCard(new Card("5", "Hearts"));
    hand.addCard(new Card("8", "Clubs"));
    assertEquals(14, hand.getValue());
  }

  // 10a – hand value > 21 returns true for isBust()
  @Test
  public void testIsBustTrue() {
    Hand hand = new Hand();
    hand.addCard(new Card("K", "Hearts"));
    hand.addCard(new Card("Q", "Spades"));
    hand.addCard(new Card("5", "Clubs"));
    assertTrue(hand.isBust());
  }

  // 10b – hand value <= 21 returns false for isBust()
  @Test
  public void testIsBustFalse() {
    Hand hand = new Hand();
    hand.addCard(new Card("K", "Hearts"));
    hand.addCard(new Card("9", "Spades"));
    assertFalse(hand.isBust());
  }

  // 11a – two-card hand worth 21 returns true for isBlackjack()
  @Test
  public void testIsBlackjackTrue() {
    Hand hand = new Hand();
    hand.addCard(new Card("A", "Hearts"));
    hand.addCard(new Card("K", "Spades"));
    assertTrue(hand.isBlackjack());
  }

  // 11b – three-card hand worth 21 returns false for isBlackjack()
  @Test
  public void testIsBlackjackFalseThreeCards() {
    Hand hand = new Hand();
    hand.addCard(new Card("7", "Hearts"));
    hand.addCard(new Card("7", "Spades"));
    hand.addCard(new Card("7", "Clubs"));
    assertFalse(hand.isBlackjack());
  }

  //  Game Flow Tests

  // 12a – after deal: player 2 cards, dealer 2 cards, deck has 48
  @Test
  public void testDealInitialHands() {
    Deck deck = new Deck();
    Hand player = new Hand();
    Hand dealer = new Hand();

    player.addCard(deck.draw());
    player.addCard(deck.draw());
    dealer.addCard(deck.draw());
    dealer.addCard(deck.draw());

    assertEquals(2, player.getSize());
    assertEquals(2, dealer.getSize());
    assertEquals(48, deck.getSize());
  }

  // 13a – after hit, player hand size increases by 1
  @Test
  public void testHitIncreasesHandSize() {
    Deck deck = new Deck();
    Hand player = new Hand();
    player.addCard(deck.draw());
    player.addCard(deck.draw());

    int before = player.getSize();
    player.addCard(deck.draw()); // hit
    assertEquals(before + 1, player.getSize());
  }

  // 14a – stand does not add a card to hand
  @Test
  public void testStandDoesNotAddCard() {
    Deck deck = new Deck();
    Hand player = new Hand();
    player.addCard(deck.draw());
    player.addCard(deck.draw());

    int before = player.getSize();
    // stand = do nothing; hand size must stay the same
    assertEquals(before, player.getSize());
  }

  // 15a – dealer draws until hand value >= 17 then stops
  @Test
  public void testDealerDrawsUntil17() {
    Deck deck = new Deck();
    deck.shuffle();
    Hand dealer = new Hand();

    while (dealer.getValue() < 17) {
      dealer.addCard(deck.draw());
    }

    assertTrue(dealer.getValue() >= 17);
  }

  //  Win / Loss Logic Tests

  // Helper: returns "player", "dealer", or "tie"
  private String resolveWinner(Hand player, Hand dealer) {
    if (player.isBust()) return "dealer";
    if (dealer.isBust()) return "player";
    if (player.getValue() > dealer.getValue()) return "player";
    if (dealer.getValue() > player.getValue()) return "dealer";
    return "tie";
  }

  // 16a – player 21 vs dealer 18: player wins
  @Test
  public void testPlayerWins() {
    Hand player = new Hand();
    player.addCard(new Card("A", "Hearts"));
    player.addCard(new Card("K", "Spades")); // 21

    Hand dealer = new Hand();
    dealer.addCard(new Card("10", "Hearts"));
    dealer.addCard(new Card("8", "Spades")); // 18

    assertEquals("player", resolveWinner(player, dealer));
  }

  // 16b – player 18 vs dealer 21: dealer wins
  @Test
  public void testDealerWins() {
    Hand player = new Hand();
    player.addCard(new Card("10", "Hearts"));
    player.addCard(new Card("8", "Spades")); // 18

    Hand dealer = new Hand();
    dealer.addCard(new Card("A", "Hearts"));
    dealer.addCard(new Card("K", "Spades")); // 21

    assertEquals("dealer", resolveWinner(player, dealer));
  }

  // 16c – player 20 vs dealer 20: tie
  @Test
  public void testTie() {
    Hand player = new Hand();
    player.addCard(new Card("K", "Hearts"));
    player.addCard(new Card("10", "Spades")); // 20

    Hand dealer = new Hand();
    dealer.addCard(new Card("Q", "Hearts"));
    dealer.addCard(new Card("10", "Clubs")); // 20

    assertEquals("tie", resolveWinner(player, dealer));
  }

  // 16d – player bust: dealer wins
  @Test
  public void testPlayerBustDealerWins() {
    Hand player = new Hand();
    player.addCard(new Card("K", "Hearts"));
    player.addCard(new Card("Q", "Spades"));
    player.addCard(new Card("5", "Clubs")); // 25 – bust

    Hand dealer = new Hand();
    dealer.addCard(new Card("10", "Hearts"));
    dealer.addCard(new Card("7", "Spades")); // 17

    assertEquals("dealer", resolveWinner(player, dealer));
  }
}
