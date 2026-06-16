/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.usuariomodelo;

/**
 *
 * @author Damian0
 */
@WebServlet(name = "usuarioscontrolador", urlPatterns = {"/usuarioscontrolador"})
public class usuarioscontrolador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
        * Maneja las solicitudes POST para el módulo de USUARIOS.
        * Permite registrar, actualizar, eliminar, buscar usuarios
        * y generar el informe/reporte de usuarios.
        */
       @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {

           processRequest(request, response);

           // Vista por defecto a la que redirigiremos al finalizar
           String pagina = "/vistas/usuarios.jsp";

           // Obtenemos la acción enviada desde el formulario
           String accion = request.getParameter("accion");

           // Creamos el modelo de usuarios para manejar la lógica de negocio
           usuariomodelo p1 = new usuariomodelo();

           if (accion.equals("guardar")) {
               // --- REGISTRO DE NUEVO USUARIO ---
               // Asignamos los datos del formulario al modelo
               p1.setCodigo(request.getParameter("txtcodigo"));
               p1.setUsuario(request.getParameter("txtnombre"));
               p1.setClave(request.getParameter("txtclave"));
               p1.setTipo(request.getParameter("txttipo"));
               p1.setPersonal(request.getParameter("txtpersonal"));

               // Guardamos el nuevo usuario en la base de datos
               p1.guardar();

           } else if (accion.equals("modificarUsuario")) {
               // --- ACTUALIZACIÓN DE USUARIO EXISTENTE ---
               // Asignamos el código y los nuevos datos del usuario
               p1.setCodigo(request.getParameter("txtcodigo"));
               p1.setUsuario(request.getParameter("txtnombre"));
               p1.setClave(request.getParameter("txtclave"));
               p1.setTipo(request.getParameter("txttipo"));
               p1.setPersonal(request.getParameter("txtpersonal"));

               // Actualizamos el registro en la base de datos
               p1.actualizar();

           } else if (accion.equals("eliminar")) {
               // --- ELIMINACIÓN DE USUARIO ---
               // Obtenemos el código del usuario a eliminar
               String codigo = request.getParameter("txtcodigo");

               // Eliminamos el usuario de la base de datos
               p1.eliminar(codigo);

           } else if (accion.equals("buscar")) {
               // --- BÚSQUEDA DE USUARIO POR NOMBRE ---
               // Obtenemos el nombre ingresado en el campo de búsqueda
               String nombreBuscado = request.getParameter("txtbuscar");

               // Ejecutamos la búsqueda y enviamos los resultados a la vista
               List<usuariomodelo> resultado = p1.buscarPorNombre(nombreBuscado);
               request.setAttribute("listaUsuarios", resultado);

           } else if (accion.equals("informe")) {
               // --- GENERACIÓN DE INFORME ---
               // Redirigimos al reporte de usuarios en lugar de la vista normal
               pagina = "/rpt/rptusuarios.jsp";
           }

           // Enviamos el mensaje de resultado (éxito o error) a la vista
           request.setAttribute("mensaje", p1);

           // Redirigimos a la vista correspondiente
           request.getRequestDispatcher(pagina).forward(request, response);
       }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
