/**
* Represents a single playing card with a rank a suit.
* @author Super Bash Bros
* @version 1.0
*/

public class Card {
    String rank;
    String suit;

    public Card (String  rank,String  suit) {
		this.rank = rank;
		this.suit = suit;
    }

	public int value() {
		if (rank.equals("A")) return 11;
		if (rank.equals("J") || rank.equals("Q") || rank.equals("K")) return 10;
		return Integer.parseInt(rank);
	}
	
    public String displayCard() {
		return rank + " of " + suit;
    }
}
