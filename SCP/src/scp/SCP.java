package scp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SCP {
    public class Cromossomo {
        ArrayList<Integer> listaElementos = new ArrayList<>(); 
        Double custoTotal;

        public Cromossomo() {
            listaElementos = new ArrayList<>();
            custoTotal = 0.0;
        }
        
        public Cromossomo(ArrayList<Integer> lista, ArrayList<Double> peso){
            this.listaElementos = lista;
            this.custoTotal = 0.0;
            for(int a : lista){
                this.custoTotal += peso.get(a-1);
            }
        }
        
    }
    
    public static int buscaNum(String linha){
        //System.out.println(linha);
        int indice = linha.indexOf(" ");
        String sLinha = linha.substring(indice);
        int nLinha = Integer.parseInt(sLinha.trim()); 
        //System.out.println(nLinha);
        return nLinha;
    }
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //Estrturas Utilizadas
        ArrayList<Double> listaPeso = new ArrayList<>();
        ArrayList<ArrayList<Integer>> listaColuna = new ArrayList<>();
        ArrayList<Cromossomo> listaPopulacao = new ArrayList<>(); 
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
        
        int i = 0;
        linha = br.readLine();
        while(!linha.equals("")){
            int id = 0;
            String sublinha[] = linha.split(" ");
            listaColuna.add(new ArrayList<>());
            for (String sublinha1 : sublinha) {
                ArrayList<Integer> aux = new ArrayList<>();
                //System.out.println(sublinha1);
                if(!sublinha1.equals("")){
                    if(id == 0){
                        //System.out.print("coluna = " + sublinha1 + " -- ");
                        id++;
                    } else if(id == 1){
                        listaPeso.add(Double.parseDouble(sublinha1));
                        id++;
                    } else { 
                        //System.out.print(sublinha1 + " ");
                        //aux.add(Integer.parseInt(sublinha1));
                        listaColuna.get(listaColuna.size() - 1).add(Integer.parseInt(sublinha1));
                        //System.out.println(listaColuna.get(listaColuna.size() - 1));
                        //System.out.print(aux);
                    }
                }
                
            }
            
            linha = br.readLine();
            //System.out.println();
        }
        
        /*int kk = 0;
        for(ArrayList a : listaColuna){
            System.out.print(++kk + " --> ");
            for(int j  = 0; j < a.size(); j++){
                System.out.print(a.get(j) + " - ");
            }
            System.out.println();
        }*/
        //System.out.println(listaPeso.get(4960));
        //System.out.println(nLinha);
        //System.out.println(nColuna);
        
        
        
        
        
    }
    
}
