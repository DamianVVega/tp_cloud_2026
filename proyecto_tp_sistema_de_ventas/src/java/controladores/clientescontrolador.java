/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.clientesmodelo;

/**
 *
 * @author Damian0
 */
@WebServlet(name = "clientescontrolador", urlPatterns = {"/clientescontrolador"})
public class clientescontrolador extends HttpServlet {

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
    /**
 * Maneja las solicitudes GET para el módulo de CLIENTES.
 * Redirige al método processRequest para mostrar la vista principal.
 */
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    processRequest(request, response);
}

    /**
     * Maneja las solicitudes POST para el módulo de CLIENTES.
     * Permite registrar, actualizar, eliminar clientes
     * y generar el informe/reporte de clientes.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

        // Obtenemos la acción enviada desde el formulario
        String boton = request.getParameter("accion");

        // Vista por defecto a la que se redirigirá al finalizar
        String pagina = "/vistas/clientes.jsp";

        // Creamos el modelo de clientes para manejar la lógica de negocio
        clientesmodelo m1 = new clientesmodelo();

        if (boton.equals("guardar")) {
            // --- REGISTRO DE NUEVO CLIENTE ---
            // Asignamos los datos del formulario al modelo
            m1.setNombre(request.getParameter("txtnombre"));
            m1.setApellido(request.getParameter("txtapellido"));
            m1.setDni(request.getParameter("txtdni"));
            m1.setTelefono(request.getParameter("txttelefono"));

            // Guardamos el nuevo cliente en la base de datos
            m1.guardar();

        } else if (boton.equals("actualizar")) {
            // --- ACTUALIZACIÓN DE CLIENTE EXISTENTE ---
            // Asignamos el código y los nuevos datos del cliente
            m1.setCodigo(request.getParameter("txtcodigo"));
            m1.setNombre(request.getParameter("txtnombre"));
            m1.setApellido(request.getParameter("txtapellido"));
            m1.setDni(request.getParameter("txtdni"));
            m1.setTelefono(request.getParameter("txttelefono"));

            // Actualizamos el registro en la base de datos
            m1.actualizar();

        } else if (boton.equals("eliminar")) {
            // --- ELIMINACIÓN DE CLIENTE ---
            // Obtenemos el código del cliente a eliminar
            String codigo = request.getParameter("txtcodigo");

            // Eliminamos el cliente de la base de datos
            m1.eliminar(codigo);

        } else if (boton.equals("informe")) {
            // --- GENERACIÓN DE INFORME ---
            // Redirigimos al reporte de clientes en lugar de la vista normal
            pagina = "/rpt/rptclientes.jsp";
        }

        // Enviamos el mensaje de resultado (éxito o error) a la vista
        request.setAttribute("mensaje", m1);

        // Redirigimos a la página correspondiente con el resultado
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
