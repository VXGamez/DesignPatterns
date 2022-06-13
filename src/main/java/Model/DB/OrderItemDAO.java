package Model.DB;

import Model.*;

import java.sql.*;
import java.util.ArrayList;

/*
CLASSE OrderItemDAO

 DAO encarregada de parlar amb la base de dades centrant-se en la taula OrderItem. Implementa la interficie DAO i també crea una instància del singleton de connexió.

*/
public class OrderItemDAO implements DAO<OrderItem>{

    static Connection con = DBSingleton.getConnection();

    @Override
    public void addItem(OrderItem item) {
        String query = "INSERT INTO OrderItem (id_pizza, id_massa, id_drink, extra, id_order) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, item.getPizza().getId());
            ps.setInt(2, item.getMassa().getId());
            ps.setInt(3, item.getDrink().getId());
            ps.setString(4, item.getExtra());
            ps.setInt(5, item.getOrder().getIdOrder());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItemDrink(OrderItem item) {
        String query = "INSERT INTO OrderItem (id_drink, id_order) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, item.getDrink().getId());
            ps.setInt(2, item.getOrder().getIdOrder());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItemPizza(OrderItem item) {
        String query = "INSERT INTO OrderItem (id_pizza, id_massa, extra, id_order) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, item.getPizza().getId());
            ps.setInt(2, item.getMassa().getId());
            ps.setString(3, item.getExtra());
            ps.setInt(4, item.getOrder().getIdOrder());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBunch(ArrayList<OrderItem> bunch){
        for(OrderItem oi : bunch){
            if(oi.getDrink() == null){
                addItemPizza(oi);
            }else{
                addItemDrink(oi);
            }
        }
    }

    @Override
    public void updateItem(OrderItem item) {
        String query = "UPDATE OrderItem SET id_pizza=?, id_massa=?, id_drink=?, id_order=?, extra=? WHERE id_order_item=?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, item.getPizza().getId());
            ps.setInt(2, item.getMassa().getId());
            ps.setInt(3, item.getDrink().getId());
            ps.setInt(4, item.getOrder().getIdOrder());
            ps.setString(5, item.getExtra());
            ps.setInt(6, item.getIdOrderItem());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void removeItem(OrderItem item) {
        String query = "DELETE FROM OrderItem WHERE id_order_item = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, item.getIdOrderItem());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem getItem(int id) {
        String query = "SELECT * FROM OrderItem WHERE id_order_item = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            OrderItem n = null;
            while (rs.next()) {
                n = makeOrderItemFromRow(rs);
            }
            return n;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderItem getItem(String name) {
        /*No farem mai un get item amb el nom donat que la comanda no te nom*/
        return null;
    }

    private OrderItem makeOrderItemFromRow(ResultSet rs){
        try {
            int id = rs.getInt("id_order_item");
            int idPizza = rs.getInt("id_pizza");
            int idMassa = rs.getInt("id_massa");
            int idDrink = rs.getInt("id_drink");
            int idOrder = rs.getInt("id_order");
            String extra = rs.getString("extra");
            PizzaDAO pd = new PizzaDAO();
            MassaDAO md = new MassaDAO();
            DrinkDAO dd = new DrinkDAO();
            OrderDAO od = new OrderDAO();
            Pizza p = pd.getItem(idPizza);
            Massa m = md.getItem(idMassa);
            Drink d = dd.getItem(idDrink);
            Order o = od.getItem(idOrder);
            return new OrderItem(id, p,m,d,o,extra);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<OrderItem> getAll() {
        String query = "SELECT * FROM OrderItem";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(query);
            ArrayList<OrderItem> tmp = new ArrayList<>();;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tmp.add(makeOrderItemFromRow(rs));
            }
            return tmp;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
