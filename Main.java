import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    // Declare variables
    static JFrame frame;
    static JButton[] buttons = new JButton[9];
    static JPanel panel;
    static boolean player1Turn = true; // true player 1 turn

    public static void main(String[] args) {

        // Create the frame
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(500, 100, 400, 400);
        frame.setLayout(null);

        // Create panel
        panel = new JPanel();
        panel.setBounds(50, 50, 300, 300);
        panel.setLayout(new GridLayout(3, 3, 5, 5));

        // font
        Font myFont = new Font("Arial", Font.BOLD, 60);

        //buttons on panel
        for (int i = 0; i < 9; i++) { // for loop starts

            buttons[i] = new JButton();
            buttons[i].setFont(myFont);
            buttons[i].setFocusable(false);

            /////////////////////////////// event handling ///////////////////////////////

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton buttonClicked = (JButton) e.getSource();

                    if (buttonClicked.getText().equals("")) {
                        if (player1Turn) {
                            buttonClicked.setText("X");
                            buttonClicked.setForeground(Color.RED);
                        } else {
                            buttonClicked.setText("O");
                            buttonClicked.setForeground(Color.BLUE);
                        }
                        player1Turn = !player1Turn;
                        checkWinner();
                    }
                }
            }); // action listener ends

            panel.add(buttons[i]);
        } // for loop ends

        // panel on frame
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void checkWinner() {

        String[][] winConditions = {
                {buttons[0].getText(), buttons[1].getText(), buttons[2].getText()},
                {buttons[3].getText(), buttons[4].getText(), buttons[5].getText()},
                {buttons[6].getText(), buttons[7].getText(), buttons[8].getText()},
                {buttons[0].getText(), buttons[3].getText(), buttons[6].getText()},
                {buttons[1].getText(), buttons[4].getText(), buttons[7].getText()},
                {buttons[2].getText(), buttons[5].getText(), buttons[8].getText()},
                {buttons[0].getText(), buttons[4].getText(), buttons[8].getText()},
                {buttons[2].getText(), buttons[4].getText(), buttons[6].getText()}
        };

        // checking the winner
        for (int i = 0; i < winConditions.length; i++) {
            String[] line = winConditions[i];
            if (line[0].equals("X") && line[1].equals("X") && line[2].equals("X")) {
                JOptionPane.showMessageDialog(frame, "Player 1 (X) wins!");
                resetGame();
                return;
            } else if (line[0].equals("O") && line[1].equals("O") && line[2].equals("O")) {
                JOptionPane.showMessageDialog(frame, "Player 2 (O) wins!");
                resetGame();
                return;
            }
        }

        // Check for draw
        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                draw = false;
                break;
            }
        }

        if (draw) {
            JOptionPane.showMessageDialog(frame, "It's a draw!");
            resetGame();
        }
    }

    public static void resetGame() {
        // Reset all buttons to empty
        for (JButton button : buttons) {
            button.setText("");
        }
        player1Turn = true;
    }
}