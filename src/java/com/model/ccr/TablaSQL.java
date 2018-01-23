/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.ccr;

import static com.model.ccr.Consultas.getFromDB;
import java.util.ArrayList;

/**
 *
 * @author Disble
 */
public class TablaSQL {
    
    private ArrayList<ArrayList<Object>> tabla;
    private boolean cabecera;
    
    public TablaSQL(ArrayList<ArrayList<Object>> tabla) {
        this.tabla = tabla;
        this.cabecera = false;
    }
    
    public TablaSQL(ArrayList<ArrayList<Object>> tabla, boolean cabecera) {
        this.tabla = tabla;
        this.cabecera = cabecera;
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
    
}
