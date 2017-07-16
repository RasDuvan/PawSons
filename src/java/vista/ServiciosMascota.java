/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author Orlando
 */
import Modelo.VO.Mascotas_VO;
import controlador.ControlMascotas;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.MovilesException;
import utils.RespuestaMoviles;
import vista.GenericoServlet;

@WebServlet(name="ServiciosMascota", urlPatterns={"/ServiciosMascota/*"})
public class ServiciosMascota
extends GenericoServlet {
    public RespuestaMoviles procesarPeticion(String accion, HttpServletRequest request, HttpServletResponse response) throws MovilesException {
        RespuestaMoviles respuesta = new RespuestaMoviles();
        switch (accion) {
            case "insertar": {
                ControlMascotas Insert = new ControlMascotas(this.cnn);
                Mascotas_VO vO = new Mascotas_VO();
                vO.setNombremascota(request.getParameter("Nombre"));
                vO.setTipoEdad(request.getParameter("TipoEdad"));
                vO.setEdad(request.getParameter("Edad"));
                vO.setTamano(request.getParameter("Tamano"));
                vO.setGenero(request.getParameter("Genero"));
                vO.setTipoMascota(request.getParameter("TipoMascota"));
                vO.setFoto(request.getParameter("Foto"));
                vO.setInformacionAdicional(request.getParameter("Infoadicional"));
                vO.setIdRaza(request.getParameter("IdRaza"));
                vO.setIdUsuario(request.getParameter("IdUsuario"));
                vO.setIdCiudad(request.getParameter("IdCiudad"));
                int result = Insert.insertarMascotas(vO);
                respuesta.setCodigo(1);
                respuesta.setMensaje("Insertar OK");
                respuesta.setDatos(result);
                break;
            }
            
            case "listar":{
                ControlMascotas Insert = new ControlMascotas(this.cnn);
                Mascotas_VO vO = new Mascotas_VO();
                ArrayList result = Insert.listarMascotas(vO);
                respuesta.setCodigo(1);
                respuesta.setMensaje("Insertar OK");
                respuesta.setDatos(result);
                break;
            }
            default: {
                respuesta.setCodigo(-1);
                respuesta.setMensaje("Accion no valida");
                respuesta.setDatos((Object)null);
            }
        }
        return respuesta;
    }
}
