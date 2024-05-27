
import org.jpl7.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 import java.lang.Integer;


import entities.Cliente;

import java.util.Map;



public class App {


    public static void main(String[] args) throws Exception {

         List<Cliente> clientes = new ArrayList<>();

         try {
            File file = new File("src/DB/store.pl");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
            
                // Verifica se a linha contém um cliente
                if (line.startsWith("cliente_store")) {
                    // Extrai os dados da linha
                    String[] parts = line.split("[(),']+");
            
                    // Verifica se o número de partes está correto
                    if (parts.length >= 4) {
                        try {
                            Integer id = Integer.parseInt(parts[1]);
                            String nome = parts[2];
                            String distrito = parts[3];
                            Integer anosLealdade = Integer.parseInt(parts[4]);
            
                            // Cria um objeto Cliente e adiciona à lista
                            Cliente cliente = new Cliente(id, nome, distrito, anosLealdade);
                            clientes.add(cliente);
                        } catch (NumberFormatException e) {
                            // Trata o caso em que a conversão para Integer falha
                            System.out.println("Erro ao converter para Integer: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Formato inválido da linha: " + line);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado.");
            e.printStackTrace();
        }

        // Exibe os clientes
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }

        
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

        
       /*   String positon = getUserInput(); 

        Term[] args2 = {new Atom(positon), new Variable("Players")};
        Query q2 = new Query(
            "players_at_position",
            args2
            );

          

            Map<String, Term>[] solutions = q2.allSolutions();
            for(Map<String, Term> solution : solutions){
                Term playersTerm = solution.get("Players");
                for(Term playerTerm : playersTerm.toTermArray()){
                    String playerName = playerTerm.name();
                    Player player = new Player(playerName, positon);
                    players.add(player);
                }
            }

            for(Player player : players){
                System.out.println(player.getName() + " - " + player.getPosition());
            }




         }
            private static String getUserInput(){
                String position="";
                try(Scanner sc = new Scanner(System.in)){
                    System.out.print("Enter position: ");
                    position= sc.nextLine();
                }
                return position;
            } */

        
    
        