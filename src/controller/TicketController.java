/*
 * Name: Katherine Chun
 * Date: Sunday Devember 5th, 2021
 * Course: ICS4U1-02  Mr. Fernandes
 * Title: TTR Canada - controller - TicketController
 * Description: Generating three tickets for the player on their first turn. A pop window
 * 				will prompt the player to select at least two of the generated tickets. If
 * 				the player does not select at least two of the generated tickets, the game
 * 				will continue to prompt the player to select at least two tickets until
 * 				the player has selected at least two tickets. While the game is in play, the
 * 				TTRController is continually calling a method to check whether the ticket
 * 				has been completed yet. If the player's ticket has not been completed yet, 
 *				another method is called to determine if the two cities on the ticket are
 *				connected together.
 * Notes:
 */



//PACKAGE
package controller;



//IMPORTS
import model.*;
import java.util.*;
import javax.swing.*;



/**
 * Randomly generating the 3 initial tickets and asking the user to select at least two of the tickets to keep.
 * Determining if the ticket is complete and adding points for that ticket if necessary to the player's score.
 */
public class TicketController {
	
	
// ===== INSTANCE VARIABLES ===========================================================================
	
	// ----- FOR GAME START -----
	private static JCheckBox ticket1;				//Check box of one of the tickets generated for the player
	private static JCheckBox ticket2;				//Check box of one of the tickets generated for the plater
	private static JCheckBox ticket3;				//Check box of one of the tickets generated for the player
	
	private static String message = 						//Message displayed to the user in the JOptionPane
			"Select the tickets you wish to keep - "
			+ "you MUST keep at least 2 tickets.";
	
	public static ArrayList<Ticket> playerTickets = 				//ArrayList of the tickets that are drawn 
			new ArrayList<Ticket>();								//for the user at the beginning of the game
	
	//private static FileImportController files = 					//Obtaining the data that was imported in
			//TTRController.getFileImport();						//the FileImportController
	
	private static Ticket[] allTickets = 							//Obtaining all the tickets in the ticket deck
			TTRController.getAllTickets();
	
	private static Stack<Ticket> shuffledTickets = 					//The deck ticket cards to be used throughout
			new Stack<Ticket>();									//the game
	
	private static ArrayList<Ticket> 								//Stores the tickets that each player selected
	player1Tickets, player2Tickets, player3Tickets, player4Tickets;	
	
	
	// ----- FOR GAME PLAY -----
	private static ArrayList<Ticket> currentPlayerTickets = 		//Obtaining the tickets that the user
			new ArrayList<Ticket>();								//currently has in their possession
			//TicketControllerTest.player1.getTickets();			//Testing
			
	private static ArrayList<Route> currentPlayerRoutes = 			//Obtaining the current routes the user
			new ArrayList<Route>();									//has currently claimed	
			//TicketControllerTest.player1.getRoutes();				//Testing
			
	private static Route [] possibleRoutes = 						//Array of all the routes possible in the game
			TTRController.getAllRoutes();
	
	private static ArrayList<Ticket> checkedTickets = 				//The tickets that are finished being checked
			new ArrayList<Ticket>();								//These tickets will be added back to the 
																	//current player
	
	public static int turn = JOptionPane.OK_OPTION;
	
	//public static ArrayList<ArrayList<String>> previouslyVisitedCities = new ArrayList<ArrayList<String>>();
	
	
	
	
	
	
	
// ===== METHODS ===========================================================================

// ----- Constructor Method ---------------------------------------------------------------------------
	//Initializes the player's tickets by randomly drawing cards and asking the user to select at least two
	public TicketController () {
		
		for (Ticket ticket : allTickets)
			shuffledTickets.push(ticket);
		
		Collections.shuffle(shuffledTickets);
		
	} //End of constructor method
	
	
	
	
	
// ===== GAME START METHODS ===========================================================================
	
// ----- getInitialTickets Method ---------------------------------------------------------------------------
	//Randomly generating tickets at the beginning of the game for the user to complete
	public static void getInitialTickets (int indexOfCurrPlayer) {
		
		playerTickets.add(shuffledTickets.remove(0));
		playerTickets.add(shuffledTickets.remove(0));
		playerTickets.add(shuffledTickets.remove(0));
		
		ticketCheckBoxSetUp(indexOfCurrPlayer);
		
	} //End of getInitialTickets method
	
	
	
// ----- ticketCheckBoxSetUp Method ---------------------------------------------------------------------------
	//Setting up the check boxes with the randomly generated tickets the user must choose from
	public static void ticketCheckBoxSetUp(int indexOfCurrPlayer) {
		
		ticket1 = new JCheckBox(playerTickets.get(0).toString());
		ticket2 = new JCheckBox(playerTickets.get(1).toString());
		ticket3 = new JCheckBox(playerTickets.get(2).toString());
		
		initialTicketSelectionSetUp(indexOfCurrPlayer);
		
	} //End of ticketCheckBoxSetUp method
	
	
	
// ----- initialTicketSelectionSetUp Method ---------------------------------------------------------------------------
	//Setting up the JOptionPane that asks the user to choose at least two tickets
	//Ensures that the user has selected at least two tickets in order to continue
	public static void initialTicketSelectionSetUp (int indexOfCurrPlayer) {
		
		ArrayList<Ticket> finalTickets = 				//ArrayList of the tickets that user
				new ArrayList<Ticket>();				//chooses to keep from the initial draw
		
		Object [] selectionOptions = 					//Creating an object array to hold
			{message, ticket1, ticket2, ticket3};		//the JOptionPane check boxes
		
		boolean twoTickets = false;						//Determines if the user has selected 
														//at least two tickets
		
		do {
			
			//If the loop repeats, it must clear so that two of
			//the same tickets aren't added to the player
			finalTickets.clear();
		
			//Creating the JOptionPane with the check boxes and message
			int select = JOptionPane.showConfirmDialog(null, selectionOptions, "Select Tickets", 
					JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION);
						
			//If at ticket is selected, it is added to the final tickets
			//that the user has selected to play with
			if (ticket1.isSelected()) {
				finalTickets.add(playerTickets.get(0));
				
			} if (ticket2.isSelected()) {
				finalTickets.add(playerTickets.get(1));
				
			} if (ticket3.isSelected()) {
				finalTickets.add(playerTickets.get(2));
			
			} //End of if statements (check boxes)
			
			//If there are at least two tickets that the user has selected,
			//then the program continues on
			if (finalTickets.size()>= 2)
				twoTickets = true;
			
		//If there are less than two tickets that the user has selected, then
		//the same pop up window will appear
		} while (!twoTickets);
		
		if (!ticket1.isSelected())
			shuffledTickets.push(playerTickets.get(0));
		
		if (!ticket2.isSelected())
			shuffledTickets.push(playerTickets.get(1));
		
		if (!ticket3.isSelected())
			shuffledTickets.push(playerTickets.get(2));
		
		playerTickets.clear();
		
		switch (indexOfCurrPlayer) {
		
		case 0:
			player1Tickets = finalTickets;
		case 1:
			player2Tickets = finalTickets;
		case 2:
			player3Tickets = finalTickets;
		case 3:
			player4Tickets = finalTickets;
		
		} //End of switch statement
		
	} //End of initialTicketSelectionSetUp method

	
	
	
	public static ArrayList<Ticket> getPlayer1Tickets() {
		return player1Tickets;
	}

	
	public static void setPlayer1Tickets(ArrayList<Ticket> player1Tickets) {
		TicketController.player1Tickets = player1Tickets;
	}

	
	public static ArrayList<Ticket> getPlayer2Tickets() {
		return player2Tickets;
	}


	public static void setPlayer2Tickets(ArrayList<Ticket> player2Tickets) {
		TicketController.player2Tickets = player2Tickets;
	}


	public static ArrayList<Ticket> getPlayer3Tickets() {
		return player3Tickets;
	}


	public static void setPlayer3Tickets(ArrayList<Ticket> player3Tickets) {
		TicketController.player3Tickets = player3Tickets;
	}


	public static ArrayList<Ticket> getPlayer4Tickets() {
		return player4Tickets;
	}


	public static void setPlayer4Tickets(ArrayList<Ticket> player4Tickets) {
		TicketController.player4Tickets = player4Tickets;
	}
	
	
	
	
	
// ===== GAME PLAY METHODS ===========================================================================
	
	// ----- setCurrentPlayersTicketsAndRoutes Method ---------------------------------------------------------------------------
	//Setting the current player's tickets and routes they claimed
	public static void setCurrentPlayersTicketsAndRoutes () {
		
		currentPlayerTickets = TTRController.getCurrentPlayer().getTickets();
		currentPlayerRoutes = TTRController.getCurrentPlayer().getRoutes();
		
//		currentPlayerTickets = TicketControllerTest.player1.getTickets();
//		currentPlayerRoutes = TicketControllerTest.player1.getRoutes();
		
	} //End of setCurrentPlayersTicketsAndRoutes method
	
	
	
	// ----- ticketComplete Method ---------------------------------------------------------------------------
	//Determining if the ticket has been completed and the two cities on the ticket
	//have been connected by different routes
	public static void ticketComplete () {
		
		//Going through each of the current player's tickets
		for (Ticket currentPlayerTicket : currentPlayerTickets) {
			
			//Checks if current ticket is complete
			//Ticket has a built in completed attribute
			//		If the current ticket isn't completed, it
			//		goes to determine whether the ticket
			//		if not completed
			if(!currentPlayerTicket.getCompleted()) {
				
				//Gets the two cities that the ticket is trying to connect
				String startCity = currentPlayerTicket.getCity1();
				String endCity = currentPlayerTicket.getCity2();
				
				
				//Finding the cities that the user has connected together
				ArrayList<String> citiesVisited = getCitiesVisited();		//Holds all the cities the player has
																				//has connected via claimed routes
				
				//Checks if the two cities on the ticket are connected by
				//going through a list of the cities visited and comparing it
				//to a list of all the nearby cities of a city
				//		If the two cities on the ticket are connected to one
				//		another then it continues on and adds the ticket's point
				//		value to the player's score and it also sets the ticket
				//		as complete.
				if (checkConnection(citiesVisited, startCity, endCity)) {
					
					//Adding the ticket's point value to the current player's score 
					TTRController.currentPlayer.setPlayerScore(ticketScoring(currentPlayerTicket));
					//TicketControllerTest.player1.setPlayerScore(ticketScoring(currentPlayerTicket));		//Testing
					
					//Sets the ticket as completed so that more points aren't added
					//when the ticket is one again checked
					currentPlayerTicket.setCompleted(true);
					
					//Adding the player's ticket to the list of all the checked
					//tickets in order to be added back into the current player
					//in TTRController so that all the information is up to date
					checkedTickets.add(currentPlayerTicket);
				
				//If the two cities are not connected, then the incomplete ticket
				//is added back into current player
				} else 
					checkedTickets.add(currentPlayerTicket);
					
			} //End of if statement (checking if current player's ticket has already been completed)
			
		} //End of enhanced for loop (going through each ticket of the current player)
		
		//Adding all the updated tickets to the current player's ticket array
		TTRController.currentPlayer.setTickets(checkedTickets);
		//TicketControllerTest.player1.setTickets(checkedTickets);			//Testing
		
	} //End of ticketCompleted method
	
	
	
// ----- checkConnection Method ---------------------------------------------------------------------------
	//Checking that nearby cities are cities that user has connected to via routes
	private static boolean checkConnection (ArrayList<String> citiesVisited, String startCity, String endCity) {
		
		
		boolean found = false;		//Allows while loop to continue repeating
		
		
		do {
			
			//Finding all the cities near the starting city
			//by searching through all available routes and
			//determining which routes include the starting city
			ArrayList<String> citiesNearby = getNearbyCities(startCity); 	//Nearby cities mean that there are routes that can lead from
																			//the starting to city to another city in one route.
			
			//Checking if there are any duplicate cities in either of the city lists
			citiesNearby = checkForDuplicates(citiesNearby);
			citiesVisited = checkForDuplicates(citiesVisited);
			
			//Checking to make sure that the start and end cities have actually been visited
			//If they are not visited, then it jumps out of the method by returning false
			if (!checkCitiesAreVisited(citiesVisited, startCity) || !checkCitiesAreVisited(citiesVisited, endCity))
				return false;
			
			//Removes the starting city from the list of cities visited
			//in order to prevent an infinite loop
			citiesVisited.remove(startCity);
			
			//Goes through each city the player visited
			for (String visited : citiesVisited) {
				
				boolean broke = false;		//If the break statement in the if statement is reached
				
				//ArrayList<String> possibleCities = new ArrayList<String>();
				
				//Goes through each nearby city 
				for (String nearby : citiesNearby) {
					
					//Compares the nearby city with all the visited cities
					
					//If a nearby city and a visited city are equal,
					//but it's not the final destination
					if (nearby.equals(visited) && !visited.equals(endCity)) {
						
						//possibleCities.add(nearby);
						
						//Removes the visited city from cities visited list
						//This ensures that there isn't an infinite loop and
						//ensures that the algorithm continues searching for
						//a path to the final destination.
						citiesNearby.remove(nearby);
						
						//Sets the starting city as the nearby city, so that
						//it is moving along the route to find if the start
						//city is connected to end city
						//(Like moving your piece from city to city along a path)
						startCity = nearby;
						
						broke = true;		//Sets as true to know that the program
											//has reached the break line
						
						//Exits out of this checking so that it can begin searching
						//with a new starting city
						break;
					
					//If a nearby city and a visited city are equal, 
					//and both the nearby and visited city are the final destination
					} else if (nearby.equals(visited) && visited.equals(endCity))
						
						//Returns true that the start city and nearby city are connected
						return true;
					
				} //End of for loop
				
				//previouslyVisitedCities.add(possibleCities);
				
				//If the break on line 312 is reached, break again to exit out of
				//the nested for loops
				if (broke == true)
					break;
				
			} //End of for loop
			
		} while (!found);
		
		
		return false;
	
		
	} //End of checkConnection method
	
	
	
// ----- getNearbyCities Method ---------------------------------------------------------------------------
	//Finding the nearby cities to a specific city (parameter)
	//Nearby cities are cities that are connected with routes
	private static ArrayList<String> getNearbyCities (String middleCity) {
		
		//Creating an ArrayList of all the cities that is one route away
		//from the center city
		ArrayList<String> citiesNearby = new ArrayList<String>();
		
		//Goes through a list of all the routes that you can take
		for (Route nearbyRoutes : possibleRoutes) {
			
			//If a city in any of the routes matches the starting city,
			//then the other city in the route is added to the list of
			//all the nearby cities 
			if (nearbyRoutes.getSourceCity().equals(middleCity))		//If the first city matches the starting city, then the 
				citiesNearby.add(nearbyRoutes.getDestinationCity());	//second city on the route is added to the cities nearby
			
			if (nearbyRoutes.getDestinationCity().equals(middleCity))	//If the second city matches the starting city, then
				citiesNearby.add(nearbyRoutes.getSourceCity());			//the first city is added to the cities nearby
			
		} //End of for loop (Cities Nearby)
		
		//Ensures that there are no duplicate cities in the list
		citiesNearby = checkForDuplicates(citiesNearby);
		
		//returns a list of the nearby cities
		return citiesNearby;
		
	} //End of getNearbyCities method
	
	
	
// ----- getCitiesVisited Method ---------------------------------------------------------------------------
	//Finding which cities the user has connected with one another via claimed routes
	private static ArrayList<String> getCitiesVisited () {
		
		//Creating an ArrayList of all the cities that is being connected
		//via claimed routes by the player
		ArrayList<String> citiesVisited = new ArrayList<String>();
		
		//Going through all the routes that user has claimed and adding the cities
		//the route connects to the cities that the user visited
		for (Route currentPlayerRoutesCompleted : currentPlayerRoutes) {
			
			citiesVisited.add(currentPlayerRoutesCompleted.getSourceCity());
			citiesVisited.add(currentPlayerRoutesCompleted.getDestinationCity());
			
		} //End of for loop (adding cities that have been connected via routes claimed)
		
		//Sorts the list of cities alphabetically
		Collections.sort(citiesVisited);
		
		//Ensures that there are no duplicated in the list
		citiesVisited = checkForDuplicates(citiesVisited);
		
		//Returns the final list of all the cities visited
		return citiesVisited;
		
	} //End of getCitiesVisited method
	
	
	
// ----- checkForDuplicates Method ---------------------------------------------------------------------------
	//Checks a list of cities for any duplicates and removes them if there are any duplicates
	private static ArrayList<String> checkForDuplicates (ArrayList<String> list) {
		
		for (int index = 0; index < list.size() - 1; index++) {
			
			//If the current city and the next city have the same name
			//then the current city is removed from the ArrayList
			if (list.get(index).equals(list.get(index + 1)))
				list.remove(index);
			
		} //End of for loop (duplicate cities)
		
		return list;
		
	} //End of checkForDuplicates
	
	
	
// ----- checkCitiesAreVisited Method ---------------------------------------------------------------------------
	//Checks that the starting city and that the ending city can actually be found in the list
	//of visited cities
	private static boolean checkCitiesAreVisited (ArrayList<String> visitedList, String city) {
		
		for (String visited : visitedList) {
			
			if (visited.contains(city) && isThereARoute(visited, city))
				return true;
					
		} //End of for loop (checking if starting city has been visited)
		
		return false;
		
	} //End of checkCitiesAreVisited
	
	
	
// ----- isThereARoute Method ---------------------------------------------------------------------------
	//Checks to make sure there is a route that the user claimed between the two cities
	private static boolean isThereARoute (String visited, String city) {
		
		//If the program goes through each of the current player's claimed routes and
		//the two cities don't match any of the player's routes, then there is no
		//route between the cities and the method returns false
		for (Route routeClaimed : currentPlayerRoutes) {
			
			if ((routeClaimed.getSourceCity().equals(city) || routeClaimed.getSourceCity().equals(visited))
					&& routeClaimed.getDestinationCity().equals(city) || routeClaimed.getDestinationCity().equals(visited))
				return true;
			
		} //End of for loop (routeClaimed checking)
		
		return false;
		
	} //End of isThereARoute method
	
	

// ----- ticketScoring Method ---------------------------------------------------------------------------
	//Getting the point value for completing a ticket
	private static int ticketScoring (Ticket currentTicket) {
		
		return currentTicket.getPointValue();
		
	} //End of ticketScoring method
	
	
	public static void ticketCardButton(int indexOfCurrPlayer) {
		
		//Doesn't give new cards to the user unless the previous
		//the player had clicked "Ok"
		//Prevents new cards from being drawn if the user presses
		//cancel
		if (turn == JOptionPane.OK_OPTION) {
			
			playerTickets.add(shuffledTickets.remove(0));
			playerTickets.add(shuffledTickets.remove(0));
			playerTickets.add(shuffledTickets.remove(0));
			
			ticket1 = new JCheckBox(playerTickets.get(0).toString());
			ticket2 = new JCheckBox(playerTickets.get(1).toString());
			ticket3 = new JCheckBox(playerTickets.get(2).toString());
			
		} //End of if statement (redrawing cards)
			
		ArrayList<Ticket> finalTickets = 						//ArrayList of the tickets that user
				new ArrayList<Ticket>();						//chooses to keep from the initial draw
		
		Object [] selectionOptions = 							//Creating an object array to hold
			{"Select the tickets you wish to keep "				//the JOptionPane check boxes and message
					+ "- you MUST keep at least 1 ticket.", 
					ticket1, ticket2, ticket3};		
		
		boolean oneTicket = false;						//Determines if the user has selected 
														//at least two tickets
		
		do {
			
			//If the loop repeats, it must clear so that two of
			//the same tickets aren't added to the player
			finalTickets.clear();
		
			//Creating the JOptionPane with the check boxes and message
			turn = JOptionPane.showConfirmDialog(null, selectionOptions, "Select Tickets", JOptionPane.OK_CANCEL_OPTION);
						
			//If at ticket is selected, it is added to the final tickets
			//that the user has selected to play with
			if (ticket1.isSelected()) {
				finalTickets.add(playerTickets.get(0));
				
			} if (ticket2.isSelected()) {
				finalTickets.add(playerTickets.get(1));
				
			} if (ticket3.isSelected()) {
				finalTickets.add(playerTickets.get(2));
			
			} //End of if statements (check boxes)
			
			//If there are at least two tickets that the user has selected,
			//then the program continues on
			if (finalTickets.size()>= 1 || turn == JOptionPane.CANCEL_OPTION)
				oneTicket = true;
			
		//If there are less than two tickets that the user has selected, then
		//the same pop up window will appear
		} while (!oneTicket);
		
		//If any of the cards aren't selected, they are
		//placed on thhe bottom of the tickets deck
		if (!ticket1.isSelected())
			shuffledTickets.push(playerTickets.get(0));
		
		if (!ticket2.isSelected())
			shuffledTickets.push(playerTickets.get(1));
		
		if (!ticket3.isSelected())
			shuffledTickets.push(playerTickets.get(2));
		
		switch (indexOfCurrPlayer) {
		
		case 0:
			player1Tickets = finalTickets;
		case 1:
			player2Tickets = finalTickets;
		case 2:
			player3Tickets = finalTickets;
		case 3:
			player4Tickets = finalTickets;
		
		} //End of switch statement
		
	} //End of ticketCardButton method
		
} //End of TickeController class