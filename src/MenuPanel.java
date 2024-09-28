import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {

    JButton startButton;
    JButton rulesButton;
    CardLayout cardLayout;
    JPanel container;
    GamePanel gamePanel;

    MenuPanel(CardLayout cardLayout, JPanel container, GamePanel gamePanel) {
        this.cardLayout = cardLayout;
        this.container = container;
        this.gamePanel = gamePanel;

        setLayout(new GridBagLayout()); // Center buttons
        setBackground(new Color(50, 50, 50)); // Dark gray background

        startButton = new JButton("Start Game");
        rulesButton = new JButton("Rules");

        // Style buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        Dimension buttonSize = new Dimension(200, 60); // Set the same size for both buttons
        Insets buttonPadding = new Insets(10, 20, 10, 20); // Internal padding

        startButton.setFont(buttonFont);
        startButton.setPreferredSize(buttonSize); // Set preferred size
        startButton.setMargin(buttonPadding); // Add internal padding
        startButton.setBackground(new Color(34, 139, 34)); // Green background
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        rulesButton.setFont(buttonFont);
        rulesButton.setPreferredSize(buttonSize); // Set preferred size
        rulesButton.setMargin(buttonPadding); // Add internal padding
        rulesButton.setBackground(new Color(70, 130, 180)); // Steel blue background
        rulesButton.setForeground(Color.WHITE);
        rulesButton.setFocusPainted(false);
        rulesButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Layout the buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Space between buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch buttons horizontally
        add(startButton, gbc);

        gbc.gridy = 1;
        add(rulesButton, gbc);

        // Button actions
        startButton.addActionListener(e -> {
            // Switch to GamePanel
            cardLayout.show(container, "GamePanel");
            gamePanel.startGame(); // Start the game
            gamePanel.requestFocusInWindow(); // Ensure GamePanel gets focus for key events
        });


        rulesButton.addActionListener(e -> showRulesDialog());
    }

    private void showRulesDialog() {
        JOptionPane.showMessageDialog(this, "Rules: \n1. Use W/S to move Player 1's paddle.\n2. Use Up/Down to move Player 2's paddle.\n3. First to 10 points wins!", "Game Rules", JOptionPane.INFORMATION_MESSAGE);
    }
}
