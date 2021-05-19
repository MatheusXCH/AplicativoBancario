import java.math.BigDecimal;

public abstract class Conta implements OperacoesBancarias{
    protected String numeroConta = "";
    protected BigDecimal saldo = new BigDecimal(0);

    // Relacionamento com Cliente (N -> 1)
    protected Cliente cliente;

    // Métodos abstratos
    //
    //
    public abstract void investir(Cliente cliente, BigDecimal valorInvestido, int tempoInvestimento);

    // Métodos concretos
    //
    //
    public void transferir(Conta contaDestino, BigDecimal valorTransferencia){
        this.sacar(valorTransferencia);
        contaDestino.depositar(valorTransferencia);
    }

    public BigDecimal sacar(BigDecimal valorSaque){
        BigDecimal taxa = new BigDecimal(0);
        if (this.cliente instanceof ClientePJ){
            BigDecimal taxaAux = new BigDecimal(0.005);
            taxa = taxaAux;
        }

        BigDecimal valorTaxado = valorSaque.multiply(taxa);

        if (this.saldo.compareTo(valorSaque) >= 0){
            BigDecimal auxSub = this.saldo.subtract(valorSaque.add(valorTaxado));
            this.saldo = auxSub;
            return valorSaque;
        } else {
            return new BigDecimal(-1);
        }
    }

    public String getNumeroConta(){
        return this.numeroConta;
    }

    public BigDecimal consultarSaldo(){
        return this.saldo;
    }

    public void depositar(BigDecimal valorDeposito){
        BigDecimal auxSum = this.saldo.add(valorDeposito);
        this.saldo = auxSum;
    }
}
