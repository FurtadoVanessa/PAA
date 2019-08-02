import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dijsktra implements Runnable {
    
    
    private Grafo g;
    private int origem = -1;
    private int arquivoSaida;
    HashMap<String, Integer> valores;

     public Dijsktra(Grafo g, int arquivoSaida){
        this.g = g;
        this.arquivoSaida = arquivoSaida;
    }
     
     public Dijsktra(Grafo g, int origem, int arquivoSaida){
        this.g = g;
        this.origem = origem;
        this.arquivoSaida = arquivoSaida;
        
        
    }
    
    
    
    int[][] dijsktraParaTodosPares(){
        Grafo grafo = g;
        ArrayList<Vertice> lista = new ArrayList<Vertice>();

        int[][] matrizAdjacencia = new int[grafo.vertices.size()][grafo.vertices.size()];

        for(int j=0; j<grafo.vertices.size(); j++){
            lista.clear();


            inicializarGrafo(grafo);
            int origem = grafo.vertices.get(j).nome;

            lista.add(encontrarVertice(grafo, grafo.vertices.get(origem)));


            grafo.vertices.get(origem).peso = 0;        

            for(int i=0; i<grafo.vertices.size(); i++){

                Vertice verticeOrigem = grafo.vertices.get(origem);

                for (Vertice vertice : verticeOrigem.adjacente) {

                    int pesoAresta = getPesoAresta(grafo, verticeOrigem, vertice);
                    int posicao = vertice.nome;

                    int novoPeso = pesoAresta + verticeOrigem.peso;

                    if(grafo.vertices.get(vertice.nome).peso > novoPeso ){
                        grafo.vertices.get(posicao).peso = novoPeso;
                        lista.add(encontrarVertice(grafo,vertice));
                        matrizAdjacencia[j][vertice.nome] = novoPeso;
                    }
                }
                lista.remove(0);

                if(lista.isEmpty()){
                    i=grafo.vertices.size();
                }
                origem = encontraVerticeMenorPeso(lista);

            }

        }
      //  printMatrizAdjacencia(matrizAdjacencia);
        return matrizAdjacencia;
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
   }
    
    
         
    int getPesoAresta(Grafo grafo, Vertice origem, Vertice destino){

        return grafo.matrizAdjacentes[origem.nome][destino.nome];
    }  
    
    void inicializarGrafo(Grafo grafo){
        for(int i=0; i<grafo.vertices.size(); i++){
            grafo.vertices.get(i).peso = Integer.MAX_VALUE-10000;
        }
    }
    
    int encontraVerticeMenorPeso(ArrayList<Vertice> lista){
        int menor=Integer.MAX_VALUE;
        int vertice=0;
        
        for(Vertice v : lista){
            if(v.peso<menor){
                menor = v.peso;
                vertice = v.nome;
            }
        }
        return vertice;
    }
        
    private Vertice encontrarVertice(Grafo grafo, Vertice vertice){
        return grafo.vertices.get(vertice.nome);
    }

    private void disjktraSimples(){
        Grafo grafo = g;
        inicializarGrafo(g);
        ArrayList<Vertice> lista = new ArrayList<Vertice>();
        ArrayList<Vertice> visitados = new ArrayList<Vertice>();
        lista.add(encontrarVertice(grafo, grafo.vertices.get(origem)));
        visitados.add(encontrarVertice(grafo, grafo.vertices.get(origem)));
        grafo.vertices.get(origem).peso = 0;        
        
//        for(int i=0; i<grafo.vertices.size(); i++){
//            Vertice verticeOrigem = grafo.vertices.get(origem);
//            for (Vertice vertice : verticeOrigem.adjacente) {
//                Vertice original = encontrarVertice(grafo, vertice);
//                Aresta a = getAresta(grafo, verticeOrigem, vertice);
//                if(original.peso > (a.peso + verticeOrigem.peso) ){
//                    original.peso = a.peso + verticeOrigem.peso;
//                    lista.add(encontrarVertice(grafo,original));
//                    visitados.add(encontrarVertice(grafo,original));
//                }
//            }
//            lista.remove(grafo.vertices.get(origem));
//            origem = encontraVerticeMenorPeso(lista);
//        }
        
        for(Vertice v : visitados){
            System.out.println(v.nome+" -> "+v.peso);
        }
    }
    
    

    public void run() {
        String formato = "#0000";
        DecimalFormat d = new DecimalFormat(formato);
        
       long tempoInicio = System.currentTimeMillis();
        
        if(origem == -1){
          //  System.out.println("Todos os paress");
            EscreverArquivo.getInstance().escreverArquivo(dijsktraParaTodosPares(), String.valueOf(arquivoSaida)); 
        }else{
          //  System.out.println("Simples");
             disjktraSimples();
        }
        
        
           long tempoFim = System.currentTimeMillis();
        long tempoTotal = tempoFim - tempoInicio;
        String escrita = d.format(arquivoSaida)+":"+String.valueOf(tempoTotal)+"\n";
        
        try {
            Files.write(Paths.get("C:/Users/Cristiano Fagundes/Documents/500 Grafos/valores.txt"), escrita.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(Dijsktra.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
}
