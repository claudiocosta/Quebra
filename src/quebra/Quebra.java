package quebra;
import java.util.LinkedList;
/**
 * @authores 
 * Cristiano Vicente
 * Claudio Roberto Costa RA 527033    
 * Rafael Anselmo RA
 * Melisa Cordeiro  
 */

public class Quebra 
{
    public static void main(String[] args) 
    {
    LinkedList arvore = new LinkedList<No>();
    Busca find = new Busca();
    No novo = new No();
    No raiz = new No();
    int iniciar[][];
    iniciar = new int[][]{{4,5,6},{1,3,0},{7,8,2}};
    raiz.estado = iniciar;
    raiz.acao = "";
    raiz.pai = null;
    raiz.custocaminho = 0;
    raiz.profundidade = 0;
    
    arvore.add(raiz);
    System.out.println("LinkedList Content after addition: " +arvore);
    
    
    
    novo = find.buscaProfLimitadade(raiz);
        while(novo.pai != null)
        {   
            arvore.add(novo);
            novo = novo.pai;
        }
        arvore.add(novo);
    }
    
}
