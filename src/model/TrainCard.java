/*
 * Name: Vidhi Ruparel(50%), Tifffany(50%)
 * Date: Sunday December 5, 2021
 * Course: ICS4U1-02  Mr. Fernandes
 */

//packages
package model;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * TrainCard class is a template class that is used to create TrainCard objects with its colour as a field
 */
public class TrainCard extends JButton {

// ===== INSTANCE VARIABLES ================================================================
	private CardColour color;
	private ImageIcon cardImage;
	


// ===== METHODS ===========================================================================
	
	// ----- Constructor Method ------------------------------------------------------------
	public TrainCard(CardColour color) {
		super();
		this.color = color;
		setCardImage(color);
	}
	
	//Overloaded constructor 
	public TrainCard() {	//Needed so you can call the Train Cards at the start of the game without the color parameter

	}


	// ----- Getters and Setters ------------------------------------------------------------
	public CardColour getColor() {
		return color;
	}

	public void setColor(CardColour color) {
		this.color = color;
	}
	
	
	//utility methods
	public ImageIcon getCardImage() {
		return cardImage;
	}
	
	public void setCardImage(CardColour color) {
		
		if (CardColour.BLACK == color)
			cardImage = new ImageIcon("images/trainCardBlack.png");
		
		else if (CardColour.BLUE == color)
			cardImage = new ImageIcon("images/trainCardBlue.png");
		
		else if (CardColour.GREEN == color)
			cardImage = new ImageIcon("images/trainCardGreen.png");
		
		else if (CardColour.ORANGE == color)
			cardImage = new ImageIcon("images/trainCardOrange.png");
		
		else if (CardColour.PURPLE == color)
			cardImage = new ImageIcon("images/trainCardPurple.png");
		
		else if (CardColour.RAINBOW == color)
			cardImage = new ImageIcon("images/trainCardRainbow.png");
		
		else if (CardColour.RED == color)
			cardImage = new ImageIcon("images/trainCardRed.png");
		
		else if (CardColour.WHITE == color)
			cardImage = new ImageIcon("images/trainCardWhite.png");
		
		else if (CardColour.YELLOW == color)
			cardImage = new ImageIcon("images/trainCardYellow.png");	
		
		this.setIcon(cardImage);
		
	}

	//toString method
	@Override
	public String toString() {
		return "TrainCard [color=" + color + "]";
	}
	
	

}
