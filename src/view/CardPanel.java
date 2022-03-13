/*
 * Name: Jathusaa Indrakumaran
 * Date: Sunday, December 5th, 2021
 * Course: ICS4U1 - Mr. Fernandes
 */
package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.TrainCardController;
import model.TrainCard;

public class CardPanel extends JPanel {

	// ===== INSTANCE VARIABLES
	// ===========================================================================

	// Labels
	private JLabel cardPanelTitleLabel = new JLabel(" CARD PANEL ", SwingConstants.CENTER);

	private JLabel ticketDeckLabel = new JLabel(" TICKET DECK ", SwingConstants.CENTER);
	private JLabel cardDeckLabel = new JLabel(" CARD DECK ", SwingConstants.CENTER);

	// Image Buttons
	private static ImageIcon ticketDeckimage = new ImageIcon("images/ticketDeck.png");
	private static JButton ticketDeckButton = new JButton(ticketDeckimage);

	private static ImageIcon cardDeckimage = new ImageIcon("images/cardBack.png");
	private static JButton cardDeckButton = new JButton(cardDeckimage);

	// Train Card Array
	public static TrainCard[] trainCardsArray = new TrainCard[5];

	// ===== METHODS
	// ===========================================================================

	// ----- Constructor Method
	// ---------------------------------------------------------------------------
	public CardPanel() {

		// Panel Setup
		setLayout(null); // Null layout – place GUI objects using coordinates
		setBackground(Color.WHITE); // Sets the background color of the panel to
									// white

		// Paste Title Labels onto the panel
		cardPanelTitleLabel.setBounds(800, 5, 150, 25);
		ticketDeckLabel.setBounds(15, 5, 150, 25);
		cardDeckLabel.setBounds(185, 5, 150, 25);
		
		add(cardPanelTitleLabel);
		add(ticketDeckLabel);
		add(cardDeckLabel);

		// Paste the button images onto the panel
		ticketDeckButton.setBounds(25, 30, 125, 75);
		add(ticketDeckButton);

		cardDeckButton.setBounds(200, 30, 125, 75);
		add(cardDeckButton);

		addTrainCards();

	}// ends constructor

	// ----- Getters and Setters
	// ------------------------------------------------------------------------
	public JLabel getCardPanelTitleLabel() {
		return cardPanelTitleLabel;
	}

	public void setCardPanelTitleLabel(JLabel cardPanelTitleLabel) {
		this.cardPanelTitleLabel = cardPanelTitleLabel;
	}

	public JLabel getTicketDeckLabel() {
		return ticketDeckLabel;
	}

	public void setTicketDeckLabel(JLabel ticketDeckLabel) {
		this.ticketDeckLabel = ticketDeckLabel;
	}

	public JLabel getCardDeckLabel() {
		return cardDeckLabel;
	}

	public void setCardDeckLabel(JLabel cardDeckLabel) {
		this.cardDeckLabel = cardDeckLabel;
	}

	public static JButton getTicketDeckButton() {
		return ticketDeckButton;
	}

	public void setTicketDeckButton(JButton ticketDeckButton) {
		this.ticketDeckButton = ticketDeckButton;
	}

	public static JButton getCardDeckButton() {
		return cardDeckButton;
	}

	public void setCardDeckButton(JButton cardDeckButton) {
		this.cardDeckButton = cardDeckButton;
	}

	public static TrainCard[] getTrainCardsArray() {
		return trainCardsArray;
	}

	public static void setTrainCardsArray(TrainCard[] trainCardsArray) {
		CardPanel.trainCardsArray = trainCardsArray;
	}

	// ----- Utility Method
	// ---------------------------------------------------------------------

	// Display Train Card Buttons
	private void addTrainCards() {

		// Train Card Controller will generate 5 new cards from the the card deck

		for (int x = 0; x < trainCardsArray.length; x++) {

			//Will create new 5 train card buttons
			trainCardsArray[x] = new TrainCard();
			trainCardsArray[x].setBounds(475 + 175 * x, 30, 125, 75);
			
			// Add train card buttons onto the panel
			add(trainCardsArray[x]);

		}
		


	}

}