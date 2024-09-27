import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//the window that has the min and max button(the Frame)
public class GameFrame extends JFrame {

	//create an instance of the game panel
	GamePanel panel;
	
	GameFrame(){
		panel = new GamePanel();
		this.add(panel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
