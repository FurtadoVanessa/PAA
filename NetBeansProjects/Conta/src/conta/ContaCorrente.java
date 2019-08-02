package conta;

public class ContaCorrente extends Conta{

private double juros;
private double rendimento;

public boolean sacarValidando(double valor){
	if(valor<=(this.getSaldo()+this.juros+this.getLimite())){
		this.sacar(valor);
		this.sacar(juros);
		return true;
	}else{
		return false;
	}
}

public void depositar(double valor){
	this.saldo+=valor - 0.10;
}

public double getJuros(){
	return this.juros;
}

public void setJuros(double juros){
	this.juros = juros;
}

public void atualiza(double taxa){
	this.saldo += this.saldo*2*taxa; 

}

}