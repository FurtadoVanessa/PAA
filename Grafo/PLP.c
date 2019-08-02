#include <stdio.h>
#include <stdlib.h>

//inicio de bloco
int soma(int a, int b){
    return a+b;
    //operacao
}
//fim de bloco

//inicio de bloco
void subtracao(int *a, int *b){
    *b = *b - *a;
    //atribuicao
}
//fim de bloco

int main(){

    int a, b, c;
    a=10;
    b=20;
    //atribuicao

    c = soma(a, b);
    //atribuicao e controle
    printf("%d\n", c);
    subtracao(&a, &b);
    //controle
    printf("%d", b);
}