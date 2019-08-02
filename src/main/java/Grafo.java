
import java.util.ArrayList;
import java.util.List;

public class Grafo {

    List<Vertice> vertices;


    public Grafo() {
        vertices = new ArrayList<Vertice>();
    }

    Vertice addVertice(Vertice v) {
        vertices.add(v);
        return v;
    }

    public void mostraGrafo(){
        for(Vertice v: vertices){
            System.out.print(v.nome + " ");
            for(Vertice a : v.adjacente){
                System.out.print("->" + a.nome);
            }
            System.out.println();
        }

    }


}