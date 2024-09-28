import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//the window that has the min and max button(the Frame)
public class GameFrame extends JFrame {

    CardLayout cardLayout;
    JPanel container;

    GamePanel gamePanel;
    MenuPanel menuPanel;

    GameFrame() {
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        // Create game and menu panels
        gamePanel = new GamePanel();
        menuPanel = new MenuPanel(cardLayout, container, gamePanel);

        // Add panels to the container
        container.add(menuPanel, "MenuPanel");
        container.add(gamePanel, "GamePanel");

        // Show menu panel first
        cardLayout.show(container, "MenuPanel");

        this.add(container);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
