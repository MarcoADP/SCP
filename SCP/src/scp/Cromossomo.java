package scp;

import java.util.ArrayList;

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
