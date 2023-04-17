package application;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.*;
import java.io.File;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.Frame;


public class Coinflip {

    private FlowPane pane;
    private Frame mainFrame;
    private Stage coinflipStage;
    private Button flipButton;
    private Button headsButton;
    private Button tailsButton;
    private Text outcomeText;
    private TextField betAmount;
    private ImageView coinImage;
    private boolean userChoiceHeads;
    private Button backBtn;

    public Coinflip(Stage coinflipStage, Frame mainFrame) {
    	this.coinflipStage = coinflipStage;
        this.mainFrame = mainFrame;
        
         flipButton = new Button("Flip the Coin!");
         headsButton = new Button("Heads");
         tailsButton = new Button("Tails");
         outcomeText = new Text("Welcome, Please Place Your Bet!");
         betAmount = new TextField();
         betAmount.setPromptText("Enter Bet Amount");
         coinImage = new ImageView();

         flipButton.setOnAction(this::processCoinFlip);
         headsButton.setOnAction(this::chooseHeads);
         tailsButton.setOnAction(this::chooseTails);

         pane = new FlowPane(headsButton, tailsButton, betAmount, flipButton, outcomeText, coinImage);
         pane.setAlignment(Pos.CENTER);
         pane.setHgap(20);
         pane.setStyle("-fx-background-color: slategray");
         Scene scene = new Scene (pane, 700, 500);

         coinflipStage.setTitle("Coin Flip");
         coinflipStage.setScene(scene);
        
        
    }

    public void start(Stage stage) {
        coinflipStage.show();
        mainFrame.dispose();
    }

    public void processCoinFlip(ActionEvent event) {

        Random game = new Random();
        boolean coinIsHeads = game.nextBoolean();
        
        if (coinIsHeads) { 
            outcomeText.setText("Outcome is Heads");
            pane.setStyle("-fx-background-color: #097aeb");
            Image headsImage = new Image(getClass().getResourceAsStream("/application/heads.png"));
            //Image headsImage = new Image(headsFile.toURI().toString());
            coinImage.setImage(headsImage);
            coinImage.setFitWidth(400);
            coinImage.setFitHeight(400);
        } else { 
            outcomeText.setText("Outcome is Tails");
            pane.setStyle("-fx-background-color: tomato");
            Image tailsImage = new Image(getClass().getResourceAsStream("/application/tails.png"));
            //Image tailsImage = new Image(tailsFile.toURI().toString());
            coinImage.setImage(tailsImage);
            coinImage.setFitWidth(400);
            coinImage.setFitHeight(400);
        }
        
        if (userChoiceHeads == coinIsHeads) {
            outcomeText.setText(outcomeText.getText() + ". You won!");
        } else {
            outcomeText.setText(outcomeText.getText() + ". You lost.");
        }

    }
    
    public void chooseHeads(ActionEvent event) {
        userChoiceHeads = true;
    }
    
    public void chooseTails(ActionEvent event) {
        userChoiceHeads = false;
    }
    
}
