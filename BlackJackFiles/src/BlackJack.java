import java.util.Scanner;

/**
 * Main entry point for the BlackJack card game.
 * @author Super Bash Bros
 * @version 1.0
 */

public class BlackJack {
    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in, java.nio.charset.StandardCharsets.UTF_8);
		Deck deck = new Deck();
		
		while(true) {
			System.out.println("Choose whether to 's'huffle, 'd'raw, show 'a'll, or 'q'uit");
			String choice = scanner.nextLine();
			
			if(choice.equals("s")) {
				deck.shuffle();
			} else if (choice.equals("d")) {
				deck.draw();
			} else if(choice.equals("a")) {
				deck.showAll();
			} else if (choice.equals("q")) {
				scanner.close();
				System.exit(1);//ends program
			}
			
			System.out.println();
		}
    }
}
