/*
 * Names: Tiffany, Jathusaa
 * Date: December 5th, 2021
 * Course: ICS4U1 - Mr. Fernandes
 * 
 * This class contains methods to manage the train cards including creating the train card deck, dealing the train cards, 
 * dealing a single train card to a specific player, flipping over five cards, checking for three rainbow cards showing, 
 * and flipping over the next card from the top of the deck
 */

package controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
// ===== IMPORTS =======================================================================================================
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.CardColour;
import model.TrainCard;
import view.CardPanel;




// Class that controllers the actions done with the train cards
public class TrainCardController{

// ===== INSTANCE VARIABLES ============================================================================================

	// Keeps track of how many cards the player took this turn to determine when to
	// end their turn (which is after 3 draws)
	public static ArrayList<TrainCard> cardsChosen = new ArrayList<TrainCard>(); // can check the length of array list

	// Stack of train cards in the deck
	public static Stack<TrainCard> deckOfCards = new Stack<TrainCard>();

	// Card panel buttons
	public static TrainCard[] cardsArray = CardPanel.getTrainCardsArray();
	public static JButton cardDeckButton = CardPanel.getCardDeckButton();
	public static JButton ticketCardButton = CardPanel.getTicketDeckButton();
	
	// Array that stores the number of each train card type to set new values and return to player
	private static int[] anything;
	
	private static int [] player1Cards, player2Cards, player3Cards, player4Cards;
	
	
	
	

// ===== Methods =======================================================================================================

	// ----- Constructor Method ----------------------------------------------------------------------------------------
	public TrainCardController() {

		createDeck();

	} // ends the constructor method
	
	
	// ----- createDeck Method ----------------------------------------------------------------------------------------
	// Creates the train card deck ~ Tiffany
	public void createDeck() {

		// Initialize the stack - 12 of each type, 14 locomotives (110 total cards)
		for (CardColour trainType : CardColour.values()) {

			int counter = 1;

			// Start at negative two so 14 locomotives are added to the stack
			if (trainType == CardColour.RAINBOW)
				counter -= 2;

			// Adds 12 of each card colour type
			do {

				if (counter <= 12)
					deckOfCards.add(new TrainCard(trainType));

				// Increment the counter
				counter++;

			} while (counter <= 12);

		}

		// Shuffle/randomize the cards (x2!)
		// https://stackoverflow.com/questions/27238673/shuffling-a-deck-of-cards-array-or-stack/27238836
		Collections.shuffle(deckOfCards);
		Collections.shuffle(deckOfCards);

	} // ends the method that creates the deck of train cards

	
	// ----- startDeal Method -----------------------------------------------------------------------------------------
	// Deal 4 cards to each player at the start of the game
	public static void startDeal(int indexOfCurrPlayer) {
		
		// Makes sure that the number of cards variable is cleared
		if (anything != null) {
			
			for(int index = 0; index < anything.length; index++) {
				
				anything[index] = 0;
				
			}
			
		}
		
		// Get 4 cards from the deck
		for (int cards = 0; cards < 4; cards++) {
			cardsChosen.add(deckOfCards.pop());
		}
		
		// Stores the anything array of the player into this class' anything array
		anything = TTRController.getCurrentPlayer().getNumOfCards();
	
		int index = 4; 	// Amount of cards being dealt
		
		// Deals the cards
		do {
			
			// Keeps track of which train card type to increase
			int colourIndex = 0;
			
			// Loops through all the train card types
			for (CardColour trainType : CardColour.values()) {
				
				// If the train card type matches the type of the card to give to the user
				if (trainType == cardsChosen.get(index-1).getColor()) {

					// Increment the number of cards the player has of the specific card
					anything[colourIndex]++;
				
					break;	// Already found the train card match
				}
				
				colourIndex++;	// Increment to look at the next chosen train card type
				
			}
			
			index--;	// Decrement the index until it reaches 0 (no more cards need to be dealt to this player)
		
		} while (index > 0);
		
		// Set the new card array for the player
		TTRController.getPlayer(indexOfCurrPlayer).setNumOfCards(anything);
		System.out.println("Im printing: " + Arrays.toString(TTRController.getPlayer(indexOfCurrPlayer).getNumOfCards()));
		
		// Removes all the elements from the cardsChosen array list
		cardsChosen.clear();
		
		System.out.println("\n\nDealt Cards");
		
	} // ends the method startDeal
	
	
	
	public static int[] getPlayer1Cards() {
		return player1Cards;
	}


	public static void setPlayer1Cards(int[] player1Cards) {
		TrainCardController.player1Cards = player1Cards;
	}


	public static int[] getPlayer2Cards() {
		return player2Cards;
	}


	public static void setPlayer2Cards(int[] player2Cards) {
		TrainCardController.player2Cards = player2Cards;
	}


	public static int[] getPlayer3Cards() {
		return player3Cards;
	}


	public static void setPlayer3Cards(int[] player3Cards) {
		TrainCardController.player3Cards = player3Cards;
	}


	public static int[] getPlayer4Cards() {
		return player4Cards;
	}


	public static void setPlayer4Cards(int[] player4Cards) {
		TrainCardController.player4Cards = player4Cards;
	}


	// ----- dealCards Method -----------------------------------------------------------------------------------------
	// Deal the cards to the players ~ Tiffany
	public static void dealCards(String place, int spot, int indexOfCurrPlayer) {

		// STH TO STORE THE CARDS WANTED

		// If chosen from the display
		if (place == "display") {

			// Add to the cards wanted
			cardsChosen.add(CardPanel.trainCardsArray[spot]);

			// Show a message to let the user know they selected a card
			// Show a message to let the user know they selected a card
			JOptionPane.showMessageDialog(null,
				    "You selected a " + cardsArray[spot].getColor() + " card!\n"
				    + "You can pick " + (2 - cardsChosen.size()) + " more train cards.");


		} // ends the if statement for choosing from deck

		// If picked from the deck
		if (place == "deck") {

			// Add to the cards wanted
			cardsChosen.add(deckOfCards.get(deckOfCards.size() - 1));
			
			// Show a message to let the user know they selected a card
			JOptionPane.showMessageDialog(null,
				    "You selected a " + deckOfCards.get(deckOfCards.size() - 1).getColor() + " card!\n"
				    + "You can pick " + (2 - cardsChosen.size()) + " more train cards.");
					

			// Remove the last card from the card deck
			deckOfCards.pop();

		} // ends the if statement for choosing from deck

		// Give to current player the cards wanted
		
		// Stores the anything array of the player into this class' anything array
		anything = TTRController.getPlayer(indexOfCurrPlayer).getNumOfCards();
		
		// Give the current player the cards they chose
		
		// Keeps track of which train card type to increase
		int colourIndex = 0;
		
		// Loops through all the train card types
		for (CardColour trainType : CardColour.values()) {
			
			// If the train card type matches the type of the card to give to the user
			if (trainType == cardsChosen.get(0).getColor()) {
			
				// Increment the number of cards the player has of the specific card
				anything[colourIndex]++;
				
				break;	// Already found the train card match
				
			}
			
			colourIndex++;	// Increment to look at the next chosen train card type
	
		}
		
		System.out.println(anything.toString());
		
		// Set the new card array for the player
		TTRController.getPlayer(indexOfCurrPlayer).setNumOfCards(anything);
		
		cardsChosen.clear();
		
		// Calls the method displays another card in the space
		displayCards(spot);
		
	} // ends the method that deals the train cards out

	
	// ----- displayCards Method --------------------------------------------------------------------------------------
	// flips over the next card in the deck onto the display ~ Jathusaa
	public static void displayCards(int spot) {

		if (spot >= 0) {
		
			// Keeps track of what spot the player previously choose from
			System.out.println(spot);
	
			// Choose the next card from the deck to replace
			TrainCard nextCard = deckOfCards.pop();
	
			// Make the Card on Display have the same color and image as the next card from the deck
			CardPanel.trainCardsArray[spot].setColor(nextCard.getColor());
			CardPanel.trainCardsArray[spot].setIcon(nextCard.getIcon());
		
		}
		
		is3Rainbow();
		
	} // ends the method that displays a few train cards

	
	// ----- flipAll Method -------------------------------------------------------------------------------------------
	// Flips 5 cards onto the display ~ Jathusaa
	public static void flipAll() {

		// Choose new 5 train cards from the deck
		for (int x = 0; x < CardPanel.trainCardsArray.length; x++) {

			TrainCard nextCard = deckOfCards.pop(); // Take the next card from the top of the deck

			// Choose the next card from the deck to replace the cards in the display
			CardPanel.trainCardsArray[x].setColor(nextCard.getColor()); // Add the next card to the displayed train
																		// cards
			CardPanel.trainCardsArray[x].setIcon(nextCard.getIcon()); 	// The card on display will clone the next card
																		// (Color and Image)

		}
		
		is3Rainbow();

	} // ends the method that flips 5 new cards onto the display

	
	// ----- is3Rainbow Method ----------------------------------------------------------------------------------------
	// Checks whether or not there are 3 rainbows on display ~ Jathusaa
	public static void is3Rainbow() {

		// Rainbow counter
		int countRain = 0;

		// Check the 5 train cards to see whether it's rainbow or not
		for (int x = 0; x < CardPanel.trainCardsArray.length; x++) {
			if (CardPanel.trainCardsArray[x].getColor() == CardColour.RAINBOW)
				countRain++;// Increment the counter if it is a rainbow train card

		}

		// If 3+ have been found, refresh the cards on display
		if (countRain >= 3)
			flipAll();

	} // ends the method that creates the deck of train cards

} // ends the class