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

    private final Object PAUSE_KEY = new Object();
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

    private int showNumber;
    private int enteredNumber;
    private int currLevel = 1;

    private void pause() {
        Platform.enterNestedEventLoop(PAUSE_KEY);
    }

    private void resume() {
        Platform.exitNestedEventLoop(PAUSE_KEY, null);
    }

    public void initialize(){
        gFourNextBtn.setVisible(false);
        gFourSubmitButton.setVisible(false);
        gFourTextField.setVisible(false);
    }

    public void startGameFour(MouseEvent mouseEvent) {
        gFourLabelInfo.setVisible(false);
        gFourLabelStart.setVisible(false);

        runGame(currLevel);
    }

    private void runGame(int level) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), evt -> showNumber()),
                new KeyFrame(Duration.seconds(level), evt -> askNumber()));
        timeline.play();
    }

    private void showNumber() {
        gFourLabelInfo.setVisible(false);
        gFourNextBtn.setVisible(false);
        this.showNumber = getRandomNumber((int)Math.pow(10,currLevel-1),(int)Math.pow(10,currLevel));
        gFourLabelHeading.setText(String.valueOf(showNumber));
    }
    private void askNumber() {
        gFourLabelHeading.setText("What was the Number?");
        gFourLabelInfo.setVisible(true);
        gFourLabelInfo.setText("Press Enter To Submit");
        gFourTextField.setVisible(true);
        gFourSubmitButton.setVisible(true);
    }
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

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().goToHomePage(actionEvent);
    }

    public void onClickSubmitBtn(ActionEvent actionEvent) {
        enteredNumber = Integer.parseInt(gFourTextField.getText());
        gFourSubmitButton.setVisible(false);
        showResult(currLevel);
    }

    public void onClickNextBtn(ActionEvent actionEvent) {
        runGame(currLevel++);
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openGameFour(actionEvent);
    }
}
