/*
    handles logic of game six: CHIMP TEST
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class GameSixBoxDraw extends GameSixFXMLController {

    //Declared global variables
    private int level = 1;  //tracks level
    private int strikes  = 0; //track lives remaining
    private final Pane pane;
    private final ArrayList<Button> btnsClicked = new ArrayList<>(); //list of clicked buttons
    private final ArrayList<Button> btnsShown = new ArrayList<>(); //list of shown buttons

    //constructor
    public GameSixBoxDraw(Pane gSixCanvasHolderPane) {
        this.pane = gSixCanvasHolderPane;
    }
    //constructor
    public GameSixBoxDraw(Pane gSixCanvasHolderPane,int level) {
        this.pane = gSixCanvasHolderPane;
        this.level = level;
    }

    //called showPattern() method
    public void start() {
        showPattern();
    }
    //variable to track how many times user clicks button
    final int[] count = {0};

    /*
        for each level, shown level + 3 buttons with corresponding numbers on them
        Added all buttons to pane
        when button is clicked, it disappears
        After user finishes clicking, checkPattern() method is called
     */
    private void showPattern() {
        this.pane.getChildren().clear();
        for (int i = 1; i<= level+3; i++){
            Button btn = new Button();
            btn.setText(String.valueOf(i));

            btn.setLayoutX(Math.random()*635);
            btn.setLayoutY(Math.random()*270);

            btn.setStyle("-fx-background-color: LIGHTGREY");

            this.pane.getChildren().add(btn);
            btnsShown.add(btn);

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    count[0]++;
                    btnsClicked.add((Button) event.getSource());
                    ((Button) event.getSource()).setVisible(false);
                    if (count[0]==1){
                        for (Button b: btnsShown
                        ) {
                            b.setTextFill(Color.LIGHTGREY);
                        }
                    }
                    if (count[0]==level+3){
                        checkPattern();
                        count[0]=0;
                    }
                }
            });
        }
    }
    private boolean checkError = false; //checks if the order in which user clicks the button is right or wrong
    /*
        Checking if the order in which user clicks the button is same as the order in which they are shown
        If yes, advance to higher level
        If Not, decrease strikes and call showStrikesMessage() method and continue the same level
        If all strikes are gone, then showGameOverMessage() is called
     */
    private void checkPattern() {
        this.pane.getChildren().clear();
        for (int i = 0; i< btnsClicked.size(); i++) {
            if (!btnsClicked.get(i).getText().equals(String.valueOf(i+1))){
                checkError=true;
            }
        }
        if (!checkError){
            level++;
            Label strikeMessage = new Label("SCORE: "+ (level+2)+"\nSTRIKES: "+strikes+" of 2");
            Button button = new Button("Continue");

            button.setLayoutX(this.pane.getWidth()/2 - 30);
            button.setLayoutY(this.pane.getHeight()/2 + 70);

            button.setOnAction(event -> new GameSixBoxDraw(this.pane,level).showPattern());

            strikeMessage.setFont(new Font(22));
            strikeMessage.setTextFill(Color.WHITE);
            strikeMessage.setPrefWidth(this.pane.getWidth());
            strikeMessage.setPrefHeight(this.pane.getHeight());
            strikeMessage.setAlignment(Pos.CENTER);

            this.pane.getChildren().addAll(strikeMessage,button);
        }else{
            strikes++;
            if (strikes==2) {
                showGameOverMessage();
            }else{
                showStrikesMessage();
            }
        }
    }
    /*
        display the score and strikes message
     */
    private void showStrikesMessage() {
        Label strikeMessage = new Label("SCORE: "+ (level+2)+"\nSTRIKES: "+strikes+" of 2");
        Button button = new Button("Continue");

        button.setLayoutX(this.pane.getWidth()/2 - 30);
        button.setLayoutY(this.pane.getHeight()/2 + 70);

        button.setOnAction(event -> showPattern());

        strikeMessage.setFont(new Font(23));
        strikeMessage.setTextFill(Color.WHITE);
        strikeMessage.setPrefWidth(this.pane.getWidth());
        strikeMessage.setPrefHeight(this.pane.getHeight());
        strikeMessage.setAlignment(Pos.CENTER);

        this.pane.getChildren().addAll(strikeMessage,button);
    }

    /*
        display game over message with final score
     */
    private void showGameOverMessage() {
        Label loseMessage = new Label("     You Lose\n "+"Your Score: "+(level+2));

        loseMessage.setFont(new Font(24));
        loseMessage.setTextFill(Color.WHITE);
        loseMessage.setPrefWidth(this.pane.getWidth());
        loseMessage.setPrefHeight(this.pane.getHeight());
        loseMessage.setAlignment(Pos.CENTER);

        this.pane.getChildren().addAll(loseMessage);
    }

    public int getLevel() {
        return this.level;
    }
}
