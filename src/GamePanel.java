import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;


public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * 0.5555);
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    
    boolean isGameRunning = false; // Control game start
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    
    public GamePanel() {
        random = new Random(); // Initialize the random object
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }


 // Method to reset game state (called when "Start Game" is pressed)
    public void resetGame() {
        score.player1Score = 0;
        score.player2Score = 0;
        newBall();
        newPaddles();
        isGameRunning = false; // Keep the game paused
    }

    //TODO: fix delayed start of ball
    // Method to start the game when "Start Game" button is pressed
    public void startGame() {
        resetGame(); // This resets scores, ball, and paddles
        isGameRunning = true; // Allow the game to start
        this.requestFocusInWindow(); // Ensure GamePanel gets focus for key events
    }


    public void newBall() {
        random = new Random();
        
        // Create a new ball at the center
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), 
                        (GAME_HEIGHT / 2) - (BALL_DIAMETER / 2), 
                        BALL_DIAMETER, BALL_DIAMETER);
        
        // Set the initial direction randomly
        int randomXDirection = random.nextBoolean() ? 1 : -1;
        int randomYDirection = random.nextBoolean() ? 1 : -1;
        
        ball.setXDiraction(randomXDirection * ball.initialSpeed); // Initialize ball speed
        ball.setYDiraction(randomYDirection * ball.initialSpeed);
    }



    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move() {
        if (isGameRunning) { // Only move if the game is running
            paddle1.move();
            paddle2.move();
            ball.move();
        }
    }

    public void checkCollision() {
        if (!isGameRunning) return; // Prevent checking collisions when the game is not running

        // Ball bouncing off top and bottom edges
        if (ball.y <= 0) {
            ball.setYDiraction(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            ball.setYDiraction(-ball.yVelocity);
        }

        // Ball bouncing off paddles
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            ball.yVelocity = (ball.yVelocity > 0) ? ball.yVelocity + 1 : ball.yVelocity - 1;
            ball.setXDiraction(ball.xVelocity);
            ball.setYDiraction(ball.yVelocity);
        }

        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            ball.yVelocity = (ball.yVelocity > 0) ? ball.yVelocity + 1 : ball.yVelocity - 1;
            ball.setXDiraction(-ball.xVelocity);
            ball.setYDiraction(ball.yVelocity);
        }

        // Paddles stop at window edges
        if (paddle1.y <= 0) paddle1.y = 0;
        if (paddle1.y >= GAME_HEIGHT - PADDLE_HEIGHT) paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        if (paddle2.y <= 0) paddle2.y = 0;
        if (paddle2.y >= GAME_HEIGHT - PADDLE_HEIGHT) paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

        // Give a player 1 point and create new paddles and ball
        if (ball.x <= 0) {
            score.player2Score++;
            newPaddles();
            newBall();
            isGameRunning = false; // Pause game after score
            System.out.println("PLAYER 2: " + score.player2Score);
            restartGame();
        }
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1Score++;
            newPaddles();
            newBall();
            isGameRunning = false; // Pause game after score
            System.out.println("PLAYER 1: " + score.player1Score);
            restartGame();
        }
    }

    private void restartGame() {
        // Use a timer to restart the game after a delay
        new Timer(2000, new ActionListener() { // 2000 ms delay
            @Override
            public void actionPerformed(ActionEvent e) {
                isGameRunning = true; // Resume the game
            }
        }).start();
    }

    
    //TODO: fix coninuos 
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                if (isGameRunning) {  // Check if the game is running
                    move();
                    checkCollision();
                    repaint();
                }
                delta--;
            }
        }
    }


    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
