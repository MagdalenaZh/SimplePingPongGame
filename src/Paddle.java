import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Paddle extends Rectangle {
	
	//1 or 2 corresponding to player 1 or 2 
	int id;
	//how fast the paddle is moving up and down 
	int yVelocity;
	
	Paddle(int x, int y , int PADDLE_WIDTH, int PADDLE_HEIGHT, int id ){
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
	}
	
	public void keyPressed(KeyEvent e) {
		
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	
	//paddles are only moving up and down,therefore only y direction is set
	public void setYDiraction(int yDiraction) {
		
	}
	
	public void move() {
		
	}
	
	public void draw(Graphics g) {
		 if(id == 1) {
			 g.setColor(Color.blue);
		 }
		 else {
			 g.setColor(Color.red);
		 }
		 
		 g.fillRect(x, y, width, height);
	}
	
}
