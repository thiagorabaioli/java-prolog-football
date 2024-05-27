package Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Cliente;

public class FileReader {

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
}
