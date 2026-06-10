<%-- 
    Document   : rptclientes
    Created on : 3 jun 2025, 23:13:38
    Author     : guido
--%>


<%@page import="java.util.Map"%>
<%@page import="java.sql.Connection"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="java.util.HashMap"%>
<%@page import="utilidades.conexion"%>


<%
    Connection conn=null;
    try {
        conn = utilidades.conexion.enlace();
        String ruta = application.getRealPath("reportes/rptpersonales.jasper");

        Map<String, Object> parametros = new HashMap<>(); // Si no hay par?metros, queda vac?o
        String usuario = (String) session.getAttribute("usuario");
        parametros.put("usuario", usuario);
        JasperPrint jasperPrint = JasperFillManager.fillReport(ruta, parametros, conn);
        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();
        return; // Para evitar seguir procesando
    } catch (Exception e) {
        out.println("Error al generar el reporte: " + e.getMessage());
    } finally {
        if (conn != null) conn.close();
    }
%>
