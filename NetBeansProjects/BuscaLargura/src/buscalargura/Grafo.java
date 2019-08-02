package buscalargura;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

        List<Vertice> vertices;
        List<Aresta> arestas;

        public Grafo() {
            vertices = new ArrayList<>();
            arestas = new ArrayList<>();
        }

        Vertice addVertice(Vertice v) {
            vertices.add(v);
            return v;
        }

        Aresta addAresta(Vertice origem, Vertice destino) {
            Aresta e = new Aresta(origem, destino);
            arestas.add(e);
            for(Vertice v : vertices){
                if(v.nome.compareToIgnoreCase(origem.nome)==0){
                    v.addAdj(destino);
                }
            }
            return e;
        }
}