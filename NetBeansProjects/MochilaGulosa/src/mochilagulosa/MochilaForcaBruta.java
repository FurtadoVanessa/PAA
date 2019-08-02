package mochilagulosa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class MochilaForcaBruta {

    private int pesoMaximo;
    private int pesos[];
    private int valores[];
    private List<SortedSet<Comparable>> allCombList;
    private ArrayList<int[]> combinacoes = new ArrayList<>();
    private int resultadosPeso[];
    private int resultadosValores[];
    private int melhor;

    public MochilaForcaBruta(int pesoM, int[] p, int[] valor) {

        this.pesoMaximo = pesoM;
        this.pesos = p;
        this.valores = valor;
      
        long tempoInicio = System.currentTimeMillis();
        
        gerarCombinacoes();
        for (SortedSet<Comparable> antes : allCombList) {
            int[] c = new int[antes.size()];
            int i = 0;
            while (!antes.isEmpty()) {
                c[i] = Integer.parseInt(antes.first().toString());
                antes.remove(antes.first());
                i++;
            }
            combinacoes.add(c);
        }
        
        this.resultadosPeso = new int[combinacoes.size()];
        this.resultadosValores = new int[combinacoes.size()];
        int x=0;
        for (int[] a : combinacoes) {
            int somaPeso=0;
            int somaValor=0;
            for (int i : a) {
                somaPeso+=pesos[i];
                somaValor+=valores[i];                
            }
            resultadosPeso[x] = somaPeso;
            resultadosValores[x] = somaValor;
            x++;
        }
        int peso=0;
        int v=0;
        for(int i=0; i<resultadosPeso.length; i++){
            if((resultadosPeso[i]<=pesoMaximo)&&(resultadosValores[i]>v)){
                peso = resultadosPeso[i];
                v = resultadosValores[i];
                melhor = i;
            }
        }
        
        System.out.println("A melhor combinacao eh com os valores "+Arrays.toString(combinacoes.get(melhor))+" com peso total "+resultadosPeso[melhor]+" e valor "+resultadosValores[melhor]);
        
        long tempoFim = System.currentTimeMillis();
        long tempoTotal = tempoFim - tempoInicio;
        System.out.println("Para "+p.length+" itens o tempo foi: "+tempoTotal);

    }

    private void gerarCombinacoes() {

        int[] status = new int[pesos.length];
        for (int i = 0; i < pesos.length; i++) {
            status[i] = i;
        }
        allCombList = new ArrayList<SortedSet<Comparable>>();
        for (int nstatus : status) {
            allCombList.add(new TreeSet<Comparable>(Arrays.asList(nstatus)));
        }
        for (int nivel = 1; nivel < status.length; nivel++) {
            List<SortedSet<Comparable>> statusAntes = new ArrayList<SortedSet<Comparable>>(allCombList);
            for (Set<Comparable> antes : statusAntes) {
                SortedSet<Comparable> novo = new TreeSet<Comparable>(antes);
                novo.add(status[nivel]);
                if (!allCombList.contains(novo)) {
                    allCombList.add(novo);
                }
            }
        }
        Collections.sort(allCombList, new Comparator<SortedSet<Comparable>>() {
            @Override
            public int compare(SortedSet<Comparable> o1, SortedSet<Comparable> o2) {
                int sizeComp = o1.size() - o2.size();
                if (sizeComp == 0) {
                    Iterator<Comparable> o1iIterator = o1.iterator();
                    Iterator<Comparable> o2iIterator = o2.iterator();
                    while (sizeComp == 0 && o1iIterator.hasNext()) {
                        sizeComp = o1iIterator.next().compareTo(o2iIterator.next());
                    }
                }
                return sizeComp;
            }
        });
    }
}
