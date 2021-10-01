import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameTwoFXMLController {
    private final int levelValue = 1;
    private boolean isClicked = false;
    private boolean gameOver = false;
    private int lastIndex = 0;
    private final ArrayList<Label> labelShown = new ArrayList<>();
    private final ArrayList<Label> labelClicked = new ArrayList<>();
    private final ArrayList<Label> labels = new ArrayList<>();
    private final Object PAUSE_KEY = new Object();


    @FXML
    public GridPane gTwoGridPane;
    @FXML
    public Label gTWoLevelLabel;
    @FXML
    public Label gTwoLevelNumLabel;
    @FXML
    private Pane gTwoCanvasHolderPane;
    @FXML
    private Canvas gTwoCanvas;
    @FXML
    private Label gTwoLabelStart;
    @FXML
    private Label gTwoLabelHeading;
    @FXML
    private Label gTwoLabelInfo;
    @FXML
    private Button main_menu_btn;
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

    public void initialize() {
        gTwoGridPane.setVisible(false);
        gTWoLevelLabel.setVisible(false);
        gTwoLevelNumLabel.setVisible(false);
    }

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

            runGameTwo(levelValue);
        } else {
            System.out.println("Was Clicked before");
        }
    }

    public void runGameTwo(int level) throws InterruptedException {
        while (!gameOver) {
            showSequence(level);
            checkSequence(level);
        }
        isClicked = true;
    }

    private void pause() {
        Platform.enterNestedEventLoop(PAUSE_KEY);
    }

    private void resume() {
        Platform.exitNestedEventLoop(PAUSE_KEY, null);
    }

    private void checkSequence(int level) throws InterruptedException {

        for (int i = 0; i < level; i++) {
            pause();
            if (!labelShown.get(i).equals(labelClicked.get(i))) {
                gameOver = true;
                gTwoGridPane.setVisible(false);
                gTwoLabelHeading.setText("You Lose");
                gTwoLabelHeading.setVisible(true);
                break;
            }
        }
        labelClicked.clear();
        int newLevel = level + 1;
        runGameTwo(newLevel);
    }

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

        for (Label l : labelShown
        ) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), evt -> l.setStyle("-fx-background-color: WHITE")),
                    new KeyFrame(Duration.seconds(1), evt -> l.setStyle("-fx-background-color: #2e2ea9")));
            timeline.play();
        }
    }


    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().goToHomePage(actionEvent);
    }

    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openGameTwo(actionEvent);
    }
}
