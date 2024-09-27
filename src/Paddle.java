import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Paddle extends Rectangle {
	
	//1 or 2 corresponding to player 1 or 2 
	int id;
	//how fast the paddle is moving up and down 
	int yVelocity;
	
	int speed = 10;
	
	Paddle(int x, int y , int PADDLE_WIDTH, int PADDLE_HEIGHT, int id ){
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1: 
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDiraction(-speed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDiraction(speed);
				move();
			}
			break;
		case 2: 
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDiraction(-speed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDiraction(speed);
				move();
			}
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1: 
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDiraction(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDiraction(0);
				move();
			}
			break;
		case 2: 
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDiraction(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDiraction(0);
				move();
			}
			break;
		}
	}
	
	
	//paddles are only moving up and down,therefore only y direction is set
	public void setYDiraction(int yDiraction) {
		yVelocity = yDiraction;
	}
	
	public void move() {
		y = y + yVelocity;
	}
	
	public void draw(Graphics g) {
		 if(id == 1) {
			 g.setColor(Color.green);
		 }
		 else {
			 g.setColor(Color.magenta);
		 }
		 
		 g.fillRect(x, y, width, height);
	}
	
}
