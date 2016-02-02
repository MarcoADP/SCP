package scp;

import java.util.ArrayList;

public class Populacao {
    public Cromossomo[] listaPopulacao ;

    public Populacao(int nLinha, int qtd, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha) {
        //this.listaPopulacao = new Cromossomo[qtd];
        this.listaPopulacao = geraPopulacao(qtd, listaColuna, listaLinha, nLinha);
    }
    
    public static Cromossomo[] geraPopulacao(int qtd, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, int nLinha){
        //Populacao pop = new Populacao(qtd);
        //listaLinha -> ALFHAi
        //listaColuna -> BHETAj
        
        
        Cromossomo[] cr = new Cromossomo[qtd];
        
        ArrayList<Integer> solucaoColunas = new ArrayList<>();
        ArrayList<Integer> descobertasLinhas = new ArrayList<>();
        for(int i = 1; i <= nLinha; i++){
            descobertasLinhas.add(i);
        }
        return cr;
        //return pop;
    }
}
