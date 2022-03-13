// /* Names: Tiffany
// * Date: December 5th, 2021
// * Course: ICS4U1 - Mr. Fernandes
// */
//
//package controller;
//
//
//
//
////===== IMPORTS ===================================================================================
//import java.util.Random;
//
//import javax.swing.JOptionPane;
//
//import java.util.ArrayList;
//
//import model.CardColour;
//import model.Player;
//import model.PlayerColour;
//import model.Route;
//import model.Ticket;
//import model.TrainCard;
//import view.CardPanel;
//
//
//
//
//
//
//public class ComputerPlayerController extends Player {
//	
//
//// ===== INSTANCE VARIABLES ========================================================================
//	
//	// Random Object
//	Random random = new Random();	// Creates a "Random" object
//	
//	// JOptionPane
//	private JOptionPane popUpMessage;
//	// ask katherine to create an option to pick ai or just manually code it into the TTRController as the 4th player
//	
//	
//	
//	
//	
//// ===== METHODS ===================================================================================
//
//	// ----- Constructor Method ~  ----------------------------- -----------------------------------
//	public ComputerPlayerController(String playerName, PlayerColour colour, ArrayList<Ticket> tickets, 
//			ArrayList<Route> routes, int [] numOfCards, int numOfTrains, int playerScore) {
//
//		// Send information to the player superclass
//		super(playerName, colour, tickets, routes, numOfCards, numOfTrains, playerScore);
//		
//		// Calls the method that determines what move the computer will make
//		getChoice();
//		
//	}
//	
//	
//	// ----- getChoice Method ~ Game turn --> game 1/3 possible options -----------------------------
//	private void getChoice() {
//		
//		int choice;			// Represents what the computer player does this turn 
//		
//		int numTrains = 40;	// Represents the the lowest number of trains in the game, to base how far into the game the players are
//		
//		// Gets a value using the random object
//		choice = random.nextInt(5) + 1; // Creates a random integer from 1-5 inclusive
//		
//		// Distribute the weighting based on how far into the game this round is (based on # of trains left)
//		// Find which player has the least number of trains -- what to base it on
//		// run through the array or list of players
//		for (Player player : TTRController.getPlayers()) {
//		  
//		  	// Assign the number of trains the player has to the numTrains variable if it is lower then the current lowest
//		  	if (numTrains > player.getNumOfTrains())
//		  		numTrains = player.getNumOfTrains();
//		  
//		 }
//		
//		/* Higher weighting for getting train cards, as those allow one to collect routes.
//		 * The choice of using the turn to get ticket cards is more likely in the earlier stages of the game vs
//		 * the increase in possibility of choosing to claim a route near the later portion of the game.
//		 * (Need to use up assets(by claiming routes) in order to connect cities and win)
//		 */
//		 
//		// Counts as early on in the game
//		if (numTrains >= 30) {
//		 	 
//			// Calls the method that performs the turn
//			if (choice == 1)
//		  		collectRoute();
//			  
//		  	else if (choice == 2 || choice == 3)
//		  		pickTicketCards(1);
//			  
//		  	else if (choice == 4 || choice == 5)
//		  		pickTrainCards();
//		  
//		  } // ends the early game if statement
//		  
//		  // Counts as later on in the game
//		  else if (numTrains < 30) {
//		 
//			// Calls the method that performs the turn
//			if (choice == 1 || choice == 2)
//	  		collectRoute();
//		  
//		  	else if (choice == 3)
//		  		pickTicketCards(1);
//			  
//		  	else if (choice == 4 || choice == 5)
//		  		pickTrainCards();
//			
//		} // ends the if statement - condition: later portion of the game
//	
//	} // ends the method getChoice
//
//
//
//// ----- Game Turn Options ----------------------------------------------------------------------------
//	
//	// ----- collect a route --------------------------------------------------------------------------
//	// [should not be a frequently chosen option during the first few moves]
//	private void collectRoute() {
//		
//		// Fields
//		int numRoutesToConnect = 0;
//		int routeNum = 0;
//
//		// Picks possible routes based on the train cards got
//		ArrayList<Route> possibleRoutes = TTRController.getRouteController().getPossibleRoutes();
//		int[] score = new int[possibleRoutes.size()];
//		
//		// If the possible routes are connected to a route the computer player already has, increase the score of that route
//		for (Route route : possibleRoutes) {
//			
//			// Checking through all the routes the player currently has
//			for (Route playerRoutes : TTRController.getCurrentPlayer().getRoutes()) {
//		
//				// Checks if the source or destination cities of the possible route match the source or destination of claimed route
//				if ((route.getSourceCity() == playerRoutes.getDestinationCity()) ||
//						(route.getDestinationCity() == playerRoutes.getDestinationCity()) ||
//						(route.getSourceCity() == playerRoutes.getSourceCity()) ||
//						(route.getDestinationCity() == playerRoutes.getSourceCity()))
//					++score[routeNum];	// Increment the score array at the index corresponding to the current possible route being checked
//			
//			} // ends enhanced for loop that increases the score of the corresponding route if that route is connected with one the player already claimed
//		
//			++routeNum;	// Increment the route number to show that the next possible route is being checked
//			
//		} // ends for loop that gives a score to match each possible route	
//		
//		// Put the routes with a higher score in the array list twice to double the chance of picking it to claim
//		for (int index = 0; index < score.length; index++) {
//			
//			// Higher score --> add route to array list the number of times = to its score
//			while (score[index] >= 0) {
//				
//				// Duplicate the number of times the route is in the array by adding it to the array list
//				possibleRoutes.add(possibleRoutes.get(index));
//				
//				--score[index];	// Decrement the score so the replication process occurs for the number of times = to its score
//				
//			} // ends the while loop --> replication route
//			
//		} // ends the for loop --> replication of possible routes
//		
//		// Choose a route from the possible options [higher weighting/probability to pick ones that build a connection]
//		int option = random.nextInt(possibleRoutes.size()); // Generates a random integer from 0-(array list size - 1) inclusive
//		
//		// Choose one of the routes to claim depending on the value of option
//		TTRController.getRouteController().claimRoute(possibleRoutes.get(option));
//		
//		// Display a message to show that the route has been claimed
//		JOptionPane.showMessageDialog(TTRController.getGameFrame(),
//			    "The " + possibleRoutes.indexOf(option) + " route was claimed.");
//	
//	} // ends the method to claim a route
//	
//	
//	// ----- pick ticket cards ---------------------------------------------------------------------
//	// [should not be chosen as often during the ending of the game~ more in the beginning]
//	private void pickTicketCards(int min) {
//	
//		// give the ticket card to the player (call TTRController.NAMEcontroller
//	
//		// Randomly choose which card(s) to pick
//		int numCards = random.nextInt(3) + 1;	// Creates a random integer from 1-3 inclusive
//		
//		// Picking the ticket cards until the numCards to pick left is 0		
//		// Pick starting from the top
//
//		// Select the top option
//			// Get the value of that first ticket
//			// Give that ticket to the player
//			// Display a message saying that the computer player has chosen their card(and the card that it is) - JOptionPane?
//			popUpMessage.showMessageDialog(TTRController.getGameFrame(),
//				    "A " + STH.toString() + " was selected.");
//							
//		--numCards; // After choosing a ticket card, decrement the number of cards left to be chosen 
//		
//
//		// Continue on if there are more cards to choose
//		// - since the player has to choose a minimum of 2 ticket cards at the start, if min is 2 keep going
//		if (numCards > 0 || min == 2) {
//			
//			// select the second option
//				// Get the value of the ticket
//				// Give that ticket to the player
//				// Display a message saying that the computer player has chosen their card(and the card that it is)
//				popUpMessage.showMessageDialog(TTRController.getGameFrame(),
//				    "A " + STH.toString() + " was selected.");
//			
//			--numCards; // After choosing a ticket card, decrement the number of cards left to be chosen 
//	
//			// Continue and select the last ticket card as well
//			if (numCards > 0) {
//				
//				// select third option
//					// Get the value of the ticket
//					// Give that ticket to the player
//					// Display a message saying that the computer player has chosen their card(and the card that it is)
//					popUpMessage.showMessageDialog(TTRController.getGameFrame(),
//					    "A " + STH.toString() + " was selected.");
//					
//			} // ends the if statement that takes care of selecting the last ticket card
//			
//		}	// ends the if statement that takes care of selecting more than one ticket card
//		
//		// Confirming the selections to get the ticket cards
//		
//	} // ends the method that picks (a) new ticket card(s)
//	
//	
//	// ----- pick train cards ----------------------------------------------------------------------
//	// repeat this process 2 times (pick 2 cards)
//	private void pickTrainCards() {
//	
//		// Fields
//		int numOfCardsChosen = 0;	// Keeps track of the number of train cards the player has chosen -- don't past maximum
//		int spot = 0;				// Keeps track of the placement of the card to pick form the display
//		
//		// If there is a locomotive/rainbow card, choose that
//		for (TrainCard cardType : CardPanel.getTrainCardsArray()) {
//			
//			if (cardType.getColor() == CardColour.RAINBOW) {
//				
//				// Pick the locomotive card ~ yay! Card got!
//				TTRController.getTrainCardController().dealCards("display", spot);
//				
//				++numOfCardsChosen;	// Increment the variable to account for another chosen card
//				++spot;				// Increment the variable to show that the next card on display is being looked at
//						
//				// Break out of the enhanced for loop if the maximum number of cards have been chosen
//				if (numOfCardsChosen >= 2)
//					break;
//				
//			} // ends the if statement that selects the card
//			
//		} // ends the enhanced for loop that checks to see if there is a locomotive card on the display
//		
//		// If there are still more cards that need to be chosen (have not reached the maximum of 2)
//		if (numOfCardsChosen <= 1) {
//	
//			// Decide whether or not to choose from deck or display
//			int deckOrDisplay = random.nextInt(2); 		// Generates an integer between 0-1 inclusive
//	
//			// Display -- choose which of the 5 to choose
//			if (deckOrDisplay == 0) {
//				
//				while(numOfCardsChosen < 2) {
//					
//					// Determine which spot on the display to pick the card
//					spot = random.nextInt(5); 			// Generates an integer between 0-4 inclusive
//					
//					// Pick the card from the display ~ yay! Card got!
//					TTRController.getTrainCardController().dealCards("display", spot);
//					
//					++numOfCardsChosen; // Increment the variable to account for another chosen card
//					
//				} // ends the while loop once the number of cards chosen reaches the maximum(2)
//				
//			} // ends the else statement that chooses train cards from the display
//			
//			// Deck -- draw a card from the top of the deck
//			else {
//				
//				while(numOfCardsChosen < 2) {
//					
//					// Draw a card from the deck ~ yay! Card got! Pop! (Goes the weasel)
//					TTRController.getTrainCardController().dealCards("deck", -1);
//					
//					++numOfCardsChosen; // Increment the variable to account for another chosen card
//					
//				} // ends the while loop once the number of cards chosen reaches the maximum(2)
//				
//			} // ends the else statement that chooses train cards from the deck
//			
//		} // ends the if statement that chooses a random card from the display or draws from the deck
//		
//	} // ends the pick train cards method
//	
//} // ends the class
