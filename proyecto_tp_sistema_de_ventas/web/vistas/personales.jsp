<%@page import="modelos.personalesmodelo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vistas/complementos/header.jsp" />
<jsp:include page="/vistas/modales/AgregarPersonales.jsp" />
<jsp:include page="/vistas/modales/EditarPersonales.jsp" />

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");
        return;
    }
%>

<%-- Mensaje de resultado de operación (guardar, actualizar, eliminar) --%>
<%
    personalesmodelo per1 = (personalesmodelo) request.getAttribute("mensaje");
    if (per1 != null && per1.getMensaje() != null) {
%>
<div style="
    position: fixed;
    top: 160px;
    left: 50%;
    transform: translateX(-50%);
    background-color: #1a56a0;
    color: white;
    padding: 12px 28px;
    border-radius: 8px;
    font-size: 15px;
    font-weight: 600;
    z-index: 9999;
    box-shadow: 0 4px 12px rgba(0,0,0,0.3);
    text-align: center;
    min-width: 250px;"
    id="toast-mensaje">
    <i class="bi bi-check-circle" style="margin-right:8px;"></i>
    <%= per1.getMensaje() %>
</div>
<script>
    setTimeout(function() {
        var el = document.getElementById("toast-mensaje");
        if (el) el.style.display = "none";
    }, 3000);
</script>
<% } %>

<div class="div3" style="overflow:auto;">
    <div style="width:100%; padding:16px 28px;">
        <div style="display:flex; align-items:center; justify-content:space-between; margin-bottom:18px;">
            <h1 style="font-size:26px; font-weight:700; color:#1a3a6a; margin:0;">
                <i class="bi bi-person-badge" style="margin-right:8px;"></i>Personal
            </h1>
            <div style="display:flex; gap:10px;">
                <button class="btn btn-primary" onclick="abrir()">
                    <i class="bi bi-plus-circle"></i> Agregar Nuevo Personal
                </button>
                <form action="<%= request.getContextPath() %>/personalescontrolador" method="post" target="_blank" style="margin:0;">
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
                    personalesmodelo per = new personalesmodelo();
                    List<personalesmodelo> personales = per.listar();
                    for (personalesmodelo i : personales) {
                %>
                <tr>
                    <td><%= i.getNombre() %></td>
                    <td><%= i.getApellido() %></td>
                    <td><%= i.getDni() %></td>
                    <td><%= i.getTelefono() %></td>
                    <td style="text-align:center;">
                        <button onclick="abrirEditarPersonales('<%= i.getCodigo() %>', '<%= i.getNombre() %>',
                            '<%= i.getApellido() %>', '<%= i.getDni() %>', '<%= i.getTelefono() %>')"
                            class="btn btn-primary btn-sm">
                            <i class="bi bi-pencil"></i> Modificar
                        </button>
                        <form action="<%= request.getContextPath() %>/personalescontrolador" method="post" style="display:inline;">
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