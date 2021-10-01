/*
    NUMBER MEMORY TEST
    This is the Controller class of game_four.fxml
    In this class, I have shown a number which user has to remember and type in the textField shown
    immediately. Each time user get it right I add a digit in the number and the process continues
    till user can't remember the number. I display the score and end the game.
    user can save the score or restart the game.
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;

public class GameFourFXMLController {

    // Accessing nodes from corresponding FXML file
    @FXML
    public Button gFourSubmitButton;
    @FXML
    public Button gFourNextBtn;
    @FXML
    public TextField gFourTextField;
    @FXML
    private Pane gFourCanvasHolderPane;
    @FXML
    private Canvas gFourCanvas;
    @FXML
    private Label gFourLabelStart;
    @FXML
    private Label gFourLabelHeading;
    @FXML
    private Label gFourLabelInfo;
    @FXML
    private Button main_menu_btn;

    private int showNumber; //tracks which number is shown to user
    private int enteredNumber; //tracks user input number
    private int currLevel = 1; //tracks level of game

    //sets visibility of some nodes to false
    public void initialize(){
        gFourNextBtn.setVisible(false);
        gFourSubmitButton.setVisible(false);
        gFourTextField.setVisible(false);
    }

    /*
        This method runs when user clicks the initial pane shown when game four loads
        called runGame method
     */
    public void startGameFour(MouseEvent mouseEvent) {
        gFourLabelInfo.setVisible(false);
        gFourLabelStart.setVisible(false);

        runGame(currLevel);
    }

    /*
        display a number to user by calling showNumber() method for time in seconds equivalent to
        current level the user is on. for example: 1 seconds display when user is in 1 level
        After the time transition, ask user to enter the shown number by calling askNumber() method
     */
    private void runGame(int level) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), evt -> showNumber()),
                new KeyFrame(Duration.seconds(level), evt -> askNumber()));
        timeline.play();
    }

    /*
        show a randomly generated number to the user
     */
    private void showNumber() {
        gFourLabelInfo.setVisible(false);
        gFourNextBtn.setVisible(false);
        this.showNumber = getRandomNumber((int)Math.pow(10,currLevel-1),(int)Math.pow(10,currLevel));
        gFourLabelHeading.setText(String.valueOf(showNumber));
    }
    /*
        ask user for input after displaying the number
     */
    private void askNumber() {
        gFourLabelHeading.setText("What was the Number?");
        gFourLabelInfo.setVisible(true);
        gFourLabelInfo.setText("Press Enter To Submit");
        gFourTextField.setVisible(true);
        gFourSubmitButton.setVisible(true);
    }
    /*
        Check if displayed number is equal to user input.
        If yes, advance to higher level
        If Not, then display a 'lose' message with score
     */
    private void showResult(int level) {
        if (enteredNumber == showNumber){
            gFourNextBtn.setVisible(true);
        }else{
            gFourLabelStart.setText("You Lose");
            gFourLabelStart.setVisible(true);
        }
        gFourTextField.clear();
        gFourTextField.setVisible(false);
        gFourLabelHeading.setText("Level: "+ level);
        gFourLabelInfo.setText("Shown Number: " + showNumber + "  You Entered: "+ enteredNumber);
    }

    // Assign the value in text field to enteredNumber after parsing it to Integer. called showResult method from here.
    public void onClickSubmitBtn(ActionEvent actionEvent) {
        enteredNumber = Integer.parseInt(gFourTextField.getText());
        gFourSubmitButton.setVisible(false);
        showResult(currLevel);
    }

    //Advance to next level on click of the NEXT button
    public void onClickNextBtn(ActionEvent actionEvent) {
        runGame(currLevel++);
    }
    //generates a random number
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
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
        This method navigates user to game four again.
     */
    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openGameFour(actionEvent);
    }
}
