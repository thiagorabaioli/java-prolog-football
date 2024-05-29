
import org.jpl7.*;


import Reader.FileReader;
import java.util.List;
import java.util.Scanner;
import entities.Cart;
import entities.Cliente;
import entities.Item;
import java.util.Map;
import java.lang.Integer;




public class Store {
  

    public static void main(String[] args) throws Exception {

        menuPrincipal(true);

        
        
    }

    

    private static void menuPrincipal(boolean b) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("=== MENU PRINCIPAL ===");
            System.out.println("1. Opção 1 Mostrar todos os clientes");
            System.out.println("2. Opção 2 Adicionar novo cliente");
            System.out.println("3. Opção 3 Adicionar um novo Cliente");
            System.out.println("4. Opção 4 Consultar itens da base de dados");
            System.out.println("5. Opção 5 Ver Clientes por anos de lealdade");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                // Instanciar PrologIntegration com o caminho do arquivo store.pl
              FileReader fileReader = new FileReader();

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
                    FileReader.adicionarIremCartCliente();
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

        


       

        
    
        