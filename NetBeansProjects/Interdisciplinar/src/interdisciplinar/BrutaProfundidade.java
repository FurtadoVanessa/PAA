package interdisciplinar;

import com.sun.xml.internal.ws.client.ContentNegotiation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrutaProfundidade implements Runnable {

    private int[][] matriz;
    private int saida;
    private int valorMinimo = Integer.MAX_VALUE;
    private No menor;
    private String tipo = "Teste/";
    private ArrayList<No> folhas = new ArrayList<>();

    public BrutaProfundidade(int[][] m, int ArquivoSaida) {
        this.matriz = m;
        this.saida = ArquivoSaida;
    }

    private void arvore() {

        int linha = 0;
        No origem = new No(linha, null);

        ArrayList<Integer> todosNos = new ArrayList<>();

        int total = matriz.length;
        for (int i = 0; i < total; i++) {
            todosNos.add(i);
        }

        origem.setNaoVisitados(todosNos);
    }

    private No BDS(No origem) {
        No atual = origem;

        ArrayList<No> expansaoAtual = new ArrayList<>();

        for (Integer i : atual.getNaoVisitado()) {
            No novo = new No(i, atual, matriz[atual.valor][i]);
            novo.setNaoVisitados(atual.naoVisitados);
            expansaoAtual.add(novo);
        }

        for (No n : expansaoAtual) {
            expande(n);
        }

        for (No n : folhas) {
            System.out.println(n.valor + " " + getCaminho(n));
        }
        return atual;
    }

public void expande(No n) {
        
        if (!n.naoVisitados.isEmpty()) {
            
            for (Integer i : n.naoVisitados) {
                
                No novo = new No(i, n, matriz[n.valor][i]);
                novo.setNaoVisitados(n.naoVisitados);
                expande(n);
            }
        } else {
            folhas.add(n);
        }
        
    }
    
    public String getCaminho(No n) {
        String saida = n.valor + "-";
        
        No pai = n.pai;
        while (pai != null) {
            saida += pai.valor + "-";
            pai = pai.pai;
        }
        return saida;
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
        
        EscreverArquivo.getInstance().escreverCaixeiroViajante(getCaminho(menor), valorMinimo, String.valueOf(saida), tipo);
        
        try {
            Files.write(Paths.get("C:/Users/vanes/Documents/Interdisciplinar/grafos/ForcaBruta/tempo.txt"), escrita.getBytes(), StandardOpenOption.APPEND);
        



} catch (IOException ex) {
            Logger.getLogger(ForcaBruta.class

.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
