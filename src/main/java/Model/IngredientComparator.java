package Model;

import java.util.Comparator;

/*
CLASSE IngredientComparator

Per comparar ingredients entre sí, i poder-los endreçar correctament.

Inicialment vaig realitzar aquest comparador d'una necessitat real a nivell de codi però ha resultat també ser un patró de disseny de tipus estratègic.

*/
public class IngredientComparator implements Comparator<Ingredient> {
    @Override
    public int compare(Ingredient o1, Ingredient o2) {
        return o1.getId() - o2.getId();
    }
}