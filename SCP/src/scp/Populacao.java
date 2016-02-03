package scp;

import java.util.ArrayList;
import java.util.Random;

public class Populacao {

    private static int fazInterseccao(ArrayList<Integer> lista1, ArrayList<Integer> lista2) {
        ArrayList<Integer> inter = new ArrayList<>();
        int tamanho = 0;
        
        for(Integer i : lista1){
            if(lista2.contains(i)){
                inter.add(i);
            }
        }
        
        tamanho = inter.size();
        
        return tamanho;
    }

    private static int buscaBinaria(ArrayList<Integer> lista, int ln, int tam) {
        int meio = tam/2;
        boolean achou = false;
        int num = lista.get(meio); 
        if(num == ln){
            return meio;
        }
        else if (num < ln){
            buscaBinaria(lista, ln, tam);
        } else {
            buscaBinaria(lista, ln, meio);
        }
        return -1;
    }
    
    public Cromossomo[] listaPopulacao ;

    public Populacao(int nLinha, int qtd, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, ArrayList<Double> listaPeso) {
        //this.listaPopulacao = new Cromossomo[qtd];
        this.listaPopulacao = geraPopulacao(qtd, listaColuna, listaLinha, nLinha, listaPeso);
    }
    
    public static Cromossomo[] geraPopulacao(int qtd, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, int nLinha, ArrayList<Double> listaPeso){
        //Populacao pop = new Populacao(qtd);
        //listaLinha -> ALFHAi
        //listaColuna -> BHETAj
        
        Random ran = new Random();
        Cromossomo[] cr = new Cromossomo[qtd];
        
        ArrayList<Integer> solucaoColunas = new ArrayList<>();      //S -> colunas na solucao
        ArrayList<Integer> descobertasLinhas = new ArrayList<>();   //U -> linhas nao cobertas
        int cobremLinhas[] = new int[nLinha];    //wi -> Numero de colunas q cobrem a linha i
        for(int i = 1; i <= nLinha; i++){
            descobertasLinhas.add(i);   // i na posicao i-1        // 
        }
        //while(descobertasLinhas.size() != 0){
            int escolhidaPosicao = ran.nextInt(descobertasLinhas.size()); //escolhe posicao aleatoria
            System.out.println("Posicao ->" + escolhidaPosicao);
            int escolhidaLinha = descobertasLinhas.get(escolhidaPosicao); //escolhe linha na posicao
            System.out.println("linha -> " + escolhidaLinha);
            Double menor = Double.MAX_VALUE;
            int escolhidaColuna = -1;
            System.out.println(listaLinha[escolhidaLinha]);
            for(int i = 0; i < listaLinha[escolhidaLinha].size(); i++){
                int atualColuna = listaLinha[escolhidaLinha].get(i);  //ele pega a coluna na posicao i
                System.out.print(atualColuna + " - ");
                int interseccao = fazInterseccao(descobertasLinhas, listaColuna.get(atualColuna-1)); //calcula o tamanho da inter U com com linhas em atualColuna
                Double peso = listaPeso.get(atualColuna-1);
                System.out.println(peso);
                Double resultado = peso/interseccao;
                System.out.println("Resultado -> " + resultado);
                if(resultado < menor){
                    menor = resultado;
                    escolhidaColuna = atualColuna;
                }
            }
            System.out.println(escolhidaColuna + " == " + menor);
            solucaoColunas.add(escolhidaColuna);
            for(int ln : listaColuna.get(escolhidaColuna-1)){
                System.out.print(ln + " - " + descobertasLinhas.get(ln-1) + "//");
                int pos = buscaBinaria(descobertasLinhas, ln, descobertasLinhas.size());
                //descobertasLinhas.remove(ln-1);
                
            }
            
        //}
        return cr;
        //return pop;
    }
}
