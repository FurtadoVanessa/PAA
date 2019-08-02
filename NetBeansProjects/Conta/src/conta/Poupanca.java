/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conta;

public class Poupanca extends Conta{

private double rendimento;

public double calcularRendimento(){
	return this.getSaldo()*this.rendimento;
}

public double getRendimento(){
	return this.rendimento;
}

public void setRendimento(double rendimento){
	this.rendimento = rendimento;
}

public void atualiza(double taxa){
	this.saldo += this.saldo*3*taxa; 

}

}
