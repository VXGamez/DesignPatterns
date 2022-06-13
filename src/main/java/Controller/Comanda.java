package Controller;

import Model.*;
import Model.DB.CustomerDAO;
import Model.DB.DelegationDAO;
import Model.DB.OrderDAO;
import Model.DB.OrderItemDAO;
import View.Menus;

import java.util.ArrayList;
import java.util.Calendar;

/*
CLASSE COMANDA

La classe comanda és la classe que conté tota la informació de la sessió. Això vol dir, les dades del client realitzant la comanda i els productes demanats.
Forma part del controlador donat que hi ha molta part de llògica i de gestió d'aquests productes així com afegir-ho a la base de dades.

*/
public class Comanda {
    private ArrayList<Pizza> pizzes;
    private ArrayList<Drink> begudes;
    private Customer client;

    public Comanda(){
        this.pizzes = new ArrayList<>();
        this.begudes = new ArrayList<>();
    }

    public void validateCustomer(Customer c){
        CustomerDAO cd = new CustomerDAO();
        if(!cd.checkExists(c)){
            cd.addItem(c);
        }
        Customer end = cd.getItem(c.getName(), c.getSurname1(), c.getSurname2());
        end.setDelegation(c.getDelegation());
        this.client = end;
    }

    public void confirmCommand(){
        DelegationDAO dd = new DelegationDAO();
        Order order = new Order(new java.sql.Date(Calendar.getInstance().getTime().getTime()), this.client, dd.getItem(this.client.getDelegation()));
        OrderDAO od = new OrderDAO();
        od.addItem(order);
        Order stored = od.getItem(order);
        ArrayList<OrderItem> all = new ArrayList<>();
        for(Pizza p : this.pizzes){
            all.add(new OrderItem(p, p.getMassa(), stored, p.getExtra()));
        }
        for(Drink p : this.begudes){
            all.add(new OrderItem(p, stored));
        }
        OrderItemDAO oid = new OrderItemDAO();
        oid.addBunch(all);
    }

    public String getDelegation(){
        return this.client.getDelegation();
    }


    public ArrayList<Pizza> getPizzes() {
        return pizzes;
    }

    public ArrayList<Drink> getBegudes() {
        return begudes;
    }

    public void addProduct(Object p){
        if(p instanceof Pizza){
            this.pizzes.add((Pizza)p);
        }else if(p instanceof Drink){
            this.begudes.add((Drink)p);
        }
    }

    private int getOrderSize(){
        int size=0;
        if(this.pizzes!=null){
            size += this.pizzes.size();
        }
        if(this.begudes!=null){
            size += this.begudes.size();
        }
        return size;
    }

    public boolean haveSameIngredients(ArrayList<Ingredient> arr1, ArrayList<Ingredient> arr2){
        if(arr1 == null || arr2 == null){
            return false;
        }
        if (arr1.size() != arr2.size()) {
            return false;
        }
        arr1.sort(new IngredientComparator());
        arr2.sort(new IngredientComparator());
        for(int i=0; i<arr1.size() ;i++){
            if(arr1.get(i).getId() != arr2.get(i).getId()){
                return false;
            }
        }
        return true;
    }

    public static void removeFirstOcurrance(ArrayList<Ingredient> arr1, Ingredient i){
        int pos=-1;
        for(Ingredient ingr : arr1){
            pos++;
            if(ingr.getNom().equals(i.getNom()) && ingr.getId() == i.getId()){
               break;
            }
        }
        if(pos != -1){
            arr1.remove(pos);
        }
    }

    public static boolean checkForIngredient(ArrayList<Ingredient> arr1, Ingredient i){
        boolean hasIngredient=false;
        for(Ingredient ingr : arr1){
            if(ingr.getNom().equals(i.getNom()) && ingr.getId() == i.getId()){
                return true;
            }
        }
        return hasIngredient;
    }

    public int findOcurrences(Object p){
        int count = 0;
        if(p instanceof Drink){
            Drink s = (Drink)p;
            for(Drink d : this.begudes){
                if(d.getName().equals(s.getName())){
                    count++;
                }
            }
        }else if(p instanceof Pizza){
            Pizza s = (Pizza)p;
            for(Pizza pz : this.pizzes){
                if(pz.getNom().equals(s.getNom()) && pz.getMassa().getName().equals(s.getMassa().getName()) && haveSameIngredients(pz.getIngredients(), s.getIngredients())){
                    count++;
                }
            }
        }
        return count;
    }


    public void addMoreDrinks(int amount, Drink p){
        for(int i=0; i<amount ;i++){
            this.begudes.add(p.clone());
        }
    }


    public void addMore(int amount, Pizza p){
        for(int i=0; i<amount ;i++){
            this.pizzes.add(p.clone());
        }
    }

    public boolean has(Object arr, Object p){
        if(p instanceof Pizza){
            Pizza obj = (Pizza)p;
            ArrayList<Pizza> ar = (ArrayList<Pizza>)arr;
            for (Pizza o : ar){
                if(o.getNom().equals(obj.getNom()) && o.getMassa().getName().equals(obj.getMassa().getName()) && haveSameIngredients(o.getIngredients(), obj.getIngredients())){
                    return true;
                }
            }
        }else if(p instanceof Drink){
            Drink obj = (Drink)p;
            ArrayList<Drink> ar = (ArrayList<Drink>)arr;
            for (Drink o : ar){
                if(o.getName().equals(obj.getName())){
                    return true;
                }
            }
        }else if(p instanceof Ingredient){
            Ingredient obj = (Ingredient)p;
            ArrayList<Ingredient> ar = (ArrayList<Ingredient>)arr;
            for (Ingredient o : ar){
                if(o.getNom().equals(obj.getNom())){
                    return true;
                }
            }
        }
        return false;
    }

    private String getIngredients(ArrayList<Ingredient> ingr){
        String str="[ ";
        ArrayList<Ingredient> added = new ArrayList<>();
        for(Ingredient i : ingr){
            if(!has(added, i)){
                str+=i.getNom() +" x "+ Menus.checkRepeated(ingr, i) +", ";
                added.add(i);
            }
        }
        str = str.substring(0, str.length()-2);
        str+=" ]";
        return str;
    }



    @Override
    public String toString() {
        ArrayList<Pizza> pShown = new ArrayList<>();
        ArrayList<Drink> dShown = new ArrayList<>();
        int i=0;
        String str= "Your order has a total of " + getOrderSize() + " products\n";
        if(this.pizzes != null && this.pizzes.size()>0){
            str+="\nPizzas\n";
            for(Pizza p : this.pizzes){
                if(!has(pShown, p)){
                    str+="\t"+ (++i)+ ". "+p.getNom()+" |Massa "+ p.getMassa().getName() + "| x "+findOcurrences(p)+" -> "+ getIngredients(p.getIngredients()) +"\n";
                    pShown.add(p);
                }
            }
        }
        if(this.begudes != null && this.begudes.size()>0){
            str+="\nDrinks\n";
            i=0;
            for(Drink d : this.begudes){
                if(!has(dShown, d)){
                    str+="\t"+ (++i)+ ". " +d.getName()+" x "+findOcurrences(d)+"\n";
                    dShown.add(d);
                }
            }
        }
        return str;
    }
}
