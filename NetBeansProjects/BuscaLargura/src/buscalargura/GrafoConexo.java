package buscalargura;

import java.util.ArrayList;
import java.util.Scanner;

public class GrafoConexo {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Grafo grafo = new Grafo();
        String origem, destino;
        String vertices[] = {"A", "B", "C", "D", "E", "F"};
        String arestas[] = {"A", "B",
                            "A", "C",
                            "B", "E", 
                            "C", "B",
                            "C", "E",
                            "D", "F",
                            "E", "C"};

        for (String vertice : vertices) {
            grafo.addVertice(new Vertice(vertice, null));
        }
        
        for(int i=0; i<arestas.length; i++){
            grafo.addAresta(new Vertice(arestas[i], null), new Vertice(arestas[i+1], null));
            i++;
        }            
        BFS(grafo);

    }
    
    static Vertice encontrarVertice(Grafo grafo, Vertice vertice){
        for(Vertice v : grafo.vertices){
            if(v.nome.compareToIgnoreCase(vertice.nome)==0){
                return v;
            }
        }
        return null;
    }
    
    static boolean encontrarLista( ArrayList<Vertice> lista, Vertice vertice){
        for(Vertice v : lista){
            if(v.nome.compareToIgnoreCase(vertice.nome)==0){
                return false;
            }
        }
        return true;
    }
    
    static void BFS(Grafo grafo){
        ArrayList<Vertice> lista = new ArrayList<>();
        lista.add(encontrarVertice(grafo, grafo.vertices.get(0)));
        for(int i=0; i<grafo.vertices.size(); i++){
            if(lista.size()>i){
                for (Vertice vertice : lista.get(i).adjacente) {
                    if(encontrarLista(lista, vertice)){
                        lista.add(encontrarVertice(grafo,vertice)); 
                       }
                }
            }
            System.out.println("");
        }
        if(lista.size()<grafo.vertices.size()){
            System.out.println("Grafo desconexo");
        }else{
            System.out.println("Grafo conexo");
        }
    }
    
}
