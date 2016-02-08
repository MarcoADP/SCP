package scp;

import java.util.ArrayList;

public class Util {
    
    //Quantidade de elementos presentes em ambas listas
    static int fazInterseccao(ArrayList<Integer> lista1, ArrayList<Integer> lista2) {
        ArrayList<Integer> inter = new ArrayList<>();
        int tamanho;
        
        //System.out.print("Inter => ");
        for(Integer i : lista1){
            if(lista2.contains(i)){
                //System.out.print(i + " == ");
                inter.add(i);
            }
        }
        //System.out.println();
        
        tamanho = inter.size();
        
        return tamanho;
    }
    
    static int buscaBinaria(ArrayList<Integer> lista, int ln, int inicio, int fim) {
        int meio;
        int num; 
        boolean achou = false;
        while(inicio <= fim){
            meio = (fim+inicio)/2;
            num = lista.get(meio);
            //System.out.print(num + " -- ");
            //System.out.println("ln => " + ln + " Inicio => " + inicio + " Meio => " + meio + " Fim => " + fim + " num => " + num);
            if(num == ln){
               return meio;
            }
            else if(num < ln){
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return -1;
        
        /*if(num == ln){
            System.out.println("achou!" + meio);
            return meio;
        }
        if (num < ln){
            buscaBinaria(lista, ln, meio, fim);
        } else {
            buscaBinaria(lista, ln, inicio, meio);
        }
        System.out.println("nao achou!");
        return -1;*/
    }
}
