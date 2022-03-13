/*
 * Name: Katherine Chun
 * Date: Sunday December 5, 2021
 * Course: ICS4U1-02  Mr. Fernandes
 * Title: TTR Canada - model - Player Object
 * Description: Creating player objects with its attributes. Each player has
 * 				a player name, an assigned player colour, the routes they
 * 				should complete, the routes the player has claimed, the
 * 				number of trains that the player has left to use, and the
 * 				player's current score.
 * Note:
 */



//PACKAGE
package model;



//IMPORTS
import java.util.*;



/**
 * Creating player objects with a name, colour, tickets, routes claimed, # of coloured cards,
 * # of trains available to be used and the player's score.
 */
public class Player {
	
	
// ===== INSTANCE VARIABLES ===========================================================================
	private String playerName;											//The player's name
	private PlayerColour playerColour;										//The colour piece the player is assigned
	private ArrayList<Ticket> tickets = new ArrayList<Ticket>();		//The different route tickets that the player should complete
	private ArrayList<Route> routes = new ArrayList<Route>();			//The different routes that the player has claimed
	private int [] numOfCards = new int[9];								//The amount of cards the player has of each colour
	private int numOfTrains;											//The amount of trains the player has left to play
	private int playerScore;											//The total amount of points that the player has

	
	
	
	
// ===== METHODS ===========================================================================
	
// ----- Constructor Method ---------------------------------------------------------------------------
	//Initializes the attributes of every player
	public Player (String playerName, PlayerColour playerColour, ArrayList<Ticket> tickets, 
			ArrayList<Route> routes, int [] numOfCards, int numOfTrains, int playerScore) {
		
		super();
		
		setPlayerName(playerName);
		setColour(playerColour);
		setTickets(tickets);
		setRoutes(routes);
		setNumOfCards(numOfCards);
		setNumOfTrains(numOfTrains);
		setPlayerScore(playerScore);
		
	} //End of constructor method
	
	
	public Player () {
		
		super();
		
		setPlayerName("");
		setColour(PlayerColour.YELLOW);
		setTickets(null);
		setRoutes(null);
		setNumOfCards(null);
		setNumOfTrains(0);
		setPlayerScore(0);
		
	} //End of constructor method

	
	
	
	
// ===== GET & SET METHODS ===========================================================================
	
// ----- Player Name Method ---------------------------------------------------------------------------
	//Setting and getting the player's name
	public String getPlayerName() {
		
		return playerName;
		
	} //End of getPlayerName method


	public void setPlayerName(String playerName) {
		
		this.playerName = playerName;
		
	} //End of setPlayerName method

	
	
// ----- Colour Method ---------------------------------------------------------------------------
	//Setting and getting the player's colour piece
	public PlayerColour getColour() {
		
		return playerColour;
		
	} //End of getColour method


	public void setColour(PlayerColour playerColour) {
		
		this.playerColour = playerColour;
		
	} //End of setColour method


	
// ----- Tickets Method ---------------------------------------------------------------------------
	//Setting and getting the routes that the player should complete
	public ArrayList<Ticket> getTickets() {
		
		return tickets;
		
	} //End of getTickets method


	public void setTickets(ArrayList<Ticket> tickets) {
		
		this.tickets = tickets;
		
	} //End of setTickets method


	
// ----- Routes Method ---------------------------------------------------------------------------
	//Setting and getting the routes the player has claimed
	public ArrayList<Route> getRoutes() {
		
		return routes;
		
	} //End of getRoutes method


	public void setRoutes(ArrayList<Route> routes) {
		
		this.routes = routes;
		
	} //End of setRoutes method


	
// ----- Number of Cards Method ---------------------------------------------------------------------------
	//Setting and getting the number of cards for each colour
	public int[] getNumOfCards() {
		
		return numOfCards;
		
	} //End of getNumOfCards method


	public void setNumOfCards(int[] numOfCards) {
		
		this.numOfCards = numOfCards;
		
	} //End of setNumOfCards method


	
// ----- Number of Trains Method ---------------------------------------------------------------------------
	//Setting and getting the number of trains the player has left to use
	public int getNumOfTrains() {
		
		return numOfTrains;
		
	} //End of getNumOfTrains method


	public void setNumOfTrains(int numOfTrains) {
		
		this.numOfTrains = numOfTrains;
		
	} //End of setNumOfTrains method

	
	
// ----- Player Score Method ---------------------------------------------------------------------------
	//Setting and getting the player's current score
	public int getPlayerScore() {
		
		return playerScore;
		
	} //End of getPlayerScore method


	public void setPlayerScore(int playerScore) {
		
		this.playerScore = playerScore;
		
	} //End of setPlayerScore method
	
	
	
	public void setNumOfCardsAtIndex (int index, int numOfCards) {
		
		this.numOfCards[index] = numOfCards;
		
	}
	
	
	
	
	
// ===== UTILITY METHODS ===========================================================================

// ----- toString Method ---------------------------------------------------------------------------
	//Displays the attributes of each player object
	@Override
	public String toString ()  {
		
		return String.format("%s \nColour: %s \nTickets: %s \nRoutes Claimed: %s \n"
				+ "Number of Colour Cards: %s \nTrains Remaining: %d \nCurrent Score: %d", 
				playerName, playerColour, Arrays.toString(tickets.toArray()), Arrays.toString(routes.toArray()),
				Arrays.toString(numOfCards), numOfTrains, playerScore);
		
	} //End of toString method
	
	
} //End of Player class