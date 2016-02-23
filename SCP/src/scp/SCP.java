package scp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static scp.Algoritmo.algoritmoGenetico;

public class SCP {
    
    public static int buscaNum(String linha){
        //System.out.println(linha);
        int indice = linha.indexOf(" ");
        String sLinha = linha.substring(indice);
        int nLinha = Integer.parseInt(sLinha.trim()); 
        //System.out.println(nLinha);
        return nLinha;
    }
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //Caso1: 422.43
        //Caso2: 398.69
        //Caso3: 417.13
        
        
        String arq = "teste1.txt";
        FileInputStream stream = new FileInputStream(arq);
        InputStreamReader reader = new InputStreamReader(stream);
        //InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        
        String linha = br.readLine();
        String coluna = br.readLine();
        int nLinha = buscaNum(linha);      
        int nColuna = buscaNum(coluna);
        br.readLine(); //Lendo a linha dados ou densidades
        
        linha = br.readLine();
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //Estrturas Utilizadas
        ArrayList<Double> listaPeso = new ArrayList<>();
        listaPeso.add(-1.0);
        ArrayList<ArrayList<Integer>> listaColuna = new ArrayList<>();
        ArrayList<Integer> nula = new ArrayList<>();
        listaColuna.add(nula);
        //ArrayList<ArrayList<Integer>> listaLinha = new ArrayList<>();
        ArrayList<Integer>[] listaLinha = new ArrayList[nLinha+1];
        for(int a = 0; a <= nLinha; a++){
            listaLinha[a] = new ArrayList<>();
        }
        ArrayList<Cromossomo> listaPopulacao = new ArrayList<>(); 
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        
        while(!linha.equals("")){
            int id = 0;
            String sublinha[] = linha.split(" ");
            listaColuna.add(new ArrayList<>());
            int colAtual= 0;
            for (String sublinha1 : sublinha) {
                ArrayList<Integer> aux = new ArrayList<>();
                if(!sublinha1.equals("")){
                    if(id == 0){
                        colAtual = Integer.parseInt(sublinha1);
                        id++;
                    } else if(id == 1){
                        listaPeso.add(Double.parseDouble(sublinha1));
                        id++;
                    } else { 
                        int linhaAtual = Integer.parseInt(sublinha1);
                        //System.out.println(linhaAtual);
                        listaColuna.get(listaColuna.size() - 1).add(linhaAtual);
                        listaLinha[linhaAtual].add(colAtual);
                        
                    }
                }
                
            }
            linha = br.readLine();
        }
        int aj = 0;
        /*System.out.println("Lista Linha:");
        for(ArrayList a : listaLinha){
            System.out.println(aj++ + " --> " + a);
        }*/
        
        //aj = 0;
        /*System.out.println("\n\nLista Coluna:");
        for(ArrayList a: listaColuna){
            System.out.println(aj++ + " --> " + a);
        }
        /*System.out.println("\n\nLista Peso");
        for(double a : listaPeso){
            System.out.println(aj++ + " --> " + a);
        }*/
        
        int qtdPop = 500;
        Cromossomo resposta = algoritmoGenetico(listaPeso, listaColuna, listaLinha, qtdPop, nLinha);
        Double custoResposta = resposta.custoTotal;
        ArrayList<Integer> elementosResposta = resposta.listaElementos;
        System.out.println("custo => " + custoResposta);
        System.out.println("Elementos = > " + elementosResposta);
        
        /*ArrayList<Double> peso;
        Collections.sort(listaPeso);
        int foi = 1;
        int soma = 0;
        for(Double a : listaPeso){
            soma += a;
            if(a < 60){
                foi++;
            }
            //System.out.println(a);
        }
        //System.out.println(foi);
        int tam = listaPeso.size();
        //System.out.println(soma/tam);*/
        
    }
    
}
