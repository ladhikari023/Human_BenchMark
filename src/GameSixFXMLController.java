/*
    CHIMP TEST
    This is the Controller class of game_six.fxml
    In this class, I have to show user few numbers in a button which are disappeared when one
    button is clicked. User have to guess the number and click the rest of the button in same order.
    There are 3 lives in the game. If user takes all 2 lives then a display of game over message with
    scores are shown.
    user can save the score or restart the game again.
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GameSixFXMLController extends UsersScore {

    private GameSixBoxDraw gameSixBoxDraw;

    // Accessing nodes from corresponding FXML file
    @FXML
    private Pane gSixCanvasHolderPane;
    @FXML
    private Canvas gSixCanvas;
    @FXML
    private Label gSixLabelStart;
    @FXML
    private Label gSixLabelHeading;
    @FXML
    private Label gSixLabelInfo;
    @FXML
    private Button main_menu_btn;
    @FXML
    public Button save_score_btn;

    /*
        This method runs when user clicks the initial pane shown when game six loads
        sets visibility of few nodes to false
        called runGameSix() method
     */
    public void startGameSix(MouseEvent mouseEvent) {
        //game logic goes here
        gSixLabelInfo.setVisible(false);
        gSixLabelStart.setVisible(false);
        gSixLabelHeading.setVisible(false);
        save_score_btn.setDisable(false);

        runGameSix();
    }

    /*
        create a new instance of GameSixBoxDraw class and calls start method of that class
     */
    private void runGameSix() {
        GameSixBoxDraw gameSixBoxDraw = new GameSixBoxDraw(this.gSixCanvasHolderPane);
        this.gameSixBoxDraw = gameSixBoxDraw;
        gameSixBoxDraw.start();
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
        This method navigates user to game six again.
     */
    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openGameSix(actionEvent);
    }

    public void saveScore(ActionEvent actionEvent) {
        UsersScore.chimpTestScore = gameSixBoxDraw.getLevel();
    }
}
