package Controller;

import Model.Pizza;
import View.Menus;
import View.Screen;
import com.mysql.cj.util.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
CLASSE VALIDATOR

Aquesta classe ha estat emprada per demanar tot el que és per pantalla i validar-ho mitjançant regex.

També la considero de tipus controlador donat que té molta llògica implícita i és qui parlarà amb la vista per printar per pantalla els missatges.

*/
public class Validator {

    public static boolean isValidName(String name){
        return name.matches( "[A-Z][a-zA-Z]*" );
    }

    public static String getValidName(String ask){
        Screen.print("Please tell us your "+ ask +": ");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        while(!Validator.isValidName(str)){
            Screen.println("\nThis "+ ask +" is not valid. Please enter again");
            Screen.print("Please tell us your "+ ask +": ");
            str = s.nextLine();
        }
        return str;
    }

    public static String getValidString(){
        Screen.print("What extras would you like: ");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        return str;
    }

    public static boolean isValidNumber(String str){
        Pattern ptrn = Pattern.compile("(\\+34)?[ -]*(6|7)[ -]*([0-9][ -]*){8}");
        Matcher match = ptrn.matcher(str);
        return (match.find() && match.group().equals(str));
    }

    public static String getValidPhone(){
        Screen.print("Add a contact telephone number: ");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        while(!Validator.isValidNumber(str)){
            Screen.println("\nThis telephone number is not valid. Please enter again");
            Screen.print("Add a contact telephone number: ");
            str = s.nextLine();
        }
        String start = str.substring(0, 3);
        if(!start.equals("+34")){
            str = "+34 " + str;
        }
        return str;
    }

    public static boolean isValidAddress(String str){
        return str.matches(".[a-zA-Z\\s]+,+\\s+\\d+\\b");
    }

    public static String getValidAddress(){
        Screen.println("Add your delivery address. Follow the next format");
        Screen.println("\t<Street>, <number>");
        Screen.print("Input: ");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        while(!Validator.isValidAddress(str)){
            Screen.println("Please enter a valid address following the format");
            Screen.println("\t<Street>, <number>");
            Screen.print("Input: ");
            str = s.nextLine();
        }
        return str;
    }

    public static boolean isValidDelegation(String str){
        return str.matches("Barcelona|Lleida|Girona|General|Tarragona");
    }



    public static String getValidDelegation(){
        Screen.println("Please enter the delegation you are in");
        Screen.println("\tBarcelona, Girona, Lleida or Tarragona");
        Screen.print("Input: ");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        while(!Validator.isValidDelegation(str)){
            Screen.println("Please enter a valid delegation, choose from");
            Screen.println("\tBarcelona, Girona, Lleida or Tarragona");
            Screen.print("Input: ");
            str = s.nextLine();
        }
        return str;
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static int getValidOption(int start, int finish){
        Scanner s = new Scanner(System.in);
        String option = s.nextLine();
        while(!isNumeric(option)){
            Screen.print("Please enter a number between ["+start+" - "+finish+"]: ");
            option = s.nextLine();
        }
        int opt =  Integer.parseInt(option);
        while(opt>finish || opt<start){
            Screen.println("------------------------------------------");
            Screen.println("Please enter a valid option.");
            Menus.printCompactMenu();
            option = s.nextLine();
            while(!isNumeric(option)){
                Screen.print("Please enter a number between ["+start+" - "+finish+"]: ");
                option = s.nextLine();
            }
            opt =  Integer.parseInt(option);
        }
        return opt;
    }

    public static void clearNonDelegationPizzas(String delegation, ArrayList<Pizza> all){
        ArrayList<Pizza> toRemove = new ArrayList<>();
        for(Pizza p : all){
            if(Validator.isValidDelegation(p.getNom())){
                if(!p.getNom().equals(delegation)){
                    toRemove.add(p);
                }
            }
        }
        for(Pizza p : toRemove){
            all.remove(p);
        }
    }


}
