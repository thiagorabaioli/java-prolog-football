
import org.jpl7.*;
import java.util.ArrayList;
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

    Query q1 = new Query(
        "consult",
        new Term[] {new Atom("src/football_players.pl")}
        );
       q1.hasMoreSolutions();
        
        String positon = getUserInput();

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
            }


}
