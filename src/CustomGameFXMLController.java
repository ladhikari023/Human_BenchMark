/*
    COUNT MASTER TEST
    This is the Controller class of custom_game.fxml
    In this class, I show random number of Red circles along with random number of non-red circles.
    Then I ask user to enter the number of red circles shown. If user is right, they advance to higher
    level. If not user will be displayed game over message with their score
    user can save the score or restart the game
 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;

/*
    Controller class
 */
public class CustomGameFXMLController extends UsersScore{

    // Accessing nodes from corresponding FXML file
    @FXML
    public Circle gCustomCircle;
    @FXML
    public Pane gCustomCanvasHolderPane;
    @FXML
    public Label gCustomLabelHeading;
    @FXML
    public Label gCustomLabelInfo;
    @FXML
    public Button main_menu_btn;
    @FXML
    public AnchorPane gCustomMainPane;
    @FXML
    public Button save_score_btn;

    //Declaring global variables
    private Pane gamePane; //pane to run the game
    public int level; // tracks level of game
    private int numberOfCircles; //random number of circles to be shown
    private int minNumber = level+3; //min number of circles to show

    /*
        runs first when the fxml loads
     */
    public void initialize(){
        gCustomCircle.setVisible(false);
        level =1;
    }

    /*
        Added gamePane to main pane
        called runTimeLine() method
     */
    public void startCustomGame(MouseEvent mouseEvent) {
        gamePane = new Pane();
        gamePane.setPrefWidth(gCustomCanvasHolderPane.getWidth());
        gamePane.setPrefHeight(gCustomCanvasHolderPane.getHeight());
        gamePane.setLayoutX(gCustomCanvasHolderPane.getLayoutX());
        gamePane.setLayoutY(gCustomCanvasHolderPane.getLayoutY());
        gamePane.setStyle("-fx-background-color: BLUE");
        //gamePane.getChildren().addAll();

        runTimeLine();

        gCustomMainPane.getChildren().add(gamePane);
    }
    /*
        Used timeline to show circles first and then ask input from user
     */
    private void runTimeLine(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), evt -> showCircles()),
                new KeyFrame(Duration.seconds(level), evt -> askUserInput()));
        timeline.play();
    }

    /*
        Creates random number of red and non-red circles and add it to gamePane
     */
    private void showCircles() {
        gamePane.getChildren().clear();
        gCustomLabelInfo.setText("Level: "+ level);
        gamePane.getChildren().addAll(gCustomLabelInfo);
        int rand = (int)(Math.random()*1.3*((level+7)-minNumber)+minNumber);
        int redRand = (int)(Math.random()*((level+7)-(1.3*minNumber))+(1.3*minNumber));
        numberOfCircles = redRand;
        minNumber = redRand;
        for (int i = 0; i < rand; i++) {
            double dx = Math.random()*635;
            double dy = Math.random()*270;
            Circle circle = new Circle(dx,dy,15, Color.CHARTREUSE);
            gamePane.getChildren().addAll(circle);
        }
        for (int i = 0; i < redRand; i++) {
            double dx = Math.random()*635;
            double dy = Math.random()*270;
            Circle circle = new Circle(dx,dy,15, Color.RED);
            gamePane.getChildren().addAll(circle);
        }
    }

    /*
        shows textField where user can input the number of red circles
        checked if user entered right number in textField, when user hit submit button
        if yes, call runTimeLine() again
        if no, call showResults() method
     */
    private void askUserInput() {
        gamePane.getChildren().clear();
        gCustomLabelHeading.setText("How many red circles were there?");
        TextField textField = new TextField();
        textField.setLayoutX((gamePane.getWidth()/2)-80);
        textField.setLayoutY(gamePane.getHeight()/2);
        Button submitBtn = new Button("Submit");
        submitBtn.setLayoutX((gamePane.getWidth()/2)-30);
        submitBtn.setLayoutY((gamePane.getHeight()/2)+30);
        gamePane.getChildren().addAll(gCustomLabelHeading,textField,submitBtn);

        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int enteredNumber = Integer.parseInt(textField.getText());
                if (enteredNumber==numberOfCircles){
                    level++;
                    runTimeLine();
                }else{
                    showResults();
                }
            }
        });
    }
    /*
        shown game over message along with user's score
     */
    private void showResults() {
        gamePane.getChildren().clear();
        gCustomLabelHeading.setText("Level: "+level);
        gCustomLabelInfo.setText("Game Over!!");
        save_score_btn.setDisable(false);
        gamePane.getChildren().addAll(gCustomLabelHeading,gCustomLabelInfo);
    }

    /*
     This method runs when user clicks Main Menu button.
     This method navigates user to home page.
    */
    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().goToHomePage(actionEvent);
    }
    /*
        This method runs when user clicks Play Again button.
        This method navigates user to game eight again.
     */
    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openCustomGame(actionEvent);
    }

    /*
        This method saves the score of user
     */
    public void saveScore(ActionEvent actionEvent) {
        UsersScore.countMasterScore = this.level-1;
    }
}
