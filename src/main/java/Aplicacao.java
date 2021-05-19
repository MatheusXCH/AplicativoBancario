import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Aplicacao {

    // ArrayList global que armazena os clientes ativos no banco
    private static final ArrayList<Cliente> clientesAtivos = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Sistema de Conta Bancária ---");

        menuEntrada: while (true) {
            Cliente cliente;
            System.out.println("\n---------- MENU INICIAL ----------");
            System.out.println("1) Entrar");
            System.out.println("2) Cadastrar");
            System.out.println("0) Sair");
            System.out.print("Sua escolha: ");
            int opcaoEntrada = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoEntrada) {
                case 1:
                    cliente = entrar(scanner);
                    if (cliente == null) {
                        System.out.println("CLIENTE NÃO ENCONTRADO!\nPor favor, tente novamente!");
                        continue menuEntrada;
                    }
                    break;

                case 2:
                    cliente = novoCadastro(scanner);
                    if (cliente == null) {
                        continue menuEntrada;
                    }
                    break;

                case 0:
                    System.out.println("\n--- Encerrando Sistema --- \n--- Volte sempre! ---");
                    System.out.println("Todos os clientes ativos durante a execução: \n");
                    System.out.println(clientesAtivos.toString());
                    break menuEntrada;

                default:
                    System.out.println("\nOpção inválida!\nTente novamente!");
                    TimeUnit.SECONDS.sleep(3);
                    continue menuEntrada;
            }

            menuCliente: while (true) {
                System.out.println("\n---------- MENU CLIENTE ----------");
                System.out.println("O que deseja fazer?");
                System.out.println("1) Abrir Conta");
                System.out.println("2) Sacar");
                System.out.println("3) Depositar");
                System.out.println("4) Transferir");
                System.out.println("5) Investir");
                System.out.println("6) Consultar Saldo");
                System.out.println("0) Sair");
                System.out.print("Sua escolha: ");
                int opcaoCliente = scanner.nextInt();
                scanner.nextLine();

                switch (opcaoCliente) {
                    case 1:
                        abrirConta(scanner, cliente);
                        break;
                    case 2:
                        sacar(scanner, cliente);
                        break;
                    case 3:
                        depositar(scanner, cliente);
                        break;
                    case 4:
                        transferir(scanner, cliente);
                        break;
                    case 5:
                        investir(scanner, cliente);
                        break;
                    case 6:
                        consultarSaldo(scanner, cliente);
                        break;
                    case 0:
                        break menuCliente;
                    default:
                        System.out.println("\nOpção inválida!\nTente novamente!");
                }
                TimeUnit.SECONDS.sleep(3);

            }
        }
    }
    public static void abrirConta(Scanner scanner, Cliente cliente) {
        System.out.println("\n----- ABRINDO CONTA -----");
        System.out.println("----- Informe o tipo de conta que deseja abrir: ");
        System.out.println("1) Conta Corrente");
        System.out.println("2) Conta Poupanca");
        System.out.println("3) Conta Investimento");
        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        switch (tipoConta) {
            case 1:
                // Cria conta corrente
                ContaCorrente contaCorrente = new ContaCorrente(cliente);
                cliente.getListaContas().add(contaCorrente);

                System.out.println("Conta Corrente criada com sucesso! Dados da conta:\n");
                System.out.println(contaCorrente.toString());
                break;

            case 2:
                try {
                    ContaPoupanca contaPoupanca = new ContaPoupanca(cliente);
                    cliente.getListaContas().add(contaPoupanca);

                    System.out.println("Conta Poupança criada com sucesso! Dados da conta:\n");
                    System.out.println(contaPoupanca.toString());
                    break;
                }catch (IllegalArgumentException e){
                    System.out.println("Cliente PJ não pode criar conta poupança.\nTente abrir outro tipo de conta.");
                    break;
                }

            case 3:
                ContaInvestimento contaInvestimento = new ContaInvestimento(cliente);
                cliente.getListaContas().add(contaInvestimento);

                System.out.println("Conta Investimento criada com sucesso! Dados da conta:\n");
                System.out.println(contaInvestimento.toString());
                break;

            default:
                System.out.println("\nOpção inválida!\nTente novamente!");
                break;
        }
    }

    public static void sacar(Scanner scanner, Cliente cliente) {
        System.out.println("\n----- SAQUE -----\n");
        int contaConsulta = acessarConta(scanner, cliente);
        if (contaConsulta == -1) { return; }
        System.out.println("Valor do saque: ");
        BigDecimal valorSaque = scanner.nextBigDecimal();
        scanner.nextLine();

        BigDecimal valorSacado = cliente.getListaContas().get(contaConsulta).sacar(valorSaque);

        if (valorSacado.equals(new BigDecimal(-1))) {
            System.out.println("\n----- ERRO:\nNão há saldo suficiente para esta operação!");
        } else {
            System.out.println("Saque efetuado com sucesso!");
        }
    }

    public static void depositar(Scanner scanner, Cliente cliente) {
        System.out.println("\n----- DEPÓSITO -----\n");
        int contaConsulta = acessarConta(scanner, cliente);
        if (contaConsulta == -1) { return; }
        System.out.println("Valor do depósito: ");
        BigDecimal valorDeposito = scanner.nextBigDecimal();
        scanner.nextLine();

        cliente.getListaContas().get(contaConsulta).depositar(valorDeposito);
        System.out.println("Depósito feito com sucesso!");
    }

    public static void transferir(Scanner scanner, Cliente cliente){
        System.out.println("\n----- TRANSFERÊNCIA -----");
        System.out.println("\n--- Selecione a conta remetente: ");
        int contaConsulta = acessarConta(scanner, cliente);
        if (contaConsulta == -1) { return; }

        System.out.println("--- Destinatário: ");
        System.out.println("Informe o CPF/CNPJ: ");
        Long numeroCadastro = scanner.nextLong();
        scanner.nextLine();

        for (int i = 0; i < clientesAtivos.size(); i++) {
            if (clientesAtivos.get(i).getNumeroCadastro().equals(numeroCadastro)){
                System.out.println("\nPara qual conta de " + clientesAtivos.get(i).getNome() + " deseja transferir?");
                int contaDestino = acessarConta(scanner, clientesAtivos.get(i));

                System.out.println("Informe o valor a ser transferido: ");
                BigDecimal valorTransferencia = scanner.nextBigDecimal();

                //TRANSFERIR
                cliente.getListaContas().get(contaConsulta).transferir(clientesAtivos.get(i).getListaContas().get(contaDestino), valorTransferencia);
                System.out.println("Transferência feita com sucesso!");
            }
        }
    }

    public static void investir(Scanner scanner, Cliente cliente){
        System.out.println("\n----- INVESTIMENTO -----");
        int contaConsulta = acessarConta(scanner, cliente);
        if (contaConsulta == -1) { return; }

        if (cliente.getListaContas().get(contaConsulta) instanceof ContaInvestimento) {
            System.out.println("Qual o valor a ser investido?");
            BigDecimal valorInvestido = scanner.nextBigDecimal();
            System.out.println("Por quanto tempo (em anos) será o investimento?");
            int tempoInvestimento = scanner.nextInt();

            cliente.getListaContas().get(contaConsulta).investir(cliente, valorInvestido, tempoInvestimento);

            System.out.println("--- Seus Investimentos:");

            for (int i = 0; i < ((ContaInvestimento) cliente.getListaContas().get(contaConsulta)).getInvestimentos().size(); i++){
                System.out.println((i+1) + ") " + ((ContaInvestimento) cliente.getListaContas().get(contaConsulta)).getInvestimentos().get(i).toString());
            }

        } else {
            System.out.println("A conta deve ser do tipo 'Conta Investimento'.\nPor favor, crie uma caso não a possua!");
        }

    }

    public static void consultarSaldo(Scanner scanner, Cliente cliente) {
        System.out.println("\n----- CONSULTA DE SALDO -----\n");
        int contaConsulta = acessarConta(scanner, cliente);
        if (contaConsulta == -1) { return; }

        System.out.println("Saldo da conta: R$" + cliente.getListaContas().get(contaConsulta).consultarSaldo());
    }

    public static Cliente entrar(Scanner scanner) {
        System.out.println("Insira seu CPF ou CNPJ: ");
        Long cpf = scanner.nextLong();

        for (Cliente c : clientesAtivos){
            if (c.getNumeroCadastro().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public static Cliente novoCadastro(Scanner scanner) throws InterruptedException {
        System.out.println("\n----- NOVO CADASTRO -----");

        System.out.println("Selecione o tipo de pessoa:\n1) Pessoa Fisica (PF)\n2) Pessoa Jurídica (PJ)");
        System.out.println("Sua escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao != 1 && opcao != 2){
            System.out.println("\nOpção inválida!\nTente novamente!");
            TimeUnit.SECONDS.sleep(3);
            return null;
        }

        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        if (opcao == 1){
            System.out.println("CPF: ");
            Long cpf = scanner.nextLong();
            var novoClientePF = new ClientePF(nome, cpf);
            clientesAtivos.add(novoClientePF);
            return novoClientePF;
        } else {
            System.out.println("CNPJ: ");
            Long cnpj = scanner.nextLong();
            var novoClientePJ = new ClientePJ(nome, cnpj);
            clientesAtivos.add(novoClientePJ);
            return novoClientePJ;
        }
    }

    public static int acessarConta(Scanner scanner, Cliente cliente) {
        System.out.println("----- CONTAS DISPONIVEIS: ");

        if (cliente.getListaContas().size() == 0){
            System.out.println("Não há contas cadastradas!");
            return -1;
        } else {
            for (int i = 0; i < cliente.getListaContas().size(); i++){
                System.out.println((i+1) + ") Conta: " + cliente.getListaContas().get(i).getNumeroConta() + ", Tipo: " + cliente.getListaContas().get(i).getClass().getName());
            }
            System.out.println("Qual a conta (informe o índice)?");
            int contaConsulta = scanner.nextInt();
            if (contaConsulta > cliente.getListaContas().size()){
                System.out.println("Conta invalida!");
                return -1;
            }

            scanner.nextLine();
            return --contaConsulta;
        }
    }
}
