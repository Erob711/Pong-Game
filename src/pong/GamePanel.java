package pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable { // We make our "GamePanel" class is a sub-class of JPanel,
															// so we can
															// treat it as a JPanel. We make it implement the Runnable
															// interface so it can
															// run on a thread.

	private static final long serialVersionUID = 1L;
	// values within our GamePanel class
	static final int GAME_WIDTH = 1000; // We want these static so that in case we have multiple instances of GamePanel
										// they only share one variable.
										// We also want these final so that we cannot potentially change our game width
										// later on in development of the game.
	static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));

	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT); // we use Dimension class to create an
																					// instance of a dimension called
																					// SCREEN_SIZE which takes our
																					// GAME_WIFTH and GAME_HEIGHT as
																					// parameters

	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;

	// Instances we are declaring but not assigning any value to yet.

	Thread gameThread; // Since we are implementing the Runnable interface, we must use an instance of
						// Thread.

	Image image; // Instance of Image class.

	Graphics graphics; // Instance of Graphics class.
	Random random; // Instance of Random class.
	Paddle paddle1; // Instance of our custom class Paddle
	Paddle paddle2; // Instance of our custom class Paddle
	Ball ball; // Instance of our custom class Ball
	Score score; // Instance of our custom class Score
	

	// Constructor method for game panel
	GamePanel() {
		// We need to create new paddles every time we create our game thru gamepanel
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT); // we are constructing an instance of Score here
		this.setFocusable(true); //
		this.addKeyListener(new AL()); // Here we are using the inner class from GamePanel to get AL
		this.setPreferredSize(SCREEN_SIZE);

		gameThread = new Thread(this); // Since we are implementing the Runnable interface, we use a THread
		gameThread.start();
		
		
		
	}

	// declaring methods for this class

	// method to create new ball on screen
	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER),
				BALL_DIAMETER, BALL_DIAMETER);

	}

	// method to create new paddles
	public void newPaddles() {
		paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
	}

	// method to paint on the screen
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	// method to draw on the screen
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}

	// method that allows us to move things for each iteration of our
	// "game loop"
	public void move() {
		// makes the movement of paddles smoother
		paddle1.move();
		paddle2.move();
		ball.move();
	}

	// method that will allow us to know when things touch each other in our game
	public void checkCollision() {
		// bounce ball off top and bottom window edges
		if (ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
		// bounces ball off paddles
		if (ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; // increases speed of ball every time it hits a paddle
			if (ball.yVelocity > 0)
				ball.yVelocity++;
			else
				ball.yVelocity--;
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		if (ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; // increases speed of ball every time it hits a paddle
			if (ball.yVelocity > 0)
				ball.yVelocity++;
			else
			ball.yVelocity--;
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}

		// Stops paddles at window edges
		if (paddle1.y <= 0)
			paddle1.y = 0;
		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;

		if (paddle2.y <= 0)
			paddle2.y = 0;
		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

		// give a player 1 point and creates new paddles and ball
		if (ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
			// TEST PRINT//
			//System.out.println(score.player2);
		}
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
			// TEST PRINT//
			//System.out.println(score.player1);
		}
		

	}

	// method where we will run the iterations of the game loop
	public void run() {
		// Create a game loop
		// "frames per second"
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while (true && score.player1 != 5 && score.player2 != 5) { // Our game loop will run as long as these
																	// connditions are met
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
			
		} 
		
	}
	
	
	
	

	// We have an inner class here. "AL" stands for "Action Listener"
	// This class is going to be added to the GamePanel and we will call the
	// appropriate keyPressed and keyReleased methods
	// for our paddle.
	public class AL extends KeyAdapter {
		// declaring methods within out inner class
		public void keyPressed(KeyEvent e) { // These methods in our "Action Listener" class are outlining the keyboard
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
			
			//Here we are using our innerclass AL "Action Listener" to check if the score of any player is 5
			//If so, then we will create an instance of the restart class and call restart.keypressed which takes a parameter KeyEvent
			
			//If we read the keyPressed method of the restart class we see that is reads for the user pressing the spacebar and then calls the PongGame.startGame() method if so.
			if (score.player1  == 5 || score.player2 == 5) {
				Restart restart = new Restart();
				restart.keyPressed(e);
			}
			// events that we will be listening for.

		}

		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
			//restart.keyReleased(e);
		}
	}
		
	
}
