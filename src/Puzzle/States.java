package Puzzle;

import java.util.AbstractCollection;

/**
 * Created by cristiano on 04/11/15.
 */
public class States {
    public static void genStates(No aux, AbstractCollection<No> list) {
        int index = pos_0(aux);
        int posX = index % 3;
        int mov = aux.pos_ant - index;
        if (index < 3)
            list.add(movUP(aux, index));
        else if (index < 6) {
            if (mov != 3)
                list.add(movUP(aux, index));
            if (mov != -3)
                list.add(movDown(aux, index));
        } else
            list.add(movDown(aux, index));

        if (posX == 0)
            list.add(movLeft(aux, index));
        else if (posX == 1) {
            if (mov != 1)
                list.add(movLeft(aux, index));
            if (mov != -1)
                list.add(movRight(aux, index));
        } else
            list.add(movRight(aux, index));
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
    public static No movUP(No pai, int i) {
        No novo = new No(pai.estado.clone(), "up", pai, pai.g + 1, i);
        novo.estado[i] = pai.estado[i + 3];
        novo.estado[i + 3] = 0;
        return novo;
    }

    public static No movDown(No pai, int i) {
        No novo = new No(pai.estado.clone(), "down", pai, pai.g + 1, i);
        novo.estado[i] = pai.estado[i - 3];
        novo.estado[i - 3] = 0;
        return novo;
    }

    public static No movLeft(No pai, int i) {
        No novo = new No(pai.estado.clone(), "left", pai, pai.g + 1, i);
        novo.estado[i] = pai.estado[i + 1];
        novo.estado[i + 1] = 0;
        return novo;
    }

    public static No movRight(No pai, int i) {
        No novo = new No(pai.estado.clone(), "right", pai, pai.g + 1, i);
        novo.estado[i] = pai.estado[i - 1];
        novo.estado[i - 1] = 0;
        return novo;
    }
}
