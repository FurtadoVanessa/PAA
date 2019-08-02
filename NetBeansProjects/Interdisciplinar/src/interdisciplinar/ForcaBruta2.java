package interdisciplinar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForcaBruta2 implements Runnable {

    private int[][] matrizAdjacencia;
    public int[] menorCaminho, vertices;
    public int pesoMinimo = Integer.MAX_VALUE;
    private int[] p;
    private int arquivoSaida;
    private String tipo = "ForcaBruta/";

    public ForcaBruta2(int[][] matriz, int arquivoSaida) {

        this.matrizAdjacencia = matriz;
        this.arquivoSaida = arquivoSaida;
        this.p = new int[vertices.length];

    }

    void compara(int soma, int[] caminho) {
        if (pesoMinimo > soma) {
            pesoMinimo = soma;
            menorCaminho = caminho;
        }
    }

    int calculaCaminho(int[] caminho) {

        int soma = 0;
        for (int i = 0; i < caminho.length - 1; i++) {
            soma += matrizAdjacencia[caminho[i]][caminho[i + 1]];
        }
        return soma;
    }

    void permuta(int[] vet, int n) {

        if (n == vet.length) {
            int[] copia = new int[p.length + 1];
            for (int a = 0; a < p.length; a++) {
                copia[a] = p[a];
            }
            copia[p.length] = copia[0];
            compara(calculaCaminho(copia), copia);

        } else {

            for (int i = 0; i < vet.length; i++) {

                boolean achou = false;

                for (int j = 0; j < n; j++) {

                    if (p[j] == vet[i]) {
                        achou = true;
                    }
                }

                if (!achou) {

                    p[n] = vet[i];
                    permuta(vet, n + 1);
                }
            }
        }
    }

    @Override
    public void run() {
        String formato = "#0000";
        DecimalFormat d = new DecimalFormat(formato);

        long tempoInicio = System.currentTimeMillis();

        permuta(vertices, 0);

        long tempoFim = System.currentTimeMillis();
        long tempoTotal = tempoFim - tempoInicio;
        String escrita = d.format(arquivoSaida) + ":" + String.valueOf(tempoTotal) + "\n";

        //EscreverArquivo.getInstance().escreverCaixeiroViajante(menorCaminho, pesoMinimo, String.valueOf(arquivoSaida), tipo);

        try {
            Files.write(Paths.get("C:/Users/vanes/Documents/Interdisciplinar/grafos/ForcaBruta/tempo.txt"), escrita.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(Dijsktra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
