package Model.DB;

import Model.Drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
CLASSE DrinkDAO

 DAO encarregada de parlar amb la base de dades centrant-se en la taula Drink. Implementa la interficie DAO i també crea una instància del singleton de connexió.

*/
public class DrinkDAO implements DAO<Drink>{

    static Connection con = DBSingleton.getConnection();


    @Override
    public void addItem(Drink item) {
        /*No afegirem noves begudes a la bbdd*/
    }

    @Override
    public void updateItem(Drink item) {
        /*No actualitzarem els valors de una beguda a la bbdd*/
    }

    @Override
    public void removeItem(Drink item) {
        String query = "DELETE FROM Drink WHERE name=? OR id_drink = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, item.getName());
            ps.setInt(2, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Drink getItem(int id) {
        String query = "SELECT * FROM Drink WHERE id_drink = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Drink n = null;
            while (rs.next()) {
                String nom = rs.getString("name");
                int dd = rs.getInt("id_drink");
                n = new Drink(dd, nom);
            }
            return n;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Drink getItem(String name) {
        String query = "SELECT * FROM Drink WHERE name = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Drink n = null;
            while (rs.next()) {
                String nom = rs.getString("name");
                int dd = rs.getInt("id_drink");
                n = new Drink(dd, nom);
            }
            return n;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Drink> getAll() {
        String query = "SELECT * FROM Drink";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ArrayList<Drink> tmp = new ArrayList<>();;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("name");
                int id = rs.getInt("id_drink");
                tmp.add(new Drink(id, nom));
            }
            return tmp;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
