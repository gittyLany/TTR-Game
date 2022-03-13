/*
 * Names: Tiffany (25%), Vidhi(25%), Katherine(25%), Jathusaa(25%)
 * 
 * Tiffany: TTRCanadaApplication(Code), TrainCard(50%), GameFrame, BoardPanel(50%), ScorePanel(40%), FileImportController(60%), TrainCardController(50%)
 * Vidhi: Route, RouteController, TrainCard(50%), CardColour (Enum), BoardPanel(50%), ScorePanel(60%), TTRController(5%), TTRCanadaApplication(Header)
 * Katherine: City, Player, Ticket, PlayerColour (Enum), PlayerPanel(5%), FileImportController(5%), TicketController, TTRController(95%)
 * Jathusaa: CardPanel, PlayerPanel(95%), FileImportController(35%), TrainCardController(50%)
 * 
 * Date: December 5th, 2021
 * 
 * Course: ICS4U1-02 - Mr. Fernandes
 * 
 * Title: Ticket to Ride
 * 
 * Description: An online version of the Ticket To Ride Canada board game where players can
 * deal cards, claim routes, complete tickets and get scores or pass their turn until the winner is determined
 * 
 * Features:
 * 1. Interactive GUI for online version of the Ticket To Ride board game with a Canadian version
 * 2. Able to deal cards, claim a route, and complete a ticket
 * 3. Able to get scores, pass turns, and pick new cards from the deck
 * 
 * - Extra Features That Work:
 * 1. Highlight possible routes
 * 2. Sound/Music
 * 
 * - Extra Features Attempted:
 * 1. AI/Computer Player
 * 2. Save + Load new game
 * 3. Extra points for longest road
 * 
 * Major skills: GUI (Swing package), Enum, Object oriented programming, Communication and team-work skills, Handling files and exceptions
 * 
 * Areas Of Concern: 
 * PlayerPanel/TrainCardController: The traincards are given properly to each player initially (as seen when the program is run), but after the first round, all traincards become the same for each player
 * TicketController: Tickets are scored but not completed
 * The tickets for each player get increased once the player plays for a long time without picking those tickets
 */

package application;

//import controller.MenuBarController;
//import controller.MusicController;

//===== IMPORTS ==============================================================================
import controller.*;

/**
* Creates an instance of the train to ride game controller to run the application
*/
public class TTRCanadaApplication {
	
// ===== METHODS =============================================================================

	// ----- Main Method ---------------------------------------------------------------------
	// Runs the application
	public static void main(String[] args) {
		
		// Create the controller that controls the game
		new TTRController();
 
	} // ends the main method
	
} // ends the program


