public class Deck{
    List<Card> deck = new ArrayList<>();
    String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
    String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    for(int i = 0; i < suits.length; i++){
	for(int j = 0; j< ranks.length; j++){
	    Card newCard = new Card(rank, suit);
	    deck.add(newCard);
	}
    }
}
	
