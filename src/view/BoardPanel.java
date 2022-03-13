/*
 * Name: Tiffany Tang (50%), Vidhi Ruparel (50%)
 * Date: Sunday, December 5th, 2021
 * Course: ICS4U1 - Mr. Fernandes
 */

package view;



// ===== IMPORTS =====================================================================================
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.IOException;

import controller.TTRController;
import model.Route;


/**
 * Board panel class has a label to hold the game board image; 
 * this image will hold the labels for the cities and routes so 
 * they should also be added to the label
 * 
 * **Also performs adding check-marks for the RouteController
 *
 */
public class BoardPanel extends JPanel {


// ===== INSTANCE VARIABLES ==========================================================================
	
	// JLabel Images
	private static JLabel blueCheckLabel;
	private static JLabel greenCheckLabel;
	private static JLabel redCheckLabel;
	private static JLabel yellowCheckLabel;

	// Board Image JLabel
	public static JLabel boardImageLabel = new JLabel(new ImageIcon("./images/board.png"));	// Create an icon with the bingo card png

	Route route;
	
	
		

  
	
	
// ===== METHODS ======================================================================================
	
	// ----- Constructor Method -------------------------------------------------------------------
	public BoardPanel () {
			
		// Sets up the check mark images
		try {
			setCheckMarkImages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Set up the board image label
		setupBoard();
		
	} // ends the constructor

	
	// ----- Utility Method -----------------------------------------------------------------------
	
	// ----- Setup Method -------------------------------------------------------------------------
	// Add the game board onto the panel
	public void setupBoard() {
		
		boardImageLabel.setBounds(0, 0, 1400, 900);					// Sizing and location
		add(boardImageLabel);										// Put the label onto the panel

	} // ends the method that sets up the board panel
	
	
	// ----- setCheckMarkImages Method ---------------------------------------------------------------
	public void setCheckMarkImages() throws IOException {
		
		// Create a new label and then set the icon of the label with the check mark image
		yellowCheckLabel = new JLabel();
		yellowCheckLabel.setIcon(new ImageIcon("./images/checkmarkYellow.png"));
		
		greenCheckLabel = new JLabel();
		greenCheckLabel.setIcon(new ImageIcon("./images/checkmarkGreen.png"));
		
		blueCheckLabel = new JLabel();
		blueCheckLabel.setIcon(new ImageIcon("./images/checkmarkBlue.png"));
		
		redCheckLabel = new JLabel();
		redCheckLabel.setIcon(new ImageIcon("./images/checkmarkRed.png"));
		
		// Set the bounds and then add the check mark image labels onto the panel
		yellowCheckLabel.setBounds(2000, 0, 15, 15);
		boardImageLabel.add(yellowCheckLabel);
		yellowCheckLabel.setVisible(false);
		
		greenCheckLabel.setBounds(2000, 0, 15, 15);
		boardImageLabel.add(greenCheckLabel);
		greenCheckLabel.setVisible(false);
		
		blueCheckLabel.setBounds(2000, 0, 15, 15);
		boardImageLabel.add(blueCheckLabel);
		blueCheckLabel.setVisible(false);
		
		redCheckLabel.setBounds(2000, 0, 15, 15);
		boardImageLabel.add(redCheckLabel);
		redCheckLabel.setVisible(false);
		
		//yellowCheckLabel.setIcon(icon);
		
//		checkIcon = ImageIO.read(new File("./images/checkmarkBlue.png"));
//		blueCheckLabel = new JLabel(new ImageIcon(checkIcon));
//		
//		checkIcon = ImageIO.read(new File("./images/checkmarkGreen.png"));
//		greenCheckLabel = new JLabel(new ImageIcon(checkIcon));
//		System.out.println("YOYO");
//		checkIcon = ImageIO.read(new File("./images/checkmarkRed.png"));
//		redCheckLabel = new JLabel(new ImageIcon(checkIcon));
//		
//		checkIcon = ImageIO.read(new File("./images/checkmarkYellow.png"));
//		yellowCheckLabel = new JLabel(new ImageIcon(checkIcon));
		
	} // ends the method that sets the check mark images
	
	
	// ----- placeCheckMarks Method ---------------------------------------------------------------
	// Add check mark images to routes that are claimed
	public void placeCheckMarks(Route route) {
		
		//this.route = route;
		
		System.out.println("ARRIVED AT: placeCheckMarks method");
		
		//JLabel checkImage;						// Stores which check mark image to use

		// Determines which colour check mark image should be used based on the player
		switch (TTRController.currentPlayer.getPlayerName()) {
		
		case "Player 1":
			
			yellowCheckLabel.setBounds(route.getxCoordinate(), route.getyCoordinate(), 15, 15);	// Position and sizing of the image/label
			System.out.println(route.getxCoordinate() + " " + route.getyCoordinate());
			//yellowCheckLabel.isOpaque();
			yellowCheckLabel.setVisible(true);
			boardImageLabel.add(yellowCheckLabel);
			
			System.out.print("YELLOW");
			break;
			
		case "Player 2":
			
			greenCheckLabel.setBounds((int)route.getxCoordinate(), (int)route.getyCoordinate(), 15, 15);	// Position and sizing of the image/label
			//greenCheckLabel.isOpaque();
			
			greenCheckLabel.setVisible(true);
			boardImageLabel.add(greenCheckLabel);
			
			//checkImage = greenCheckLabel;
			System.out.print("GREEN");
			break;
			
		case "Player 3":
			
			blueCheckLabel.setBounds((int)route.getxCoordinate(), (int)route.getyCoordinate(), 15, 15);	// Position and sizing of the image/label
			//blueCheckLabel.isOpaque();
			
			blueCheckLabel.setVisible(true);
			boardImageLabel.add(blueCheckLabel);
			
			//checkImage = blueCheckLabel;
			System.out.print("BLUE");
			break;
			
		case "Player 4":
			
			redCheckLabel.setBounds((int)route.getxCoordinate(), (int)route.getyCoordinate(), 15, 15);	// Position and sizing of the image/label
			//redCheckLabel.isOpaque();
			
			redCheckLabel.setVisible(true);
			boardImageLabel.add(redCheckLabel);
			
			//checkImage = redCheckLabel;
			System.out.print("RED");
			break;
			
		default:
			//checkImage = yellowCheckLabel;
			System.out.println("Playing missing error");
		
		}
		
		//TTRController.getGameFrame().add(TTRController.getGameFrame().getBoardPanel());
		//TTRController.getGameFrame().getBoardPanel().setVisible(true);
		//TTRController.getGameFrame().setVisible(true);
		
//		if (checkImage == null)
//			checkImage = yellowCheckLabel;
//		
//		boardLabel.add(checkImage);
		
		// Create a JLabel for each city
//		checkImage.setBounds(route.getX(), route.getY(), 15, 15);	// Position and sizing of the image/label
//		checkImage.isOpaque();
//		checkImage.setVisible(true);
		
		// Add the image onto the board panel, and the panel back onto the frame
//			boardImageLabel.add(checkImage); // double add and still doesn't work T_T

//			TTRController.getGameFrame().getScorePanel().add(checkImage);
//			TTRController.getGameFrame().getScorePanel().repaint();
		
		
		//TTRController.getGameFrame().add(TTRController.getGameFrame().getBoardPanel());
		
		
		System.out.println("ADDED!!! PLEASE!!");
		
		revalidate();
		repaint();													// Refreshes the panel so the new additions are visible
		
		System.out.println("END OF placeCheckMarks method");
		
		
	} //  ends the method place check marks
		
//	public void paintComponent(Graphics g) {
//		
//        try {
//        	if (route != null)
//        		g.drawImage(ImageIO.read(new File("./images/checkmarkYellow.png")), route.getX(), route.getY(), this);
//        
//            g.drawImage(ImageIO.read(new File("./images/board.png")), 0, 0, this);
//        } catch (IOException ex) {
//            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
		
	  
} // ends the class