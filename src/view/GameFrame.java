/*
 * Name: Tiffany Tang
 * Date: Sunday, December 5th, 2021
 * Course: ICS4U1 - Mr. Fernandes
 */

package view;




// ===== IMPORTS =====================================================================================
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import application.TTRCanadaApplication;

import java.awt.Color;

import javax.swing.*;





public class GameFrame extends JFrame {

// ===== INSTANCE VARIABLES ===========================================================================
	
	// Panels
	private BoardPanel boardPanel = new BoardPanel();
	public static ScorePanel scorePanel = new ScorePanel();
	private static CardPanel cardPanel = new CardPanel();
	public static PlayerPanel playerPanel = new PlayerPanel();
	public static ImageIcon checkmark = new ImageIcon();

	// Menu
	private static JMenuBar menubar = new JMenuBar(); 			// Creates the menu bar which is where the menus are located

	
		
		
		
// ===== METHODS =======================================================================================
	
	// ----- Constructor Method ------------------------------------------------------------------------
	public GameFrame() {

		// Set up the frame
		frameSetUp();
		
		// Add the panels onto the frame
	    addPanels();
		
	    // Sets up the menu bar onto the frame
		setJMenuBar(menubar);
		
		// Makes the components of the frame visible
		setVisible(true);
			
	} // ends the constructor

	
	// ----- frameSetUp Method ------------------------------------------------------------------------
	private void frameSetUp() {
		
		// Set up the frame
		setLayout(null);																				// Grid bag layout - arranges components in rows and columns
		setTitle("Train to Ride ~ Canada!"); 															// Title 
		setSize(1920, 1080); 																			// Size
		setResizable(false);																			// Makes the size not adjustable
		getContentPane().setBackground(Color.WHITE);													// Background colour
		// https://stackoverflow.com/questions/20165698/java-how-to-draw-a-border-around-an-undecorated-jframe
		getRootPane().setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, new Color(169, 139, 105))); // Adds a brown border around the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 												// When you stop the program it will automatically close the frame
	
	}


	// ----- addPanels Method ------------------------------------------------------------------------
	private void addPanels() {
		
		// Tired GridBayLayout
		// https://stackoverflow.com/questions/23995566/java-gridbaglayout-components-positioning
		// Create a constraints variable to help layout the components of the frame 
//		GridBagConstraints left = new GridBagConstraints();
//        left.anchor = GridBagConstraints.EAST;
//        left.weightx = 0;
//        left.weighty = 0;
//        
//        GridBagConstraints right = new GridBagConstraints();
//        right.weightx = 0;
//        right.weighty = 0;
//       // right.gridwidth = GridBagConstraints.REMAINDER;
//        right.anchor = GridBagConstraints.WEST;

		boardPanel.setBounds(0, 0, 1400, 900);		// Position and sizing
  		add(boardPanel); 							// Add the board panel
  		
  		scorePanel.setBounds(1400, 0, 520, 200);	// Position and sizing
   		add(scorePanel);							// Add the score panel
  		
   		cardPanel.setBounds(0, 900, 1400, 180);		// Position and sizing
  		add(cardPanel);								// Add the card panel
  		
  		playerPanel.setBounds(1400, 200, 520, 880);	// Position and sizing
  		add(playerPanel);							// Add the player panel		

	}


	// ----- Getters and Setters ------------------------------------------------------------------------
	
	public BoardPanel getBoardPanel() {
		return boardPanel;
	}



	public void setBoardPanel(BoardPanel boardPanel) {
		this.boardPanel = boardPanel;
	}



	public ScorePanel getScorePanel() {
		return scorePanel;
	}



	public void setScorePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
	}



	public static CardPanel getCardPanel() {
		return cardPanel;
	}



	public void setCardPanel(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
	}



	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}



	public void setPlayerPanel(PlayerPanel playerPanel) {
		this.playerPanel = playerPanel;
	}


	public static JMenuBar getMenubar() {
		return menubar;
	}


	public static void setMenubar(JMenuBar menubar) {
		GameFrame.menubar = menubar;
	}


} // ends the class