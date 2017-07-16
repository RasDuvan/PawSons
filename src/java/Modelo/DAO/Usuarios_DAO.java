/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.Usuarios_VO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Orlando
 */
public class Usuarios_DAO {
    Connection cnn;

    public Usuarios_DAO(Connection cnn) {
        this.cnn = cnn;
    }

    public int insertar(Usuarios_VO vo) throws SQLException{
        PreparedStatement sentencia = cnn.prepareStatement("INSERT INTO usuario (nombre, apellido, correo, telefono, contrasena)"
                + " VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        sentencia.setString(1, vo.getNombre());
        sentencia.setString(2, vo.getApellido());
        sentencia.setString(3, vo.getCorreo());
        sentencia.setString(4, vo.getTelefono());
        sentencia.setString(5, vo.getContrasena());
        
        int sent =  sentencia.executeUpdate();
        ResultSet llaves = sentencia.getGeneratedKeys();
        if (llaves.next()) {
            vo.setIdUsuario(llaves.getInt(1));
        }
            return sent;
        
    }

    public void modificar(Usuarios_VO vo) throws SQLException{
        PreparedStatement sentencia = cnn.prepareStatement("UPDATE usuario nombre=?, apellido=?, correo=?, telefono=?,  contrasena=? WHERE idusuario");
        sentencia.setString(1, vo.getNombre());
        sentencia.setString(2, vo.getApellido());
        sentencia.setString(3, vo.getCorreo());
        sentencia.setString(4, vo.getTelefono());
        sentencia.setString(5, vo.getContrasena());
        
        sentencia.setInt(6, vo.getIdUsuario());
        
        int res = sentencia.executeUpdate();
        if(res<0){
            throw new SQLException("No se pudo ingregar el dato de Usuario ");
        }
    }

    public List<Usuarios_VO> consultar() throws SQLException {
        PreparedStatement sentencia = this.cnn.prepareStatement("SELECT * FROM usuario");
        List<Usuarios_VO> lista = new ArrayList<>();
        ResultSet resul = sentencia.executeQuery();
        while (resul.next()) {
            lista.add(getUsuarioResult(resul));
        }
        return lista;
    }

    private Usuarios_VO getUsuarioResult(ResultSet resultado) throws SQLException {
        Usuarios_VO vo = new Usuarios_VO();
        vo.setIdUsuario(resultado.getInt("idusuario"));
        vo.setNombre(resultado.getString("nombre"));
        vo.setApellido(resultado.getString("apellido"));
        vo.setCorreo(resultado.getString("correo")); 
        vo.setTelefono(resultado.getString("telefono"));
        vo.setContrasena(resultado.getString("contrasena"));
        return vo;
    }
    
    public Usuarios_VO consultar(String correo) throws SQLException {
        PreparedStatement sentencia = this.cnn.prepareStatement("SELECT * FROM usuario WHERE correo = ?");
        sentencia.setString(1, correo);
        ResultSet resul = sentencia.executeQuery();
        if (resul.next()) {
            return getUsuarioResult(resul);
        }
        return null;
    }

    public Usuarios_VO consultar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void eliminar(Usuarios_VO vo) throws SQLException {
        PreparedStatement sentencia = cnn.prepareStatement("DELETE * FROM usuario WHERE Idusuario=?");
        sentencia.setInt(1, vo.getIdUsuario());
        
        int res = sentencia.executeUpdate();
        if(res<0){
            throw new SQLException("No se pudo ingregar el dato de Usuario ");
        }
    }
}
