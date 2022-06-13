package Model;

/*
CLASSE Delegation

Classe model per llegir una delegació de la BBDD

*/
public class Delegation {
    private int idDelegation;
    private String name;

    public Delegation(int idDelegation, String name) {
        this.idDelegation = idDelegation;
        this.name = name;
    }

    public int getIdDelegation() {
        return idDelegation;
    }

    public void setIdDelegation(int idDelegation) {
        this.idDelegation = idDelegation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
