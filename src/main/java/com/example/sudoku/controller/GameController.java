package com.example.sudoku.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GameController {

    @FXML
    private GridPane gridPane;

    public void initialize() {
        // Código que deseas ejecutar antes de cargar la vista
        for (int row = 0; row < gridPane.getRowCount(); row++) {
            for (int col = 0; col < gridPane.getColumnCount(); col++) {
                // Verificar si la celda ya tiene un panel

                    // Crear un nuevo panel y un nuevo label
                    //StackPane panel = new StackPane();
                    TextField textField = new TextField();
                    textField.getStyleClass().add("text-field-transparent");

                    // Agregar el label al panel
                    //panel.getChildren().add(textField);

                    // Agregar el panel a la celda del GridPane
                    gridPane.add(textField, col, row);

            }
        }
        System.out.println("El método initialize() se ha ejecutado.");
    }


}
