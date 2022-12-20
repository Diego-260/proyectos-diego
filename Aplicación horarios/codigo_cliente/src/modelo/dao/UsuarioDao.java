/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import com.mysql.conexion.DBConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dto.Perfil;
import modelo.dto.Usuario;

/**
 *
 * @author diego
 */
public class UsuarioDao {
    
    DBConexion db = new DBConexion();

    public void crearUsuario(Usuario usuario, Perfil perfil){
        PreparedStatement ps;
        ResultSet rs;
        String Query1 = "INSERT INTO `Usuario`(`nombre`, `correo`, `institucion`) VALUES (?,?,?)";
        String Query2 = "SELECT `ID_Usuario` FROM `Usuario` WHERE `nombre` = ?";
        String Query3 = "INSERT INTO `Perfil`(`ID_usuario`, `nombre_usuario`, `contrasena`) VALUES (?,?,?)";
        String nombre = usuario.getNombre();
        String correo = usuario.getCorreo();
        String institucion = usuario.getInstitucion();
        String nombre_usuario = perfil.getNombre_usuario();
        String contrasena = perfil.getContrasena();
        
        try{
            ps = db.getConnection().prepareStatement(Query1);
            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, institucion);
            ps.executeUpdate();
            
            ps = db.getConnection().prepareStatement(Query2);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if (rs.next()){
                usuario.setID_Usuario(rs.getInt(1));
            }
            
            ps = db.getConnection().prepareStatement(Query3);
            ps.setInt(1, usuario.getID_Usuario());
            ps.setString(2, nombre_usuario);
            ps.setString(3, contrasena);
            ps.executeUpdate();
        }    
        catch (SQLException ex){
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public boolean verificarPerfil(Perfil perfil){
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM `Perfil` WHERE `nombre_usuario` = ? AND `contrasena` = ?";
        String nombre_usuario = perfil.getNombre_usuario();
        String contrasena = perfil.getContrasena();
        
        try{
            
            ps = db.getConnection().prepareStatement(query);
            ps.setString(1, nombre_usuario);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }
        }    
        catch (SQLException ex){
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Usuario buscarDatos(Perfil perfil){
        Usuario usuario = new Usuario();
        PreparedStatement ps;
        ResultSet rs;
        String Query1 = "SELECT `ID_Usuario` FROM `Perfil` WHERE `nombre_usuario` = ?";
        String Query2 = "SELECT * FROM `Usuario` WHERE `ID_usuario` = ?";
        String nombre_usuario = perfil.getNombre_usuario();
        try{           
            ps = db.getConnection().prepareStatement(Query1);
            ps.setString(1, nombre_usuario);
            rs = ps.executeQuery();

            if (rs.next()){
                usuario.setID_Usuario(rs.getInt(1));
            }
            ps = db.getConnection().prepareStatement(Query2);
            ps.setInt(1, usuario.getID_Usuario());
            rs = ps.executeQuery();
            if (rs.next()){
                usuario.setNombre(rs.getString(2));
                usuario.setCorreo(rs.getString(3));
                usuario.setInstitucion(rs.getString(4));
            }    
        }    
        catch (SQLException ex){
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }
    
    public void actualizarDatos(Usuario usuario, Perfil perfil){
        PreparedStatement ps;
        ResultSet rs;
        String Query1 = "UPDATE `Usuario` SET `nombre`= ?, `correo`= ?, `institucion`= ? WHERE `ID_usuario` = ?";
        String Query2 = "UPDATE `Perfil` SET `nombre_usuario`= ?, `contrasena`= ? WHERE `ID_usuario` = ?";
        try{           
            ps = db.getConnection().prepareStatement(Query1);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getInstitucion());
            ps.setInt(4, usuario.getID_Usuario());
            ps.executeUpdate();
            
            ps = db.getConnection().prepareStatement(Query2);
            ps.setString(1, perfil.getNombre_usuario());
            ps.setString(2, perfil.getContrasena());
            ps.setInt(3, usuario.getID_Usuario());
            ps.executeUpdate();

               
        }    
        catch (SQLException ex){
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarUsuario(Usuario usuario) {
        PreparedStatement ps;
        String Query1 = "DELETE FROM `Horario` WHERE `ID_usuario`= ?";
        String Query2 = "DELETE FROM `Materia` WHERE `ID_usuario`= ?";
        String Query3 = "DELETE FROM `Perfil` WHERE `ID_usuario`= ?";
        String Query4 = "DELETE FROM `Usuario` WHERE `ID_usuario`= ?";
        try{
            ps = db.getConnection().prepareStatement(Query1);
            ps.setInt(1, usuario.getID_Usuario());
            ps.executeUpdate();
            
            ps = db.getConnection().prepareStatement(Query2);
            ps.setInt(1, usuario.getID_Usuario());
            ps.executeUpdate();
            
            ps = db.getConnection().prepareStatement(Query3);
            ps.setInt(1, usuario.getID_Usuario());
            ps.executeUpdate();
            
            ps = db.getConnection().prepareStatement(Query4);
            ps.setInt(1, usuario.getID_Usuario());
            ps.executeUpdate();
            
        }catch (SQLException ex){
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
