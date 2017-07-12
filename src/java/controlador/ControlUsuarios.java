/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.DAO.Usuarios_DAO;
import Modelo.VO.Usuarios_VO;
import java.sql.Connection;
import java.sql.SQLException;
import utils.MovilesException;

/**
 *
 * @author Orlando
 */
public class ControlUsuarios {
    Connection cnn;
    Usuarios_DAO dao;

    public ControlUsuarios(Connection cnn) {
        this.cnn = cnn;
        this.dao = new Usuarios_DAO(cnn);
    }

    public Usuarios_VO validarUsuario(Usuarios_VO vo) throws MovilesException {
        try {
            if (vo.getCorreo() != null && vo.getContrasena() != null) {
                Usuarios_VO voVerificado = dao.consultar(vo.getCorreo());
                if (voVerificado != null && voVerificado.getContrasena().equals(vo.getContrasena())) {
                    return voVerificado;
                }
            }
            throw new MovilesException("Datos de Usario invalidos", null);
        } catch (SQLException ex) {
            throw new MovilesException("No se puede validar el Usuario", ex);
        }
    }
    
    public int insertarUsuario (Usuarios_VO vo) throws MovilesException {
        try {
            int num = 0;
            if (vo.getNombre()!= null && vo.getApellido()!= null && vo.getTelefono()!= null && vo.getCorreo() != null && vo.getContrasena() != null) {
                Usuarios_VO voVerificado = dao.consultar(vo.getCorreo());
                if (voVerificado == null){
                num = dao.insertar(vo);
                return num;
                }
            }
            throw new MovilesException("Ya existe un usuario con este Correo", null);
        } catch (SQLException ex) {
            throw new MovilesException("No se puede validar el Usuario", ex);
        }    
    }  
}
