import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Unit tests for the BlackJack Teacher application.
 * @author Super Bash Bros
 * @version 1.0
 */
public class BlackJackTest {

    private Deck deck;
    private Hand hand;

    @BeforeEach
    void setUp() {
        deck = new Deck();
        hand = new Hand();
    }

    // 1a. Check if drawn card is a valid Card object (not null)
    @Test
    void testDrawReturnsNonNullCard() {
        Card drawn = deck.draw();
        assertNotNull(drawn, "draw() should return a non-null Card");
    }

    // 1b. Check no card drawn twice without reshuffling
    @Test
    void testNoDuplicateCardsDrawn() {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            String key = deck.draw().displayCard();
            assertFalse(seen.contains(key), "Duplicate card drawn: " + key);
            seen.add(key);
        }
    }

    // 2a. Check displayCard() returns correct format
    @Test
    void testDisplayCardFormat() {
        Card card = new Card("A", "Hearts");
        assertEquals("A of Hearts", card.displayCard());

        Card card2 = new Card("10", "Spades");
        assertEquals("10 of Spades", card2.displayCard());
    }

    // 3a. Check face cards (J, Q, K) return 10
    @Test
    void testFaceCardValues() {
        assertEquals(10, new Card("J", "Hearts").value());
        assertEquals(10, new Card("Q", "Clubs").value());
        assertEquals(10, new Card("K", "Diamonds").value());
    }

    // 3b. Check number cards return their face value
    @Test
    void testNumberCardValues() {
        for (int i = 2; i <= 10; i++) {
            Card c = new Card(String.valueOf(i), "Spades");
            assertEquals(i, c.value(), "Expected " + i + " for rank " + i);
        }
    }

    // 3c. Check Ace returns 11 by default
    @Test
    void testAceDefaultValue() {
        assertEquals(11, new Card("A", "Hearts").value());
    }

    // 4a. Store deck order before shuffle, assert order is different after
    @Test
    void testShuffleChangesOrder() {
        Deck unshuffled = new Deck();
        Deck shuffled = new Deck();
        shuffled.shuffle();

        boolean different = false;
        for (int i = 0; i < 52; i++) {
            String a = unshuffled.draw().displayCard();
            String b = shuffled.draw().displayCard();
            if (!a.equals(b)) {
                different = true;
                break;
            }
        }
        assertTrue(different, "Shuffled deck should differ from unshuffled deck");
    }

    // 4b. Confirm deck still has 52 cards after shuffle
    @Test
    void testDeckSizeAfterShuffle() {
        deck.shuffle();
        assertEquals(52, deck.getSize());
    }

    // 5a. Draw all 52 cards, confirm no duplicates
    @Test
    void testNoDuplicatesInFullDeck() {
        deck.shuffle();
        Set<String> cards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            String card = deck.draw().displayCard();
            assertFalse(cards.contains(card), "Duplicate found after shuffle: " + card);
            cards.add(card);
        }
        assertEquals(52, cards.size());
    }

    // 6a. Assert new deck has exactly 52 cards
    @Test
    void testNewDeckHas52Cards() {
        assertEquals(52, deck.getSize());
    }

    // 6b. Assert deck size decreases by 1 after draw
    @Test
    void testDeckSizeDecreaseOnDraw() {
        int before = deck.getSize();
        deck.draw();
        assertEquals(before - 1, deck.getSize());
    }

    // 7a. Assert deck has 52 cards on initialization
    @Test
    void testDeckInitializationSize() {
        assertEquals(52, new Deck().getSize());
    }

    // 8a. Call reset() and assert deck returns to 52 cards
    @Test
    void testDeckResetRestores52Cards() {
        deck.draw();
        deck.draw();
        deck.draw();
        deck.reset();
        assertEquals(52, deck.getSize());
    }

    // 9a. Hand with Ace + 10 = 21
    @Test
    void testAcePlusTenEquals21() {
        hand.addCard(new Card("A", "Hearts"));
        hand.addCard(new Card("10", "Spades"));
        assertEquals(21, hand.getValue());
    }

    // 9b. Hand with Ace + 5 + 8 = 14 (Ace has to drop to 1)
    @Test
    void testAceDropsToAvoidBust() {
        hand.addCard(new Card("A", "Hearts"));
        hand.addCard(new Card("5", "Spades"));
        hand.addCard(new Card("8", "Clubs"));
        assertEquals(14, hand.getValue());
    }

    // 9c. Two Aces = 12 (one stays 11, other drops to 1)
    @Test
    void testTwoAces() {
        hand.addCard(new Card("A", "Hearts"));
        hand.addCard(new Card("A", "Spades"));
        assertEquals(12, hand.getValue());
    }

    // 9d. Ace + K + 5 = 16 (Ace drops to 1)
    @Test
    void testAceKingFiveDrops() {
        hand.addCard(new Card("A", "Hearts"));
        hand.addCard(new Card("K", "Spades"));
        hand.addCard(new Card("5", "Clubs"));
        assertEquals(16, hand.getValue());
    }

    // 10a. Hand value > 21 returns true for isBust()
    @Test
    void testIsBustOverTwentyOne() {
        hand.addCard(new Card("10", "Hearts"));
        hand.addCard(new Card("10", "Spades"));
        hand.addCard(new Card("5", "Clubs"));
        assertTrue(hand.isBust());
    }

    // 10b. Hand value <= 21 returns false for isBust()
    @Test
    void testIsNotBustAtOrUnder21() {
        hand.addCard(new Card("10", "Hearts"));
        hand.addCard(new Card("9", "Spades"));
        assertFalse(hand.isBust());

        Hand exactHand = new Hand();
        exactHand.addCard(new Card("10", "Clubs"));
        exactHand.addCard(new Card("A", "Diamonds"));
        assertFalse(exactHand.isBust(), "Hand worth exactly 21 should not be a bust");
    }

    // 10c. Ace saves hand from bust (10 + 10 + A = 21, not bust)
    @Test
    void testAceSavesFromBust() {
        hand.addCard(new Card("10", "Hearts"));
        hand.addCard(new Card("10", "Spades"));
        hand.addCard(new Card("A", "Clubs"));
        assertEquals(21, hand.getValue());
        assertFalse(hand.isBust());
    }

    // 11a. Two card hand worth 21 returns true for isBlackjack()
    @Test
    void testIsBlackjackTwoCardNatural() {
        hand.addCard(new Card("A", "Hearts"));
        hand.addCard(new Card("K", "Spades"));
        assertTrue(hand.isBlackjack());
    }

    // 11b. Three card hand worth 21 returns false for isBlackjack()
    @Test
    void testIsNotBlackjackThreeCardTwentyOne() {
        hand.addCard(new Card("7", "Hearts"));
        hand.addCard(new Card("7", "Spades"));
        hand.addCard(new Card("7", "Clubs"));
        assertEquals(21, hand.getValue());
        assertFalse(hand.isBlackjack());
    }

    // 11c. Two card hand not worth 21 is not blackjack
    @Test
    void testTwoCardHandNotBlackjack() {
        hand.addCard(new Card("10", "Hearts"));
        hand.addCard(new Card("9", "Spades"));
        assertFalse(hand.isBlackjack());
    }

    // 12a. After dealing check player has 2 cards, dealer has 2 cards and deck has 48
    @Test
    void testInitialDealCardCounts() {
        Hand player = new Hand();
        Hand dealer = new Hand();
        deck.shuffle();

        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());

        assertEquals(2, player.getSize(), "Player should have 2 cards after deal");
        assertEquals(2, dealer.getSize(), "Dealer should have 2 cards after deal");
        assertEquals(48, deck.getSize(), "Deck should have 48 cards after dealing 4");
    }

    // 13a. After hit, player hand size increases by 1
    @Test
    void testHitIncreasesHandSize() {
        hand.addCard(new Card("7", "Hearts"));
        hand.addCard(new Card("8", "Spades"));
        int before = hand.getSize();
        hand.addCard(deck.draw());
        assertEquals(before + 1, hand.getSize());
    }

    // 13b. Player can hit multiple times and hand grows correctly
    @Test
    void testMultipleHitsGrowHand() {
        hand.addCard(new Card("2", "Hearts"));
        hand.addCard(new Card("3", "Spades"));
        hand.addCard(deck.draw());
        hand.addCard(deck.draw());
        hand.addCard(deck.draw());
        assertEquals(5, hand.getSize());
    }

    // 14a. Stand returns without adding a card
    @Test
    void testStayDoesNotChangePlayerHand() {
        hand.addCard(new Card("10", "Hearts"));
        hand.addCard(new Card("8", "Spades"));
        int sizeBefore = hand.getSize();
        int valueBefore = hand.getValue();
        // Stay = player stops drawing; hand unchanged
        assertEquals(sizeBefore, hand.getSize());
        assertEquals(valueBefore, hand.getValue());
    }

    // 15a. Dealer draws until hand >= 17 then stops
    @Test
    void testDealerDrawsUntil17() {
        Hand dealer = new Hand();
        deck.shuffle();
        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());

        int deckSizeBefore = deck.getSize();
        while (dealer.getValue() < 17) {
            dealer.addCard(deck.draw());
        }
        assertTrue(dealer.getValue() >= 17, "Dealer should stop at or above 17");
        assertEquals(deckSizeBefore - (dealer.getSize() - 2), deck.getSize());
    }

    // 15b. Dealer already at 17 on deal draws no extra cards
    @Test
    void testDealerStaysAt17() {
        Hand dealer = new Hand();
        dealer.addCard(new Card("10", "Hearts"));
        dealer.addCard(new Card("7", "Spades"));
        int sizeBefore = dealer.getSize();
        while (dealer.getValue() < 17) {
            dealer.addCard(deck.draw());
        }
        assertEquals(sizeBefore, dealer.getSize(), "Dealer at 17 should not draw");
    }

    // 15c. Dealer soft 17 (Ace + 6) stays at 17
    @Test
    void testDealerSoftSeventeen() {
        Hand dealer = new Hand();
        dealer.addCard(new Card("A", "Hearts"));
        dealer.addCard(new Card("6", "Spades"));
        assertEquals(17, dealer.getValue());
        assertFalse(dealer.isBust());
    }

    // 16a. Player 21 vs Dealer 18 : Player wins
    @Test
    void testPlayerWinsHigherValue() {
        Hand player = new Hand();
        Hand dealer = new Hand();
        player.addCard(new Card("A", "Hearts"));
        player.addCard(new Card("K", "Spades")); // 21
        dealer.addCard(new Card("10", "Hearts"));
        dealer.addCard(new Card("8", "Spades")); // 18

        assertFalse(player.isBust());
        assertFalse(dealer.isBust());
        assertTrue(player.getValue() > dealer.getValue());
    }

    // 16b. Player 18 vs Dealer 21 : Dealer wins
    @Test
    void testDealerWinsHigherValue() {
        Hand player = new Hand();
        Hand dealer = new Hand();
        player.addCard(new Card("10", "Hearts"));
        player.addCard(new Card("8", "Spades")); // 18
        dealer.addCard(new Card("A", "Hearts"));
        dealer.addCard(new Card("K", "Spades")); // 21

        assertFalse(player.isBust());
        assertFalse(dealer.isBust());
        assertTrue(dealer.getValue() > player.getValue());
    }

    // 16c. Player 20 vs Dealer 20 : Tie
    @Test
    void testTie() {
        Hand player = new Hand();
        Hand dealer = new Hand();
        player.addCard(new Card("10", "Hearts"));
        player.addCard(new Card("10", "Spades")); // 20
        dealer.addCard(new Card("K", "Hearts"));
        dealer.addCard(new Card("Q", "Spades")); // 20

        assertEquals(player.getValue(), dealer.getValue());
    }

    // 16d. Player bust : Dealer wins
    @Test
    void testPlayerBustDealerWins() {
        Hand player = new Hand();
        player.addCard(new Card("10", "Hearts"));
        player.addCard(new Card("10", "Spades"));
        player.addCard(new Card("5", "Clubs")); // 25
        assertTrue(player.isBust(), "Player should be bust");
    }

    // 16e. Dealer bust : Player wins
    @Test
    void testDealerBustPlayerWins() {
        Hand dealer = new Hand();
        dealer.addCard(new Card("10", "Hearts"));
        dealer.addCard(new Card("10", "Spades"));
        dealer.addCard(new Card("5", "Clubs")); // 25
        assertTrue(dealer.isBust(), "Dealer should be bust");
    }

    // 16f. Both players bust : dealer bust check still triggers
    @Test
    void testBothBustDealerBustTakesPriority() {
        Hand player = new Hand();
        Hand dealer = new Hand();
        player.addCard(new Card("10", "Hearts"));
        player.addCard(new Card("10", "Spades"));
        player.addCard(new Card("5", "Clubs")); // 25

        dealer.addCard(new Card("10", "Hearts"));
        dealer.addCard(new Card("10", "Diamonds"));
        dealer.addCard(new Card("6", "Clubs")); // 26

        assertTrue(player.isBust());
        assertTrue(dealer.isBust());
    }

    // 17a. Empty hand getValue() returns 0
    @Test
    void testEmptyHandGetValue() {
        assertEquals(0, hand.getValue(), "Empty hand should have value 0");
    }

    // 17b. Empty hand isBust() returns false
    @Test
    void testEmptyHandIsNotBust() {
        assertFalse(hand.isBust(), "Empty hand should not be a bust");
    }

    // 17c. Empty hand isBlackjack() returns false
    @Test
    void testEmptyHandIsNotBlackjack() {
        assertFalse(hand.isBlackjack(), "Empty hand should not be blackjack");
    }

    // 17d. Empty hand showHand() returns empty string
    @Test
    void testEmptyHandShowHand() {
        assertEquals("", hand.showHand(), "Empty hand should display as empty string");
    }

    // 17e. Empty hand getSize() returns 0
    @Test
    void testEmptyHandGetSize() {
        assertEquals(0, hand.getSize(), "Empty hand should have size 0");
    }

    // 18a. Single card hand returns correct value
    @Test
    void testSingleCardHandValue() {
        hand.addCard(new Card("7", "Hearts"));
        assertEquals(7, hand.getValue());
    }

    // 18b. Single card hand is not a bust
    @Test
    void testSingleCardHandNotBust() {
        hand.addCard(new Card("K", "Spades"));
        assertFalse(hand.isBust());
    }

    // 18c. Single card hand is not blackjack (needs exactly 2 cards)
    @Test
    void testSingleCardHandNotBlackjack() {
        hand.addCard(new Card("A", "Hearts"));
        assertFalse(hand.isBlackjack(), "Single Ace alone is not blackjack");
    }

    // 19a. showHand() with one card has no leading comma
    @Test
    void testShowHandSingleCard() {
        hand.addCard(new Card("Q", "Diamonds"));
        assertEquals("Q of Diamonds", hand.showHand());
    }

    // 19b. showHand() with multiple cards uses comma separator
    @Test
    void testShowHandMultipleCards() {
        hand.addCard(new Card("A", "Hearts"));
        hand.addCard(new Card("K", "Spades"));
        assertEquals("A of Hearts, K of Spades", hand.showHand());
    }

    // 20a. Three aces = 13 (two drop from 11 to 1)
    @Test
    void testThreeAces() {
        hand.addCard(new Card("A", "Hearts"));
        hand.addCard(new Card("A", "Spades"));
        hand.addCard(new Card("A", "Clubs"));
        assertEquals(13, hand.getValue(), "A+A+A should equal 13");
        assertFalse(hand.isBust());
    }

    // 21a. Drawing from an empty deck throws IndexOutOfBoundsException
    @Test
    void testDrawFromEmptyDeckThrows() {
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertThrows(IndexOutOfBoundsException.class, () -> deck.draw(),
                "Drawing from empty deck should throw IndexOutOfBoundsException");
    }

    // 22a. After reset, all 52 cards are unique
    @Test
    void testDeckResetHas52UniqueCards() {
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        deck.reset();
        assertEquals(52, deck.getSize());
        java.util.Set<String> unique = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            unique.add(deck.draw().displayCard());
        }
        assertEquals(52, unique.size(), "All 52 cards after reset should be unique");
    }

    // 23a. resetHand allows hand to be reused correctly
    @Test
    void testResetHandAndReuse() {
        hand.addCard(new Card("K", "Hearts"));
        hand.addCard(new Card("Q", "Spades"));
        hand.resetHand();
        hand.addCard(new Card("5", "Clubs"));
        assertEquals(1, hand.getSize());
        assertEquals(5, hand.getValue());
    }

    // getCards() returns the correct cards
    @Test
    void testGetCardsSize() {
        hand.addCard(new Card("A", "Hearts"));
        hand.addCard(new Card("5", "Spades"));
        assertEquals(2, hand.getCards().size());
    }

    @Test
    void testGetCardsContent() {
        hand.addCard(new Card("7", "Hearts"));
        hand.addCard(new Card("K", "Clubs"));
        assertEquals("7 of Hearts", hand.getCards().get(0).displayCard());
        assertEquals("K of Clubs", hand.getCards().get(1).displayCard());
    }

    // resetHand() clears all cards from the hand
    @Test
    void testResetHandClearsCards() {
        hand.addCard(new Card("A", "Hearts"));
        hand.addCard(new Card("K", "Spades"));
        hand.resetHand();
        assertEquals(0, hand.getSize());
        assertEquals(0, hand.getValue());
    }
}
