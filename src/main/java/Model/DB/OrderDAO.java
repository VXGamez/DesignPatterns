package Model.DB;

import Model.*;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/*
CLASSE OrderDAO

 DAO encarregada de parlar amb la base de dades centrant-se en la taula Order. Implementa la interficie DAO i també crea una instància del singleton de connexió.

*/
public class OrderDAO implements DAO<Order>{

    static Connection con = DBSingleton.getConnection();

    @Override
    public void addItem(Order item) {
        String query = "INSERT INTO COrder (id_customer, id_delegation) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            //ps.setDate(1, item.getDate());
            ps.setInt(1, item.getCustomer().getIdCustomer());
            ps.setInt(2, item.getDelegation().getIdDelegation());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateItem(Order item) {
        String query = "UPDATE COrder SET datetime=?, id_customer=?, id_delegation=? WHERE id_order=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setDate(1, item.getDate());
            ps.setInt(2, item.getCustomer().getIdCustomer());
            ps.setInt(3, item.getDelegation().getIdDelegation());
            ps.setInt(4, item.getIdOrder());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeItem(Order item) {
        String query = "DELETE FROM COrder WHERE id_order = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, item.getIdOrder());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order getItem(int id) {
        String query = "SELECT * FROM COrder WHERE id_order = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Order n = null;
            while (rs.next()) {
                n = makeOrderFromRow(rs);
            }
            return n;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Order getItem(Order item) {
        String query = "SELECT * FROM COrder WHERE datetime = ? AND id_customer = ? AND id_delegation=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = dateFormat.format(item.getDate());
            ps.setString(1, date);
            ps.setInt(2, item.getCustomer().getIdCustomer());
            ps.setInt(3, item.getDelegation().getIdDelegation());
            ResultSet rs = ps.executeQuery();
            Order n = null;
            while (rs.next()) {
                n = makeOrderFromRow(rs);
            }
            return n;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Order getItem(String name) {
        /*La order no se li farà mai un get pel nom*/
        return null;
    }

    private Order makeOrderFromRow(ResultSet rs){
        try {
            int id = rs.getInt("id_order");
            Date date = rs.getDate("datetime");
            int idCustomer = rs.getInt("id_customer");
            int idDelegation = rs.getInt("id_delegation");
            CustomerDAO cd = new CustomerDAO();
            DelegationDAO dd = new DelegationDAO();
            Customer c = cd.getItem(idCustomer);
            Delegation d = dd.getItem(idDelegation);
            return new Order(id, date, c, d);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Order> getAll() {
        String query = "SELECT * FROM COrder";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ArrayList<Order> tmp = new ArrayList<>();;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tmp.add(makeOrderFromRow(rs));
            }
            return tmp;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
