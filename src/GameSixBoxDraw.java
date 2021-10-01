import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class GameSixBoxDraw {
    private static final Object PAUSE_KEY = new Object();
    private int level = 1;
    private int strikes  = 0;
    private final boolean gameOver = false;
    private final Pane pane;
    private Button btn;
    private final ArrayList<Button> btnsClicked = new ArrayList<>();
    private final ArrayList<Button> btnsShown = new ArrayList<>();

    public GameSixBoxDraw(Pane gSixCanvasHolderPane) {
        this.pane = gSixCanvasHolderPane;
    }
    public GameSixBoxDraw(Pane gSixCanvasHolderPane,int level) {
        this.pane = gSixCanvasHolderPane;
        this.level = level;
    }

    private void pause() {
        Platform.enterNestedEventLoop(PAUSE_KEY);
    }

    private static void resume() {
        Platform.exitNestedEventLoop(PAUSE_KEY, null);
    }

    public void start() {
        showPattern();
    }

    final int[] count = {0};
    private void showPattern() {
        this.pane.getChildren().clear();
        for (int i = 1; i<= level+3; i++){
            btn = new Button();
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
    private boolean checkError = false;
    private void checkPattern() {
        this.pane.getChildren().clear();
        for (int i = 0; i< btnsClicked.size(); i++) {
            if (!btnsClicked.get(i).getText().equals(String.valueOf(i+1))){
                checkError=true;
            }
        }
        if (!checkError){
            level++;
            new GameSixBoxDraw(this.pane,level).showPattern();
        }else{
            strikes++;
            if (strikes==3) {
                showGameOverMessage();
            }else{
                showStrikesGoneMessage();
            }
        }
    }

    private void showStrikesGoneMessage() {
        Label strikeMessage = new Label("SCORE: "+ (level+3)+"\nSTRIKES: "+strikes+" of 3");
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

    private void showGameOverMessage() {
        Label loseMessage = new Label("     You Lose\n "+"Your Score: "+(level+3));

        loseMessage.setFont(new Font(24));
        loseMessage.setTextFill(Color.WHITE);
        loseMessage.setPrefWidth(this.pane.getWidth());
        loseMessage.setPrefHeight(this.pane.getHeight());
        loseMessage.setAlignment(Pos.CENTER);

        this.pane.getChildren().addAll(loseMessage);
    }
}
