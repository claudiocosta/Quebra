package Puzzle;

/**
 * Cristiano Vicente RA 443913
 * Claudio Roberto Costa RA 527033
 * Rafael Anselmo RA 525650
 * Melisa Cordeiro RA 532533
 */
public class No implements Comparable<No> {
    int estado[] = new int[9];
    String acao;
    No pai;
    int g;
    int h;
    int pos_ant;

    public No() {
    }

    public No(int[] estado, String acao, No pai, int g, int pos_ant) {
        this.estado = estado;
        this.acao = acao;
        this.pai = pai;
        this.g = g;
        this.pos_ant = pos_ant;
    }

    public void printEstado() {
        for (int x = 0; x < 9; x++) {
            System.out.printf("%d ", this.estado[x]);
            if (x == 2 || x == 5)
                System.out.println();
        }
        System.out.println();
    }

    public int getH() {
        return getHeuristic(this.estado);
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getGH() {
        return this.g + this.h;
    }

    // Algoritmo Manhattan distances
    public int getHeuristic(int[] array) {
        int heuristic = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0)
                heuristic += Math.abs((i / 3) - ((array[i] - 1) / 3)) + Math.abs((i % 3) - ((array[i] - 1) % 3));
        }
        return heuristic;
    }

    @Override
    public int compareTo(No o) {
        if(this.h < o.h) {
            return 1;
        }
        return 0;
    }

}
