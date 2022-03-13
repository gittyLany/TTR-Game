/*
 * Names: Tiffany
 * Date: December 5th, 2021
 * Course: ICS4U1 - Mr. Fernandes
 */

package controller;



//===== IMPORTS ==================================================================================
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.GameFrame;
import view.HelpFrame;





public class MenuBarController extends JMenuBar implements ActionListener {

// ===== INSTANCE VARIABLES ======================================================================
	
	// Create menu bar
	private static JMenuBar menubar = GameFrame.getMenubar();
	private static JMenuBar helpFrameMenubar = HelpFrame.getMenubar();
	
	// Create menus
	private static JMenu fileMenu = new JMenu("File");				// The file menu that offers different game options
	private static JMenu helpMenu = new JMenu("Help");				// The help menu

	private static JMenu helpFramefileMenu = new JMenu("File");		// The file menu
	
	// Create menu items
	private static JMenuItem[] fileArray = new JMenuItem[2];		// The game options
	private static JMenuItem helpMenuItem = new JMenuItem("Help");	// Help menu item
	
	private static JMenuItem exitMenuItem = new JMenuItem("Exit");	// Exit menu item
	
	// Help frame
	public HelpFrame helpFrame;
	
	
	
	
	
// ===== METHODS =================================================================================	
	
	// ----- Constructor Method ------------------------------------------------------------------
	public MenuBarController() {
		
		// Calls the method that sets up the menu bar
		createMenu();
		
	} // ends the constructor method
	

	// ----- Getters and Setters ------------------------------------------------------------------
	public static JMenuBar getMenubar() {
		return menubar;
	}


	public static void setMenubar(JMenuBar menubar) {
		MenuBarController.menubar = menubar;
	}


	public static JMenu getFileMenu() {
		return fileMenu;
	}


	public static void setFileMenu(JMenu fileMenu) {
		MenuBarController.fileMenu = fileMenu;
	}


	public JMenu getHelpMenu() {
		return helpMenu;
	}


	public void setHelpMenu(JMenu helpMenu) {
		MenuBarController.helpMenu = helpMenu;
	}


	public static JMenuItem[] getFileArray() {
		return fileArray;
	}


	public static void setFileArray(JMenuItem[] fileArray) {
		MenuBarController.fileArray = fileArray;
	}


	public static JMenuItem getHelpMenuItem() {
		return helpMenuItem;
	}


	public static void setHelpMenuItem(JMenuItem helpMenuItem) {
		MenuBarController.helpMenuItem = helpMenuItem;
	}
	
	
	
	// ----- createMenu Method --------------------------------------------------------------------
	private void createMenu() {
		
		// Creates the menu bar for the game frame
		// Creates the menu items in the file array
		fileArray[0] = new JMenuItem("Start/Pause Music");
		fileArray[1] = new JMenuItem("Exit");
		
		// Assigns each item to the file menu
		for (JMenuItem item: fileArray) {
			
			fileMenu.add(item);					// Menu item is added onto the file menu
			item.addActionListener(this);		// Add an action listener to each of the menu items
		
		}
		
		helpMenu.add(helpMenuItem);				// Menu item added onto the help menu
		helpMenuItem.addActionListener(this);	// Adds an action listener to the menu item
		
		// Menu bar setup
		menubar.add(fileMenu);					// Attach the file menu to the menu bar
		menubar.add(helpMenu);					// Attach the help menu to the menu bar
		
		// Creates the menu bar for the help frame
		helpFramefileMenu.add(exitMenuItem);	// Menu item added onto the help menu
		exitMenuItem.addActionListener(this);	// Adds an action listener to the menu item
		
		// Menu bar setup
		helpFrameMenubar.add(helpFramefileMenu);// Attach the file menu to the menu bar
		
	}
	

	// ----- actionPerformed Method --------------------------------------------------------------------
	// This method deals with the actions and events (when user clicks on the menu bar)
	@Override
	public void actionPerformed(ActionEvent event) {
		
		// Perform desired actions when menu items are selected by the user
		
		// Open the help GUI
		if (event.getSource() == helpMenuItem) {
			
			// Creates a new help frame
			helpFrame = new HelpFrame();
			// maybe set the game frame visible(false)
			
		}
		
		// "sound" menu item
		else if (event.getSource() == fileArray[0]) {
					
			// Checks if the background music is paused or already playing by checking the clip status
			if (TTRController.getMusic().status == "play")
				
				// Pauses the background music
				TTRController.getMusic().pause();
				
			else if (TTRController.getMusic().status == "paused")
				
				// Resumes the background music
				TTRController.getMusic().gameTheme();
			
		}
		
		// "exit" menu item
		else if (event.getSource() == fileArray[1]) {
					
			// Close the program
			System.exit(0);
			
		}
		
		// Close the help frame to allow the user to return back to the game
		else if (event.getSource() == exitMenuItem) {
			
			helpFrame.dispose();
			
		}
		
	} // ends the actionPerformed method

	
} // ends the class