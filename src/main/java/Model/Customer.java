package Model;

/*
CLASSE Customer

Aquesta classe és simplement un model del que hi ha a la BBDD del que és un customer.
S'instanciarà al principi del programa, mitjançant un builder, per saber quin client ens està comprant els productes.

*/
public class Customer {
    private int idCustomer;
    private String name;
    private String surname1;
    private String surname2;
    private String phoneNumber;
    private String address;
    private String city;

    //Ho deso aqui donat que ho demano per pantalla al inici del programa i aquesta classe retornarà les dades demanades per pantalla.
    //Si la delegació fos una aspecte fixa't per cada una d'elles al inici, la posaria al main del programa com una constant però com no m'ha quedat clar la seva utilitat, la demano per pantalla.
    //Imagino que "som els que atenen al telèfon" pel que omplim la delegació de la que formem part.
    private String delegation;

    public Customer(String name, String surname1, String surname2, String phoneNumber, String address, String city, String delegation) {
        this.idCustomer = -1;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.delegation = delegation;
    }

    public Customer(int idCustomer, String name, String surname1, String surname2) {
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
    }

    public Customer(String name) {
        this.name = name;
    }

    public Customer(int idCustomer, String name, String surname1, String surname2, String phoneNumber, String address, String city) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
    }

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
