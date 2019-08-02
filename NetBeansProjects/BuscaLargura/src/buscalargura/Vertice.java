package buscalargura;

import java.util.ArrayList;
import java.util.List;

 public class Vertice {
            String nome;
            List<Vertice> adjacente;
            Vertice pai;

            Vertice(String nome, Vertice pai) {
                this.nome = nome;
                this.adjacente = new ArrayList<Vertice>();
                this.pai = pai;
            }

            void addAdj(Vertice e) {
                adjacente.add(e);
            }
            
            void addPai(Vertice pai){
                this.pai = pai;
            }
        }
