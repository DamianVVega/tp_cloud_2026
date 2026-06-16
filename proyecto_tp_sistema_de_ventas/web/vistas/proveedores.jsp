<%-- 
    Document   : proveedores
    Created on : 27 jun. 2025, 14:09:39
    Author     : Damian0
--%>

<%@page import="modelos.proveedoresmodelo"%>
<%@page import="modelos.usuariomodelo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vistas/complementos/header.jsp" />
<jsp:include page="/vistas/modales/AgregarProveedores.jsp" />
<jsp:include page="/vistas/modales/EditarProveedores.jsp" />

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");
        return;
    }
%>

<%
    proveedoresmodelo p1 = (proveedoresmodelo) request.getAttribute("mensaje");
    if (p1 != null && p1.getMensaje() != null && !p1.getMensaje().equals("buscar")) {
%>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var mensaje = "<%= p1.getMensaje().replace("\"", "\\\"").replace("\n", "\\n") %>";
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
                <i class="bi bi-truck" style="margin-right:8px;"></i>Proveedores
            </h1>
            <div style="display:flex; gap:10px;">
                <button class="btn btn-primary" onclick="abrir()">
                    <i class="bi bi-plus-circle"></i> Agregar Nuevo Proveedor
                </button>
                <form action="<%= request.getContextPath() %>/proveedorescontrolador" method="post" target="_blank" style="margin:0;">
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
                    <th>Ruc</th>
                    <th>Telefono</th>
                    <th>Correo</th>
                    <th style="text-align:center;">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    proveedoresmodelo prov = new proveedoresmodelo();
                    List<proveedoresmodelo> proveedores = prov.listar();
                    for (proveedoresmodelo i : proveedores) {
                %>
                <tr>
                    <td><%= i.getNombre() %></td>
                    <td><%= i.getRuc() %></td>
                    <td><%= i.getTelefono() %></td>
                    <td><%= i.getCorreo() %></td>
                    <td style="text-align:center;">
                        <button onclick="abrirEditarProveedores('<%= i.getCodigo() %>', '<%= i.getNombre() %>',
                            '<%= i.getRuc() %>', '<%= i.getTelefono() %>', '<%= i.getCorreo() %>')"
                            class="btn btn-primary btn-sm">
                            <i class="bi bi-pencil"></i> Modificar
                        </button>
                        <form action="<%= request.getContextPath() %>/proveedorescontrolador" method="post" style="display:inline;">
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