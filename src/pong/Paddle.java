package pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle{ //we make our Paddle class a subclass of Rectangle so we can treat Paddle as a rectangle

	
	//values of our Paddle class
	
	
	//This id will be either 1 or  2 for player 1 and player 2's paddle 1 and paddle2
	int id;
	
	//This will be how fast the Paddle moves when the paddle moves either up or down
	int yVelocity;
	int speed = 10;
	
	//Constructor method for Paddle
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id = id;
	}
	
	
	//methods for key-pressed events that we are calling from the GamePanel inner class "AL" or Action-listener 
	public void keyPressed(KeyEvent e) {
		//switch statement to determine the action the user has taken on the key
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-speed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(speed);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-speed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(speed);
				move();
			}
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
				move();
			}
			break;
		}
	}
	
	//method to set direction of paddles
	//Since our paddles only will move up and down on the screen, we do not need to set the x direction, only the y direction  
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	
	//method allowing paddles to move
	public void move() {
		y = y + yVelocity;
	}
	
	//method allowing us to draw the graphics of the paddle 
	public void draw(Graphics g) {
		if(id == 1)
			g.setColor(Color.blue);
		else 
			g.setColor(Color.red);
		    g.fillRect(x, y, width, height);
	}
	
}
