/*
 * Name: Jathusaa Indrakumaran
 * Date: Sunday, December 5th, 2021
 * Course: ICS4U1 - Mr. Fernandes
 * Notes: Class for the Save Game Additional features (NOT COMPLETE AND DOES NOT WORK)
 * Links: https://stackoverflow.com/questions/54858030/how-do-i-save-variables-after-closing-the-jframe-and-re-opening-it
 * 		  https://stackoverflow.com/questions/17293991/how-to-write-and-read-java-serialized-objects-into-a-file
 * 			
 */
//package controller;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//import java.util.ArrayList;
//
//import javax.swing.JFileChooser;
//
//import view.GameFrame;
//
////Save File ~Jathusaa~
////This class implements Serializable interface
//public class SaveFile implements Serializable {
//	
//	//Instance Variables 
//	ArrayList<String> saveFile = new ArrayList<String>(); //This array list will hold the data from the game
//
//	//Constructor 
//	public SaveFile(ArrayList<String> saveFile) {
//		super();
//		this.saveFile = saveFile;
//	}
//
//	//Getters and Setters
//	public ArrayList<String> getSaveFile() {
//		return saveFile;
//	}
//
//	public void setSaveFile(ArrayList<String> saveFile) {
//		this.saveFile = saveFile;
//	}
//	
//	//Utility Method 
//	
//	//Add String to saveFile Object 
//	private void addStringSaveFile() {
//		
//		//Add data from TrainCardController
//		saveFile.add(TrainCardController.deckOfCards.toString());
//		saveFile.add(TrainCardController.cardsArray.toString());
//		
//		//Add data from TTR Controller
//		saveFile.add(TTRController);
//		
//		//Add data of all the players current player information 
//		saveFile.add(TTRController.currentPlayer().getPlayerName());
//		saveFile.add(TTRController.currentPlayer().getClaimedRoutes());
//		saveFile.add("Volvo");
//	}
//	
//	//Serialize the saveFile Array
//	public void serializeFile() {
//		
//		FileOutputStream file = new FileOutputStream("G:\\address.ser");
//		ObjectOutputStream files = new ObjectOutputStream(file);
//		files.writeObject(saveFile);
//		
//		 try {
//		     streamIn = new FileInputStream("G:\\address.ser");
//		     ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
//		     MyClass readCase = (gameFrame) objectinputstream.readObject();
//		     saveFile.add(readCase);
//		     
//		     System.out.println(saveFile.get());
//		 } catch (Exception e) {
//		     e.printStackTrace();
//		 }
//		
//	}
//
//	
//	//Shows Dialog
//	public void showSaveDialog(GameFrame gameFrame) {
//		
//	}
//	
//	//creates a dialog for user to choose a location and type a file name to be saved
//	public void saveFileDialog()
//	{
//		// parent component of the dialog
//		GameFrame gameFrame = new GameFrame();
//		
//		JFileChooser fileChooser = new JFileChooser();
//		fileChooser.setDialogTitle("Specify a file to save");
//
//		int userSelection = fileChooser.showSaveDialog(this);
//		if (userSelection == JFileChooser.APPROVE_OPTION) {
//			File fileToSave = fileChooser.getSelectedFile();
//			System.out.println("Save as file: " + fileToSave.getAbsolutePath());
//		}
//		
//	}
//	
//
//	
//
//}
