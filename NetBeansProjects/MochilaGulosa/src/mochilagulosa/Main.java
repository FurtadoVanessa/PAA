/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mochilagulosa;

/**
 *
 * @author vanes
 */
public class Main {
    
    public static void main(String args[]){
    
        int pesoMaximo = 6;
        int pesos[] = {2, 3, 1, 5};
        int valores[] = {10, 20, 50, 15};
        
            //MochilaGulosa m = new MochilaGulosa(pesoMaximo, pesos, valores);
            MochilaForcaBruta c = new MochilaForcaBruta(pesoMaximo, pesos, valores);
    
    
    
    }
    
}
