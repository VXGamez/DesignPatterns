package Model;

/*
CLASSE OrderItem

La classe OrderItem és també una classe per llegir i escriure de la BBDD. No te gaire carecterística especial no comentada anteriorment.

*/
public class OrderItem {
    private int idOrderItem;
    private Pizza pizza;
    private Massa massa;
    private Drink drink;
    private Order order;
    private String extra;

    public OrderItem() {

    }

    public OrderItem(Drink drink, Order order) {
        this.idOrderItem=-1;
        this.drink = drink;
        this.order = order;
    }

    public OrderItem(Pizza pizza, Massa massa, Order order, String extra) {
        this.idOrderItem=-1;
        this.pizza = pizza;
        this.massa = massa;
        this.order = order;
        this.extra = extra;
    }

    public OrderItem(Pizza pizza, Massa massa, Drink drink, Order order, String extra) {
        this.idOrderItem = -1;
        this.pizza = pizza;
        this.massa = massa;
        this.drink = drink;
        this.order = order;
        this.extra = extra;
    }

    public OrderItem(int idOrderItem, Pizza pizza, Massa massa, Drink drink, Order order, String extra) {
        this.idOrderItem = idOrderItem;
        this.pizza = pizza;
        this.massa = massa;
        this.drink = drink;
        this.order = order;
        this.extra = extra;
    }

    public int getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(int idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Massa getMassa() {
        return massa;
    }

    public void setMassa(Massa massa) {
        this.massa = massa;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
