package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


//import blackjackapp.BlackjackGame;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Welcomepage1 implements ActionListener{
    String userFile = application.RegistrationPage1.getUserFile();

    JFrame frame = new JFrame("The Millennium Casino");
    JLabel WelcomeLabel = new JLabel("Welcome");
    JLabel ReadyLabel = new JLabel("Ready to play?");
    JLabel gameprompt = new JLabel("Pick a game:");
    Color c1 = new Color(255, 204, 51); 
    Color c3 = new Color(0,153,0); 
    Color c4 = new Color(255,0,0); 

    JButton LogoutButton = new JButton("Logout");
    JButton coinflipbtn = new JButton("Coin Flip");
    JButton blackjackbtn = new JButton("Black Jack");

    //constructor
    Welcomepage1(String userID){
    	
    	coinflipbtn.addActionListener(this);
    	blackjackbtn.addActionListener(this);
    	
        WelcomeLabel.setFont(new Font(null,Font.BOLD,24));
        WelcomeLabel.setText("Welcome " + userID + "!");
        WelcomeLabel.setBounds(40,40,350,30);
        WelcomeLabel.setForeground(c1);

        ReadyLabel.setFont(new Font(null,Font.BOLD,24));
        ReadyLabel.setBounds(40,75,350,30);
        ReadyLabel.setForeground(c1);

        gameprompt.setFont(new Font(null, Font.BOLD, 16));
        gameprompt.setForeground(Color.white);
        gameprompt.setBounds(60,135,100,30);

        coinflipbtn.setBounds(250,145,100,30);
        coinflipbtn.setFocusable(false);
        coinflipbtn.setBackground(c4);
        coinflipbtn.setForeground(Color.BLACK);
        coinflipbtn.setFont(new Font(null, Font.BOLD,12));

        blackjackbtn.setBounds(250,235,100,30);
        blackjackbtn.setFocusable(false);
        blackjackbtn.setForeground(Color.WHITE);
        blackjackbtn.setBackground(c3);
        blackjackbtn.setFont(new Font(null, Font.BOLD,12));

        LogoutButton.setBounds(35,350,100,25);
        LogoutButton.setFocusable(false);
        LogoutButton.addActionListener((java.awt.event.ActionListener) this);
        LogoutButton.setBackground(c1);
        LogoutButton.setFont(new Font(null, Font.BOLD,12));
          
        frame.add(WelcomeLabel);
        frame.add(ReadyLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,450);
        frame.getContentPane().setBackground(Color.BLACK);
        
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); 

        frame.add(gameprompt);
        frame.add(coinflipbtn);
        frame.add(blackjackbtn);
        frame.add(LogoutButton);

    }
    

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==LogoutButton) {
            frame.dispose();
            LoginPage1 login = new LoginPage1(userFile);
        }

        if(e.getSource()==coinflipbtn) {
            frame.dispose();
            Platform.runLater(()  -> {
                Stage coinflipStage = new Stage();
                Coinflip cf = new Coinflip(coinflipStage, frame);
                cf.start(coinflipStage);
            });
        }

        if(e.getSource()==blackjackbtn) {
            frame.dispose();
            
            Platform.runLater(()  -> {
                Stage bjStage = new Stage();
                BlackJack bj = new BlackJack(bjStage, frame);
                bj.start(bjStage);
            });
        }
    }
}
