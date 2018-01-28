/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.ct;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Disble
 */
public class TablaSQL {
    
    private ArrayList<ArrayList<Object>> tabla;
    private boolean cabecera;
    private static Statement stmt;
    private static ResultSet rs;
    
    public TablaSQL(String sql) {
        this.tabla = getFromDB(sql);
        this.cabecera = false;
    }
    
    public TablaSQL(String sql, boolean cabecera) {
        this.tabla = getFromDB(sql);
        this.cabecera = cabecera;
    }
    
    private ArrayList<ArrayList<Object>> getFromDB(String sql) {
        try {
            try {
                ArrayList<ArrayList<Object>> resultado = new ArrayList<>();
                stmt = Conexion.abrir().createStatement();
                rs = stmt.executeQuery(sql);
                System.out.println("SQL (get) : " + sql);
                rs = stmt.executeQuery(sql);
                ResultSetMetaData metaData = rs.getMetaData();
                int nroColums = metaData.getColumnCount();
                
                ArrayList<Object> nombresFilas = new ArrayList<>();
                for (int columna = 1; columna <= nroColums; columna++) {
                    nombresFilas.add(metaData.getColumnLabel(columna));
                }
                resultado.add(nombresFilas);
                
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
    
    public int getNumColumns() {
        try {
            return tabla.get(0).size();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return -1;
        }
    }
    
    public int getNumRows() {
        try {
            if (cabecera) {
                return tabla.size();
            } else {
                return tabla.size() - 1;
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return -1;
        }
    }
    
    public Object getCell(int fila, String columna) {
        try {
            if (tabla != null && fila > 0) {
                if (!cabecera) fila++;
                int nombres = tabla.get(0).indexOf(columna);
                ArrayList<Object> row = tabla.get(fila);
                return row.get(nombres);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }
    
    public Object getCell(int fila, int columna) {
        try {
            if (tabla != null && fila >= 0 && columna >= 0) {
                if (!cabecera) fila++;
                return tabla.get(fila).get(columna);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }
    
    public Object[] getRow(int fila) {
        try {
            if (tabla != null && fila >= 0) {
                if (!cabecera) fila++;
                return tabla.get(fila).toArray();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }
    
    public ArrayList<ArrayList<Object>> getData() {
        try {
            if (cabecera) {
                return tabla;
            } else {
                ArrayList<ArrayList<Object>> data = new ArrayList<>();
                for (int i = 1; i < tabla.size(); i++) {
                    ArrayList<Object> get = tabla.get(i);
                    data.add(get);
                }
                return data;
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return null;
        }
    }
    
    public String reporteHTML() {
        if (tabla != null) {
            String reporte = "";
            reporte += "<table class=\"reporte\">";
            for (int i = !cabecera ? 1 : 0; i < tabla.size(); i++) {
                ArrayList<Object> fila = tabla.get(i);
                reporte += "<tr>";
                for (Object columna : fila) {
                    reporte += "<td>" + columna + "</td>";
                }
                reporte += "</tr>";
            }
            reporte += "</table>";
            return reporte;
        } else {
            return "<p>Hubo un problema generando el reporte<p>";
        }
    }
    
    public String reporteCSS() {
        if (cabecera)
            return reportesCSS(2);
        else
            return reportesCSS(1);
    }
    
    public String reportesCSS(int tipoReporte) {
        switch(tipoReporte) {
            case 1:
                return "<style>"
                + ".reporte {\n" +
"                margin: 0 auto;\n" +
"            }\n" +
"            .reporte, .reporte tr, .reporte td {\n" +
"                border-collapse: collapse;\n" +
"                border: 1px solid black;\n" +
"            }\n" +
"            .reporte td {\n" +
"                padding: 8px;\n" +
"                text-align: center;\n" +
"            }" +
"            </style>";
            case 2:
                return "<style>"
                + ".reporte {\n" +
"                margin: 0 auto;\n" +
"            }\n" +
"            .reporte, .reporte tr, .reporte td {\n" +
"                border-collapse: collapse;\n" +
"                border: 1px solid black;\n" +
"            }\n" +
"            .reporte tr:first-child {\n" +
"                background-color: aqua;\n" +
"                font-weight: bold;\n" +
"                font-variant: all-petite-caps;\n" +
"                font-size: 1.1em;\n" +
"            }\n" +
"            .reporte td {\n" +
"                padding: 8px;\n" +
"                text-align: center;\n" +
"            }" +
"            </style>";
            default:
                return "<style>"
                + ".reporte {\n" +
"                margin: 0 auto;\n" +
"            }\n" +
"            .reporte, .reporte tr, .reporte td {\n" +
"                border-collapse: collapse;\n" +
"                border: 1px solid black;\n" +
"            }\n" +
"            .reporte td {\n" +
"                padding: 8px;\n" +
"                text-align: center;\n" +
"            }" +
"            </style>";
        }
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
