package Model;

/*
CLASSE Drink

Classe model per llegir begudes de la BBDD.

Aquesta classe implementa una interfície Producte donat que faig servir el patró de Prototype.

Faig servir aquest patró perque es podrà alterar la quantitat de begudes al cistell de la compra, i la manera més fàcil será clonar-les.

Per fer-ho la interfície producte exté de Clonable e implementa la funció clone. Per tant Drink, al implementar Producte, té la funció clone que retorna una copia de si mateixa.

*/
public class Drink implements Producte<Drink>{
    private int id;
    private String name;

    public Drink() {
    }

    public Drink(String name) {
        this.name = name;
    }

    public Drink(Drink d) {
        this.name = d.getName();
        this.id = d.getId();
    }

    public Drink(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Drink clone() {
        return new Drink(this);
    }
}
