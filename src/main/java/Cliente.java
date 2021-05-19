import java.util.ArrayList;

public abstract class Cliente {
    protected String nome;
    protected String id;

    // Relacionamento com Contas (1 -> N)
    protected ArrayList<Conta> contas = new ArrayList<>();

    // Métodos abstratos
    //
    //
    public abstract Long getNumeroCadastro();
    public abstract String getNome();
    public abstract String toString();

    // Métodos concretos
    //
    //
    public ArrayList<Conta> getListaContas(){
        return this.contas;
    }
}
