package Model;

import java.sql.Date;

/*
CLASSE Order

Novament, tenim una classe model per llegir dades de la base de dades. En aquest cas sobretot ens servirà per escriure-hi donat que no es demana la funcionalitat de consultar el històric.

Comentar que tot i que a la base de dades els parametres customer i delegation estan com a ids, és interessant tenir el objecte complert per estalviar-nos queries futures.

*/
public class Order {
    private int idOrder;
    private Date date;
    private Customer customer;
    private Delegation delegation;

    public Order(){

    }

    public Order(Date date, Customer customer, Delegation delegation) {
        this.idOrder = -1;
        this.date = date;
        this.customer = customer;
        this.delegation = delegation;
    }

    public Order(int idOrder, Date date, Customer customer, Delegation delegation) {
        this.idOrder = idOrder;
        this.date = date;
        this.customer = customer;
        this.delegation = delegation;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Delegation getDelegation() {
        return delegation;
    }

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
    }
}
