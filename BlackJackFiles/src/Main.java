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
        JFrame frame = new JFrame("BlackJack Teacher");
        frame.setResizable(false);//keeps the window the same size no matter what
        JLabel label = new JLabel("Shuffle deck or draw cards to learn blackjack", SwingConstants.CENTER);
        JLabel labeltest = new JLabel();//test label to check if cards can update on screen
        labeltest.setBounds(200, 100, 100, 100);



        JButton shuffleB = new JButton("Shuffle");
        shuffleB.setBounds(SwingConstants.LEFT, 350, 220, 50);
        frame.add(shuffleB);
        shuffleB.addActionListener(e -> {
            labeltest.setText("Deck shuffled");
            deck.shuffle();
        });

        JButton drawB = new JButton("Draw");
        drawB.setBounds(SwingConstants.RIGHT+220,350, 220, 50);
        frame.add(drawB);
        drawB.addActionListener(e -> {
            labeltest.setText(deck.draw().displayCard());
        });

        JButton resetB = new JButton("Reset");
        resetB.setBounds(SwingConstants.RIGHT+165,400, 110, 25);
        frame.add(resetB);
        resetB.addActionListener(e -> {
            labeltest.setText("Deck Reset");
            deck.reset();
        });


        frame.add(labeltest);
        frame.add(label);
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
