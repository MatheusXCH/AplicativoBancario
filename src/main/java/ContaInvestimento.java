import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class ContaInvestimento extends Conta{
    // Relacionamento com Investimentos (1 -> N)
    private ArrayList<Investimento> investimentos = new ArrayList<>();

    // Construtor
    public ContaInvestimento(Cliente cliente) {
        this.cliente = cliente;

        Random rand = new Random();
        for (int i = 0; i < 4; i++){
            this.numeroConta += Integer.toString(rand.nextInt(10));
        }
    }

    // Métodos concretos
    //
    //
    public ArrayList<Investimento> getInvestimentos() {
        return investimentos;
    }

    // Métodos sobreescritos
    //
    //
    @Override
    public void investir(Cliente cliente, BigDecimal valorInvestimento, int tempoInvestimento) {
        if (this.saldo.compareTo(valorInvestimento) >= 0){
            BigDecimal saldoAux = this.saldo.subtract(valorInvestimento);
            this.saldo = saldoAux;
            this.investimentos.add(new Investimento(cliente, valorInvestimento, tempoInvestimento));
            System.out.println("Investimento criado com sucesso!");
        } else {
            System.out.println("Não há saldo o suficiente na conta para esta operação.");
        }


    }

    @Override
    public String toString() {
        return "ContaInvestimento{" +
                "cliente=" + cliente.getNome() +
                ", numeroConta='" + numeroConta + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
