/*
    AIM TRAINER
    This is the Controller class of game_three.fxml
    In this class, I have used a randomly generated circle which appears randomly throughout
    the pane which user have to click as soon as they can. After they are finished clicking
    all the circles, I display the average time they took to click one circle
    user can save the score or restart the game
 */
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class GameThreeFXMLController {

    private final Object PAUSE_KEY = new Object();// pause key variable to pause and resume the interface

    // Accessing nodes from corresponding FXML file
    @FXML
    public Circle gThreeCircle;
    @FXML
    public Button gThreeStartButton;
    @FXML
    private Pane gThreeCanvasHolderPane;
    @FXML
    private Label gThreeLabelHeading;
    @FXML
    private Label gThreeLabelInfo;
    @FXML
    private Button main_menu_btn;


    //pause the interface
    private void pause() {
        Platform.enterNestedEventLoop(PAUSE_KEY);
    }
    //resumes the interface
    private void resume() {
        Platform.exitNestedEventLoop(PAUSE_KEY, null);
    }

    //sets visibility of circle to false
    public void initialize(){
        gThreeCircle.setVisible(false);
    }

    /*
        This method runs when user clicks the initial pane shown when game three loads
        Set Visibility of some nodes to true and some to false
        Tracks the time when the game started and when the user finished clicking all the circles
        Run a loop and called showCircle method which shows a circle at random position
        when loop ends average time to hit target is calculated and displayed
     */
    public void startGameThree(ActionEvent actionEvent) {

        gThreeLabelInfo.setVisible(false);
        gThreeStartButton.setVisible(false);

        int cycle = 30;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < cycle; i++) {
            showCircle(i,cycle);
        }
        long endTime = System.currentTimeMillis();
        long diff = (endTime - startTime) / cycle;
        gThreeLabelHeading.setText("Average Time: " + diff + " ms");
    }

    /*
        generated random x and y coordinated for circle
        creates a new circle each time with radius 20
        updates remaining target label
     */
    private void showCircle(int i,int cycle) {
        double dx = Math.random()*635;
        double dy = Math.random()*270;
        this.gThreeCircle = new Circle(dx,dy,20, Color.LIGHTGREY);
        gThreeCircle.setVisible(true);
        gThreeLabelHeading.setText("Remaining Target: "+ (cycle-i));
        gThreeCanvasHolderPane.getChildren().addAll(gThreeCircle);
        pause();
    }

    /*
        This method runs when user clicks Main Menu button.
        This method navigates user to home page.
     */
    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().goToHomePage(actionEvent);
    }

    /*
        This method runs when circle is clicked
        I check if the clicked coordinates are contained inside the circle, if yes, I remove the circle and resume
        the game
     */
    public void onClick(MouseEvent mouseEvent) {
        if (this.gThreeCircle.contains(mouseEvent.getX(),mouseEvent.getY())){
            this.gThreeCanvasHolderPane.getChildren().removeAll(gThreeCircle);
            resume();
        }
    }

    /*
        This method runs when user clicks Play Again button.
        This method navigates user to game two again.
     */
    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openGameThree(actionEvent);
    }
}
