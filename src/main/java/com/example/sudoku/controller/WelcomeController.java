package com.example.sudoku.controller;

import com.example.sudoku.view.GameStage;
import com.example.sudoku.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
public class WelcomeController {

    @FXML
    public void startGame(ActionEvent event) throws IOException {
        System.out.println("Iniciar juego");
        GameStage.getInstance();
        WelcomeStage.deleteInstance();
    }

    @FXML
    public void endGame(ActionEvent event) {
        WelcomeStage.deleteInstance();
    }

}