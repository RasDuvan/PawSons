/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Modelo.DAO.Mascotas_DAO;
import Modelo.VO.Mascotas_VO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MovilesException;
/**
 *
 * @author Orlando
 */
public class ControlMascotas {
        Connection cnn;
    Mascotas_DAO dao;

    public ControlMascotas(Connection cnn) {
        this.cnn = cnn;
        this.dao = new Mascotas_DAO(cnn);
    }

    public ArrayList listarMascotas(Mascotas_VO vo) throws MovilesException {
        try {
            ArrayList voVerificado = (ArrayList) this.dao.consultar();
            return voVerificado;
        }
        catch (SQLException ex) {
            throw new MovilesException("No se puede validar el Usuario", (Throwable)ex);
        }
    }

    public int insertarMascotas(Mascotas_VO vo) throws MovilesException {
        try {
            int num = 0;
            if (vo.getNombremascota() != null && vo.getTipoEdad() != null && vo.getEdad() != null && vo.getTamano() != null && vo.getGenero() != null && vo.getTipoMascota() != null && vo.getFoto() != null && vo.getInformacionAdicional() != null && vo.getIdRaza() != null && vo.getIdUsuario() != null && vo.getIdCiudad() != null) {
                num = this.dao.insertar(vo);
                return num;
            }
            throw new MovilesException("No se pudo registrar mascota", null);
        }
        catch (SQLException ex) {
            throw new MovilesException("No se puede validar la mascota", (Throwable)ex);
        }
    }
}
