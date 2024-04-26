package com.example.sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Game {
    private final int BASE = 3;
    private final int LADO = BASE * BASE;
    private final int[][] matrizGanadora;

    private boolean running;
    public Game() {
        matrizGanadora = new int[LADO][LADO]; // inicializar matriz ganadora
        llenarMatriz(); // llenamos matriz
        running = true;
    }

    /**
     * Funcion que devuelve un index en base a la columna y fila que se le pase
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
     * Funcion para llenar la matriz ganadora y poder compararla a futuro con
     * los inputs del usuario
     * */
    private void llenarMatriz() {
        // Inicializamos la lista de datos necesarias
        List<Integer> nums = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9));
        nums = shuffle(nums); // Sorteamos la lista de numeros para la matriz
        // Llenamos filas y columnas
        Stack<Integer> filas = llenarPila();
        Stack<Integer> columnas = llenarPila();

        // Se llena la matriz
        for (int f = 0;f<filas.size();f++) {
            for (int c=0;c<columnas.size();c++){
                /*
                * Se llenara cada posicion-celda con el index de
                * nums en base al patron dado por la fila y columna
                * y aunque siempre se retornara el mismo numero
                * el valor sera distinto y se hara una matriz 9x9
                * distinta siempre
                * */
                matrizGanadora[f][c] = nums.get(patron(f, c));
            }
        }
    }

    /**
     * LLena las pilas de fila y columna, utilizando un array de
     * 0 hasta 2 para una operacion que devolvera un numero unico en cada ocasión
     * y llena 9 veces la pila
     *
     * @return fila ya llena de valores random
     * */
    private Stack<Integer> llenarPila() {
        ArrayList<Integer> baseRango = new ArrayList<>(List.of(0,1,2));
        Stack<Integer> lista = new Stack<>();
        /* se hace el recorrido de la lista baseRango sorteada
        * y se hace lo mismo dentro, en base a eso se hace la ecuacion
        * g * BASE * l y se llenara la lista 9 veces en base a esta
        * ecuacion, ej:
        * g=0,l=2
        * 0 * BASE + 2 = 2
        * y se guardara en la lista
        * */
        for (int g :  shuffle(baseRango)) {
            for (int l : shuffle(baseRango)) {
                lista.push(g * BASE + l);
            }
        }
        return lista;
    }

    /**
     * una forma de extraer la matriz ganadora y usarla en el juego
     * @return matrizGanadora
     * */
    public int[][] getMatrizGanadora(){
        return matrizGanadora;
    }

    public boolean stateGame() {
        return running;
    }

    public void finishGame() {
        running = false;
    }
}
