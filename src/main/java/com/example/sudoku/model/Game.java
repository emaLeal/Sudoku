package com.example.sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Game {
    private final int BASE = 3;
    private final int SIDE = BASE * BASE;
    private final int[][] winnerBoard;
    private boolean running;
    private int errors;

 /** Constructor for the Game class.
  * Initializes the game state by creating the winner board matrix, filling it with numbers,
  * setting the game state to running, and initializing the error count.
  **/
    public Game() {
        winnerBoard = new int[SIDE][SIDE]; // initialize winner matrix
        fillBoard(); // fill matrix
        running = true;
        errors = 0;
    }

    /**
     * returns an index in base to the row and column
     * @param row pattern's row
     * @param col pattern's column
     * @return index with the formula
     * */
    private Integer pattern(int row, int col) {
        /*
        * (3 * (1 % 3) + 1 / 3 + 0) % 9 = 3
        * */
        return (BASE * (row % BASE) + row / BASE + col) % SIDE;
    }

  /**
   * returns the sorted list in a random way
   * @param list ordered list
   * @return sorted list
   * */
    private <T> List<T> shuffle(List<T> list) {
        List<T> shuffled = new ArrayList<>(list); // create a copy from the list
        Collections.shuffle(shuffled); // sort the list
        return shuffled;
    }

    /**
     * fill the board to compare and fill the actual sudoku
     * */
    private void fillBoard() {
        // initialize the list with the necesary data
        List<Integer> nums = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9));
        nums = shuffle(nums); // sort the number's list of values
        // fill rows and cols
        Stack<Integer> rows = fillStack();
        Stack<Integer> cols = fillStack();

        // we fill the board
        for (int f = 0;f<rows.size();f++) {
            for (int c=0;c<cols.size();c++){
                /*
                 * fills the cell with the nums index in base to the pattern
                 * in the row and col and returns the same number, because
                 * nums is shuffled is going to be always different
                 */
                winnerBoard[f][c] = nums.get(pattern(f, c));
            }
        }
    }

    /**
     * fills the stack using an array from 0 to 2 and make an equation
     * @return stack with random values
     * */
    private Stack<Integer> fillStack() {
        ArrayList<Integer> baseR = new ArrayList<>(List.of(0, 1, 2));
        Stack<Integer> list = new Stack<>();
        /*
        * runs the baseR list sorted and do an anitated loop
        * and fills the list 9 times in base to the equation and store
        * in the stack
        * */
        for (int g :  shuffle(baseR)) {
            for (int l : shuffle(baseR)) {
                list.push(g * BASE + l);
            }
        }
        return list;
    }

    /**
     * extract the winner matrix and returns it to give it a use
     * @return winnerBoard
     * */
    public int[][] getWinnerBoard(){
        return winnerBoard;
    }

    /**
     * verify if the game is running
     * @return running
     * */
    public boolean stateGame() {
        return running;
    }

    /**
     * finish the game
     * */
    public void finishGame() {
        running = false;
    }


    /**
     * Sets the number of errors in the game.
     * @param errors The number of errors to set.
     */
    public void setErrors(int errors) {
        this.errors = errors;
    }

    /**
     * Retrieves the number of errors in the game.
     * @return The number of errors in the game.
     */
    public int getErrors() {
        return errors;
    }
}
