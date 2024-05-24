
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
       // q1.hasMoreSolutions();
        System.out.println( "consult " + (q1.hasSolution() ? "succeeded" : "failed"));

        String positon = getUserInput();



    }
    private static String getUserInput(){
        String position="";
        try(Scanner sc = new Scanner(System.in)){
            System.out.println("Enter position");
            position= sc.nextLine();
        }
        return position;

    }


}
