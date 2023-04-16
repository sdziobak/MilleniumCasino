package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.security.*;
import java.io.*;

public class RegistrationPage1 implements ActionListener{

    private static final String USER_FILE = "users.txt";

    Color c1 = new Color(255, 204, 51); 

    JFrame frame = new JFrame("The Millennium Casino");
    JLabel player = new JLabel("Player Registration");
    JLabel RegistrationLabel = new JLabel("Enter your information below to register");
    JLabel UsernameLabel = new JLabel("User ID:");
    JLabel PasswordLabel = new JLabel("Password:");
    JLabel ConfirmPasswordLabel = new JLabel("Confirm password:");

    JLabel DOBLabel = new JLabel("Date of Birth:");

    String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String days[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26",
         "27","28","29","30","31"};
    String years[] = {"2013","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000","1999","1998","1997",
         "1996","1995","1994","1993","1992","1991","1990","1989","1988","1987","1986","1985","1984","1983","1982","1981","1980","1979","1978",
         "1977","1976","1975","1974","1973","1972","1971","1970","1969","1968","1967","1966","1965","1964","1963","1962","1961","1960","1959",
         "1958","1957","1956","1955","1954","1953","1952","1951","1950"};
    JComboBox daysComboBox = new JComboBox<>(days);
    JComboBox monthsComboBox = new JComboBox<>(months);
    JComboBox yearsComboBox = new JComboBox<>(years);

    JTextField UsernameField = new JTextField();
    JPasswordField PasswordField = new JPasswordField();
    JPasswordField ConfirmPasswordField = new JPasswordField();

    JButton RegisterButton = new JButton("Register");
    JButton BackButton = new JButton("Back");
    JButton ResetButton = new JButton("Reset");

    RegistrationPage1(){
        Color c2 = new Color(204,204,204); 
       
       RegistrationLabel.setBounds(250,80,350,35);
       RegistrationLabel.setFont(new Font(null,Font.BOLD | Font.ITALIC,14));
       RegistrationLabel.setForeground(Color.WHITE);


       player.setBounds(50,30,350,35);
       player.setFont(new Font(null,Font.PLAIN | Font.BOLD,23));
       player.setForeground(c1);
       
       UsernameLabel.setBounds(210,130,200,45);
       UsernameLabel.setFont(new Font(null, Font.BOLD, 15));
       UsernameLabel.setForeground(Color.WHITE);

       PasswordLabel.setBounds(210, 180, 200, 45 );
       PasswordLabel.setFont(new Font(null, Font.BOLD, 15));
       PasswordLabel.setForeground(Color.WHITE);

       ConfirmPasswordLabel.setBounds(210, 230, 200, 45 );
       ConfirmPasswordLabel.setFont(new Font(null, Font.BOLD, 15));
       ConfirmPasswordLabel.setForeground(Color.WHITE);

       UsernameField.setBounds(370, 140, 200, 25);
       PasswordField.setBounds(370, 190, 200, 25);
       ConfirmPasswordField.setBounds(370, 240, 200, 25);

       DOBLabel.setBounds(210, 295, 200, 45);
       DOBLabel.setFont(new Font(null, Font.BOLD, 15));
       DOBLabel.setForeground(Color.WHITE);


       daysComboBox.setBounds(370, 305, 70, 20);
       monthsComboBox.setBounds(450, 305, 95, 20);
       yearsComboBox.setBounds(555, 305, 75, 20);
       daysComboBox.setBackground(Color.WHITE);

       RegisterButton.setBounds(295,380,100,25);
       RegisterButton.setFocusable(false);
       RegisterButton.addActionListener((java.awt.event.ActionListener) this);
       RegisterButton.setBackground(c1);
       RegisterButton.setFont(new Font(null, Font.BOLD,12));
       RegisterButton.setForeground(Color.BLACK);


       ResetButton.setBounds(415, 380, 100, 25);
       ResetButton.setFocusable(false);
       ResetButton.addActionListener((java.awt.event.ActionListener) this);
       ResetButton.setBackground(c1);
       ResetButton.setFont(new Font(null, Font.BOLD,12));
       ResetButton.setForeground(Color.BLACK);

       BackButton.setBounds(80,455,100,25);
       BackButton.setFocusable(false);
       BackButton.addActionListener((java.awt.event.ActionListener) this);
       BackButton.setBackground(c1);
       BackButton.setFont(new Font(null, Font.BOLD,12));
       BackButton.setForeground(Color.BLACK);

       frame.add(RegistrationLabel);
       frame.add(UsernameLabel);
       frame.add(PasswordLabel);
       frame.add(ConfirmPasswordLabel);
       frame.add(DOBLabel);
       frame.add(UsernameField);
       frame.add(PasswordField);
       frame.add(ConfirmPasswordField);
       frame.add(daysComboBox);
       frame.add(monthsComboBox);
       frame.add(yearsComboBox);
       frame.add(RegisterButton);
       frame.add(BackButton);
       frame.add(ResetButton);
       frame.add(player);

       frame.setBackground(Color.ORANGE);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(800,600);
       frame.setLayout(null);
       frame.setVisible(true);
       frame.getContentPane().setBackground(Color.BLACK);
       //makes the window appear in the center
       frame.setLocationRelativeTo(null); 

    }

public void actionPerformed (ActionEvent e) {

        if(e.getSource()==ResetButton){
            UsernameField.setText("");
            PasswordField.setText("");
            ConfirmPasswordField.setText("");
            daysComboBox.setSelectedItem("1");
            monthsComboBox.setSelectedItem("January");
            yearsComboBox.setSelectedItem("2013");
        }

        if(e.getSource()==RegisterButton){
            LocalDate currentDate = LocalDate.now();

            int year = Integer.parseInt(yearsComboBox.getSelectedItem().toString());
            String months = monthsComboBox.getSelectedItem().toString();
            int day = Integer.parseInt(daysComboBox.getSelectedItem().toString());

            int month = 0;

            switch(months) {
                case "January":
                    month = 1;
                    break;
                case "February":
                    month = 2;
                    break;
                case "March":
                    month = 3;
                    break;
                case "April":
                    month = 4;
                    break;
                case "May":
                    month = 5;
                    break;
                case "June":
                    month = 6;
                    break;
                case "July":
                    month = 7;
                    break;
                case "August":
                    month = 8;
                    break;
                case "September":
                    month = 9;
                    break;
                case "October":
                    month = 10;
                    break;
                case "November":
                    month = 11;
                    break;
                case "December":
                    month = 12;
                    break;
            }

            String username = UsernameField.getText();
            String password = PasswordField.getText();
            String confirmpassword = ConfirmPasswordField.getText();

            if (isValidDate(year, month, day)) {
            // date is valid, proceed with submission

            LocalDate dob = LocalDate.of(year, month, day);

            if ((dob != null) && (currentDate != null)) {
                
                Period period = Period.between(dob, currentDate);

                if (period.getYears() < 18) {
                    //message box saying youre not old enough
                    JFrame f1 = new JFrame();
                    JOptionPane.showMessageDialog(f1,"Sorry you are not old enough to play.","Alert",JOptionPane.WARNING_MESSAGE);

                } else {

                    if (password.equals(confirmpassword) && (username.isEmpty() == false && password.isEmpty() == false && confirmpassword.isEmpty() == false)) {
                       
                        if (isUsernameTaken(username)) {

                            System.out.println("Username taken.");

                            JFrame f2 = new JFrame();
                            JOptionPane.showMessageDialog(f2,"Username already taken.","Alert",JOptionPane.WARNING_MESSAGE);
                        
                        } else {
                            registerUser(username, password);

                            boolean isAuthenticated = authenticateUser(username, password);

                            if (isAuthenticated) {
                                System.out.println("User authenticated successfully.");

                                JFrame f3 = new JFrame();
                                JOptionPane.showMessageDialog(f3,"Registration successful.","Alert",JOptionPane.INFORMATION_MESSAGE);
                            
                                frame.dispose();
                                LoginPage1 login = new LoginPage1(USER_FILE);
    
                            } else {
                                System.out.println("User authentication failed.");
                            }
                        }

                    } else if (password.equals(confirmpassword) == false) {
                        JFrame f4 = new JFrame();
                        JOptionPane.showMessageDialog(f4,"The password you entered doesn't match in both fields.","Alert",JOptionPane.WARNING_MESSAGE);
                        
                    } else if (username.isEmpty() == true || password.isEmpty() == true || confirmpassword.isEmpty() == true) {
                        JFrame f5 = new JFrame();
                        JOptionPane.showMessageDialog(f5,"One or more fields were left blank.","Alert",JOptionPane.WARNING_MESSAGE);
                        
                    }
                }
            } else {
                // date is invalid, display error message
                JFrame f6 = new JFrame();
                JOptionPane.showMessageDialog(f6,"Invalid date.","Alert",JOptionPane.WARNING_MESSAGE);
            }
         } else {
                // date is invalid, display error message
                JFrame f7 = new JFrame();
                JOptionPane.showMessageDialog(f7,"Sorry you did not enter a valid date.","Alert",JOptionPane.WARNING_MESSAGE);
            }
        }

        if(e.getSource()==BackButton) {
            frame.dispose();

            LoginPage1 login = new LoginPage1(USER_FILE);
        }
    }


    public static boolean isValidDate(int year, int month, int day) {
            Calendar cal = Calendar.getInstance();
            cal.setLenient(false);
            cal.set(year, month - 1, day);
            try {
                cal.getTime();
                return true;
            } catch (Exception e) {
                return false;
            }
        }

    public static String getUserFile() {
        return USER_FILE;
    }

    public static boolean isUsernameTaken(String username) {
        boolean isTaken = false;
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    isTaken = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isTaken;
    }

    public static void registerUser(String username, String password) {
        try {
            File file = new File(USER_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }

            String passwordHash = hashPassword(password);

            try (FileWriter writer = new FileWriter(file, true);
                 BufferedWriter bufferedWriter = new BufferedWriter(writer);
                 PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
                printWriter.println(username + "," + passwordHash);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("User registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean authenticateUser(String username, String password) {
        try {
            String passwordHash = hashPassword(password);

            File file = new File(USER_FILE);
            if (!file.exists()) {
                return false;
            }

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] userData = line.split(",");
                    if (userData[0].equals(username) && userData[1].equals(passwordHash)) {
                        // User is authenticated
                        return true;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static String hashPassword(String password) {
        String passwordHash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            passwordHash = hexString.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return passwordHash;
    }
}
