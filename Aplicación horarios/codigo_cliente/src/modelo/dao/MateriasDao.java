/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import com.mysql.conexion.DBConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import modelo.dto.Materia;
import modelo.dto.Usuario;

/**
 *
 * @author diego
 */
public class MateriasDao {

    DBConexion db = new DBConexion();

    public void crearMateria(Materia materia, int[] posicionCasilla, Usuario usuario) {
        PreparedStatement ps;
        ResultSet rs;
        String Query1 = "INSERT INTO `Materia`(`ID_usuario`, `nombre_materia`, `semestre`) VALUES (?,?,?)";
        String Query2 = "SELECT `ID_Materia` FROM `Materia` WHERE `nombre_materia` = ? AND `ID_usuario` = ? ";
        String Query3 = "INSERT INTO `Grupo`(`ID_materia`, `numero_grupo`, `duracion_clase`, `dias`, `nombre_profesor`, `link`, `salon`) VALUES (?,?,?,?,?,?,?)";
        String Query4 = "INSERT INTO `Horario`(`ID_usuario`, `ID_materia`, `fila`, `columna`) VALUES (?,?,?,?)";
        try {
            ps = db.getConnection().prepareStatement(Query1);
            ps.setInt(1, usuario.getID_Usuario());
            ps.setString(2, materia.getNombre_materia());
            ps.setString(3, materia.getSemestre());
            ps.executeUpdate();

            ps = db.getConnection().prepareStatement(Query2);
            ps.setString(1, materia.getNombre_materia());
            ps.setInt(2, usuario.getID_Usuario());
            rs = ps.executeQuery();
            if (rs.next()) {
                materia.setID_materia(rs.getInt(1));
            }

            String diasStr = String.join(" ", materia.getDias());
            ps = db.getConnection().prepareStatement(Query3);
            ps.setInt(1, materia.getID_materia());
            ps.setInt(2, materia.getNumero_grupo());
            ps.setInt(3, materia.getDuracion_clase());
            ps.setString(4, diasStr);
            ps.setString(5, materia.getNombre_profesor());
            ps.setString(6, materia.getLink());
            ps.setString(7, materia.getSalon());
            ps.executeUpdate();

            String[] semana = {"Lu", "Ma", "Mi", "Ju", "Vi", "Sa"};
            ArrayList<String> dias = materia.getDias();
            for (String dia : dias) {
                int nColumna = 0;
                for (int i = 1; i <= 6; i++) {
                    if (dia.equals(semana[i - 1])) {
                        nColumna = i;
                    }
                }
                ps = db.getConnection().prepareStatement(Query4);
                ps.setInt(1, usuario.getID_Usuario());
                ps.setInt(2, materia.getID_materia());
                ps.setInt(3, posicionCasilla[0]);
                ps.setInt(4, nColumna);
                ps.executeUpdate();
                if (materia.getDuracion_clase() == 2) {
                    ps = db.getConnection().prepareStatement(Query4);
                    ps.setInt(1, usuario.getID_Usuario());
                    ps.setInt(2, materia.getID_materia());
                    ps.setInt(3, posicionCasilla[0] + 1);
                    ps.setInt(4, nColumna);
                    ps.executeUpdate();
                }
                if (materia.getDuracion_clase() == 3) {
                    ps = db.getConnection().prepareStatement(Query4);
                    ps.setInt(1, usuario.getID_Usuario());
                    ps.setInt(2, materia.getID_materia());
                    ps.setInt(3, posicionCasilla[0] + 1);
                    ps.setInt(4, nColumna);
                    ps.executeUpdate();

                    ps = db.getConnection().prepareStatement(Query4);
                    ps.setInt(1, usuario.getID_Usuario());
                    ps.setInt(2, materia.getID_materia());
                    ps.setInt(3, posicionCasilla[0] + 2);
                    ps.setInt(4, nColumna);
                    ps.executeUpdate();
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public JTable crearHorario(JTable jCalendario, Usuario usuario) {
        PreparedStatement ps;
        ResultSet rs;
        String Query1 = "SELECT * FROM `Horario` WHERE `ID_usuario` = ?";
        String Query2 = "SELECT `nombre_materia` FROM `Materia` WHERE `ID_materia` = ?";
        String Query3 = "SELECT `numero_grupo`, `nombre_profesor` FROM `Grupo` WHERE `ID_materia` = ?";
        try {
            ps = db.getConnection().prepareStatement(Query1);
            ps.setInt(1, usuario.getID_Usuario());
            rs = ps.executeQuery();
            int[][] tablaHorario = null;
            if (rs.next()) {
                tablaHorario = ResultSetToArray(rs);
            }
            if (tablaHorario != null) {
                for (int i = 0; i < tablaHorario.length; i++) {
                    Materia materia = new Materia();
                    materia.setID_materia(tablaHorario[i][2]);

                    ps = db.getConnection().prepareStatement(Query2);
                    ps.setInt(1, materia.getID_materia());
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        materia.setNombre_materia(rs.getString(1));
                    }

                    ps = db.getConnection().prepareStatement(Query3);
                    ps.setInt(1, materia.getID_materia());
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        materia.setNumero_grupo(rs.getInt(1));
                        materia.setNombre_profesor(rs.getString(2));
                    }
                    String cadena = String.format("<html>" + materia.getNombre_materia() + "<br>" + "Grupo: " + materia.getNumero_grupo() + "<br>" + materia.getNombre_profesor() + "</html>");
                    jCalendario.setValueAt(cadena, tablaHorario[i][3], tablaHorario[i][4]);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jCalendario;
    }

    public Materia buscarMateria(int[] posicionCasilla, Usuario usuario) {
        Materia materia = new Materia();
        try {
            PreparedStatement ps;
            ResultSet rs;
            String Query1 = "SELECT `ID_Materia` FROM `Horario` WHERE `fila` = ? AND `columna` = ? AND `ID_usuario` = ?";
            String Query2 = "SELECT * FROM `Materia` WHERE `ID_materia` = ?";
            String Query3 = "SELECT * FROM `Grupo` WHERE `ID_materia` = ?";
            ps = db.getConnection().prepareStatement(Query1);
            ps.setInt(1, posicionCasilla[0]);
            ps.setInt(2, posicionCasilla[1]);
            ps.setInt(3, usuario.getID_Usuario());
            rs = ps.executeQuery();
            if (rs.next()) {
                materia.setID_materia(rs.getInt(1));
            }

            ps = db.getConnection().prepareStatement(Query2);
            ps.setInt(1, materia.getID_materia());
            rs = ps.executeQuery();
            if (rs.next()) {
                materia.setNombre_materia(rs.getString(3));
                materia.setSemestre(rs.getString(4));
            }

            ps = db.getConnection().prepareStatement(Query3);
            ps.setInt(1, materia.getID_materia());
            rs = ps.executeQuery();
            if (rs.next()) {
                materia.setNumero_grupo(rs.getInt(3));
                materia.setDuracion_clase(rs.getInt(4));
                String diasStr = rs.getString(5);
                materia.setDias(new ArrayList<String>(Arrays.asList(diasStr.split(" "))));
                materia.setNombre_profesor(rs.getString(6));
                materia.setLink(rs.getString(7));
                materia.setSalon(rs.getString(8));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materia;
    }

    public JTable quitarMateriaHorario(Materia materia, JTable jCalendario) {
        PreparedStatement ps;
        ResultSet rs;
        String Query3 = "SELECT * FROM `Horario` WHERE `ID_materia` = ?";
        try {
            ps = db.getConnection().prepareStatement(Query3);
            ps.setInt(1, materia.getID_materia());
            rs = ps.executeQuery();
            int[][] tablaHorario = null;
            if (rs.next()) {
                tablaHorario = ResultSetToArray(rs);
            }
            for (int i = 0; i < tablaHorario.length; i++) {
                jCalendario.setValueAt(null, tablaHorario[i][3], tablaHorario[i][4]);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jCalendario;
    }

    public void editarMateria(Materia materia, int[] posicionCasilla, Usuario usuario) {
        PreparedStatement ps;
        ResultSet rs;
        String Query1 = "UPDATE `Materia` SET `nombre_materia`= ?, `semestre`= ? WHERE `ID_materia` = ?";
        String Query2 = "UPDATE `Grupo` SET `numero_grupo`= ?, `duracion_clase`= ?, `dias`= ?, `nombre_profesor`= ?, `link`= ?, `salon`= ? WHERE `ID_materia` = ?";
        String Query3 = "DELETE FROM `Horario` WHERE `ID_materia`= ?";
        String Query4 = "INSERT INTO `Horario`(`ID_usuario`, `ID_materia`, `fila`, `columna`) VALUES (?,?,?,?)";
        try {
            ps = db.getConnection().prepareStatement(Query1);
            ps.setString(1, materia.getNombre_materia());
            ps.setString(2, materia.getSemestre());
            ps.setInt(3, materia.getID_materia());
            ps.executeUpdate();

            ps = db.getConnection().prepareStatement(Query2);
            ps.setInt(1, materia.getNumero_grupo());
            ps.setInt(2, materia.getDuracion_clase());
            String diasStr = String.join(" ", materia.getDias());
            ps.setString(3, diasStr);
            ps.setString(4, materia.getNombre_profesor());
            ps.setString(5, materia.getLink());
            ps.setString(6, materia.getSalon());
            ps.setInt(7, materia.getID_materia());
            ps.executeUpdate();

            ps = db.getConnection().prepareStatement(Query3);
            ps.setInt(1, materia.getID_materia());
            ps.executeUpdate();

            String[] semana = {"Lu", "Ma", "Mi", "Ju", "Vi", "Sa"};
            ArrayList<String> dias = materia.getDias();
            for (String dia : dias) {
                int nColumna = 0;
                for (int i = 1; i <= 6; i++) {
                    if (dia.equals(semana[i - 1])) {
                        nColumna = i;
                    }
                }
                ps = db.getConnection().prepareStatement(Query4);
                ps.setInt(1, usuario.getID_Usuario());
                ps.setInt(2, materia.getID_materia());
                ps.setInt(3, posicionCasilla[0]);
                ps.setInt(4, nColumna);
                ps.executeUpdate();
                if (materia.getDuracion_clase() == 2) {
                    ps = db.getConnection().prepareStatement(Query4);
                    ps.setInt(1, usuario.getID_Usuario());
                    ps.setInt(2, materia.getID_materia());
                    ps.setInt(3, posicionCasilla[0] + 1);
                    ps.setInt(4, nColumna);
                    ps.executeUpdate();
                }
                if (materia.getDuracion_clase() == 3) {
                    ps = db.getConnection().prepareStatement(Query4);
                    ps.setInt(1, usuario.getID_Usuario());
                    ps.setInt(2, materia.getID_materia());
                    ps.setInt(3, posicionCasilla[0] + 1);
                    ps.setInt(4, nColumna);
                    ps.executeUpdate();

                    ps = db.getConnection().prepareStatement(Query4);
                    ps.setInt(1, usuario.getID_Usuario());
                    ps.setInt(2, materia.getID_materia());
                    ps.setInt(3, posicionCasilla[0] + 2);
                    ps.setInt(4, nColumna);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean verificarMateriaCruzada(Materia materia, int[] posicionCasilla, Usuario usuario) {
        PreparedStatement ps;
        ResultSet rs;
        String Query1 = "SELECT * FROM `Horario` WHERE `ID_usuario` = ?";
        try {
            ps = db.getConnection().prepareStatement(Query1);
            ps.setInt(1, usuario.getID_Usuario());
            if (materia.getID_materia() != 0){
                Query1 = "SELECT * FROM `Horario` WHERE `ID_materia`<>? AND `ID_usuario` = ?";
                ps = db.getConnection().prepareStatement(Query1);
                ps.setInt(1, materia.getID_materia());
                ps.setInt(2, usuario.getID_Usuario());
            }  
            rs = ps.executeQuery();
            int[][] tablaHorario = null;
            if (rs.next()) {
                tablaHorario = ResultSetToArray(rs);
            }
            if (tablaHorario != null) {
                String[] semana = {"Lu", "Ma", "Mi", "Ju", "Vi", "Sa"};
                ArrayList<String> dias = materia.getDias();
                for (int i = 0; i < tablaHorario.length; i++) {
                    for (String dia : dias) {
                        int nColumna = 0;
                        for (int j = 1; j <= 6; j++) {
                            if (dia.equals(semana[j - 1])) {
                                nColumna = j;
                            }
                        }

                        if (posicionCasilla[0] == tablaHorario[i][3] && nColumna == tablaHorario[i][4]) {
                            return true;
                        }

                        if (materia.getDuracion_clase() == 2) {
                            if ((posicionCasilla[0] + 1) == tablaHorario[i][3] && nColumna == tablaHorario[i][4]) {
                                return true;
                            }
                        }

                        if (materia.getDuracion_clase() == 3) {
                            if ((posicionCasilla[0] + 1) == tablaHorario[i][3] && nColumna == tablaHorario[i][4]) {
                                return true;
                            }
                            if ((posicionCasilla[0] + 2) == tablaHorario[i][3] && nColumna == tablaHorario[i][4]) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void eliminarMateria(Materia materia) {
        PreparedStatement ps;
        String Query1 = "DELETE FROM `Horario` WHERE `ID_materia`= ?";
        String Query2 = "DELETE FROM `Materia` WHERE `ID_materia`= ?";
        try{
            ps = db.getConnection().prepareStatement(Query1);
            ps.setInt(1, materia.getID_materia());
            ps.executeUpdate();
            
            ps = db.getConnection().prepareStatement(Query2);
            ps.setInt(1, materia.getID_materia());
            ps.executeUpdate();
            
        }catch (SQLException ex){
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int[][] ResultSetToArray(ResultSet rs) {
        int[][] obj = null;
        try {
            rs.last();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            int numFils = rs.getRow();
            obj = new int[numFils][numCols];
            int j = 0;
            rs.beforeFirst();
            while (rs.next()) {
                for (int i = 0; i < numCols; i++) {
                    obj[j][i] = rs.getInt(i + 1);
                }
                j++;
            }
        } catch (Exception e) {
        }
        return obj;
    }

    
}
