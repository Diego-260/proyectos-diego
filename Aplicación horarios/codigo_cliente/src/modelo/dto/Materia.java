/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class Materia {
    private int ID_materia;
    private String nombre_materia;
    private String semestre;
    private int numero_grupo;
    private int duracion_clase;
    private ArrayList<String> dias;
    private String nombre_profesor;
    private String link;
    private String salon;

    public int getID_materia() {
        return ID_materia;
    }

    public void setID_materia(int ID_materia) {
        this.ID_materia = ID_materia;
    }

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getNumero_grupo() {
        return numero_grupo;
    }

    public void setNumero_grupo(int numero_grupo) {
        this.numero_grupo = numero_grupo;
    }

    public int getDuracion_clase() {
        return duracion_clase;
    }

    public void setDuracion_clase(int duracion_clase) {
        this.duracion_clase = duracion_clase;
    }

    public ArrayList getDias() {
        return dias;
    }

    public void setDias(ArrayList dias) {
        this.dias = dias;
    }
    
    public String getNombre_profesor() {
        return nombre_profesor;
    }

    public void setNombre_profesor(String nombre_profesor) {
        this.nombre_profesor = nombre_profesor;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }
    
    
    
}
