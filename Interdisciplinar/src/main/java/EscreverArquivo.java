/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
    public void escreverArquivo(int[][] matrizAdjacencia, String saida){
            Writer wr;
        try {    
            wr = new FileWriter("C:/Users/vanes/Documents/Interdisciplinar/grafos/disjktra/"+saida+".txt");
            for (int i=0; i<matrizAdjacencia.length; ++i){ 
                for (int j=0; j<matrizAdjacencia.length; ++j){ 
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
    
    
    
    public Grafo leituraArquivo(int i){
        String formato = "#0000";
        DecimalFormat d = new DecimalFormat(formato);
      Grafo grafo = null;
        int contarLinha=0;
        int contarColuna=0;
       // BufferedWriter buffWrite = new BufferedWriter(new FileWriter("C:/Users/vanes/Documents/Interdisciplinar/grafos/valores.txt"));

        
        //System.out.println("Iteracao "+d.format(i));
        try {
          FileReader arq = new FileReader("C:/Users/vanes/Documents/Interdisciplinar/grafos/"+d.format(i)+".txt");
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

}
