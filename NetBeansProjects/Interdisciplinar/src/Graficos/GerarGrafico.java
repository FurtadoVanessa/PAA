package Graficos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class GerarGrafico {
    
    public static void main(String args[]){
        PrimeiraParte p = new PrimeiraParte();   
        
    }
    
    static public void gerarGrafico(ArrayList<int[]> pontos, String tipoCodigo){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int[] ponto: pontos){
            dataset.addValue(ponto[1], tipoCodigo, String.valueOf(ponto[0]));
        }
        
        JFreeChart criaGrafico = ChartFactory.createLineChart("Gráfico Simples",
                "Vertices", "Tempo", dataset, PlotOrientation.VERTICAL, true, true, false);
        
        try{
            System.out.println("Criando o grafico...");
            try (OutputStream png = new FileOutputStream("C:/Users/vanes/Documents/Interdisciplinar/graficos/GráficoSimples.png")) {
                ChartUtilities.writeChartAsPNG(png, criaGrafico, 500, 400);
            }
        }catch(IOException io){
            System.out.println("ERRO: " + io.getMessage());
        }
        System.out.println("Gráfico criado!");
        
    }
    
    static public ArrayList<int[]> leituraArquivo(String caminho) {
        String formato = "#0000";
        DecimalFormat d = new DecimalFormat(formato);
        ArrayList<int[]> listaPontos = new ArrayList<>();
        try {
            FileReader arq = new FileReader("C:/Users/vanes/Documents/Interdisciplinar/grafos/" + caminho + "tempo.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            String split[] = linha.split(":");
            int[] ponto = {Integer.parseInt(split[0]), Integer.parseInt(split[1])};
            listaPontos.add(ponto);
            linha = lerArq.readLine();
            while (linha != null) {
                split = linha.split(":");
                int[] p = {Integer.parseInt(split[0]), Integer.parseInt(split[1])};
                listaPontos.add(p);
                linha = lerArq.readLine();
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        return listaPontos;
    }
    
}
