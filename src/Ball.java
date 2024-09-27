import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{
	Random random;
	int xVelocity;
	int yVelocity;
	int initialSpeed = 2;
	
	Ball(int x, int y, int width, int height){
		super(x,y,width,height);
		
		random = new Random();
		int randomXDiraction = random.nextInt(2);
		if(randomXDiraction == 0) {
			randomXDiraction--;
		}
		
		setXDiraction(randomXDiraction * initialSpeed);
		
		int randomYDiraction = random.nextInt(2);
		if(randomYDiraction == 0) {
			randomYDiraction--;
		}
		setYDiraction(randomYDiraction * initialSpeed);
	}
	
	
	
	public void setYDiraction(int randomYDiraction) {
		yVelocity = randomYDiraction;
	}

	public void setXDiraction(int randomXDiraction) {
		xVelocity = randomXDiraction;
	}
	
	public void move() {
		x += xVelocity;
		y += yVelocity;
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
}
