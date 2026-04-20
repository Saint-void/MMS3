import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HangmanGame extends JFrame {

    private String[] words = {"DOCKER", "KUBERNETES", "JAVA", "SPRINGBOOT", "MICROSERVICE"};
    private String targetWord;
    private StringBuilder displayWord;
    private int attemptsLeft;
    private JLabel wordLabel, statusLabel;
    private JPanel drawingPanel, keyboardPanel;
    private ArrayList<Character> guessedLetters;

    public HangmanGame() {
        setupLookAndFeel();
        initializeGame();

        setTitle("🔥 Cloud Hangman");
        setSize(750, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(new Color(30, 30, 30));

        // Top: Word Display
        wordLabel = new JLabel(formatWord(displayWord.toString()), SwingConstants.CENTER);
        wordLabel.setFont(new Font("Consolas", Font.BOLD, 36));
        wordLabel.setForeground(Color.WHITE);
        add(wordLabel, BorderLayout.NORTH);

        // Center: Drawing Panel
        drawingPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(30, 30, 30));
                drawMan(g);
            }
        };
        add(drawingPanel, BorderLayout.CENTER);

        // Right: Keyboard
        keyboardPanel = new JPanel(new GridLayout(5, 6, 8, 8));
        keyboardPanel.setBackground(new Color(30, 30, 30));
        keyboardPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        createKeyboard();
        add(keyboardPanel, BorderLayout.EAST);

        // Bottom: Status + Restart
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(30, 30, 30));

        statusLabel = new JLabel("Attempts Left: " + attemptsLeft, SwingConstants.CENTER);
        statusLabel.setForeground(Color.LIGHT_GRAY);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton restartBtn = new JButton("Restart");
        restartBtn.setFont(new Font("Arial", Font.BOLD, 14));
        restartBtn.addActionListener(e -> resetGame());

        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(restartBtn, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
    }

    private void initializeGame() {
        targetWord = words[new Random().nextInt(words.length)];
        displayWord = new StringBuilder("_".repeat(targetWord.length()));
        attemptsLeft = 6;
        guessedLetters = new ArrayList<>();
    }

    private void createKeyboard() {
        keyboardPanel.removeAll();

        for (char c = 'A'; c <= 'Z'; c++) {
            JButton btn = new JButton(String.valueOf(c));

            // 🔥 MAKE LETTERS CLEAR
            btn.setFont(new Font("Consolas", Font.BOLD, 18));
            btn.setFocusPainted(false);

            // 🎨 HIGH CONTRAST COLORS
            btn.setBackground(new Color(70, 70, 70));
            btn.setForeground(Color.WHITE);

            // ✨ BORDER FOR VISIBILITY
            btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

            // 💡 HOVER EFFECT
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(100, 100, 100));
                }

                public void mouseExited(MouseEvent e) {
                    if (btn.isEnabled()) {
                        btn.setBackground(new Color(70, 70, 70));
                    }
                }
            });

            btn.addActionListener(e -> {
                processGuess(btn.getText().charAt(0));
                btn.setEnabled(false);

                // 👇 SHOW USED LETTER
                btn.setBackground(new Color(30, 30, 30));
                btn.setForeground(Color.GRAY);
            });

            keyboardPanel.add(btn);
        }
    }

    private void processGuess(char c) {
        if (guessedLetters.contains(c) || attemptsLeft <= 0) return;

        guessedLetters.add(c);

        if (targetWord.indexOf(c) >= 0) {
            for (int i = 0; i < targetWord.length(); i++) {
                if (targetWord.charAt(i) == c) {
                    displayWord.setCharAt(i, c);
                }
            }
        } else {
            attemptsLeft--;
        }

        updateUI();
        checkGameOver();
    }

    private void updateUI() {
        wordLabel.setText(formatWord(displayWord.toString()));
        statusLabel.setText("Attempts Left: " + attemptsLeft);
        drawingPanel.repaint();
    }

    private void checkGameOver() {
        if (displayWord.toString().equals(targetWord)) {
            int choice = JOptionPane.showConfirmDialog(this,
                    "🔥 YOU WON!\nPlay again?",
                    "Victory",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) resetGame();
            else System.exit(0);

        } else if (attemptsLeft <= 0) {
            int choice = JOptionPane.showConfirmDialog(this,
                    "💀 GAME OVER!\nWord was: " + targetWord + "\nPlay again?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) resetGame();
            else System.exit(0);
        }
    }

    private void resetGame() {
        initializeGame();
        createKeyboard();
        updateUI();
    }

    private String formatWord(String s) {
        return s.replace("", " ").trim();
    }

    private void drawMan(Graphics g) {
        g.setColor(attemptsLeft <= 2 ? Color.RED : Color.WHITE);

        // Gallows
        g.drawLine(50, 300, 150, 300);
        g.drawLine(100, 300, 100, 50);
        g.drawLine(100, 50, 250, 50);
        g.drawLine(250, 50, 250, 80);

        if (attemptsLeft < 6) g.drawOval(230, 80, 40, 40);
        if (attemptsLeft < 5) g.drawLine(250, 120, 250, 200);
        if (attemptsLeft < 4) g.drawLine(250, 140, 210, 170);
        if (attemptsLeft < 3) g.drawLine(250, 140, 290, 170);
        if (attemptsLeft < 2) g.drawLine(250, 200, 210, 250);
        if (attemptsLeft < 1) g.drawLine(250, 200, 290, 250);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HangmanGame().setVisible(true));
    }
}