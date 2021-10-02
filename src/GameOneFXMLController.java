/*
    This is the Controller class of game_one.fxml
    In this class, I have written all the logic needed for REACTION TIME TEST.
    First I show the user a pane where basic information on game is displayed.
    When user clicks the pane the game starts. First the red screen appears with a
    message to user to click after screen turns green. If user clicks the screen early
    then, a message of restart the game will appear.
    If user clicks after the green screen then a display of their reaction time is displayed.
    User can save their score and go to home page or restart the game.
 */

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;

public class GameOneFXMLController extends UsersScore {
    // Accessing nodes from corresponding FXML file
    @FXML
    Canvas gOneCanvas;
    @FXML
    Label gOneLabelHeading;
    @FXML
    Label gOneLabelInfo;
    @FXML
    Label gOneLabelStart;
    @FXML
    Pane gOneCanvasHolderPane;
    @FXML
    public Button save_score_btn; // saves reaction time

    //Declaring global variables startTime, endTime, wait, and started
    private long startTime;  //tracks start time when screen turns green
    private long endTime; //tracks end time when user clicks the green screen
    private double diff; // gives the reaction time
    private PauseTransition wait; //helps pause the red screen for few seconds
    boolean started = false; //tracks if the redScreen has already started or not

    //initializes background of pane
    public void initialize(){
        gOneCanvasHolderPane.setStyle("-fx-background-color: RED");
    }

    /*
        This method runs when user clicks the pane.
        Used pause transition to pause red screen for 3 seconds
        If user clicks the red screen, a display message of "Too early" is shown
        If user clicks after the green screen appears, a display message of their reaction time is displayed.
     */
    public void onClick(MouseEvent mouseEvent) throws IOException {
        if (gOneCanvasHolderPane.getStyle().equals("-fx-background-color: RED")) {
            if (!started) {
                started = true;
                startTime = System.currentTimeMillis();
                gOneLabelHeading.setText("Wait for Green");
                gOneLabelInfo.setVisible(false);
                gOneLabelStart.setVisible(false);
                wait = new PauseTransition(Duration.seconds(3));
                wait.setOnFinished(event -> {
                    gOneLabelHeading.setText("Click");
                    gOneCanvasHolderPane.setStyle("-fx-background-color: GREEN");
                });
                wait.play();
            }else{
                gOneLabelHeading.setText("Too Soon");
                gOneCanvasHolderPane.setStyle("-fx-background-color: BLUE");
                wait.stop();
            }
        }else{
            endTime = System.currentTimeMillis();
            gOneLabelStart.setVisible(true);
            diff = endTime - startTime;
            gOneLabelStart.setText((diff - 3000) + " ms");
            save_score_btn.setDisable(false);
        }
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
        This method navigates user to game one again.
     */
    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openGameOne(actionEvent);
    }
    /*
        This method saves the score of user
     */
    public void saveScore(ActionEvent actionEvent) {
        UsersScore.reactionTimeScore = (this.diff-3000);
    }
}
