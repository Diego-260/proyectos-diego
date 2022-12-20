/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

/**
 *
 * @author diego
 */
public class Perfil {
    private int ID_perfil;
    private String nombre_usuario;
    private String contrasena;

    public int getID_perfil() {
        return ID_perfil;
    }

    public void setID_perfil(int ID_perfil) {
        this.ID_perfil = ID_perfil;
    }
    
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
    
}
