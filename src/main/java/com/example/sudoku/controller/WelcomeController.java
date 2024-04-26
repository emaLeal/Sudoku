package com.example.sudoku.controller;

import com.example.sudoku.view.GameStage;
import com.example.sudoku.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
public class WelcomeController {

    /**
     * Starts the game when the corresponding button is clicked.
     * Creates a new instance of the GameStage and deletes the WelcomeStage instance.

     **/
    @FXML
    public void startGame(ActionEvent event) throws IOException {

        GameStage.getInstance();
        WelcomeStage.deleteInstance();
    }
    /**
     * finish the game when the corresponding button is clicked.

     **/
    @FXML
    public void endGame(ActionEvent event) {
        WelcomeStage.deleteInstance();
    }

}