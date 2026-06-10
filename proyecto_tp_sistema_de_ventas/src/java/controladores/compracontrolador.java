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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
// Recuperamos el parámetro "accion" que viene del botón del formulario
String accion = request.getParameter("accion");
String pagina = ""; // Variable que usaremos para definir a qué JSP redirigir luego

// Si el usuario hizo clic en "NUEVA COMPRA"
if (accion.equals("nuevo")) {
    pagina = "/vistas/compranuevo.jsp"; // Mostramos el formulario para cargar una nueva compra

} else if (accion.equals("imprimir")) {
    // Capturamos el ID de la compra que queremos imprimir
    String idcompra = request.getParameter("txtid");
    String monto_total = request.getParameter("txtmontototal");

    // Convertir el monto a letras
    int montoEntero = Integer.parseInt(monto_total);
    utilidades.pasar_a_letras conversor = new utilidades.pasar_a_letras();
    String montoLetras = conversor.convertir(montoEntero);

    // Codificamos el texto para URL (espacios, tildes, etc.)
    String montoLetrasURL = java.net.URLEncoder.encode(montoLetras, "UTF-8");

    // Redirigir al JSP del reporte pasando ambos parámetros
    pagina = "/rpt/rptcompras.jsp?idcompra=" + idcompra + "&montoletras=" + montoLetrasURL;

} else if (accion.equals("guardarcompra")) {
    try {
        // 1. Recuperar los datos de la cabecera del formulario
        String fecha = request.getParameter("txtfecha");
        String condicion = request.getParameter("txtcondicion");
        String estado = request.getParameter("txtestado");
        String proveedor = request.getParameter("idproveedor");
        String usuario = request.getParameter("txtusuario");

        // 2. Crear el objeto de modelo y setear los datos de cabecera
        compramodelo compra = new compramodelo();
        compra.setFecha(fecha);
        compra.setCondicion(condicion);
        compra.setEstado(estado);
        compra.setProveedor(proveedor);
        compra.setUsuario(usuario);

        // 3. Guardar cabecera y obtener el ID generado
        String idcompra = compra.guardarCabecera(); // Este método debe retornar el ID de la compra

        // 4. Procesar el detalle si hay datos en jsonDetalle
        String jsonDetalle = request.getParameter("jsonDetalle");
        if (jsonDetalle != null && !jsonDetalle.isEmpty()) {
            org.json.JSONArray detalleArray = new org.json.JSONArray(jsonDetalle);

            for (int i = 0; i < detalleArray.length(); i++) {
                org.json.JSONObject item = detalleArray.getJSONObject(i);
                String idproducto = item.optString("idproducto");
                String cantidad = item.getString("cantidad");
                String costo = item.getString("costo"); // En compras, se asume que 'precio' es el costo

                compra.guardarDetalle(idcompra, idproducto, costo, cantidad);
            }

            request.setAttribute("mensaje", "Compra guardada correctamente.");
        } else {
            request.setAttribute("mensaje", "No se encontró el detalle de la compra.");
        }

    } catch (Exception e) {
        request.setAttribute("mensaje", "ERROR al guardar la compra: " + e.getMessage());
    }

    // Redirigimos a la vista de compras
    pagina = "/vistas/compras.jsp";
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
