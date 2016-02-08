package scp;

import java.util.ArrayList;
import java.util.Random;

public class Cromossomo {
    ArrayList<Integer> listaElementos = new ArrayList<>();
    int qtdLinhaCoberto[];
    Double custoTotal;

    public Cromossomo(int nLinha) {
        this.listaElementos = new ArrayList<>();
        this.qtdLinhaCoberto = new int[nLinha];
        custoTotal = 0.0;
    }

    public Cromossomo(ArrayList<Integer> lista, ArrayList<Double> peso){
        this.listaElementos = lista;
        this.custoTotal = 0.0;
        for(int a : lista){
            this.custoTotal += peso.get(a-1);

        }
    }

    public Cromossomo geraIndividuo(int nLinha, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, ArrayList<Double> listaPeso){
        //listaLinha -> ALFHAi
        //listaColuna -> BHETAj
        
        Cromossomo cr = new Cromossomo(nLinha);

        Random ran = new Random();

        ArrayList<Integer> solucaoColunas = new ArrayList<>();      //S -> colunas na solucao
        ArrayList<Integer> descobertasLinhas = new ArrayList<>();   //U -> linhas nao cobertas
        int cobremLinhas[] = new int[nLinha+1];                       //wi -> Numero de colunas q cobrem a linha i
        for(int i = 0; i <= nLinha; i++){
            descobertasLinhas.add(i);                               // i na posicao i        // 
        }


        while(descobertasLinhas.size() != 1){
            System.out.println((descobertasLinhas));
            int escolhidaPosicao = ran.nextInt(descobertasLinhas.size()-1) + 1; //escolhe posicao aleatoria
            //System.out.print("\nDe 1 a " + descobertasLinhas.size() + " == Posicao -> " + escolhidaPosicao + " ## ");
            int escolhidaLinha = descobertasLinhas.get(escolhidaPosicao); //escolhe linha na posicao
            //System.out.println("linha -> " + escolhidaLinha);
            Double menor = Double.MAX_VALUE;
            int escolhidaColuna = -1;
            //System.out.println(listaLinha[escolhidaLinha]);
            for(int i = 0; i < listaLinha[escolhidaLinha].size(); i++){
                int atualColuna = listaLinha[escolhidaLinha].get(i);  //ele pega a coluna na posicao i
                //System.out.println(atualColuna + " --> " + listaColuna.get(atualColuna));
                int interseccao = Util.fazInterseccao(descobertasLinhas, listaColuna.get(atualColuna)); //calcula o tamanho da inter U com com linhas em atualColuna
                Double peso = listaPeso.get(atualColuna);
                //System.out.println(peso);
                Double resultado = peso/interseccao;
                //System.out.println("Interseccao -> " + interseccao + " Peso -> " + peso + " Resultado -> " + resultado);
                if(resultado < menor){
                    menor = resultado;
                    escolhidaColuna = atualColuna;
                }
            }
            //System.out.println("\nElementos da Coluna: " + escolhidaColuna + " => "+ listaColuna.get(escolhidaColuna));
            //System.out.println(escolhidaColuna + " == " + menor);
            solucaoColunas.add(escolhidaColuna);                        // S <- S + escolhidaColuna;
            for(int ln : listaColuna.get(escolhidaColuna)){
                //System.out.print(ln + " - " + descobertasLinhas.get(ln) + " // ");
                //System.out.print("\n" + ln + " - ");
                cobremLinhas[ln] += 1;

                int tam = descobertasLinhas.size()-1;
                int pos = Util.buscaBinaria(descobertasLinhas, ln, 0, tam);
                //System.out.print("\n" + ln + " -- Posicao ==> " + pos);
                if(pos != -1){
                    //System.out.print("  OK");
                    descobertasLinhas.remove(pos);                  // U <- U - linha cobertas pela escolhidaColuna
                }                          

            }
        }
        cr.listaElementos = solucaoColunas;
        cr.qtdLinhaCoberto = cobremLinhas;
        return cr;
    }
        
}
