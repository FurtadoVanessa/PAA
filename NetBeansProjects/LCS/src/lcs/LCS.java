package lcs;

import java.util.Scanner;

public class LCS {
    
    private static int[][] matriz;
    private static char[][] caminho;
    

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o primeiro DNA");
        String s1 = sc.nextLine();
        System.out.println("Digite o segundo DNA");
        String s2 = sc.nextLine();
        LCS(s1, s2);
        int i=s1.length()-1;
        int j=s2.length()-1;
        System.out.println("Resposta eh: ");
        while((i!=0)&&(j!=0)){
            if(caminho[i][j]=='d'){
                System.out.print(s1.charAt(matriz[i][j]));
                i--;
                j--;
            }else if(caminho[i][j]=='s'){
                i--;
            }else if(caminho[i][j]=='l'){
                j--;
            }
        }
        sc.close();
    }
    
    private static void LCS(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        caminho = new char[m][n];
        matriz = new int[m][n];
        for(int i=1; i<m; i++){
            matriz[i][0] = 0;
        }
        for(int i=0; i<n; i++){
            matriz[0][i] = 0;
        }
        
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(s1.charAt(i)==s2.charAt(j)){
                    matriz[i][j] = matriz[i-1][j-1]+1;
                    caminho[i][j] = 'd';
                }else if(matriz[i-1][j]>=matriz[i][j-1]){
                    matriz[i][j] = matriz[i-1][j];
                    caminho[i][j] = 's';
                }else{
                    matriz[i][j] = matriz[i][j-1];
                    caminho[i][j] = 'l';
                }
            }
        }
    }
    
}
