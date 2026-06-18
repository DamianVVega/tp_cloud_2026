<%-- 
    Document   : rptventas
    Created on : 30 jun. 2025, 14:49:41
    Author     : Damian0
--%>

<%@page import="java.util.Map"%>
<%@page import="java.sql.Connection"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="java.util.HashMap"%>
<%@page import="utilidades.conexion"%>


<%
Connection conn = null;
try {
    conn = utilidades.conexion.enlace();
    String ruta = application.getRealPath("reportes/rptventas.jasper");

    // Capturamos los parámetros recibidos por la URL
    String idventa = request.getParameter("idventa");
    String montoLetras = request.getParameter("montoletras");

    // Creamos el mapa de parámetros
    Map<String, Object> parametros = new HashMap<>();
    parametros.put("factura", Integer.parseInt(idventa)); // parámetro numérico
    parametros.put("letras", montoLetras);           // parámetro en letras

    // Llenamos el reporte
    JasperPrint jasperPrint = JasperFillManager.fillReport(ruta, parametros, conn);
    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

    // Devolvemos el PDF al navegador
    response.setContentType("application/pdf");
    response.setContentLength(bytes.length);
    response.getOutputStream().write(bytes);
    response.getOutputStream().flush();
    response.getOutputStream().close();
    return;
} catch (Exception e) {
    out.println("Error al generar el reporte: " + e.getMessage());
} finally {
    if (conn != null) conn.close();
}
%>

