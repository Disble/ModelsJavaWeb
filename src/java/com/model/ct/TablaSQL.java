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
    
    /**
     * Constructor de la clase. Recibe un query con una consulta para la base de
     * datos. Su principal función es la de guardar todos los datos de la tabla
     * a consultar en un objeto facilmente manejable.
     * @param sql Query con la consulta a la base de datos.
     */
    public TablaSQL(String sql) {
        this.tabla = getFromDB(sql);
        this.cabecera = false;
    }
    
    /**
     *Constructor de la clase. Recibe un query con una consulta para la base de
     * datos. Su principal función es la de guardar todos los datos de la tabla
     * a consultar en un objeto facilmente manejable.
     * @param sql Query con la consulta a la base de datos.
     * @param cabecera Valor booleano que indica si el objeto guarda o no la
     * cabecera de la tabla (nombres de columnas).
     */
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
    
    /**
     * Retorna el número de columnas de la tabla.
     * @return El número de columnas de la tabla. En caso de error retorna -1.
     */
    public int getNumColumns() {
        try {
            return tabla.get(0).size();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * Retorna el número de filas de la tabla.
     * @return El número de filas de la tabla. En caso de error retorna -1.
     */
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
    
    /**
     * Obtiene una celda de la tabla, se necesita el fila y la columna de la 
     * celda buscada.
     * @param fila Número de la fila a buscar.
     * @param columna Nombre de la columna a buscar.
     * @return {@link Object} con la celda de la tabla especificada.
     * En caso de error retorna {@link Null}.
     */
    public Object getCell(int fila, String columna) {
        try {
            if (tabla != null && fila >= 0) {
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
    
    /**
     * Obtiene una celda de la tabla, se necesita el fila y la columna de la 
     * celda buscada.
     * @param fila Número de la fila a buscar.
     * @param columna Número de la columna a buscar.
     * @return {@link Object} con la celda de la tabla especificada.
     * En caso de error retorna {@link Null}.
     */
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
    
    /**
     * Obtiene una fila completa de la tabla.
     * @param fila Número de la fila de la tabla.
     * @return Array {@link Object} con la fila de la tabla especificada. 
     * En caso de error retorna {@link Null}.
     */
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
    
    /**
     * Obtiene un doble array con toda la información de la tabla.
     * @return Un doble {@link ArrayList} del tipo {@link Object} con toda la 
     * información de la tabla. En caso de error retorna {@link Null}.
     */
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
    
    /**
     * Imprime en el HTML una tabla con todos los datos de la tabla.
     * @return {@link String} en formato HTML.
     */
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
    
    /**
     * Imprime un tag css con estilos css para dar formato a
     * {@link com.model.ct.TablaSQL#reporteHTML()}.
     * @return {@link String} en formato HTML.
     */
    public String reporteCSS() {
        if (cabecera)
            return reportesCSS(2);
        else
            return reportesCSS(1);
    }
    
    /**
     * Imprime un tag css con estilos css para dar formato a 
     * {@link com.model.ct.TablaSQL#reporteHTML()}. 
     * @param tipoReporte Integer con dos posibles valores. 1 y 2: 1 es para 
     * muestra un estilo simple sin colores. 2 muestra un estilo más trabajado
     * con colores (aqua).
     * @return {@link String} en formato HTML. En caso de error retorna el valor
     * por defecto 1.
     */
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
    
    /**
     * Método estático que permite hacer consultas rápidas de inserción en la 
     * base de datos.
     * @param sql Query con la consulta a la base de datos.
     * @return {@link Boolean} que confirma la consulta se ejecutó con exito.
     */
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
    
    /**
     * Método estático cuya función es buscar el último Id de un tabla.
     * @param sql Query con la consulta para buscar el último Id.
     * @return El número del último Id encontrado. En caso de error retorna 
     * -1.
     */
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
