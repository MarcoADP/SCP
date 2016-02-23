package scp;

import java.util.ArrayList;
import java.util.Random;

public class Cromossomo {
    ArrayList<Integer> listaElementos = new ArrayList<>();
    int qtdLinhaCoberto[];
    Double custoTotal;

    public Cromossomo(int nLinha, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, ArrayList<Double> listaPeso) {
        //this.listaElementos = new ArrayList<>();
        this.qtdLinhaCoberto = new int[nLinha];
        this.custoTotal = 0.0;
        this.geraIndividuo(nLinha, listaColuna, listaLinha, listaPeso);
        //custoTotal = 0.0;
    }
    
    public Cromossomo(){
        this.listaElementos = new ArrayList<>();
        this.qtdLinhaCoberto = null;
        this.custoTotal = 0.0;
    }
    
    public Cromossomo(ArrayList<Integer> lista, ArrayList<Double> peso){
        this.listaElementos = lista;
        this.custoTotal = 0.0;
        for(int a : lista){
            this.custoTotal += peso.get(a-1);

        }
    }

    public void geraIndividuo(int nLinha, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Integer>[] listaLinha, ArrayList<Double> listaPeso){
        //listaLinha -> ALFHAi
        //listaColuna -> BHETAj

        Random ran = new Random();

        ArrayList<Integer> solucaoColunas = new ArrayList<>();      //S -> colunas na solucao
        ArrayList<Integer> descobertasLinhas = new ArrayList<>();   //U -> linhas nao cobertas
        int cobremLinhas[] = new int[nLinha+1];                       //wi -> Numero de colunas q cobrem a linha i
        for(int i = 0; i <= nLinha; i++){
            descobertasLinhas.add(i);                               // i na posicao i        // 
        }


        while(descobertasLinhas.size() != 1){
            //System.out.println((descobertasLinhas));
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
            //System.out.println("Lista Peso => " + listaPeso.size() +  " Peso => " + listaPeso.get(escolhidaColuna) + "  COLUNA => " + escolhidaColuna);
            
            this.custoTotal += listaPeso.get(escolhidaColuna);
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
        this.listaElementos = solucaoColunas;
        this.qtdLinhaCoberto = cobremLinhas;
    }
       
    public void eliminaRedundancia(ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Double> listaPeso){
        ArrayList<Integer> T = new ArrayList<>();
        for(int a : this.listaElementos){
            T.add(a);
        }
        while(!T.isEmpty()){
            Random random = new Random();
            int num = random.nextInt(T.size());
            //System.out.println("Tamanho => " + T.size() + "  Numero escolhido => " + num);
            //int pos = Util.buscaBinaria(T, num, 0, T.size());
            int escolhidaColuna = T.get(num);
            //System.out.print("\nColuna => " + escolhidaColuna);
            T.remove(num);
            //System.out.println(T);
            //System.out.println("\nColuna: " + escolhidaColuna);
            boolean maior2 = true;
            for(int a : listaColuna.get(escolhidaColuna)){
                //System.out.print(" -- " + a);
                if(this.qtdLinhaCoberto[a] < 2){
                    maior2 = false;
                    break;
                }
            }
            if(maior2){
                //System.out.println("entrou!!");
                //int tam = this.listaElementos.size();
                //int pos = Util.buscaBinaria(this.listaElementos, escolhidaColuna, 0, tam);
                //System.out.println(this.listaElementos);
                //System.out.println(escolhidaColuna);
                int pos = 0;
                for(int a : this.listaElementos){
                    if(a == escolhidaColuna){
                        break;
                    }
                    pos++;
                }
                int colExcluida = this.listaElementos.remove(pos);
                this.custoTotal -= listaPeso.get(colExcluida);
                //System.out.println("oii");
                
                for(int a : listaColuna.get(escolhidaColuna)){
                    this.qtdLinhaCoberto[a] = this.qtdLinhaCoberto[a] - 1;
                }
            }
        }
    }

    public Double calculaCusto(int nLinha, ArrayList<ArrayList<Integer>> listaColuna, ArrayList<Double> listaPeso){
        Double custo = 0.0;
        int coberta[] = new int[nLinha+1];
        for(int col : this.listaElementos){
            //System.out.print(col + " -- " + listaPeso.get(col)+ " ==> ");
            //System.out.println(col);
            custo += listaPeso.get(col);
            
            for(int ln : listaColuna.get(col)){
                //System.out.print(ln + " -- ");
                coberta[ln] += 1;
            }
            //System.out.println();
        }
        this.qtdLinhaCoberto = coberta;
        return custo;
    }

    public void addColuna(int coluna, ArrayList<Double> listaPeso){
        if(this.listaElementos.contains(coluna)){
            return;
        }
        this.listaElementos.add(coluna);
        this.custoTotal += listaPeso.get(coluna);
    }
}
