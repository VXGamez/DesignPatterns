package View;

import Controller.Comanda;
import Controller.Validator;
import Model.*;
import Model.DB.CustomerDAO;
import Model.DB.IngredientDAO;
import Model.DB.MassaDAO;
import java.util.ArrayList;
import java.util.Scanner;


/*
CLASSE MENUS

Com és evident fins ara, s'ha emprat el model MVC. Aquesta és la classe principal de la View.

En aquesta classe es fa gran part de la gestió de la pantalla, tot i tenir alguna part de llògica implicita també.

S'ha intentat que la llògica que té sigui la mínima necessària i ajudi a que la vista mostri les dades adequades.

S'accedeix a totes les funcions de manera estàtica donat que la classe Menus no esta intencionada per ser instanciada però per fer les crides a les funcions individuals.

En cas que es tinguéssin múltiples vistes o es volgués fer un sistema de finestres llavors sí és probable que instancii la classe Menus perque cada objecte tingui la seva llògica de manera separada i no compartida a nivell estàtic.

*/
public class Menus {

    public static void intro(){
        System.out.println("\n\n\n-------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("\033[1;97m██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗    ████████╗ ██████╗     ██████╗ ██╗███████╗███████╗██╗███████╗ █████╗ ██╗     ██╗     ███████╗");
        System.out.println("██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝    ╚══██╔══╝██╔═══██╗    ██╔══██╗██║╚══███╔╝╚══███╔╝██║██╔════╝██╔══██╗██║     ██║     ██╔════╝");
        System.out.println("██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗         ██║   ██║   ██║    ██████╔╝██║  ███╔╝   ███╔╝ ██║███████╗███████║██║     ██║     █████╗  ");
        System.out.println("██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝         ██║   ██║   ██║    ██╔═══╝ ██║ ███╔╝   ███╔╝  ██║╚════██║██╔══██║██║     ██║     ██╔══╝  ");
        System.out.println("╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗       ██║   ╚██████╔╝    ██║     ██║███████╗███████╗██║███████║██║  ██║███████╗███████╗███████╗");
        System.out.println(" ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝       ╚═╝    ╚═════╝     ╚═╝     ╚═╝╚══════╝╚══════╝╚═╝╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝");
        System.out.println("\033[1;95m  __  __           _        _              __     ___      _              __  ___                 ");
        System.out.println(" |  \\/  | __ _  __| | ___  | |__  _   _ _  \\ \\   / (_) ___| |_ ___  _ __  \\ \\/ (_)_ __ __ _ _   _ ");
        System.out.println(" | |\\/| |/ _` |/ _` |/ _ \\ | '_ \\| | | (_)  \\ \\ / /| |/ __| __/ _ \\| '__|  \\  /| | '__/ _` | | | |");
        System.out.println(" | |  | | (_| | (_| |  __/ | |_) | |_| |_    \\ V / | | (__| || (_) | |     /  \\| | | | (_| | |_| |");
        System.out.println(" |_|  |_|\\__,_|\\__,_|\\___| |_.__/ \\__, (_)    \\_/  |_|\\___|\\__\\___/|_|    /_/\\_\\_|_|  \\__,_|\\__,_|");
        System.out.println("                                  |___/                                                           \033[0m");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------\n");

        System.out.println("Please add some personal information before starting your command!\n");
        System.out.print("Press enter to add personal information...");
        Scanner s = new Scanner(System.in);
        String k = s.nextLine();
    }

    public static Customer getUserInfo(){
        CustomerBuilder cb = new CustomerBuilder(Validator.getValidName("name"))
        .setSurname1(Validator.getValidName("surname"))
        .setSurname2(Validator.getValidName("second surname"));
        CustomerDAO cd = new CustomerDAO();
        if(!cd.checkExists(cb.build())){
            cb.setPhoneNumber(Validator.getValidPhone())
            .setAddress(Validator.getValidAddress())
            .setCity(Validator.getValidName("city"))
            .setDelegation(Validator.getValidDelegation());
            Customer end = cb.build();
            welcomeCustomer(end.getName(), end.getSurname1(), true);
            return end;
        }else{
            cb.setDelegation(Validator.getValidDelegation());
            Customer end = cb.build();
            welcomeCustomer(end.getName(), end.getSurname1(), false);
            Customer c = cd.getItem(end.getName(), end.getSurname1(), end.getSurname2());
            c.setDelegation(end.getDelegation());
            return c;
        }
    }

    public static void mainMenu(Comanda p){
        printLine();
        System.out.println(p.toString());
        printCompactMenu();
    }

    public static void goodBye(){
        System.out.println("\n\nThankyou for calling Pizzisalle");
        System.out.println("Until we meet again!\n");
    }

    public static void printCompactMenu(){
        printLine();
        System.out.println("\t1. Add Pizza");
        System.out.println("\t2. Add Drinks");
        System.out.println("\t3. Modify Order");
        System.out.println("\t4. Cancel Order");
        System.out.println("\t5. Confirm order");
        System.out.print("Choose Option: ");
    }

    public static void itemAdded(String item){
        printHashtag();
        System.out.println("\t\t" + item + " added to basket.");
        printHashtag();
    }

    public static void printHashtag(){
        System.out.println("###########################################");
    }

    public static void printLine(){
        System.out.println("------------------------------------------");
    }

    public static void addExtras(){
        System.out.println("Do you want to add any extra to your order? ");
        System.out.println("\t1. Yes");
        System.out.println("\t2. No");
        System.out.print("Choice: ");
    }

    public static int checkRepeated(ArrayList<Ingredient> arr, Ingredient i){
        int count =0;
        for(Ingredient ig : arr){
            if(ig.getNom().equals(i.getNom())){
                count++;
            }
        }
        return count;
    }

    public static boolean has(ArrayList<Ingredient> arr, Ingredient i){
        for(Ingredient ig : arr){
            if(ig.getNom().equals(i.getNom())){
                return true;
            }
        }
        return false;
    }

    public static Pizza askExtras(Pizza s){
        int extras=0;
        IngredientDAO id = new IngredientDAO();
        ArrayList<Ingredient> all = id.getAll();
        Pizza original = new Pizza(s.getId(), s.getNom(), s.getIngredients());
        ArrayList<Ingredient> printed = new ArrayList<>();
        ArrayList<Ingredient> allExtras = new ArrayList<>();
        ArrayList<Ingredient> allIngredients = new ArrayList<>(s.getIngredients());
        if(s.getExtra()==null){
            s.setExtra("");
        }
        while((extras!= all.size()+1) && (extras!= all.size()) ){
            printLine();
            System.out.println("Choose from all of our ingredients: ");
            for(int i=0; i< all.size() ;i++){
                if( i%3==0 && i!=0 ){
                    System.out.println();
                }
                String str = (i+1)+". "+all.get(i).getNom();
                System.out.print(str);
                for(int k=str.length(); k<24 ;k++){
                    System.out.print(" ");
                }
            }
            System.out.println();
            String confirm = all.size()+". Confirm";
            System.out.print(confirm);
            for(int k=confirm.length(); k<24 ;k++){
                System.out.print(" ");
            }
            System.out.println((all.size()+1)+". Cancel\n");
            printLine();
            System.out.println("Current ingredients");
            int j=0;
            printed.clear();
            for(Ingredient i : allIngredients){
                if(!has(printed, i)){
                    System.out.println("\t" + (++j) + ". "+ i.getNom() + " x "+checkRepeated(allIngredients, i));
                    printed.add(i);
                }
            }
            printHashtag();
            System.out.println("If you'd like to remove an ingredient add a - at the start. EX: -10");
            System.out.print("Choose your ingredient: ");
            extras = Validator.getValidOption(-all.size(), all.size()+1);
            if((extras!= all.size()+1) && (extras!= all.size())){
                if(extras>0){
                    allExtras.add(all.get(extras-1));
                    allIngredients.add(all.get(extras-1));
                }else{
                    extras = extras*-1;
                    if(Comanda.checkForIngredient(allExtras, all.get(extras-1))){
                        Comanda.removeFirstOcurrance(allExtras, all.get(extras-1));
                        Comanda.removeFirstOcurrance(allIngredients, all.get(extras-1));
                    }else if(Comanda.checkForIngredient(s.getIngredients(), all.get(extras-1))){
                        Comanda.removeFirstOcurrance(s.getIngredients(), all.get(extras-1));
                        Comanda.removeFirstOcurrance(allIngredients, all.get(extras-1));
                        s.setExtra("-" +all.get(extras-1).getNom()+"#" + s.getExtra());
                    }else{
                        System.out.println("This ingredient was not added");
                    }
                }
            }
        }
        s.getIngredients().addAll(allExtras);
        String finalExtras = allExtras.toString();
        finalExtras =  finalExtras.substring(1, finalExtras.length() - 1);
        finalExtras = finalExtras.replaceAll(", ", "#");
        s.setExtra(s.getExtra()+finalExtras);
        printHashtag();
        System.out.println("\n");
        if(extras == all.size()+1){
            return original;
        }
        if(!new Comanda().haveSameIngredients(original.getIngredients(), s.getIngredients())){
            s.setModified(true);
        }
        return s;
    }

    public static Massa askDough(){
        System.out.println("Choose your dough");
        MassaDAO md = new MassaDAO();
        ArrayList<Massa> all = md.getAll();
        int sel = 1;
        for(Massa m : all){
            System.out.println("\t"+ (sel++)+". "+m.getName());
        }
        System.out.print("Choose: ");
        int opt = Validator.getValidOption(1, all.size());
        printHashtag();
        return all.get(opt-1);
    }


    public static void pizzaMenu(ArrayList<Pizza> all){
        printLine();
        int i=0;
        System.out.println("All pizzas available:");
        for(Pizza p : all){
            System.out.println("\t"+(++i)+". "+p.getNom()+" -> "+p.getIngredients().toString()+"");
        }
        i++;
        System.out.println("\t"+(i)+". Exit");
        System.out.print("Enter the number of the pizza you want to order: ");
    }

    public static void drinksMenu(ArrayList<Drink> all){
        printLine();
        int i=0;
        System.out.println("All drinks available:");
        for(Drink p : all){
            System.out.println("\t"+(++i)+". "+p.getName());
        }
        i++;
        System.out.println("\t"+(i)+". Exit");
        System.out.print("Enter the number of the drink you want to order: ");
    }

    public static void confirmCancel(){
        printHashtag();
        System.out.println("Are you sure you want to cancel your order?");
        System.out.println("\t1. Yes");
        System.out.println("\t2. No");
        System.out.print("Choice: ");
    }

    public static void orderCancelled(){
        System.out.println("Your order has been canceled.");
        printHashtag();
    }

    public static void modifyCommand(){
        System.out.println("What category do you want to modify?");
        System.out.println("\t1. Pizzas");
        System.out.println("\t2. Drinks");
        System.out.println("\t3. Cancel");
        System.out.print("Choice: ");
    }

    public static void successfullyChanged(int mc){
        printHashtag();
        if(mc == 1){
            System.out.println("Item was succesfully updated");
        }else{
            System.out.println("Item was succesfully deleted");
        }
        printHashtag();
    }

    public static void chooseItemPizza(ArrayList<Pizza> arr){
        System.out.println("Choose what pizza you want to modify");
        int i=0;
        for(Pizza p : arr){
            System.out.println("\t" + (++i) + ". " + p.getNom() + " -> "+p.getIngredients().toString()+"");
        }
        System.out.println("\t"+ (arr.size()+1)+". Exit");
        System.out.print("Choice: ");
    }

    public static void chooseItemDrinks(ArrayList<Drink> arr){
        System.out.println("What drink would you want to modify?");
        int i=0;
        for(Drink p : arr){
            System.out.println("\t" + (++i) + ". " + p.getName());
        }
        System.out.println("\t"+ (arr.size()+1)+". Exit");
        System.out.print("Choice: ");
    }

    public static void chooseModify(){
        System.out.println("What do you want to do?");
        System.out.println("\t1. Modify Ingredients");
        System.out.println("\t2. Modify Dough");
        System.out.println("\t3. Change amount");
        System.out.println("\t4. Delete item");
        System.out.print("Choice: ");
    }

    public static void chooseModifyDrink(){
        System.out.println("What do you want to do?");
        System.out.println("\t1. Change amount");
        System.out.println("\t2. Delete item");
        System.out.print("Choice: ");
    }

    public static void changeAmount(int amount){
        System.out.println("You currently have " + amount);
        System.out.println("How many more would you like to order? (MAX: "+(10-amount)+")");
        System.out.print("Input the number: ");
    }

    public static void maxReached(){
        printHashtag();
        System.out.println("You have reached the maximum amount of items for this product");
        printHashtag();
    }

    public static void confirmCommand(Comanda c){
        printHashtag();
        System.out.println("Command Confirmed!");
        System.out.println(c.toString());
        printHashtag();
    }

    public static void welcomeCustomer(String name, String surname1, boolean isNew){
        System.out.println("\n");
        printHashtag();
        if(isNew){
            System.out.println("Welcome to PizziSalle " + name + " " + surname1 + "!");
        }else{
            System.out.println("Welcome back to PizziSalle " + name + " " + surname1 + "!");
        }
        printHashtag();
        System.out.println("\n");
    }


}
