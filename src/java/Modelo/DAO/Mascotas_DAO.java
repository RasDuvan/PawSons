/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.Mascotas_VO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Orlando
 */
public class Mascotas_DAO {
    Connection cnn;

    public Mascotas_DAO(Connection cnn) {
        this.cnn = cnn;
    }

    public int insertar(Mascotas_VO vo) throws SQLException {
        PreparedStatement sentencia = this.cnn.prepareStatement("INSERT INTO mascota (nombremascota, tipoedad, edad, tamano, genero, tipomascota, fotos, infoadicional, Idraza, Idusuario, IdCiudad) VALUES(?,?,?,?,?,?,?,?,?,?,?)", 1);
        sentencia.setString(1, vo.getNombremascota());
        sentencia.setString(2, vo.getTipoEdad());
        sentencia.setString(3, vo.getEdad());
        sentencia.setString(4, vo.getTamano());
        sentencia.setString(5, vo.getGenero());
        sentencia.setString(6, vo.getTipoMascota());
        sentencia.setString(7, vo.getFoto());
        sentencia.setString(8, vo.getInformacionAdicional());
        sentencia.setString(9, vo.getIdRaza());
        sentencia.setString(10, vo.getIdUsuario());
        sentencia.setString(11, vo.getIdCiudad());
        int sent = sentencia.executeUpdate();
        ResultSet llaves = sentencia.getGeneratedKeys();
        if (llaves.next()) {
            vo.setIdMascota(llaves.getInt(1));
        }
        return sent;
    }

    public List<Mascotas_VO> consultar() throws SQLException {
        PreparedStatement sentencia = this.cnn.prepareStatement("SELECT * FROM mascota");
        ArrayList<Mascotas_VO> lista = new ArrayList();
        ResultSet resul = sentencia.executeQuery();
        while (resul.next()) {
            lista.add(this.getMascotaResult(resul));
        }
        return lista;
    }

    private Mascotas_VO getMascotaResult(ResultSet resultado) throws SQLException {
        Mascotas_VO vo = new Mascotas_VO();
        vo.setIdMascota(resultado.getInt("idmascota"));
        vo.setNombremascota(resultado.getString("nombremascota"));
        vo.setTipoEdad(resultado.getString("tipoedad"));
        vo.setEdad(resultado.getString("edad"));
        vo.setTamano(resultado.getString("tamano"));
        vo.setGenero(resultado.getString("genero"));
        vo.setTipoMascota(resultado.getString("tipomascota"));
        vo.setFoto(resultado.getString("fotos"));
        vo.setInformacionAdicional(resultado.getString("infoadicional"));
        vo.setIdRaza(resultado.getString("Idraza"));
        vo.setIdUsuario(resultado.getString("Idusuario"));
        vo.setIdCiudad(resultado.getString("IdCiudad"));
        return vo;
    }

    public Mascotas_VO consultar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void eliminar(Mascotas_VO vo) throws SQLException {
        PreparedStatement sentencia = this.cnn.prepareStatement("DELETE * FROM usuario WHERE Idmascota=?");
        sentencia.setInt(1, vo.getIdMascota());
        int res = sentencia.executeUpdate();
        if (res < 0) {
            throw new SQLException("No se pudo eliminar mascota");
        }
    }
}
