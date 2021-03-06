/*
    SEQUENCE MEMORY
    This is the Controller class of game_two.fxml
    In this class, I have used a grid pane which I have declared in fxml and each cell is
    given by a label which is named in similar manner as r0c0 for row index 0 and column index 0.
    First, I am showing users random cells turns white which they have to click in order.
    If user is able to click the cells in order as they are shown then they advance to higher level.
    If failed, they are displayed "You Lose" message.
    User can save their score or restart the game.
 */

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameTwoFXMLController extends UsersScore {
    //Declaring global variables used in the class
    private int level = 1;  //level of game
    private boolean isClicked = false; //checks if the pane is clicked or not
    private boolean gameOver = false; //checks if game is over or not
    private int lastIndex = 0;
    private final ArrayList<Label> labelShown = new ArrayList<>(); //lists of labels shown
    private final ArrayList<Label> labelClicked = new ArrayList<>(); //lists of labels clicked
    private final ArrayList<Label> labels = new ArrayList<>(); //lists of labels
    private final Object PAUSE_KEY = new Object(); // pause key variable to pause and resume the interface


    // Accessing nodes from corresponding FXML file
    @FXML
    public GridPane gTwoGridPane;
    @FXML
    public Label gTWoLevelLabel;
    @FXML
    public Label gTwoLevelNumLabel;
    @FXML
    private Label gTwoLabelStart;
    @FXML
    private Label gTwoLabelHeading;
    @FXML
    private Label gTwoLabelInfo;
    @FXML
    public Button save_score_btn;
    @FXML
    private Label r0c0;
    @FXML
    private Label r0c1;
    @FXML
    private Label r0c2;
    @FXML
    private Label r1c0;
    @FXML
    private Label r1c1;
    @FXML
    private Label r1c2;
    @FXML
    private Label r2c0;
    @FXML
    private Label r2c1;
    @FXML
    private Label r2c2;

    /*
        These methods are called from inside the 'on mouse clicked' action on cells of grid
        Each time user clicks the cell, the label of cell is added into labelClicked list
        and thread if paused is resumed.
     */
    @FXML
    void r0c0Clicked(MouseEvent event) {
        labelClicked.add(r0c0);
        resume();
    }

    @FXML
    void r0c1Clicked(MouseEvent event) {
        labelClicked.add(r0c1);
        resume();
    }

    @FXML
    void r0c2Clicked(MouseEvent event) {
        labelClicked.add(r0c2);
        resume();
    }

    @FXML
    void r1c0Clicked(MouseEvent event) {
        labelClicked.add(r1c0);
        resume();
    }

    @FXML
    void r1c1Clicked(MouseEvent event) {
        labelClicked.add(r1c1);
        resume();
    }

    @FXML
    void r1c2Clicked(MouseEvent event) {
        labelClicked.add(r1c2);
        resume();
    }

    @FXML
    void r2c0Clicked(MouseEvent event) {
        labelClicked.add(r2c0);
        resume();
    }

    @FXML
    void r2c1Clicked(MouseEvent event) {
        labelClicked.add(r2c1);
        resume();
    }

    @FXML
    void r2c2Clicked(MouseEvent event) {
        labelClicked.add(r2c2);
        resume();
    }

    // set visibility of these nodes to false
    public void initialize() {
        gTwoGridPane.setVisible(false);
        gTWoLevelLabel.setVisible(false);
        gTwoLevelNumLabel.setVisible(false);
    }

    /*
        This method runs when user clicks the initial pane shown when game two loads
        Set Visibility of some nodes to true and some to false
        Added all labels from fxml to labels list
        called runGameTwo method.
     */
    public void startGameTwo(MouseEvent mouseEvent) throws InterruptedException {
        if (!isClicked) {
            gTwoLabelInfo.setVisible(false);
            gTwoLabelStart.setVisible(false);
            gTwoLabelHeading.setVisible(false);
            gTwoGridPane.setVisible(true);
            gTWoLevelLabel.setVisible(true);
            gTwoLevelNumLabel.setVisible(true);

            labels.add(r0c0);
            labels.add(r0c1);
            labels.add(r0c2);
            labels.add(r1c0);
            labels.add(r1c1);
            labels.add(r1c2);
            labels.add(r2c0);
            labels.add(r2c1);
            labels.add(r2c2);

            runGameTwo(level);
        }
    }

    /*
        Runs in a loop with a condition if gameOver is true or not
        called methods showSequence and checkSequence, both with level as parameter
     */
    public void runGameTwo(int level) throws InterruptedException {
        while (!gameOver) {
            showSequence(level);
            checkSequence(level);
        }
        isClicked = true;
    }

    //pause the interface
    private void pause() {
        Platform.enterNestedEventLoop(PAUSE_KEY);
    }

    //resumes the interface
    private void resume() {
        Platform.exitNestedEventLoop(PAUSE_KEY, null);
    }

    /*
        Updated value of level
        Assign currShownLabel to a random label from labels list
        Added currShownLabel to labelShown list
        Use Animation timer to show the white blink for each shown Labels to user
        called showCell inside Animation timer to access each shown labels and turn them to white
     */

    int index = 0; // tracks the index which is to be shown to user

    private void showSequence(int level) throws InterruptedException {
        Random rand = new Random();
        gTwoLevelNumLabel.setText(String.valueOf(level));

        int randInt = rand.nextInt(9);
        while (randInt == lastIndex) {
            randInt = rand.nextInt(9);
        }
        Label currShownLabel = labels.get(randInt);
        labelShown.add(level - 1, currShownLabel);
        lastIndex = randInt;


        AnimationTimer timer = new AnimationTimer() {
            private long start = 0;
            @Override

            /*
                timer runs as long as index is less than size of labelShown list, when label clicked and
                label shown have same size then timer stops and index is again set to be 0
                otherwise in every one second showCell method is called to show white blink
                when index reaches size of labelShown, timer stops and index is set to be 0.
             */
            public void handle(long now) {
                if (index<labelShown.size()) {
                    if (labelShown.size() == labelClicked.size()) {
                        index = 0;
                        this.stop();
                    }
                    if (now - start > 1_000_000_000) {
                        showCell(index);
                        start = now;
                    }
                }else {
                    this.stop();
                    index = 0;
                }
            }
        };
        timer.start();
    }

    /*
        creates a transition of white blinks in cells
        index is incremented every time to access newer cells
     */
    private void showCell(int i) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), evt -> labelShown.get(i).setStyle("-fx-background-color: WHITE")),
                new KeyFrame(Duration.seconds(0.8), evt -> labelShown.get(i).setStyle("-fx-background-color: #2e2ea9")));
        timeline.play();
        index++;
    }

    /*
        Checked if the order in which labels were shown matches the order in which user clicks the corresponding cell
        of grid pane.
        if the order is correct then user advance to higher level
        if not, "You Lose" message is displayed
     */
    private void checkSequence(int level) throws InterruptedException {

        for (int i = 0; i < level; i++) {
            pause();
            if (!labelShown.get(i).equals(labelClicked.get(i))) {
                gameOver = true;
                gTwoGridPane.setVisible(false);
                this.level = level-1;
                gTwoLabelHeading.setText("You Lose");
                save_score_btn.setDisable(false);
                gTwoLabelHeading.setVisible(true);
                break;
            }
        }
        labelClicked.clear();
        int newLevel = level + 1;
        runGameTwo(newLevel);
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
        This method navigates user to game two again.
     */
    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openGameTwo(actionEvent);
    }
    /*
        This method saves the score of user
     */
    public void saveScore(ActionEvent actionEvent) {
        UsersScore.sequenceMemoryScore = this.level;
    }
}
