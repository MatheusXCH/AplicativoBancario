import java.math.BigDecimal;

public class Investimento {
    private static final int[] ALIQUOTAS_ANOS = {1, 3, 5, 10};
    private static final BigDecimal RENDIMENTO_ANUAL_BASE = new BigDecimal(1.06);
    private static final BigDecimal BONUS_ANUAL = new BigDecimal(0.005);
    private BigDecimal valor;
    private int tempoDuracao;

    // Relacionamento com Cliente (N -> 1)
    private Cliente cliente;

    // Construtor
    public Investimento(Cliente cliente, BigDecimal valor, int tempoDuracao) {
        this.cliente = cliente;
        this.valor = valor;
        this.tempoDuracao = tempoDuracao;
    }

    // Métodos concretos
    //
    //
    public BigDecimal rendimento() {
        BigDecimal jurosAdicional = new BigDecimal(0);

        if (this.cliente instanceof ClientePJ){
            BigDecimal jurosAdicionalAux = new BigDecimal(0.02);
            jurosAdicional = jurosAdicionalAux;
        }

        BigDecimal jurosAux = jurosAdicional.add(RENDIMENTO_ANUAL_BASE);

        if (this.tempoDuracao <= ALIQUOTAS_ANOS[0]) {
            BigDecimal juros = jurosAux.add(BONUS_ANUAL.multiply(BigDecimal.valueOf(this.tempoDuracao)));
            BigDecimal jurosTotais = juros.pow(this.tempoDuracao);
//            BigDecimal jurosTotais = juros.multiply(BigDecimal.valueOf(ALIQUOTAS_ANOS[0]));
            return this.valor.multiply(jurosTotais);
        }
        if (this.tempoDuracao <= ALIQUOTAS_ANOS[1]){
            BigDecimal juros = jurosAux.add(BONUS_ANUAL.multiply(BigDecimal.valueOf(this.tempoDuracao)));
            BigDecimal jurosTotais = juros.pow(this.tempoDuracao);
//            BigDecimal jurosTotais = juros.multiply(BigDecimal.valueOf(ALIQUOTAS_ANOS[1]));
            return this.valor.multiply(jurosTotais);
        }
        if (this.tempoDuracao <= ALIQUOTAS_ANOS[2]){
            BigDecimal juros = jurosAux.add(BONUS_ANUAL.multiply(BigDecimal.valueOf(this.tempoDuracao)));
            BigDecimal jurosTotais = juros.pow(this.tempoDuracao);
//            BigDecimal jurosTotais = juros.multiply(BigDecimal.valueOf(ALIQUOTAS_ANOS[2]));
            return this.valor.multiply(jurosTotais);
        }
        if (this.tempoDuracao <= ALIQUOTAS_ANOS[3]){
            BigDecimal juros = jurosAux.add(BONUS_ANUAL.multiply(BigDecimal.valueOf(this.tempoDuracao)));
            BigDecimal jurosTotais = juros.pow(this.tempoDuracao);
//            BigDecimal jurosTotais = juros.multiply(BigDecimal.valueOf(ALIQUOTAS_ANOS[3]));
            return this.valor.multiply(jurosTotais);
        }
        else{
            BigDecimal juros = jurosAux.add(BONUS_ANUAL.multiply(BigDecimal.valueOf(this.tempoDuracao)));
            BigDecimal jurosTotais = juros.pow(ALIQUOTAS_ANOS[0]);
//            BigDecimal jurosTotais = juros.multiply(BigDecimal.valueOf(ALIQUOTAS_ANOS[4]));
            return this.valor.multiply(jurosTotais);
        }
    }

    // Métodos sobreescritos
    //
    //
    @Override
    public String toString() {
        return "Investimento{" +
                "valor=" + this.valor +
                ", tempoDuracao=" + this.tempoDuracao +
                ", montanteEsperado=" + this.rendimento() +
                '}';
    }
}
