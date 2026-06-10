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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String accion= request.getParameter("accion");
        String pagina="";
        //instancia del modelo
        usuariomodelo lo= new usuariomodelo();
        if(accion.equals("btniniciar")){
            try {
                lo.setUsuario(request.getParameter("txtusuario"));
                lo.setClave(request.getParameter("txtclave"));
                if(lo.iniciar().equals("si")){
                    pagina="index.jsp";
                    //Enviar desde el login al menu el nombre del usuario y la contraseña con el cual se logueo
                    HttpSession session= request.getSession();
                    session.setAttribute("usuario", lo.getUsuario());
                    session.setAttribute("codigo", lo.getCodigo());
                    session.setAttribute("tipo", lo.getTipo());
                    
                }else{
                    pagina="/vistas/login.jsp";
                    request.setAttribute("mensaje", "no");
                }
            } catch (SQLException ex) {
                Logger.getLogger(logincontrolador.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
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
