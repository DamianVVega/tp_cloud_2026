<%@page import="modelos.usuariomodelo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vistas/complementos/header.jsp" />
<jsp:include page="/vistas/modales/AgregarUsuario.jsp" />
<jsp:include page="/vistas/modales/EditarUsuario.jsp" />

<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");
        return;
    }
%>

<%-- Mensaje de resultado de operación (guardar, actualizar, eliminar) --%>
<%
    usuariomodelo ul = (usuariomodelo) request.getAttribute("mensaje");
    if (ul != null && ul.getMensaje() != null) {
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
    <%= ul.getMensaje() %>
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
                <i class="bi bi-shield-lock" style="margin-right:8px;"></i>Usuarios
            </h1>
            <div style="display:flex; gap:10px;">
                <button class="btn btn-primary" onclick="abrir()">
                    <i class="bi bi-plus-circle"></i> Agregar Nuevo Usuario
                </button>
                <form action="usuarioscontrolador" method="post" target="_blank" style="margin:0;">
                    <button class="btn btn-secondary" value="informe" name="accion">
                        <i class="bi bi-printer"></i> Imprimir
                    </button>
                </form>
            </div>
        </div>

        <div style="display:flex; align-items:center; gap:10px; margin-bottom:16px;">
            <form action="usuarioscontrolador" method="post" style="display:flex; gap:8px; margin:0;">
                <input style="width:300px;" class="form-control" name="txtbuscar" type="text" placeholder="Buscar por nombre de usuario">
                <button type="submit" name="accion" value="buscar" class="btn btn-outline-primary">
                    <i class="bi bi-search"></i> Buscar
                </button>
            </form>
        </div>

        <table class="table table-bordered table-hover bg-white">
            <thead class="table-dark">
                <tr>
                    <th>Codigo</th>
                    <th>Nombre</th>
                    <th>Contraseña</th>
                    <th>Tipo</th>
                    <th>Estado</th>
                    <th>Personal Relacionado</th>
                    <th style="text-align:center;">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<usuariomodelo> usuarios2 = (List<usuariomodelo>) request.getAttribute("listaUsuarios");
                    if (usuarios2 == null) {
                        usuariomodelo usu = new usuariomodelo();
                        usuarios2 = usu.listar();
                    }
                    for (usuariomodelo i : usuarios2) {
                %>
                <tr>
                    <td><%= i.getCodigo() %></td>
                    <td><%= i.getUsuario() %></td>
                    <td><%= i.getClave() %></td>
                    <td><%= i.getTipo() %></td>
                    <td><%= i.getEstado() %></td>
                    <td><%= i.getPersonal() %></td>
                    <td style="text-align:center;">
                        <button onclick="abrirEditarUsuarios('<%= i.getCodigo() %>', '<%= i.getUsuario() %>',
                            '<%= i.getClave() %>', '<%= i.getTipo() %>', '<%= i.getPersonal() %>')"
                            class="btn btn-primary btn-sm">
                            <i class="bi bi-pencil"></i> Modificar
                        </button>
                        <form action="<%= request.getContextPath() %>/usuarioscontrolador" method="post" style="display:inline;">
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