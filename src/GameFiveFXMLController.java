/*
    VERBAL MEMORY TEST
    This is the Controller class of game_five.fxml
    In this class, I show a random word from a list of word to user.
    User has to choose it the words was shown before or it is a new word.
    They get score of 1 if they get the option right or lose 1 life among 3
    if they get it wrong.
    If all their lives is finished, a game over message with their score is displayed
    user can save the score or restart the game
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class GameFiveFXMLController {

    //Declaring global variables
    private int score = 0;
    private int lives = 3;
    private String currShowingWord = "";

    String[] words = {"Hey","Hola","Namaste","Ssup","Dog","Puppy","Elephant"};
    ArrayList<String> allWords = new ArrayList<>();
    ArrayList<String> shownWords = new ArrayList<>();

    // Accessing nodes from corresponding FXML file
    @FXML
    private Button gFiveNewBtn;
    @FXML
    private Button gFiveSeenBtn;
    @FXML
    private Label gFiveWordViewLabel;
    @FXML
    private Pane gFiveCanvasHolderPane;
    @FXML
    private Canvas gFiveCanvas;
    @FXML
    private Label gFiveLabelStart;
    @FXML
    private Label gFiveLabelHeading;
    @FXML
    private Label gFiveLabelInfo;
    @FXML
    private Button main_menu_btn;

    //adds all words from list to allWords list
    public void initialize(){
        for (int i=0;i<words.length;i++){
            allWords.add(words[i]);
        }
        gFiveWordViewLabel.setVisible(false);
        gFiveSeenBtn.setVisible(false);
        gFiveNewBtn.setVisible(false);
    }

    /*
        This method runs when user clicks the initial pane shown when game five loads
        sets visibility of few nodes to false and few to true
        called runGameFive() method
     */
    public void startGameFive(MouseEvent mouseEvent) {

        gFiveLabelStart.setVisible(false);
        gFiveLabelInfo.setVisible(false);
        gFiveWordViewLabel.setVisible(true);
        gFiveSeenBtn.setVisible(true);
        gFiveNewBtn.setVisible(true);

        runGameFive();
    }

    /*
        show a random word from allWords list
     */
    private void runGameFive(){
        System.out.println(shownWords+ " - shown words");
        this.currShowingWord = allWords.get((int) (Math.random()*(allWords.size()-1)));
        gFiveLabelHeading.setText("Lives: | "+lives+"   Score: | "+score);
        gFiveWordViewLabel.setText(currShowingWord);
    }

    /*
        check if the word was already shown
        If yes, increase score and advance to higher level
        If No, decrease life
     */
    public void onClickSeenBtn(ActionEvent actionEvent) {
        if (shownWords.contains(currShowingWord)){
            score++;
        }else{
            lives--;
        }
        if (!shownWords.contains(currShowingWord)) {
            shownWords.add(currShowingWord);
        }
        runGameFive();
    }
    /*
        check if the word is new
        If yes, increase score and advance to higher level
        If No, decrease life
     */
    public void onClickNewBtn(ActionEvent actionEvent) {
        if (!shownWords.contains(currShowingWord)){
            score++;
        }else{
            lives--;
        }
        if (!shownWords.contains(currShowingWord)) {
            shownWords.add(currShowingWord);
        }
        runGameFive();
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
        This method navigates user to game five again.
     */
    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openGameFive(actionEvent);
    }
}
