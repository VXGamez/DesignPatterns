package Model.DB;

import Model.Ingredient;
import Model.Pizza;

import java.sql.*;
import java.util.ArrayList;

/*
CLASSE PizzaDAO

 DAO encarregada de parlar amb la base de dades centrant-se en la taula Pizza. Implementa la interficie DAO i també crea una instància del singleton de connexió.

*/
public class PizzaDAO implements DAO<Pizza>{
    static Connection con = DBSingleton.getConnection();

    @Override
    public void addItem(Pizza item) {
        /*No afegirem noves pizzes a la bbdd*/
    }

    @Override
    public void updateItem(Pizza item) {
        /*No actualitzarem els valors de una pizza a la bbdd*/
    }

    @Override
    public void removeItem(Pizza item) {
        String query = "DELETE FROM Pizza WHERE name=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, item.getNom());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pizza getItem(int id) {
        String query = "SELECT tmp.id_ingredient AS idIngredient, tmp.name AS nomIngredient, p.name AS nomPizza, p.id_pizza AS idPizza FROM (SELECT * FROM Ingredient WHERE " +
                "id_ingredient IN (select ip.id_ingredient from PizzaItem AS ip, Pizza AS p WHERE p.id_pizza = ip.id_pizza AND p.id_pizza= ?)) AS tmp, Pizza AS p WHERE p.id_pizza = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, id);
            Pizza emp = new Pizza();
            ArrayList<Ingredient> tmp = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            boolean check = false;
            while (rs.next()) {
                if(check == false){
                    emp.setNom(rs.getString("nomPizza"));
                    emp.setId(rs.getInt("idPizza"));
                }
                Ingredient i = new Ingredient();
                i.setId(rs.getInt("idIngredient"));
                i.setNom(rs.getString("nomIngredient"));
                tmp.add(i);
                check = true;
            }
            if (check == true) {
                emp.setIngredients(tmp);
                return emp;
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Pizza getItem(String name) {
        String query = "SELECT tmp.id_ingredient AS idIngredient, tmp.name AS nomIngredient, p.name AS nomPizza, p.id_pizza AS idPizza FROM (SELECT * FROM Ingredient WHERE " +
                "id_ingredient IN (select ip.id_ingredient from PizzaItem AS ip, Pizza AS p WHERE p.id_pizza = ip.id_pizza AND p.name= ?)) AS tmp, Pizza AS p WHERE p.name = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, name);
            Pizza emp = new Pizza();
            ArrayList<Ingredient> tmp = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            boolean check = false;
            while (rs.next()) {
                if(check == false){
                    emp.setNom(rs.getString("nomPizza"));
                    emp.setId(rs.getInt("idPizza"));
                }
                Ingredient i = new Ingredient();
                i.setId(rs.getInt("idIngredient"));
                i.setNom(rs.getString("nomIngredient"));
                tmp.add(i);
                check = true;
            }
            if (check == true) {
                emp.setIngredients(tmp);
                return emp;
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Pizza> getAll() {
        String query = "SELECT p.name AS nomPizza,p.id_pizza AS idPizza, i.id_ingredient AS idIngredient, i.name AS nomIngredient FROM Pizza AS p, PizzaItem AS ip, Ingredient AS i WHERE ip.id_pizza = p.id_pizza AND ip.id_ingredient = i.id_ingredient ORDER BY p.name";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ArrayList<Pizza> done = new ArrayList<>();

            Pizza emp = null;
            ArrayList<Ingredient> tmp = new ArrayList<>();;
            ResultSet rs = ps.executeQuery();
            boolean check = false;
            String prevName = "";
            while (rs.next()) {
                String nom = rs.getString("nomPizza");
                if(!prevName.equals(nom)){
                    if(emp != null){
                        emp.setIngredients(tmp);
                        tmp.clear();
                        done.add(emp);
                    }
                    prevName = nom;
                    int id = rs.getInt("idPizza");
                    emp = new Pizza(id, nom);
                }
                Ingredient i = new Ingredient();
                i.setId(rs.getInt("idIngredient"));
                i.setNom(rs.getString("nomIngredient"));
                tmp.add(i);
                check = true;
            }
            if (check == true) {
                return done;
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
