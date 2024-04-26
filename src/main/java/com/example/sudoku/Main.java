package com.example.sudoku;

import com.example.sudoku.view.WelcomeStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    /**
     * The main method of the application.
    * */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * The start method of the JavaFX application.
     * Initializes and displays the welcome stage when the application starts.
     * */
    @Override
    public void start(Stage primaryStage) throws IOException {
        WelcomeStage.getInstance();
    }
}
