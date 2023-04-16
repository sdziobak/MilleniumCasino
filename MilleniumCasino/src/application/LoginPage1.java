package application;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.w3c.dom.UserDataHandler;

import javafx.scene.control.PasswordField;

public class LoginPage1 implements ActionListener {
    Color c1 = new Color(255, 204, 51); 

    private static final String USER_FILE = "users.txt";
    private static Map<String, String> users = new HashMap<>();
    
    //setting up environment with labels and buttons
    JFrame frame = new JFrame("The Millennium Casino");


    //creating and naming the buttons
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
  
    //create a text field where the user will type in
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();

    //adding a "Welcome to the casino JLabel"
    JLabel welcomemsg = new JLabel("Welcome to the Millennium Casino!");
    JLabel stars = new JLabel("*********************************************");
    //adding a label 
    JLabel userIDLabel = new JLabel("User ID:");
    JLabel userPasswordLabel = new JLabel("Password:");

    //shows if login was successful or not
    JLabel messageLabel = new JLabel("");

    //add register button
    JButton registerButton = new JButton("Register");

    //add register label
    JLabel registerLabel = new JLabel("Don't have an account? Register now.");

    //constructor for login page
    LoginPage1(String USER_FILE){
 
        //setting bounds for the labels, text fields and buttons
        //adding fonts and colors
        
        userIDLabel.setBounds(280,170,75,25);
        userIDLabel.setFont(new Font(null, Font.BOLD,15));
        userIDLabel.setForeground(Color.WHITE);

        userPasswordLabel.setBounds(280,220,125,25);
        userPasswordLabel.setFont(new Font(null, Font.BOLD,15));
        userPasswordLabel.setForeground(Color.WHITE);

        messageLabel.setBounds(125,230,250,35);
        messageLabel.setFont(new Font(null,Font.ITALIC,18));

        registerLabel.setBounds(300, 340, 300, 25);
        registerLabel.setForeground(Color.WHITE);

        userIDField.setBounds(380,170,150,25);
        userPasswordField.setBounds(380,220,150,25);

        loginButton.setBounds(300,280,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        loginButton.setBackground(c1);
        loginButton.setFont(new Font(null, Font.BOLD,12));
        loginButton.setForeground(Color.BLACK);

        resetButton.setBounds(420,280,100,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener((java.awt.event.ActionListener) this);
        resetButton.setBackground(c1);
        resetButton.setFont(new Font(null, Font.BOLD,12));
        resetButton.setForeground(Color.BLACK);

        registerButton.setBounds(360, 380, 100, 25);
        registerButton.setFocusable(false);
        registerButton.addActionListener((java.awt.event.ActionListener) this);
        registerButton.setBackground(c1);
        registerButton.setFont(new Font(null, Font.BOLD, 12));
        registerButton.setForeground(Color.BLACK);
        
        welcomemsg.setBounds(210, 60, 500,30);
        welcomemsg.setFont(new Font(null, Font.ITALIC | Font.BOLD,25));
        Color c1 = new Color(255, 204, 51);  
        welcomemsg.setForeground(c1);
        stars.setBounds(190, 100, 500, 30);
        stars.setFont(new Font(null, Font.ITALIC | Font.BOLD,25));
        stars.setForeground(Color.RED);

        
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(welcomemsg);
        frame.add(registerLabel);
        frame.add(stars);

        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(registerButton);

        frame.setBackground(Color.ORANGE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);
        //makes the window appear in the center
        frame.setLocationRelativeTo(null); 


    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //if statements that when a user types
        //something and clicks the reset button the text dissappears
        if(e.getSource()==resetButton){
            userIDField.setText("");
            userPasswordField.setText("");
        }

        if(e.getSource()==loginButton){
            String username = userIDField.getText();
            @SuppressWarnings("deprecation")
			String password = userPasswordField.getText();

            if (application.RegistrationPage1.authenticateUser(username, password)) {
                frame.dispose();
                Welcomepage1 welcomepage = new Welcomepage1(username);

            } else {
                JFrame f1 = new JFrame();
                JOptionPane.showMessageDialog(f1, "Invalid username or password.","Alert",JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if(e.getSource()==registerButton) {
            frame.dispose();
            RegistrationPage1 registrationpage = new RegistrationPage1();
        
        }
    }

}
