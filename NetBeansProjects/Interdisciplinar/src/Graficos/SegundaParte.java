package Graficos;

import java.util.ArrayList;

public class SegundaParte {

    public SegundaParte(){
    
        ArrayList<int[]> pontosForcaBruta = new ArrayList<>();
        pontosForcaBruta = GerarGrafico.leituraArquivo("ForcaBruta/");
        ArrayList<int[]> pontosForcaBrutaBacktracking = new ArrayList<>();
        pontosForcaBrutaBacktracking = GerarGrafico.leituraArquivo("ForcaBrutaBacktracking/");
        ArrayList<int[]> pontosProgramacaoDinamica = new ArrayList<>();
        pontosProgramacaoDinamica = GerarGrafico.leituraArquivo("ProgramacaoDinamica/");

        ArrayList<int[]> listaCombinacoes = new ArrayList<>();
        for(int[] i : pontosForcaBruta){
            int[] a = {i[0], fatorial(i[0])};
            listaCombinacoes.add(a);
        }
    }
    
    
    
    
    private int fatorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return (n * fatorial(n - 1));
        }

    }

}
