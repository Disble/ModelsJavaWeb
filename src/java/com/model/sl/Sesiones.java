/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.sl;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Disble
 */
public class Sesiones {
    
    private HttpSession sesion;
    private int[] levels;
    
    public Sesiones(HttpSession sesion) {
        this.sesion = sesion;
        //this.levels = new int[] {1, 2};
        this.levels = new int[2];
        this.levels[0] = 1; //Admin
        this.levels[1] = 0; //User
    }
    
    public boolean setSesion(int id, String nombre) {
        return setSesion(new String[] {
            "id",
            "nombre"
        }, new Object[] {
            id,
            nombre
        });
    }
    
    public boolean setSesion(int id, String nombre, int nivel) {
        return setSesion(new String[] {
            "id",
            "nombre",
            "nivel"
        }, new Object[] {
            id,
            nombre,
            nivel
        });
    }
    
    public boolean setSesion(String[] nombres, Object[] valores) {
        if (nombres.length != valores.length){
            return false;
        }
        for (int i = 0; i < nombres.length; i++) {
            sesion.setAttribute(nombres[i], valores[i]);
        }
        return true;
    }
        
    public boolean validarAdmin() {
        return validar(levels[0]);
    }
    
    public boolean validarUsuario() {
        return validar(levels[1]);
    }
    
    public void invalidar() {
        sesion.invalidate();
    }
    
    private boolean validar(int level) {
        int nivel = 0;
        Integer A = (Integer) sesion.getAttribute("nivel");
        if (A == null) { 
            System.out.println("------------------------ No validado -------------------");
            sesion.invalidate();
            return false;
        } else {
            nivel = A.intValue();
            if (nivel != level) {
                System.out.println("------------------------ No validado -------------------");
                sesion.invalidate();
                return false;
            }
        }
        System.out.println("-----------*-------------- Validado -------------------");
        return true;
    }
    
    public void setId(int id) {
        sesion.setAttribute("id", id);
    }
    
    public void setNombre(String nombre) {
        sesion.setAttribute("nombre", nombre);
    }
    
    public void setNivel(int nivel) {
        sesion.setAttribute("nivel", nivel);
    }
    
    public void setNivelUser() {
        sesion.setAttribute("nivel", levels[1]);
    }
    
    public void setNivelAdmin() {
        sesion.setAttribute("nivel", levels[0]);
    }
    
    public int getId() {
        return (Integer) sesion.getAttribute("id");
    }
    
    public String getNombre() {
        return (String) sesion.getAttribute("nombre");
    }
    
    public int getNivel() {
        return (Integer) sesion.getAttribute("nivel");
    }
}
