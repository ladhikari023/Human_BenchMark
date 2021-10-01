import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;

public class GameOneFXMLController {
    @FXML
    Canvas gOneCanvas;
    @FXML
    Label gOneLabelHeading; //Reaction Time Test
    @FXML
    Label gOneLabelInfo; //When the red box turns green, click as quickly as you can
    @FXML
    Label gOneLabelStart; //Click anywhere to start
    @FXML
    Pane gOneCanvasHolderPane;

    private long startTime;
    private long endTime;
    private PauseTransition wait;

    public void initialize(){
        gOneCanvasHolderPane.setStyle("-fx-background-color: RED");
    }
    boolean started = false;
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
            double diff = endTime - startTime;
            gOneLabelStart.setText((diff - 3000) + " ms");
        }
    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().goToHomePage(actionEvent);
    }

    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openGameOne(actionEvent);
    }
}
