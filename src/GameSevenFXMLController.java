/*
    VISUAL MEMORY
    This is the Controller class of game_seven.fxml
    In this class, I show user some cells in grid pane turns into white and user have to click the same cells
    but the order doesn't matter. If user get all white turned cell right then they advance to higher level.
    If not, their strikes decrease by 1. When user is out of strikes, they are shown a game over message with their
    final score.
    user can save the score or restart the game.
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class GameSevenFXMLController extends UsersScore {

    // Accessing nodes from corresponding FXML file
    @FXML
    private Pane gSevenCanvasHolderPane;
    @FXML
    public Button save_score_btn;
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

    //Declaring global variables
    private int sizeOfGrid = 3; //tracks size of grid. 3 means 3*3 grid
    private int level = 1; //tracks level of game
    private int strike = 2; //tracks strikes
    private int count = 0; //tracks count to increase the size of grid
    private final ArrayList<int[]> cellList = new ArrayList<>(); //list to hold int array of row and column index
    private final ArrayList<Button> btnList = new ArrayList<>(); //list to hold all buttons added into gridpane
    private final ArrayList<Button> shownBtnList = new ArrayList<>(); //list to hold all white turned buttons
    private final ArrayList<Button> clickedBtnList = new ArrayList<>(); //list to hold all buttons that user clicks
    private final Pane gridRoot = new Pane(); //root to add gridpane
    private final Pane showResultRoot = new Pane(); //root to show result

    /*
        This method runs when user clicks the initial pane shown when game seven loads
        called runGame() method.
     */
    public void startGameSeven(MouseEvent mouseEvent) {
        runGame();
    }

    // add int array to cellList as row index and column index
    private void gridCellIndexes() {
        cellList.clear();
        for (int i = 0; i < sizeOfGrid; i++) {
            for (int j = 0; j < sizeOfGrid; j++) {
                int[] cell = {i,j};
                cellList.add(cell);
            }
        }
    }

    // add row and column constraints in the grid pane
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

    //adding buttons in each cell of grid pane
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

    //sets visibility of few nodes to false
    public void initialize(){
        gSevenGrid.setVisible(false);
        gSevenLevelLabel.setVisible(false);
        gSevenStrikeLabel.setVisible(false);
    }

    /*
        add gridRoot pane to main pane of game seven
        called gridBuild(), gridCellIndexes() and addBtnGrid() method to develop and show a grid on the pane
        called showBoxes() to show white turned cells to user which they have to remember
     */
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

    // list that holds indexes of btnList to generate a random index
    ArrayList<Integer> indexArr = new ArrayList<>();

    /*
        updates level and strike labels
        increase the grid size when the counter is 2 and sets the count to 0 again
        turn level+2 number of buttons to white for time in seconds equivalent to level the user is in
        Used Timeline for the transition
        adds button to clickedBtnList when user clicks in any button
        when size of clickedBtnList and shownBtnList equals, checkBoxes() method is called
     */
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
                    clickedBtn.setStyle("-fx-background-color: WHITE");
                    clickedBtnList.add(clickedBtn);
                    if (clickedBtnList.size()==shownBtnList.size()){
                        checkBoxes();
                    }
                }
            });
        }
        count++;
    }

    //generates non repeated random value in a range
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

    /*
        checks if the user clicked cell are the ones that were turned to white
        If yes, advance to higher level
        If No, decrease strikes
        If all strikes is finished, then called resultPane() method
     */
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
            for (Button b:clickedBtnList)
            {
                b.setStyle("-fx-background-color: LIGHTBLUE");
            }
            clickedBtnList.clear();
            showBoxes();
        }else{
            strike--;
            if (strike>0){
                count--;
                shownBtnList.clear();
                for (Button b:clickedBtnList)
                {
                    b.setStyle("-fx-background-color: LIGHTBLUE");
                }
                clickedBtnList.clear();
                showBoxes();
            }else{
                resultPane();
            }
        }
    }

    /*
        displays game over message with users final score
        Added to main pane
     */
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
        save_score_btn.setDisable(false);

        gSevenMainPane.getChildren().addAll(showResultRoot);

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
        This method navigates user to game seven again.
     */
    public void restartGame(ActionEvent actionEvent) throws IOException {
        new HomePageFXMLController().openGameSeven(actionEvent);
    }
    /*
        This method saves the score of user
     */
    public void saveScore(ActionEvent actionEvent) {
        UsersScore.visualMemoryScore = level-1;
    }
}
