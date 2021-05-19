import java.math.BigDecimal;
import java.util.Random;

public class ContaPoupanca extends Conta{

    // Construtor
    public ContaPoupanca(Cliente cliente) throws IllegalArgumentException{

        if (cliente instanceof ClientePJ){
            throw new IllegalArgumentException();
        }

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
        return "ContaPoupanca{" +
                "cliente=" + cliente.getNome() +
                ", numeroConta='" + numeroConta + '\'' +
                ", saldo=" + saldo +
                '}';
    }

    @Override
    public void investir(Cliente cliente, BigDecimal valorInvestido, int tempoInvestimento) {

    }
}
