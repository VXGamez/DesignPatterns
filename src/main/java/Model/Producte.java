package Model;

/*
INTERFICIE Producte

Aquesta interficie em permet fer ús del patró de disseny Prototype.

D'aquesta manera aquelles classes que l'implementen poden clonar les seves instàncies amb facilitat.

Fa ús de la classe abstracte T per que pugui ser una interfície genèrica i servir per tot tipus d'objectes. Es podria haver fet que clone retornés Object però això implicaria la necessitat de fer cast en un futur.

*/
public interface Producte<T> extends Cloneable {
    T clone();
}
