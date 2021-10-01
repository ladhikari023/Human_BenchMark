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

    private int score = 0;
    private int lives = 3;
    private String currShowingWord = "";

    String[] words = {"Hey","Hola","Namaste","Ssup","Dog","Puppy","Elephant"};
    ArrayList<String> allWords = new ArrayList<>();
    ArrayList<String> shownWords = new ArrayList<>();

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

    public void initialize(){
        for (int i=0;i<words.length;i++){
            allWords.add(words[i]);
        }
        gFiveWordViewLabel.setVisible(false);
        gFiveSeenBtn.setVisible(false);
        gFiveNewBtn.setVisible(false);
    }
    public void startGameFive(MouseEvent mouseEvent) {

        gFiveLabelStart.setVisible(false);
        gFiveLabelInfo.setVisible(false);
        gFiveWordViewLabel.setVisible(true);
        gFiveSeenBtn.setVisible(true);
        gFiveNewBtn.setVisible(true);

        runGameFive();
    }

    private void runGameFive(){
        System.out.println(shownWords+ " - shown words");
        this.currShowingWord = allWords.get((int) (Math.random()*(allWords.size()-1)));
        gFiveLabelHeading.setText("Lives: | "+lives+"   Score: | "+score);
        gFiveWordViewLabel.setText(currShowingWord);
    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().goToHomePage(actionEvent);
    }

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
}
