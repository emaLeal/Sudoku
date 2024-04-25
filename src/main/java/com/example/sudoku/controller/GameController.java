package com.example.sudoku.controller;

import com.example.sudoku.model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.*;

public class GameController {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label interactionLabel;
    private Game game;

    public void initialize() {
        game = new Game();
        int[][] grid = game.getMatrizGanadora();
        System.out.println(game);
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
                    textField.getProperties().put("row", r);
                    textField.getProperties().put("col", c);
                    if (numerosMostrados < 4) {
                        textField.setText(String.valueOf(grid[r][c]));
                        textField.setEditable(false);
                        numerosMostrados++;
                    } else {
                        textField.getStyleClass().add("text-field-editable");
                        // Agregar un escucha evento para verificar la entrada del usuario
                        textField.textProperty().addListener((observable, oldValue, newValue) -> {
                            if (!newValue.matches("\\d*")) { // Verificar si la entrada no es un número con el metodo matches
                                setInteractionLabel("Invalido!! Por favor, ingrese un número.", "purple");
                                textField.setText(oldValue); // Restaurar el valor anterior
                            } else {
                                int fila = (int) textField.getProperties().get("row");
                                int columna = (int) textField.getProperties().get("col");//almacenamos los valores ingrasados por teclado
                                int valueInMatrix = game.getMatrizGanadora()[fila][columna];//evaluamos en la matrz ganadora
                                if (newValue.isEmpty()) { // Verificar si el usuario ha borrado el número
                                    return; // Nada si el campo está vacío
                                }
                                int valueEntered = Integer.parseInt(newValue);
                                if (valueEntered == valueInMatrix) {
                                    textField.setEditable(false); // Desactivar la edición si el número es correcto
                                    setInteractionLabel("El numero ingresado es correcto :)", "purple");
                                } else {
                                    setInteractionLabel("El numero ingrasado no es correcto :(", "purple");// imprime mensaje dado el caso
                                }
                            }
                        });
                    }
                    gridPane.add(textField, c, r);
                }
            }
        }
    }

    private void setInteractionLabel(String text, String color) {
        interactionLabel.setStyle("-fx-text-fill:" + color);
        interactionLabel.setText(text);
    }
}
