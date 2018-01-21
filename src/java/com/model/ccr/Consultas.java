/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.ccr;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Disble
 */
public class Consultas {
    private static Statement stmt;
    private static ResultSet rs;
    
    private Consultas() {
        
    }
    
    public static boolean insertarDB(String sql) {
        try {
            try {
                stmt = Conexion.abrir().createStatement();
                System.out.println("SQL (insertar) : " + sql);
                if (stmt.executeUpdate(sql) == 1)
                    return true;
                else
                    return false;
            } finally {
                stmt.close();
                Conexion.cerrar();
            }
        } catch (SQLException e) {
            System.out.println("Exception SQL : " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return false;
        }
    }
    
    public static ArrayList<ArrayList<Object>> getFromDB(String sql) {
        return getFromDB(sql, false);
    }
    
    public static ArrayList<ArrayList<Object>> getFromDB(String sql, boolean nombreFilas) {
        try {
            try {
                ArrayList<ArrayList<Object>> resultado = new ArrayList<>();
                stmt = Conexion.abrir().createStatement();
                rs = stmt.executeQuery(sql);
                System.out.println("SQL (get) : " + sql);
                rs = stmt.executeQuery(sql);
                ResultSetMetaData metaData = rs.getMetaData();
                int nroColums = metaData.getColumnCount();
                
                if (nombreFilas) {
                    ArrayList<Object> nombresFilas = new ArrayList<>();
                    for (int columna = 1; columna <= nroColums; columna++) {
                        nombresFilas.add(metaData.getColumnLabel(columna));
                    }
                    resultado.add(nombresFilas);
                }
                
                while (rs.next()) {
                    ArrayList<Object> nuevaFila = new ArrayList<>();
                    for (int fila = 1; fila <= nroColums; fila++) {
                        nuevaFila.add(rs.getObject(fila));
                    }
                    resultado.add(nuevaFila);
                }
                
                return resultado;
            } finally {
                rs.close();
                stmt.close();
                Conexion.cerrar();
            }
        } catch (SQLException e) {
            System.out.println("Exception SQL : " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }
    
    public static int getLastIdTableDB(String sql) {
        try {
            try {
                int id = -1;
                stmt = Conexion.abrir().createStatement();
                System.out.println("SQL (get ID) : " + sql);
                rs = stmt.executeQuery(sql);
                while(rs.next()) {
                    id = rs.getInt(1);
                }
                return id;
            }  finally {
                rs.close();
                stmt.close();
                Conexion.cerrar();
            }
        } catch (SQLException e) {
            System.out.println("Exception SQL : " + e.getMessage());
            return -1;
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return -1;
        }
    }
}
