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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String pagina= "/vistas/usuarios.jsp";
        String accion=request.getParameter("accion");
        usuariomodelo p1=new usuariomodelo();
        if(accion.equals("guardar")){
            p1.setCodigo(request.getParameter("txtcodigo"));
            p1.setUsuario(request.getParameter("txtnombre"));
            p1.setClave(request.getParameter("txtclave"));
            p1.setTipo(request.getParameter("txttipo"));
            p1.setPersonal(request.getParameter("txtpersonal"));
            //Al obtener todos los datos ya se puede ejecutar el insert into
            p1.guardar();
        }else if(accion.equals("eliminar")){
            String codigo = request.getParameter("txtcodigo");
            p1.eliminar(codigo);
            
        }else if ("modificarUsuario".equals(accion)) {
            p1.setCodigo(request.getParameter("txtcodigo"));
            p1.setUsuario(request.getParameter("txtnombre"));
            p1.setClave(request.getParameter("txtclave"));
            p1.setTipo(request.getParameter("txttipo"));
            p1.setPersonal(request.getParameter("txtpersonal"));
            p1.actualizar();
           
         }
        else if ("buscar".equals(accion)) {
            //Proceso para filtrar por nombre
            String nombreBuscado = request.getParameter("txtbuscar");
            List<usuariomodelo> resultado = p1.buscarPorNombre(nombreBuscado);
            request.setAttribute("listaUsuarios", resultado); // Enviás la lista filtrada
            
         }
        else if ("informe".equals(accion)) {
         pagina= "/rpt/rptusuarios.jsp";
         }
        //enviar a la vista el mensaje guardado
        request.setAttribute("mensaje", p1);
        //Redireccionar a la vista productos
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
