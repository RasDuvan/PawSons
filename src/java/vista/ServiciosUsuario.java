/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Modelo.VO.Usuarios_VO;
import controlador.ControlUsuarios;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.MovilesException;
import utils.RespuestaMoviles;

/**
 *
 * @author Orlando
 */
@WebServlet(name = "ServiciosUsuario", urlPatterns = {"/ServiciosUsuario/*"})
public class ServiciosUsuario extends GenericoServlet {

    @Override
    public RespuestaMoviles procesarPeticion(String accion, HttpServletRequest request, HttpServletResponse response) throws MovilesException{
        RespuestaMoviles respuesta = new RespuestaMoviles();

        switch (accion) {
            case "validar":
                ControlUsuarios control = new ControlUsuarios(cnn);
                
                Usuarios_VO vo = new Usuarios_VO();
                
                vo.setCorreo(request.getParameter("Correo"));
                vo.setContrasena(request.getParameter("Contrasena"));
                
                vo = control.validarUsuario(vo);
                respuesta.setCodigo(1);
                respuesta.setMensaje("Validacion OK");
                respuesta.setDatos(vo);
                break;
            case "insertar":
                
                ControlUsuarios Insert = new ControlUsuarios(cnn);
                
                Usuarios_VO vO = new Usuarios_VO();
                
                vO.setNombre(request.getParameter("Nombre"));
                vO.setApellido(request.getParameter("Apellido"));
                vO.setTelefono(request.getParameter("Telefono"));
                //vO.setEstadoUsu(request.getParameter("Estado"));
                vO.setCorreo(request.getParameter("Correo"));
                vO.setContrasena(request.getParameter("Contrasena"));
                
                int result = Insert.insertarUsuario(vO);
                respuesta.setCodigo(1);
                respuesta.setMensaje("Insertar OK");
                respuesta.setDatos(result);
                respuesta.setDatos(vO);
                break;
                
            default:
                respuesta.setCodigo(-1);
                respuesta.setMensaje("Accion no valida");
                respuesta.setDatos(null);
               
        }

        return respuesta;
    }

}
