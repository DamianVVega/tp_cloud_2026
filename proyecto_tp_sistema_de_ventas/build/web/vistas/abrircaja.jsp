<%-- 
    Document   : abrircaja
    Created on : 27 jun. 2025, 14:10:38
    Author     : Damian0
--%>


<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.ZoneOffset"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="modelos.aperturamodelo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
   aperturamodelo ap1 = (aperturamodelo) request.getAttribute("mensaje");
    if (ap1 != null && ap1.getMensaje() != null && !ap1.getMensaje().equals("buscar")) {
%>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            //var mensaje = "<%= ap1.getMensaje().replace("\"", "\\\"").replace("\n", "\\n") %>";
            var div = document.getElementById("mensaje-alerta");
            var span = document.getElementById("mensaje-texto");
            span.innerText = mensaje;
            div.style.display = "block";

            setTimeout(function() {
                div.style.display = "none";
            }, 3000); // se oculta luego de 3 segundos
        });
    </script>
<%
    }
%>

    <div class="titulomodulo"> 
        <h1>Apertura</h1>
    </div>
 
           <div class="container mt-4">
    <div class="card shadow border-primary">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Apertura de Caja</h4>
        </div>
        <div class="card-body">
            <form action="aperturacontrolador" method="post">
                <div class="mb-3">
                    <label class="form-label">Monto</label>
                    <input type="number" name="txtmonto" class="form-control" placeholder="Ingrese monto">
                </div>
                <div class="mb-3">
                    <label class="form-label">Usuario</label>
                    <input type="text" name="txtusuario" value="<%= session.getAttribute("codigo") %>" class="form-control" readonly>
                </div>
                <div class="text-center">
                    <button name="accion" value="btnabrir" class="btn btn-success w-50">Abrir Caja</button>
                </div>
            </form>
        </div>
    </div>
</div>




</div>
<jsp:include page="/vistas/complementos/footer.jsp" />
<script src="<%= request.getContextPath() %>/js/scripts.js"></script>

<!--  <script src="<%= request.getContextPath() %>/js/scripts.js"></script>-->