/*
    This is the Controller class of homepage.fxml
    In this class, I have given background colors to the corresponding panes of each game shown in home page.
    Also, this class is responsible for user interaction like opening games, restarting games and navigating
    back to home page.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class HomePageFXMLController {

    // Accessing nodes from corresponding FXML file
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

    // Declared global variables stage and root.
    private Stage stage;
    private Parent root;

    // This method is responsible for navigating user to game one (Reaction time)
    public void openGameOne(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_one.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
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

    // This method is responsible for navigating user to home page
    public void goToHomePage(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    // generates and return Color with random r,g,b values
    public Color colorGenerator(){
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
    }
}
