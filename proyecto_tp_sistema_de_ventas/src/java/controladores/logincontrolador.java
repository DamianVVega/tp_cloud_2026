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
import javax.servlet.http.HttpSession;
import modelos.usuariomodelo;

/**
 *
 * @author Damian0
 */
@WebServlet(name = "logincontrolador", urlPatterns = {"/logincontrolador"})
public class logincontrolador extends HttpServlet {

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
        * Maneja las solicitudes POST para el módulo de LOGIN.
        * Valida las credenciales del usuario y, si son correctas,
        * inicia la sesión y redirige al menú principal.
        * En caso contrario, regresa al login con un mensaje de error.
        */
       @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {

           processRequest(request, response);

           // Obtenemos la acción enviada desde el formulario
           String accion = request.getParameter("accion");

           // Vista a la que redirigiremos al finalizar el proceso
           String pagina = "";

           // Creamos el modelo de usuario para manejar la lógica de autenticación
           usuariomodelo lo = new usuariomodelo();

           if (accion.equals("btniniciar")) {
               // --- INICIO DE SESIÓN ---
               try {
                   // Asignamos las credenciales ingresadas por el usuario
                   lo.setUsuario(request.getParameter("txtusuario"));
                   lo.setClave(request.getParameter("txtclave"));

                   // Verificamos si las credenciales son válidas en la base de datos
                   if (lo.iniciar().equals("si")) {
                       // --- CREDENCIALES CORRECTAS ---
                       // Redirigimos al menú principal
                       pagina = "index.jsp";

                       // Iniciamos la sesión y guardamos los datos del usuario logueado
                       HttpSession session = request.getSession();
                       session.setAttribute("usuario", lo.getUsuario()); // Nombre de usuario
                       session.setAttribute("codigo", lo.getCodigo());   // Código/ID del usuario
                       session.setAttribute("tipo", lo.getTipo());       // Tipo/rol del usuario

                   } else {
                       // --- CREDENCIALES INCORRECTAS ---
                       // Regresamos al login con un mensaje de error
                       pagina = "/vistas/login.jsp";
                       request.setAttribute("mensaje", "no");
                   }

               } catch (SQLException ex) {
                   // Error al intentar validar las credenciales en la base de datos
                   Logger.getLogger(logincontrolador.class.getName())
                         .log(Level.SEVERE, "Error al intentar iniciar sesión", ex);
               }
           }

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
