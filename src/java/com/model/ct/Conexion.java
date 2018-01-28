/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.ct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Disble
 */
public class Conexion {
    private static Connection conn;
    private Conexion(){
    }
    
    static public Connection abrir(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;dataBaseName=PIVPROJECT", "sa", "root");
            //System.out.println("-----------------------Conexion Abierta-----------------------");
        } catch (SQLException ex) {
            System.out.println("SQL Exception : " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
        }
        return conn;
    }
    
    static public void cerrar(){
        try {
            conn.close();
            //System.out.println("-----------------------Conexion Cerrada-----------------------");
        } catch (SQLException ex) {
            System.out.println("SQL Exception : " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
        }
    }
}
