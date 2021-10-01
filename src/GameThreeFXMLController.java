import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class GameThreeFXMLController {

    private final Object PAUSE_KEY = new Object();
    @FXML
    public Circle gThreeCircle;
    @FXML
    public Button gThreeStartButton;
    @FXML
    private Pane gThreeCanvasHolderPane;
    @FXML
    private Canvas gThreeCanvas;
    @FXML
    private Label gThreeLabelHeading;
    @FXML
    private Label gThreeLabelInfo;
    @FXML
    private Button main_menu_btn;


    private void pause() {
        Platform.enterNestedEventLoop(PAUSE_KEY);
    }

    private void resume() {
        Platform.exitNestedEventLoop(PAUSE_KEY, null);
    }

    public void initialize(){
        gThreeCircle.setVisible(false);
    }

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

    private void showCircle(int i,int cycle) {
        double dx = Math.random()*635;
        double dy = Math.random()*270;
        this.gThreeCircle = new Circle(dx,dy,20, Color.LIGHTGREY);
        gThreeCircle.setVisible(true);
        gThreeLabelHeading.setText("Remaining Target: "+ (cycle-i));
        gThreeCanvasHolderPane.getChildren().addAll(gThreeCircle);
        pause();
    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().goToHomePage(actionEvent);
    }

    public void onClick(MouseEvent mouseEvent) {
        if (this.gThreeCircle.contains(mouseEvent.getX(),mouseEvent.getY())){
            this.gThreeCanvasHolderPane.getChildren().removeAll(gThreeCircle);
            resume();
        }
    }

    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openGameThree(actionEvent);
    }
}
