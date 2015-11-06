package Puzzle;

import java.util.AbstractCollection;

/**
 * Cristiano Vicente RA 443913
 * Claudio Roberto Costa RA 527033
 * Rafael Anselmo RA 525650
 * Melisa Cordeiro RA 532533
 */

public class States {
    public static void genStates(No aux, AbstractCollection<No> list) {
        genStates(aux, list, 0);
    }

    public static void genStates(No aux, AbstractCollection<No> list, int op) {
        int index = pos_0(aux);
        int posX = index % 3;
        int mov = aux.pos_ant - index;
        if (index < 3)
            list.add(movUP(aux, index, op));
        else if (index < 6) {
            if (mov != 3)
                list.add(movUP(aux, index, op));
            if (mov != -3)
                list.add(movDown(aux, index, op));
        } else
            list.add(movDown(aux, index, op));

        if (posX == 0)
            list.add(movLeft(aux, index, op));
        else if (posX == 1) {
            if (mov != 1)
                list.add(movLeft(aux, index, op));
            if (mov != -1)
                list.add(movRight(aux, index, op));
        } else
            list.add(movRight(aux, index, op));
    }

    // localiza a posição do 0
    public static int pos_0(No aux) {
        for (int i = 0; i < 9; i++) {
            if (aux.estado[i] == 0)
                return i;
        }
        return 9;
    }

    // conjunto de metodos de movimentos
    public static No movUP(No pai, int i, int op) {
        No novo = new No(pai.estado.clone(), "up", pai, pai.g + 1, i);
        novo.estado[i] = pai.estado[i + 3];
        novo.estado[i + 3] = 0;
        novo.setH(getHeutistic(novo.estado, op));
        return novo;
    }

    public static No movDown(No pai, int i, int op) {
        No novo = new No(pai.estado.clone(), "down", pai, pai.g + 1, i);
        novo.estado[i] = pai.estado[i - 3];
        novo.estado[i - 3] = 0;
        novo.setH(getHeutistic(novo.estado, op));
        return novo;
    }

    public static No movLeft(No pai, int i, int op) {
        No novo = new No(pai.estado.clone(), "left", pai, pai.g + 1, i);
        novo.estado[i] = pai.estado[i + 1];
        novo.estado[i + 1] = 0;
        novo.setH(getHeutistic(novo.estado, op));
        return novo;
    }

    public static No movRight(No pai, int i, int op) {
        No novo = new No(pai.estado.clone(), "right", pai, pai.g + 1, i);
        novo.estado[i] = pai.estado[i - 1];
        novo.estado[i - 1] = 0;
        novo.setH(getHeutistic(novo.estado, op));
        return novo;
    }

    public static int getHeutistic(int[] array, int op) {
        int heuristic = 0;
        if (op == 1) {
            // H1 - peças fora do lugar
            for (int i = 0; i < array.length; i++) {
                if ((i != (array[i] - 1)) && (array[i] != 0))
                    heuristic++;
            }
        } else if (op == 2) {
            // H2 - Algoritmo Manhattan distances
            for (int i = 0; i < array.length; i++) {
                if (array[i] != 0)
                    heuristic += Math.abs((i / 3) - ((array[i] - 1) / 3)) + Math.abs((i % 3) - ((array[i] - 1) % 3));
            }
        } else return 0;
        return heuristic;
    }
}
