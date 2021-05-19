import java.util.ArrayList;
import java.util.UUID;

public class ClientePJ extends Cliente{
    private Long cnpj;

    // Construtor
    public ClientePJ(String nome, Long cnpj) {
        this.nome = nome;
        this.id = UUID.randomUUID().toString();
        this.cnpj = cnpj;
    }

    // Métodos sobreescritos
    //
    //
    @Override
    public Long getNumeroCadastro() {
        return cnpj;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        return "ClientePJ{" +
                "nome='" + nome + '\'' +
                ", id='" + id + '\'' +
                ", contas=" + contas +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}
