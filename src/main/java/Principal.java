import Controller.Comanda;
import Controller.Validator;
import Model.*;
import Model.DB.DrinkDAO;
import Model.DB.PizzaDAO;
import View.Menus;
import java.util.ArrayList;


/*
CLASSE Principal

El motiu de que aquesta classe es digui Principal i no Main és un error que tinc al meu IDE que no em deixa posar el nom Main a una classe. Tinc pendent solucionar-ho.

És, essencialment, el main del progrma. Qui instancia al controlador i té també una part de llògica de en quin moment cridar cada funció.

Realment es podria haver fet una classe Programa, on podria haver creat una funció startProgram() que contingués tot això però m'ha semblat que no era necessari, donat que no faria servir per gaire més aquella funció.

*/
public class Principal {

    public static void main(String[] args) {
        Comanda command = new Comanda();
        Menus.intro();
        Customer c = Menus.getUserInfo();
        command.validateCustomer(c);
        int opt=0;
        int modify=0;
        while(opt!=4 && opt!=5){
            Menus.mainMenu(command);
            opt = Validator.getValidOption(1, 5);
            switch (opt) {
                case 1 -> {
                    int optP = 0;
                    int extras = 0;
                    opt = 0;
                    PizzaDAO p = new PizzaDAO();
                    ArrayList<Pizza> all = p.getAll();
                    Validator.clearNonDelegationPizzas(command.getDelegation(), all);
                    Menus.pizzaMenu(all);
                    optP = Validator.getValidOption(1, all.size() + 1);
                    if (optP != all.size() + 1) {
                        Pizza selected = all.get(optP - 1);
                        Massa dough = Menus.askDough();
                        selected.setMassa(dough);
                        Menus.addExtras();
                        extras = Validator.getValidOption(1, 2);
                        if (extras == 1) {
                            selected = Menus.askExtras(selected);
                        }
                        if (command.findOcurrences(selected) < 10) {
                            Menus.itemAdded(selected.getNom());
                            command.addProduct(selected);
                        } else {
                            Menus.maxReached();
                        }
                    }
                }
                case 2 -> {
                    DrinkDAO dd = new DrinkDAO();
                    ArrayList<Drink> allD = dd.getAll();
                    Menus.drinksMenu(allD);
                    int optD = Validator.getValidOption(1, allD.size() + 1);
                    if (optD != allD.size() + 1) {
                        if (command.findOcurrences(allD.get(optD - 1)) < 10) {
                            Menus.itemAdded(allD.get(optD - 1).getName());
                            command.addProduct(allD.get(optD - 1));
                        } else {
                            Menus.maxReached();
                        }
                    }
                }
                case 3 -> {
                    Menus.modifyCommand();
                    int mc = Validator.getValidOption(1, 3);
                    switch (mc) {
                        case 1 -> {
                            Menus.chooseItemPizza(command.getPizzes());
                            int itemChoice = Validator.getValidOption(1, command.getPizzes().size() + 1);
                            if (itemChoice != command.getPizzes().size() + 1) {
                                Menus.chooseModify();
                                modify = Validator.getValidOption(1, 4);
                                switch (modify) {
                                    case 1 -> command.getPizzes().set(itemChoice - 1, Menus.askExtras(command.getPizzes().get(itemChoice - 1)));
                                    case 2 -> {
                                        Massa dough = Menus.askDough();
                                        command.getPizzes().get(itemChoice - 1).setMassa(dough);
                                    }
                                    case 3 -> {
                                        int ocurrences = command.findOcurrences(command.getPizzes().get(itemChoice - 1));
                                        if (ocurrences < 10) {
                                            Menus.changeAmount(ocurrences);
                                            int newAmount = Validator.getValidOption(0, 10 - ocurrences);
                                            command.addMore(newAmount, command.getPizzes().get(itemChoice - 1));
                                        } else {
                                            Menus.maxReached();
                                        }
                                    }
                                    case 4 -> command.getPizzes().remove(itemChoice - 1);
                                }
                            }
                        }
                        case 2 -> {
                            Menus.chooseItemDrinks(command.getBegudes());
                            int itemDrink = Validator.getValidOption(1, command.getBegudes().size() + 1);
                            if (itemDrink != command.getBegudes().size() + 1) {
                                Menus.chooseModifyDrink();
                                modify = Validator.getValidOption(1, 2);
                                switch (modify) {
                                    case 1 -> {
                                        int ocurrences = command.findOcurrences(command.getBegudes().get(itemDrink - 1));
                                        if (ocurrences < 10) {
                                            Menus.changeAmount(ocurrences);
                                            int newAmount = Validator.getValidOption(0, 10 - ocurrences);
                                            command.addMoreDrinks(newAmount, command.getBegudes().get(itemDrink - 1));
                                        } else {
                                            Menus.maxReached();
                                        }
                                    }
                                    case 2 -> command.getBegudes().remove(itemDrink - 1);
                                }

                            }
                        }
                        default -> {
                        }
                    }
                    Menus.successfullyChanged(mc);
                }
                case 4 -> {
                    Menus.confirmCancel();
                    int cancel = Validator.getValidOption(1, 2);
                    Menus.printHashtag();
                    if (cancel == 2) {
                        opt = 0;
                    } else {
                        Menus.orderCancelled();
                    }
                }
                case 5 -> {
                    command.confirmCommand();
                    Menus.confirmCommand(command);
                }
                default -> {
                }
            }
        }

        Menus.goodBye();

    }

}
