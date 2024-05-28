
import org.jpl7.*;


import Reader.FileReader;
import java.util.List;
import java.util.Scanner;
import entities.Cart;
import entities.Cliente;
import entities.Item;
import java.util.Map;



public class Store {


    public static void main(String[] args) throws Exception {

          menuPrincipal(true);

        List<Cliente> cli = FileReader.loadClientesFromFile("/home/ubuntu/LP/efolioB/java-prolog-football/App/src/DB/store.pl");
        List<Item> items = FileReader.loadItemsFromFile("/home/ubuntu/LP/efolioB/java-prolog-football/App/src/DB/store.pl");

        System.out.println(cli);

        Scanner scanner = new Scanner(System.in);

        // Seleciona o cliente
        System.out.println("Selecione o cliente para a venda:");
        for (int i = 0; i < cli.size(); i++) {
            System.out.println((i + 1) + ". " + cli.get(i));
        }

        int clienteIndex = -1;
        while (clienteIndex < 0 || clienteIndex >= cli.size()) {
            System.out.print("Digite o número do cliente (1-" + cli.size() + "): ");
            clienteIndex = scanner.nextInt() - 1;

            if (clienteIndex < 0 || clienteIndex >= cli.size()) {
                System.out.println("Índice inválido. Por favor, tente novamente.");
            }
        }
        Cliente clienteSelecionado = cli.get(clienteIndex);

        // Cria o carrinho e associa o cliente a ele
        Cart carrinho = new Cart();
        carrinho.setCliente(clienteSelecionado);

        // Seleciona os itens para o carrinho
        while (true) {
            System.out.println("Selecione o item para adicionar ao carrinho (ou 0 para finalizar):");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }

            int itemIndex = -1;
            while (itemIndex < 0 || itemIndex >= items.size()) {
                System.out.print("Digite o número do item (1-" + items.size() + ", ou 0 para finalizar): ");
                itemIndex = scanner.nextInt() - 1;

                if (itemIndex == -1) {
                    break;
                }

                if (itemIndex < 0 || itemIndex >= items.size()) {
                    System.out.println("Índice inválido. Por favor, tente novamente.");
                }
            }

            if (itemIndex == -1) {
                break;
            }

            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();

            Item itemSelecionado = items.get(itemIndex);
            if (quantidade <= itemSelecionado.getQuantidade()) {
                carrinho.adicionarItem(itemSelecionado, quantidade);
                itemSelecionado.setQuantidade(itemSelecionado.getQuantidade() - quantidade);
            } else {
                System.out.println("Quantidade indisponível em estoque.");
            }
        }

        // Exibe o carrinho final
        System.out.println("Carrinho final:");
        System.out.println(carrinho);

        // Salva a venda no arquivo
        FileReader.saveVendaToFile("src/DB/store.pl", clienteSelecionado, carrinho);

        scanner.close();
       
    }
    

    private static void menuPrincipal(boolean b) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("=== MENU PRINCIPAL ===");
            System.out.println("1. Opção 1 Mostrar todos os clientes");
            System.out.println("2. Opção 2 Adicionar novo cliente");
            System.out.println("3. Opção 3");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                // Instanciar PrologIntegration com o caminho do arquivo store.pl
              FileReader fileReader = new FileReader("/home/ubuntu/LP/efolioB/java-prolog-football/App/src/DB/store.pl");

                // Exemplo de consulta para obter todos os clientes
                Query consultaClientes = fileReader.obterConsulta("todos_clientes(Clientes)");
        
                // Verificar se a consulta tem solução e imprimir os resultados
                if (consultaClientes.hasSolution()) {
                    System.out.println("Clientes encontrados:");
                    while (consultaClientes.hasMoreSolutions()) {
                    Map<String, Term> solucao = consultaClientes.nextSolution();
                    // Manipular e imprimir os resultados conforme necessário
                    System.out.println(solucao);
                 }
               } else {
              System.out.println("Nenhum cliente encontrado.");
               }  
                break;
                case 2:
                    System.out.println("Você escolheu a Opção 2.");
                    FileReader.adicionarClinete();
                    break;
                    
                case 3:
                    System.out.println("Você escolheu a Opção 3.");
                    // Adicione a lógica para a Opção 3 aqui
                    break;
                case 0:
                    System.out.println("Saindo...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 4.");
            }

            System.out.println(); // Linha em branco para separar as interações
        }

        scanner.close(); 

    } 
}

        


       

        
    
        