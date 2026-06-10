<%-- 
    Document   : index.jsp
    Created on : 26 jun. 2025, 16:43:24
    Author     : Damian0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/vistas/complementos/header.jsp" />
<jsp:include page="/vistas/complementos/menu.jsp" />
<div class="div3">
     <%
            String usuario= (String)session.getAttribute("usuario");
            if(usuario == null){
               response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");

                return;
            }
        %>
     <%
    String mensaje = (String) request.getAttribute("mensajeape");
    String mensaje2 = (String) request.getAttribute("mensajecie");
%>

<% if (mensaje != null || mensaje2 != null) { %>
    <h2 style="margin-top: 30px;color: #155724; background-color: #d4edda; padding: 10px; border-radius: 10px; border: 1px solid #c3e6cb; text-align: center; font-family: Arial, sans-serif;">
        <%= mensaje != null ? mensaje : "" %> <%= mensaje2 != null ? mensaje2 : "" %>
    </h2>
<% } %>

</div>
<jsp:include page="/vistas/complementos/footer.jsp" />