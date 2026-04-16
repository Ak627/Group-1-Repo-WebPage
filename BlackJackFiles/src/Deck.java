import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deck of 52 playing cards.
 * @author Super Bash Bros
 * @version 1.0
 */

public class Deck {
	private List<Card> deck = new ArrayList<>();


    public Deck() {
	String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
	String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		for(int i = 0; i < suits.length; i++){
			for(int j = 0; j< ranks.length; j++){
				Card newCard = new Card(ranks[j],suits[i]);
				deck.add(newCard);
			}
		}
    }
    // shuffles deck
    public void shuffle() {
        Collections.shuffle(deck);
        System.out.println("Cards Shuffled");
    }
    //draws card from top of deck and returns it
    // todo: add functionality to add to discard, to indicate when decks needs to be reshuffled
    public String draw() {
        Card first = deck.remove(0);
        System.out.println(first.displayCard());
        return first;
    }
	
    public void showAll() {
    	for (Card c: deck) {
    	    System.out.println(c.displayCard());
    	}
    }
	public void reset() {
        System.out.println("Deck reset");
        deck.clear();

        String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
        String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                deck.add(new Card(ranks[j], suits[i]));
            }
        }
    }
    public int getSize() {
		return deck.size();
	}
}
