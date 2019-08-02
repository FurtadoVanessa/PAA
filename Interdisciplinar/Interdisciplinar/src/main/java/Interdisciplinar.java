import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Interdisciplinar {
    
    
    EscreverArquivo escrever = EscreverArquivo.getInstance();
    
    public static void main(String[] args) throws IOException {
       
        int[][] matrizdeAdjacencia;
        mostrarHora();
       // System.out.println("Tempo de inicio: "+String.valueOf(System.currentTimeMillis()));
    for(int i=3; i<503; i++){
        //long tempoInicio = System.currentTimeMillis();
        while(Thread.activeCount() > 6){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Interdisciplinar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Thread " +i);
        Grafo grafo = EscreverArquivo.getInstance().leituraArquivo(i);

        Dijsktra d = new Dijsktra(grafo,i);
        
        Thread t = new Thread(d);
        t.start();
        
        }
        mostrarHora();
        //System.out.println("Tempo de fim: "+String.valueOf(System.currentTimeMillis()));
    }

    static void mostrarHora(){
    long tempo = System.currentTimeMillis();
    long segundos = (tempo / 1000 ) % 60;      // se não precisar de segundos, basta remover esta linha.
    long minutos  = ( tempo / 60000 ) % 60;     // 60000   = 60 * 1000
    long horas    = tempo / 3600000;            // 3600000 = 60 * 60 * 1000
    System.out.println( String.format( "%03d:%02d:%02d", horas, minutos, segundos ) );
    }
    
    


}
