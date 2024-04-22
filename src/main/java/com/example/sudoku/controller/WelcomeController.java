package com.example.sudoku.controller;

import com.example.sudoku.model.Game;
import com.example.sudoku.view.GameStage;
import com.example.sudoku.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import java.io.IOException;
public class WelcomeController {

    @FXML
    public void StartGame(ActionEvent event) throws IOException {
        System.out.println("Iniciar juego");
        GameStage.getInstance();
        WelcomeStage.deleteInstance();
    }

    @FXML
    public void EndGame(ActionEvent event) {
        WelcomeStage.deleteInstance();
    }

}