import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class GameEightFXMLController {
    @FXML
    private TextArea gEightTextArea;

    public void initialize(){
        gEightTextArea.setWrapText(true);
        gEightTextArea.setEditable(false);
        gEightTextArea.setText("Typing words come here");
    }

    public void startGameEight(MouseEvent mouseEvent) {
        //game logic goes here
    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().goToHomePage(actionEvent);
    }
}
