/*
 * Name: Vidhi Ruparel
 * Date: December 5, 2021
 * Course: ICS4U1-02 Mr. Fernandes
 */

//package
package controller;

//imports
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.CardColour;
import model.Player;
import model.Route;
import model.TrainCard;
import view.HighlightLine;

/**
 * RouteController class contains methods to manage the routes including 
 * generating available routes,removing appropriate cards for completed routes 
 * and scoring the routes.
 */
public class RouteController {
	
	//instance variables
	private static Route[] routesAvailable = TTRController.getFileImport().getRoutes();
	private static ArrayList<Route> routesToChooseFrom = new ArrayList<Route>();
	private static ArrayList<TrainCard> cardStrings = new ArrayList<TrainCard>();
	public static Route selectedRoute;
	public static boolean somethingSelected = false;
	public static boolean noRoutesAvailable = false;
	private static ImageIcon icon;
	
	//claims a route
	public static void claimRoute() {
		
		//to help TicketController
		if (somethingSelected == true) {
			
			routesToChooseFrom.removeAll(routesToChooseFrom);
			routesToChooseFrom.clear();
			cardStrings.clear();
			selectedRoute = null;
			
			somethingSelected = false;
		}
		
		//determines the current player	
		Player currentPlayer;
		currentPlayer = TTRController.currentPlayer;
		
		//displays list of available routes for the current player to select from
		getAvailableRoutes(currentPlayer);
		
		//if the user clicks on the selected route
		//if selected route is null
        if (selectedRoute == null) {
            TTRController.getGameFrame().getBoardPanel().repaint();
        }
        
        else {	//any of the routes is selected
        	
        	somethingSelected = true;
        	
        	//remove the claimed route from available routes
        	for (Route r : routesAvailable) {
        		if (r.equals(selectedRoute))
        			r = null;
        	}
			
			//removes cards from the user and shows them
			removeCards(currentPlayer);
			
			System.out.println("REMOVED CARDSSSSS");
			
			int updatedScore = currentPlayer.getPlayerScore() + RouteController.updateScore(selectedRoute.getLength());
			currentPlayer.setPlayerScore(updatedScore);                             //pass scores to TTRController
			
			//set the owner of the route the current player
			selectedRoute.setOwner(currentPlayer.getPlayerName());
			selectedRoute.setAcquisition(true);
			
			//sets a checkmark to indicate that the route is claimed
			//TTRController.getGameFrame().getBoardPanel().placeCheckMarks(selectedRoute);
			
			System.out.println("Getting Checkmark???");
			
	        //Set a checkmark for the route
//	        String col = currentPlayer.getColour().toString().toLowerCase();
//	        col = Character.toUpperCase(col.charAt(0))+col.substring(1);
//	        System.out.println(col);
//	        //icon = new ImageIcon("./images/checkmark"+col+".png");
//	        //System.out.println(icon.toString());
//	        
//	        JLabel checkLabel = new JLabel();
//			checkLabel.setIcon(new ImageIcon(new ImageIcon(
//					"images/checkmarkYellow.png")
//					.getImage()));
//			
//			checkLabel.setVisible(true);
//			
//			checkLabel.setBounds(selectedRoute.getX(), selectedRoute.getY(), 15, 15);	// Position and sizing of the image/label
//			
//			//TTRController.getGameFrame().add(TTRController.getGameFrame().getBoardPanel());
//			TTRController.getGameFrame().setVisible(true);
//			System.out.println("Hiii again");
			
			TTRController.getGameFrame().getBoardPanel().placeCheckMarks(selectedRoute);
			//TTRController.getGameFrame().getBoardPanel().add(checkLabel);
			
	        //TTRController.getGameFrame().BoardPanel.setIcon(icon);
	        //selectedRoute.setIcon(new ImageIcon("./images/checkmark"+col+".png"));
	        //JLabel label = new JLabel(new ImageIcon("./images/checkmark"+col+".png"));
		    //System.out.println("\nHiii");
		    //TTRController.getGameFrame().getBoardPanel().add(label);
		    // add the Jpanel to the main window
		    //TTRController.getGameFrame().add(label);
		    //TTRController.getGameFrame().setVisible(true);
			
			//adds the route to the current player's claimed route list
			currentPlayer.getRoutes().add(selectedRoute);
			currentPlayer.setRoutes(currentPlayer.getRoutes());
			
			//removes the trains from the current player
			currentPlayer.setNumOfTrains(currentPlayer.getNumOfTrains() - selectedRoute.getLength());
			
			TicketController.setCurrentPlayersTicketsAndRoutes();
			
			TicketController.ticketComplete();
			
//			if (currentPlayer.getNumOfTrains() <= 25) 													//Handled in TTRController by endGame() method
//				TTRController.determineWinner();
//			else 
//				TTRController.disableElements();
		
		}  //end of if statement (if user selects a route to claim)
			
	}	//end of claimRoute method
	
	
	//displays a list of available routes for the current player to select from
	private static void getAvailableRoutes(Player player) {
			
		//goes through all routes
		for (int index = 0; index < routesAvailable.length; index++) {
			
			int colourNum = determineRouteColour(routesAvailable[index]);
			
			// If the route colour is Gray
			if (colourNum == 9) {
				
				// Run through all the colours since any colour could apply for a gray route
				for (int colour = 0; colour < 9; colour++) {
					
					if ((player.getNumOfCards()[colour] + player.getNumOfCards()[5]) >= routesAvailable[index].getLength()) {

						routesToChooseFrom.add(routesAvailable[index]);
						
						break;
						
					} // end the if statement that sees if there are enough cards to claim the route
					
				} // ends the for loop that checks through each colour
				
			}  // ends if statement that runs if the current route being checked is gray
			
			// Checks there are enough cards (colourNum + Rainbow) to get the route
			else if (routesAvailable[index].getLength() <= (player.getNumOfCards()[colourNum] + player.getNumOfCards()[5])) {
				
				routesToChooseFrom.add(routesAvailable[index]);
				
			} //ends the else if statement that checks enough cards (colourNum + Rainbow) to claim the route
				
		} //End of for loop (going through each route)
		
		//displays routes player can choose from
		showAvailableRoutes(routesToChooseFrom);
	}
	
	
	//displays the routes the player can choose from
	private static void showAvailableRoutes(ArrayList<Route> routesToChooseFrom) {
		
		//if there are no choices available, then cancel route claiming 
        if (routesToChooseFrom.isEmpty()) {
        	noRoutesAvailable = true;
            JOptionPane.showMessageDialog(TTRController.getGameFrame(), "No routes available - Canceling Route Claiming...");
        }
        
        highlightRoutes();
        
        //otherwise, display all valid routes the player can choose
		JList routesList = new JList(routesAvailable);
		
		routesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		routesList.setLayoutOrientation(JList.VERTICAL);
		routesList.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(routesList);
		listScroller.setPreferredSize(new Dimension(1000, 200));
		
		Object[] routesToChoose = routesToChooseFrom.toArray();
		
		Route selected = (Route) JOptionPane.showInputDialog(null, "Choose route to claim:", "Claim route",
				JOptionPane.PLAIN_MESSAGE, null, routesToChoose, routesToChoose[0]);
			
		System.out.print(selected);
		
		selectedRoute = selected;
		
		//selectedRoute = getSelectedRoute(routesList, routesToChooseFrom);
		
		//remove highlights from routes
        TTRController.getGameFrame().getBoardPanel().repaint();
		
//        Route route = (Route) JOptionPane.showInputDialog(TTRController.getGameFrame(),
//                "Choose route to claim...",
//                "Claim Route", JOptionPane.QUESTION_MESSAGE, null,
//                routesToChooseFrom.toArray(),
//                routesToChooseFrom.get(0));
	}
	
	
	//returns route the user selected
	private static Route getSelectedRoute(JList routesList, ArrayList<Route> routesToChooseFrom) {
		int index = routesList.getSelectedIndex();
		
		if (index < 0) 
			return null;
		
		else
			return routesToChooseFrom.get(index);
		
	}

	//determines the colour of the route
	private static int determineRouteColour(Route route) {
		if (route.getColour().equals(CardColour.BLACK)) 
			return 0;
		
		else if (route.getColour().equals(CardColour.BLUE)) 
			return 1;
		
		else if (route.getColour().equals(CardColour.GREEN)) 
			return 2;
		
		else if (route.getColour().equals(CardColour.ORANGE)) 
			return 3;
		
		else if (route.getColour().equals(CardColour.PURPLE)) 
			return 4;
		
		//skip rainbow
		
		else if (route.getColour().equals(CardColour.RED)) 
			return 6;
		
		else if (route.getColour().equals(CardColour.WHITE)) 
			return 7;
		
		else if (route.getColour().equals(CardColour.YELLOW))
			return 8;
		
		else
			return 9;	// Gray
	
	}
	
	//highlights routes and cities
	private static void highlightRoutes () {
		
        for (Route route : routesToChooseFrom) 
            HighlightLine.drawHighlightLine(TTRController.getGameFrame().getBoardPanel().getGraphics(), route);
        
    }

	//remove cards from user
	private static void removeCards(Player currentPlayer) {

        //remove the player's cards
		//finds the corresponding number for the colour of the route
		int colour = determineRouteColour(selectedRoute);
		
        //temporary array for player cards
     	int[] temp = currentPlayer.getNumOfCards();
		
		//if the route is not gray
        if (colour != 9) {
            
        	//finds number of cards of the same colour the player has
        	int enoughCards = currentPlayer.getNumOfCards()[colour];
        	
        	//if player has same number of cards as route length, remove all cards
        	if (enoughCards == selectedRoute.getLength()) {
        		temp[colour] = 0; 
        	}
        	
        	//if player has more cards than the route length, some cards still remain
        	else if (enoughCards > selectedRoute.getLength()) {
        		temp[colour] = enoughCards - selectedRoute.getLength();
        	}
        	
        	//if player has less number of cards than route length, remove all cards
        	else {
        		temp[colour] = 0;
        		
        		//then use rainbow cards
        		//find number of rainbow cards needed                              
                int rainbowCards = selectedRoute.getLength() - enoughCards;
        		
                //remove rainbow cards
                temp[5] = temp[5] - rainbowCards;
        	}	                         
        	
    		//update player cards
            currentPlayer.setNumOfCards(temp);
        }
        
        //if the route is gray
        else {
        
        	//list of cards to display
    		ArrayList<CardColour> cardColours = new ArrayList<>();
    		
    		//list of possible colours the player can choose
    		CardColour colours[] = CardColour.values();
    		
    		//temporary array to update player cards
    		int[] temp2 = currentPlayer.getNumOfCards();
        	
        	for (int index = 0; index < currentPlayer.getNumOfCards().length; index++) {
        		
        		//if player has enough cards of route's colour
        		if ((index != 5) && ((currentPlayer.getNumOfCards()[index] + currentPlayer.getNumOfCards()[5]) >= selectedRoute.getLength())) {
        			
        			//add that colour to the cardColours ArrayList
        			cardColours.add(colours[index]);
        		}
        	}
        	
        	cardColours.remove(CardColour.RAINBOW);
        	 
        	//display all colour options
        	Object[] coloursToChooseFrom = cardColours.toArray();
        
        	CardColour cardColour = (CardColour) JOptionPane.showInputDialog(null, "Choose the card colour you wish to use to claim the Gray route:", "Select Colour", 
        			JOptionPane.QUESTION_MESSAGE, null, coloursToChooseFrom, coloursToChooseFrom[0]);
			
        	
        	//if the player selects a colour
			if (cardColour != null) {
				
				int colourNum = cardColour.ordinal();
				
				int enoughCards = currentPlayer.getNumOfCards()[colourNum];
				
				if (enoughCards == selectedRoute.getLength())
					temp2[colourNum] = 0;
				
				else if (enoughCards > selectedRoute.getLength())
					temp2[colourNum] = enoughCards - selectedRoute.getLength();
				
				else {
					temp2[colourNum] = 0;
					
					int rainbowCards = selectedRoute.getLength() - enoughCards;
					
					temp[5] = temp[5] - rainbowCards;
				}
				
        		//update player cards
                currentPlayer.setNumOfCards(temp);
                
			}	//end of if the player selects a colour	
			
        }  //end of if the route is gray
        
	} //end of remove cards method

	
	//updates the score
	private static int updateScore(int routeLength) {			
		int score = 0;
		
		if (routeLength == 1)
			score = 1;
		
		else if (routeLength == 2)
			score = 2;
		
		else if (routeLength == 3)
			score = 4;
		
		else if (routeLength == 4)
			score = 7;
		
		else if (routeLength == 5)
			score = 10;
		
		else if (routeLength == 6)
			score = 15;
		
		else if (routeLength == 7)
			score = 18;
		
		return score;
	}
}
