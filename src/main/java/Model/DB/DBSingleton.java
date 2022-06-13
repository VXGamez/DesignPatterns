package Model.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*
CLASSE DBSingleton

Aquesta classe és l'encarregada da realitzar la connexió amb la BBDD i mantenir el singleton d'aquesta al llarg de l'execució.

Caldrà canviar el camp password de la linea 25 per executar en el local donat que he tret la meva per penjar-ho.

*/
public class DBSingleton {

    private static Connection conn = null;

    private DBSingleton(){

        String url = "jdbc:mysql://localhost:3306/pizzisalle";
        String driver = "com.mysql.cj.jdbc.Driver";
        String usuario = "root";
        String password = ":)";

        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){

        if (conn == null){
            new DBSingleton();
        }
        return conn;
    }

    public static void closeConnection(){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
