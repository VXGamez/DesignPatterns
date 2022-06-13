package Model.DB;

import Model.Ingredient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
CLASSE IngredientDAO

 DAO encarregada de parlar amb la base de dades centrant-se en la taula Drink. Implementa la interficie DAO i també crea una instància del singleton de connexió.

*/
public class IngredientDAO implements DAO<Ingredient>{

    static Connection con = DBSingleton.getConnection();

    @Override
    public void addItem(Ingredient item) {
        /*No afegirem nous ingredients a la bbdd*/
    }

    @Override
    public void updateItem(Ingredient item) {
        /*No actualitzarem els ingredients de la bbdd*/
    }

    @Override
    public void removeItem(Ingredient item) {
        String query = "DELETE FROM Ingredient WHERE name=? OR id_ingredient = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, item.getNom());
            ps.setInt(2, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ingredient getItem(int id) {
        String query = "SELECT * FROM Ingredient WHERE id_ingredient = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Ingredient n = null;
            while (rs.next()) {
                String nom = rs.getString("name");
                int dd = rs.getInt("id_ingredient");
                n = new Ingredient(dd, nom);
            }
            return n;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Ingredient getItem(String name) {
        String query = "SELECT * FROM Ingredient WHERE name = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Ingredient n = null;
            while (rs.next()) {
                String nom = rs.getString("name");
                int dd = rs.getInt("id_ingredient");
                n = new Ingredient(dd, nom);
            }
            return n;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Ingredient> getAll() {
        String query = "SELECT * FROM Ingredient";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ArrayList<Ingredient> tmp = new ArrayList<>();;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("name");
                int id = rs.getInt("id_ingredient");
                tmp.add(new Ingredient(id, nom));
            }
            return tmp;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
