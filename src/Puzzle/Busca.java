/**
 * Cristiano Vicente RA 443913
 * Claudio Roberto Costa RA 527033
 * Rafael Anselmo RA 525650
 * Melisa Cordeiro RA 532533
 */

package Puzzle;


import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Busca {
    List<No> pilha_fila = new LinkedList<>();
    PriorityQueue<No> queue = new PriorityQueue();
    int limite = 30;

    // testa a solução
    public boolean testeObjetivo(int[] base) {
        int objetivo[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
        return Arrays.equals(base, objetivo);
    }

    // H1 - peças fora do lugar
    public int misplacedTiles(int[] array) {
        int heuristic = 0;
        for (int i = 0; i < array.length; i++) {
            if ((i != (array[i] - 1)) && (array[i] != 0))
                heuristic++;
        }
        return heuristic;
    }

    // H2 - Algoritmo Manhattan distances
    public int manhattanDistance(int[] array) {
        int heuristic = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0)
                heuristic += Math.abs((i / 3) - ((array[i] - 1) / 3)) + Math.abs((i % 3) - ((array[i] - 1) % 3));
        }
        return heuristic;
    }

    // localiza a posição do 0
    public int pos_0(No aux) {
        for (int i = 0; i < 9; i++) {
            if (aux.estado[i] == 0)
                return i;
        }
        return 9;
    }

    // grupo de ações possivéis para cada estado v2
    public void sucessor(No aux) {
        int index = pos_0(aux);
        int posX = index % 3;
        int mov = aux.pos_ant - index;
        if (index < 3)
            pilha_fila.add(movUP(aux, index));
        else if (index < 6) {
            if (mov != 3)
                pilha_fila.add(movUP(aux, index));
            if (mov != -3)
                pilha_fila.add(movDown(aux, index));
        } else
            pilha_fila.add(movDown(aux, index));

        if (posX == 0)
            pilha_fila.add(movLeft(aux, index));
        else if (posX == 1) {
            if (mov != 1)
                pilha_fila.add(movLeft(aux, index));
            if (mov != -1)
                pilha_fila.add(movRight(aux, index));
        } else
            pilha_fila.add(movRight(aux, index));
    }

    // conjunto de metodos de movimentos
    public No movUP(No pai, int i) {
        No novo = new No(pai.estado.clone(), "up", pai, pai.g + 1, i);
        novo.estado[i] = pai.estado[i + 3];
        novo.estado[i + 3] = 0;
        return novo;
    }

    public No movDown(No pai, int i) {
        No novo = new No(pai.estado.clone(), "down", pai, pai.g + 1, i);
        novo.estado[i] = pai.estado[i - 3];
        novo.estado[i - 3] = 0;
        return novo;
    }

    public No movLeft(No pai, int i) {
        No novo = new No(pai.estado.clone(), "left", pai, pai.g + 1, i);
        novo.estado[i] = pai.estado[i + 1];
        novo.estado[i + 1] = 0;
        return novo;
    }

    public No movRight(No pai, int i) {
        No novo = new No(pai.estado.clone(), "right", pai, pai.g + 1, i);
        novo.estado[i] = pai.estado[i - 1];
        novo.estado[i - 1] = 0;
        return novo;
    }


    // busca em h com limite
    public No buscaProfLimit(No raiz) {
        pilha_fila.add(raiz);
        while (!pilha_fila.isEmpty()) {
            No aux = pilha_fila.remove(pilha_fila.size() - 1);
            if (testeObjetivo(aux.estado))
                return aux;
            else if (aux.h < limite)
                sucessor(aux);
        }
        return raiz;
    }

    // busca em h
    public No buscaProf(No raiz) {
        pilha_fila.add(raiz);
        while (!pilha_fila.isEmpty()) {
            No aux = pilha_fila.remove(pilha_fila.size() - 1);
            if (testeObjetivo(aux.estado))
                return aux;
            else
                sucessor(aux);
        }
        return raiz;
    }

    // busca em largura
    public No buscaLargura(No raiz) {
        pilha_fila.add(raiz);
        while (!pilha_fila.isEmpty()) {
            No aux = pilha_fila.remove(0);
            if (testeObjetivo(aux.estado))
                return aux;
            else
                sucessor(aux);
        }
        return raiz;
    }

    public No aStar (No raiz, int op) {
        if(op == 1)
            raiz.setH(misplacedTiles(raiz.estado));
        else
            raiz.setH(manhattanDistance(raiz.estado));

        int distance = raiz.h;
        No busca = raiz;
        pilha_fila.add(raiz);
        while (distance != 0) {
            while (!pilha_fila.isEmpty()) {
                No aux = pilha_fila.remove(0);

                System.out.printf("%d %d\n\n", aux.h, aux.g);
                System.out.printf("\n######### %d #########\n\n", distance);
                aux.printEstado();
                if (aux.h == 0)
                    return aux;
                else if (aux.h < distance) {
                    distance = aux.h;
                    System.out.printf("\n######### %d #########\n\n", aux.h);
                    pilha_fila.add(0, aux);
                }
                sucessor(aux);
            }

        }
        System.out.println(busca.h);

        return busca;
    }
}
