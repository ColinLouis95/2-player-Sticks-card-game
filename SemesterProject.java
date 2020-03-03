//Colin Kugler
//CSC161.101
//Semester Project
//Sticks card game
// This project will create the Card Game Sticks,
// also create a Goal for the player to achieve to win.
// once the player achieves their goal, a message will appear saying they won!
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SemesterProject extends Application{
// Global objects to handle reading the GameGoals.txt file
// as well as objects to hold the card names for the deck
	String[] cardImageNames = new String[52];
	int[][] cardIntegerEqs = new int[52][2];
	int indexCounter = 0;
	int[] cardsInHand = {0,0,0,0,0};
	
	String [] goalNames = new String [10];
	int [][] goals = new int [50][2];
	String currentGoalName = null;
	int [][] goalToMeet = new int [5][2];
	Random r = new Random();
	int [][] currentHand = new int [5][2];

// players 1 & 2 lose labels for when a player wins, the others label will appear with a loser label
// could only get this to work with the labels in global
	Label p1loseLabel = new Label("Player 2 has reached their goal. YOU LOSE!!!!");
	Label p2loseLabel = new Label("Player 1 has reached their goal. YOU LOSE!!!!");
	
	
	//@Override
	public void start(Stage primaryStage) {	
//try/catch method to store Stick Game Goals for use to check if player wins game
		GameGoals ggP1 = new GameGoals();
		GameGoals ggP2 = new GameGoals();

// setting the lose labels characteristics 		
		p1loseLabel.setTextFill(Color.BLACK);
		p1loseLabel.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 20));
		
		p2loseLabel.setTextFill(Color.BLACK);
		p2loseLabel.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 20));
		
		
// try block to read the GameGoals.txt file to the global arrays.
		try {
			Scanner fileInput = new Scanner(new File("StickGameGoals.txt"));
			
			int c1 = 0;
			int c2 = 0;
			
			while (fileInput.hasNext()){
				String line = fileInput.nextLine();
				if (line.length() > 5){
					//td2.goalNames[c1] = line;
					goalNames[c1] = line;
					c1 += 1;
				}
				else{
					String [] temp = line.split(" ");
					int value = Integer.parseInt(temp[0]);
					int suit = Integer.parseInt(temp[1]);
					goals[c2][0] = value;
					goals[c2][1] = suit;
					c2 += 1;
				}
			}
			
			int randomValue = r.nextInt(10);
			currentGoalName = goalNames[randomValue];
			int counter = randomValue * 5;
			
			for (int x = 0; x < 5; x++){
				goalToMeet[x][0] = goals[counter][0];
				goalToMeet[x][1] = goals[counter][1];
				
				counter += 1;
			}			
			fileInput.close();
		}
		catch(IOException e) {
			System.out.println("The file StickGameGoals.txt cannot be found. Check the location of the file as well as the spelling of file.");
		}
		
		try {
//create all pieces of Scene
//create a deck of 52 cards from text file
			cardImageNames[indexCounter] = "as.gif";
			cardIntegerEqs[indexCounter][0] = 1;
			cardIntegerEqs[indexCounter][1] = 1;
			indexCounter++;
			cardImageNames[indexCounter] = "ac.gif";
			cardIntegerEqs[indexCounter][0] = 1;
			cardIntegerEqs[indexCounter][1] = 2;
			indexCounter++;
			cardImageNames[indexCounter] = "ad.gif";
			cardIntegerEqs[indexCounter][0] = 1;
			cardIntegerEqs[indexCounter][1] = 3;
			indexCounter++;
			cardImageNames[indexCounter] = "ah.gif";
			cardIntegerEqs[indexCounter][0] = 1;
			cardIntegerEqs[indexCounter][1] = 4;
			indexCounter++;
			cardImageNames[indexCounter] = "ks.gif";
			cardIntegerEqs[indexCounter][0] = 13;
			cardIntegerEqs[indexCounter][1] = 1;
			indexCounter++;
			cardImageNames[indexCounter] = "kh.gif";
			cardIntegerEqs[indexCounter][0] = 13;
			cardIntegerEqs[indexCounter][1] = 4;
			indexCounter++;
			cardImageNames[indexCounter] = "kc.gif";
			cardIntegerEqs[indexCounter][0] = 13;
			cardIntegerEqs[indexCounter][1] = 2;
			indexCounter++;
			cardImageNames[indexCounter] = "kd.gif";
			cardIntegerEqs[indexCounter][0] = 13;
			cardIntegerEqs[indexCounter][1] = 3;
			indexCounter++;
			cardImageNames[indexCounter] = "js.gif";
			cardIntegerEqs[indexCounter][0] = 11;
			cardIntegerEqs[indexCounter][1] = 1;
			indexCounter++;
			cardImageNames[indexCounter] = "jc.gif";
			cardIntegerEqs[indexCounter][0] = 11;
			cardIntegerEqs[indexCounter][1] = 2;
			indexCounter++;
			cardImageNames[indexCounter] = "jd.gif";
			cardIntegerEqs[indexCounter][0] = 11;
			cardIntegerEqs[indexCounter][1] = 3;
			indexCounter++;
			cardImageNames[indexCounter] = "jh.gif";
			cardIntegerEqs[indexCounter][0] = 11;
			cardIntegerEqs[indexCounter][1] = 4;
			indexCounter++;
			cardImageNames[indexCounter] = "qs.gif";
			cardIntegerEqs[indexCounter][0] = 12;
			cardIntegerEqs[indexCounter][1] = 1;
			indexCounter++;
			cardImageNames[indexCounter] = "qc.gif";
			cardIntegerEqs[indexCounter][0] = 12;
			cardIntegerEqs[indexCounter][1] = 2;
			indexCounter++;
			cardImageNames[indexCounter] = "qd.gif";
			cardIntegerEqs[indexCounter][0] = 12;
			cardIntegerEqs[indexCounter][1] = 3;
			indexCounter++;
			cardImageNames[indexCounter] = "qh.gif";
			cardIntegerEqs[indexCounter][0] = 12;
			cardIntegerEqs[indexCounter][1] = 4;
			indexCounter++;
			cardImageNames[indexCounter] = "ts.gif";
			cardIntegerEqs[indexCounter][0] = 10;
			cardIntegerEqs[indexCounter][1] = 1;
			indexCounter++;
			cardImageNames[indexCounter] = "tc.gif";
			cardIntegerEqs[indexCounter][0] = 10;
			cardIntegerEqs[indexCounter][1] = 2;
			indexCounter++;
			cardImageNames[indexCounter] = "td.gif";
			cardIntegerEqs[indexCounter][0] = 10;
			cardIntegerEqs[indexCounter][1] = 3;
			indexCounter++;
			cardImageNames[indexCounter] = "th.gif";
			cardIntegerEqs[indexCounter][0] = 10;
			cardIntegerEqs[indexCounter][1] = 4;
			indexCounter++;
			for(int x = 2; x < 10; x++) {
				cardImageNames[indexCounter] = Integer.toString(x) + "c.gif";
				cardIntegerEqs[indexCounter][0] = x;
				cardIntegerEqs[indexCounter][1] = 2;
				indexCounter++;
				cardImageNames[indexCounter] = Integer.toString(x) + "d.gif";
				cardIntegerEqs[indexCounter][0] = x;
				cardIntegerEqs[indexCounter][1] = 3;
				indexCounter++;
				cardImageNames[indexCounter] = Integer.toString(x) + "h.gif";
				cardIntegerEqs[indexCounter][0] = x;
				cardIntegerEqs[indexCounter][1] = 4;
				indexCounter++;
				cardImageNames[indexCounter] = Integer.toString(x) + "s.gif";
				cardIntegerEqs[indexCounter][0] = x;
				cardIntegerEqs[indexCounter][1] = 1;
				indexCounter++;
			}
// create main VBox to hold game/ game title heading
			VBox root = new VBox();
			root.setSpacing(20);
			HBox heading = new HBox();

// create player 1 view			
			HBox player1Name = new HBox();
			VBox p1cardsAndButtons = new VBox();
			p1cardsAndButtons.setSpacing(25);
			HBox p1cardHand = new HBox(); // window holding cards
			p1cardHand.setSpacing(10);
			HBox p1radioButtons = new HBox();
			p1radioButtons.setSpacing(58);
			HBox p1goalArea = new HBox();
			p1goalArea.setSpacing(20);
			HBox p1discardAndDrawArea = new HBox();

// create separating border line between players using Rectangle object
			Rectangle playerBorder = new Rectangle(20,20,1600,20);
			playerBorder.setFill(Color.DARKMAGENTA);
			
// create player 2 view
			HBox player2Name = new HBox();
			VBox p2cardsAndButtons = new VBox();
			p2cardsAndButtons.setSpacing(25);
			HBox p2cardHand = new HBox(); // window holding cards
			p2cardHand.setSpacing(10);
			HBox p2radioButtons = new HBox();
			p2radioButtons.setSpacing(58);
			HBox p2goalArea = new HBox();
			p2goalArea.setSpacing(20);
			HBox p2discardAndDrawArea = new HBox();
			
// create view for rules 		
			HBox footer = new HBox();
			footer.setSpacing(20);			
			
//fill in heading for Sticks
			Text headingText = new Text();
			headingText.setText("Welcome to the Stick Card Game!");
			headingText.setFill(Color.DARKBLUE);
			headingText.setFont(Font.font("verdana",FontWeight.EXTRA_BOLD,FontPosture.REGULAR,45));
			heading.getChildren().add(headingText);
			heading.setAlignment(Pos.TOP_CENTER);
			
// fill in player1 name
			Text player1Text = new Text();
			player1Text.setText("Player 1");
			player1Text.setFill(Color.RED);
			player1Text.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 20));
			player1Name.getChildren().add(player1Text);
			player1Name.setAlignment(Pos.CENTER);
			
// Create an Image for the card back
			String emptySpace = "backofcard.png";
			FileInputStream inputStreamEmptySpace = new FileInputStream(emptySpace);
			Image emptySpaceImage = new Image(inputStreamEmptySpace);
			
// create images of cards to display
			int randomCard = r.nextInt(52);
			returnValues(currentHand, randomCard, 0);
			String selectedCard = getNextCard(cardImageNames, randomCard); 
			FileInputStream inputStream1 = new FileInputStream("cards//" + selectedCard);
			Image image1 = new Image(inputStream1);
			
			randomCard = r.nextInt(52);
			returnValues(currentHand, randomCard, 1);
			selectedCard = getNextCard(cardImageNames, randomCard);
			FileInputStream inputStream2 = new FileInputStream("cards//" + selectedCard);
			Image image2 = new Image(inputStream2);
			
			randomCard = r.nextInt(52);
			returnValues(currentHand, randomCard, 2);
			selectedCard = getNextCard(cardImageNames, randomCard);
			FileInputStream inputStream3 = new FileInputStream("cards//" + selectedCard);
			Image image3 = new Image(inputStream3);
			
			randomCard = r.nextInt(52);
			returnValues(currentHand, randomCard, 3);
			selectedCard = getNextCard(cardImageNames, randomCard);
			FileInputStream inputStream4 = new FileInputStream("cards//" + selectedCard);
			Image image4 = new Image(inputStream4);
			
			randomCard = r.nextInt(52);
			returnValues(currentHand, randomCard, 4);
			selectedCard = getNextCard(cardImageNames, randomCard); 
			FileInputStream inputStream5 = new FileInputStream("cards//" + selectedCard); 
			Image image5 = new Image(inputStream5);
// as soon as the hand is dealt, check to see if drew their goal			
			boolean winTest1 = ggP1.testGoals(goalToMeet,currentHand);
				
			
//create images of first 5 cards to display in hand
				ImageView imageV1 = new ImageView(image1);
				ImageView imageV2 = new ImageView(image2);
				ImageView imageV3 = new ImageView(image3);
				ImageView imageV4 = new ImageView(image4);
				ImageView imageV5 = new ImageView(image5);
				
				p1cardHand.getChildren().addAll(imageV1,imageV2,imageV3,imageV4,imageV5);
				p1cardHand.setAlignment(Pos.CENTER);
				p1cardsAndButtons.getChildren().add(p1cardHand);
				
				

//create radio buttons to indicate which card to discard/draw
				ToggleGroup discard = new ToggleGroup();
				
				RadioButton card1 = new RadioButton();
				card1.setToggleGroup(discard);
				
				RadioButton card2 = new RadioButton();
				card2.setToggleGroup(discard);
				
				RadioButton card3 = new RadioButton();
				card3.setToggleGroup(discard);
				
				RadioButton card4 = new RadioButton();
				card4.setToggleGroup(discard);
				
				RadioButton card5 = new RadioButton();
				card5.setToggleGroup(discard);
				
				p1radioButtons.getChildren().addAll(card1,card2,card3,card4,card5);
				p1radioButtons.setAlignment(Pos.CENTER);
				p1cardsAndButtons.getChildren().add(p1radioButtons);

//Create Label to Display the Game Goal, whether if they've reached it or not
				Label p1labelGoal = new Label("Your Goal: " + currentGoalName);
				p1labelGoal.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
				p1goalArea.getChildren().add(p1labelGoal);
				
				Label p1winLabel = new Label("Goal has been met. YOU HAVE WON!!!!");
				p1winLabel.setTextFill(Color.DEEPSKYBLUE);
				p1winLabel.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 16));
				
				Label p1goalMetOrNot = new Label("   goal has not been met!");
				p1goalMetOrNot.setTextFill(Color.CHOCOLATE);
				p1goalMetOrNot.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
				p1goalArea.getChildren().add(p1goalMetOrNot);
				p1goalArea.setAlignment(Pos.CENTER);
				p1cardsAndButtons.getChildren().add(p1goalArea);
				
//create discard button
				Button p1discardButton = new Button("Discard");
				EventHandler<ActionEvent> p1discardButtonPushed = new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent e) {
						System.out.println("Discard Button Pressed");
						if(card1.isSelected())
							imageV1.setImage(emptySpaceImage);
							cardsInHand[0] = 1;
						if(card2.isSelected())
							imageV2.setImage(emptySpaceImage);
							cardsInHand[1] = 1;
						if(card3.isSelected())
							imageV3.setImage(emptySpaceImage);
							cardsInHand[2] = 1;
						if(card4.isSelected())
							imageV4.setImage(emptySpaceImage);
							cardsInHand[3] = 1;
						if(card5.isSelected())
							imageV5.setImage(emptySpaceImage);
							cardsInHand[4] = 1;
					}
				};
				p1discardButton.setOnAction(p1discardButtonPushed);
				p1discardAndDrawArea.getChildren().add(p1discardButton);
				p1discardAndDrawArea.setAlignment(Pos.CENTER);
				
				
// create draw button
// EventHandler to set a new card when draw is pushed.
				Button p1drawButton = new Button("Draw");
				EventHandler<ActionEvent> p1drawButtonPushed = new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent e) {			
						int randomCard = r.nextInt(51);
						System.out.println("random " + randomCard);
						
						if(card1.isSelected()) {
							String nextCard = getNextCard(cardImageNames, randomCard);
							System.out.println("Image name" + nextCard);
							imageV1.setImage(createDrawCard(nextCard));
							returnValues(currentHand, randomCard, 0);
						}
						if(card2.isSelected()) {
							String nextCard = getNextCard(cardImageNames, randomCard);
							imageV2.setImage(createDrawCard(nextCard));
							returnValues(currentHand, randomCard, 1);
						}
						if(card3.isSelected()){
							String nextCard = getNextCard(cardImageNames, randomCard);
							imageV3.setImage(createDrawCard(nextCard));
							returnValues(currentHand, randomCard, 2);
						}
						if(card4.isSelected()) {
							String nextCard = getNextCard(cardImageNames, randomCard);
							imageV4.setImage(createDrawCard(nextCard));
							returnValues(currentHand, randomCard, 3);
						}
						if(card5.isSelected()) {
							String nextCard = getNextCard(cardImageNames, randomCard);
							imageV5.setImage(createDrawCard(nextCard));
							returnValues(currentHand, randomCard, 4);
						}
						
						System.out.println("curent Hand p1" + Arrays.deepToString(currentHand));
						boolean p1winTest = ggP1.testGoals(goalToMeet,currentHand);
						if(p1winTest == true) {
							p1goalArea.getChildren().clear();
							p1goalArea.getChildren().add(p1winLabel);
							
							p2goalArea.getChildren().clear();
							p2goalArea.getChildren().add(p2loseLabel);
						}
						
					}
					
				};
				p1drawButton.setOnAction(p1drawButtonPushed);
				p1discardAndDrawArea.getChildren().add(p1drawButton);
				p1discardAndDrawArea.setAlignment(Pos.CENTER);
				p1cardsAndButtons.getChildren().add(p1discardAndDrawArea);
				

// create view for player two
				// player 2 text header
				Text player2Text = new Text();
				player2Text.setText("Player 2");
				player2Text.setFill(Color.RED);
				player2Text.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 20));
				player2Name.getChildren().add(player2Text);
				player2Name.setAlignment(Pos.CENTER);
				
				//create initial hand for player 2
				randomCard = r.nextInt(52);
				returnValues(currentHand, randomCard, 0);
				String p2selectedCard = getNextCard(cardImageNames, randomCard); 
				FileInputStream p2inputStream1 = new FileInputStream("cards//" + p2selectedCard);
				Image p2image1 = new Image(p2inputStream1);
				
				randomCard = r.nextInt(52);
				returnValues(currentHand, randomCard, 1);
				p2selectedCard = getNextCard(cardImageNames, randomCard);
				FileInputStream p2inputStream2 = new FileInputStream("cards//" + p2selectedCard);
				Image p2image2 = new Image(p2inputStream2);
				
				randomCard = r.nextInt(52);
				returnValues(currentHand, randomCard, 2);
				p2selectedCard = getNextCard(cardImageNames, randomCard);
				FileInputStream p2inputStream3 = new FileInputStream("cards//" + p2selectedCard);
				Image p2image3 = new Image(p2inputStream3);
				
				randomCard = r.nextInt(52);
				returnValues(currentHand, randomCard, 3);
				p2selectedCard = getNextCard(cardImageNames, randomCard);
				FileInputStream p2inputStream4 = new FileInputStream("cards//" + selectedCard);
				Image p2image4 = new Image(p2inputStream4);
				
				randomCard = r.nextInt(52);
				returnValues(currentHand, randomCard, 4);
				p2selectedCard = getNextCard(cardImageNames, randomCard); 
				FileInputStream p2inputStream5 = new FileInputStream("cards//" + selectedCard); 
				Image p2image5 = new Image(p2inputStream5);
				boolean winTest2 = ggP2.testGoals(goalToMeet,currentHand);
				
				//create images of first 5 cards to display in hand for player 2
				ImageView p2imageV1 = new ImageView(p2image1);
				ImageView p2imageV2 = new ImageView(p2image2);
				ImageView p2imageV3 = new ImageView(p2image3);
				ImageView p2imageV4 = new ImageView(p2image4);
				ImageView p2imageV5 = new ImageView(p2image5);
				
				p2cardHand.getChildren().addAll(p2imageV1,p2imageV2,p2imageV3,p2imageV4,p2imageV5);
				p2cardHand.setAlignment(Pos.CENTER);
				p2cardsAndButtons.getChildren().add(p2cardHand);
				
				//create radio buttons to indicate which card to discard/draw
				ToggleGroup p2discard = new ToggleGroup();
				
				RadioButton p2card1 = new RadioButton();
				p2card1.setToggleGroup(p2discard);
				
				RadioButton p2card2 = new RadioButton();
				p2card2.setToggleGroup(p2discard);
				
				RadioButton p2card3 = new RadioButton();
				p2card3.setToggleGroup(p2discard);
				
				RadioButton p2card4 = new RadioButton();
				p2card4.setToggleGroup(p2discard);
				
				RadioButton p2card5 = new RadioButton();
				p2card5.setToggleGroup(p2discard);
				
				p2radioButtons.getChildren().addAll(p2card1,p2card2,p2card3,p2card4,p2card5);
				p2radioButtons.setAlignment(Pos.CENTER);
				p2cardsAndButtons.getChildren().add(p2radioButtons);
				
				//Create Label to Display the Game Goal, and whether if they've reached it or not
				Label p2labelGoal = new Label("Your Goal: " + currentGoalName);
				p2labelGoal.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
				p2goalArea.getChildren().add(p2labelGoal);
				
				Label p2winLabel = new Label("Goal has been met. YOU HAVE WON!!!!");
				p2winLabel.setTextFill(Color.DEEPSKYBLUE);
				p2winLabel.setFont(Font.font("verdana", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 16));
				
				Label p2goalMetOrNot = new Label("   goal has not been met!");
				p2goalMetOrNot.setTextFill(Color.CHOCOLATE);
				p2goalMetOrNot.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
				p2goalArea.getChildren().add(p2goalMetOrNot);
				p2goalArea.setAlignment(Pos.CENTER);
				p2cardsAndButtons.getChildren().add(p2goalArea);
				
				
				// create player 2 discard button, event handler
				Button p2discardButton = new Button("Discard");
				EventHandler<ActionEvent> p2discardButtonPushed = new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent e) {
						System.out.println("Discard Button Pressed");
						if(p2card1.isSelected())
							p2imageV1.setImage(emptySpaceImage);
							cardsInHand[0] = 1;
						if(p2card2.isSelected())
							p2imageV2.setImage(emptySpaceImage);
							cardsInHand[1] = 1;
						if(p2card3.isSelected())
							p2imageV3.setImage(emptySpaceImage);
							cardsInHand[2] = 1;
						if(p2card4.isSelected())
							p2imageV4.setImage(emptySpaceImage);
							cardsInHand[3] = 1;
						if(p2card5.isSelected())
							imageV5.setImage(emptySpaceImage);
							cardsInHand[4] = 1;
					}
				};
				p2discardButton.setOnAction(p2discardButtonPushed);
				p2discardAndDrawArea.getChildren().add(p2discardButton);
				p2discardAndDrawArea.setAlignment(Pos.CENTER);
				
				// create player 2 draw button, event handler.
				Button p2drawButton = new Button("Draw");
				EventHandler<ActionEvent> p2drawButtonPushed = new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent e) {			
						int randomCard = r.nextInt(51);
						System.out.println("random " + randomCard);
						
						if(p2card1.isSelected()) {
							String nextCard = getNextCard(cardImageNames, randomCard);
							System.out.println("Image name" + nextCard);
							p2imageV1.setImage(createDrawCard(nextCard));
							returnValues(currentHand, randomCard, 0);
						}
						if(p2card2.isSelected()) {
							String nextCard = getNextCard(cardImageNames, randomCard);
							p2imageV2.setImage(createDrawCard(nextCard));
							returnValues(currentHand, randomCard, 1);
						}
						if(p2card3.isSelected()){
							String nextCard = getNextCard(cardImageNames, randomCard);
							p2imageV3.setImage(createDrawCard(nextCard));
							returnValues(currentHand, randomCard, 2);
						}
						if(p2card4.isSelected()) {
							String nextCard = getNextCard(cardImageNames, randomCard);
							p2imageV4.setImage(createDrawCard(nextCard));
							returnValues(currentHand, randomCard, 3);
						}
						if(p2card5.isSelected()) {
							String nextCard = getNextCard(cardImageNames, randomCard);
							p2imageV5.setImage(createDrawCard(nextCard));
							returnValues(currentHand, randomCard, 4);
						}
						
						System.out.println("curent Hand p2" + Arrays.deepToString(currentHand));
						boolean p2winTest = ggP2.testGoals(goalToMeet,currentHand);
						if(p2winTest == true) {
							p2goalArea.getChildren().clear();
							p2goalArea.getChildren().add(p2winLabel);
							
							p1goalArea.getChildren().clear();
							p1goalArea.getChildren().add(p1loseLabel);
						}
						
					}
					
				};
				p2drawButton.setOnAction(p2drawButtonPushed);
				p2discardAndDrawArea.getChildren().add(p2drawButton);
				p2discardAndDrawArea.setAlignment(Pos.CENTER);
				p2cardsAndButtons.getChildren().add(p2discardAndDrawArea);
				
//fill in footer
			Text footerText = new Text();
			String textForFooter = "The name of the game is Sticks. The object of Sticks is to draw and discard cards until you meet "
					+ "\nyour goal. Each player will have different goals. You and your opponent will take turns drawing"
					+ " \nand discarding. First player to meet their goal wins the game. Good Luck and Godspeed.";
			footerText.setText(textForFooter);
			footerText.setFill(Color.STEELBLUE);
			footerText.setFont(Font.font("verdana", 20));
			footer.getChildren().add(footerText);
			footer.setAlignment(Pos.CENTER);
			
//add pieces to Scene
			root.getChildren().add(heading);
			root.getChildren().add(player1Name);
			root.getChildren().add(p1cardsAndButtons);
			root.getChildren().add(playerBorder);
			root.getChildren().add(player2Name);
			root.getChildren().add(p2cardsAndButtons);
			root.getChildren().add(footer);
			Scene scene = new Scene(root, 1200, 1200);
			scene.setFill(Color.DARKGREEN);
			
//set stage and display scene
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		}
		catch(Exception e) {
			System.out.println("oops, error has occured: ");
			e.printStackTrace();
		}
		
	}

	public Image createDrawCard(String cardName) {
		Image view = null;
		try {
			String randomcard = cardName; 
			FileInputStream inputStream = new FileInputStream("cards//" + randomcard);
			view = new Image(inputStream);
		}
		catch(Exception e) {
			System.out.println("oops, error has occured.");
			e.printStackTrace();
		}
		return view;
	}
	
	public String getNextCard(String[] deck, int random) {
		String returnCard = deck[random];
		return returnCard;
	}
	
	public void returnValues(int[][] currentHand, int random, int cardNumber){
		int value = cardIntegerEqs[random][0];
		int suit = cardIntegerEqs[random][1];
		
		currentHand[cardNumber][0] = value;
		currentHand[cardNumber][1] = suit;
	}
	

	public static void main(String[] args) {
		Application.launch();
	}

}


