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

int removeAresta(ListaLista *lista, char verticeIncidente, char verticeAdjacente){
    Vertice *incidente;
    incidente = buscaVertice(lista, verticeIncidente);
    while(incidente != NULL){
        if(incidente->proximo->nome == verticeAdjacente){
            incidente->proximo = incidente->proximo->proximo;
            return 1;
        }else{
            incidente = incidente->proximo;
        }
    }
    printf("Ajdacente nao encontrado\n");
    return 0;
}

int removeVertice(ListaLista *lista, char vertice){
    listaVertices *l;
    l = lista->inicio;
    if(lista->inicio->vertice->nome == vertice){
        lista->inicio = l->proximo;
        return 1;
    }else{
        while(l != NULL){
            if(l->proximo->vertice->nome == vertice){
                if(lista->fim->vertice == l->proximo->vertice){
                    lista->fim = l;
                    l->proximo = NULL;
                    printf("Vertice %c removido\n", vertice);
                    return 1;
                }else{
                    l->proximo = l->proximo->proximo;
                    printf("Vertice %c removido\n", vertice);
                    return 1;
                }
            }else{
                l = l->proximo;
            }
        }
        printf("Vertice %c removido\n", vertice);
        return 1;
    }
    printf("Vertice nao encontrado\n");
    return 0;
}

void menu(){
    printf("Digite 1 para inserir vertice\n");
    printf("Digite 2 para inserir aresta\n");
    printf("Digite 3 para remover vertice\n");
    printf("Digite 4 para remover aresta\n");
    printf("Digite 5 para imprimir lista de adjacencia\n");
    printf("Digite 0 para sair\n");
}


int main(){

    ListaLista lista;
    char vertice, aresta;
    int opcao=1;

    while(opcao!=0){
        menu();
        scanf("%d", &opcao);
        fflush(stdin);
        system("cls");
        switch(opcao){
            case 1:
                printf("Digite o nome do vertice a ser inserido: ");
                scanf("%c", &vertice);
                inserirVertice(&lista, vertice);
            break;
            case 2:
                printf("Digite o nome do vertice incidente: ");
                scanf("%c", &vertice);
                fflush(stdin);
                printf("Digite o nome do vertice adjacente: ");
                scanf("%c", &aresta);
                inserirAdjacencia(&lista, vertice, aresta);
            break;
            case 3:
                printf("Digite o nome do vertice a ser removido: ");
                scanf("%c", &vertice);
                removeVertice(&lista, vertice);
            break;
            case 4:
                printf("Digite o nome do vertice incidente: ");
                scanf("%c", &vertice);
                fflush(stdin);
                printf("Digite o nome do vertice adjacente: ");
                scanf("%c", &aresta);
                removeAresta(&lista, vertice, aresta);
            break;
            case 5:
                imprimeLista(&lista);
            break;
            case 0:
                exit(1);
            break;
        }
    }
}

