package pong;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle { //We make our Score class a subclass of Rectangle so  we can treat is as a Rectangle

	
	static int GAME_WIDTH;      
	static int GAME_HEIGHT;
	int player1;                //this variable holds the current score for player1
	int player2;				//this variable holds the current score for player2
	
	//Constructor class for our score
	Score(int GAME_WIDTH, int GAME_HEIGHT) {
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}
	
	
	//method to draw the score
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas", Font.PLAIN,60));
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(player1/10) + String.valueOf(player1%10), (GAME_WIDTH/2) -85, 50);
		g.drawString(String.valueOf(player2/10) +String.valueOf(player2%10), (GAME_WIDTH/2) +20, 50);
		
		
		//This code is my addition. Adds a "game over" screen once a player has reached the maximum possible score
		if (player1 == 5 || player2 == 5){
			g.setFont(new Font("Consolas", Font.PLAIN,50));
			g.drawString("GAME OVER", (GAME_WIDTH/2) -150, 250);
			if (player1 == 5) {
				g.drawString("PLAYER ONE WINS", (GAME_WIDTH/2) -220, 350);
				g.setFont(new Font("Consolas", Font.PLAIN,30));
				g.drawString("PRESS SPACE TO PLAY AGAIN", (GAME_WIDTH/2) -210, 425);
			} else {
					g.drawString("PLAYER TWO WINS", (GAME_WIDTH/2) -220, 350);
					g.setFont(new Font("Consolas", Font.PLAIN,30));
					g.drawString("PRESS SPACE TO PLAY AGAIN", (GAME_WIDTH/2) -210, 425);
				}
			}
	}
	
	

}
