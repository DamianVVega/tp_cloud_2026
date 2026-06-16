<%-- 
    Document   : clientes
    Created on : 27 jun. 2025, 14:10:38
    Author     : Damian0
--%>

<%@page import="modelos.clientesmodelo"%>
<%@page import="modelos.usuariomodelo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vistas/complementos/header.jsp" />
<jsp:include page="/vistas/modales/AgregarClientes.jsp" />
<jsp:include page="/vistas/modales/EditarClientes.jsp" />

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");
        return;
    }
%>

<%
    clientesmodelo c1 = (clientesmodelo) request.getAttribute("mensaje");
    if (c1 != null && c1.getMensaje() != null && !c1.getMensaje().equals("buscar")) {
%>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var mensaje = "<%= c1.getMensaje().replace("\"", "\\\"").replace("\n", "\\n") %>";
        var div = document.getElementById("mensaje-alerta");
        var span = document.getElementById("mensaje-texto");
        span.innerText = mensaje;
        div.style.display = "block";
        setTimeout(function() { div.style.display = "none"; }, 3000);
    });
</script>
<% } %>

<div class="div3" style="overflow:auto;">
    <div style="width:100%; padding:16px 28px;">
        <div style="display:flex; align-items:center; justify-content:space-between; margin-bottom:18px;">
            <h1 style="font-size:26px; font-weight:700; color:#1a3a6a; margin:0;">
                <i class="bi bi-people" style="margin-right:8px;"></i>Clientes
            </h1>
            <div style="display:flex; gap:10px;">
                <button class="btn btn-primary" onclick="abrir()">
                    <i class="bi bi-plus-circle"></i> Agregar Nuevo Cliente
                </button>
                <form action="<%= request.getContextPath() %>/clientescontrolador" method="post" target="_blank" style="margin:0;">
                    <button class="btn btn-secondary" value="informe" name="accion">
                        <i class="bi bi-printer"></i> Imprimir
                    </button>
                </form>
            </div>
        </div>

        <table class="table table-bordered table-hover bg-white">
            <thead class="table-dark">
                <tr>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>CI</th>
                    <th>Telefono</th>
                    <th style="text-align:center;">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    clientesmodelo cli = new clientesmodelo();
                    List<clientesmodelo> clientes = cli.listar();
                    for (clientesmodelo i : clientes) {
                %>
                <tr>
                    <td><%= i.getNombre() %></td>
                    <td><%= i.getApellido() %></td>
                    <td><%= i.getDni() %></td>
                    <td><%= i.getTelefono() %></td>
                    <td style="text-align:center;">
                        <button onclick="abrirEditarClientes('<%= i.getCodigo() %>', '<%= i.getNombre() %>',
                            '<%= i.getApellido() %>', '<%= i.getDni() %>', '<%= i.getTelefono() %>')"
                            class="btn btn-primary btn-sm">
                            <i class="bi bi-pencil"></i> Modificar
                        </button>
                        <form action="<%= request.getContextPath() %>/clientescontrolador" method="post" style="display:inline;">
                            <input type="hidden" name="txtcodigo" value="<%= i.getCodigo() %>">
                            <button class="btn btn-danger btn-sm" value="eliminar" name="accion">
                                <i class="bi bi-trash"></i> Eliminar
                            </button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>

<script src="<%= request.getContextPath() %>/js/scripts.js"></script>
<jsp:include page="/vistas/complementos/footer.jsp" />