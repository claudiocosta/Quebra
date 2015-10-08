
package quebra;
import java.util.List;
import java.util.LinkedList;
public class Busca 
{
    List<No> pilha = new LinkedList<No>();
    List<No> fila = new LinkedList<No>();    
    int limite = 10;
    
    public boolean testeObjetivo(No base)
    {
        int objetivo[][];
        objetivo = new int [][]{{1,2,3},{4,5,6},{7,8,0}};
        
        for(int i=0 ; i<3; i++ )
        {
            for(int j=0; j<3; j++)
            {
                if(base.estado[i][j] != objetivo[i][j])
                {
                    return false;
                }
            }
        }
    return true;    
    }
    public int acao(No aux){
        int cont = 0;
	for (int i =0 ; i < 3; i++){
            for (int j =0 ; j < 3; j++){
                if (aux.estado[i][j] == 0){
                    break;
		}
                else{
                    cont++;
                    }
		}
            }
    return cont;
    }
    
    public void sucessor(No aux)
    {
        int mov;
        mov = acao(aux);
        switch (mov)
        {
            case 0:
                pilha.add(movUP(aux, 0, 0));
                pilha.add(movLeft(aux, 0, 0));
            case 1:
                pilha.add(movRight(aux, 0, 1));
                pilha.add(movUP(aux, 0, 1));
                pilha.add(movLeft(aux, 0, 1));
            case 2:
                pilha.add(movRight(aux, 0, 2));
                pilha.add(movUP(aux, 0, 2));
            case 3:
                pilha.add(movDown(aux, 1, 0));
                pilha.add(movUP(aux, 1, 0));
                pilha.add(movLeft(aux, 1, 0));
            case 4:
                pilha.add(movRight(aux, 1, 1));
                pilha.add(movDown(aux, 1, 1));
                pilha.add(movUP(aux, 1, 1));
                pilha.add(movLeft(aux, 1, 1));
            case 5:
                pilha.add(movRight(aux, 1, 2));
                pilha.add(movDown(aux, 1, 2));
                pilha.add(movUP(aux, 1, 2));
            case 6:
                pilha.add(movDown(aux, 2, 0));
                pilha.add(movLeft(aux, 2, 0));
            case 7:
                pilha.add(movRight(aux, 2, 1));
                pilha.add(movDown(aux, 2, 1));
                pilha.add(movLeft(aux, 2, 1));
            case 8:
                pilha.add(movRight(aux, 2, 2));
                pilha.add(movDown(aux, 2, 2));
        }
    }
    
    public No movUP(No pai, int i, int j)
    {
        No novo = new No();
        novo.estado[i][j] = pai.estado[i+1][j];  
        novo.estado[i+1][j] = 0;  
        novo.pai = pai;
        novo.custocaminho +=1;
        novo.profundidade +=1;
        novo.acao = "up";
        return novo;
    }
    
    public No movDown(No pai, int i, int j)
    {
        No novo = new No();
        novo.estado[i][j] = pai.estado[i-1][j];  
        novo.estado[i-1][j] = 0;  
        novo.pai = pai;
        novo.custocaminho +=1;
        novo.profundidade +=1;
        novo.acao = "down";
        return novo;
    }
    
    public No movLeft(No pai, int i, int j)
    {
        No novo = new No();
        novo.estado[i][j] = pai.estado[i][j+1];  
        novo.estado[i][j+1] = 0;  
        novo.pai = pai;
        novo.custocaminho +=1;
        novo.profundidade +=1;
        novo.acao = "left";
        return novo;
    }
    
    public No movRight(No pai, int i, int j)
    {
        No novo = new No();
        novo.estado[i][j] = pai.estado[i][j-1];  
        novo.estado[i][j-1] = 0;  
        novo.pai = pai;
        novo.custocaminho +=1;
        novo.profundidade +=1;
        novo.acao = "right";
        return novo;
    }
    
    public No buscaProfLimitadade(No raiz)
    {
        pilha.add(raiz);
        while (pilha.size() != 0)
        {
            No aux = pilha.remove(pilha.size()-1);
            if (testeObjetivo(aux))
                    return aux;
            else
                if (aux.profundidade < limite)
                    sucessor(aux);
        }
        return raiz;
    }
/*    
    public buscalargura(raiz)
    {
        fila.inserir(raiz);
        while (!= fila.vazia())
        {
            No aux = fila.remove();
            if testeObjetivo(aux) = true
                    return aux;
            else 
                sucessor(aux, fila);
        }
    }
 */   
}
