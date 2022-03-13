/*
 * Names: Tiffany
 * Date: December 5th, 2021
 * Course: ICS4U1 - Mr. Fernandes
 */

package view;



//===== IMPORTS ====================================================================================
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;





// Help Window GUI
public class HelpFrame extends JFrame implements ActionListener {

// ===== INSTACE VARIABLES ==========================================================================
	
	// Panels
	private static JPanel helpPanel = new JPanel();
	private static JPanel informationPanel = new JPanel();
	
	// Array
	private static JButton[] helpImageButtonArray = new JButton[2];
	
	// JLabel
	private static JLabel helpContentLabel = new JLabel();
	
	// Text Area
	private static JTextArea helpTextArea = new JTextArea();

	// Menu bar
	private static JMenuBar menubar = new JMenuBar();
	
	// Borders
	private static Border frameBorder = BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Color.BLUE), BorderFactory.createLineBorder(Color.PINK));
	private static Border buttonBorder = BorderFactory.createBevelBorder(
			0, Color.WHITE, Color.YELLOW, Color.DARK_GRAY, Color.ORANGE);
	
	// Image Icons
	private static ImageIcon gameTurnIcon = new ImageIcon("./images/gameTurn.png");
	private static ImageIcon componentsIcon = new ImageIcon("./images/components.png");
	
	// Fonts
	private static Font buttonFont = new Font("Verdana", Font.HANGING_BASELINE, 18);
	private static Font textFont = new Font("Verdana", Font.BOLD, 14);
	
	
	
// ===== METHODS =================================================================================	
	
	// ----- Constructor Method ------------------------------------------------------------------
	public HelpFrame() {
		
		// Call the methods that sets up the frames and panels within
		informationPanelSetup();
		helpPanelSetup();
		frameSetup();		
		
 	} // ends the constructor method


	// ----- Getters and Setters ------------------------------------------------------------------
	public static JMenuBar getMenubar() {
		return menubar;
	}

	public void setMenubar(JMenuBar menubar) {
		this.menubar = menubar;
	}
	
	
	// ----- info Panel Setup Method ---------------------------------------------------------------
	private void informationPanelSetup() {
		
		// Panel set up
		informationPanel.setBounds(100, 150, 1720, 880);	// Sizing and position of panel
		informationPanel.setLayout(null);					// Null layout
		informationPanel.setBackground(getBackground()); 	// Sets the background to the frame's background
		
		// Set up the image label
		helpContentLabel.setBackground(getBackground()); 	// Sets the background to the frame's background
		
		// Set up the text area
		helpTextArea.setBackground(getBackground());		// Sets the background to the frame's background
		helpTextArea.setLineWrap(true); 					// Wraps the text so that it fits instead the text area
		helpTextArea.setFont(textFont); 					// Sets the font of the text to the text font Font created
	
	}
	
	
	// ----- help Panel Setup Method ---------------------------------------------------------------
	private void helpPanelSetup() {
		
		// Sets up the help panel
		helpPanel.setBounds(0, 0, 1920, 150);				// Sizing and position of panel
		helpPanel.setLayout(null);							// Null layout
		helpPanel.setBackground(getBackground()); 			// Sets the background to the frame's background
		
		// For loop creating the help image buttons
		for (int index = 0; index < helpImageButtonArray.length; index++) {
			
			// Creates a JButton offering different help options
			helpImageButtonArray[index] = new JButton();
			helpImageButtonArray[index].setBounds(300 + 760 * index, 75, 560, 50);
			helpImageButtonArray[index].setBorder(buttonBorder);
			helpImageButtonArray[index].setHorizontalAlignment(SwingConstants.CENTER);
			helpImageButtonArray[index].setFont(buttonFont);
			helpImageButtonArray[index].addActionListener(this);
			helpPanel.add(helpImageButtonArray[index]);
						
		} // ends the for loop
		
		// Sets the content of the buttons
		helpImageButtonArray[0].setText("RULES");
		helpImageButtonArray[1].setText("COMPONENTS / GAME SET UP");
		
	} // ends the method helpPanelSetup

	// ----- Frame Setup Method ------------------------------------------------------------------
	private void frameSetup() {
		
		// Sets up the frame
		setSize(1920, 1080);					// Sizing
		setResizable(false);					// Makes the size not adjustable
		setLayout(null);						// Null layout - manually place components using coordinates
		getRootPane().setBorder(frameBorder);	// Adds a border onto the frame
		
		// Set the menu bar to the frame
		setJMenuBar(getMenubar());
		
		// Disposes of the frame (but keeps the application running) when exited out of
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// Add the panels onto the frame
		add(helpPanel);
		add(informationPanel);
		
		// Displays the frame
		setVisible(true);
		
	} // ends the method frameSetup

		
	// ----- actionPerformed Method ---------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent event) {
		
		// Check if the user clicked on the help images to provide a message
		if (event.getSource() == helpImageButtonArray[0]) {
			
			// Set the text and icon to display the rules
			helpTextArea.setText("Ticket to Ride is a cross-country train adventure.\r\n"
					+ "Players compete to connect different cities by laying claim to railway routes on a map of North America.");
			helpContentLabel.setIcon(gameTurnIcon);
			

			helpContentLabel.setBounds(0, 0, 850, 880); 		// Sizing and position of the label (fill the left portion of the panel)
			helpTextArea.setBounds(850, 0, 870, 880);			// Sizing and position of the text area(right side of panel)
			
		}
		
		else if (event.getSource() == helpImageButtonArray[1]) {
			
			// Set the icon of the label to the components image
			helpContentLabel.setIcon(componentsIcon);	
			helpTextArea.setText("");
			
			// Sizing and position of the label (fill whole panel)
			helpContentLabel.setBounds(0, 0, 1720, 880);		// change to 500 if adding text underneath
			helpContentLabel.setHorizontalAlignment(SwingConstants.CENTER);
			//helpTextArea.setBounds(0, 500, 1720, 380);	
			
		}
		
		// Adds the image label + text area onto the help panel
		informationPanel.add(helpContentLabel);
		informationPanel.add(helpTextArea);
		
		// Refreshes the panel so the new additions are visible
		repaint();
		
	} // ends the actionPerformed method

	
} // ends the class
