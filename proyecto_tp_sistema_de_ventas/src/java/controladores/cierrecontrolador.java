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
import modelos.cierremodelo;

/**
 *
 * @author Damian0
 */
@WebServlet(name = "cierrecontrolador", urlPatterns = {"/cierrecontrolador"})
public class cierrecontrolador extends HttpServlet {

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
         * Maneja las solicitudes POST para el CIERRE DE CAJA.
         * Registra el monto final, cierra la apertura activa
         * y actualiza el estado de la caja en la base de datos.
         */
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            processRequest(request, response);

            // Obtenemos la acción enviada desde el formulario
            String accion = request.getParameter("accion");

            // Creamos el modelo de cierre para manejar la lógica de negocio
            cierremodelo cie = new cierremodelo();

            // Si el usuario presionó el botón de cerrar caja
            if (accion.equals("btncerrar")) {

                // Asignamos el monto final y el ID de la apertura a cerrar
                cie.setMonto(request.getParameter("txtmonto"));
                cie.setIdapertura(request.getParameter("txtapertura"));

                // Registramos el cierre de caja en la base de datos
                cie.cerrar();

                // Actualizamos el estado de la apertura a "cerrada"
                cie.actualizarapertura();

                // Enviamos mensaje de confirmación a la vista
                request.setAttribute("mensajecie", "Caja cerrada");
            }

            // Redirigimos al index con el resultado de la operación
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
