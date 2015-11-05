/**
 * Cristiano Vicente RA 443913
 * Claudio Roberto Costa RA 527033
 * Rafael Anselmo RA 525650
 * Melisa Cordeiro RA 532533
 */

package Puzzle;


import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Busca {
    LinkedList<No> list = new LinkedList<>();
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

    // busca em h com limite
    public No buscaProfLimit(No raiz) {
        list.add(raiz);
        while (!list.isEmpty()) {
            No aux = list.remove(list.size() - 1);
            if (testeObjetivo(aux.estado))
                return aux;
            else if (aux.h < limite)
                States.genStates(aux, list);
        }
        return raiz;
    }

    // busca em h
    public No buscaProf(No raiz) {
        list.add(raiz);
        while (!list.isEmpty()) {
            No aux = list.remove(list.size() - 1);
            if (testeObjetivo(aux.estado))
                return aux;
            else
                States.genStates(aux, list);
        }
        return raiz;
    }

    // busca em largura
    public No buscaLargura(No raiz) {
        list.add(raiz);
        while (!list.isEmpty()) {
            No aux = list.remove(0);
            if (testeObjetivo(aux.estado))
                return aux;
            else
                States.genStates(aux, list);
        }
        return raiz;
    }

    public No aStar(No raiz, int op) {
        if (op == 1)
            raiz.setH(misplacedTiles(raiz.estado));
        else
            raiz.setH(manhattanDistance(raiz.estado));

        //genStates(raiz);
        int distance = raiz.getGH();
        No busca = raiz;
        queue.add(raiz);

        while (distance != 0) {

            States.genStates(raiz, queue, op);

            //for (No no : queue)
            //        queue.add(no);


            //while (!list.isEmpty()) {

            break;
        }
        System.out.println(busca.h);

        return busca;
    }
}

class ComparatorH {
    public static Comparator<No> getComparatorH() {
        return new Comparator<No>() {
            @Override
            public int compare(No o1, No o2) {
                if (o1.getH() < o1.getH())
                    return 1;
                else if (o1.getH() > o1.getH())
                    return -1;
                return 0;
            }
        };
    }

    public static Comparator<No> getComparatorGH() {
        return new Comparator<No>() {
            @Override
            public int compare(No o1, No o2) {
                if (o1.getGH() < o2.getGH())
                    return 1;
                else if (o1.getGH() > o2.getGH())
                    return -1;
                return 0;
            }
        };
    }
}