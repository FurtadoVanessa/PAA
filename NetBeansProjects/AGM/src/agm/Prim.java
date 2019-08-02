package agm;

import java.util.ArrayList;

public class Prim {
    
    
    
    public Prim(int[][] matriz){
        
         long tempoInicio = System.currentTimeMillis();
           
        ArrayList<Vertice> listaVertices = new ArrayList<>();
        ArrayList<Vertice> fila = new ArrayList<>();
        ArrayList<Aresta> resultado = new ArrayList<>();
        
        
        for(int i=0; i<matriz.length; i++){
            Vertice v = new Vertice(i);
            for(int j=0; j<matriz.length; j++){
                if(matriz[i][j]!=0){
                    v.addAdj(new Vertice(j, Integer.MAX_VALUE-1000, -1));
                }
            }
            listaVertices.add(v);
        }
        listaVertices.get(0).addPeso(0);
        for(Vertice v : listaVertices){
            fila.add(v);
        }        
        
        while(fila.size()>2){
           Vertice u = fila.get(encontraVerticeMenorPeso(fila));
            fila.remove(encontraVerticeMenorPeso(fila));
            for(Vertice v : u.adjacente){
                if((encontrouNaFila(v, fila))&&(matriz[u.nome][v.nome]<v.peso)){
                    listaVertices.get(v.nome).pai = u.nome;
                    listaVertices.get(v.nome).peso = matriz[u.nome][v.nome];                    
                    Vertice a = encontraFila(v, fila);
                    a.pai = u.nome;
                    a.peso = matriz[u.nome][v.nome];
                    resultado.add(new Aresta(u.nome, v.nome, matriz[u.nome][v.nome]));
                }
            }
        }
        for(Aresta a : resultado){
            System.out.println(a.origem+" -> "+a.destino+" = "+a.peso);
        }
        
        long tempoFim = System.currentTimeMillis();
        long tempoTotal = tempoFim - tempoInicio;
        System.out.println("Para "+matriz.length+" vertices o tempo foi: "+tempoTotal);
    }
    
    boolean encontrouNaFila(Vertice v, ArrayList<Vertice> lista){
            for(Vertice a : lista){
                if(a.nome == v.nome){
                    return true;
                }
            }
        return false;
    }
    
    Vertice encontraFila(Vertice v, ArrayList<Vertice> lista){
            for(Vertice a : lista){
                if(a.nome == v.nome){
                    return a;
                }
            }
        return null;
    }
    
    int encontraVerticeMenorPeso(ArrayList<Vertice> lista){
        int menor=Integer.MAX_VALUE;
        int vertice=0;
        for(Vertice v : lista){
            if(v.peso<menor){
                menor = v.peso;
                vertice = v.nome;
            }
        }
        return vertice;
    }
    
}
