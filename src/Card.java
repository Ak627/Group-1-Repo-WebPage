public class Card {
    String rank;
    String suit;

    public Card (String  rank,String  suit) {
		this.rank = rank;
		this.suit = suit;
    }

	public int value() {
		if (rank.equals("A")) return 11; // deal with ace logic in the hand logic
		if (rank.equals("J") || rank.equals("Q") || rank.equals("K")) return 10;
		return Integer.parseInt(rank);
	}
	
    public String DisplayCard() {
		return rank + " of " + suit;
    }
}
