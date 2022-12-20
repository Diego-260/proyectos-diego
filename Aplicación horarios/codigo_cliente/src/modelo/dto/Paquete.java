/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class Paquete implements Serializable{
    private String nombre_usuario, ip, mensaje;
    private HashMap<String,String> conectados = null;

    public HashMap<String, String> getConectados() {
        return conectados;
    }

    public void setConectados(HashMap<String, String> conectados) {
        this.conectados = conectados;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
