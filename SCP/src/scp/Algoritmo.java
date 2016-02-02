package scp;

import java.util.ArrayList;

public class Algoritmo {
    public static void algoritmoGenetico(ArrayList<Double> listaPeso, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, int qtdPop, int nLinha){
        //Geração de População
        Populacao populacao = new Populacao(nLinha, qtdPop, listaColuna, listaLinha);
        
    }
}
