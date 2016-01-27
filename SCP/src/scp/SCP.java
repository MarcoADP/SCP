package scp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SCP {
    public static int buscaNum(String linha){
        int indice = linha.indexOf(" ");
        String sLinha = linha.substring(indice);
        int nLinha = Integer.parseInt(sLinha.trim());
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
        String lixo = br.readLine();
        int nLinha = buscaNum(linha);      
        int nColuna = buscaNum(coluna);
        
        coluna = br.readLine();
        while(!coluna.equals("")){
            int indice = coluna.indexOf(" ");
            String linhaAtual = coluna.substring(indice);
            linhaAtual = linhaAtual.trim();
            String elemento[];
            elemento = linhaAtual.split(" ");
            int k = 0;
            String elAtual = elemento[k];
            while(!elAtual.equals("")){
                System.out.println(elAtual);
                k++;
                elAtual = elemento[k];
            }
            System.out.println("======");
            coluna = br.readLine();
        }
        //System.out.println(nLinha);
        //System.out.println(coluna);
        
        
        
        
    }
    
}
