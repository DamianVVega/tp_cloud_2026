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
import modelos.personalesmodelo;

/**
 *
 * @author Damian0
 */
@WebServlet(name = "personalescontrolador", urlPatterns = {"/personalescontrolador"})
public class personalescontrolador extends HttpServlet {

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
        String boton = request.getParameter("accion");
        String pagina="/vistas/personales.jsp";
        personalesmodelo per1= new personalesmodelo();
        if(boton.equals("guardar")){
            per1.setNombre(request.getParameter("txtnombre"));
            per1.setApellido(request.getParameter("txtapellido"));
            per1.setDni(request.getParameter("txtdni"));
            per1.setTelefono(request.getParameter("txttelefono"));
            per1.guardar();
           
            
        }else if ("actualizar".equals(boton)) {
            per1.setCodigo(request.getParameter("txtcodigo"));
            per1.setNombre(request.getParameter("txtnombre"));
            per1.setApellido(request.getParameter("txtapellido"));
            per1.setDni(request.getParameter("txtdni"));
            per1.setTelefono(request.getParameter("txttelefono"));
            per1.actualizar();
            
         }else if(boton.equals("eliminar")){
            String codigo = request.getParameter("txtcodigo");
            per1.eliminar(codigo);
        
        }else if(boton.equals("informe")){
             pagina= "/rpt/rptpersonales.jsp";
         }
        request.setAttribute("mensaje", per1);
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
