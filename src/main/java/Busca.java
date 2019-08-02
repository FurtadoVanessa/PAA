import java.util.ArrayList;
import java.util.Scanner;

public class Busca {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Grafo grafo = new Grafo();
        String origem, destino, opcao;
        ArrayList<Vertice> caminho = new ArrayList<Vertice>();
        String vertices[] = {"A", "B", "C", "D", "E", "F"};
        String arestas[] = {"A", "B",
                "A", "C",
                "B", "D",
                "C", "B",
                "C", "E",
                "D", "F",
                "D", "C",
                "E", "B",
                "E", "D",
                "E", "F"};

        for (String vertice : vertices) {
            grafo.addVertice(new Vertice(vertice, null));
        }

        for (int i = 0; i < arestas.length; i = i + 2) {
            Vertice verticeOrigem = encontrarVertice(grafo, arestas[i]);
            verticeOrigem.addAdj(encontrarVertice(grafo, arestas[i + 1]));
        }

        System.out.println("Digite qual vertice sera a raiz:");
        origem = sc.nextLine();
        System.out.println("Digite qual vertice sera o destino:");
        destino = sc.nextLine();
        System.out.println("Digite qual opcao: Largura (L) x Profundidade (P)");
        opcao = sc.nextLine();
        grafo.mostraGrafo();
        Vertice fim = new Vertice("", null);

        if(opcao.compareToIgnoreCase("l")==0){
             fim = BFS(grafo, origem, destino);
        } else if(opcao.compareToIgnoreCase("p")==0) {
             fim = DFS(grafo, origem, destino);
        }

        while (fim != null) {
            caminho.add(fim);
            fim = fim.pai;
        }

        for(int i = caminho.size()-1; i>=0; i--){
            System.out.print(caminho.get(i).nome+ " -> ");
        }
    }

    static Vertice BFS(Grafo grafo, String raiz, String destino) {

        Vertice raizLocal = encontrarVertice(grafo, raiz);
        Vertice vRaiz = new Vertice(raizLocal, null);

        ArrayList<Vertice> filhos = new ArrayList<Vertice>();
        while (true) {

            for (Vertice adj : vRaiz.adjacente) {

                Vertice v = new Vertice(adj, vRaiz);

                filhos.add(v);

                if (v.nome.equals(destino)) {
                    return v;
                }
            }
            vRaiz = filhos.remove(0);
        }
    }


    static Vertice DFS(Grafo grafo, String raiz, String destino) {
        Vertice raizLocal = encontrarVertice(grafo, raiz);
        Vertice vRaiz = new Vertice(raizLocal, null);

        return expande(vRaiz, destino);
    }


    static Vertice expande(Vertice vertice, String destino) {
        if(vertice.nome.equals(destino)){
            return  vertice;
        }

        ArrayList<Vertice> filhos = new ArrayList<Vertice>();
        for (Vertice v : vertice.adjacente) {
            Vertice novo = new Vertice(v, vertice);
            filhos.add(novo);
            if(confere(novo)) {
                return  expande(novo, destino);
            }
        }
    return null;

    }

    static boolean confere(Vertice v){
        String valor = v.nome;
        v = v.pai;
        while(v !=null){
            if(valor.equals(v.nome)){
                return false;
            }
            v = v.pai;
        }

        return true;
    }


    static Vertice encontrarVertice(Grafo grafo, String vertice) {
        for (Vertice v : grafo.vertices) {
            if (v.nome.compareToIgnoreCase(vertice) == 0) {
                return v;
            }
        }
        return null;
    }


}
