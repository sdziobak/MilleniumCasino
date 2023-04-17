package application;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.Frame;

public class BlackJack extends Application {

    private Deck deck = new Deck();
    private Hand dealer, player;
    private Text message = new Text();
    private SimpleBooleanProperty playable = new SimpleBooleanProperty(false);
    private HBox dealerCards = new HBox(20);
    private HBox playerCards = new HBox(20);
    private TextField betTextField;
    
    private Frame mainFrame;
    private Stage blackjackStage;

    private Parent createContent() throws FileNotFoundException {

        dealer = new Hand(dealerCards.getChildren());
        player = new Hand(playerCards.getChildren());

        Pane root = new Pane();
        root.setPrefSize(800, 600);

        Region background = new Region();
        background.setPrefSize(800, 600);
        background.setStyle("-fx-background-color: rgba(0, 0, 0, 1)");

        HBox rootLayout = new HBox(5);
        rootLayout.setPadding(new Insets(5, 5, 5, 5));
        Rectangle leftBG = new Rectangle(550, 560);
        leftBG.setArcWidth(20);
        leftBG.setArcHeight(20);
        
        
    
        Rectangle rightBG = new Rectangle(230, 560);
        rightBG.setArcWidth(20);
        rightBG.setArcHeight(20);
        rightBG.setFill(Color.GREEN);

        VBox leftVBox = new VBox(50);
        leftVBox.setAlignment(Pos.TOP_CENTER);

        Text dealerScore = new Text("Dealer: ");
        Text playerScore = new Text("Player: ");

        
        Image tableImage = new Image(getClass().getResourceAsStream("/application/bjtable.jpg"));
        leftBG.setFill(new ImagePattern(tableImage));
        leftVBox.getChildren().addAll(dealerScore, dealerCards, message, playerCards, playerScore);

        VBox rightVBox = new VBox(20);
        rightVBox.setAlignment(Pos.CENTER);

        betTextField = new TextField("Enter bet amount");
        betTextField.setPrefWidth(150);
        Button btnPlaceBet = new Button("Place Bet");

        Button btnPlay = new Button("PLAY");
        Button btnHit = new Button("HIT");
        Button btnStand = new Button("STAND");

        HBox buttonsHBox = new HBox(15, btnHit, btnStand);
        buttonsHBox.setAlignment(Pos.CENTER);

        rightVBox.getChildren().addAll(btnPlay,buttonsHBox, betTextField, btnPlaceBet);


        rootLayout.getChildren().addAll(new StackPane(leftBG, leftVBox), new StackPane(rightBG, rightVBox));
        root.getChildren().addAll(background, rootLayout);
        
        btnPlay.disableProperty().bind(playable);
        btnHit.disableProperty().bind(playable.not());
        btnStand.disableProperty().bind(playable.not().or(betTextField.textProperty().isEmpty()));

        btnPlay.disableProperty().bind(playable);
        btnHit.disableProperty().bind(playable.not());
        btnStand.disableProperty().bind(playable.not());

        playerScore.textProperty().bind(new SimpleStringProperty("Player: ").concat(player.valueProperty().asString()));
        dealerScore.textProperty().bind(new SimpleStringProperty("Dealer: ").concat(dealer.valueProperty().asString()));

        player.valueProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                endGame();
            }
        });

        dealer.valueProperty().addListener((obs, old, newValue) -> {
            if (newValue.intValue() >= 21) {
                endGame();
            }
        });

        btnPlay.setOnAction(event -> {
            startNewGame();
        });

        btnHit.setOnAction(event -> {
            player.takeCard(deck.drawCard());
        });

        btnStand.setOnAction(event -> {
            while (dealer.valueProperty().get() < 17) {
                dealer.takeCard(deck.drawCard());
            }

            endGame();
        });

        return root;
    }

    private void startNewGame() {
        playable.set(true);
        message.setText("");

        deck.refill();

        dealer.reset();
        player.reset();

        dealer.takeCard(deck.drawCard());
        dealer.takeCard(deck.drawCard());
        player.takeCard(deck.drawCard());
        player.takeCard(deck.drawCard());
    }

    private void endGame() {
    	playable.set(false);

        int dealerValue = dealer.valueProperty().get();
        int playerValue = player.valueProperty().get();
        String winner = "Exceptional case: d: " + dealerValue + " p: " + playerValue;

        if (dealerValue == 21 || playerValue > 21 || dealerValue == playerValue
                 || (dealerValue < 21 && dealerValue > playerValue)) {
            winner = "Dealer";
        } else if (playerValue == 21 || dealerValue > 21 || playerValue > dealerValue) {
            winner = "Player";
        } else if (playerValue == dealerValue || (playerValue == 21 && dealerValue > 21)) {
            winner = "Draw";
        }

        message.setText(winner+ "wins!");


        int betAmount = 0;
        try {
            betAmount = Integer.parseInt(betTextField.getText());
        } catch (NumberFormatException e) {
            // Handle invalid bet amount input
        }

        int amountWonOrLost = 0;
        if (winner.equals("Player")) {
            amountWonOrLost = betAmount;
        } else if (winner.equals("Dealer")) {
            amountWonOrLost = -betAmount;
        }

        String result = "";
        if (amountWonOrLost > 0) {
            result = "You won: $" + amountWonOrLost;
        } else if (amountWonOrLost < 0) {
            result = "You lost: $" + (-amountWonOrLost);
        } else {
            result = "Bet not Placed, Please Enter Bet!";
        }

        message.setText(result);
//        playable.set(false);
//
//        int dealerValue = dealer.valueProperty().get();
//        int playerValue = player.valueProperty().get();
//        String winner = "Exceptional case: d: " + dealerValue + " p: " + playerValue;
//
//        if (dealerValue == 21 || playerValue > 21 || dealerValue == playerValue
//                || (dealerValue < 21 && dealerValue > playerValue)) {
//            winner = "Dealer";
//        }
//        else if (playerValue == 21 || dealerValue > 21 || playerValue > dealerValue) {
//            winner = "Player";
//        }
//        else if (playerValue == dealerValue){
//            winner = "Draw";
//        }
//        message.setText(winner + " WON!");
    }

    public BlackJack(Stage blackjackStage, Frame mainFrame) {
    	this.blackjackStage = blackjackStage;
        this.mainFrame = mainFrame;
    	
    	try {
			blackjackStage.setScene(new Scene(createContent(),600,500));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        blackjackStage.setWidth(800);
		blackjackStage.setHeight(600);
        blackjackStage.setResizable(false);
        blackjackStage.setTitle("BlackJack");
    }

	@Override
	public void start(Stage blackjackStage) {
		blackjackStage.show();
        mainFrame.dispose();
		
	}

//    public static void main(String[] args) {
//        launch(args);
//    }
    

}