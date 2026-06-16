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
import modelos.proveedoresmodelo;

/**
 *
 * @author Damian0
 */
@WebServlet(name = "proveedorescontrolador", urlPatterns = {"/proveedorescontrolador"})
public class proveedorescontrolador extends HttpServlet {

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
        * Maneja las solicitudes POST para el módulo de PROVEEDORES.
        * Permite registrar, actualizar, eliminar proveedores
        * y generar el informe/reporte de proveedores.
        */
       @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {

           processRequest(request, response);

           // Obtenemos la acción enviada desde el formulario
           String boton = request.getParameter("accion");

           // Vista por defecto a la que redirigiremos al finalizar
           String pagina = "/vistas/proveedores.jsp";

           // Creamos el modelo de proveedores para manejar la lógica de negocio
           proveedoresmodelo per1 = new proveedoresmodelo();

           if (boton.equals("guardar")) {
               // --- REGISTRO DE NUEVO PROVEEDOR ---
               // Asignamos los datos del formulario al modelo
               per1.setNombre(request.getParameter("txtnombre"));
               per1.setRuc(request.getParameter("txtruc"));
               per1.setTelefono(request.getParameter("txttelefono"));
               per1.setCorreo(request.getParameter("txtcorreo"));

               // Guardamos el nuevo proveedor en la base de datos
               per1.guardar();

           } else if (boton.equals("actualizar")) {
               // --- ACTUALIZACIÓN DE PROVEEDOR EXISTENTE ---
               // Asignamos el código y los nuevos datos del proveedor
               per1.setCodigo(request.getParameter("txtcodigo"));
               per1.setNombre(request.getParameter("txtnombre"));
               per1.setRuc(request.getParameter("txtruc"));
               per1.setTelefono(request.getParameter("txttelefono"));
               per1.setCorreo(request.getParameter("txtcorreo"));

               // Actualizamos el registro en la base de datos
               per1.actualizar();

           } else if (boton.equals("eliminar")) {
               // --- ELIMINACIÓN DE PROVEEDOR ---
               // Obtenemos el código del proveedor a eliminar
               String codigo = request.getParameter("txtcodigo");

               // Eliminamos el proveedor de la base de datos
               per1.eliminar(codigo);

           } else if (boton.equals("informe")) {
               // --- GENERACIÓN DE INFORME ---
               // Redirigimos al reporte de proveedores en lugar de la vista normal
               pagina = "/rpt/rptproveedores.jsp";
           }

           // Enviamos el mensaje de resultado (éxito o error) a la vista
           request.setAttribute("mensaje", per1);

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
