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
//################### H1 - PEÇAS FORA DO LUGAR################################## 
    public int misplacedTiles(int[] array) {
        int heuristic = 0;
        for (int i = 0; i < array.length; i++) {
            if ((i != (array[i] - 1)) && (array[i] != 0))
                heuristic++;
        }
        return heuristic;
    }
//################### H2 - ALGORITMO MANHATTAN DISTANCE ######################## 
    public int manhattanDistance(int[] array) {
        int heuristic = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0)
                heuristic += Math.abs((i / 3) - ((array[i] - 1) / 3)) + Math.abs((i % 3) - ((array[i] - 1) % 3));
        }
        return heuristic;
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
<<<<<<< HEAD

    // busca IDA
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

    // busca em largura
    public No buscaLargura(No raiz) {
=======
    
//################### BUSCA DE APROFUNDAMENTO ITERATIVO ######################## 
    public No buscaIDA(No raiz) {
>>>>>>> e5bb6acc8b67916f74455c4100e049c45f67d310
        list.add(raiz);
        while (!list.isEmpty()) {
            No aux = list.remove(list.size() - 1);
            if (testeObjetivo(aux.estado))
                return aux;
            else if (aux.g < limite)
                States.genStates(aux, list);
            else if ((aux.g == limite) && list.isEmpty())
                States.genStates(aux, list);
                limite++;
        }
        return raiz;
    }
//################### BUSCA DE CUSTO UNIFORME ##################################
    public No buscaCustoUniforme(No raiz) {
        list.add(raiz);
        while (!list.isEmpty()) {
            No aux = list.remove(0);  //remover o nó com menor caminho
            if (testeObjetivo(aux.estado))
                return aux;
            else
                States.genStates(aux, list);
        }
        return raiz;
    }
//################### BUSCA GME ################################################
    public No buscaGME(No raiz, int op){
        if (op == 1)
            raiz.setH(misplacedTiles(raiz.estado));
        else
            raiz.setH(manhattanDistance(raiz.estado));
        
        //ainda tem que implementar
        
        
        return raiz;
    }
//################### BUSCA A* #################################################     
    public No aStar(No raiz, int op) {
        queue = new PriorityQueue<>(ComparatorH.getComparatorGH());
        No aux = raiz;

        if (op == 1)
            raiz.setH(misplacedTiles(raiz.estado));
        else
            raiz.setH(manhattanDistance(raiz.estado));

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
}

//################### COMPARATOR H ############################################# 
class ComparatorH {
    public static Comparator<No> getComparatorH() {
        return new Comparator<No>() {
            @Override
            public int compare(No o1, No o2) {
                if (o1.getH() > o1.getH())
                    return 1;
                else if (o1.getH() < o1.getH())
                    return -1;
                return 0;
            }
        };
    }
//################### COMPARATOR GH ############################################
    public static Comparator<No> getComparatorGH() {
        return new Comparator<No>() {
            @Override
            public int compare(No o1, No o2) {
                if (o1.getGH() > o2.getGH())
                    return 1;
                else if (o1.getGH() < o2.getGH())
                    return -1;
                return 0;
            }
        };
    }
//################### COMPARATOR G #############################################    
    public static Comparator<No> getComparatorG() {
        return new Comparator<No>() {
            @Override
            public int compare(No o1, No o2) {
                if (o1.getG() < o2.getG())
                    return 1;
                else if (o1.getG() > o2.getG())
                    return -1;
                return 0;
            }
        };
    }
}