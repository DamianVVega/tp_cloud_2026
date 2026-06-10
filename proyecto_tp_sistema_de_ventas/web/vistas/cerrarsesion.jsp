<%-- 
    Document   : cerrarsesion
    Created on : 27 jun. 2025, 18:31:37
    Author     : Damian0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    session.invalidate();
    response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");
%>

