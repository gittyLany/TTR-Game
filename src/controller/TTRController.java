/*
 * Name: Katherine Chun, Vidhi Ruparel
 * Date: Sunday December 5th, 2021
 * Course: ICS4U1-02  Mr. Fernandes
 * Title: TTR Canada - controller - TTRController
 * Description: Handles the interactions between the controllers and the GUI's
 * Notes:
 */



//PACKAGE
package controller;



//IMPORTS
import model.*;
import view.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;





public class TTRController implements ActionListener, MouseListener {
	
	
// ===== INSTANCE VARIABLES ===========================================================================
	
	private static GameFrame gameFrame;
	
	private static TrainCardController trainCardController;
	
	private static TicketController ticketController;
	
	private static RouteController routeController = new RouteController();
	
	private static FileImportController fileImport = new FileImportController();
	
	private static Player [] players = new Player[4];					//Holds all the information for each player
	private static int [][] playerTemp = new int[4][9];
	public static Player currentPlayer = new Player();
	
	private static Route [] allRoutes = FileImportController.getRoutes();
	private static City [] allCities = FileImportController.getCities();
	private static Ticket [] allTickets = FileImportController.getTickets();
	
	private static final int ENDOFGAMENUM = 2;
	
	private static int indexOfCurrPlayer = 0;			//Player number we are currently on
	
	private static int moved = 0;					//Which move the player chooses during their turn
	
	private static int indexOfWinner = 0;
	
	private static int chosen = 0;
	
	private static MenuBarController menuBarController;
	
	private static MusicController musicController;
	
	private int numOfTies = 0;										//The number of ties for the longest route
	
	//private static PlayerPanel [] playerPanels = new PlayerPanel[4];
	
	
	
	
	
	
	
// ===== METHODS ===========================================================================
	
// ----- Constructor Method ---------------------------------------------------------------------------
	//Initializes each player and the game board itself
	//Creates a new deck of train cards
	//Creates a new deck of ticket cards
	public TTRController () {
		
		System.out.println("Step 1. Initialized!!");
		System.out.println("Step 2. Got to Constructor!!");
		
		int [] numOfCardsSetter = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		//Initializing each player
		setPlayer("Player 1", PlayerColour.YELLOW, new ArrayList<Ticket>(), new ArrayList<Route>(), numOfCardsSetter, 45, 0);
		setPlayer("Player 2", PlayerColour.GREEN, new ArrayList<Ticket>(), new ArrayList<Route>(), numOfCardsSetter, 45, 0);
		setPlayer("Player 3", PlayerColour.BLUE, new ArrayList<Ticket>(), new ArrayList<Route>(), numOfCardsSetter, 45, 0);
		setPlayer("Player 4", PlayerColour.RED, new ArrayList<Ticket>(), new ArrayList<Route>(), numOfCardsSetter, 45, 0);
		
		indexOfCurrPlayer = 0;
		
		System.out.println("Step 3. All players set!!");
		
		//Creates a new GameFrame and displays the game
		gameFrame = new GameFrame();
		System.out.println("Step 4. GameFrame is online!!");
		
		// Starts the music
		musicController = new MusicController();
		musicController.gameTheme();
		
		// Creates the menu bar controller (calls the constructor)
		menuBarController = new MenuBarController();
		
		//Creates a new TrainCardController and creates a new deck of train cards
		trainCardController = new TrainCardController();
		System.out.println("Step 5. Train Cards are shuffled!!");
		System.out.println("Step 6. Each player has 4 cards now!!");
		
		//After dealing out 4 train cards to each player,
		//it then takes the next 5 cards from the top of
		//the deck and displays them in the card panel
		TrainCardController.flipAll();
		System.out.println("Step 7. 5 train cards are now visible!!");
		
		boolean firstRound = true;		//Indicates if it's the first round
		
		ticketController = new TicketController();
		
		//Goes through the beginning of the game
		//for each player
		do {
			
			//Sets the current player
			currentPlayer = players[indexOfCurrPlayer];
			
			//playerPanelDisplay();
			
			//Goes through the first round sequence
			firstRoundSequence();
			
			playerTemp[indexOfCurrPlayer] = players[indexOfCurrPlayer].getNumOfCards();
			
			playerPanelDisplay();
			
			System.out.println("\n\n\n" + players[0].toString());
			System.out.println("\n" + players[1].toString());
			System.out.println("\n" + players[2].toString());
			System.out.println("\n" + players[3].toString());
			
			//Once the indexOfCurrPlayer is 0, that means
			//the firstRoundSequence of player 4 has been
			//carried out successfully and that the first
			//round has been finished for each player
			if (indexOfCurrPlayer == 0)
				firstRound = false;
			
		} while (firstRound);
		
		System.out.println("Step 8. Tickets have been selected!!");
		
		//Displays that it's the end of the start up sequence
		String message = "Game Ready!!";
		JOptionPane.showMessageDialog(null, message);
		
		//Begins the main game play for the first player
		playGame();
		
	} //End of constructor method
	
	
	
// ----- playGame Method ---------------------------------------------------------------------------
	//The main game play
	//It enables all of the buttons and allows the user to make their move
	public void playGame () {
		
		//Goes through the rest of the game
		
		System.out.println("IM INDEX: " + indexOfCurrPlayer);
		
		//Sets the current player
		currentPlayer = players[indexOfCurrPlayer];
		
		//Displaying each player's panel
		playerPanelDisplay();
		
		//Displays a new pop up window each time it's a new player's turn
		String message = players[indexOfCurrPlayer].getPlayerName() + "'s Turn";
		JOptionPane.showMessageDialog(null, message);
		
		//Enables the actionListenter for each of the buttons
		TrainCardController.cardDeckButton.addActionListener(this);
		TrainCardController.ticketCardButton.addActionListener(this);
		PlayerPanel.claimRouteButton.addActionListener(this);
		PlayerPanel.nextTurnButton.addActionListener(this);
		
		for (int index = 0; index < TrainCardController.cardsArray.length; index++)
			TrainCardController.cardsArray[index].addActionListener(this);
		
		PlayerPanel.repaintPlayerPanel();
			
	} //End of playGame method
	
	
	
// ----- endGame Method ---------------------------------------------------------------------------
	//Determines whether the game should end
	//Game ends when any player reaches a certain amount of trains
	private boolean endGame () {
		
		if(players[indexOfCurrPlayer].getNumOfTrains() <= ENDOFGAMENUM)
			return true;
			
		return false;
		
	} //End of endGame method
	
	
	
// ----- firstRoundSequence Method ---------------------------------------------------------------------------
	//Goes through the first round sequence of each player
	//The first round in when each player selects the 2+ tickets they
	//Want to keep
	private void firstRoundSequence () {
		
		switch (indexOfCurrPlayer) {
		
		case 0:
			TrainCardController.startDeal(indexOfCurrPlayer);
			TicketController.getInitialTickets(indexOfCurrPlayer);
			players[indexOfCurrPlayer].setTickets(TicketController.getPlayer1Tickets());
			indexOfCurrPlayer = 1;
			break;
			
		case 1:
			TrainCardController.startDeal(indexOfCurrPlayer);
			TicketController.getInitialTickets(indexOfCurrPlayer);
			players[indexOfCurrPlayer].setTickets(TicketController.getPlayer2Tickets());
			indexOfCurrPlayer = 2;
			break;
		
		case 2:
			TrainCardController.startDeal(indexOfCurrPlayer);
			TicketController.getInitialTickets(indexOfCurrPlayer);
			players[indexOfCurrPlayer].setTickets(TicketController.getPlayer3Tickets());
			indexOfCurrPlayer = 3;
			break;
		case 3:
			TrainCardController.startDeal(indexOfCurrPlayer);
			TicketController.getInitialTickets(indexOfCurrPlayer);
			players[indexOfCurrPlayer].setTickets(TicketController.getPlayer4Tickets());
			indexOfCurrPlayer = 0;
			break;
		default:
			indexOfCurrPlayer = -1;
		
		} //End of switch statement
		
	} //End of firstRoundSequence method
	
	
	
// ----- playerPanelDisplay Method ---------------------------------------------------------------------------
	//Displays each player's panel when it is their turn
	public static void playerPanelDisplay () {
		
		gameFrame.getPlayerPanel().initializeCurrentPlayerName();
		gameFrame.getPlayerPanel().initializeCurrentPlayerColor();
		gameFrame.getPlayerPanel().initializeColorNumArray();
		gameFrame.getPlayerPanel().initializeTickets();
		gameFrame.getPlayerPanel().initializaNumTrains();

		gameFrame.getScorePanel().setPlayerColour(players[indexOfCurrPlayer].getColour().toString());
		
		gameFrame.getPlayerPanel().repaintPlayerPanel();

		gameFrame.scorePanel.repaintAll();
		gameFrame.scorePanel.displayScores();
		
		gameFrame.add(gameFrame.playerPanel);
		gameFrame.add(gameFrame.scorePanel);
		
	} //End of playerPanelDisplay method
	
	
	
// ----- endSequence Method ---------------------------------------------------------------------------
	//Conducts the end sequence of the game
	//The end sequence is when the longest route points are awarded to player(s)
	//It also determines the winner of the game
	private void endSequence() {
		
		//longestRoute();
		determineWinner(numOfTies);
		
	} //End of endSequence method
	
	
	//Music
	public static MusicController getMusic() {
		
		return musicController;
		
	}
	
	// ----- longestRoute Method ---------------------------------------------------------------------------
		//Determines which player has the longest route and adds 10 extra points to the player(s)
		private void longestRoute() {
			
			int [] playersLongestRouteLength = new int[4];			//Holds the longest route of each player
			int [] playersLongestRouteLengthCopy = new int[4];		//Copy of the longest route of each player
			
			//Going through each player and determining the longest route length
			//of each player
			for (int index = 0; index < playersLongestRouteLength.length; index++)
				playersLongestRouteLength[index] = playersLongestRoute(index);
				
			
			//Creating a copy of the each player's longest route length so that points can be added
			//to the right player accordingly
			playersLongestRouteLengthCopy = Arrays.copyOf(playersLongestRouteLength, playersLongestRouteLength.length);
			
			//Sorting the longest routes of each player from longest to shortest
			Arrays.sort(playersLongestRouteLength);
			
			//Determining if there are any ties between the players
			for (int index = 3; index > 0; index--) {
				
				if (playersLongestRouteLength[index] == playersLongestRouteLength[index - 1])
					numOfTies++;
				
			} //End of of for loop (Checking for longest route ties)
			
			//If there are no ties, 
			//then it searches for the player with the longest score and adds 10 points
			//to the player's score
			if (numOfTies <= 3) {
				for (int index = 1; index < players.length; index++) {
					
					if (playersLongestRouteLength[3] == playersLongestRouteLengthCopy[index])
						players[index].setPlayerScore(players[index].getPlayerScore() + 10);;
					
				} //End of for loop
				
			//If each player is tied for the longest route, then each player
			//gets an extra 10 points
			} else {
				
				for (int index = 0; index < players.length; index++)
					players[index].setPlayerScore(players[index].getPlayerScore() + 10);
					
			} //End of else if statement
			
		} //End of longestRoute method
		
		
		
	// ----- playersLongestRoute Method ---------------------------------------------------------------------------
		//Determines the longest route of each player
		private int playersLongestRoute(int indexOfCurrPlayer) {
			
			int maxLength = players[indexOfCurrPlayer].getRoutes().size();
			ArrayList<Route> routesClaimed = players[indexOfCurrPlayer].getRoutes();
			int [] routeLengths = new int[maxLength];
			int index = 0;
			
			for (Route currRoute : routesClaimed) {
				
				routeLengths[index] = longestConnection(routesClaimed, currRoute);
				
				index++;
				
			} //End of for loop
			
			Arrays.sort(routeLengths);
			
			return routeLengths[maxLength - 1];
			
		} //End of playersLongestRoute method
		
		
		
	// ----- longestConnection Method ---------------------------------------------------------------------------
		//Determines the longest connection that one route can make as the start/end route
		private int longestConnection (ArrayList<Route> routesClaimed, Route currRoute) {
			
			boolean connection = true;		//Determines if there is a connection between routes
			int longestConnection = 0;		//Keeps track of the longest connection between routes
			String sourceCity = currRoute.getSourceCity();
			String destinationCity = currRoute.getDestinationCity();
			
			//While there is still a connection between routes
			do {
				
				//Going through each of the player's claimed routes and checking if there is a connecting city
				for (Route claimed : routesClaimed) {
					
					if ((claimed.getSourceCity().equals(sourceCity) || claimed.getSourceCity().equals(destinationCity))
							|| claimed.getDestinationCity().equals(sourceCity) || claimed.getDestinationCity().equals(destinationCity))
						longestConnection++;
					else 
						connection = false;
						
				} //End of for loop
			
			} while (connection);
			
			//Returns the longest connection the route can make
			return longestConnection;
			
		} //End of longestConnection method
	
	
	
		// ----- determineWinner Method ---------------------------------------------------------------------------
		//Determines who the winner is by comparing each player's score
		public void determineWinner(int numOfTies) {
			
			String playersWon = "";
			
			System.out.println("DETERMINE WINNER - START");
			
			if (numOfTies == 0) {
			
				//Going through each player's score and comparing it with one another 
				//If a player's score is higher than the current highest score, then 
				//the player's score is now set as the current highest score
				for (int index = 1; index < players.length; index++) {
					
					if (players[indexOfWinner].getPlayerScore() < players[index].getPlayerScore())
						indexOfWinner = index;
					
				} //End of for loop
				
				playersWon = players[indexOfWinner].getPlayerName();
				
			} else if (numOfTies >= 1 && numOfTies < 4) {
					
				for (int index = 1; index < players.length; index++) {
					
					if (players[indexOfWinner].getPlayerScore() < players[index].getPlayerScore())
						indexOfWinner = index;
					
				} //End of for loop
				
				for (int index = 0; index < players.length; index++) {
					
					if (players[indexOfWinner].getPlayerScore() == players[index].getPlayerScore()) {
						
						if (index == 3)
							playersWon += "and " + players[index].getPlayerName();
							
						else
							playersWon += players[index].getPlayerName() + ", ";
						
					}
					
				} //End of for loop
				
			} else {
				
				for (int index = 0; index < players.length; index++) {
					
					if (index == 3)
						playersWon += "and " + players[index].getPlayerName();
						
					else
						playersWon += players[index].getPlayerName() + ", ";
					
				} //End of for loop
				
			} //End if/else if/else statement
			
			//Prints a message informing the players that the game has ended
			String exitMessage = "Game End\n" + playersWon + " has won the game.\nCongratulations " + players[indexOfWinner].getPlayerName();
			
			Object [] options = {"Ok"};
			
			int exitGame = JOptionPane.showOptionDialog(null, exitMessage, "Game End", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			
			//Once the player clicks "Ok" it shuts down the entire operation
			if (exitGame == JOptionPane.OK_OPTION) {
				
				System.out.println("DETERMINE WINNER - START");
				
				System.exit(0);
				
			}
			
		} //End of determineWinner method
	
	
	
	
	
	
// ===== GET & SET METHODS ===========================================================================
	
	public static void setPlayer
	(String playerName, PlayerColour playerColour, ArrayList<Ticket> tickets, ArrayList<Route> routes, 
			int[] numOfCards, int numOfTrains, int playerScore) {
		
		 Player player = new Player(playerName, playerColour, tickets, routes, numOfCards, numOfTrains, playerScore);
		 
		 players[indexOfCurrPlayer] = player;
		 
		 indexOfCurrPlayer++;
		
	} //End of setPlayer method
	
	public static void setPlayerAt (int indexOfCurrPlayer, Player player) {
		
		players[indexOfCurrPlayer] = player;
		
	} //End of setPlayerAt method
	
	public static Player getPlayer(int indexOfCurrPlayer) {
		
		return players[indexOfCurrPlayer];
		
	} //End of getPlayer method
	
	
// ----- Current Player Method ---------------------------------------------------------------------------
	//
	public static void setCurrentPlayer (int playerNum) {
		
		currentPlayer = players[playerNum];
		
	} //End of setCurrentPlayer method
	
	
	public static Player getCurrentPlayer () {
		
		return currentPlayer;
		
	} //End of getCurrentPlayer method
	
	
	
// ----- Game Frame Method ---------------------------------------------------------------------------
	//
	public static void setGameFrame (GameFrame newGameFrame) {
		
		gameFrame = newGameFrame;
		
	} //End of setGameFrame method
	
	
	public static GameFrame getGameFrame () {
		
		return gameFrame;
		
	} //End of getGameFrame method
	
	
	
// ----- Player Array Method ---------------------------------------------------------------------------
	//
	public static Player[] getPlayerArray () {
		
		return players;
		
	} //End of getPlayerArray method
	
	
	
// ----- File Import Method ---------------------------------------------------------------------------
	//
	public static FileImportController getFileImport () {
		
		return fileImport;
		
	} //End of getFileImportController method
	
	
	
// ----- All Routes Method ---------------------------------------------------------------------------
	//
	public static void setAllRoutes (Route [] routes) {
		
		allRoutes = routes;
		
	} //End of setAllRoutes method
	
	
	public static Route [] getAllRoutes () {
		
		return allRoutes;
		
	} //End of getAllRoutes method
	
	
	
// ----- All Cities Method ---------------------------------------------------------------------------
	//
	public static void setAllCities (City [] cities) {
		
		allCities = cities;
		
	} //End of setAllRoutes method
	
	
	public static City [] getAllCities () {
		
		return allCities;
		
	} //End of getAllRoutes method
	
	
	

// ----- All Tickets Method ---------------------------------------------------------------------------
	//
	public static void setAllTickets (Ticket [] tickets) {
		
		allTickets = tickets;
		
	} //End of setAllRoutes method
	
	
	public static Ticket [] getAllTickets () {
		
		return allTickets;
		
	} //End of getAllRoutes method
	
	
	
	
// ===== UTILITY METHODS ===========================================================================
	
// ----- actionPerformed Method ---------------------------------------------------------------------------
	//Determines what to do when each button is clicked
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//Enables all the buttons once again after they are diabled the last time
		enableElements();
		
		playerPanelDisplay();
		
		//When either the card deck button or any of the train card buttons are clicked ~ Tiffany
		if(event.getSource() == TrainCardController.cardDeckButton 
				|| event.getSource() == TrainCardController.cardsArray[0] 
						|| event.getSource() == TrainCardController.cardsArray[1]
								|| event.getSource() == TrainCardController.cardsArray[2]
										|| event.getSource() == TrainCardController.cardsArray[3]
												|| event.getSource() == TrainCardController.cardsArray[4]) {
			
			TrainCardController.ticketCardButton.removeActionListener(this);
			PlayerPanel.claimRouteButton.removeActionListener(this);
			
			// Chose the card deck
			if (event.getSource() == TrainCardController.cardDeckButton) {
	
				TrainCardController.dealCards("deck", -1, indexOfCurrPlayer); // Spot variable is only for if a card was chosen from the display area
				
				chosen++;
				
				currentPlayer = players[indexOfCurrPlayer];
				
				playerPanelDisplay();
	
				// ends the if statement for choosing a card from the card deck
	
			} //End of if statement (cardDeckButton)
	
			// if the user clicks on a card on the display
			else if (event.getSource() instanceof TrainCard) {
				
				int cardSpot = 0;
	
				for (TrainCard trainCard : TrainCardController.cardsArray) {
	
					if (event.getSource() == trainCard) {
						
						TrainCardController.dealCards("display", cardSpot, indexOfCurrPlayer);
					
						chosen++;
						
						currentPlayer = players[indexOfCurrPlayer];
						
						playerPanelDisplay();
						
					}
					
					cardSpot++;
					
				} //End of for loop
	
			} // ends the if statement for choosing a card from the display deck
			
			// Repaint the card panel
			gameFrame.getCardPanel().revalidate();
			gameFrame.getCardPanel().repaint();
			
			if (chosen >= 2) {
		
				disableElements();
				
				chosen = 0;
				moved = 1;
				
				JOptionPane.showMessageDialog(null, "You can not make any more moves this turn. Please click the 'NEXT TURN' button.");
	
			} //If the amount of cards that they've clicked is larger than 2, then you can't click any more cards
			
		//When the ticketCard Button is clicked
		} else if (event.getSource() == TrainCardController.ticketCardButton) {
			
			TrainCardController.cardDeckButton.removeActionListener(this);
			PlayerPanel.claimRouteButton.removeActionListener(this);
			
			for (int index = 0; index < TrainCardController.cardsArray.length; index++)
				TrainCardController.cardsArray[index].removeActionListener(this);
			
			TicketController.turn = JOptionPane.OK_OPTION;
			
			TicketController.ticketCardButton(indexOfCurrPlayer);
			
			if (TicketController.turn == JOptionPane.OK_OPTION) {
				
				TicketController.playerTickets.clear();
				
				ArrayList<Ticket> newTickets = new ArrayList<Ticket>();
				
				switch (indexOfCurrPlayer) {
				
				case 0:
					newTickets = TicketController.getPlayer1Tickets();
					break;
				case 1:
					newTickets = TicketController.getPlayer2Tickets();
					break;
				case 2:
					newTickets = TicketController.getPlayer3Tickets();
					break;
				case 3:
					newTickets = TicketController.getPlayer4Tickets();
					break;
				
				} //End of switch statement
				
				ArrayList<Ticket> currTicket =  TTRController.getCurrentPlayer().getTickets();
				
				for (Ticket addingTicket : newTickets)
					currTicket.add(addingTicket);
			
				players[indexOfCurrPlayer].setTickets(currTicket);
				
				currentPlayer = players[indexOfCurrPlayer];
				
				playerPanelDisplay();
				
				disableElements();
				
				moved = 1;
				
				JOptionPane.showMessageDialog(null, "You can not make any more moves this turn. Please click the 'NEXT TURN' button.");
					
			} //End of if statement
			
		//When the claimed route button is clicked
		} else if (event.getSource() == PlayerPanel.claimRouteButton) {
			
			//Call the RouteController class for what happens
			RouteController.claimRoute();
			
			currentPlayer = players[indexOfCurrPlayer];
			
			playerPanelDisplay();
			
			//If a route was selected
			if(RouteController.somethingSelected)
				moved = 1;
			
			//If there are no routes available, the player is not allowed
			//to click the claim route button again
			if(RouteController.noRoutesAvailable)
				PlayerPanel.claimRouteButton.removeActionListener(this);
			
			//Should appear after every time the player claims a route
			if (endGame())
				endSequence();
			else
				disableElements();
			
		} //End of if/else if statement
		
		//When the next turn button is clicked
		if (event.getSource() == PlayerPanel.nextTurnButton) {
			
			int skipTurn = 0;
			
			if (moved == 0)
				skipTurn = JOptionPane.showConfirmDialog(null, "Are you sure you want to skip your turn?", "Skip Turn", JOptionPane.YES_NO_OPTION);
			
			if (skipTurn == JOptionPane.YES_OPTION || moved > 0) {
				
				System.out.println("HELLOOOOO!!!!");
				
				if (indexOfCurrPlayer == 3)
					indexOfCurrPlayer = 0;
				
				else
					indexOfCurrPlayer++;
				
				moved = 0;
				
				PlayerPanel.nextTurnButton.removeActionListener(this);
				
				playGame();
				
			} //End of if statement (Skip Turn)
			
		} //End of if statement (nextTurnButton)
		
		
		
		//moveOption == 1; If any of the train cards or train card deck is clicked
		//moveOption == 2; If the ticket deck is clicked
		//moveOption == 3; If the claim route button is clicked
		if (moved > 0) {
			
			TrainCardController.ticketCardButton.removeActionListener(this);
			PlayerPanel.claimRouteButton.removeActionListener(this);
			TrainCardController.cardDeckButton.removeActionListener(this);
			
			for (int index = 0; index < TrainCardController.cardsArray.length; index++)
				TrainCardController.cardsArray[index].removeActionListener(this);
			
		} //End of if/else if statement (Player Move Decision)
		
		
	} //End of actionPerformed method
	
	
	
	


// ----- actionPerformed Method ---------------------------------------------------------------------------
	//
	@Override
	public void mouseClicked(MouseEvent e) {
		
	} //End of mouseClicked method


// ----- mousePressed Method ---------------------------------------------------------------------------
	//
	@Override
	public void mousePressed(MouseEvent e) {
		
	} //End of mousePressed method


// ----- mouseReleased Method ---------------------------------------------------------------------------
	//
	@Override
	public void mouseReleased(MouseEvent e) {
		
	} //End of mouseReleased method


// ----- mouseEntered Method ---------------------------------------------------------------------------
	//
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}//End of mouseEntered method

	
// ----- mouseExited Method ---------------------------------------------------------------------------
	//
	@Override
	public void mouseExited(MouseEvent e) {
		
	} //End of mouseExited method
	
	
// ----- disableElements Method ---------------------------------------------------------------------------
	//
	public static void disableElements() {
		
		gameFrame.getCardPanel().getTicketDeckButton().setEnabled(false);
		gameFrame.getCardPanel().getCardDeckButton().setEnabled(false);
		gameFrame.getPlayerPanel().getClaimRouteButton().setEnabled(false);
		
		for (JButton trainCardButton : gameFrame.getCardPanel().getTrainCardsArray())
			trainCardButton.setEnabled(false);
		
	} //End of disableElements method
	
	
// ----- disableElements Method ---------------------------------------------------------------------------
	//
	public static void enableElements() {
		
		gameFrame.getCardPanel().getTicketDeckButton().setEnabled(true);
		gameFrame.getCardPanel().getCardDeckButton().setEnabled(true);
		gameFrame.getPlayerPanel().getClaimRouteButton().setEnabled(true);
		
		for (JButton trainCardButton : gameFrame.getCardPanel().getTrainCardsArray())
			trainCardButton.setEnabled(true);
		
	} //End of enableElements method
	
	

	
	
	
} //End of TTRController class