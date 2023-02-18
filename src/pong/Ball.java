package pong;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{ //We make our Ball class a subclass of Rectangle so we can treat is as a Rectangle

	//TEST commit 
	Random random;			//instance of Random
	int xVelocity;          //How fast our ball moves on the x axis
	int yVelocity;			//How fast our ball moves on the y axis
	int initialSpeed = 3;
	
	//Constructor class for our ball
	Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0)
			randomXDirection --;
		setXDirection(randomXDirection * initialSpeed);
		
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0)
			randomYDirection --;
		setYDirection(randomYDirection * initialSpeed);
		
	}
	
	
	//set direction of ball
	
	//when we create a new ball, it will go in a random direction, so we will use the parameter randomXDirection
	//We will use an instance of the Random class to help us with that  
	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
	}
	
	//same here, but with the y direction
	//our paddles only need to move up and down, but our ball needs to be able to move dynamically around the screen
	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;
	}
	
	//method to let the ball move 
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	
	//method to allow us the draw the graphics of ou ball
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
}
