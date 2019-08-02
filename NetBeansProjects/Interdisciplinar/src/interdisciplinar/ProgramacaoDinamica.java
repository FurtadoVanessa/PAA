package interdisciplinar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgramacaoDinamica implements Runnable {

    private int[][] matriz;
    public int[] menorCaminho, vertices;
    public int pesoMinimo = Integer.MAX_VALUE;
    private int[] p;
    private int arquivoSaida;
    private String tipo = "ProgramacaoDinamica/";
    private ArrayList<String> caminhos = new ArrayList<>();
    private ArrayList<Integer> pesos = new ArrayList<>();
    private ArrayList<int[]> permutacoes = new ArrayList<>();

    public ProgramacaoDinamica(int[][] m, int arquivoSaida) {

        this.arquivoSaida = arquivoSaida;
        this.matriz = m;
        int[] v = new int[matriz.length];
        this.p = new int[v.length];
        for (int i = 0; i < v.length; i++) {
            v[i] = i;
            p[i] = 0;
        }
        this.vertices = v;
    }

    void compara(int soma, int[] caminho) {
        if (pesoMinimo > soma) {
            pesoMinimo = soma;
            menorCaminho = caminho;
        }
    }

    int calculaPeso(int[] vetor) {
        int soma = 0;
        for (int i = 0; i < vetor.length - 1; i++) {
            soma += matriz[vetor[i]][vetor[i + 1]];
        }
        return soma;
    }

    void armazenaCaminho(int[] vetor) {
        String caminho = Arrays.toString(vetor).substring(0, Arrays.toString(vetor).length() - 1) + Arrays.toString(vetor).substring(2, Arrays.toString(vetor).length());
        int soma = calculaPeso(vetor);
        pesos.add(soma);
        caminhos.add(caminho);
        compara(soma, vetor);
    }

    boolean validaCaminho(int[] caminho) {

        for (String s : caminhos) {
            if (s.contains(Arrays.toString(caminho).substring(1, Arrays.toString(caminho).length() - 1))) {
                System.out.println("O caminho " + s + " possui o caminho " + Arrays.toString(caminho));
                return false;
            } else {
                System.out.println("O caminho " + s + " nao possui o caminho " + Arrays.toString(caminho));
            }
        }
        return true;

    }

    void print(int[] v) {
        for (int i = 0; i < v.length; i++) {
            System.out.print(v[i] + " - ");
        }
        System.out.println("");
    }

    void permuta(int[] vet, int n) {

        if (n == vet.length) {
            print(p);
            int[] copia = new int[p.length + 1];
            for (int a = 0; a < p.length; a++) {
                copia[a] = p[a];
            }
            copia[p.length] = copia[0];
            if (validaCaminho(copia)) {
                armazenaCaminho(copia);
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
//                    if (calculaCaminhoParcial(p, vet[i], n)) {
//                        System.out.println("Adicionou o " + vet[i] + " na posicao " + n);
                    p[n] = vet[i];
                    permuta(vet, n + 1);
//                    } else {
//                        System.out.println("Nao adicionou " + vet[i] + " na posicao " + n);
//                    }
                }
            }
        }
    }

    boolean calculaCaminhoParcial(int[] caminho, int valor, int posicao) {

        int soma = 0;
        caminho[posicao] = valor;
        System.out.println("peso minimo " + pesoMinimo);
        print(caminho);
        for (int i = 0; i < caminho.length - 1; i++) {
            if ((caminho[i] == valor) && (i != posicao)) {
                soma += 0;
            } else {
                soma += matriz[caminho[i]][caminho[i + 1]];
            }
            System.out.println("Soma parcial: " + soma);

            if (soma > pesoMinimo) {
                return false;
            }
        }
        System.out.println("Soma do caminho deu " + soma);
        return true;
    }

    @Override
    public void run() {
        String formato = "#0000";
        DecimalFormat d = new DecimalFormat(formato);

        long tempoInicio = System.currentTimeMillis();

        permuta(vertices, 0);
        for(int[] caminho : permutacoes){
            print(caminho);
        }

        long tempoFim = System.currentTimeMillis();
        long tempoTotal = tempoFim - tempoInicio;
        String escrita = d.format(arquivoSaida) + ":" + String.valueOf(tempoTotal) + "\n";
        String caminho = "";
        for (int i = 0; i < menorCaminho.length; i++) {
            caminho = caminho.concat(String.valueOf(menorCaminho[i]) + "-");
        }

        EscreverArquivo.getInstance().escreverCaixeiroViajante(caminho, pesoMinimo, String.valueOf(arquivoSaida), tipo);

        try {
            Files.write(Paths.get("C:/Users/vanes/Documents/Interdisciplinar/grafos/ProgramacaoDinamica/tempo.txt"), escrita.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(Dijsktra.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
