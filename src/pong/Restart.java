package pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Restart {

	
	
	//int id;
	
	
//This method reads if the user presses space. If he does, the method will call PongGame.startGame();	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		 PongGame.startGame();
		} 
	}
	
	
	/**
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		PongGame.startGame();
	}
}
**/
	
	
}
