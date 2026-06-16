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
import modelos.productosmodelo;

/**
 *
 * @author Damian0
 */
@WebServlet(name = "productoscontrolador", urlPatterns = {"/productoscontrolador"})
public class productoscontrolador extends HttpServlet {

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
 * Maneja las solicitudes GET para el módulo de PRODUCTOS.
 * Redirige al método processRequest para mostrar la vista principal.
 */
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    processRequest(request, response);
}

    /**
     * Maneja las solicitudes POST para el módulo de PRODUCTOS.
     * Permite registrar, actualizar, eliminar productos
     * y generar el informe/reporte de productos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

        // Vista por defecto a la que redirigiremos al finalizar
        String pagina = "/vistas/productos.jsp";

        // Obtenemos la acción enviada desde el formulario
        String accion = request.getParameter("accion");

        // Creamos el modelo de productos para manejar la lógica de negocio
        productosmodelo p1 = new productosmodelo();

        if (accion.equals("guardar")) {
            // --- REGISTRO DE NUEVO PRODUCTO ---
            // Asignamos los datos del formulario al modelo
            p1.setNombre(request.getParameter("txtnombre"));
            p1.setCantidad(request.getParameter("txtcantidad"));
            p1.setPrecio(request.getParameter("txtprecio"));
            p1.setIva(request.getParameter("txtiva"));
            p1.setCosto(request.getParameter("txtcosto"));

            // Guardamos el nuevo producto en la base de datos
            p1.guardar();

        } else if (accion.equals("actualizar")) {
            // --- ACTUALIZACIÓN DE PRODUCTO EXISTENTE ---
            // Asignamos el código y los nuevos datos del producto
            p1.setCodigo(request.getParameter("txtcodigo"));
            p1.setNombre(request.getParameter("txtnombre"));
            p1.setCantidad(request.getParameter("txtcantidad"));
            p1.setPrecio(request.getParameter("txtprecio"));
            p1.setIva(request.getParameter("txtiva"));
            p1.setCosto(request.getParameter("txtcosto"));

            // Actualizamos el registro en la base de datos
            p1.actualizar();

        } else if (accion.equals("eliminar")) {
            // --- ELIMINACIÓN DE PRODUCTO ---
            // Obtenemos el código del producto a eliminar
            String codigo = request.getParameter("txtcodigo");

            // Eliminamos el producto de la base de datos
            p1.eliminar(codigo);

        } else if (accion.equals("informe")) {
            // --- GENERACIÓN DE INFORME ---
            // Redirigimos al reporte de productos en lugar de la vista normal
            pagina = "/rpt/rptproductos.jsp";
        }

        // Enviamos el mensaje de resultado (éxito o error) a la vista
        request.setAttribute("mensaje", p1);

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
