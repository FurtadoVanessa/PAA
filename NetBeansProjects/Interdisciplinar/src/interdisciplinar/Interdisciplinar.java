package interdisciplinar;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Interdisciplinar {

    EscreverArquivo escrever = EscreverArquivo.getInstance();

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String caminho = "";
        System.out.println("Digite qual codigo deseja rodar: ");
        System.out.println("1 - Bellman Ford");
        System.out.println("2 - Dijsktra");
        System.out.println("3 - Floyd Warshall");
        System.out.println("4 - Caixeiro viajante - Força Bruta");
        System.out.println("5 - Caixeiro viajante - Força Bruta com Backtracking");
        System.out.println("6 - Caixeiro viajante - Programação Dinamica");
        //int opcao = sc.nextInt();
        System.out.println("Digite o numero de grafos que deseja rodar: ");
        //int quantidade = sc.nextInt();
        int opcao = 6;
        int quantidade = 9;
        switch (opcao) {

            case 4:
                caminho = "BellmanFord/";
                break;
            case 5:
                caminho = "BellmanFord/";
                break;
            case 6:
                caminho = "BellmanFord/";
                break;

        }
        mostrarHora();
            for (int i = 8; i <= quantidade; i++) {
                while (Thread.activeCount() > 6) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Interdisciplinar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("Thread " + i);
                Grafo grafo = EscreverArquivo.getInstance().leituraArquivo(i, caminho);
                int[][] matriz = EscreverArquivo.getInstance().gerarMatriz(i, caminho);
                Thread t = null;
                switch (opcao) {

                    case 1:
                        BellmanFord b = new BellmanFord(grafo, i);
                        t = new Thread(b);
                        break;
                    case 2:
                        Dijsktra d = new Dijsktra(grafo, i);
                        t = new Thread(d);
                        break;
                    case 3:
                        FloydWarshall f = new FloydWarshall(grafo, i);
                        t = new Thread(f);
                        break;
                    case 4:
                        BrutaProfundidade fb = new BrutaProfundidade(matriz, i);
                        t = new Thread(fb);
                        break;
                    case 5:
                        ForcaBrutaBacktracking2 fbb = new ForcaBrutaBacktracking2(matriz, i);
                        t = new Thread(fbb);
                        break;
                case 6:
                    Dinamica pd = new Dinamica(matriz, i);
                    t = new Thread(pd);
                    break;

                }
                t.start();

            }
        
        mostrarHora();
    }

    static void mostrarHora() {
        long tempo = System.currentTimeMillis();
        long segundos = (tempo / 1000) % 60;      // se não precisar de segundos, basta remover esta linha.
        long minutos = (tempo / 60000) % 60;     // 60000   = 60 * 1000
        long horas = tempo / 3600000;            // 3600000 = 60 * 60 * 1000
        System.out.println(String.format("%03d:%02d:%02d", horas, minutos, segundos));
    }
}
