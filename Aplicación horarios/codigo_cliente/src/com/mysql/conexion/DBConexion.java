/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author diego
 */
public class DBConexion {
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String USUARIO = "root";
    private static String CONTRASENA = "123456";
    private static String URL = "jdbc:mysql://localhost:3306/PROYECTO_ARQUITECTURA?useSSL=false";
    
    static{
        try{
            Class.forName(DRIVER);
        }
        catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e);
        }     
    }
    public Connection getConnection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e + "Error en la conexión");
        }
        return con;
    }
    
}
