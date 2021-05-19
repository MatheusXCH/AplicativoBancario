import java.math.BigDecimal;
import java.util.Random;

public class ContaCorrente extends Conta{

    // Construtor
    public ContaCorrente(Cliente cliente) {
        this.cliente = cliente;

        Random rand = new Random();
        for (int i = 0; i < 4; i++){
            this.numeroConta += Integer.toString(rand.nextInt(10));
        }
    }

    // Métodos sobreescritos
    //
    //
    @Override
    public String toString() {
        return "ContaCorrente{" +
                "cliente=" + cliente.getNome() +
                ", numeroConta='" + numeroConta + '\'' +
                ", saldo=" + saldo +
                '}';
    }

    @Override
    public void investir(Cliente cliente, BigDecimal valorInvestido, int tempoInvestimento) {
    }
}
