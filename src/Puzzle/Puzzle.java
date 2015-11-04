package Puzzle;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Cristiano Vicente RA 443913
 * Claudio Roberto Costa RA 527033
 * Rafael Anselmo RA 525650
 * Melisa Cordeiro RA 532533
 */

public class Puzzle {
    public static void main(String[] args) {
        List<No> arvore = new LinkedList<>();
        Busca find = new Busca();
        Scanner scan = new Scanner(System.in);
        int opcao;

        // Teste com custo da solução //#
        //int iniciar[] = new int[]{1, 2, 3, 4, 5, 6, 0, 7, 8}; //2
        //int iniciar[] = new int[]{1, 5, 2, 4, 0, 3, 7, 8, 6}; //4
        //int iniciar[] = new int[]{0, 2, 3, 1, 7, 5, 8, 4, 6}; //8
        int iniciar[] = new int[]{1, 2, 3, 7, 0, 8, 6, 4, 5}; //10
        //int iniciar[] = new int[]{2, 6, 3, 1, 7, 8, 4, 5, 0}; //12
        //int iniciar[] = new int[]{1, 3, 0, 2, 6, 5, 4, 7, 8}; //12
        //int iniciar[] = new int[]{0, 3, 6, 2, 1, 7, 4, 8, 5}; //14
        //int iniciar[] = new int[]{0, 2, 3, 7, 4, 1, 8, 6, 5}; //14
        //int iniciar[] = new int[]{2, 8, 3, 5, 0, 6, 1, 4, 7}; //14
        //int iniciar[] = new int[]{7, 4, 1, 8, 3, 2, 0, 5, 6}; //14
        //int iniciar[] = new int[]{3, 5, 0, 2, 1, 7, 8, 4, 6}; //16
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
        System.out.println("4- Busca GME");
        System.out.println("5- Busca A*");

        opcao = 1;//scan.nextInt();
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
                //---
                break;
            case 5:
                System.out.println("1- H1 - nome");
                System.out.println("2- H2 - nome");
                opcao = 2;//scan.nextInt();

                aux = find.aStar(raiz, opcao);
                break;
        }

        System.out.println("Inicial");
        raiz.printEstado();
        System.out.println("Final");
        aux.printEstado();
        // empilha os nos para imprimir ordenado ascendente
        while (aux.pai != null) {
            arvore.add(aux);
            aux = aux.pai;
        }
        arvore.add(aux); // <- raiz

        // imprime o caminho da solução
        long endTime = System.currentTimeMillis();
        System.out.printf("Solução #time: %d ms\n", endTime - startTime);

        while (arvore.size() != 0) {
            aux = arvore.remove(arvore.size() - 1);
            System.out.println(aux.g + " " + aux.acao);
        }
    }
}