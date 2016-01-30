package scp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

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
        
        //Estrturas Utilizadas
        ArrayList<Double> listaPeso = new ArrayList<>();
        
        String arq = "teste8.txt";
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
            for (String sublinha1 : sublinha) {
                if(!sublinha1.equals("")){
                    if(id == 0){
                        System.out.print("coluna = " + sublinha1 + " -- ");
                        id++;
                    } else if(id == 1){
                        listaPeso.add(Double.parseDouble(sublinha1));
                        id++;
                    } else { 
                        System.out.print(sublinha1 + " ");
                    }
                }
                /*if(!sublinha1.equals("")){
                    System.out.print(sublinha1 + " ");
                }*/
            }
            linha = br.readLine();
            System.out.println();
        }
        //System.out.println(listaPeso.get(4960));
        //System.out.println(nLinha);
        //System.out.println(nColuna);
        
        
        
        
    }
    
}
