
import org.jpl7.*;
import Reader.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.Cart;
import entities.Cliente;
import entities.Item;

import java.util.Map;



public class Store {


    public static void main(String[] args) throws Exception {

           // Instanciar PrologIntegration com o caminho do arquivo store.pl
          FileReader fileReader = new FileReader("src/DB/store.pl");

          

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

        List<Cliente> cli = FileReader.loadClientesFromFile("src/DB/store.pl");
        List<Item> items = FileReader.loadItemsFromFile("src/DB/store.pl");

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
    
    

        Query q1 = new Query(
            "consult",
            new Term[] {new Atom("src/DB/store.pl")}
            );
    
            System.out.println( "consult " + (q1.hasSolution() ? "succeeded" : "failed"));
           q1.hasMoreSolutions();

        menuPrincipal(true);
       
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
                    System.out.println("Você escolheu a Opção 1.");
                    
                    Query query = new Query("todos_clientes(Clientes)");
                    Map<String, Term>[] solutions = query.allSolutions();
                     ArrayList<String> players2 = new ArrayList<>();
                     for (Map<String, Term> solution : solutions) {
                         Term playersTerm = solution.get("Clientes");
                         players2.add(playersTerm.toString());
                     }
             
                     // Imprimir os jogadores
                     System.out.println("Lista de Clientes:");
                     for (String player : players2) {
                         System.out.println(player);
                     }
                    break;
                case 2:
                    System.out.println("Você escolheu a Opção 2.");
                    System.out.println("Digite o ID.");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha após o próximoInt()
                
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.nextLine();
                
                    System.out.print("Digite a localização do cliente: ");
                    String localizacao = scanner.nextLine();
                
                    System.out.print("Digite o número de anos como cliente: ");
                    int anos = scanner.nextInt();
                
                   // Adicionar um novo cliente
                    String consulta = "adicionar_cliente(" + id + ", '" + nome + "', '" + localizacao + "', " + anos + ")";
                    Query addCliente = new Query(consulta);

                    if (addCliente.hasSolution()) {
                        System.out.println("Novo cliente adicionado com sucesso.");
                    } else {
                        System.out.println("Falha ao adicionar novo cliente.");
                    }
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

        


       

        
    
        