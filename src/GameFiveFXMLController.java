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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameFiveFXMLController extends UsersScore {

    //Declaring global variables
    private int score = 0;
    private int lives = 3;
    private String currShowingWord = "";

    ArrayList<String> readWords = new ArrayList<>();
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
    private Label gFiveLabelStart;
    @FXML
    private Label gFiveLabelHeading;
    @FXML
    private Label gFiveLabelInfo;
    @FXML
    public Button save_score_btn;

    //adds all words from list to allWords list
    public void initialize() throws IOException {

        // Reads dictionary.txt file and add all words to readWords
        Scanner scanner = new Scanner(new File("/Users/laxmanadhikari/CS_351/humanbenchmark/src/dictionary.txt"));
        while (scanner.hasNextLine()){
            readWords.add(scanner.next());
        }
        scanner.close();
        for (int i = 0; i < 100; i++) {
            int rand = getRandomNumber(0,readWords.size()-1);
            allWords.add(readWords.get(rand));
        }
        gFiveWordViewLabel.setVisible(false);
        gFiveSeenBtn.setVisible(false);
        gFiveNewBtn.setVisible(false);
        save_score_btn.setDisable(false);
    }
    /*
        generates a random int
     */
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /*
        This method runs when user clicks the initial pane shown when game five loads
        sets visibility of few nodes to false and few to true
        called runGameFive() method
     */
    public void startGameFive(MouseEvent mouseEvent){

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
        this.currShowingWord = allWords.get(getRandomNumber(0,allWords.size()-1));
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
            if (lives==0){
                showResult();
            }
        }
        if (!shownWords.contains(currShowingWord)) {
            shownWords.add(currShowingWord);
        }
        runGameFive();
    }

    /*
        Shows game over message along with the player's score
     */
    private void showResult() {
        gFiveWordViewLabel.setVisible(false);
        gFiveLabelInfo.setVisible(true);
        gFiveLabelInfo.setText("Game Over!");
        gFiveNewBtn.setVisible(false);
        gFiveSeenBtn.setVisible(false);
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
            if (lives==0){
                showResult();
            }
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
    /*
        This method saves the score of user
     */
    public void saveScore(ActionEvent actionEvent) {
        UsersScore.verbalMemoryScore = this.score;
    }
}
