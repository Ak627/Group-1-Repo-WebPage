public class Card{
    String rank;
    String suit;

    public Card (String  rank,String  suit){
	this.rank = rank;
	this.suit = suit;
    }
    public String value(){
	return rank + " of " + suit;
        }
}
