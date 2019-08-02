/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conta;

class Conta{

protected int numero;
protected String dono;
protected double saldo;
protected double limite;

protected void sacar(double valor){
	this.saldo-=valor;
	
}

public void depositar(double valor){
	this.saldo+=valor;
}

public void atualiza(double taxa){
	this.saldo += this.saldo*taxa; 

}

public boolean sacarValidando(double valor){
	if(valor<=this.saldo){
		this.sacar(valor);
		return true;
	}else{
		return false;
	}
}

public double getSaldo(){
	return this.saldo;
}

public double getLimite(){
	return this.limite;
}

public String getDono(){
	return this.dono;
}

public void setDono(String dono){
	this.dono = dono;
}


}