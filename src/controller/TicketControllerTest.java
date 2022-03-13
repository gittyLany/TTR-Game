package controller;

import model.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class TicketControllerTest {
	
	
	public static Player player1 = new Player();
	
	
	public static void main (String [] args) {
		
		ArrayList<Ticket> player1Tickets = new ArrayList<Ticket>();
		
		Ticket ticket1 = new Ticket("Edmonton", "St.John's", 21);
		Ticket ticket2 = new Ticket("Vancouver", "St.John's", 21);
		
		player1Tickets.add(ticket1);
		player1Tickets.add(ticket2);
		
		
		ArrayList<Route> player1Routes = new ArrayList<Route>();
		ArrayList<Route> player1AltRoutes = new ArrayList<Route>();
		
		Route route1 = new Route("Halifax", "St.John's", 5, CardColour.BLACK, 1315, 630, false);
		Route route2 = new Route("Nain", "St.John's", 6, CardColour.BLACK, 1230, 410, false);
		Route route3 = new Route("Iqaluit", "Nain", 5, CardColour.BLACK, 1035, 315, false);
		Route route4 = new Route("Coral Harbour", "Iqaluit", 5, CardColour.BLACK, 860, 325, false);
		Route route5 = new Route("Baker Lake", "Coral Harbour", 3, CardColour.ORANGE, 670, 315, false);
		Route route6 = new Route("Baker Lake", "Bathurst Inlet", 3, CardColour.RED, 540, 305, false);
		Route route7 = new Route("Bathurst Inlet", "Yellowknife", 3, CardColour.BLACK, 420, 275, false);
		Route route8 = new Route("Whitehorse", "Yellowknife", 5, CardColour.BLACK, 265, 345, true);
		Route route9 = new Route("Prince George", "Whitehorse", 5, CardColour.BLACK, 175, 400, false);
		Route route10 = new Route("Edmonton", "Prince George", 3, CardColour.BLACK, 275, 520, false);
		Route route11 = new Route("Halifax", "Quebec", 3, CardColour.BLACK, 1125, 645, false);
		Route route12 = new Route("Baker Lake", "Resolute", 5, CardColour.GREEN, 630, 215, false);
		Route route13 = new Route("Prince George", "Vancouver", 3, CardColour.BLACK, 180, 545, false);
		
		
		Route route14 = new Route("Nain", "St.John's", 6, CardColour.BLACK, 1230, 410, false);
		Route route15 = new Route("Kuujuag", "Nain", 3, CardColour.BLACK, 1015, 375, false);
		Route route16 = new Route("Kuujuag", "Puvirnituq", 2, CardColour.PURPLE, 910, 380, false);
		Route route17 = new Route("Churchill", "Puvirnituq", 4, CardColour.BLACK, 745, 440, false);
		Route route18 = new Route("Churchill", "Thompson", 1, CardColour.RED, 610, 500, false);
		Route route19 = new Route("Saskatoon", "Thompson", 3, CardColour.PURPLE, 535, 565, false);
		Route route20 = new Route("Edmonton", "Saskatoon", 2, CardColour.WHITE, 410, 560, true);
		
		player1Routes.add(route1);
		player1Routes.add(route2);
		player1Routes.add(route3);
		player1Routes.add(route4);
		player1Routes.add(route5);
		player1Routes.add(route6);
		player1Routes.add(route7);
		player1Routes.add(route8);
		player1Routes.add(route9);
		player1Routes.add(route10);
		player1Routes.add(route11);
		player1Routes.add(route12);
		player1Routes.add(route13);
		
		
		player1AltRoutes.add(route14);
		player1AltRoutes.add(route15);
		player1AltRoutes.add(route16);
		player1AltRoutes.add(route17);
		player1AltRoutes.add(route18);
		player1AltRoutes.add(route19);
		player1AltRoutes.add(route20);
		
		
		
		
		
		int [] player1Cards = {10, 10, 10, 10, 10, 10, 10, 10, 10};		
		
		
		
		
		player1 = new Player("Player 1", PlayerColour.BLUE, player1Tickets, player1AltRoutes, player1Cards, 45, 0);
		
		
		System.out.println(player1);
		
		
		TicketController.ticketComplete();
		
		
		
		if (player1.getTickets().get(0).getCompleted() == true) {
			
			System.out.println("\nTicket is complete!!");
			
		} else {
			
			System.out.println("\nNot complete :(");
		
		
		}
		
		if (player1.getTickets().get(1).getCompleted() == true) {
			
			System.out.println("\nTicket is complete!!");
			
		} else {
			
			System.out.println("\nNot complete :(");
			
		}
		
		System.out.println("\n\nScore: " + player1.getPlayerScore());
		
	} //End of main method

} //End of TicketControllerTest class
