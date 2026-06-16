/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.aperturamodelo;

/**
 *
 * @author Damian0
 */
@WebServlet(name = "aperturacontrolador", urlPatterns = {"/aperturacontrolador"})
public class aperturacontrolador extends HttpServlet {

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
    * Maneja las solicitudes POST para la APERTURA DE CAJA.
    * Recibe el monto inicial y el usuario responsable,
    * registra la apertura en la base de datos y redirige al inicio.
    */
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            processRequest(request, response);

            // Obtenemos la acción enviada desde el formulario
            String accion = request.getParameter("accion");

            // Creamos el modelo de apertura para manejar la lógica de negocio
            aperturamodelo ape = new aperturamodelo();

            // Si el usuario presionó el botón de abrir caja
            if (accion.equals("btnabrir")) {
                try {
                    // Asignamos el monto inicial y el usuario que abre la caja
                    ape.setMonto(request.getParameter("txtmonto"));
                    ape.setIdusuario(request.getParameter("txtusuario"));

                    // Guardamos el registro de apertura en la base de datos
                    ape.guardar();

                } catch (SQLException ex) {
                    // Error al intentar guardar la apertura en la base de datos
                    Logger.getLogger(aperturacontrolador.class.getName())
                          .log(Level.SEVERE, "Error al registrar la apertura de caja", ex);
                }
            }

            // Enviamos el mensaje de resultado (éxito o error) a la vista
            request.setAttribute("mensajeape", ape.getMensaje());

            // Redirigimos al index con el mensaje correspondiente
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
