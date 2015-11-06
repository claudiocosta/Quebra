package Puzzle;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Cristiano Vicente RA 443913
 * Claudio Roberto Costa RA 527033
 * Rafael Anselmo RA 525650
 * Melisa Cordeiro RA 532533
 */

public class Puzzle {
    public static void main(String[] args) {
        LinkedList<No> arvore = new LinkedList<>();
        Busca find = new Busca();
        Scanner input = new Scanner(System.in);
        int opcao, opcaoH = 0;

        // Teste com custo da solução //#
        //int iniciar[] = new int[]{1, 2, 3, 4, 5, 6, 0, 7, 8}; //2
        //int iniciar[] = new int[]{1, 5, 2, 4, 0, 3, 7, 8, 6}; //4
        //int iniciar[] = new int[]{0, 2, 3, 1, 7, 5, 8, 4, 6}; //8
        //int iniciar[] = new int[]{1, 2, 3, 7, 0, 8, 6, 4, 5}; //10
        //int iniciar[] = new int[]{2, 6, 3, 1, 7, 8, 4, 5, 0}; //12
        //int iniciar[] = new int[]{1, 3, 0, 2, 6, 5, 4, 7, 8}; //12
        //int iniciar[] = new int[]{0, 3, 6, 2, 1, 7, 4, 8, 5}; //14
        //int iniciar[] = new int[]{0, 2, 3, 7, 4, 1, 8, 6, 5}; //14
        //int iniciar[] = new int[]{2, 8, 3, 5, 0, 6, 1, 4, 7}; //14
        //int iniciar[] = new int[]{7, 4, 1, 8, 3, 2, 0, 5, 6}; //14
        int iniciar[] = new int[]{3, 5, 0, 2, 1, 7, 8, 4, 6}; //16
        //int iniciar[] = new int[]{1, 5, 4, 8, 6, 2, 0, 7, 3}; //18
        //int iniciar[] = new int[]{4, 5, 3, 2, 0, 1, 7, 6, 8}; //20
        //int iniciar[] = new int[]{3, 4, 5, 8, 0, 6, 7, 1, 2}; //22
        //int iniciar[] = new int[]{0, 8, 6, 1, 7, 5, 2, 4, 3}; //24
        //int iniciar[] = new int[]{0, 8, 3, 7, 5, 2, 4, 6, 1}; //26
        //int iniciar[] = new int[]{6, 8, 7, 1, 4, 5, 0, 2, 3}; //28
        //int iniciar[] = new int[]{5, 2, 1, 3, 0, 4, 6, 8, 7}; //30


        No raiz = new No(iniciar, "raiz", null, 0, 9);
        No aux = new No();

        System.out.println("+++++++++++++++++++++++++++");
        System.out.println("1- Busca em Largura   ");
        System.out.println("2- Busca em Profundidade Limitada");
        System.out.println("3- Busca em Profundidade");
        System.out.println("4- Busca em Aprofundamento Iterativo");
        System.out.println("5- Busca de Custo Uniforme");
        System.out.println("6- Busca GME");
        System.out.println("7- Busca A*");

        opcao = input.nextInt();
        if (opcao > 5) {
            while (opcaoH < 1 || opcaoH > 2) {
                System.out.println("1- H1 - misplacedTiles");
                System.out.println("2- H2 - manhattanDistance");
                opcaoH = input.nextInt();
            }
        }

        long startTime = System.currentTimeMillis();

        switch (opcao) {
            case 1:
                aux = find.buscaLargura(raiz);
                break;
            case 2:
                aux = find.buscaProfLimit(raiz);
                break;
            case 3:
                aux = find.buscaProf(raiz);
                break;
            case 4:
                aux = find.buscaIDA(raiz);
                break;
            case 5:
                aux = find.buscaCustoUniforme(raiz);
                break;
            case 6:
                aux = find.buscaGME(raiz, opcaoH);
                break;
            case 7:
                aux = find.aStar(raiz, opcaoH);
                break;
        }

        System.out.println("\nInicial");
        raiz.printEstado();
        System.out.println("\nFinal");
        aux.printEstado();
        // empilha os nos para imprimir ordenado ascendente
        while (aux.pai != null) {
            arvore.add(aux);
            aux = aux.pai;
        }
        arvore.add(aux); // <- raiz

        // imprime o caminho da solução
        long endTime = System.currentTimeMillis();
        System.out.printf("\nSolução #time: %d ms\n\n", endTime - startTime);

        while (arvore.size() != 0) {
            aux = arvore.remove(arvore.size() - 1);
            System.out.println(aux.g + " " + aux.acao);
        }
    }
}