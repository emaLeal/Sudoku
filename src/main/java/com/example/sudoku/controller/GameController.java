package com.example.sudoku.controller;

import com.example.sudoku.model.Game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.*;

public class GameController {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label interactionLabel;
    @FXML
    private VBox numbers;
    private Game game;

    public void llenarTablero() {
        game = new Game();
        int[][] grid = game.getMatrizGanadora();
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
                         handleTextFieldChange(newValue, oldValue, textField);
                        });
                    }
                    gridPane.add(textField, c, r);
                }
            }
        }
        handleNumbersLeftChange();
    }

    private void handleNumbersLeftChange() {
        for (Node node : numbers.getChildren()) {
            if (node instanceof HBox && node.getStyleClass().contains("box")) {
                HBox hbox = (HBox) node;
                int leftNumber = 0;
                for (Node children : hbox.getChildren()) {

                    if (children instanceof Label && children.getStyleClass().contains("left")) {
                        leftNumber = numberLeft(Integer.parseInt(((Label) children).getText()));
                    } else
                        ((Label) children).setText(String.valueOf(9 - leftNumber));
                    if (leftNumber == 9) {
                        children.getParent().getStyleClass().add("left-complete");
                        children.getStyleClass().add("left-complete");
                    }
                }

            }
        }
    }



    private int numberLeft(int number) {
        int counter = 0;
        for (Node node : gridPane.getChildren()) {
            if (node instanceof TextField) {
                String text = ((TextField) node).getText();
                if (!text.isEmpty() && Integer.parseInt(text) == number){
                    counter++;
                }
            }
        }
        return counter;
    }

    private void setInteractionLabel(String text, String color) {
        interactionLabel.setStyle("-fx-text-fill:" + color);
        interactionLabel.setText(text);
    }

    private void handleTextFieldChange(String newValue, String oldValue, TextField textField) {


        if (!newValue.matches("\\d*")) { // Verificar si la entrada no es un número con el método matches
            setInteractionLabel("¡Inválido! Por favor, ingrese un número.", "purple");
            textField.setText(oldValue); // Restaurar el valor anterior
        } else {
            int fila = (int) textField.getProperties().get("row");
            int columna = (int) textField.getProperties().get("col"); // almacenamos los valores ingresados por teclado
            int valueInMatrix = game.getMatrizGanadora()[fila][columna]; // evaluamos en la matriz ganadora
            if (newValue.isEmpty()) { // Verificar si el usuario ha borrado el número
                return; // Nada si el campo está vacío
            }
            int valueEntered = Integer.parseInt(newValue);
            if (valueEntered == valueInMatrix) {
                textField.setEditable(false); // Desactivar la edición si el número es correcto
                setInteractionLabel("El número ingresado es correcto :)", "green");
                handleNumbersLeftChange();
            } else {
                setInteractionLabel("El número ingresado no es correcto :(", "red"); // imprime mensaje dado el caso
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                    textField.clear(); // Borrar el TextField
                }));
                timeline.play(); // Iniciar la animación
            }
        }
    }
}
