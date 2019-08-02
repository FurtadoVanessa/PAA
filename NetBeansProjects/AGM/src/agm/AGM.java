/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class AGM {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        String formato = "#0000";
        DecimalFormat d = new DecimalFormat(formato);
        int contador = 0;
        int contarColuna = 0;
        System.out.println("Digite o numero de vertices que deseja no grafo");
        int grafo = sc.nextInt();
        try {
            FileReader arq = new FileReader("C:/Users/vanes/Documents/Interdisciplinar/grafos/" + d.format(grafo) + ".txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            String split[] = linha.split(" ");
            int[][] matrizAdjacentes = new int[split.length][split.length];
            int cont = 0;

            for (int i = 0; i < split.length; i++) {
                int valor = Integer.parseInt(split[i]);
                matrizAdjacentes[contador][cont] = valor;
                cont++;
            }
            contador++;

            linha = lerArq.readLine();
            while (linha != null) {
                cont=0;
                split = linha.split(" ");
                for (int i = 0; i < split.length; i++) {
                    int valor = Integer.parseInt(split[i]);
                    matrizAdjacentes[contador][cont] = valor;
                    cont++;
                }
                contador++;
                linha = lerArq.readLine();
            }
            //Prim p = new Prim(matrizAdjacentes);
            Kruskal k = new Kruskal(matrizAdjacentes);
            //FordFulkerson f = new FordFulkerson(matrizAdjacentes, 0, grafo-1);
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

    }
}
