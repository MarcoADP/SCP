package scp;

import java.util.ArrayList;

public class Populacao {
    public Cromossomo[] listaPopulacao ;

    public Populacao(int nLinha, int qtd, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, ArrayList<Double> listaPeso) {
        this.listaPopulacao = geraPopulacao(qtd, listaColuna, listaLinha, nLinha, listaPeso);
    }
    
    public static Cromossomo[] geraPopulacao(int qtd, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, int nLinha, ArrayList<Double> listaPeso){
        //Populacao pop = new Populacao(qtd);
        
        
        
        Cromossomo[] cr = new Cromossomo[qtd];
        
        //for(int i = 0; i < qtd; i++){
            Cromossomo cromossomo = new Cromossomo(nLinha);
            cromossomo = cromossomo.geraIndividuo(nLinha, listaColuna, listaLinha, listaPeso);
            System.out.println("lista => " + cromossomo.listaElementos);
            for(int i = 0; i <= nLinha; i++){
                System.out.println(i + " --> " + cromossomo.qtdLinhaCoberto[i]);
            }
            //cr[i] = cromossomo;
            
       //}
        return cr;
        //return pop;
    }
}
