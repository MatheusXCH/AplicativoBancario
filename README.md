# AplicativoBancario
 Aplicativo que simula o funcionamento de um banco. Utiliza o console como interface de interação com o usuário.

## Objetivos:
- O objetivo principal do projeto é colocar em prática conhecimentos sobre herança e polimorfismo em Java.
- Implementação de interfaces no console que se assemelhem ao funcionamento de programas na prática.

## Funcionamento:

### Princípios da Aplicação:

#### Armazenamento:
- O aplicativo não utiliza nenhuma forma de persistência de dados. Dessa forma, os clientes cadastrados e contas criadas são mantidos apenas durante o tempo de execução.

#### Menus:
- Há dois menus principais que são executados até que o usuário opte por sair. São eles:
    + Menu Entrada: Permite ao usuário criar um novo cadastro, ou fazer login caso já o possua.
    + Menu Cliente: Após feito o login, permite que usuário utilize recursos de:
        * Criação de contas;
        * Consulta de saldo;
        * Saque;
        * Depósito;
        * Transferência;
        * Investimentos;

#### Contas:
- Existem três tipos de contas possíveis de serem criadas:
    + Conta Corrente
    + Conta Poupança
    + Conta Investimento

#### Usuários:
- Existem dois tipos de usuários:
    + Pessoa Física:
        * Usuário padrão: pode criar conta de quaisquer tipo;
    + Pessoa Jurídica:
        * Não pode criar conta do tipo Conta Poupança;
        * É cobrada uma taxa de 0,5% para cada Transferência ou Saque realizado;
        * Seus investimentos em contas "Conta Investimento" rendem 2% a mais;

