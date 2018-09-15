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
    
    /**
     * Contructor de la sesión.
     * @param sesion Objecto de sesión, generalmente ya viene con JSP o Servlet.
     */
    public Sesiones(HttpSession sesion) {
        this.sesion = sesion;
        //this.levels = new int[] {1, 2};
        this.levels = new int[2];
        this.levels[0] = 1; //Admin
        this.levels[1] = 0; //User
    }
    
    /**
     * Configura la sesión.
     * @param id Id de la sesión.
     * @param nombre Nombre de la sesión.
     * @return {@link Boolean} que confirma si la sesión se configuró 
     * exitosamente.
     */
    public boolean setSesion(int id, String nombre) {
        return setSesion(new String[] {
            "id",
            "nombre"
        }, new Object[] {
            id,
            nombre
        });
    }
    
    /**
     * Configura la sesión.
     * @param id Id de la sesión.
     * @param nombre Nombre de la sesión.
     * @param nivel Nivel de la sesión.
     * @return {@link Boolean} que confirma si la sesión se configuró 
     * exitosamente.
     */
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
    
    /**
     * Guarda valores en la sesión. Puede almacenar cualquier cantidad de valores, 
     * mientras los dos arrays recibidos sea del mismo tamaño.
     * @param nombres Array con los nombres de los valores a guardar.
     * @param valores Valores a guardar.
     * @return {@link Boolean} que confirma si la sesión guardo los valores 
     * exitosamente.
     */
    public boolean setSesion(String[] nombres, Object[] valores) {
        if (nombres.length != valores.length){
            return false;
        }
        for (int i = 0; i < nombres.length; i++) {
            sesion.setAttribute(nombres[i], valores[i]);
        }
        return true;
    }
    
    /**
     * Guardar un atributo en sesión.
     * @param nombre Nombre del atributo.
     * @param valor Valore del atributo.
     */
    public void setAttribute(String nombre, Object valor) {
        sesion.setAttribute(nombre, valor);
    }
        
    /**
     * Valida que la sesión pertenezca al usuario tipo Administrador.
     * @return {@link Boolean} que confirma si la sesión se validó 
     * exitosamente.
     */
    public boolean validarAdmin() {
        return validar(levels[0]);
    }
    
    /**
     * Valida que la sesión pertenezca al usuario tipo Usuario.
     * @return {@link Boolean} que confirma si la sesión se validó 
     * exitosamente.
     */
    public boolean validarUsuario() {
        return validar(levels[1]);
    }
    
    /**
     * Invalida la sesión actual. Una vez invalidada la sesión los métodos
     * {@link #validarAdmin()} y {@link #validarUsuario()} retornaran siempre
     * false (es decir no pasaran la validación).
     */
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
    
    /**
     * Retorna el Id de la sesión.
     * @param id Id de la sesión.
     */
    public void setId(int id) {
        sesion.setAttribute("id", id);
    }
    
    /**
     * Retorna el nombre de la sesión.
     * @param nombre Nombre de la sesión.
     */
    public void setNombre(String nombre) {
        sesion.setAttribute("nombre", nombre);
    }
    
    /**
     * Retorna el nivel (asignado) de la sesión
     * @param nivel Retorna del nivel de la sesión.
     */
    public void setNivel(int nivel) {
        sesion.setAttribute("nivel", nivel);
    }
    
    /**
     * Asigna el nivel del usuario tipo Usuario.
     */
    public void setNivelUser() {
        sesion.setAttribute("nivel", levels[1]);
    }
    
    /**
     * Asigna el nivel del usuario tipo Administrador.
     */
    public void setNivelAdmin() {
        sesion.setAttribute("nivel", levels[0]);
    }
    
    /**
     * Obtiene el Id de la sesión.
     * @return {@link Integer} con el Id.
     */
    public int getId() {
        return (Integer) sesion.getAttribute("id");
    }
    
    /**
     * Obtiene el nombre de la sesión.
     * @return {@link String} con el nombre.
     */
    public String getNombre() {
        return (String) sesion.getAttribute("nombre");
    }
    
    /**
     * Obtiene el nivel de la sesión.
     * @return {@link Integer} con el nivel.
     */
    public int getNivel() {
        return (Integer) sesion.getAttribute("nivel");
    }
    
    /**
     * Obtiene el atributo de la sesión del cual se ha dado su nombre.
     * @param nombre Nombre del atributo.
     * @return {@link Object} con el atributo.
     */
    public Object getAttribute(String nombre) {
        return sesion.getAttribute(nombre);
    }
    
    /**
     * Eliminar el atributo de la sesión del cual se ha dado su nombre.
     * @param nombre Nombre del atributo.
     */
    public void removeAttribute(String nombre) {
        sesion.removeAttribute(nombre);
    }
}
