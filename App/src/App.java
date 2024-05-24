
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
        System.out.println("Hello, World!");
    }
}
