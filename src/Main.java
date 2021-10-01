/**
   @author Laxman Adhikari
   CS 351
   Project 2: Human benchmark
   This is the main class of my project 2.
   In this project, I have made nine mini-games referencing the humanbenchmark website. I have tried my best
   to make my games look exactly like on the website. I have used FXML and JavaFx to achieve the goal of the
   project. I have made nine corresponding FXML files with a controller class each. In addition, I have created
   one FXML and controller class specifically for the home page.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/*
    Main class of project 2
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Loading homepage fxml file on the first screen user see
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homepage.fxml")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
