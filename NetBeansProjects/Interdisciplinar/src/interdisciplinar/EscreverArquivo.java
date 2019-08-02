package interdisciplinar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EscreverArquivo {

    private static EscreverArquivo a;

    private EscreverArquivo() {
    }

    public static EscreverArquivo getInstance() {
        if (a == null) {
            a = new EscreverArquivo();
        }
        return a;
    }

    public void escreverArquivo(int[][] matrizAdjacencia, String saida, String codigo) {
        Writer wr;
        try {
            wr = new FileWriter("C:/Users/vanes/Documents/Interdisciplinar/grafos/" + codigo + "" + saida + ".txt");
            for (int i = 0; i < matrizAdjacencia.length; ++i) {
                for (int j = 0; j < matrizAdjacencia.length; ++j) {
                    wr.write(String.valueOf(matrizAdjacencia[i][j]));
                    wr.write(" ");
                }
                wr.write("\n");
            }
            wr.close();
        } catch (IOException ex) {
            Logger.getLogger(Interdisciplinar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escreverCaixeiroViajante(String c, int peso, String saida, String tipo) {
        Writer wr;
        try {
            wr = new FileWriter("C:/Users/vanes/Documents/Interdisciplinar/grafos/" + tipo + "" + saida + ".txt");

            wr.write("Caminho: ");
            wr.write(c);
            wr.write("Peso: " + peso);
            wr.close();
        } catch (IOException ex) {
            Logger.getLogger(Interdisciplinar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Grafo leituraArquivo(int i, String caminho) {
        String formato = "#0000";
        DecimalFormat d = new DecimalFormat(formato);
        Grafo grafo = null;
        int contarLinha = 0;
        int contarColuna = 0;
        try {
            FileReader arq = new FileReader("C:/Users/vanes/Documents/Interdisciplinar/grafos/" + caminho + "" + d.format(i) + ".txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            String split[] = linha.split(" ");
            grafo = new Grafo(split.length);
            grafo.addLinha(split);
            linha = lerArq.readLine();
            while (linha != null) {
                split = linha.split(" ");
                grafo.addLinha(split);
                linha = lerArq.readLine();
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        return grafo;
    }

    public int[][] gerarMatriz(int i, String caminho) {
        String formato = "#0000";
        DecimalFormat d = new DecimalFormat(formato);
        int contarLinha = 0;
        int[][] matriz = null;
        try {
            FileReader arq = new FileReader("C:/Users/vanes/Documents/Interdisciplinar/grafos/" + caminho + "" + d.format(i) + ".txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            String split[] = linha.split(" ");
            matriz = new int[split.length][split.length];
            for (int j = 0; j < split.length; j++) {
                matriz[contarLinha][j] = Integer.parseInt(split[j]);
            }
            contarLinha++;
            linha = lerArq.readLine();
            while (linha != null) {
                split = linha.split(" ");
                for (int j = 0; j < split.length; j++) {
                    matriz[contarLinha][j] = Integer.parseInt(split[j]);
                }
                contarLinha++;
                linha = lerArq.readLine();
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        return matriz;
    }

}
