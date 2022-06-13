package Model;

/*
CLASSE CustomerBuilder

He emprat aquest patró de disseny per un motiu molt senzill:
- Al iniciar el programa demano nom, cognom i segon cognom.
- Si aquest usuari existeix a la BBDD es retorna la informació restant.
- En cas contrari es segueix demanant la informació del usuari.

Això fa que poguem voler crear un customer per "parts" i anar afegint els paràmetres a mesura que els necessitem.

*/
public class CustomerBuilder {
    private Customer customer;

    public CustomerBuilder(String name){
        this.customer = new Customer(name);
    }

    public CustomerBuilder setId(int s){
        this.customer.setIdCustomer(s);
        return this;
    }

    public CustomerBuilder setSurname1(String s){
        this.customer.setSurname1(s);
        return this;
    }

    public CustomerBuilder setSurname2(String s){
        this.customer.setSurname2(s);
        return this;
    }

    public CustomerBuilder setPhoneNumber(String s){
        this.customer.setPhoneNumber(s);
        return this;
    }

    public CustomerBuilder setAddress(String s){
        this.customer.setAddress(s);
        return this;
    }

    public CustomerBuilder setCity(String s){
        this.customer.setCity(s);
        return this;
    }

    public CustomerBuilder setDelegation(String s){
        this.customer.setDelegation(s);
        return this;
    }

    public Customer build(){
        return this.customer;
    }

}
