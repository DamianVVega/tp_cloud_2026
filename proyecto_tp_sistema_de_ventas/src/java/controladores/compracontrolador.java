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
import modelos.compramodelo;

/**
 *
 * @author Damian0
 */
@WebServlet(name = "compracontrolador", urlPatterns = {"/compracontrolador"})
public class compracontrolador extends HttpServlet {

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
      * Maneja las solicitudes POST para el módulo de COMPRAS.
      * Permite navegar al formulario de nueva compra, imprimir una compra
      * y guardar la cabecera junto al detalle de una compra nueva.
      */
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {

         processRequest(request, response);

         // Obtenemos la acción enviada desde el formulario
         String accion = request.getParameter("accion");

         // Vista a la que redirigiremos al finalizar el proceso
         String pagina = "";

         if (accion.equals("nuevo")) {
             // --- NUEVA COMPRA ---
             // Redirigimos al formulario para registrar una nueva compra
             pagina = "/vistas/compranuevo.jsp";

         } else if (accion.equals("imprimir")) {
             // --- IMPRIMIR COMPRA ---
             // Obtenemos el ID y monto total de la compra a imprimir
             String idcompra = request.getParameter("txtid");
             String monto_total = request.getParameter("txtmontototal");

             // Convertimos el monto numérico a su representación en letras
             int montoEntero = Integer.parseInt(monto_total);
             utilidades.pasar_a_letras conversor = new utilidades.pasar_a_letras();
             String montoLetras = conversor.convertir(montoEntero);

             // Codificamos el texto para URL (para manejar espacios, tildes, etc.)
             String montoLetrasURL = java.net.URLEncoder.encode(montoLetras, "UTF-8");

             // Redirigimos al reporte pasando el ID y el monto en letras como parámetros
             pagina = "/rpt/rptcompras.jsp?idcompra=" + idcompra + "&montoletras=" + montoLetrasURL;

         } else if (accion.equals("guardarcompra")) {
             // --- GUARDAR COMPRA ---
             try {
                 // 1. Recuperamos los datos de la cabecera desde el formulario
                 String fecha      = request.getParameter("txtfecha");
                 String condicion  = request.getParameter("txtcondicion");
                 String estado     = request.getParameter("txtestado");
                 String proveedor  = request.getParameter("idproveedor");
                 String usuario    = request.getParameter("txtusuario");

                 // 2. Creamos el modelo y asignamos los datos de la cabecera
                 compramodelo compra = new compramodelo();
                 compra.setFecha(fecha);
                 compra.setCondicion(condicion);
                 compra.setEstado(estado);
                 compra.setProveedor(proveedor);
                 compra.setUsuario(usuario);

                 // 3. Guardamos la cabecera y obtenemos el ID generado para el detalle
                 String idcompra = compra.guardarCabecera();

                 // 4. Procesamos el detalle de productos si existe
                 String jsonDetalle = request.getParameter("jsonDetalle");

                 if (jsonDetalle != null && !jsonDetalle.isEmpty()) {
                     // Parseamos el JSON con los productos del detalle
                     org.json.JSONArray detalleArray = new org.json.JSONArray(jsonDetalle);

                     for (int i = 0; i < detalleArray.length(); i++) {
                         org.json.JSONObject item = detalleArray.getJSONObject(i);

                         // Extraemos los datos de cada producto del detalle
                         String idproducto = item.optString("idproducto");
                         String cantidad   = item.getString("cantidad");
                         String costo      = item.getString("costo");

                         // Guardamos cada línea del detalle vinculada a la compra
                         compra.guardarDetalle(idcompra, idproducto, costo, cantidad);
                     }

                     request.setAttribute("mensaje", "Compra guardada correctamente.");

                 } else {
                     // No se enviaron productos en el detalle
                     request.setAttribute("mensaje", "No se encontró el detalle de la compra.");
                 }

             } catch (Exception e) {
                 // Error inesperado al guardar la compra
                 request.setAttribute("mensaje", "ERROR al guardar la compra: " + e.getMessage());
             }

             // Redirigimos al listado de compras con el mensaje de resultado
             pagina = "/vistas/compras.jsp";
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
