/*
 * Name: Jathusaa Indrakumaran
 * Date: Sunday, December 5th, 2021
 * Course: ICS4U1 - Mr. Fernandes
 */

package view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

//import model.Player;
import controller.TTRController;
import model.CardColour;

public class PlayerPanel extends JPanel {
	
	
	//Instance Variables
	
	//Panel Title 
	private static JLabel playerPanelTitleLabel = new JLabel(" PLAYER PANEL ", SwingConstants.CENTER);
	
	//Current Player Title
	private static JLabel playerTitleLabel = new JLabel(" NAME: ", SwingConstants.CENTER);
	private static JLabel playerNameLabel = new JLabel("Test" );
	
	//Current Player's Color
	private static JLabel colourTitleLabel = new JLabel(" COLOUR: ", SwingConstants.CENTER);
	private static JLabel colourLabel = new JLabel("Colour");
	
	//Tickets Display 
	private static JLabel ticketsTitleLabel = new JLabel(" TICKETS ", SwingConstants.CENTER);
	private static JScrollPane ticketsScrollPane = new JScrollPane(new JList()); 
	
	//Train Cards
	private static JLabel trainCardsTitleLabel = new JLabel(" TRAIN CARDS ", SwingConstants.CENTER);
	private static JLabel[] trainCardsColorArray = new JLabel[9];
	private static JLabel[] trainCardsColorNumArray = new JLabel[9];
	private static int[] cardsColorNumArray = new int[9];
	
	//Number of Trains 
	private static JLabel numTrainsTitleLabel = new JLabel(" NUMBER OF TRAINS: ", SwingConstants.CENTER);
	private static JLabel numTrainsLabel = new JLabel("0"); 
	
	//Claim Route Button 
	public static JButton claimRouteButton = new JButton("CLAIM ROUTE");
	
	//Next Route Buttons 
	public static JButton nextTurnButton = new JButton("NEXT TURN");

	public PlayerPanel() {
		setLayout(null);							// Null layout – place GUI objects using coordinates
		setBackground(Color.WHITE);	
		
		//Set Sizes for all the labels and buttons
		playerPanelTitleLabel.setBounds(200,5,120,75);  //(1400, 200, 520, 880) 
		
		playerTitleLabel.setBounds(75,20,50,75);
		playerNameLabel.setBounds(125,20,50,75);
		
		colourTitleLabel.setBounds(345,20,75,75);
		colourLabel.setBounds(420,20,50,75);
		
		ticketsTitleLabel.setBounds(25,50,75,75);
		ticketsScrollPane.setBounds(25,100,470,200);
		
		trainCardsTitleLabel.setBounds(25,300,100,75);
		
		//Add the train card labels and its values
		addTrainCardsColor();
		addTrainCardsColorNum();
		
		numTrainsTitleLabel.setBounds(225,300,150,75);
		numTrainsLabel.setBounds(400,300,50,75);
		
		claimRouteButton.setBounds(350,400,150,75);
		
		nextTurnButton.setBounds(350,500,150,75);
		
		//Add all the labels, buttons and scroll pane to the panel
		add(playerPanelTitleLabel);
		add(playerTitleLabel);
		add(playerNameLabel);
		add(colourTitleLabel);
		add(colourLabel);
		add(ticketsTitleLabel);
		add(ticketsScrollPane);
		add(ticketsTitleLabel);
		add(trainCardsTitleLabel);
		add(numTrainsTitleLabel);
		add(numTrainsLabel);
		add(claimRouteButton);
		add(nextTurnButton);
		
		
		
		
	}



	//Setters and Getters

	public JLabel getPlayerPanelTitleLabel() {
		return playerPanelTitleLabel;
	}

	public void setPlayerPanelTitleLabel(JLabel playerPanelTitleLabel) {
		this.playerPanelTitleLabel = playerPanelTitleLabel;
	}

	public JLabel getPlayerTitleLabel() {
		return playerTitleLabel;
	}

	public void setPlayerTitleLabel(JLabel playerTitleLabel) {
		this.playerTitleLabel = playerTitleLabel;
	}

	public JLabel getPlayerNameLabel() {
		return playerNameLabel;
	}

	public void setPlayerNameLabel(JLabel playerNameLabel) {
		this.playerNameLabel = playerNameLabel;
	}

	public JLabel getColourTitleLabel() {
		return colourTitleLabel;
	}

	public void setColourTitleLabel(JLabel colourTitleLabel) {
		this.colourTitleLabel = colourTitleLabel;
	}

	public JLabel getColourLabel() {
		return colourLabel;
	}

	public void setColourLabel(JLabel colourLabel) {
		this.colourLabel = colourLabel;
	}

	public JLabel getTicketsTitleLabel() {
		return ticketsTitleLabel;
	}

	public void setTicketsTitleLabel(JLabel ticketsTitleLabel) {
		this.ticketsTitleLabel = ticketsTitleLabel;
	}

	public JScrollPane getTicketsScrollPane() {
		return ticketsScrollPane;
	}

	public void setTicketsScrollPane(JScrollPane ticketsScrollPane) {
		this.ticketsScrollPane = ticketsScrollPane;
	}

	public JLabel getTrainCardsTitleLabel() {
		return trainCardsTitleLabel;
	}

	public void setTrainCardsTitleLabel(JLabel trainCardsTitleLabel) {
		this.trainCardsTitleLabel = trainCardsTitleLabel;
	}

	public static JLabel[] getTrainCardsColorArray() {
		return trainCardsColorArray;
	}

	public void setTrainCardsColorArray(JLabel[] trainCardsColorArray) {
		this.trainCardsColorArray = trainCardsColorArray;
	}

	public static JLabel[] getTrainCardsColorNumArray() {
		return trainCardsColorNumArray;
	}

	public void setTrainCardsColorNumArray(JLabel[] trainCardsColorNumArray) {
		this.trainCardsColorNumArray = trainCardsColorNumArray;
	}

	public JLabel getNumTrainsTitleLabel() {
		return numTrainsTitleLabel;
	}

	public void setNumTrainsTitleLabel(JLabel numTrainsTitleLabel) {
		this.numTrainsTitleLabel = numTrainsTitleLabel;
	}

	public JLabel getNumTrainsLabel() {
		return numTrainsLabel;
	}

	public void setNumTrainsLabel(JLabel numTrainsLabel) {
		this.numTrainsLabel = numTrainsLabel;
	}

	public int[] getCardsColorNumArray() {
		return cardsColorNumArray;
	}
	
	public void setCardsColorNumArray(int[] cardsColorNumArray) {
		this.cardsColorNumArray = cardsColorNumArray;
	}

	public static JButton getClaimRouteButton() {
		return claimRouteButton;
	}

	public void setClaimRouteButton(JButton claimRouteButton) {
		this.claimRouteButton = claimRouteButton;
	}

	public JButton getNextTurnButton() {
		return nextTurnButton;
	}

	public void setNextTurnButton(JButton nextTurnButton) {
		this.nextTurnButton = nextTurnButton;
	}
	
	//Utility Methods 
	
	//Display Train Card Color Names
	private void addTrainCardsColor() {
	
		int x = 0; 
	
		for(CardColour trainColour : CardColour.values())
		{
			
			trainCardsColorArray[x] = new JLabel(trainColour.toString() + ":");
			trainCardsColorArray[x].setBounds(50, 325 + 25 * x ,150, 75);
			add(trainCardsColorArray[x]);
			x++;

		}
		
		
	}
	//Display Number of different train cards
	private void addTrainCardsColorNum() {
		
		int x = 0; 
	
		for(CardColour trainColour : CardColour.values())
		{
			
			trainCardsColorNumArray[x] = new JLabel("0"); 
			trainCardsColorNumArray[x].setBounds(175, 325 + 25 * x ,150, 75);
			add(trainCardsColorNumArray[x]);
			x++;

		}

		
	}
	
	
	
	
	
	//This method initializes the train card color value labels after player collects a train card
	public void initializeColorNumArray() {
		
		for (int index = 0; index < trainCardsColorNumArray.length; index++) {
			
			cardsColorNumArray[index] = TTRController.getCurrentPlayer().getNumOfCards()[index];
			
		}
		
	}
	
	public void addTrainCardsColorNumNewTallies() { 
		
		for(int index = 0; index < trainCardsColorNumArray.length; index++) {
			
			trainCardsColorNumArray[index] = new JLabel(String.valueOf(cardsColorNumArray[index]));
			trainCardsColorNumArray[index].setBounds(175, 325 + 25 * index ,150, 75);
			add(trainCardsColorNumArray[index]);

		}
		
	}
	
	//This method initializes the new tickets info in the scroll pane after player collects a new ticket
	public void initializeTickets() {
	
		ticketsScrollPane = new JScrollPane(new JList(TTRController.getCurrentPlayer().getTickets().toArray())); 
	
		
	}
	
	//This method initializes the current player's name
	public void initializeCurrentPlayerName() {
		
		playerNameLabel = new JLabel(TTRController.getCurrentPlayer().getPlayerName());
	}
	
	//This method initializes the current player's color
	public void initializeCurrentPlayerColor() {
		
		colourLabel = new JLabel(TTRController.getCurrentPlayer().getColour().toString());
	}
	
	public void initializaNumTrains () {
		
		numTrainsLabel = new JLabel(String.valueOf(TTRController.getCurrentPlayer().getNumOfTrains()));
		
	}
	
	
	//Repaints player panel
	public static void repaintPlayerPanel() {
		
		GameFrame.playerPanel.removeAll();
		
		playerPanelTitleLabel.setBounds(200,5,120,75);  //(1400, 200, 520, 880) 
		
		playerTitleLabel.setBounds(75,20,50,75);
		playerNameLabel.setBounds(125,20,50,75);
		
		colourTitleLabel.setBounds(345,20,75,75);
		colourLabel.setBounds(420,20,50,75);
		
		ticketsTitleLabel.setBounds(25,50,75,75);
		ticketsScrollPane.setBounds(25,100,470,200);
		
		trainCardsTitleLabel.setBounds(25,300,100,75);
		
		//Add the train card labels and its values
		GameFrame.playerPanel.addTrainCardsColor();
		GameFrame.playerPanel.addTrainCardsColorNumNewTallies();
		
		numTrainsTitleLabel.setBounds(225,300,150,75);
		numTrainsLabel.setBounds(400,300,50,75);
		
		claimRouteButton.setBounds(350,400,150,75);
		
		nextTurnButton.setBounds(350,500,150,75);		//725
		
		//Add all the labels, buttons and scroll pane to the panel
		GameFrame.playerPanel.add(playerPanelTitleLabel);
		GameFrame.playerPanel.add(playerTitleLabel);
		GameFrame.playerPanel.add(playerNameLabel);
		GameFrame.playerPanel.add(colourTitleLabel);
		GameFrame.playerPanel.add(colourLabel);
		GameFrame.playerPanel.add(ticketsTitleLabel);
		GameFrame.playerPanel.add(ticketsScrollPane);
		GameFrame.playerPanel.add(ticketsTitleLabel);
		GameFrame.playerPanel.add(trainCardsTitleLabel);
		GameFrame.playerPanel.add(numTrainsTitleLabel);
		GameFrame.playerPanel.add(numTrainsLabel);
		GameFrame.playerPanel.add(claimRouteButton);
		GameFrame.playerPanel.add(nextTurnButton);
		
		GameFrame.playerPanel.revalidate();
		GameFrame.playerPanel.repaint();
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}