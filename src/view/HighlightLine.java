/*
 * Name: Vidhi Ruparel
 * Date: December 5, 2021
 * Course: ICS4U1-02 Mr. Fernandes
 */

//package
package view;

//imports
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import controller.TTRController;
import model.City;
import model.Route;

/**
 * HighLightLine class draws a highlight line on routes with pointer circles at the source
 * and destination city so the player can see the routes available to claim.
 **/
public class HighlightLine extends JComponent {
    
	//instance variables
    private static final int ADJUST_X = 5, ADJUST_Y = 15;
    private static City[] cities = TTRController.getFileImport().getCities();
    
    //draws the highlight line
    public static void drawHighlightLine (Graphics g, Route route) {
        
    	//coordinates of the city
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        
        //finds the source and destination city coordinates
        for (City city : cities) {
        	if (city.getName().equals(route.getSourceCity())) {
        		x1 = city.getX() + ADJUST_X;
                y1 = city.getY() + ADJUST_Y;
        	}
        	
        	if (city.getName().equals(route.getDestinationCity())) {
                x2 = city.getX() + ADJUST_X;
                y2 = city.getY() + ADJUST_Y;
        	}
        }
        
        Graphics2D g2D = (Graphics2D) g;
        
        g2D.setColor(new Color(238, 130, 238, 150)); //violet coloured line
        g2D.setStroke(new BasicStroke(10));
        
        g.drawLine(x1, y1, x2, y2);					//line over the route
        
        g2D.setColor(new Color(255, 215, 0, 150)); //golden coloured circle
        
        g2D.fillOval(x1, y1, 10, 10);			   //circle at source city
        g2D.fillOval(x2, y2, 10, 10);			   //circle at destination city
        
        g2D.drawOval(x1, y1, 15, 15);			   //circle at source city
        g2D.drawOval(x2, y2, 15, 15);			   //circle at destination city
    }
    
}