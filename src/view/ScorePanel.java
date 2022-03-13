/*
 * Name: Vidhi Ruparel(60%), Tiffany Tang(40%)
 * Date: Sunday, December 5th, 2021
 * Course: ICS4U1 - Mr. Fernandes
 */

package view;

// ===== IMPORTS =====================================================================================
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.TTRController;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;



public class ScorePanel extends JPanel {


// ===== Tiffany: INSTANCE VARIABLES ===========================================================================
	
	// Player Specific Instance Variables
	private int currentPlayer;				// The current player
	private String playerColour;			// Colour assigned to the current player
	
	// JLabel
	private JLabel scoreTitleLabel = new JLabel("Scores:");
	
	private JLabel[] playerScores = new JLabel[4];
	
	//Scores
	private int[] scoreArray = new int[4];
	
	// Fonts
	private Font titleFont = new Font("Verdana", Font.CENTER_BASELINE, 18);
	
	//
	ImageIcon image;
	
	
	
// ===== METHODS ======================================================================================
	
		// ----- Tiffany: Constructor Method -------------------------------------------------------------------
		public ScorePanel () {
				
			// Set up the panel
			setLayout(null);								// Null layout - manually put in coordinates
			setBackground(new Color(181, 147, 202, 250));	// Default background colour
			
			// Label set up
			scoreTitleLabel.setFont(titleFont);				// Set the font
			scoreTitleLabel.setBounds(200, 10, 120, 75);	//Position and sizing
			add(scoreTitleLabel);							// Add the title label onto the panel
			
			displayScores();								// Call the method that displays the player scores
			
		} // ends the constructor
		
		
		// ---- Vidhi: initializeScores Method -------------------------------------------------------------------
		public void initializeScores() {
			
			
			// Get player scores and put into scoreArray
			for (int index = 0; index < 4; index++) {
				
				scoreArray[index] = TTRController.getPlayerArray()[index].getPlayerScore();
			
			}
			
			repaint();
			
		}

		
		// ----- Vidhi: displayScores Method -------------------------------------------------------------------
		public void displayScores() {
			
			initializeScores();
						
			// Create a JLabel
			for (int index = 0; index < getPlayerScores().length; index++) {
				
				//Create a JLabel for each player and their score
				getPlayerScores()[index] = new JLabel("Player " + (index + 1) + ": " + scoreArray[index]);
				getPlayerScores()[index].setBounds(200, 50 + (index * 25), 120, 75);		// Position and sizing
			
			}
				
			// Add the score labels onto the panel
			for (JLabel playerScore : getPlayerScores())
				add(playerScore);
			
			revalidate();
			repaint();
			
		}
		
		
		// ----- reapintAll Method -------------------------------------------------------------------
		public void repaintAll() {
			
			TTRController.getGameFrame().getScorePanel().removeAll();
			
			initializeScores();
			
			// Label set up
			scoreTitleLabel.setFont(titleFont);				// Set the font
			scoreTitleLabel.setBounds(200, 10, 120, 75);	//Position and sizing
			add(scoreTitleLabel);							// Add the title label onto the panel
			
			// Add the score labels onto the panel
			for (JLabel playerScore : getPlayerScores())
				add(playerScore);
			
			revalidate();
			repaint();
			
		} // ends the method that repaints all the components


		// ----- Getter and Setter Methods --------------------------------------------------------------
		public int getCurrentPlayer() {
			return currentPlayer;
		}

		public void setCurrentPlayer(int currentPlayer) {
			this.currentPlayer = currentPlayer;
		}

		public String getPlayerColour() {
			return playerColour;
		}

		public void setPlayerColour(String playerColour) {
			
			//playerColour.toLowerCase();			// Change all the letters to lowercase
			
			// Player colour is yellow
			if (playerColour == "YELLOW")
				setBackground(new Color(255, 235, 128));
			
			// Player colour is green
			else if (playerColour == "GREEN")
				setBackground(new Color(206, 245, 153));
			
			// Player colour is red
			else if (playerColour == "RED")
				setBackground(new Color(202, 121, 121));
			
			// Player colour is blue
			else if (playerColour == "BLUE")
				setBackground(new Color(177, 236, 247));
			
			revalidate();
			repaint();							// Refreshes the screen to apply the new background colour
		}


		public JLabel[] getPlayerScores() {
			return playerScores;
		}


		public void setPlayerScores(JLabel[] playerScores) {
			this.playerScores = playerScores;
		}
		

}