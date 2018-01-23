/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.ccr;

import java.util.ArrayList;

/**
 *
 * @author Disble
 */
public class Reportes {
    
    private Reportes() {
        
    }
    
    public static ArrayList<ArrayList<Object>> reporteArray(String sql) {
        return Consultas.getFromDB(sql).getData();
    }
    
    public static ArrayList<ArrayList<Object>> reporteArray(String sql, boolean nombreColumnas) {
        return Consultas.getFromDB(sql).getData();
    }
    
    public static String reporteHTML(String sql) {
        return reporteHTML(sql, false);
    }
    
    public static String reporteHTML(String sql, boolean nombreColumnas) {
        ArrayList<ArrayList<Object>> consulta = Consultas.getFromDB(sql, nombreColumnas).getData();
        if (consulta != null) {
            String reporte = "";
            reporte += "<table class=\"reporte\">";
            for (ArrayList<Object> fila : consulta) {
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
    
    public static String reporteCSS() {
        return reportesCSS(1);
    }
    
    public static String reporteCSS(int numCss) {
        return reportesCSS(numCss);
    }
    
    private static String reportesCSS(int tipoReporte) {
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
}
