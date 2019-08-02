package interdisciplinar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FloydWarshall implements Runnable{
    private Grafo g;
    private int arquivoSaida;

     public FloydWarshall(Grafo g, int arquivoSaida){
        this.g = g;
        this.arquivoSaida = arquivoSaida;
    }
     
     int[][] floydWarshall(){
         
        Grafo grafo = g;
        int[][] matrizAdjacencia = inicializaMatriz(grafo.matrizAdjacentes);
         
        for(int k=0; k<grafo.vertices.size(); k++){
        
            for(int i=0; i<grafo.vertices.size(); i++){
            
                for(int j=0; j<grafo.vertices.size(); j++){
                
                    matrizAdjacencia[i][j] = encontraMenor(matrizAdjacencia[i][j], matrizAdjacencia[i][k] + matrizAdjacencia[k][j]);
                }
            }
        }

        return matrizAdjacencia;
     }
    
     int [][] inicializaMatriz(int [][] matriz){
         for(int i=0; i<matriz.length; i++){
                for(int j=0; j<matriz.length; j++){
                    if((i!=j)&&(matriz[i][j]==0))
                        matriz[i][j] = 100000;
                }
         }
         return matriz;
     }
     
    void printMatrizAdjacencia(int[][] matrizAdjacencia){
        for (int i=0; i<matrizAdjacencia.length; ++i) 
            { 
            for (int j=0; j<matrizAdjacencia.length; ++j) 
                { 
                    System.out.print(matrizAdjacencia[i][j]+" \t");
                }
                System.out.println("");
            }
        
                System.out.println("");
    }
     
     int encontraMenor(int local, int intermediario){
         if(local<intermediario)
             return local;
         else
             return intermediario;
     }
     
      @Override
    public void run() {
        String formato = "#0000";
        DecimalFormat d = new DecimalFormat(formato);
        
       long tempoInicio = System.currentTimeMillis();
        

        EscreverArquivo.getInstance().escreverArquivo(floydWarshall(), String.valueOf(arquivoSaida), "FloydWarshall/"); 

        long tempoFim = System.currentTimeMillis();
        long tempoTotal = tempoFim - tempoInicio;
        String escrita = d.format(arquivoSaida)+":"+String.valueOf(tempoTotal)+"\n";
        
        try {
            Files.write(Paths.get("C:/Users/vanes/Documents/Interdisciplinar/grafos/FloydWarshall/tempo.txt"), escrita.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(Dijsktra.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
}
