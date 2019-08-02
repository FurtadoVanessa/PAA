package mochilagulosa;

public class MochilaGulosa {

    //mochila fracionada
    //tem que adicionar os de melhor valor, mesmo que pequenos
    
    
    public int pesoMaximo;
    public int pesos[];
    public int valores[];

    public MochilaGulosa(int pesoM, int[] p, int[] valor) {

        pesoMaximo = pesoM;
        pesos = p;
        valores = valor;
         long tempoInicio = System.currentTimeMillis();

        quickSort(relacao(), 0, pesos.length - 1);
        int peso = pesoM;
        int[] saida = new int[pesos.length];

        for (int i = pesos.length - 1; i > 0; i--) {
            if (pesos[i] <= peso) {
                saida[i] = valores[i];
                peso -= pesos[i];
            } else {
                saida[i] = 0;
            }
        }
        int soma = 0;
        System.out.println("Foi adicionado na mochila os itens de valor: ");
        for (int i = 0; i < saida.length; i++) {
            if(saida[i]!=0){
                System.out.print(" "+saida[i]);
                soma+=pesos[i];
            }
        }
        System.out.println("");
        System.out.println("O peso total deu "+soma);
        
        long tempoFim = System.currentTimeMillis();
        long tempoTotal = tempoFim - tempoInicio;
        System.out.println("Para "+p.length+" itens o tempo foi: "+tempoTotal);

    }

    int[] relacao() {
        int[] vetor = new int[pesos.length];
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = valores[i] / pesos[i];
        }
        return vetor;
    }

    void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(vetor, inicio, fim);
            quickSort(vetor, inicio, posicaoPivo - 1);
            quickSort(vetor, posicaoPivo + 1, fim);
        }
    }

    int separar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[inicio];
        int pivoP = pesos[inicio];
        int pivoV = valores[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (vetor[i] <= pivo) {
                i++;
            } else if (pivo < vetor[f]) {
                f--;
            } else {
                int troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                int auxiliarPeso = pesos[i];
                pesos[i] = pesos[f];
                pesos[f] = auxiliarPeso;
                int auxiliarValor = valores[i];
                valores[i] = valores[f];
                valores[f] = auxiliarValor;
                i++;
                f--;
            }
        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        pesos[inicio] = pesos[f];
        pesos[f] = pivoP;
        valores[inicio] = valores[f];
        valores[f] = pivoV;
        return f;
    }
}
