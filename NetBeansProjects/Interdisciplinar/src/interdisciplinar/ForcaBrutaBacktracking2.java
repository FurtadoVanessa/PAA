package interdisciplinar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForcaBrutaBacktracking2 implements Runnable {

    private int[][] matrizAdjacencia;
    public int[] menorCaminho, vertices;
    public int pesoMinimo = Integer.MAX_VALUE;
    public int soma = 0;
    private int[] p;
    private int arquivoSaida;
    private String tipo = "ForcaBrutaBacktracking/";

    public ForcaBrutaBacktracking2(int[][] matriz, int arquivoSaida) {

        this.matrizAdjacencia = matriz;
        this.arquivoSaida = arquivoSaida;
        this.vertices = new int[matriz.length];
        for(int i=0; i<matriz.length; i++)
            this.vertices[i] = i;
        this.p = new int[vertices.length];

    }

    boolean calculaCaminho(int[] caminho) {

        soma = 0;
        for (int i = 0; i < caminho.length - 1; i++) {
            soma += matrizAdjacencia[caminho[i]][caminho[i + 1]];
            if (soma > pesoMinimo) {
                return false;
            }
        }
        return true;
    }

    boolean calculaCaminhoParcial(int[] caminho, int valor, int posicao) {

        soma = 0;
        caminho[posicao] = valor;
        for (int i = 0; i < caminho.length - 1; i++) {
            soma += matrizAdjacencia[caminho[i]][caminho[i + 1]];
            if (soma > pesoMinimo) {
                return false;
            }
        }
        return true;
    }

    void compara(int[] caminho) {
        if (pesoMinimo > soma) {
            pesoMinimo = soma;
            menorCaminho = caminho;
        }
    }

    void permuta(int[] vet, int n) {

        if (n == vet.length) {

            int[] copia = new int[p.length + 1];
            for (int i = 0; i < p.length; i++) {
                copia[i] = p[i];
            }
            copia[p.length] = copia[0];
            if (calculaCaminho(copia)) {
                compara(copia);
            }

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
                    if (calculaCaminhoParcial(p, vet[i], n)) {
                        permuta(vet, n + 1);
                    } 
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
        
        //System.out.println(Arrays.toString(menorCaminho));
        //System.out.println(pesoMinimo);

        EscreverArquivo.getInstance().escreverCaixeiroViajante(Arrays.toString(menorCaminho), pesoMinimo, String.valueOf(arquivoSaida), tipo);

        try {
            Files.write(Paths.get("C:/Users/vanes/Documents/Interdisciplinar/grafos/ForcaBrutaBacktracking/tempo.txt"), escrita.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(Dijsktra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
