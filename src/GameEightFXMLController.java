/*
    TYPING TEST
    This is the Controller class of game_eight.fxml
    I could only finish upto the display of text in the text area.
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class GameEightFXMLController extends UsersScore {

    // Accessing nodes from corresponding FXML file
    @FXML
    private TextArea gEightTextArea;
    @FXML
    public Button save_score_btn;

    //display the changes of properties of text area when the game loads
    public void initialize(){
        gEightTextArea.setWrapText(true);
        gEightTextArea.setEditable(false);
        gEightTextArea.setText("Typing words come here");
    }

    public void startGameEight(MouseEvent mouseEvent) {
        //game logic goes here
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
        new HomePageFXMLController().openGameEight(actionEvent);
    }
    /*
        This method saves the score of user
     */
    public void saveScore(ActionEvent actionEvent) {
        UsersScore.typingGameScore = 0;
    }
}
