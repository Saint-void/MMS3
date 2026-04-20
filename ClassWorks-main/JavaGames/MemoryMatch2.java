import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class MemoryMatch2 extends JFrame {

    private final String[] symbols = {
        "🍎", "🍎", "🍎",
        "🍌", "🍌", "🍌",
        "🍇", "🍇", "🍇",
        "🍒", "🍒", "🍒",
        "🍍", "🍍", "🍍",
        "🥝", "🥝", "🥝"
    };

    private final JButton[] buttons = new JButton[18];
    private String[] board;
    private ArrayList<JButton> selected = new ArrayList<>();
    private int matchesFound = 0;
    
    // NEW: Prevents clicking more cards while the 3-card timer is running
    private boolean isProcessing = false; 

    public MemoryMatch2() {
        setTitle("Triple Memory Match Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 6, 10, 10));

        setupGame();
        setVisible(true);
    }

    private void setupGame() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, symbols);
        Collections.shuffle(list);
        board = list.toArray(new String[0]);

        for (int i = 0; i < 18; i++) {
            buttons[i] = new JButton("?");
            buttons[i].setFont(new Font("Segoe UI Emoji", Font.BOLD, 40));
            buttons[i].setFocusPainted(false);

            final int index = i;
            buttons[i].addActionListener(e -> handleCardClick(buttons[index], index));
            add(buttons[i]);
        }
    }

    private void handleCardClick(JButton clickedButton, int index) {
        // Block clicks if 3 cards are already being processed OR card is already flipped
        if (isProcessing || !clickedButton.getText().equals("?") || selected.contains(clickedButton)) {
            return;
        }

        clickedButton.setText(board[index]);
        selected.add(clickedButton);

        if (selected.size() == 3) {
            isProcessing = true; // Lock the board
            Timer timer = new Timer(800, e -> checkMatch2());
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void checkMatch2() {
        String a = selected.get(0).getText();
        String b = selected.get(1).getText();
        String c = selected.get(2).getText();

        boolean match = a.equals(b) && b.equals(c);

        for (JButton btn : selected) {
            if (match) {
                btn.setEnabled(false);
            } else {
                btn.setText("?");
            }
        }

        if (match) {
            matchesFound++;
            if (matchesFound == 6) {
                JOptionPane.showMessageDialog(this, "You cleared the triple match board! 🔥");
            }
        }

        selected.clear();
        isProcessing = false; // Re-enable clicking
    }

    public static void main(String[] args) {
        // Corrected class name reference
        SwingUtilities.invokeLater(MemoryMatch2::new);
    }
}