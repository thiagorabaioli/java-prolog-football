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

      private Query connectionQuery;

    public FileReader(String filePath) {
        // Estabelecer a conexão inicial ao consultar o arquivo Prolog
        this.connectionQuery = new Query("consult", new Term[] { new Atom(filePath) });
        this.connectionQuery.hasSolution(); // Executar a consulta inicial
    }

    public Query obterConsulta(String consulta) {
        // Retorna uma nova instância de Query para uma consulta específica
        return new Query(consulta);
    }

    public static List<Cliente> loadClientesFromFile(String filePath) {
        List<Cliente> clientes = new ArrayList<>();

        try {
            File file = new File(filePath);
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
    public static List<Item> loadItemsFromFile(String filePath) {
        List<Item> items = new ArrayList<>();

        try {
            File file = new File(filePath);
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

