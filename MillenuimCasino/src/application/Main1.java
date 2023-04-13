package application;

import javax.swing.SwingUtilities;

import CoinFlip.CoinFlipFX;
import javafx.application.*;

public class Main1 {
    public static void main(String[] args) {
    	
    	// Initialize JavaFX toolkit
        Platform.startup(new Runnable() {
        	public void run() {
        		
        	}
        });

        // Launch the Swing application
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CoinFlipFX();
            }
        });
    	
    	
    	
        IDandPasswords1 idandPasswords = new IDandPasswords1();

        //creating an instance of the login page
        LoginPage1 loginPage = new LoginPage1(idandPasswords.getLoginInfo());
        
        
    }
}
