package pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {  //We are making GameFrame a subclass of JFrame. So here we can treat "GameFrame" as a JFrame 


	//Before our GameFrame constructor, we create an instance of our GamePanel class.
	GamePanel panel;
	//Constructor method for game frame 
	GameFrame() {
		panel = new GamePanel();
		this.add(panel);                   //adding game panel (extends JFrame) to game frame (Extends JFrame)
		this.setTitle("Pong Game");		   //creates title of GameFrame
		this.setResizable(false);		   // the size of the Game Frame can never be changed
		this.setBackground(Color.black);   // The background set to black   
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //We set GameFrame to exit once th frame is closed.
		this.pack();											//The .pack method allows GmeFrame to fit to the size of GamePanel 
		this.setVisible(true);									//Without this, we would not be able to view the Game Frame or Game Panel
		this.setLocationRelativeTo(null);						//Setting location relative to null allows the GameFrame to pop up at the middle of our screen.
	}
	
	
	
}
