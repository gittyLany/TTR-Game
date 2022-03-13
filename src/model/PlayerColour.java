/*
 * Name: Katherine Chun
 * Date: Sunday December 5, 2021 
 * Course: ICS4U1-02  Mr. Fernandes
 * Title: TTR Canada - model - PlayerColour (enum)
 * Description: Creating constants for player colours. Constants have the rgb code
 * 				for each colour as integers. The first integer is the red code, the
 * 				second integer is the green code, and the third integer is the blue code.
 * Notes: 
 */



//PACKAGE
package model;



public enum PlayerColour {

	YELLOW (255, 235, 128),			//RGB code for the yellow player
	GREEN (206, 245, 153),			//RGB code for the green player
	BLUE (177, 236, 247),			//RGB code for the blue player
	RED (202, 121, 121);			//RGB code for the red player
	
	private int r;		//Holds the red code for the rgb code
	private int g;		//Holds the green code for rgb code
	private int b;		//Holds the blue code for te rgb code
	
	
	//Initializes the rgb code of each colour
	private PlayerColour (int r, int b, int g) {
		
		this.r = r;
		this.g = g;
		this.b = b;
		
	} //End of constructor method (private constructor)
	
	
	
} //End of PlayerColour enum
