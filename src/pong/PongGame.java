package pong;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class PongGame {

	
	public static void main(String[] args) {
		
		//in our main method we create an instance of our GameFrame class 
		//The instance of GameFrame will hold GamePanel
		//GameFrame basically gives us the window on the screen that has a border, minimize button, exit button.
		//GamePanel will hold all the elements of our game.
		
		PongGame.startGame();
	}
	

	
	
	public static void startGame() {
		GameFrame frame = new GameFrame();
	}
	
}
