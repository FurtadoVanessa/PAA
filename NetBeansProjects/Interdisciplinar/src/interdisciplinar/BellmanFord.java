package interdisciplinar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BellmanFord implements Runnable{
    
        private Grafo g;
        private int arquivoSaida;
    
    public BellmanFord(Grafo g, int arquivoSaida){
        this.g = g;
        this.arquivoSaida = arquivoSaida;
     
    }

   int[][] BellmanFordParaTodosOsPares(){
       
       Grafo grafo = g;   
       int V = grafo.vertices.size();
        int E = grafo.arestas.size(); 
        int[] distancia = new int[V]; 
        int[][] matriz = new int[V][V];

        for (int k=0; k<V; ++k){ 
            Arrays.fill(distancia, Integer.MAX_VALUE-1000); 
            distancia[k] = 0; 

            for (int i=1; i<V; ++i){ 
                for (int j=0; j<E; ++j){ 
                    int u = grafo.arestas.get(j).origem.nome; 
                    int v = grafo.arestas.get(j).destino.nome; 
                    int peso = grafo.arestas.get(j).peso; 
                    if((distancia[u]+peso) < distancia[v]){
                        distancia[v] = distancia[u] + peso;
                    }
                } 
            } 
            for (int j=0; j<V; ++j){
                for (int i=0; i<V; ++i) {
                    if(j==k)
                        matriz[j][i] = distancia[i];
                }
            }
         }
        return matriz;
   }
   
    void inicializarGrafo(Grafo grafo){
        for(int i=0; i<grafo.vertices.size(); i++){
            grafo.vertices.get(i).peso = Integer.MAX_VALUE-10000;
        }
    }
   
   void printMatrizAdjacencia(int[][] matrizAdjacencia){
        for (int i=0; i<matrizAdjacencia.length; ++i) 
            { 
            for (int j=0; j<matrizAdjacencia.length; ++j) 
                { 
                    System.out.print(matrizAdjacencia[i][j]+" \t");
                }
                System.out.println("\n");
            }
   }
   
    void BellmanFordSimples(Grafo grafo,int origem){ 
        int V = grafo.vertices.size();
        int E = grafo.arestas.size(); 
        int[] distancia = new int[V]; 

            Arrays.fill(distancia, Integer.MAX_VALUE-1000); 
            distancia[origem] = 0; 
        

            for (int i=1; i<V; ++i){ 
                for (int j=0; j<E; ++j){ 
                    int u = grafo.arestas.get(j).origem.nome; 
                    int v = grafo.arestas.get(j).destino.nome; 
                    int peso = grafo.arestas.get(j).peso; 
                    if((distancia[u]+peso) < distancia[v]) 
                        distancia[v] = distancia[u] + peso; 
                } 
            }          

        for (int j=0; j<E; ++j){ 
            int u = grafo.arestas.get(j).origem.nome; 
            int v = grafo.arestas.get(j).destino.nome; 
            int weight = grafo.arestas.get(j).peso; 
            if (distancia[u] != Integer.MAX_VALUE && 
                distancia[u]+weight < distancia[v]) 
              System.out.println("Grafo possui caminhos negativos"); 
        } 
        printArr(distancia, V); 
    } 

    void printArr(int dist[], int V){ 
        System.out.println("Vertice   Distancia da Origem"); 
        for (int i=0; i<V; ++i) 
            System.out.println(i+"\t\t"+dist[i]); 
    } 
    
    int[][] preencheMatriz(int dist[], int V, int linha){ 
        int[][] matriz = new int[V][V];
        for (int j=0; j<V; ++j){
            for (int i=0; i<V; ++i) {
                if(j==linha){
                matriz[linha][i] = dist[i];
                }
            }
        }
        return matriz;     
    } 

    @Override
    public void run() {
        
        String formato = "#0000";
        DecimalFormat d = new DecimalFormat(formato);
        
       long tempoInicio = System.currentTimeMillis();

        EscreverArquivo.getInstance().escreverArquivo(BellmanFordParaTodosOsPares(), String.valueOf(arquivoSaida), "BellmanFord/"); 
       
        long tempoFim = System.currentTimeMillis();
        long tempoTotal = tempoFim - tempoInicio;
        String escrita = d.format(arquivoSaida)+":"+String.valueOf(tempoTotal)+"\n";
        
        try {
            Files.write(Paths.get("C:/Users/vanes/Documents/Interdisciplinar/grafos/BellmanFord/tempo.txt"), escrita.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(Dijsktra.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
}
