import java.math.BigDecimal;

public interface OperacoesBancarias {
    BigDecimal sacar(BigDecimal valorSaque);
    void depositar(BigDecimal valorDeposito);
    void transferir(Conta contaDestino, BigDecimal valorTransferencia);
    void investir(Cliente cliente, BigDecimal valorInvestido, int tempoInvestimento);
    BigDecimal consultarSaldo();

}
