package Reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;

import entities.Cart;
import entities.Cliente;
import entities.Item;

public class FileReader {

    private static final String FILE_PATH = "/home/ubuntu/LP/efolioB/java-prolog-football/App/src/DB/store.pl";

      private Query connectionQuery;

    public FileReader() {
        // Estabelecer a conexão inicial ao consultar o arquivo Prolog
        this.connectionQuery = new Query("consult", new Term[] { new Atom(FILE_PATH) });
        this.connectionQuery.hasSolution(); // Executar a consulta inicial
    }

    public Query obterConsulta(String consulta) {
        // Retorna uma nova instância de Query para uma consulta específica
        return new Query(consulta);
    }

    public static List<Cliente> loadClientesFromFile() {
        List<Cliente> clientes = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Verifica se a linha contém um cliente
                if (line.startsWith("cliente_store")) {
                    String[] parts = line.split("[(),']+");
                    Integer id = Integer.parseInt(parts[1]);
                    String nome = parts[2];
                    String distrito = parts[3];
                    Integer anosLealdade = Integer.parseInt(parts[4]);

                    // Cria um objeto Cliente e adiciona à lista
                    Cliente cliente = new Cliente(id, nome, distrito, anosLealdade);
                    clientes.add(cliente);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado.");
            e.printStackTrace();
        }

        return clientes;
    }

     //Adicionar um cliente à base de dados

      public static void adicionarItemCartCliente(){
        Scanner scanner = new Scanner(System.in);
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

    }

  


    public static List<Item> loadItemsFromFile() {
        List<Item> items = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.startsWith("item_store")) {
                    String[] parts = line.split("[(),']+");
                    Integer id = Integer.parseInt(parts[1]);
                    String nome = parts[2];
                    String categoria = parts[3];
                    Double preco = Double.parseDouble(parts[4]);
                    Integer quantidade = Integer.parseInt(parts[5]);

                    Item item = new Item(id, nome, categoria,preco, quantidade);
                    items.add(item);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado.");
            e.printStackTrace();
        }

        return items;
    }

    public static void saveVendaToFile(String filePath, Cliente cliente, Cart carrinho) {
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Map.Entry<Item, Integer> entry : carrinho.getItems().entrySet()) {
                Item item = entry.getKey();
                Integer quantidade = entry.getValue();

                bufferedWriter.write(String.format("venda(%d, %d, %d, %f).\n",
                        cliente.getId(), item.getId(), quantidade, item.getPreco() * quantidade));
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar a venda: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void adicionarCliente(){
        Scanner scanner = new Scanner(System.in);
        try {
            String FILE_PATH = "/home/ubuntu/LP/efolioB/java-prolog-football/App/src/DB/store.pl";

            // Carregando clientes e itens do arquivo Prolog
            List<Cliente> cli = FileReader.loadClientesFromFile();
            List<Item> items = FileReader.loadItemsFromFile();

            // Verifica se há clientes e itens carregados
            if (cli.isEmpty() || items.isEmpty()) {
                System.out.println("Não há clientes ou itens disponíveis. Encerrando o programa.");
                return;
            }

            // Seleciona o cliente
            System.out.println("Selecione o cliente para a venda:");
            for (int i = 0; i < cli.size(); i++) {
                System.out.println((i + 1) + ". " + cli.get(i));
            }

            int clienteIndex = -1;
            boolean inputValido = false;
            while (!inputValido) {
                System.out.print("Digite o número do cliente (1-" + cli.size() + "): ");
                String input = scanner.nextLine();
                try {
                    clienteIndex = Integer.parseInt(input) - 1;
                    if (clienteIndex >= 0 && clienteIndex < cli.size()) {
                        inputValido = true;
                    } else {
                        System.out.println("Índice inválido. Por favor, tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                }
            }

            Cliente clienteSelecionado = cli.get(clienteIndex);
            Cart carrinho = new Cart();
            carrinho.setCliente(clienteSelecionado);

            // Seleciona os itens para o carrinho
            while (true) {
                System.out.println("Selecione o item para adicionar ao carrinho (ou 0 para finalizar):");
                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i + 1) + ". " + items.get(i));
                }

                int itemIndex = -1;
                inputValido = false;
                while (!inputValido) {
                    System.out.print("Digite o número do item (1-" + items.size() + ", ou 0 para finalizar): ");
                    String input = scanner.nextLine();
                    try {
                        itemIndex = Integer.parseInt(input) - 1;
                        if (itemIndex >= -1 && itemIndex < items.size()) {
                            inputValido = true;
                        } else {
                            System.out.println("Índice inválido. Por favor, tente novamente.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, digite um número.");
                    }
                }

                if (itemIndex == -1) {
                    break;
                }

                System.out.print("Quantidade: ");
                int quantidade = -1;
                inputValido = false;
                while (!inputValido) {
                    String input = scanner.nextLine();
                    try {
                        quantidade = Integer.parseInt(input);
                        if (quantidade > 0) {
                            inputValido = true;
                        } else {
                            System.out.println("Quantidade inválida. Por favor, digite um número maior que zero.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, digite um número inteiro maior que zero.");
                    }
                }

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
            FileReader.saveVendaToFile(FILE_PATH, clienteSelecionado, carrinho);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}

