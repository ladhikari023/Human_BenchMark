import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @FXML
    private Button tryBtn;
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

    private Stage stage;
    private Parent root;

    public void openGameOne(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_one.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openGameEight(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_eight.fxml")));
        stage = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openGameSeven(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_seven.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openGameSix(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_six.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openGameFive(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_five.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openGameFour(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_four.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openGameThree(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_three.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openGameTwo(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game_two.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void goToHomePage(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public Color colorGenerator(){
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 0.5);
    }

    public void initialize(){
        bgColor(gameOnePane, gameTwoPane, gameThreePane, gameFourPane);
        bgColor(gameFivePane, gameSixPane, gameSevenPane, gameEightPane);
        tryBtn.setStyle("-fx-border-color: #0c0c0c");
    }

    private void bgColor(Pane gameOnePane, Pane gameTwoPane, Pane gameThreePane, Pane gameFourPane) {
        gameOnePane.setBackground(new Background(new BackgroundFill(colorGenerator(), CornerRadii.EMPTY, Insets.EMPTY)));
        gameTwoPane.setBackground(new Background(new BackgroundFill(colorGenerator(), CornerRadii.EMPTY, Insets.EMPTY)));
        gameThreePane.setBackground(new Background(new BackgroundFill(colorGenerator(), CornerRadii.EMPTY, Insets.EMPTY)));
        gameFourPane.setBackground(new Background(new BackgroundFill(colorGenerator(), CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
