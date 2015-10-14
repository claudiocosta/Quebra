package Puzzle;

/**
 * Cristiano Vicente
 * Claudio Roberto Costa RA 527033
 * Rafael Anselmo RA
 * Melisa Cordeiro
 */
public class No {
    int estado[][] = new int[3][3];
    String acao;
    No pai;
    int custocaminho;
    int profundidade;

    public No() {
    }

    public No(int[][] estado, String acao, No pai, int custocaminho, int profundidade) {
        int aux[][] = new int[3][3];
        for (int i = 0; i < 3; i++) {
            aux[i] = estado[i].clone();
        }
        this.estado = aux;
        this.acao = acao;
        this.pai = pai;
        this.custocaminho = custocaminho;
        this.profundidade = profundidade;
    }

    public void printEstado() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                System.out.printf("%d ", this.estado[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
