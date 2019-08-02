#include <stdio.h>
#include <stdlib.h>
#include <strings.h>

typedef struct Vertice{
    char nome;
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

void inserirVertice(ListaLista *lista, char vertice){
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

Vertice *buscaVertice(ListaLista *lista, char vertice){
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
            printf("%c -> ", v->nome);
            v = v->proximo;
        }
        printf("\n");
        l = l->proximo;
    }
}

void inserirAdjacencia(ListaLista *lista, char verticeIncidente, char verticeAdjacente){
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
    printf("Inserido %c -> %c\n", incidente->nome, adjacente->nome);
}