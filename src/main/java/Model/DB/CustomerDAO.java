package Model.DB;

import Model.Customer;
import Model.CustomerBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/*
CLASSE CustomerDAO

Aquesta és la primera de les DAOs que torbarem. Implementa la interficie DAO donat que aquesta té un seguit de funcions comunes entre totes les DAOs.

Aquesta classe esta destinada simplement a interaccionar amb la base de dades amb tot lo relacionat amb la taula client, donat que s'ha fet una DAO per taula.

El que sí podrem veure és que recupera una instància de la connexió mitjançant la classe DBSingletonq ue té un singleton intern amb la connexió a la BBDD.

*/
public class CustomerDAO implements DAO<Customer>{

    static Connection con = DBSingleton.getConnection();

    @Override
    public void addItem(Customer item) {
        String query = "INSERT INTO Customer (name, surname1, surname2, phone_number, address, city) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, item.getName());
            ps.setString(2, item.getSurname1());
            ps.setString(3, item.getSurname2());
            ps.setString(4, item.getPhoneNumber());
            ps.setString(5, item.getAddress());
            ps.setString(6, item.getCity());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkExists(Customer id){
        Customer c = getItem(id.getIdCustomer());
        Customer cName = getItem(id.getName(), id.getSurname1(), id.getSurname2());
        if(c == null && cName == null){
            return false;
        }
        return true;
    }

    @Override
    public void updateItem(Customer item) {
        String query = "UPDATE Customer SET name=?, surname1=?, surname2=?, phone_number=?, address=?, city=? WHERE id_customer=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, item.getName());
            ps.setString(2, item.getSurname1());
            ps.setString(3, item.getSurname2());
            ps.setString(4, item.getPhoneNumber());
            ps.setString(5, item.getAddress());
            ps.setString(6, item.getCity());
            ps.setInt(7, item.getIdCustomer());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeItem(Customer item) {
        String query = "DELETE FROM Customer WHERE id_customer = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, item.getIdCustomer());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Customer customerFromRow(ResultSet rs){
        CustomerBuilder cb;
        try {
            int d = rs.getInt("id_customer");
            cb = new CustomerBuilder(rs.getString("name"))
            .setId(d)
            .setSurname1(rs.getString("surname1"))
            .setSurname2(rs.getString("surname2"))
            .setPhoneNumber(rs.getString("phone_number"))
            .setAddress(rs.getString("address"))
            .setCity(rs.getString("city"));
            return cb.build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getItem(int id) {
        String query = "SELECT * FROM Customer WHERE id_customer = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Customer n = null;
            while (rs.next()) {
                n = customerFromRow(rs);
            }
            return n;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Customer getItem(String name, String surname, String surname2) {
        String query = "SELECT * FROM Customer WHERE name = ? AND surname2 = ? AND surname1 = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, surname2);
            ps.setString(3, surname);
            ResultSet rs = ps.executeQuery();
            Customer n = null;
            while (rs.next()) {
                n = customerFromRow(rs);
            }
            return n;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer getItem(String name) {
        /*Com que poden haver-hi múltiples usuaris amb el mateix nom, no implemnto el getItem amb el nom*/
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() {
        String query = "SELECT * FROM Customer";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ArrayList<Customer> tmp = new ArrayList<>();;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tmp.add(customerFromRow(rs));
            }
            return tmp;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
