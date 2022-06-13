package Model;

/*
CLASSE Massa

Classe model per llegir Massa de la BBDD. No és clonable donat que només hi ha 3 tipus de massa i no n'afegirem més. Ens servirà simplement per assignar la massa a la pizza.

*/
public class Massa {
    private int id;
    private String name;

    public Massa(int id, String name) {
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
}
