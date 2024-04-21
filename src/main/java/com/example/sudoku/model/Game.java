package com.example.sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Game {
    private final int BASE = 3;
    private final int LADO = BASE * BASE;
    private int[][] matrizGanadora;
    public Game() {
        matrizGanadora = new int[LADO][LADO]; // inicializar matriz ganadora
        llenarMatriz(); // llenamos matriz
    }
    /**
     * @param fila fila del patrón
     * @param columna columna del patrón
     *
     * @return patron ya con la formula
     * */
    private Integer patron(int fila, int columna) {
        /*
        * (3 * (1 % 3) + 1 / 3 + 0) % 9 = 3
        * */
        return (BASE * (fila % BASE) + fila / BASE + columna) % LADO;
    }

  /**
   * Retorna una lista barajada de forma aleatoria
   * @param list Lista ordenada
   * @return lista barajada
   * */
    private <T> List<T> shuffle(List<T> list) {
        List<T> shuffled = new ArrayList<>(list); // Crear una copia de la lista
        Collections.shuffle(shuffled); // Barajar la lista
        return shuffled;
    }

    /**
     *
     * */
    private void llenarMatriz() {
        ArrayList<Integer> baseRango = new ArrayList<>();
        Stack<Integer> filas = new Stack<>();
        Stack<Integer> columnas = new Stack<>();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0;i<this.BASE;i++) {
            baseRango.add(i);
        }
        for (int g :  shuffle(baseRango)) {
            for (int f : shuffle(baseRango)) {
                filas.push(g * BASE + f);
            }
        }
        for (int g : shuffle(baseRango)) {
            for (int c : shuffle(baseRango)) {
                columnas.push(g * BASE + c);
            }
        }
        for (int i = 1;i<=LADO;i++) {
            nums.add(i);
        }
        nums = shuffle(nums);

        for (int f = 0;f<filas.size();f++) {
            for (int c=0;c<columnas.size();c++){
                matrizGanadora[f][c] = nums.get(patron(f, c));
                System.out.print(matrizGanadora[f][c] + " ");
            }
            System.out.println("\n");

        }
    }
}
