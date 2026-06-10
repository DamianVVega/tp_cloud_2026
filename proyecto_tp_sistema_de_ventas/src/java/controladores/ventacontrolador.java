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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
                     // Este método puede contener lógica común para GET y POST (si está implementado)
                     processRequest(request, response);
                     // Recuperamos el parámetro "accion" que viene del botón del formulario
                     String accion = request.getParameter("accion");
                     String pagina = ""; // Variable que usaremos para definir a qué JSP redirigir luego
                     // Si el usuario hizo clic en "NUEVA VENTA"
                     if (accion.equals("nuevo")) {
                     pagina = "/vistas/ventanuevo.jsp"; // Mostramos el formulario para cargar una nueva venta
                     }else if (accion.equals("imprimir")) {
                // Capturamos el ID de la venta que queremos imprimir
                String idventa = request.getParameter("txtid");
                String monto_total = request.getParameter("txtmontototal");

                // Convertir el monto a letras
                int montoEntero = Integer.parseInt(monto_total);
                utilidades.pasar_a_letras conversor = new utilidades.pasar_a_letras();
                String montoLetras = conversor.convertir(montoEntero);

                // Codificamos el texto para URL (espacios, tildes, etc.)
                String montoLetrasURL = java.net.URLEncoder.encode(montoLetras, "UTF-8");

                // Redirigir al JSP del reporte pasando ambos parámetros
                pagina = "/rpt/rptventas.jsp?idventa=" + idventa + "&montoletras=" + montoLetrasURL;
            }

                                 // Si el usuario hizo clic en "GUARDAR VENTA"
                                 else if (accion.equals("guardarventa")) {
                try {
                    // 1. Recuperar los datos de la cabecera del formulario
                    String fecha = request.getParameter("txtfecha");
                    String condicion = request.getParameter("txtcondicion");
                    String estado = request.getParameter("txtestado");
                    String cliente = request.getParameter("idcliente");
                    String usuario = request.getParameter("txtusuario");

                    // 2. Crear el objeto de modelo y setear los datos de cabecera
                    ventamodelo venta = new ventamodelo();
                    venta.setFecha(fecha);
                    venta.setCondicion(condicion);
                    venta.setEstado(estado);
                    venta.setCliente(cliente);
                    venta.setUsuario(usuario);

                    // 3. Procesar detalle (formato JSON)
                    String jsonDetalle = request.getParameter("jsonDetalle");
                    if (jsonDetalle != null && !jsonDetalle.isEmpty()) {
                        // 4. Convertimos el JSON en lista de DetalleVenta
                        org.json.JSONArray detalleArray = new org.json.JSONArray(jsonDetalle);
                        List<detalleventa> listaDetalles = new ArrayList<>();

                    for (int i = 0; i < detalleArray.length(); i++) {
                        org.json.JSONObject item = detalleArray.getJSONObject(i);
                        String idproducto = item.optString("idproducto");
                        String cantidad = item.getString("cantidad");
                        String precio = item.getString("precio");

                        listaDetalles.add(new detalleventa(idproducto, cantidad, precio));
                    }

                                // 5. Guardamos TODO con transacción
                                String mensaje = venta.guardarVentaConDetalle(listaDetalles);

                                // 6. Mostrar resultado en JSP
                                request.setAttribute("mensaje", mensaje);
                            } else {
                                request.setAttribute("mensaje", "No se encontró el detalle de la venta.");
                            }
                        } catch (Exception e) {
                            request.setAttribute("mensaje", "ERROR al guardar la venta: " + e.getMessage());
                        }

                        // Redirigimos a la vista de ventas, pase lo que pase
                        pagina = "/vistas/ventas.jsp";
                    }

                     // Redirigimos al JSP correspondiente con el mensaje cargado
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
