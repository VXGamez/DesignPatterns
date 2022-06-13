package Model;

import java.util.ArrayList;

/*
CLASSE Pizza

De manera similar a Drink, la classe pizza és una classe del model emprada per llegir i escriure a la BBDD però que implementa la interfície Producte.

Per pizza també s'ha emprat prototype donat que també es podrà alterar la quantitat d'elements de cada tipus, aquest cas le mètode clone retorna una nova instància d'una pizza.

*/
public class Pizza implements Producte<Pizza>{
    private int id;
    private String nom;
    private ArrayList<Ingredient> ingredients;
    private Massa massa;
    private boolean modified;
    private String extra;

    public Pizza(){
        this.modified=false;
        this.extra="";
    }

    public Pizza(Pizza p){
        this.id = p.getId();
        this.nom = p.getNom();
        this.ingredients = p.getIngredients();
        this.massa = p.getMassa();
        this.modified=p.isModified();
        this.extra = p.getExtra();
    }


    public Massa getMassa() {
        return massa;
    }

    public void setMassa(Massa massa) {
        this.massa = massa;
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public Pizza(int id, String nom, ArrayList<Ingredient> ingredients) {
        this.id = id;
        this.nom = nom;
        this.ingredients = ingredients;
        this.modified=false;
        this.extra="";
    }

    public Pizza(int id, String nom) {
        this.id = id;
        this.nom = nom;
        this.modified=false;
        this.extra="";
    }

    public Pizza(String nom){
        this.nom = nom;
        this.ingredients = new ArrayList<>();
        this.modified=false;
        this.extra="";
    }

    public Pizza(String nom, ArrayList<Ingredient> ingredients) {
        this.nom = nom;
        this.ingredients = ingredients;
        this.modified=false;
        this.extra="";
    }

    public void removeIngredient(Ingredient i){
        for(Ingredient ig : this.ingredients){
            if(ig.getNom().equals(i.getNom()) && ig.getId() == i.getId()){
                this.ingredients.remove(ig);
                break;
            }
        }
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = (ArrayList<Ingredient>) ingredients.clone();
    }

    @Override
    public String toString() {
        return "Model.Pizza{" +
                "nom='" + nom + '\'' +
                ", ingredients=" + ingredients.toString() +
                '}';
    }

    @Override
    public Pizza clone() {
        return new Pizza(this);
    }
}
