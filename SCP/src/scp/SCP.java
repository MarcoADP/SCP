package scp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        
        
        
        
        String arq = "teste2.txt";
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
        /*
        aj = 0;
        System.out.println("\n\nLista Coluna:");
        for(ArrayList a: listaColuna){
            System.out.println(aj++ + " --> " + a);
        }*/
        /*System.out.println("\n\nLista Peso");
        for(double a : listaPeso){
            System.out.println(aj++ + " --> " + a);
        }*/
        
        int qtdPop = 50;
        algoritmoGenetico(listaPeso, listaColuna, listaLinha, qtdPop, nLinha);
        
        
        
        
    }
    
}
