/*
 * 
 * Names: Tiffany
 * Date: December 5th, 2021
 * Course: ICS4U1 - Mr. Fernandes
 * 
 * This method controls the music that plays
 * 
 * Sources: 
 * 
 * https://www.geeksforgeeks.org/play-audio-file-using-java/
 * 
 * http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
 * 
 * https://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java
 *
 */

package controller;

//===== IMPORTS ==================================================================================
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;





// Music and sound class
public class MusicController {
	
// ===== INSTANCE VARIABLES ======================================================================
	// Clips
	private static Clip themeMusicClip;
					
	// Status of the clip(for the theme music clip)
	public String status = "paused";
	
	// Stores the song clips current position
	private static long currentFrame = 0;
		

    

	
// ===== METHODS ==================================================================================
   
// ----- MUSIC METHODS ------------------------------------------------------------------------
  	// Game Theme
   	public void gameTheme() {
   	
   		// Calls the method that plays the audio
   		play();
   				
   	} // ends the method gameTheme
  

// ----- MUSIC CONTROL METHODS ----------------------------------------------------------------
	// Method to pause the audio
    public void pause() {
       
    	// Stops the music theme music
		themeMusicClip.stop();
		
		// Stores the position of the clip into currentFrame
		currentFrame = themeMusicClip.getMicrosecondPosition();
	
		// Changes the status of the clip to paused
		status = "paused";
    	
    } // ends the method pause
   	
    
   	// Method that starts the music
	private void play() {
	       
		   try {
			   	
			   	// Creates the file and the audioinputstream object
			    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sounds/theChicken.wav").getAbsoluteFile());
			    Clip clip = AudioSystem.getClip();
			    clip.open(audioInputStream);
	   
		 		// Store the clip in themeMusicClip
		 		themeMusicClip = clip;
			   
				// Changes the status of the clip to play
				status = "play";
				
				// Starts the song at where it ended off
				clip.setMicrosecondPosition(currentFrame);
				
				// Loops the game audio
				clip.loop(Clip.LOOP_CONTINUOUSLY);
   
			    clip.start(); //starts the clip
			 	
			    // Sets the volume
			    float volume = -35f;
			    
			 	// Adjusts the volume for the music
				FloatControl volumeDown = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); 
			 	volumeDown.setValue(volume); // Reduce volume to -35 decibels	
			    		 	 
		   }
		   
		   catch(Exception error) {
			   
			 // Error statement
		     System.out.println("Error with playing sound.");
		     error.printStackTrace( );
		     
		   } // ends try and catch
		        
	} // ends the method play
	
} // ends this class