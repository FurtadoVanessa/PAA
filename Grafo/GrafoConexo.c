#include <stdio.h>
#include <stdlib.h>
#include <strings.h>

typedef struct Vertice{
    int nome;
    struct Vertice *proximo;
}Vertice;

typedef struct listaVertices{
    Vertice *vertice;
    struct listaVertices *proximo;
}listaVertices;

typedef struct ListaLista{
    listaVertices *inicio;
    listaVertices *fim;
}ListaLista;

void inserirVertice(ListaLista *lista, int vertice){
    Vertice *vertices = (Vertice*) malloc(sizeof(Vertice));
    vertices->nome = vertice;
    vertices->proximo = NULL;
    listaVertices *listaV = (listaVertices*) malloc(sizeof(listaVertices));
    listaV->vertice = vertices;
    listaV->proximo = NULL;
    if(lista->inicio == NULL){
        lista->inicio = listaV;
        lista->fim = listaV;
    }else{
        lista->fim->proximo = listaV;
        lista->fim = listaV;
    }

}

Vertice *buscaVertice(ListaLista *lista, int vertice){
    listaVertices *l;
    l = lista->inicio;
    while(l != NULL){
        if(l->vertice->nome == vertice){
            return l->vertice;
        }else{

            l = l->proximo;
        }
    }
    printf("Vertice nao encontrado\n");
    return 0;

}

void imprimeLista(ListaLista *lista){
    listaVertices *l;
    Vertice *v;
    l = lista->inicio;
    while(l != NULL){
        v = l->vertice;
        while(v != NULL){
            printf("%d -> ", v->nome);
            v = v->proximo;
        }
        printf("\n");
        l = l->proximo;
    }
}

void inserirAdjacencia(ListaLista *lista, int verticeIncidente, int verticeAdjacente){
    Vertice *incidente;
    Vertice *adjacente;    
    adjacente = (Vertice*) malloc(sizeof(Vertice));
    adjacente->nome = verticeAdjacente;
    adjacente->proximo = NULL;
    incidente = buscaVertice(lista, verticeIncidente);
    while(incidente->proximo != NULL){
        incidente = incidente->proximo;
    }
    incidente->proximo = adjacente;
    printf("Inserido %d -> %d\n", incidente->nome, adjacente->nome);
}

int main(){

    int quantidadeDeVertices=0;
    int somaLinha=0, somaColuna=0;
    int vertice;
    printf("Digite a quantidade de vertices do grafo: ");
    scanf("%d", &quantidadeDeVertices);
    int matrizDeAjdacencia[100][100];
    ListaLista grafo;

    printf("Digite a matriz de ajdacencia:  \n");
    for(int i=0; i<quantidadeDeVertices; i++){
        for(int j=0; j<quantidadeDeVertices; j++){
            scanf("%d", &matrizDeAjdacencia[i][j]);   
        }
    }

    for(int i=0; i<quantidadeDeVertices; i++){
        for(int j=0; j<quantidadeDeVertices; j++){
            printf("%d\t", matrizDeAjdacencia[i][j]);   
        }
        printf("\n");
    }

    for(int i=0; i<quantidadeDeVertices; i++){
        inserirVertice(&grafo, i);
        for(int j=0; j<quantidadeDeVertices; j++){
            if(matrizDeAjdacencia[i][j]==1)
                inserirAdjacencia(&grafo, i, j);
        }
    }

    imprimeLista(&grafo);

    for(int i=0; i<quantidadeDeVertices; i++){
        Vertice v;
        
    
    }


}