import java.util.ArrayList;
import java.util.List;

 public class Vertice {
            int nome;
            List<Vertice> adjacente;
            int peso;

            Vertice(int nome, int peso) {
                this.nome = nome;
                this.adjacente = new ArrayList<Vertice>();
                this.peso = peso;
            }

            void addAdj(Vertice e) {
                adjacente.add(e);
            }
        }
