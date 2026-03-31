import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackJack {
    public static void main(String[] args ){
        List<Card> deck = new ArrayList<>();
        String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
        String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

        for(int i = 0; i < suits.length; i++){
            for (int j = 0; j < ranks.length; j++){
                Card obj = new Card(ranks[j], suits[i]);
                deck.add(obj);
            }
        }

		while(true){
			Scanner scanner = new Scanner(System.in);
			System.out.println("Choose whether to 's'huffle, 'd'raw, show 'a'll, or 'q'uit");
			String choice = scanner.nextLine();
			if(choice.equals("s")){
				Collections.shuffle(deck);//randomizes the deck
				System.out.println("Cards shuffled");
			}
			else if (choice.equals("d")) {
				Card first = deck.remove(0);  // remove first element
				first.DisplayCard();        // display it
				deck.add(first);              // add it to the back

			}
			else if(choice.equals("a")){
				for(int i = 0; i < deck.size(); i++){
					deck.get(i).DisplayCard();//for loop to display every card in the list
				}
			}
			else if (choice.equals("q")){
				scanner.close();
				System.exit(1);//ends program
			}
			System.out.println();
		}
		
    }

    public static class Card {
        String rank;
        String suit;

        public Card(String rank, String suit){
            this.rank = rank;
            this.suit = suit;
        }

		public void DisplayCard(){
			System.out.println("Suit:" + suit + " Rank:" + rank);
		}
		
    }
}
