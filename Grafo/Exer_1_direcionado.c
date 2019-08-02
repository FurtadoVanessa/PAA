#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct no{
	char valor[30];
	struct no *proxCel;
}Cel;

typedef struct noGrafo{
	char valor[30];
	Cel *proxCel;
	struct noGrafo *proxVer;
}ListaAdj;

typedef struct{
	ListaAdj *inicio;
}Lista;

void insereVertice (Lista *L, char valor[30]){

	ListaAdj *novo = (ListaAdj*) malloc(sizeof(ListaAdj));
	
	if(novo == NULL){
		printf("Sem memoria disponivel!\n");
		exit(1);
	}
	
	strncpy(novo->valor, valor, strlen(novo->valor));
	
	novo->proxCel = NULL;
	novo->proxVer = NULL;
	
	if (L->inicio == NULL){
		L->inicio = novo;
	}else{
		novo->proxVer = L->inicio;

		L->inicio = novo;
	}

}

void insereLigacoes (ListaAdj *tmp, char valor[30]){
	
	Cel *novo = (Cel*) malloc(sizeof(Cel));
	
	if(novo == NULL){
		printf("Sem memoria disponivel!\n");
		exit(1);
	}
	
	strncpy(novo->valor, valor, strlen(novo->valor));

	novo->proxCel = NULL;
	
	
	
	if (tmp->proxCel == NULL){
		tmp->proxCel = novo;
	}else{
		novo->proxCel = tmp->proxCel;
		tmp->proxCel = novo;
	}
	
}

void main (){
	
	int numVertice, i, m, n, contador;
	char valor[30];
	Cel *temp;
	ListaAdj *tmp;
	
	//cria Lista Adjacente
	Lista *L = (Lista*) malloc(sizeof(Lista));
	L->inicio = NULL;
	
	
	if (L == NULL){
		printf("Erro ao criar Lista adjacente!!\n");
		exit (0);
	}
	
	printf ("\t\tBem Vindo!\n\nPara calcular o grau de um grafo direcionado digite quantas vertices deseja inserir:\n");
	scanf ("%d", &numVertice);
	
	for (i=0;i<numVertice;i++){
		printf("Digite a vertice:\t");
		scanf ("%s", valor);
		insereVertice (L, valor);
	}
	fflush(stdin);
	system("cls");
		
	printf ("As Vertices criadas sao:\n");
	tmp = L->inicio;
	for (i=0;i<numVertice;i++){
		printf("Fila [%d] -> %s\n", i+1, tmp->valor);
		tmp = tmp->proxVer;
	}
	
	printf("\n");	//Adicionar ligacoes	
	tmp = L->inicio;		
	for (i=0;i<numVertice;i++){
		printf ("Diga quantas ligacoes o %s fara: ", tmp->valor);
		scanf ("%d", &m);
		for (n=0; n<m; n++){
			printf("Digite uma ligacao que sai de %s-> \t", tmp->valor);
			scanf ("%s", valor);
			insereLigacoes (tmp, valor);//////
		}		
		tmp = tmp->proxVer;
	}
	fflush(stdin);
	system("cls");

	tmp = L->inicio;
	temp = tmp->proxCel;
	for (i=0;i<numVertice;i++){
		printf("\nAs ligacoes de %s sao:\n", tmp->valor);
		while(temp != NULL){
			printf("%s\t", temp->valor);
			temp = temp->proxCel;
			fflush(stdin);
		}
		if (tmp->proxVer != NULL){
			tmp = tmp->proxVer;
			temp = tmp->proxCel;			
		}
	}
	do{
		contador=0;
		printf ("\nDigite uma vertice para saber o grau:\t");
		scanf ("%s", valor);
		fflush(stdin);
		printf("\n");
		tmp = L->inicio;
		temp = tmp->proxCel;
		for (i=0;i<numVertice;i++){
			if (strcmp(tmp->valor, valor) == 0){
				while(temp != NULL){
					contador++;
					temp = temp->proxCel;
				}	
			}
			if (tmp->proxVer != NULL){
				tmp = tmp->proxVer;
				temp = tmp->proxCel;			
			}
		}
		if (contador == 0){
			printf("\n** Nao encontrado **\n");
		}else{
			printf ("\nO grau para %s eh de %d", valor, contador);
		}
		printf ("\nDigite 1 para nova pesquisa\t", valor, contador);
		scanf ("%d", &n);		
	}while(n == 1);

	
}