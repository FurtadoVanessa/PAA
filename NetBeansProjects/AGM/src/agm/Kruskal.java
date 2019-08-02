package agm;

import java.util.ArrayList;

public class Kruskal {

    public Kruskal(int[][] matriz) {
        long tempoInicio = System.currentTimeMillis();

        ArrayList<Aresta> arestas = new ArrayList<>();
        ArrayList<Aresta> resposta = new ArrayList<>();
        int[] visitados;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] != 0) {
                    Aresta a = new Aresta(i, j, matriz[i][j]);
                    arestas.add(a);
                }
            }
        }
        visitados = new int[matriz.length];

        quickSort(arestas, 0, arestas.size() - 1);

        int i = 0;
        for (Aresta a : arestas) {
            if ((!procura(visitados, a.origem)) || (!procura(visitados, a.destino))) {
                resposta.add(a);
                if (i <= matriz.length) {
                    visitados[i] = a.origem;
                }
            }
        }
        System.out.println("A AGM eh dado por: ");
        for (Aresta a : resposta) {
            System.out.println("Origem: " + a.origem + " destino " + a.destino + " peso: " + a.peso);
        }

        long tempoFim = System.currentTimeMillis();
        long tempoTotal = tempoFim - tempoInicio;
        System.out.println("Para " + matriz.length + " vertices o tempo foi: " + tempoTotal);
    }

    boolean procura(int[] vetor, int valor) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == valor) {
                return true;
            }
        }
        return false;
    }

    void quickSort(ArrayList<Aresta> vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);
        }
    }

    int separar(ArrayList<Aresta> vetor, int inicio, int fim) {
        Aresta pivoA = vetor.get(inicio);
        int pivo = vetor.get(inicio).peso;
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (vetor.get(i).peso <= pivo) {
                i++;
            } else if (pivo < vetor.get(f).peso) {
                f--;
            } else {
                Aresta troca = vetor.get(i);
                //vetor.remove(i);
                vetor.add(i, vetor.get(f));
                vetor.remove(f);
                vetor.add(f, troca);
                i++;
                f--;
            }
        }
        vetor.remove(inicio);
        vetor.add(inicio, vetor.get(f - 1));
        vetor.remove(f);
        vetor.add(f, pivoA);
        return f;
    }

}
