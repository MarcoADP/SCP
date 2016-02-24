package scp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Populacao {
    public Cromossomo[] listaPopulacao ;
    int qtdPop;
    int indMenor;
    int indMaior;
    
    public Populacao(int nLinha, int qtd, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, ArrayList<Double> listaPeso) {
        qtdPop = qtd;
        this.listaPopulacao = this.geraPopulacao(listaColuna, listaLinha, nLinha, listaPeso);
    }
    
    public Cromossomo[] geraPopulacao(ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, int nLinha, ArrayList<Double> listaPeso){
        //Populacao pop = new Populacao(qtd);

        Cromossomo[] cr = new Cromossomo[this.qtdPop];
        Double custoMaior = Double.MIN_VALUE;
        Double custoMenor = Double.MAX_VALUE;
        for(int i = 0; i < this.qtdPop; i++){
            //System.out.println("Cromossomo " + (i+1));
            Cromossomo cromossomo = new Cromossomo(nLinha, listaColuna, listaLinha, listaPeso);
            //cromossomo.geraIndividuo(nLinha, listaColuna, listaLinha, listaPeso);
            //cromossomo.eliminaRedundancia(listaColuna);
            //System.out.println("lista => " + "PESO => " + cromossomo.custoTotal + " -- " + cromossomo.listaElementos);
            cromossomo.eliminaRedundancia(listaColuna, listaPeso);
            //System.out.println("lista => " + "PESO => " + cromossomo.custoTotal + " -- " + cromossomo.listaElementos);
            
            if(cromossomo.custoTotal < custoMenor){
                //this.crMenorCusto = cromossomo;
                //System.out.println(cromossomo.custoTotal);
                this.indMenor = i;
                custoMenor = cromossomo.custoTotal;
            }
            if(cromossomo.custoTotal > custoMaior){
                //this.crMaiorCusto = cromossomo;
                //System.out.println(cromossomo.custoTotal);
                this.indMaior = i;
                custoMaior = cromossomo.custoTotal;
            }
            
            /*for(int j = 0; j <= nLinha; j++){
                System.out.println(j + " --> " + cromossomo.qtdLinhaCoberto[j]);
            }*/
            //long auxRound = Math.round(cromossomo.custoTotal);
            //DecimalFormat f = new DecimalFormat("##,00");
            //String auxRound = f.format(cromossomo.custoTotal);
            //cromossomo.custoTotal = Double.parseDouble(auxRound); 
            //System.out.println("lista => " + "PESO => " + cromossomo.custoTotal + " -- " + cromossomo.listaElementos + "\n\n");
            cr[i] = cromossomo;
            
        }
        /*int ig = 0;
        for(int ia = 0; ia<qtd-1; ia++){
            //System.out.print(ia + " --> ");
            for(int ib = ia+1; ib < qtd; ib++){
                //System.out.print(ib + "--");
                if(cr[ia].listaElementos.equals(cr[ib].listaElementos)){
                    //System.out.println(ia + " e " + ib + "--> igual!!");
                    ig++;
                }
            }
            //System.out.println();
        }
        System.out.println(ig);*/
        return cr;
        //return pop;
    }

    public Cromossomo classificaPopulacao(){
        int qtd_escolhido = 3;
        //Cromossomo cr[] = new Cromossomo[qtd_escolhido];
        Cromossomo cr[] = new Cromossomo[this.qtdPop];
        Random random = new Random();
        Cromossomo crMin = new Cromossomo();
        //random.setSeed(System.nanoTime());
        for(int i=0; i <qtd_escolhido; i++){
            int posicao = random.nextInt(qtd_escolhido);
            cr[i] = this.listaPopulacao[posicao];
            Double min = Double.MAX_VALUE;
            
            if(cr[i].custoTotal < min){
                min = cr[i].custoTotal;
                crMin = cr[i];
            }
            //System.out.println(cr[i].custoTotal + " -- " + cr[i].listaElementos);
        }
        
        return crMin;
    }

    public Cromossomo crossover(int nLinha, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Double> listaPeso){
        Cromossomo cr1 = this.classificaPopulacao();
        Cromossomo cr2 = this.classificaPopulacao();
        
        while(cr1 == cr2){
            cr2 = this.classificaPopulacao();
        }
        
        ArrayList<Integer> uniao = Util.uniao(cr1.listaElementos, cr2.listaElementos);
        Cromossomo filho = new Cromossomo();
        filho.listaElementos = uniao;
        filho.custoTotal = filho.calculaCusto(nLinha, listaColuna, listaPeso);
        //System.out.println("\n" + filho.listaElementos);
        filho.eliminaRedundancia(listaColuna, listaPeso);
        //System.out.println("\n" + filho.listaElementos);
        
        return filho;
    }

    public void geraIndices(){
        Double maiorCusto = Double.MIN_VALUE;
        Double menorCusto = Double.MAX_VALUE;
        for(int i = 0; i < qtdPop; i++){
            //Cromossomo cr = this.listaPopulacao[i];
            Double custoAtual = this.listaPopulacao[i].custoTotal;
            if(custoAtual < menorCusto){
                menorCusto = custoAtual;
                this.indMenor = i;
            }
            if(custoAtual > maiorCusto){
                maiorCusto = custoAtual;
                this.indMaior = i;
            }
            
        }
    }
}
