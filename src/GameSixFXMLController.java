import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GameSixFXMLController {
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

    public void startGameSix(MouseEvent mouseEvent) {
        //game logic goes here
        gSixLabelInfo.setVisible(false);
        gSixLabelStart.setVisible(false);
        gSixLabelHeading.setVisible(false);

        runGameSix();
    }

    private void runGameSix() {
        GameSixBoxDraw gameSixBoxDraw = new GameSixBoxDraw(gSixCanvasHolderPane);
        gameSixBoxDraw.start();
    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().goToHomePage(actionEvent);
    }

    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openGameSix(actionEvent);
    }
}
