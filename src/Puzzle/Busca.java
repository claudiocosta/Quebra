package Puzzle;

import java.util.List;
import java.util.LinkedList;

public class Busca {
    List<No> pilha_fila = new LinkedList<>();
    int limite = 15;

    // testa a solução
    public boolean testeObjetivo(No base) {
        int objetivo[][] = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (base.estado[i][j] != objetivo[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // localiza a posição do 0
    public int pos_0(No aux) {
        int cont = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (aux.estado[i][j] == 0) {
                    return cont;
                } else {
                    cont++;
                }
            }
        }
        return cont;
    }

    // grupo de ações possivéis para cada estado
    public void sucessor(No aux) {
        switch (pos_0(aux)) {
            case 0:
                pilha_fila.add(movUP(aux, 0, 0));
                pilha_fila.add(movLeft(aux, 0, 0));
                break;
            case 1:
                pilha_fila.add(movRight(aux, 0, 1));
                pilha_fila.add(movUP(aux, 0, 1));
                pilha_fila.add(movLeft(aux, 0, 1));
                break;
            case 2:
                pilha_fila.add(movRight(aux, 0, 2));
                pilha_fila.add(movUP(aux, 0, 2));
                break;
            case 3:
                pilha_fila.add(movDown(aux, 1, 0));
                pilha_fila.add(movUP(aux, 1, 0));
                pilha_fila.add(movLeft(aux, 1, 0));
                break;
            case 4:
                pilha_fila.add(movRight(aux, 1, 1));
                pilha_fila.add(movDown(aux, 1, 1));
                pilha_fila.add(movUP(aux, 1, 1));
                pilha_fila.add(movLeft(aux, 1, 1));
                break;
            case 5:
                pilha_fila.add(movRight(aux, 1, 2));
                pilha_fila.add(movDown(aux, 1, 2));
                pilha_fila.add(movUP(aux, 1, 2));
                break;
            case 6:
                pilha_fila.add(movDown(aux, 2, 0));
                pilha_fila.add(movLeft(aux, 2, 0));
                break;
            case 7:
                pilha_fila.add(movRight(aux, 2, 1));
                pilha_fila.add(movDown(aux, 2, 1));
                pilha_fila.add(movLeft(aux, 2, 1));
                break;
            case 8:
                pilha_fila.add(movRight(aux, 2, 2));
                pilha_fila.add(movDown(aux, 2, 2));
                break;
        }
    }

    // conjunto de metodos de movimentos
    public No movUP(No pai, int i, int j) {
        No novo = new No(pai.estado, "up", pai, pai.custocaminho + 1, pai.profundidade + 1);
        novo.estado[i][j] = pai.estado[i + 1][j];
        novo.estado[i + 1][j] = 0;
        return novo;
    }

    public No movDown(No pai, int i, int j) {
        No novo = new No(pai.estado, "down", pai, pai.custocaminho + 1, pai.profundidade + 1);
        novo.estado[i][j] = pai.estado[i - 1][j];
        novo.estado[i - 1][j] = 0;
        return novo;
    }

    public No movLeft(No pai, int i, int j) {
        No novo = new No(pai.estado, "left", pai, pai.custocaminho + 1, pai.profundidade + 1);
        novo.estado[i][j] = pai.estado[i][j + 1];
        novo.estado[i][j + 1] = 0;
        return novo;
    }

    public No movRight(No pai, int i, int j) {
        No novo = new No(pai.estado, "right", pai, pai.custocaminho + 1, pai.profundidade + 1);
        novo.estado[i][j] = pai.estado[i][j - 1];
        novo.estado[i][j - 1] = 0;
        return novo;
    }

    // busca em profundidade com limite
    public No buscaProfLimit(No raiz) {
        pilha_fila.add(raiz);
        while (pilha_fila.size() != 0) {
            No aux = pilha_fila.remove(pilha_fila.size() - 1);
            if (testeObjetivo(aux))
                return aux;
            else if (aux.profundidade < limite)
                sucessor(aux);
        }
        return raiz;
    }

    // busca em largura
    public No buscaLargura(No raiz) {
        pilha_fila.add(raiz);
        while (pilha_fila.size() != 0) {
            No aux = pilha_fila.remove(0);
            if (testeObjetivo(aux))
                return aux;
            else
                sucessor(aux);
        }
        return raiz;
    }
}
