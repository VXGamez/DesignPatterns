package Model.DB;

import java.util.ArrayList;

/*
INTERFICIE DAO

Aquesta interficie és emprada per totes les DAOs. Permet implementar les funcions indicades a totes les classes i poder interaccionar amb la BBDD.

El que fa és implementar el objecte abstracte T de tal manera que cada una de les classes que implementi aquesta interficie podrà operar amb el seu objecte.

Hi ha funcions com la updateItem i la removeItem que no han estat implementades a cap de les classes donat que no alterem el que hi ha a la base de dades ni per eliminar-ho ni per actualitzar-ho.

Les he deixades igualemnt ja que considero que així aquesta interfície estaria completa, i si es volgués fer servir aquestes funcions en un futur es podria.

*/
public interface DAO<T> {
    void addItem(T item);
    void updateItem(T item);
    void removeItem(T item);
    T getItem(int id);
    T getItem(String name);
    ArrayList<T> getAll();
}
