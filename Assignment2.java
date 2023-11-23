/**
 * Assignment2
 */

import java.util.Scanner;
public class Assignment2 {
    

/**
 * Main entry point for program.
 * Reads user input of block type.
 * If input valid, appropriate block subclass created and initialised.
 * @param args
 */
    public static void main(String[] args) {
       Scanner reader = new Scanner(System.in);
       System.out.println("Enter block type:");
       String type = reader.nextLine();
       
       Block block;
       switch (type) {
        case "n":
            block = new BlockNumeric(reader);
            break;
        case "t":
            block = new BlockTextual(reader);
            break;
        default:
            System.out.println("Invalid block type.");
            reader.close();
            return;        
       }

       block.initialise();
       

    }
}