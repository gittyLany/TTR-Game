/*
 * Name: Katherine Chun
 * Date: Sunday December 5, 2021
 * Course: ICS4U1-02  Mr. Fernandes
 * Title: TTR Canada - model - Ticket Object
 * Description: Creating ticket objects with its attributes. Each ticket
 * 				has the names of the two cities that are trying to be
 * 				connected and the amount of points awarded for connecting
 * 				the two cites together.
 * Note:
 */

//PACKAGE
package model;

import java.util.Arrays;

public class Ticket {
	
	
// ===== INSTANCE VARIABLES ===========================================================================
	private String city1;				//First terminal city
	private String city2;				//Second terminal city
	private int pointValue;				//The points awarded if the two cities are connected
	private boolean completed;			//Tells whether the ticket's route is completed
	
	
	
	
	
// ===== METHODS ===========================================================================

// ----- Constructor Method ---------------------------------------------------------------------------
	//Initializing the attributes of each ticket
	public Ticket (String city1, String city2, int pointValue) {
		
		super();
		
		setCity1(city1);
		setCity2(city2);
		setPointValue(pointValue);
		setCompleted(false);
		
	} //End of constructor method
	
	public Ticket () {
		
		super();
		
		setCity1("");
		setCity2("");
		setPointValue(0);
		setCompleted(false);
		
	} //End of constructor method



	
	
// ===== GET & SET METHODS ===========================================================================

// ----- City 1 Method ---------------------------------------------------------------------------
	//Setting and getting the first terminal city's name
	public String getCity1() {
		
		return city1;
		
	} //End of getCity1 method

	
	public void setCity1(String city1) {
		
		this.city1 = city1;
		
	} //End of setCity1 method



// ----- City 2 Method ---------------------------------------------------------------------------
	//Setting and getting the second terminal city's name
	public String getCity2() {
		
		return city2;
		
	} //End of getCity2 method


	public void setCity2(String city2) {
		
		this.city2 = city2;
		
	} //End of setCity2 method



// ----- Point Value Method ---------------------------------------------------------------------------
	//Setting and getting the point value for completing the route
	public int getPointValue() {
		
		return pointValue;
		
	} //End of getPointValue method

	
	public void setPointValue(int pointValue) {
		
		this.pointValue = pointValue;
		
	} //End of setPointValue method
	
	
	
// ----- Completed Method ---------------------------------------------------------------------------
	//Setting and getting if the two cities on the ticket are connected
	public boolean getCompleted () {
		
		return completed;
		
	} //End of getCompleted method

	
	public void setCompleted(boolean completed) {
		
		this.completed = completed;
		
	} //End of setCompleted method
	
	
	
	
	
	
// ===== UTILITY METHODS ===========================================================================

// ----- toString Method ---------------------------------------------------------------------------
	//Displays the attributes of each ticket object
	@Override
	public String toString ()  {
		
		return String.format("Terminal Cities: %s to %s \tPoints: %d \tCompleted: %b", city1, city2, pointValue, completed);
		
	} //End of toString method
	
	
	
	
	
	
} //End of Ticket class