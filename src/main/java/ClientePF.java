import java.util.ArrayList;
import java.util.UUID;

public class ClientePF extends Cliente{
    private Long cpf;

    // Construtor
    public ClientePF(String nome, Long cpf) {
        this.nome = nome;
        this.id = UUID.randomUUID().toString();
        this.cpf = cpf;
    }

    // Métodos sobreescritos
    //
    //
    @Override
    public Long getNumeroCadastro() {
        return cpf;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        return "ClientePF{" +
                "nome='" + nome + '\'' +
                ", id='" + id + '\'' +
                ", contas=" + contas +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
