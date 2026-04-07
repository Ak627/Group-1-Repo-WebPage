import javax.swing.*;

/**
 * Main class with main method invoked on app start.
 * @version 1.0.0
 * @author Dr. Jody Paul
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
        JFrame frame = new JFrame("BlackJack Teacher");
        JLabel label = new JLabel("Shuffle deck or draw cards to learn blackjack", SwingConstants.CENTER);
        JButton shuffleB = new JButton("Shuffle");
        shuffleB.setBounds(SwingConstants.LEFT, 350, 220, 50);
        frame.add(shuffleB);
        shuffleB.addActionListener(e -> deck.shuffle());

        JButton drawB = new JButton("Draw");
        drawB.setBounds(SwingConstants.RIGHT+220,350, 220, 50);
        frame.add(drawB);
        drawB.addActionListener(e -> deck.draw());

        frame.add(label);
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
