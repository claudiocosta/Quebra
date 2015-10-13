package Quebra;

import java.util.LinkedList;
import java.util.List;

/**
 * Cristiano Vicente
 * Claudio Roberto Costa RA 527033
 * Rafael Anselmo RA
 * Melisa Cordeiro
 */

public class Puzzle {
    public static void main(String[] args) {
        List<No> arvore = new LinkedList<>();
        Busca find = new Busca();

        //int iniciar[][] = new int[][]{{4, 5, 6}, {1, 0, 3}, {7, 8, 2}};
        //int iniciar[][] = new int[][]{{0, 2, 3}, {7, 4, 1}, {8, 6, 5}};
        int iniciar[][] = new int[][]{{1, 2, 3}, {4, 5, 6}, {0, 7, 8}};
        //int iniciar[][] = new int[][]{{7, 2, 3}, {4, 6, 5}, {1, 8, 0}};

        No raiz = new No(iniciar, "raiz", null, 0, 0);
        No aux;

        // busca em profundidade ou largura
        aux = find.buscaProfLimit(raiz);
        // empilha os nos para imprimir ordenado ascendente
        while (aux.pai != null) {
            arvore.add(aux);
            aux = aux.pai;
        }
        arvore.add(aux); // <- raiz

        // imprime o caminho da solução
        System.out.println("Solução");
        while (arvore.size() != 0) {
            aux = arvore.remove(arvore.size() - 1);
            System.out.println(aux.custocaminho + " " + aux.acao);
        }
    }
}
