package Graficos;

import java.util.ArrayList;

public class PrimeiraParte {

    ArrayList<int[]> pontosBellmanFord = new ArrayList<>();
    ArrayList<int[]> pontosDijsktra = new ArrayList<>();
    ArrayList<int[]> pontosFloydWarshall = new ArrayList<>();

    ArrayList<int[]> graficoBellmanFord = new ArrayList<>();
    ArrayList<int[]> graficoDijsktra = new ArrayList<>();
    ArrayList<int[]> graficoFloydWarshall = new ArrayList<>();

    public PrimeiraParte() {
        int a = 0, mediaBellmanFord = 0, mediaDisjktra = 0, mediaFloydWarshall = 0;

        pontosFloydWarshall = GerarGrafico.leituraArquivo("FloydWarshall/");
        pontosBellmanFord = GerarGrafico.leituraArquivo("BellmanFord/");
        pontosDijsktra = GerarGrafico.leituraArquivo("Disjktra/");
        
        for(int[] pontos: pontosBellmanFord){
            System.out.println(pontos[0]+":"+pontos[1]);
        }
        
        for (a = 8; a < 100; a += 10) {
            graficoBellmanFord.add(pontosBellmanFord.get(a));
            graficoDijsktra.add(pontosDijsktra.get(a));
            graficoFloydWarshall.add(pontosFloydWarshall.get(a));
        }
        for (a = 0; a < pontosBellmanFord.size(); a++) {
            mediaBellmanFord += pontosBellmanFord.get(a)[1];
            mediaDisjktra += pontosDijsktra.get(a)[1];
            mediaFloydWarshall += pontosFloydWarshall.get(a)[1];
        }
        mediaBellmanFord /= a;
        mediaDisjktra /= a;
        mediaFloydWarshall /= a;
        System.out.println("A media do Bellman Ford eh " + mediaBellmanFord);
        System.out.println("A media do Disjktra eh " + mediaDisjktra);
        System.out.println("A media do Floyd Warshall eh " + mediaFloydWarshall);

//        for (int[] i : pontosBellmanFord) {
//            System.out.println("(X,Y) = (" + i[0] + "," + i[1] + ")");
//        }
//        for (int[] i : pontosDijsktra) {
//            System.out.println("(X,Y) = (" + i[0] + "," + i[1] + ")");
//        }
//        for (int[] i : pontosFloydWarshall) {
//            System.out.println("(X,Y) = (" + i[0] + "," + i[1] + ")");
//        }
System.out.println("O numero de pontos no bellmanford eh de "+pontosBellmanFord.size());
        GerarGrafico.gerarGrafico(graficoBellmanFord, "BellmanFord");
    }

//    void quickSort(ArrayList<int[]> vetor, int inicio, int fim) {
//        if (inicio < fim) {
//            int posicaoPivo = separar(vetor, inicio, fim);
//            quickSort(vetor, inicio, posicaoPivo - 1);
//            quickSort(vetor, posicaoPivo + 1, fim);
//        }
//    }
//
//    int separar(ArrayList<int[]> vetor, int inicio, int fim) {
//        int[] pivo = vetor.get(inicio);
//        int i = inicio + 1, f = fim;
//        while (i <= f) {
//            if (vetor.get(i)[0] <= pivo[0]) {
//                i++;
//            } else if (pivo[0] < vetor.get(f)[0]) {
//                f--;
//            } else {
//                int[] troca = vetor.get(i);
//                //vetor.remove(i);
//                vetor.add(i, vetor.get(f));
//                vetor.remove(f);
//                vetor.add(f, troca);
//                i++;
//                f--;
//            }
//        }
//        vetor.remove(inicio);
//        vetor.add(inicio, vetor.get(f));
//        vetor.remove(f);
//        vetor.add(f, pivo);
//        return f;
//    }
}
