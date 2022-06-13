package Model;

/*
CLASSE Ingredient

Classe model per llegir els ingredients de la BBDD. Tot i moder afegir múltiples ingredients del mateix tipus, tal i com esta implementada la gestió dels extres, no té sentit fer servir prototype aqui.

*/
public class Ingredient {
    private int id;
    private String nom;

    public Ingredient(){

    }

    public Ingredient(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
