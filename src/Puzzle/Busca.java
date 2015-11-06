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
    PriorityQueue<No> queue;

    int limite = 5;

    //################### TESTA O OBJETIVO #########################################
    public boolean testeObjetivo(int[] base) {
        int objetivo[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
        return Arrays.equals(base, objetivo);
    }

    //################### BUSCA EM LARGURA #########################################
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

    //################### BUSCA EM PROFUNDIDADE LIMITADA ###########################
    public No buscaProfLimit(No raiz) {
        list.add(raiz);
        while (!list.isEmpty()) {
            No aux = list.remove(list.size() - 1);
            if (testeObjetivo(aux.estado))
                return aux;
            else if (aux.g < limite)
                States.genStates(aux, list);
        }
        return raiz;
    }

    //################### BUSCA EM PROFUNDIDADE ####################################
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

    //################### BUSCA DE APROFUNDAMENTO ITERATIVO ########################
    public No buscaIDA(No raiz) {
        list.add(raiz);
        while (!list.isEmpty()) {
            No aux = list.remove(list.size() - 1);
            System.out.println(list.size());
            //aux.printEstado();
            if (testeObjetivo(aux.estado))
                return aux;
            else if (aux.g < limite)
                States.genStates(aux, list);
            else if (aux.g == limite && list.isEmpty()) {
                limite++;
                States.genStates(aux, list);
                System.out.println(limite + " g " + aux.g);
            }
        }
        return raiz;
    }

    //################### BUSCA DE CUSTO UNIFORME ##################################
    public No buscaCustoUniforme(No raiz) {
        queue = new PriorityQueue<>(ComparatorH.getComparatorGH());
        queue.add(raiz);
        while (!queue.isEmpty()) {
            No aux = queue.poll();
            if (testeObjetivo(aux.estado))
                return aux;
            else
                States.genStates(aux, queue);
        }
        return raiz;
    }

    //################### BUSCA GME ################################################
    public No buscaGME(No raiz, int op) {
        queue = new PriorityQueue<>(ComparatorH.getComparatorH());
        No aux = raiz;

        if (op == 1)
            raiz.setH(States.getHeutistic(raiz.estado, 1)); // H1 - peças fora do lugar
        else
            raiz.setH(States.getHeutistic(raiz.estado, 2)); // H2 - Algoritmo Manhattan distances

        int distance = raiz.getH();

        if (distance == 0)
            return raiz;

        while (distance != 0) {
            States.genStates(aux, queue, op);
            aux = queue.remove();
            distance = aux.getH();
        }
        return aux;
    }

    //################### BUSCA A* #################################################
    public No aStar(No raiz, int op) {
        queue = new PriorityQueue<>(ComparatorH.getComparatorGH());
        No aux = raiz;

        if (op == 1)
            raiz.setH(States.getHeutistic(raiz.estado, 1)); // H1 - peças fora do lugar
        else
            raiz.setH(States.getHeutistic(raiz.estado, 2)); // H2 - Algoritmo Manhattan distances

        int distance = raiz.getH();

        if (distance == 0)
            return raiz;

        while (distance != 0) {
            States.genStates(aux, queue, op);
            aux = queue.poll();
            distance = aux.getH();
        }
        return aux;
    }
}

class ComparatorH {
    //################### COMPARATOR H #############################################
    public static Comparator<No> getComparatorH() {
        return (o1, o2) -> {
            if (o1.getH() > o1.getH())
                return 1;
            else if (o1.getH() < o1.getH())
                return -1;
            return 0;
        };
    }

    //################### COMPARATOR GH ############################################
    public static Comparator<No> getComparatorGH() {
        return (o1, o2) -> {
            if (o1.getGH() > o2.getGH())
                return 1;
            else if (o1.getGH() < o2.getGH())
                return -1;
            return 0;
        };
    }
}