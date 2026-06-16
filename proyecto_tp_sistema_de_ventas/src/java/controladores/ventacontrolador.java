/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.detalleventa;
import modelos.ventamodelo;

/**
 *
 * @author Damian0
 */
@WebServlet(name = "ventacontrolador", urlPatterns = {"/ventacontrolador"})
public class ventacontrolador extends HttpServlet {

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
        * Maneja las solicitudes POST para el módulo de VENTAS.
        * Permite navegar al formulario de nueva venta, imprimir una venta
        * y guardar la cabecera junto al detalle de una venta nueva.
        */
       @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {

           // Este método contiene la lógica principal para las solicitudes POST
           processRequest(request, response);

           // Obtenemos la acción enviada desde el formulario
           String accion = request.getParameter("accion");

           // Vista a la que redirigiremos al finalizar el proceso
           String pagina = "";

           if (accion.equals("nuevo")) {
               // --- NUEVA VENTA ---
               // Redirigimos al formulario para registrar una nueva venta
               pagina = "/vistas/ventanuevo.jsp";

           } else if (accion.equals("imprimir")) {
               // --- IMPRIMIR VENTA ---
               // Obtenemos el ID y monto total de la venta a imprimir
               String idventa = request.getParameter("txtid");
               String monto_total = request.getParameter("txtmontototal");

               // Convertimos el monto numérico a su representación en letras
               int montoEntero = Integer.parseInt(monto_total);
               utilidades.pasar_a_letras conversor = new utilidades.pasar_a_letras();
               String montoLetras = conversor.convertir(montoEntero);

               // Codificamos el texto para URL (para manejar espacios, tildes, etc.)
               String montoLetrasURL = java.net.URLEncoder.encode(montoLetras, "UTF-8");

               // Redirigimos al reporte pasando el ID y el monto en letras como parámetros
               pagina = "/rpt/rptventas.jsp?idventa=" + idventa + "&montoletras=" + montoLetrasURL;

           } else if (accion.equals("guardarventa")) {
               // --- GUARDAR VENTA ---
               try {
                   // 1. Recuperamos los datos de la cabecera desde el formulario
                   String fecha      = request.getParameter("txtfecha");
                   String condicion  = request.getParameter("txtcondicion");
                   String estado     = request.getParameter("txtestado");
                   String cliente    = request.getParameter("idcliente");
                   String usuario    = request.getParameter("txtusuario");

                   // 2. Creamos el modelo y asignamos los datos de la cabecera
                   ventamodelo venta = new ventamodelo();
                   venta.setFecha(fecha);
                   venta.setCondicion(condicion);
                   venta.setEstado(estado);
                   venta.setCliente(cliente);
                   venta.setUsuario(usuario);

                   // 3. Procesamos el detalle de productos si existe
                   String jsonDetalle = request.getParameter("jsonDetalle");

                   if (jsonDetalle != null && !jsonDetalle.isEmpty()) {
                       // 4. Parseamos el JSON y construimos la lista de detalles
                       org.json.JSONArray detalleArray = new org.json.JSONArray(jsonDetalle);
                       List<detalleventa> listaDetalles = new ArrayList<>();

                       for (int i = 0; i < detalleArray.length(); i++) {
                           org.json.JSONObject item = detalleArray.getJSONObject(i);

                           // Extraemos los datos de cada producto del detalle
                           String idproducto = item.optString("idproducto");
                           String cantidad   = item.getString("cantidad");
                           String precio     = item.getString("precio");

                           // Agregamos cada línea a la lista de detalles
                           listaDetalles.add(new detalleventa(idproducto, cantidad, precio));
                       }

                       // 5. Guardamos la venta completa (cabecera + detalle) en una transacción
                       String mensaje = venta.guardarVentaConDetalle(listaDetalles);

                       // 6. Enviamos el resultado de la operación a la vista
                       request.setAttribute("mensaje", mensaje);

                   } else {
                       // No se enviaron productos en el detalle
                       request.setAttribute("mensaje", "No se encontró el detalle de la venta.");
                   }

               } catch (Exception e) {
                   // Error inesperado al guardar la venta
                   request.setAttribute("mensaje", "ERROR al guardar la venta: " + e.getMessage());
               }

               // Redirigimos al listado de ventas con el mensaje de resultado
               pagina = "/vistas/ventas.jsp";
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
