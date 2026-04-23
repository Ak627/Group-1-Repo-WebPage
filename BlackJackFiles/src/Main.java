import javax.swing.*;

/**
 * Main class with main method invoked on app start.
 * @version 1.0.0
 * @author Super Bash Bros
 */
public class Main{
    /** Private constructor to prevent instantiation of entry point class. */
    private Main() { }

    /**
     * Invoked on start.
     * @param args ignored
     */
    public static void main(String[] args) {
        Deck deck = new Deck();
        Hand hand = new Hand();
        JFrame frame = new JFrame("BlackJack Teacher");
        frame.setResizable(false);//keeps the window the same size no matter what
        JLabel label = new JLabel("Shuffle deck or draw cards to learn blackjack", SwingConstants.CENTER);
        JLabel cardsLabel = new JLabel();//test label to check if cards can update on screen
        JLabel handLabel = new JLabel();
        JLabel handUi = new JLabel("Your Hand");
        cardsLabel.setBounds(200, 100, 100, 100);
        handLabel.setBounds(150, 500, 250, 100);
        handUi.setBounds(175, 450, 100, 100);

        


        JButton shuffleB = new JButton("Shuffle");
        shuffleB.setBounds(SwingConstants.LEFT, 350, 220, 50);
        frame.add(shuffleB);
        shuffleB.addActionListener(e -> {
            deck.shuffle();
            hand.resetHand();
            cardsLabel.setText("Deck shuffled");
            hand.addCard(deck.draw());
            hand.addCard(deck.draw());
            handLabel.setText(hand.showHand());
            
        });

        JButton drawB = new JButton("Draw");
        drawB.setBounds(SwingConstants.RIGHT+220,350, 220, 50);
        frame.add(drawB);
        drawB.addActionListener(e -> {
            cardsLabel.setText(deck.draw().displayCard());
        });

        JButton resetB = new JButton("Reset");
        resetB.setBounds(SwingConstants.RIGHT+165,400, 110, 25);
        frame.add(resetB);
        resetB.addActionListener(e -> {
            cardsLabel.setText("Deck Reset");
            deck.reset();
        });


        frame.add(cardsLabel);
        frame.add(handLabel);
        frame.add(handUi);
        frame.add(label);
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
