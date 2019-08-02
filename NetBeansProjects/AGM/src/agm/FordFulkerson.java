package agm;

import java.util.LinkedList;

public class FordFulkerson {

    int V;

    public FordFulkerson(int[][] matriz, int s, int t) {
        V = t+1;
        System.out.println("O fluxo máximo possível é " + this.fordFulkerson(matriz, s, t));
    }

    boolean bfs(int[][] redeResidual, int s, int t, int pai[]) {
        boolean[] visitados = new boolean[V];
        for (int i = 0; i < V; ++i) {
            visitados[i] = false;
        }

        LinkedList<Integer> fila = new LinkedList<Integer>();
        fila.add(s);
        visitados[s] = true;
        pai[s] = -1;

        while (fila.size() != 0) {
            int u = fila.poll();

            for (int v = 0; v < V; v++) {
                if (visitados[v] == false && redeResidual[u][v] > 0) {
                    fila.add(v);
                    pai[v] = u;
                    visitados[v] = true;
                }
            }
        }

        return (visitados[t-1] == true);
    }

    int fordFulkerson(int[][] grafo, int s, int t) {
        int u, v;

        int[][] redeResidual = new int[V][V];

        for (u = 0; u < V; u++) {
            for (v = 0; v < V; v++) {
                redeResidual[u][v] = grafo[u][v];
            }
        }

        int[] pai = new int[V];

        int fluxoMaximo = 0;

        while (bfs(redeResidual, s, t, pai)) {
            int caminho = Integer.MAX_VALUE;
            for (v = t; v != s; v = pai[v]) {
                u = pai[v];
                caminho = Math.min(caminho, redeResidual[u][v]);
            }

            for (v = t; v != s; v = pai[v]) {
                u = pai[v];
                redeResidual[u][v] -= caminho;
                redeResidual[v][u] += caminho;
            }

            fluxoMaximo += caminho;
        }

        return fluxoMaximo;
    }
}
