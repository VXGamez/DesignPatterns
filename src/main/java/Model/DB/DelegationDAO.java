package Model.DB;

import Model.Delegation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
CLASSE DelegationDAO

 DAO encarregada de parlar amb la base de dades centrant-se en la taula Delegation. Implementa la interficie DAO i també crea una instància del singleton de connexió.

*/
public class DelegationDAO implements DAO<Delegation>{

    static Connection con = DBSingleton.getConnection();

    @Override
    public void addItem(Delegation item) {
        /*No afegirem noves delegacions a la bbdd*/
    }

    @Override
    public void updateItem(Delegation item) {
        /*No actualitzarem les delegacions de la bbdd*/
    }

    @Override
    public void removeItem(Delegation item) {
        String query = "DELETE FROM Delegation WHERE name=? OR id_delegation = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, item.getName());
            ps.setInt(2, item.getIdDelegation());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Delegation getItem(int id) {
        String query = "SELECT * FROM Delegation WHERE id_delegation = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Delegation n = null;
            while (rs.next()) {
                String nom = rs.getString("name");
                int dd = rs.getInt("id_delegation");
                n = new Delegation(dd, nom);
            }
            return n;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Delegation getItem(String name) {
        String query = "SELECT * FROM Delegation WHERE name = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Delegation n = null;
            while (rs.next()) {
                String nom = rs.getString("name");
                int dd = rs.getInt("id_delegation");
                n = new Delegation(dd, nom);
            }
            return n;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Delegation> getAll() {
        String query = "SELECT * FROM Delegation";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ArrayList<Delegation> tmp = new ArrayList<>();;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("name");
                int id = rs.getInt("id_delegation");
                tmp.add(new Delegation(id, nom));
            }
            return tmp;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
