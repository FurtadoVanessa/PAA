#include <stdio.h>
#include <stdlib.h>
#include <strings.h>

int main(){

    int quantidadeDeVertices=0;
    int quantidade;
    char vertice;
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
    
    for(int i=0; i<quantidadeDeVertices; i++){
        printf("Quantas arestas sao incidentes no vertice %c   ", vertices[i]);
        scanf("%d", &quantidade);
        fflush(stdin);
        for(int j=0; j<quantidade; j++){
            printf("Digite o vertice %d incidente a %c   ", j+1, vertices[i]);
            scanf("%c", &vertice);
            fflush(stdin);
            for(int k=0; k<quantidadeDeVertices; k++){
                if(vertices[k]==vertice){
                    matrizDeAjdacencia[i][k] = 1;
                    break;
                }
            }
        }
    }
    printf("Digite o vertice que deseja saber o grau:  ");
    scanf("%c", &vertice);
    quantidade=0;
    for(int i=0; i<quantidadeDeVertices; i++){
        if(vertices[i]==vertice){
            for(int j=0; j<quantidadeDeVertices; j++){
                if(matrizDeAjdacencia[i][j]==1){
                    quantidade++;
                }   
            }
            break;
        }
    }
    printf("O grau do vertice %c e %d", vertice, quantidade);

    for(int i=0; i<quantidadeDeVertices; i++){
        for(int j=0; j<quantidadeDeVertices; j++){
            printf("%d", matrizDeAjdacencia[i][j]);   
        }
    }
}