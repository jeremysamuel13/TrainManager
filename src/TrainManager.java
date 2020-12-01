/*
 * @author Jeremy Samuel
 * E-mail: jeremy.samuel@stonybrook.edu
 * Stony Brook ID: 113142817
 * CSE 214
 * Recitation Section 3
 * Recitation TA: Dylan Andres
 * HW #2
 */

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * TrainManager class
 * A way for a user to interact with the train through a menu based approach.
 * The user is asked for a command to execute and may also ask for
 * corresponding inputs, and then the command is executed if the conditions
 * are met.
 */
public class TrainManager {

    /**
     * main method
     *
     * @param args
     */
    public static void main(String[] args){

        //scanner
        Scanner scan = new Scanner(System.in);

        //allows the while loop to be switched on or off
        boolean active = true;

        //input for command (asks for a single letter)
        String input;

        //constructing the train linked list for usage
        TrainLinkedList list = new TrainLinkedList();

        while(active){

            menu();

            System.out.println("Enter a selection: ");
            input = scan.nextLine();

            switch (input.toLowerCase()){

                //moves cursor forward
                case "f":
                    try {
                        list.cursorForward();
                        System.out.println("Cursor moved forward");
                    }catch(NullNodeException e){
                        System.out.println(e.getMessage());
                        continue;
                    }

                    break;
                //moves cursor backward
                case "b":
                    try {
                        list.cursorBackward();
                        System.out.println("Cursor moved backward");
                    }catch(NullNodeException e){
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                //inserts car after cursor
                case "i":
                    double lengthInput;
                    double weightInput;

                    try{
                        System.out.println("Enter car length in meters:");
                        lengthInput = scan.nextDouble();
                        System.out.println("Enter car weight in tons:");
                        weightInput = scan.nextDouble();

                        //extra scan allows to prevent following scans
                        //from being skipped
                        scan.nextLine();

                    }catch (InputMismatchException e){
                        System.out.println("Invalid Input");
                        continue;
                    }
                    try {
                        TrainCar t = new TrainCar(lengthInput, weightInput);
                        try {
                            list.insertAfterCursor(t);
                        }catch (IllegalArgumentException e){
                            System.out.println(e.getMessage());
                            continue;
                        }
                    }catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        continue;
                    }

                    System.out.println("New train car "+ lengthInput + " " +
                            "meters " + weightInput +" tons inserted into " +
                            "train.");
                    break;
                //removes car at cursor
                case "r":
                    try{
                        TrainCar removed = list.removeCursor();
                        String productName = removed.getLoad().getName();
                        double productWeight = removed.getLoad().getWeight();
                        double productValue = removed.getLoad().getValue();
                        boolean productDangerous =
                                removed.getLoad().getIsDangerous();
                        String dangerousString ="";

                        if(productDangerous){
                            dangerousString = "YES";
                        }else if (!productDangerous){
                            dangerousString = "NO";
                        }

                        System.out.println("Car successfully unlinked. The " +
                                "following load has been removed from the " +
                                "train:\n" + String.format("%8s%16s%14s%12s",
                                "Name", "Weight (t)", "Value" + " ($)",
                                "Dangerous" ) + "\n" + "=======================" +
                                "============================\n" +
                                String.format("%8s%16s%14s%12s", productName,
                                        productWeight,
                                        TrainLinkedList.doubleDollarConversion
                                                (productValue),
                                        dangerousString));
                    }catch (NullNodeException e){
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                //sets load at cursor
                case "l":
                    String loadName;
                    double loadWeight;
                    double loadValue;
                    String loadDangerous;
                    boolean isLoadDangerous;

                    try{
                        System.out.println("Enter product name:");
                        loadName = scan.nextLine();
                        System.out.println("Enter product weight in tons:");
                        loadWeight = scan.nextDouble();
                        System.out.println("Enter product value in dollars:");
                        loadValue = scan.nextDouble();
                        System.out.println("Enter is product dangerous? (y/n):");

                        //extra scan allows to prevent the following scan
                        // from being skipped
                        scan.nextLine();

                        loadDangerous = scan.nextLine();
                        if(loadDangerous.toLowerCase().equals("y")){
                            isLoadDangerous = true;
                        }else if(loadDangerous.toLowerCase().equals("n")){
                            isLoadDangerous = false;
                        }else{
                            System.out.println("Invalid input");
                            continue;
                        }

                        try {
                            ProductLoad l = new ProductLoad(loadName,
                                    loadWeight, loadValue, isLoadDangerous);
                            list.getCursorData().getCar().setLoad(l);
                            list.addInfo(list.getCursorData());
                            System.out.println(loadWeight + " tons of " +
                                    loadName + " added to the current car.");
                        }catch(IllegalArgumentException | NullNodeException e){
                            System.out.println(e.getMessage());
                            continue;
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Invalid input");
                    }
                    break;
                //searches for product
                case "s":
                    try {
                        System.out.println("Enter product name:");
                        list.findProduct(scan.nextLine());
                    }catch(NullPointerException e){
                        System.out.println("Train is empty");
                        continue;
                    }
                    break;
                //gets info on train
                case "t":
                    System.out.println(list.toString());
                    break;
                //prints train manifest (loads carried by each car)
                case "m":
                    list.printManifest();
                    break;
                //removes all dangerous cars from train
                case "d":
                    try {
                        list.removeDangerousCars();
                    }catch(NullPointerException e){
                        System.out.println("No train cars with an available " +
                                "product load");
                        continue;
                    }
                    System.out.println("Dangerous cars successfully removed " +
                            "from the train.");
                    break;
                //terminates program
                case "q":
                    System.out.println("Program terminating successfully...");
                    active = false;
                    break;
                //prompts the user that their input was invalid if it doesn't
                // fall under any of the cases
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
        }
    }

    /**
     * Prints the menu for the user to see what commands they can run
     */
    public static void menu(){
        System.out.println("\n(F) Cursor Forward\n(B) Cursor Backward\n(I) " +
                "Insert Car After Cursor\n(L) Set Product Load\n(S) Search For " +
                "Product\n(T) Display Train\n(M) Display Manifest\n(D) Remove " +
                "Dangerous Cars\n(Q) Quit\n");
    }

}


