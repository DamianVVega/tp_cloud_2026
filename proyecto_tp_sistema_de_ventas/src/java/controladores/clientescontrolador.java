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
        String boton=request.getParameter("accion");
        String pagina="/vistas/clientes.jsp";
        clientesmodelo m1= new clientesmodelo();
        if(boton.equals("guardar")){
            m1.setNombre(request.getParameter("txtnombre"));
            m1.setApellido(request.getParameter("txtapellido"));
            m1.setDni(request.getParameter("txtdni"));
            m1.setTelefono(request.getParameter("txttelefono"));
            m1.guardar();
        }else if ("actualizar".equals(boton)) {
            m1.setCodigo(request.getParameter("txtcodigo"));
            m1.setNombre(request.getParameter("txtnombre"));
            m1.setApellido(request.getParameter("txtapellido"));
            m1.setDni(request.getParameter("txtdni"));
            m1.setTelefono(request.getParameter("txttelefono"));
            m1.actualizar();
            request.setAttribute("mensaje", m1);
         }else if(boton.equals("eliminar")){
            String codigo = request.getParameter("txtcodigo");
            m1.eliminar(codigo);
            request.setAttribute("mensaje", m1);
        }else if(boton.equals("informe")){
             pagina= "/rpt/rptclientes.jsp";
         }
        request.setAttribute("mensaje", m1);
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
