import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class GameSevenFXMLController {
    @FXML
    private Pane gSevenCanvasHolderPane;
    @FXML
    private Canvas gSevenCanvas;
    @FXML
    private Label gSevenLabelStart;
    @FXML
    private Label gSevenLabelHeading;
    @FXML
    private Label gSevenLabelInfo;
    @FXML
    private Label gSevenLevelLabel;
    @FXML
    private Label gSevenStrikeLabel;
    @FXML
    private Button main_menu_btn;
    @FXML
    private GridPane gSevenGrid;
    @FXML
    private AnchorPane gSevenMainPane;

    private int sizeOfGrid = 3;
    private int level = 1;
    private int strike = 3;
    private int count = 0;
    private final ArrayList<int[]> cellList = new ArrayList<>();
    private final ArrayList<Button> btnList = new ArrayList<>();
    private final ArrayList<Button> shownBtnList = new ArrayList<>();
    private final ArrayList<Button> clickedBtnList = new ArrayList<>();
    private final Pane gridRoot = new Pane();
    private final Pane showResultRoot = new Pane();

    public void startGameSeven(MouseEvent mouseEvent) {
        runGame();
    }

    private void gridCellIndexes() {
        cellList.clear();
        for (int i = 0; i < sizeOfGrid; i++) {
            for (int j = 0; j < sizeOfGrid; j++) {
                int[] cell = {i,j};
                cellList.add(cell);
            }
        }
    }

    private void gridBuild() {
        gSevenGrid.getRowConstraints().clear();
        gSevenGrid.getColumnConstraints().clear();
        for (int i = 0; i < sizeOfGrid; i++) {
            RowConstraints row = new RowConstraints(260/sizeOfGrid);
            gSevenGrid.getRowConstraints().add(row);
            ColumnConstraints col = new ColumnConstraints(260/sizeOfGrid);
            gSevenGrid.getColumnConstraints().add(col);
        }
    }

    private void addBtnOnGrid(){
        btnList.clear();
        for (int i = 0; i < cellList.size(); i++) {
            int[] point = cellList.get(i);
            Button btn = new Button(" ");
            btn.setPrefSize(260/sizeOfGrid,260/sizeOfGrid);
            btn.setStyle("-fx-background-color: LIGHTBLUE");
            btnList.add(btn);
            this.gSevenGrid.add(btn,point[0],point[1]);
        }
    }

    public void initialize(){
        gSevenGrid.setVisible(false);
        gSevenLevelLabel.setVisible(false);
        gSevenStrikeLabel.setVisible(false);
    }

    private void runGame() {
        gridRoot.setPrefWidth(gSevenCanvasHolderPane.getWidth());
        gridRoot.setPrefHeight(gSevenCanvasHolderPane.getHeight());
        gridRoot.setLayoutX(gSevenCanvasHolderPane.getLayoutX());
        gridRoot.setLayoutY(gSevenCanvasHolderPane.getLayoutY());
        gridRoot.setStyle("-fx-background-color: BLUE");
        gridRoot.getChildren().addAll(gSevenGrid,gSevenLevelLabel,gSevenStrikeLabel);
        gSevenGrid.setVisible(true);
        gSevenLevelLabel.setVisible(true);
        gSevenStrikeLabel.setVisible(true);

        gridBuild();
        gridCellIndexes();
        addBtnOnGrid();
        showBoxes();
        gSevenMainPane.getChildren().add(gridRoot);
    }


    ArrayList<Integer> indexArr = new ArrayList<>();
    private void showBoxes() {
        gSevenLevelLabel.setText("Level: "+level);
        gSevenStrikeLabel.setText("Strike: "+strike);
        if (count==2){
            gSevenGrid.getChildren().clear();
            count=0;
            sizeOfGrid++;
            gridCellIndexes();
            gridBuild();
            addBtnOnGrid();
        }
        indexArr.clear();
        for (int i = 0; i < btnList.size(); i++) {
            indexArr.add(i);
        }
        for (int i = 0; i < level+2; i++) {
            int rand = getRandom();
            Button btn = btnList.get(rand);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), evt -> btn.setStyle("-fx-background-color: WHITE")),
                    new KeyFrame(Duration.seconds(level), evt -> btn.setStyle("-fx-background-color: LIGHTBLUE")));
            timeline.play();
            shownBtnList.add(btn);
        }

        for (int i = 0; i < btnList.size(); i++) {
            Button clickedBtn = btnList.get(i);
            clickedBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //System.out.println(GridPane.getRowIndex((Node)event.getSource())+ " and "+ GridPane.getColumnIndex((Node)event.getSource()));
                    clickedBtnList.add(clickedBtn);
                    if (clickedBtnList.size()==shownBtnList.size()){
                        checkBoxes();
                    }
                }
            });
        }
        count++;
    }

    private int getRandom() {
        int rand =  (int)(Math.random()*indexArr.size()-1);
        int randIndex = 0;
        for (int i = 0; i < indexArr.size(); i++) {
            if (i==rand){
                randIndex = indexArr.get(i);
                indexArr.remove(i);
            }
        }
        return randIndex;
    }

    private void checkBoxes() {
        boolean isRight = false;
        boolean isGameOver = false;
        for (int i = 0; i < clickedBtnList.size(); i++) {
            isRight = false;
            for (int j = 0; j < shownBtnList.size(); j++) {
                if (shownBtnList.get(j)==clickedBtnList.get(i)){
                    isRight  = true;
                    shownBtnList.remove(j);
                    break;
                }
            }
            if (!isRight){
                isGameOver = true;
                break;
            }
        }
        if (!isGameOver){
            level++;
            shownBtnList.clear();
            clickedBtnList.clear();
            showBoxes();
        }else{
            strike--;
            if (strike>0){
                count--;
                shownBtnList.clear();
                clickedBtnList.clear();
                showBoxes();
            }else{
                resultPane();
            }
        }
    }

    private void resultPane() {
        gridRoot.getChildren().clear();
        showResultRoot.setPrefWidth(gSevenCanvasHolderPane.getWidth());
        showResultRoot.setPrefHeight(gSevenCanvasHolderPane.getHeight());
        showResultRoot.setLayoutX(gSevenCanvasHolderPane.getLayoutX());
        showResultRoot.setLayoutY(gSevenCanvasHolderPane.getLayoutY());
        showResultRoot.setStyle("-fx-background-color: BLUE");
        showResultRoot.getChildren().addAll(gSevenLabelStart,gSevenLabelHeading,gSevenLabelInfo);

        gSevenLabelHeading.setText("Game Over");
        gSevenLabelInfo.setText("Level: "+level);
        gSevenLabelInfo.setFont(new Font(20));
        gSevenLabelStart.setText("Click Save to Save the Score");


        gSevenMainPane.getChildren().addAll(showResultRoot);

    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().goToHomePage(actionEvent);
    }

    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openGameSeven(actionEvent);
    }
}
