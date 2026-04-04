import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player's hand of cards.
 * @author Super Bash Bros
 * @version 1.0
 */

public class Hand {
    List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    public int getValue() {
        int total = 0;
        int aces = 0;

        for (Card c : cards) {
            total += c.value();
            if (c.rank.equals("A")) aces++;
        }

        // drop each ace from 11 to 1 if we're busting
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }

    public boolean isBust() {
        return getValue() > 21;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }
}
