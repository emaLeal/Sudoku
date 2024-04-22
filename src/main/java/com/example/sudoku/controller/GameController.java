package com.example.sudoku.controller;

import com.example.sudoku.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.*;

public class GameController {

    @FXML
    private GridPane gridPane;
    private Game game;

    public void initialize() {
        // Código que deseas ejecutar antes de cargar la vista
        game = new Game();
        int [][] grid = game.getMatrizGanadora();
        List<Integer> listaIndices = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));


        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                Collections.shuffle(listaIndices);
                int numerosMostrados = 0;
                for (int index : listaIndices) {

                    int r = row + index / 3;
                    int c = col + index % 3;

                    TextField textField = new TextField();
                    textField.getStyleClass().add("text-field-grid");
                    if (numerosMostrados < 4) {
                        textField.setText(String.valueOf(grid[r][c]));
                        textField.setEditable(false); // Para evitar que el usuario modifique los números
                        numerosMostrados++;
                    }else{
                        textField.getStyleClass().add("text-field-editable");
                    }
                    gridPane.add(textField, c, r);
                }

            }
        }

    }
}
