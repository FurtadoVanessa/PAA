
package conta;

public class Principal{

	public static void main(String[] args){

		Banco banco = new Banco();
		Conta minhaConta = new Conta();
		banco.listaContas.add(minhaConta);
		minhaConta.setDono("Vanessa");
		minhaConta.depositar(5000);
		System.out.println("O nome do cliente eh "+minhaConta.getDono()+" e o saldo eh "+minhaConta.getSaldo());
		if(minhaConta.sacarValidando(6000))
			System.out.println("Foi realizado o saque e seu novo saldo eh "+minhaConta.getSaldo());
		else
			System.out.println("Não foi realizado o saque. Saldo insuficiente");
		
		Poupanca p = new Poupanca();
		banco.listaContas.add(p);
		p.setDono("Joao");
		p.setRendimento(0.05);
		p.depositar(100);
                p.sacar(50);
		System.out.println("O rendimento foi de"+p.calcularRendimento());
                ContaCorrente conta2 = new ContaCorrente();
                banco.listaContas.add(conta2);
                conta2.setDono("Maria");
                conta2.depositar(100);
                conta2.setJuros(0.01);
		banco.atualizaBanco(0.10);
                System.out.println("O valor total no banco eh de "+banco.saldoTotal);
	}

}
//protected - acessavel pelo pai e suas filhas
//private - acessavel apenas pela classe
//public - publico a todas as classes que possuem acesso a classe