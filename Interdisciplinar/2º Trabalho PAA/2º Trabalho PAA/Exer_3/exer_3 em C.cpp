#include <stdio.h>
#include <string.h>
#include <algorithm>
#define MAX 100
 
using namespace std;
 
int main()
{
 
    char X [MAX];
    char Y [MAX];
    char temp[MAX];
    char sol[MAX];
    int m;
    int n;
    int maior, cont;
    int index;
 	printf ("Entre com as duas seguencias:\n");
    scanf("%s %s",X,Y);
 
    m = strlen(X);
    n = strlen(Y);
 
    int i,j;
    maior = 0;
 
    for(i = 0; i < 1<<m ; i++){
        index = 0;
        for(j=0; j<m; j++){
            if( (i & (1<<j)) != 0 ){
              temp[index++] = X[j];
            }
        }
        temp[index] = '\0';
        printf("%s\n",temp);
 
        cont = 0;
        for(j=0;j<n && temp[cont]!='\0';j++){
          if(Y[j] == temp[cont]){
            cont++;
          }
        }
 
        if(temp[cont]=='\0' && cont > maior){
          maior = cont;
          strcpy(sol,temp);
        }
 
    }
 
 
    printf("Existem %d subsequencia em comum \n",maior);
    printf("Maior subsequencia = %s\n",sol);
 
 
 
    return 0;
}
