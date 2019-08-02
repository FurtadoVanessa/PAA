package conta;

import java.util.ArrayList;

class Banco{

    public double saldoTotal;
    public ArrayList<Conta> listaContas;

    public double getSaldoTotal(){
    return this.saldoTotal;
}

    public void atualizaBanco(double taxa){
        this.saldoTotal = 0;
        for(Conta conta : this.listaContas){
            System.out.println("Saldo anterior: "+conta.getSaldo());
            conta.atualiza(taxa);
            System.out.println("Saldo atual: "+conta.getSaldo());
            this.saldoTotal+=conta.getSaldo();
        }
        System.out.println("O saldo total do banco eh "+this.saldoTotal);

    }

}