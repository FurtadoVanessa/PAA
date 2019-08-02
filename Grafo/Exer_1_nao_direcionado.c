#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void main (){
	int numVertice, i, j, **matriz, grau = 0, n, m;
	char nome[30], *matrizChar[30];
	
	printf ("\tBem Vindo!\n\nPara calcular o grau de um grafo nao direcionado digite quantas vertices deseja inserir:\n");
	scanf ("%d", &numVertice);
	fflush(stdin);
	matriz = (int**)malloc(numVertice * sizeof(int*)); //Aloca um Vetor de Ponteiros
 
	for (i = 0; i < numVertice; i++){ //Percorre as linhas do Vetor de Ponteiros
       matriz[i] = (int*) malloc(numVertice * sizeof(int)); //Aloca um Vetor de Inteiros para cada posi��o do Vetor de Ponteiros.
       matrizChar[i] = (char*) malloc(numVertice * sizeof(char));
       for (j = 0; j < numVertice; j++){ //Percorre o Vetor de Inteiros atual.
            matriz[i][j] = 0; //Inicializa com 0.
       }
	}
	j=1;
	for (i=0;i<numVertice;i++){//salva os nomes do grafo
		printf ("Digite o elemento %d ->", j);
		scanf ("%s", matrizChar[i]);
		j++;
	}
	fflush(stdin);
	system("cls");
	j=1;
	printf ("Vertice adicionadas:\n");
	for (i=0;i<numVertice;i++){
		printf ("%d -> %s\n", j, matrizChar[i]);
		j++;
	}
	printf("\n");
	for (i=0;i<numVertice;i++){
		printf ("Diga quantas ligacoes o %s fara: ", matrizChar[i]);
		scanf ("%d", &m);
		for (n=0; n<m; n++){
			printf ("Diga uma ligacao do %s -> ", matrizChar[i]);
			scanf("%s", nome);
			for (j=0; j<numVertice; j++){
				if (strcmp(nome, matrizChar[j]) == 0){
					matriz [i][j] = 1;
				}
			}		
		}
	}
	
	printf ("\nPara saber o grau, digite o vertice:\t");
	scanf("%s", nome);
	fflush(stdin);
	for (j=0; j<numVertice; j++){
		if (strcmp(nome, matrizChar[j]) == 0){
			for (i=0;i<numVertice;i++){
				grau = grau + matriz [i][j];
			}		
		}
	}
	printf ("\nO grau de %s eh %d\n\nMatriz de adjacencias:\n", nome, grau);
	
	for (i=0; i<numVertice; i++) {
		for (j=0; j<numVertice; j++) {
			printf("[%d][%d]= %d \t",i,j,matriz[i][j]);
		}
		printf("\n");
	}
	
}


