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
        
        for(int i = 0; i < qtd; i++){
            System.out.println("Cromossomo " + (i+1));
            Cromossomo cromossomo = new Cromossomo(nLinha);
            cromossomo.geraIndividuo(nLinha, listaColuna, listaLinha, listaPeso);
            System.out.println("lista => " + cromossomo.listaElementos);
            cromossomo.eliminaRedundancia(listaColuna);
            /*for(int j = 0; j <= nLinha; j++){
                System.out.println(j + " --> " + cromossomo.qtdLinhaCoberto[j]);
            }*/
            cr[i] = cromossomo;
            
        }
        /*for(int ia = 0; ia<qtd-1; ia++){
            //System.out.print(ia + " --> ");
            for(int ib = ia+1; ib < qtd; ib++){
                //System.out.print(ib + "--");
                if(cr[ia].listaElementos.equals(cr[ib].listaElementos)){
                    System.out.println(ia + " e " + ib + "--> igual!!");
                }
            }
            //System.out.println();
        }*/
        return cr;
        //return pop;
    }
}
