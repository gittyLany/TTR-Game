/*
 * Name: Katherine Chun, Tiffany Tang, Jathussa Indrakumaran
 * Date: Sunday December 5, 2021
 * Course: ICS4U1-02  Mr. Fernandes
 * Title: TTR Canada - FileImportController
 * Description:
 * Notes:
 */

//PACKAGE
package controller;



// ===== IMPORTS ====================================================================================
import java.util.Scanner;

import model.CardColour;
import model.City;
import model.Route;
import model.Ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.awt.*;




public class FileImportController {

// ===== INSTANCE VARIABLES =========================================================================
	
	//Arrays that store the file information
	public static City [] cities = new City[41];
	public static Route [] routes = new Route[100];
	public static Ticket [] tickets = new Ticket[50];

	
	
	
	
// ===== METHOD ====================================================================================	
	
	// Katherine
	public FileImportController () {
		
// ----- Importing the city information ------------------------------------------------------------
// Tiffany		
		// Initialize the city array
		for (int index = 0; index < cities.length; index++)
			cities[index] = new City();
		
		try {
			
			Scanner input = new Scanner(new File("./files/cities.txt"));
			
			input.useDelimiter(",|\\n");
			
			int index = 0;
			
			while (input.hasNextLine()) {
			
				cities[index].setName(input.next());
				cities[index].setX(input.nextInt());
				cities[index].setY(input.nextInt());
				
				// Removes the extra line that appears b/w each city in the text file
				if (index < 40)
					input.next();
				
				index++;	// increment the index to import the information for the next city
			}
	
			// Close the file
			input.close();
			
		} catch (FileNotFoundException error) {
			System.out.println("Error:" + error);
		} //End of try and catch statement
		
		
// ----- Importing the route information ------------------------------------------------------------
// Tiffany		
		// Initialize the route array
		for (int index = 0; index < routes.length; index++)
			routes[index] = new Route();
		
		try {
			
			Scanner input = new Scanner(new File("./files/routes.txt"));
			
			input.useDelimiter(",|\\n");
			
			int index = 0;
			
			while (input.hasNextLine()) {
				
				routes[index].setSourceCity(input.next());
				routes[index].setDestinationCity(input.next());
				routes[index].setLength(input.nextInt());
				
				// Assigning a card colour to the route
				switch (input.next().toUpperCase()) {
				
					case "BLACK":
						routes[index].setColour(CardColour.BLACK);
						break;
						
					case "BLUE":
						routes[index].setColour(CardColour.BLUE);
						break;
						
					case "GREEN":
						routes[index].setColour(CardColour.GREEN);
						break;
						
					case "ORANGE":
						routes[index].setColour(CardColour.ORANGE);
						break;

					case "PURPLE":
						routes[index].setColour(CardColour.PURPLE);
						break;
				
					case "RED":
						routes[index].setColour(CardColour.RED);
						break;
						
					case "WHITE":
						routes[index].setColour(CardColour.WHITE);
						break;
						
					case "YELLOW":
						routes[index].setColour(CardColour.YELLOW);
						break;
						
					case "GRAY":
						routes[index].setColour(CardColour.RAINBOW);
						break;
						
				}
				
				routes[index].setxCoordinate(input.nextInt());
				routes[index].setyCoordinate(input.nextInt());
				routes[index].setDualRoute(input.nextBoolean());
				
				// Removes the extra line that appears b/w each city in the text file
				if (index < 99)
					input.next();
				
				index++;	// increment the index to import the information for the next route
			}
			
			// Close the file
			input.close();
			
		} catch (FileNotFoundException error) {
			System.out.println("Error:" + error);
		} //End of try and catch statement
		

// ----- Importing the ticket information ------------------------------------------------------------
// Jathusaa		
		// Initialize the tickets array
		for (int index = 0; index < tickets.length; index++)
			tickets[index] = new Ticket();
		
		
		try {
			
			Scanner input = new Scanner(new File("./files/tickets.txt"));
			
			input.useDelimiter(",|\\n");
			
			int index = 0;
			
			while (input.hasNextLine()) {
				
				tickets[index].setCity1(input.next());
				tickets[index].setCity2(input.next());
				tickets[index].setPointValue(input.nextInt());
				
				// Removes the extra line that appears b/w each city in the text file
				if (index < 49)
					input.next();
				
				index++;	// increment the index to import the information for the next ticket
			}
			
			// Close the file
			input.close();
			
		} catch (FileNotFoundException error) {
			System.out.println("Error:" + error);
		} //End of try and catch statement	
		
	} //End of constructor method
	
	
	public static Route [] getRoutes () {
		
		return routes;
		
	} //End of getRoutes method
	
	
	public static Ticket [] getTickets() {
		
		return tickets;
		
	} //End of getTickets method
	
	public static City [] getCities() {
		
		return cities;
		
	} //End of getTickets method
	
	

} //End of FileImportController