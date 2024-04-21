package com.example.sudoku.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Game {
    private static final int BASE = 3;
    private static final int SIDE = BASE * BASE;

    public Game() {
        System.out.println("Juego iniciado");
    }

    public static int[][] generateSudoku() {
        int[][] board = new int[SIDE][SIDE];

        // Patrón para una solución válida base
        for (int r = 0; r < SIDE; r++) {
            for (int c = 0; c < SIDE; c++) {
                board[r][c] = (BASE * (r % BASE) + r / BASE + c) % SIDE;
            }
        }

        // Mezclar filas, columnas y números
        List<Integer> rBase = new ArrayList<>();
        for (int i = 0; i < BASE; i++) {
            rBase.add(i);
        }
        Collections.shuffle(rBase);
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        for (int g : rBase) {
            for (int r : shuffle(rBase)) {
                rows.add(g * BASE + r);
            }
            for (int c : shuffle(rBase)) {
                cols.add(g * BASE + c);
            }
        }
        for (int i = 1; i <= SIDE; i++) {
            nums.add(i);
        }
        Collections.shuffle(nums);

        // Producir tablero usando el patrón base aleatorizado
        for (int r : rows) {
            for (int c : cols) {
                board[r][c] = nums.get(board[r][c]);
            }
        }

        return board;
    }

    // Mezclar una lista
    private static List<Integer> shuffle(List<Integer> s) {
        List<Integer> shuffled = new ArrayList<>(s);
        Collections.shuffle(shuffled);
        return shuffled;
    }

    // Imprimir el tablero
    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }


}
