/*
 * Name: Vidhi Ruparel
 * Date: December 5, 2021
 * Course: ICS4U1-02 Mr. Fernandes
 */

//package
package model;

//imports
import javax.swing.JLabel;


/**
 * Route Class is a template class that is used to create Route objects with 
 * set that holds its two terminal cities, along with its length, colour, 
 * completion point (location of the marker), the owner, and whether it is a dual route.
 */
public class Route extends JLabel {
	
	//instance variables
	private String sourceCity, destinationCity;
	private int length;
	private CardColour colour;
	private int xCoordinate, yCoordinate;
	private boolean dualRoute;
	private boolean acquired;
	private String owner;
	
	//no argument constructor
	public Route() {
			
	}
	
	//constructor
	public Route(String sourceCity, String destinationCity, int length, CardColour colour, int xCoordinate,
			int yCoordinate, boolean dualRoute) {
		super();
		this.sourceCity = sourceCity;
		this.destinationCity = destinationCity;
		this.length = length;
		this.colour = colour;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.dualRoute = dualRoute;
	}

	//getters and setters
	public String getSourceCity() {
		return sourceCity;
	}

	public void setSourceCity(String sourceCity) {
		this.sourceCity = sourceCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public CardColour getColour() {
		return colour;
	}

	public void setColour(CardColour colour) {
		this.colour = colour;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public boolean isDualRoute() {
		return dualRoute;
	}

	public void setDualRoute(boolean dualRoute) {
		this.dualRoute = dualRoute;
	}
	

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public boolean isAcquired() {
		return dualRoute;
	}

	public void setAcquisition(boolean acquired) {
		this.acquired = acquired;
	}

	//toString method
	@Override
	public String toString() {
		
		if (colour.equals(CardColour.RAINBOW))
			return "Route [sourceCity=" + sourceCity + ", destinationCity=" + destinationCity + ", length=" + length
					+ ", colour=GRAY" + ", xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + ", dualRoute="
					+ dualRoute + "]";
		
		return "Route [sourceCity=" + sourceCity + ", destinationCity=" + destinationCity + ", length=" + length
				+ ", colour=" + colour + ", xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + ", dualRoute="
				+ dualRoute + "]";
	}
	
}	//end of Route class
