package agm;

import java.util.ArrayList;
import java.util.List;

 public class Vertice {
            int nome;
            List<Vertice> adjacente;
            int peso;
            int pai;

            Vertice(int nome, int peso, int pai) {
                this.nome = nome;
                this.adjacente = new ArrayList<Vertice>();
                this.peso = peso;
                this.pai = pai;
            }
            
            Vertice(int nome) {
                this.nome = nome;
                this.adjacente = new ArrayList<Vertice>();
            }

            void addAdj(Vertice e) {
                adjacente.add(e);
            }
            
            void addPeso(int p){
                peso = p;
            }
            
            void addPai(int p){
                pai = p;
            }
        }
