/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Orlando
 */
public class Conexion {
    public static Connection getConexionBD() throws ClassNotFoundException {
        try {

            String url = "jdbc:mysql://localhost:3306/dbpawsons";
            String user = "root";
            String password = "root";
            Class.forName("com.mysql.jdbc.Driver");
            Connection cnn = (Connection) DriverManager.getConnection(url, user, password);
            cnn.setAutoCommit(false);

            return cnn;

        } catch (SQLException ex) {

        }
        return null;
        
    }
    
    public static void desconectarBD(Connection cnn){
        if(cnn != null ){
            try {
                cnn.commit();
                cnn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void revesarCambioBD(Connection cnn){
        if(cnn != null ){
            try {
                cnn.rollback();
                cnn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
