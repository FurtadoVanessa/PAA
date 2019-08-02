import java.util.ArrayList;
import java.util.List;

public class Grafo {

    List<Vertice> vertices;
    List<Aresta> arestas;
    int[][] matrizAdjacentes;
    private int contador = 0;

    public Grafo(int tamanho) {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
        matrizAdjacentes = new int[tamanho][tamanho];
    }

    Vertice addVertice(Vertice v) {
        vertices.add(v);
        return v;
    }

    Aresta addAresta(Vertice origem, Vertice destino, int peso) {
        Aresta e = new Aresta(origem, destino, peso);
        origem.addAdj(destino);
        arestas.add(e);
        return e;
    }

    public void addLinha(String s[]) {
        int cont = 0;

        Vertice atual = new Vertice(contador, 0);
        for (int i = 0; i < s.length; i++) {

            int valor = Integer.parseInt(s[i]);
            matrizAdjacentes[contador][cont] = valor;
            cont++;

                if (valor != 0) {
                    atual.adjacente.add(new Vertice(i, valor));
                }

        }
        this.vertices.add(atual);
        contador++;
    }



}