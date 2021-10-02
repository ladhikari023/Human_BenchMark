/*
    This is the Controller class of homepage.fxml
    In this class, I have given background colors to the corresponding panes of each game shown in home page.
    Also, this class is responsible for user interaction like opening games, restarting games and navigating
    back to home page.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.CodeSource;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class HomePageFXMLController extends UsersScore {

    // Accessing nodes from corresponding FXML file
    @FXML
    public AnchorPane mainPane;
    @FXML
    private Pane gameOnePane;
    @FXML
    private Pane gameTwoPane;
    @FXML
    private Pane gameThreePane;
    @FXML
    private Pane gameFourPane;
    @FXML
    private Pane gameFivePane;
    @FXML
    private Pane gameSixPane;
    @FXML
    private Pane gameSevenPane;
    @FXML
    private Pane gameEightPane;
    @FXML
    public Pane customGamePane;

    // Declared global variables stage and root.
    private Stage stage;
    private Parent root;

    // This method is responsible for navigating user to custom game (Count Master)
    public void openCustomGame(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("custom_game.fxml")));
        stage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    // This method is responsible for navigating user to game eight (Typing game)
    public void openGameEight(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_eight.fxml")));
        stage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    // This method is responsible for navigating user to game seven (Visual Memory Test)
    public void openGameSeven(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_seven.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    // This method is responsible for navigating user to game six (Chimp test)
    public void openGameSix(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_six.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    // This method is responsible for navigating user to game five (Verbal Memory)
    public void openGameFive(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_five.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    // This method is responsible for navigating user to game four (Number Memory)
    public void openGameFour(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_four.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    // This method is responsible for navigating user to game three (Aim Trainer)
    public void openGameThree(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_three.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    // This method is responsible for navigating user to game two (Sequence Memory)
    public void openGameTwo(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_two.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    // This method is responsible for navigating user to game one (Reaction time)
    public void openGameOne(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_one.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    // This method is responsible for navigating user to home page
    public void goToHomePage(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    // generates and return Color with random r,g,b values
    public static Color colorGenerator(){
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 0.5);
    }

    // run while starting the fxml
    public void initialize(){
        bgColor(gameOnePane, gameTwoPane, gameThreePane, gameFourPane);
        bgColor(gameFivePane, gameSixPane, gameSevenPane, gameEightPane);
    }

    // sets background color to the panes of corresponding games
    private void bgColor(Pane gameOnePane, Pane gameTwoPane, Pane gameThreePane, Pane gameFourPane) {
        gameOnePane.setBackground(new Background(new BackgroundFill(colorGenerator(), CornerRadii.EMPTY, Insets.EMPTY)));
        gameTwoPane.setBackground(new Background(new BackgroundFill(colorGenerator(), CornerRadii.EMPTY, Insets.EMPTY)));
        gameThreePane.setBackground(new Background(new BackgroundFill(colorGenerator(), CornerRadii.EMPTY, Insets.EMPTY)));
        gameFourPane.setBackground(new Background(new BackgroundFill(colorGenerator(), CornerRadii.EMPTY, Insets.EMPTY)));
        customGamePane.setBackground(new Background(new BackgroundFill(colorGenerator(),CornerRadii.EMPTY,Insets.EMPTY)));
    }

    public void saveScores(ActionEvent actionEvent) throws FileNotFoundException {
        askUserName();
    }

    private void askUserName() {
        mainPane.getChildren().clear();

        Pane root = new Pane();
        Label userName = new Label("Enter your Name");
        TextField userInput = new TextField();
        userInput.setPromptText("Your Name");
        Button submitBtn = new Button("Submit");

        userName.setPrefWidth(mainPane.getWidth());
        userName.setAlignment(Pos.CENTER);
        userName.setTextFill(Color.WHITE);
        userName.setFont(new Font(20));
        userName.setLayoutY(100);

        userInput.setLayoutY(140);
        userInput.setLayoutX(225);

        submitBtn.setLayoutX(270);
        submitBtn.setLayoutY(200);

        root.getChildren().addAll(userName,userInput,submitBtn);
        root.setPrefWidth(mainPane.getWidth());
        root.setPrefHeight(mainPane.getHeight()-100);
        root.setLayoutX(mainPane.getLayoutX());
        root.setLayoutY(mainPane.getLayoutY()+50);
        root.setStyle("-fx-background-color: BLUE");
        mainPane.getChildren().addAll(root);

        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UsersScore.userName = userInput.getText();
                try {
                    createCSVFile();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                root.getChildren().clear();
                userName.setText("File Created SuccessFully!!");
                Button goBackBtn = new Button("Home Page");
                Button exitGame = new Button("Exit Game");

                goBackBtn.setLayoutX(submitBtn.getLayoutX()-60);
                goBackBtn.setLayoutY(submitBtn.getLayoutY());

                goBackBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            new HomePageFXMLController().goToHomePage(event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                exitGame.setLayoutX(submitBtn.getLayoutX()+60);
                exitGame.setLayoutY(submitBtn.getLayoutY());

                exitGame.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Stage stage = (Stage) exitGame.getScene().getWindow();
                        stage.close();
                    }
                });

                root.getChildren().addAll(userName,goBackBtn,exitGame);
            }
        });
    }

    private void createCSVFile() throws FileNotFoundException {
        File file = new File("GameResults.csv");
        PrintWriter out = new PrintWriter(file);

        out.printf("                           %s                             \n"+
                        "------------------------------------------------------------\n"+
                        "  %s         |     %.2f ms  reaction time\n" +
                        "  %s       |     %d        sequence remembered\n" +
                        "  %s           |     %.2f ms  average time\n" +
                        "  %s         |     %d        size of numbers remembered\n" +
                        "  %s         |     %d        words remembered\n" +
                        "  %s            |     %d        numbers remembered\n" +
                        "  %s         |     %d        visual remembered\n"+
                        "  %s           |     %d        average words per minute\n" +
                        "  %s       |     %d        count remembered\n",UsersScore.getUserName(),
                "Reaction Time",UsersScore.getReactionTimeScore(),
                "Sequence Memory",UsersScore.getSequenceMemoryScore(),
                "Aim Trainer",UsersScore.getAimTrainerScore(),
                "Number Memory",UsersScore.getNumberMemoryScore(),
                "Verbal Memory",UsersScore.getVerbalMemoryScore(),
                "Chimp Test",UsersScore.getChimpTestScore(),
                "Visual Memory",UsersScore.getVisualMemoryScore(),
                "Typing Game",UsersScore.getTypingGameScore(),
                "Counting Master",UsersScore.getCountMasterScore());
        out.close();
    }
}
