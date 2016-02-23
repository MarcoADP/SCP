package scp;

import java.util.ArrayList;
import java.util.Random;

public class Algoritmo {
    
    public static final double TAXA_MIN_MUTACAO = 0.5;
    public static final int NUM_GERACOES = 1000;
    
    public static Cromossomo algoritmoGenetico(ArrayList<Double> listaPeso, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, int qtdPop, int nLinha){
        //Geração de População
        Populacao populacao = new Populacao(nLinha, qtdPop, listaColuna, listaLinha, listaPeso);
        //System.out.println("\n" + populacao.listaPopulacao[populacao.indMenor].custoTotal);
        //System.out.println("\n" + populacao.listaPopulacao[populacao.indMaior].custoTotal);
        /*int qtd_cruzamento = 2;
        Cromossomo cr[] = new Cromossomo[qtd_cruzamento];
        for(int i = 0; i<qtd_cruzamento; i++){
            cr[i] = populacao.classificaPopulacao();
            System.out.println(cr[i].custoTotal + " --- " + cr[i].listaElementos);
        }*/
        int i = 0;
        int foi = 1;
        while (i < NUM_GERACOES){
            Cromossomo filho = populacao.crossover(nLinha, listaColuna, listaPeso);
            Cromossomo menorCusto = populacao.listaPopulacao[populacao.indMenor];
            Cromossomo maiorCusto = populacao.listaPopulacao[populacao.indMaior];
            //System.out.println(menorCusto.custoTotal + " -- " +menorCusto.listaElementos);
            //System.out.println(maiorCusto.custoTotal + " -- " +maiorCusto.listaElementos);
            Random ranDouble = new Random();
            Double doubleEscolhido = ranDouble.nextDouble();
            //System.out.println(doubleEscolhido);
            if (doubleEscolhido < taxaMutacao(menorCusto.custoTotal, maiorCusto.custoTotal)){
                //System.out.println(foi++);
                mutacao(filho, listaColuna, listaPeso);
            }
            
            if(filho.custoTotal < maiorCusto.custoTotal){
                populacao.listaPopulacao[populacao.indMaior] = filho;
                populacao.geraIndices();
                i = 0;
            } else {
                i++;
            }
            //System.out.println(i);
        }
        return populacao.listaPopulacao[populacao.indMenor];
        
        
    }
    
    public static void mutacao(Cromossomo C, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Double> listaPeso){
        Random ran = new Random();
        double random_double = ran.nextDouble();
        int n = (int)(random_double * C.listaElementos.size());
        for (int i = 0; i < n; i++) {
            int random_col = ran.nextInt(listaColuna.size());
            //int random_col = Util.getRandomInt(listaColuna.size());
            //C.addColuna(random_col, listaCusto[random_col], listaColuna);
            C.addColuna(n, listaPeso);
        }
        C.eliminaRedundancia(listaColuna, listaPeso);
    }
    
    public static Double taxaMutacao(Double custoMaisApto, Double custoMenosApto){
        double taxa = TAXA_MIN_MUTACAO;
        taxa = taxa / (1 - Math.exp((-custoMenosApto - custoMaisApto) / custoMenosApto));
        return taxa;
    }
}
