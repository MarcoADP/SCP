package scp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Populacao {
    public Cromossomo[] listaPopulacao ;
    int qtdPop;
    
    public Populacao(int nLinha, int qtd, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, ArrayList<Double> listaPeso) {
        qtdPop = qtd;
        this.listaPopulacao = this.geraPopulacao(listaColuna, listaLinha, nLinha, listaPeso);
    }
    
    public Cromossomo[] geraPopulacao(ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, int nLinha, ArrayList<Double> listaPeso){
        //Populacao pop = new Populacao(qtd);

        Cromossomo[] cr = new Cromossomo[this.qtdPop];
        
        
        
        for(int i = 0; i < this.qtdPop; i++){
            System.out.println("Cromossomo " + (i+1));
            Cromossomo cromossomo = new Cromossomo(nLinha);
            cromossomo.geraIndividuo(nLinha, listaColuna, listaLinha, listaPeso);
            cromossomo.eliminaRedundancia(listaColuna, listaPeso);
            System.out.println("lista => " + "PESO => " + cromossomo.custoTotal + " -- " + cromossomo.listaElementos);
            
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

    public void classificaPopulacao(){
        int qtdTorneio = 5;
        Cromossomo solucoesEscolhida[] = new Cromossomo[qtdTorneio];
        for(int i = 0; i < qtdTorneio; i++){
            Random random = new Random();
            int num = random.nextInt(this.qtdPop);
            solucoesEscolhida[i] = this.listaPopulacao[num];
            //System.out.println(num+1 + " -- " + solucoesEscolhida[i]);
        }
        //int qtdTorneio = (int) Math.ceil(qtdPop/10);
    }
}
