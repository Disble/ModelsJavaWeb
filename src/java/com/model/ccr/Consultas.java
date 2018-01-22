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
    
    public static ArrayList<ArrayList<Object>> getFromDB(String sql, boolean nombreColumnas) {
        try {
            try {
                ArrayList<ArrayList<Object>> resultado = new ArrayList<>();
                stmt = Conexion.abrir().createStatement();
                rs = stmt.executeQuery(sql);
                System.out.println("SQL (get) : " + sql);
                rs = stmt.executeQuery(sql);
                ResultSetMetaData metaData = rs.getMetaData();
                int nroColums = metaData.getColumnCount();
                
                if (nombreColumnas) {
                    ArrayList<Object> nombresFilas = new ArrayList<>();
                    for (int columna = 1; columna <= nroColums; columna++) {
                        nombresFilas.add(metaData.getColumnLabel(columna));
                    }
                    resultado.add(nombresFilas);
                }
                
                while (rs.next()) {
                    ArrayList<Object> nuevaFila = new ArrayList<>();
                    for (int columna = 1; columna <= nroColums; columna++) {
                        nuevaFila.add(rs.getObject(columna));
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
    
    public static Object[] getRowFromTable(String sql, int fila) {
        return getRowFromTable(sql, fila, false);
    }
    
    public static Object[] getRowFromTable(String sql, int fila, boolean nombreColumnas) {
        ArrayList<ArrayList<Object>> resultado = getFromDB(sql, nombreColumnas);
        try {
            if (resultado != null && fila >= 0) {
                return resultado.get(fila).toArray();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }
    
    public static Object getCellFromTable(String sql, int fila, String columna) {
        ArrayList<ArrayList<Object>> resultado = getFromDB(sql, true);
        try {
            if (resultado != null && fila > 0) {
                int column = resultado.get(0).indexOf(columna);
                ArrayList<Object> row = resultado.get(fila);
                return row.get(column);
                
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }
    
    public static Object getCellFromTable(String sql, int fila, int columna) {
        return getCellFromTable(sql, fila, columna, false);
    }
    
    public static Object getCellFromTable(String sql, int fila, int columna, boolean nombreColumna) {
        ArrayList<ArrayList<Object>> resultado = getFromDB(sql, nombreColumna);
        try {
            if (resultado != null && fila >= 0 && columna >= 0) {
                return resultado.get(fila).get(columna);
            } else {
                return null;
            }
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
