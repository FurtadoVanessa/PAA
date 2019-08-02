import java.util.Arrays;

public class BellmanFord {
    
//   int[][] BellmanFordParaTodosOsPares(Grafo grafo){ 
//        int V = grafo.vertices.size();
//        int E = grafo.arestas.size(); 
//        int[][] matrizAdjacencia = new int[V][V];
////        for(int k=0; k<V; k++){
//            inicializarGrafo(grafo);
//
//            grafo.vertices.get(0).peso = 0;
//            for(int k=0; k<V; k++){
//                System.out.println(k+" vale "+grafo.vertices.get(k).peso);
//            }
//            
//            for (int i=0; i<V-1; i++){ 
//                for (int j=0; j<E; j++){ 
////                    int u = grafo.arestas.get(j).origem.nome; 
////                    int v = grafo.arestas.get(j).destino.nome; 
////                    int pesoAresta = grafo.arestas.get(j).peso; 
////                    int pesoOrigem = grafo.arestas.get(j).origem.peso;
////                    int pesoDestino = grafo.arestas.get(j).destino.peso;
////                     System.out.println("Vertice De: "+grafo.arestas.get(j).origem.nome+" \tOrig: "+grafo.arestas.get(j).destino.nome);
////                     System.out.println("Analise de: "+pesoDestino+" \tPara: "+pesoOrigem+pesoAresta);
//System.out.println("J vale: "+j);
//System.out.println(grafo.arestas.get(j).destino.nome+" -> "+grafo.arestas.get(j).origem.nome);
//System.out.println(grafo.arestas.get(j).destino.peso+" -> "+grafo.arestas.get(j).origem.peso+ " + " + grafo.arestas.get(j).peso);
////                    if(grafo.arestas.get(j).destino.peso > (grafo.arestas.get(j).origem.peso + grafo.arestas.get(j).peso)) {
////                        System.out.println("Mudou de: "+grafo.arestas.get(j).destino.peso+" \tPara: "+grafo.arestas.get(j).origem.peso+pesoAresta);
////                        grafo.arestas.get(j).destino.peso=pesoOrigem+pesoAresta; 
////                        matrizAdjacencia[u][v] = pesoOrigem+pesoAresta;
//                    
////                    }
//                }             } 
// //                           System.out.println("Depois da iteracao "+k);
//                //printMatrizAdjacencia(matrizAdjacencia);
////        }
//
//        for (int j=0; j<E; ++j){ 
//            int u = grafo.arestas.get(j).origem.nome; 
//            int v = grafo.arestas.get(j).destino.nome; 
//            int peso = grafo.arestas.get(j).peso; 
//            if (grafo.arestas.get(j).origem.peso != Integer.MAX_VALUE && 
//                grafo.arestas.get(j).origem.peso+peso < grafo.arestas.get(j).destino.peso) 
//              System.out.println("Grafo possui caminhos negativos"); 
//        } 
//        printMatrizAdjacencia(matrizAdjacencia);
//        return matrizAdjacencia;
//   }
//   
   int[][] BellmanFordParaTodosOsPares(Grafo grafo){
   
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
            //printArr(distancia, V);
            for (int j=0; j<V; ++j){
                for (int i=0; i<V; ++i) {
                    if(j==k)
                matriz[j][i] = distancia[i];
                }
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
   
    void BellmanFord(Grafo grafo,int origem){ 
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
    
}
