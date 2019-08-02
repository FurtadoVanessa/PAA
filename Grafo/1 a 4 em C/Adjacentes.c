#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <time.h>

int main(){
    clock_t tInicio, tFim, tDecorrido;

    int quantidadeDeVertices=0;
    int quantidade;
    char vertice1, vertice2;
    printf("Digite a quantidade de vertices do grafo: ");
    scanf("%d", &quantidadeDeVertices);
    char vertices[100];
    int matrizDeAjdacencia[100][100];

    for(int i=0; i<quantidadeDeVertices; i++){
        for(int j=0; j<quantidadeDeVertices; j++){
            matrizDeAjdacencia[i][j]=0;   
        }
    }

    for(int i=0; i<quantidadeDeVertices; i++){
        fflush(stdin);
        printf("Digite o nome do vertice %d   ", i+1);
        scanf("%c", &vertices[i]);
    }
       
    printf("Digite a matriz de ajdacencia:  \n");
    for(int i=0; i<quantidadeDeVertices; i++){
        for(int j=0; j<quantidadeDeVertices; j++){
            scanf("%d", &matrizDeAjdacencia[i][j]);   
        }
    }
    fflush(stdin);
    printf("Digite o vertice 1 ");
    scanf("%c", &vertice1);
    fflush(stdin);
    printf("Digite o vertice 2 ");
    scanf("%c", &vertice2);

    tInicio = clock();
    for(int i=0; i<quantidadeDeVertices; i++){
        if(vertices[i]==vertice1){
            for(int j=0; j<quantidadeDeVertices; j++){
                if(vertices[j]==vertice2){
                    if((matrizDeAjdacencia[i][j]!=0)||(matrizDeAjdacencia[j][i]!=0)){
                        printf("Sao adjacentes\n");
                    }
                    else{
                        printf("Nao sao adjacentes\n");
                    }
                }   
            }
        }
    }
    tFim = clock();
    tDecorrido = ((tFim - tInicio) / (CLOCKS_PER_SEC)/1000);
    printf("Para %d vertices gastou %f milisegundos", quantidadeDeVertices, tDecorrido);
}