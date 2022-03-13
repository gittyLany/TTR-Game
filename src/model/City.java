/*
 * Name: Katherine Chun
 * Date: Sunday December 5, 2021
 * Course: ICS4U1-02  Mr. Fernandes
 * Title: TTR Canada - model - City Object
 * Description: Creating city objects with their attributes. Each city has a name and
 * 				a location of where they are according to the location on the Board
 * 				Panel. Location is in the format of an x and y coordinate.
 * Note:
 */


//PACKAGE
package model;



//IMPORTS
import javax.swing.*;



/**
 * Creating city objects that have a name and a location. (x and y coordinates)
 */
public class City extends JLabel {
	
	
// ===== INSTANCE VARIABLES ===========================================================================
	
	private String name;		//Name of the city
	private int x;				//x-coordinate of the city's location
	private int y;				//y-coordinate of the city's location
	
	
	
	
	
// ===== METHODS ===========================================================================
	
// ----- Constructor Method ---------------------------------------------------------------------------
	//Initializes the name and coordinates of the city
	public City (String name, int x, int y) {
	
		super();
		
		setName(name);
		setX(x);
		setY(y);
		
	} //End of constructor method
	
	
	public City () {
		
		super();
		
		setName("");
		setX(0);
		setY(0);
		
	} //End of constructor method


	
	
// ===== GET & SET METHODS ===========================================================================
	
// ----- Name Method ---------------------------------------------------------------------------
	//Setting and getting the name of city
	public String getName() {
		
		return name;
		
	} //End of getName method

	
	public void setName(String name) {
		
		this.name = name;
		
	} //End of setName method


	
// ----- X-Coordinate Method ---------------------------------------------------------------------------
	//Setting and getting x-coordinate of the city's location on the board panel
	public int getX() {
		
		return x;
	
	} //End of getX method

	
	public void setX(int x) {
		
		this.x = x;
		
	} //End of setX method

	
	
// ----- Y-Coordinate Method ---------------------------------------------------------------------------
	//Setting and getting y-coordinate of the city's location on the board panel
	public int getY() {
		
		return y;
		
	} //End of getY method


	public void setY(int y) {
		
		this.y = y;
		
	} //End of setY method
	
	
	
	
	
// ===== UTILITY METHODS ===========================================================================
	
// ----- toString Method ---------------------------------------------------------------------------
	//Displaying the name and coordinates of the city
	@Override
	public String toString () {
		
		return String.format("City Name: %s \nLocation: (%d, %d)", name, x, y);
		
	} //End of toString method
	
	
	
} //End of City class