package com.example.sudoku.controller;

import com.example.sudoku.model.Game;

import com.example.sudoku.view.GameStage;
import com.example.sudoku.view.WelcomeStage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class GameController {

    private Game game;
    private Timeline timeline;

    // FXML
    @FXML
    private GridPane gridPane;
    @FXML
    private Label interactionLabel;
    @FXML
    private VBox numbers;
    @FXML
    private Label chronometer;
    @FXML
    private Label errors;

    /**
     *Initializes the game controller.
     * Performs setup tasks such as setting up the timeline and populating the Sudoku board.
     * */
    public void initialize(){
        lineTime();
        fillBoard();
    }

    /**
     * Fills the sudoku with numbers
     * */
    public void fillBoard() {
        game = new Game();
        int[][] grid = game.getWinnerBoard();

        List<Integer> indexList = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8));
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                Collections.shuffle(indexList);
                int showedNumbers = 0;
                for (int index : indexList) {

                    int r = row + index / 3;
                    int c = col + index % 3;

                    TextField textField = new TextField();
                    textField.getStyleClass().add("text-field-grid");
                    textField.getProperties().put("row", r);
                    textField.getProperties().put("col", c);
                    if (showedNumbers < 4) {
                        textField.setText(String.valueOf(grid[r][c]));
                        textField.setEditable(false);
                        showedNumbers++;
                    } else {
                        textField.getStyleClass().add("text-field-editable");
                        // add an event listener to verify user's input
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


//    public static Pane getPaneAt(GridPane gridPane, int fila, int columna) {
//        for (Node nodo : gridPane.getChildren()) {
//            if (GridPane.getRowIndex(nodo) != null && GridPane.getRowIndex(nodo) == fila
//                    && GridPane.getColumnIndex(nodo) != null && GridPane.getColumnIndex(nodo) == columna) {
//                if (nodo instanceof Pane) {
//                    return (Pane) nodo;
//                }
//            }
//        }
//        return null;
//    }
//
    /**
     * verify the board is equal to the matrix and finish game with a congratulation's message
     * */
    private boolean verifyWinner() {
        int[][] winnerBoard = game.getWinnerBoard();
        for (Node cell : gridPane.getChildren()) {
            if (cell != null){
                if (cell instanceof TextField text) {
                    Integer row = GridPane.getRowIndex(cell);
                    Integer col = GridPane.getColumnIndex(cell);
                    if (row != null && col != null) {
                        boolean isEqual = Objects.equals(text.getText(), String.valueOf(winnerBoard[row][col]));
                        if (!isEqual)
                            return false;
                    }
                }

            }
        }
        game.finishGame();
        setInteractionLabel("Felicidades, Has Ganado", "orange");
        return true;
    }

    /**
     * verify the numbers left
     * @see GameController#numberLeft(int)
     */
    private void handleNumbersLeftChange() {
        for (Node node : numbers.getChildren()) {
            if (node instanceof HBox hbox && node.getStyleClass().contains("box")) {
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

    /**
     * return the amount of times a number is in the board
     * @param number the number to search
     * @return the amount of times a number is in the board
     */
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

    /**
     * I'ts used to edit the color and text in an interface's element
     * @param text the text in the label
     * @param color the color used
     */
    private void setInteractionLabel(String text, String color) {
        interactionLabel.setStyle("-fx-text-fill:" + color);
        interactionLabel.setText(text);
    }

    /**
    * add an event to a textfield
     * @param newValue value to replace the text
     * @param oldValue old value
     * @param textField the textfield which the event is going to be applied
    * */
    private void handleTextFieldChange(String newValue, String oldValue, TextField textField) {
        if (!newValue.matches("\\d*")) { // verify if the input is not a number
            setInteractionLabel("¡Inválido! Por favor, ingrese un número.", "purple");
            textField.setText(oldValue); // replace the old value
        } else {
            int row = (int) textField.getProperties().get("row");
            int col = (int) textField.getProperties().get("col"); // store the inputs
            int valueInMatrix = game.getWinnerBoard()[row][col]; // search in the winnerBoard
            if (newValue.isEmpty()) { // verify if the value is empty
                 return; // stop the event
            }
            int valueEntered = Integer.parseInt(newValue);
            if (valueEntered == valueInMatrix) {
                textField.setEditable(false); // set the textfield in readonly
                setInteractionLabel("El número ingresado es correcto :)", "green");
                handleNumbersLeftChange();
                verifyWinner();
            } else {
                setInteractionLabel("El número ingresado no es correcto :(", "red"); // set the text if the user is wrong
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                    textField.clear(); // delete the textfiel
                }));
                game.setErrors(game.getErrors()+1);
                errors.setText(String.valueOf(game.getErrors()));
                timeline.play(); // start the animation
            }
        }
    }

    /**
     * add a timer
     * */
    public void lineTime(){
        long start = System.currentTimeMillis();
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    long elapsedTime = System.currentTimeMillis() - start;
                    long seconds = elapsedTime / 1000;
                    long minutes = seconds / 60;
                    seconds = seconds % 60;
                    chronometer.setText(String.format("%02d:%02d", minutes, seconds));
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    /**
     * View Buttons
     * return to the welcomeView
     */
    public void returnHome() throws IOException {
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
    }

    /**
     * restart the game and deletes all user's input
     * */
    public void restart(){
        for (Node node : gridPane.getChildren()) {
            if (node instanceof TextField textField && node.getStyleClass().contains("text-field-editable")) {
                textField.clear();
            }
        }
        errors.setText("0");
        game.setErrors(0);
        timeline.stop();
        lineTime();
    }

    /**
     * start with another game and board
     * */
    public void newGame(){
        gridPane.getChildren().removeIf(node -> node instanceof TextField);
        errors.setText("0");
        game.setErrors(0);
        fillBoard();
        timeline.stop();
        lineTime();

    }
}


