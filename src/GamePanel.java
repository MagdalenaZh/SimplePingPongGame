import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//the panel where everything is painted 
public class GamePanel extends JPanel implements Runnable{

	//FINAL - prohibits from accidentally modifying this var
	static final int GAME_WIDTH = 1000;
	//making the height and width ratio correspond to real life ping pong table
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	//the higher the ball the higher the diameter
	static final int BALL_DIAMETER = 20;
	
	static final int PADDLE_WIDTH = 25;
	
	static final int PADDLE_HEIGHT = 100;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	
	GamePanel(){
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		//this will focus on the keys
		this.setFocusable(true);
		//this will respond to key strokes 
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	//methods to call when wanting to restart game
	public void newBall() {
		
	}
	
	public void newPaddles() {
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT,1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT,2);
	}
	
	//method to paint stuff on the screen
	public void paint(Graphics g) {
		//create an image that has the dimensions of the width and height of the game panel
        image = createImage(getWidth(), getHeight());
        //create a graphic
        graphics = image.getGraphics();
        //call the draw method to draw all of the components
        draw(graphics);
        //starting at top left corner
        g.drawImage(image,0,0,this);
        
	}
	public void draw(Graphics g) {
		paddle1.draw(g);
	}
	
	//method to move things after each iteration of game loop
	public void move() {
		
	}
	
	//method to check collision 
	public void checkCollision() {
		
	}
	
	public void run() {
	    // Initialize the last time check using the system's current time in nanoseconds
	    long lastTime = System.nanoTime();

	    // Target frames per second (FPS), or updates per second, is 60.0
	    double amountOfTicks = 60.0;

	    // How many nanoseconds each tick or update takes (1 second / 60 updates)
	    double ns = 1000000000 / amountOfTicks;

	    // Delta will track the time difference and accumulate to decide when to update
	    double delta = 0;

	    // Infinite game loop
	    while(true) {
	        // Get the current time in nanoseconds
	        long now = System.nanoTime();

	        // Calculate how much time has passed since the last update and add it to delta
	        // delta increases by the fraction of a full update (tick) that has passed
	        delta += (now - lastTime) / ns;

	        // Update the lastTime to the current time for the next loop iteration
	        lastTime = now;

	        // If enough time has passed to process at least one game tick
	        if(delta >= 1) {
	            // Call methods to update the game state
	            move();           // Update positions of game objects
	            checkCollision(); // Check for collisions between objects
	            repaint();        // Render the updated game state on the screen

	            // Subtract 1 from delta, indicating we processed one tick
	            // If delta > 1, this will allow multiple updates in a single iteration (catch up)
	            delta--;
	        }
	    }
	}

	
	//inner class action listener 
	public class AL extends KeyAdapter{
		
		public void keyPressed(KeyEvent e) {
			
		}
		
		public void keyReleased(KeyEvent e) {
			
		}
		
	}
	
	
}
