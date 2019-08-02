package interdisciplinar;

import java.util.ArrayList;
import java.util.Collections;

public class No {

    public int valor;
    public ArrayList<Integer> naoVisitados = new ArrayList<>();
    public int peso;
    public No pai;

    public No(int valor, No pai, int peso) {
        this.valor = valor;
        this.pai = pai;
        this.peso = pai.peso + peso;
    }

    public No(int valor, No pai) {
        this.valor = valor;
        this.pai = pai;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setNaoVisitados(ArrayList<Integer> lista) {

        naoVisitados.addAll(lista);
        
        for (int i = 0; i < naoVisitados.size(); i++) {
            if (naoVisitados.get(i) == this.valor) {
                naoVisitados.remove(i);
                i = naoVisitados.size();
            }
        }
    }

    public ArrayList<Integer> getNaoVisitado() {
        return this.naoVisitados;
    }

}
