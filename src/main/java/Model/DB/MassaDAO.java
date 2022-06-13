package Model.DB;

import Model.Drink;
import Model.Massa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
CLASSE MassaDAO

 DAO encarregada de parlar amb la base de dades centrant-se en la taula Massa. Implementa la interficie DAO i també crea una instància del singleton de connexió.

*/
public class MassaDAO implements DAO<Massa>{

    static Connection con = DBSingleton.getConnection();

    @Override
    public void addItem(Massa item) {
        /*No afegirem noves masses a la bbdd*/
    }

    @Override
    public void updateItem(Massa item) {
        /*No actualitzarem els valors de una massa a la bbdd*/
    }

    @Override
    public void removeItem(Massa item) {
        String query = "DELETE FROM Massa WHERE name=? OR id_massa = ?";
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
    public Massa getItem(int id) {
        String query = "SELECT * FROM Massa WHERE id_massa = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Massa n = null;
            while (rs.next()) {
                String nom = rs.getString("name");
                int dd = rs.getInt("id_massa");
                n = new Massa(dd, nom);
            }
            return n;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Massa getItem(String name) {
        String query = "SELECT * FROM Massa WHERE name = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Massa n = null;
            while (rs.next()) {
                String nom = rs.getString("name");
                int dd = rs.getInt("id_massa");
                n = new Massa(dd, nom);
            }
            return n;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Massa> getAll() {
        String query = "SELECT * FROM Massa";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ArrayList<Massa> tmp = new ArrayList<>();;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("name");
                int id = rs.getInt("id_massa");
                tmp.add(new Massa(id, nom));
            }
            return tmp;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
