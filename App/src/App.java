
import org.jpl7.*;

import entities.Cart;
import entities.Cliente;
import entities.Item;
import entities.Store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;;


public class App {

    static class Player{
        private String name;
        private String position;
    
    public Player(String name, String position){
        this.name = name;
        this.position = position;
    }

    public String getName(){ return name;}
    
    public String getPosition(){ return position;}

}


    public static void main(String[] args) throws Exception {

     //   menuPrincipal(true);
       

    Query q1 = new Query(
        "consult",
        new Term[] {new Atom("src/DB/store.pl")}
        );

        System.out.println( "consult " + (q1.hasSolution() ? "succeeded" : "failed"));
       q1.hasMoreSolutions();


        
      /*   String positon = getUserInput();

        Term[] args2 = {new Atom(positon), new Variable("Players")};
        Query q2 = new Query(
            "players_at_position",
            args2
            );

            ArrayList<Player> players = new ArrayList<>();

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

         /*    Item item1 = new Item(1, "Potion of Healing", "potions", 10.0, 50);
	Item item2 = new Item (2, "Wand of Fireball", "wands", 20.0, 30);
	Item item3 = new Item(3, "Enchanted Spellbook", "enchanted_books", 30.0, 20);
	Item item4=  new Item(4, "Crystal of Clairvoyance", "crystals", 15.0, 40);
	Item item5 = new Item(5, "Amulet of Protection", "amulets", 25.0, 25);
	
	Cliente cli1 = new Cliente(1, "Helio Silva", "Seixal", "Setubal", 2);
	Cliente cli2 = new Cliente(1, "Carla Alves", "Amadora", "Lisboa", 1);
	Cliente cli3 = new Cliente(1, "José Tavares", "Faro", "Faro", 4);
	
	Store store = new Store(1, "Enchanted Emporium");
	store.getClientes().addAll(Arrays.asList(cli1,cli2,cli3));
	
	
	Cart cart1 = new Cart(1, cli1);
	cart1.getItems().addAll(Arrays.asList(item1,item4));
	cli1.getCarts().addAll(Arrays.asList(cart1));
	
	Cart cart2 = new Cart(2, cli2);
	cart2.getItems().addAll(Arrays.asList(item2,item5));
	cli2.getCarts().addAll(Arrays.asList(cart2));
	
	
	Cart cart3 = new Cart(3, cli3);
	cart3.getItems().addAll(Arrays.asList(item3,item4,item5));
	cli3.getCarts().addAll(Arrays.asList(cart3));
	
	System.out.println(store);
	

*/


  }  

  private static void menuPrincipal(boolean exec){
    Scanner sc = new Scanner(System.in);
    exec = true;

    while(exec){
        System.out.println("=== MENU PRINCIPAL ===");
        System.out.println("1. Opção 1");
        System.out.println("2. Opção 2");
        System.out.println("3. Opção 3");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("Você escolheu a Opção 1.");
                // Adicione a lógica para a Opção 1 aqui
                break;
            case 2:
                System.out.println("Você escolheu a Opção 2.");
                // Adicione a lógica para a Opção 2 aqui
                break;
            case 3:
                System.out.println("Você escolheu a Opção 3.");
                // Adicione a lógica para a Opção 3 aqui
                break;
            case 4:
                System.out.println("Saindo...");
                exec = false;
                break;
            default:
                System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 4.");
        }
        System.out.println(); // Linha em branco para separar as interações
    }
    sc.close();
  }

}


