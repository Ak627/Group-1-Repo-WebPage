import javax.swing.*;
import java.awt.*;

/**
 * Main class with main method invoked on app start.
 * @version 1.1.0
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
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();
        boolean[] gameOver = {true};

        JFrame frame = new JFrame("BlackJack Teacher");
        frame.setResizable(false);//keeps the window the same size no matter what
        frame.setLayout(null);

        JLabel label = new JLabel("Shuffle deck or draw cards to learn blackjack", SwingConstants.CENTER);
        label.setBounds(0, 310, 500, 30);

        JLabel cardsLabel = new JLabel();//shows dealer's visible card(s) at the top
        cardsLabel.setBounds(100, 100, 300, 25);

        JLabel handLabel = new JLabel();
        handLabel.setBounds(50, 480, 400, 60);

        JLabel handUi = new JLabel("Your Hand", SwingConstants.CENTER);
        handUi.setBounds(175, 450, 150, 25);

        // Declare all buttons before wiring listeners so lambdas can reference them
        JButton shuffleB = new JButton("Shuffle");
        shuffleB.setBounds(SwingConstants.LEFT, 350, 220, 50);

        JButton drawB = new JButton("Draw");
        drawB.setBounds(SwingConstants.RIGHT+220, 350, 220, 50);
        drawB.setEnabled(false);

        JButton resetB = new JButton("Reset");
        resetB.setBounds(SwingConstants.RIGHT+165, 400, 110, 25);
        resetB.setEnabled(false);

        // Stay button: added next to Reset; lets dealer play out and resolves the round
        JButton stayB = new JButton("Stay");
        stayB.setBounds(SwingConstants.RIGHT+285, 400, 110, 25);
        stayB.setEnabled(false);

        shuffleB.addActionListener(e -> {
            deck.shuffle();
            playerHand.resetHand();
            dealerHand.resetHand();
            gameOver[0] = false;

            playerHand.addCard(deck.draw());
            dealerHand.addCard(deck.draw());
            playerHand.addCard(deck.draw());
            dealerHand.addCard(deck.draw());

            refreshDisplay(playerHand, dealerHand, cardsLabel, handLabel);
            label.setText("Hit to draw a card, or Stay to end your turn.");
            drawB.setEnabled(true);
            stayB.setEnabled(true);
            resetB.setEnabled(false);

            if (playerHand.isBlackjack()) {
                endRound(playerHand, dealerHand, label, cardsLabel, drawB, stayB, resetB, gameOver);
            }
        });

        drawB.addActionListener(e -> {
            if (gameOver[0]) return;

            playerHand.addCard(deck.draw());
            dealerHand.addCard(deck.draw());
            refreshDisplay(playerHand, dealerHand, cardsLabel, handLabel);

            if (playerHand.isBust()) {
                cardsLabel.setText("Dealer: " + dealerHand.showHand());
                label.setText("Bust! You went over 21. Dealer wins. Press Shuffle to play again.");
                drawB.setEnabled(false);
                stayB.setEnabled(false);
                resetB.setEnabled(true);
                gameOver[0] = true;
            }
        });

        resetB.addActionListener(e -> {
            if (!gameOver[0]) return;
            playerHand.resetHand();
            dealerHand.resetHand();
            cardsLabel.setText("");
            handLabel.setText("");
            label.setText("Shuffle deck or draw cards to learn blackjack");
            resetB.setEnabled(false);
        });

        stayB.addActionListener(e -> {
            if (gameOver[0]) return;
            while (dealerHand.getValue() < 17) {
                dealerHand.addCard(deck.draw());
            }
            endRound(playerHand, dealerHand, label, cardsLabel, drawB, stayB, resetB, gameOver);
        });

        frame.add(shuffleB);
        frame.add(drawB);
        frame.add(resetB);
        frame.add(stayB);
        frame.add(cardsLabel);
        frame.add(handLabel);
        frame.add(handUi);
        frame.add(label);
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Updates cardsLabel with dealer's visible cards and handLabel with
     * player's cards, player total, and dealer's visible total (down card excluded).
     */
    private static void refreshDisplay(Hand playerHand, Hand dealerHand,
                                        JLabel cardsLabel, JLabel handLabel) {
        StringBuilder dealerStr = new StringBuilder("Dealer: ");
        dealerStr.append(dealerHand.getCards().get(0).displayCard()).append(", [Hidden]");
        int dealerVisible = dealerHand.getCards().get(0).value();
        for (int i = 2; i < dealerHand.getSize(); i++) {
            dealerStr.append(", ").append(dealerHand.getCards().get(i).displayCard());
            dealerVisible += dealerHand.getCards().get(i).value();
        }
        cardsLabel.setText(dealerStr.toString());

        handLabel.setText("<html><div style='text-align:center'>"
                + playerHand.showHand()
                + "<br>Your total: " + playerHand.getValue()
                + " &nbsp;|&nbsp; Dealer showing: " + dealerVisible
                + "</div></html>");
    }

    /**
     * Reveals the dealer's full hand, determines the winner, and updates the UI.
     * Enables Reset and disables Draw/Stay when the round ends.
     */
    private static void endRound(Hand playerHand, Hand dealerHand,
                                  JLabel label, JLabel cardsLabel,
                                  JButton drawB, JButton stayB, JButton resetB,
                                  boolean[] gameOver) {
        cardsLabel.setText("Dealer: " + dealerHand.showHand());

        int playerVal = playerHand.getValue();
        int dealerVal = dealerHand.getValue();
        String result;
        if (dealerHand.isBust()) {
            result = "Dealer busted (" + dealerVal + ")! You win with " + playerVal + ".";
        } else if (playerVal > dealerVal) {
            result = "You win! " + playerVal + " beats dealer's " + dealerVal + ".";
        } else if (playerVal < dealerVal) {
            result = "Dealer wins. " + dealerVal + " beats your " + playerVal + ".";
        } else {
            result = "Push! Both have " + playerVal + ". It's a tie.";
        }

        label.setText(result + " Press Shuffle to play again.");
        drawB.setEnabled(false);
        stayB.setEnabled(false);
        resetB.setEnabled(true);
        gameOver[0] = true;
    }
}
