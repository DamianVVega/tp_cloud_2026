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
import modelos.aperturamodelo;

/**
 *
 * @author Damian0
 */
@WebServlet(name = "menucontrolador", urlPatterns = {"/menucontrolador"})
public class menucontrolador extends HttpServlet {

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
        String pagina = "";
String accion = request.getParameter("accion");

if (accion.equals("producto")) {
    pagina = "/vistas/productos.jsp";
} else if (accion.equals("cliente")) {
    pagina = "/vistas/clientes.jsp";
} else if (accion.equals("personal")) {
    pagina = "/vistas/personales.jsp";
} else if (accion.equals("proveedor")) {
    pagina = "/vistas/proveedores.jsp";
} else if (accion.equals("cerrarC")) {
    aperturamodelo ape = new aperturamodelo();
    ape.setIdusuario(request.getParameter("txtcodusu"));
    if (ape.verificar().equals("cerrar")) {
        pagina = "/vistas/cerrarcaja.jsp";
    } else {
        pagina = "index.jsp";
        request.setAttribute("mensajeape", "No puede cerrar caja, abra una primero.");
    }
} else if (accion.equals("abrirC")) {
    aperturamodelo ape = new aperturamodelo();
    ape.setIdusuario(request.getParameter("txtcodusu"));
    if (ape.verificar().equals("abrir")) {
        pagina = "/vistas/abrircaja.jsp";
    } else {
        pagina = "index.jsp";
        request.setAttribute("mensajeape", "No puede abrir caja, ya hay una abierta.");
    }
} else if (accion.equals("compra")) {
    aperturamodelo ape = new aperturamodelo();
    ape.setIdusuario(request.getParameter("txtcodusu"));
    if (ape.verificar().equals("cerrar")) { // LA CAJA ESTÁ ABIERTA
        pagina = "/vistas/compras.jsp";
    } else {
        pagina = "index.jsp";
        request.setAttribute("mensajeape", "Debe abrir la caja para acceder a compras.");
    }
} else if (accion.equals("venta")) {
    aperturamodelo ape = new aperturamodelo();
    ape.setIdusuario(request.getParameter("txtcodusu"));
    if (ape.verificar().equals("cerrar")) { // LA CAJA ESTÁ ABIERTA
        pagina = "/vistas/ventas.jsp";
    } else {
        pagina = "index.jsp";
        request.setAttribute("mensajeape", "Debe abrir la caja para acceder a ventas.");
    }
} else if (accion.equals("usuario")) {
    pagina = "/vistas/usuarios.jsp";
} else if (accion.equals("cerrarsesion")) {
    pagina = "/vistas/cerrarsesion.jsp";
}

// Redirigir a la página seleccionada
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
