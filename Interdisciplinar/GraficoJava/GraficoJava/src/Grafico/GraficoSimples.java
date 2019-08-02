package Grafico;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class GraficoSimples {
    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(10.1, "Máximo", "Hora 1");
        dataset.addValue(20.1, "Máximo", "Hora 2");
        dataset.addValue(30.1, "Máximo", "Hora 3");
        dataset.addValue(40.1, "Máximo", "Hora 4");
        dataset.addValue(70.1, "Máximo", "Hora 5");
        
        JFreeChart criaGrafico = ChartFactory.createLineChart("Gráfico Simples",
                "Hora", "Valor", dataset, PlotOrientation.VERTICAL, true, true, false);
        
        try{
            System.out.println("Criando o grafico...");
            try (OutputStream png = new FileOutputStream("GráficoSimples.png")) {
                ChartUtilities.writeChartAsPNG(png, criaGrafico, 500, 400);
            }
        }catch(IOException io){
            System.out.println("ERRO: " + io.getMessage());
        }
        System.out.println("Gráfico criado!");
    }
}
