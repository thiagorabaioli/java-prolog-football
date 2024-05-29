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

      public static void adicionarClinete(){
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
}

