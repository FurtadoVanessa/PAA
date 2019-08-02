package interdisciplinar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForcaBrutaBacktracking implements Runnable {

    private int[][] matriz;
    private int saida;
    private int valorMinimo = Integer.MAX_VALUE;
    private No menor;
    private String tipo = "ForcaBrutaBacktracking/";
    private String caminhoFinal;

    public ForcaBrutaBacktracking(int[][] m, int ArquivoSaida) {
        this.matriz = m;
        this.saida = ArquivoSaida;
    }

    private void arvore() {

        int linha = 0;
        No origem = new No(linha, null);

        ArrayList<Integer> todosNos = new ArrayList<>();

        int total = matriz.length;
        int somaBase = matriz[matriz.length - 1][0];
        String caminhoBase = "";
        for (int i = 0; i < total; i++) {
            todosNos.add(i);
            if (i < total - 1) {
                somaBase += matriz[i][i + 1];
            }
            caminhoBase = caminhoBase.concat(String.valueOf(i) + "-");
        }
        caminhoBase = caminhoBase.concat("0");
        valorMinimo = somaBase;

        origem.setNaoVisitados(todosNos);

        No atual = origem;

        ArrayList<No> expansaoAtual = new ArrayList<>();

        for (Integer i : atual.getNaoVisitado()) {
            No novo = new No(i, atual, matriz[atual.valor][i]);
            novo.setNaoVisitados(atual.naoVisitados);
            expansaoAtual.add(novo);

        }

        boolean executa = true;
        while (executa) {
            ArrayList<No> lista = new ArrayList<No>();
            for (No n : expansaoAtual) {

                atual = n;
                for (Integer i : atual.getNaoVisitado()) {
                    No novo = new No(i, atual, matriz[atual.valor][i]);
                    novo.setNaoVisitados(atual.naoVisitados);
                    if (novo.peso < valorMinimo) {
                        lista.add(novo);
                    }
                }
            }
            if (!lista.isEmpty()) {
                expansaoAtual = lista;
            } else {
                for (No n : expansaoAtual) {
                    No novo = new No(0, n, matriz[n.valor][0]);
                    lista.add(novo);
                }
                expansaoAtual = lista;
                executa = false;
            }
        }

        for (No n : expansaoAtual) {
            if (n.peso < valorMinimo) {
                valorMinimo = n.peso;
                menor = n;
            }
        }
        if (valorMinimo != somaBase) {
            caminhoFinal = getCaminho(menor);
        } else {
            caminhoFinal = caminhoBase;
        }
    }

    public String getCaminho(No n) {
        String s = n.valor + "-";

        No pai = n.pai;
        while (pai != null) {
            s += pai.valor + "-";
            pai = pai.pai;
        }
        return s;
    }

    @Override
    public void run() {

        String formato = "#0000";
        DecimalFormat d = new DecimalFormat(formato);

        long tempoInicio = System.currentTimeMillis();

        arvore();

        long tempoFim = System.currentTimeMillis();
        long tempoTotal = tempoFim - tempoInicio;
        String escrita = d.format(saida) + ":" + String.valueOf(tempoTotal) + "\n";

        EscreverArquivo.getInstance().escreverCaixeiroViajante(caminhoFinal, valorMinimo, String.valueOf(saida), tipo);

        try {
            Files.write(Paths.get("C:/Users/vanes/Documents/Interdisciplinar/grafos/ForcaBrutaBacktracking/tempo.txt"), escrita.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(ForcaBruta.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
