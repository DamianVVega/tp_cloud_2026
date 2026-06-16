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
    /**
        * Maneja las solicitudes POST para el MENÚ PRINCIPAL.
        * Controla la navegación entre módulos del sistema y verifica
        * el estado de la caja antes de permitir acceso a ventas y compras.
        */
       @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {

           processRequest(request, response);

           // Vista a la que redirigiremos al finalizar
           String pagina = "";

           // Obtenemos la acción enviada desde el menú
           String accion = request.getParameter("accion");

           if (accion.equals("producto")) {
               // --- MÓDULO PRODUCTOS ---
               pagina = "/vistas/productos.jsp";

           } else if (accion.equals("cliente")) {
               // --- MÓDULO CLIENTES ---
               pagina = "/vistas/clientes.jsp";

           } else if (accion.equals("personal")) {
               // --- MÓDULO PERSONAL ---
               pagina = "/vistas/personales.jsp";

           } else if (accion.equals("proveedor")) {
               // --- MÓDULO PROVEEDORES ---
               pagina = "/vistas/proveedores.jsp";

           } else if (accion.equals("usuario")) {
               // --- MÓDULO USUARIOS ---
               pagina = "/vistas/usuarios.jsp";

           } else if (accion.equals("cerrarC")) {
               // --- CIERRE DE CAJA ---
               // Verificamos si el usuario tiene una caja abierta para poder cerrarla
               aperturamodelo ape = new aperturamodelo();
               ape.setIdusuario(request.getParameter("txtcodusu"));

               if (ape.verificar().equals("cerrar")) {
                   // La caja está abierta, permitimos el cierre
                   pagina = "/vistas/cerrarcaja.jsp";
               } else {
                   // No hay caja abierta, no se puede cerrar
                   pagina = "index.jsp";
                   request.setAttribute("mensajeape", "No puede cerrar caja, abra una primero.");
               }

           } else if (accion.equals("abrirC")) {
               // --- APERTURA DE CAJA ---
               // Verificamos que no haya ya una caja abierta antes de abrir otra
               aperturamodelo ape = new aperturamodelo();
               ape.setIdusuario(request.getParameter("txtcodusu"));

               if (ape.verificar().equals("abrir")) {
                   // No hay caja abierta, permitimos la apertura
                   pagina = "/vistas/abrircaja.jsp";
               } else {
                   // Ya existe una caja abierta, no se permite abrir otra
                   pagina = "index.jsp";
                   request.setAttribute("mensajeape", "No puede abrir caja, ya hay una abierta.");
               }

           } else if (accion.equals("compra")) {
               // --- MÓDULO COMPRAS ---
               // Solo se permite acceder si la caja está abierta
               aperturamodelo ape = new aperturamodelo();
               ape.setIdusuario(request.getParameter("txtcodusu"));

               if (ape.verificar().equals("cerrar")) {
                   // Caja abierta, permitimos el acceso al módulo de compras
                   pagina = "/vistas/compras.jsp";
               } else {
                   // Caja cerrada, bloqueamos el acceso
                   pagina = "index.jsp";
                   request.setAttribute("mensajeape", "Debe abrir la caja para acceder a compras.");
               }

           } else if (accion.equals("venta")) {
               // --- MÓDULO VENTAS ---
               // Solo se permite acceder si la caja está abierta
               aperturamodelo ape = new aperturamodelo();
               ape.setIdusuario(request.getParameter("txtcodusu"));

               if (ape.verificar().equals("cerrar")) {
                   // Caja abierta, permitimos el acceso al módulo de ventas
                   pagina = "/vistas/ventas.jsp";
               } else {
                   // Caja cerrada, bloqueamos el acceso
                   pagina = "index.jsp";
                   request.setAttribute("mensajeape", "Debe abrir la caja para acceder a ventas.");
               }

           } else if (accion.equals("cerrarsesion")) {
               // --- CERRAR SESIÓN ---
               // Redirigimos a la vista que destruye la sesión del usuario
               pagina = "/vistas/cerrarsesion.jsp";
           }

           // Redirigimos a la vista correspondiente según la acción ejecutada
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
