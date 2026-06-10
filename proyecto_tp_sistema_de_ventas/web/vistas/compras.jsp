<%@page import="java.util.List"%>
<%@page import="modelos.compramodelo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/vistas/complementos/header.jsp" />
<jsp:include page="/vistas/complementos/menu.jsp" />

<%
    // Verificar sesión activa
    String usuario = (String) session.getAttribute("usuario");
    String tipo = (String) session.getAttribute("tipo");
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");
        return;
    }
%>

<div class="div3" style="overflow: auto">
    <h1>LISTADO DE COMPRAS</h1>

    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null && !mensaje.isEmpty()) {
    %>
    <div style="padding: 10px; margin: 10px 0; background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb;">
        <strong><%= mensaje %></strong>
    </div>
    <%
        }
    %>

    <!-- Botón para crear una nueva compra -->
    <form action="compracontrolador" method="POST">
        <button name="accion" value="nuevo" class="btn btn-primary">NUEVA COMPRA</button>
    </form>

    <%
        // Instanciar el modelo y obtener la lista de compras
        compramodelo modelo = new compramodelo();
        List<compramodelo> lista = modelo.listar();
    %>

    <table border="1" class="table table-bordered table-striped table-hover mt-3">
        <thead>
            <tr>
                <th>ID</th>
                <th>FECHA</th>
                <th>CONDICIÓN</th>
                <th>ESTADO</th>
                <th>PROVEEDOR</th>
                <th>USUARIO</th>
                <th>ACCIONES</th>
            </tr>
        </thead>
        <tbody>
            <% for (compramodelo i : lista) { %>
            <tr>
                <td><%= i.getId() %></td>
                <td><%= i.getFecha() %></td>
                <td><%= i.getCondicion() %></td>
                <td><%= i.getEstado() %></td>
                <td><%= i.getProveedor() %></td>
                <td><%= i.getUsuario() %></td>
                <td>
                    <!-- Aquí podrías agregar botones de acciones (ver, imprimir, eliminar, etc.) -->
                    <form action="compracontrolador" method="POST" style="display:inline;">
                        <input type="hidden" name="id" value="<%= i.getId() %>">
                        <button class="btn btn-sm btn-outline-secondary" name="accion" value="imprimir">Imprimir</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>

<div class="div4" style="display: flex; flex-direction: column">
    <label style="color: white"><%= usuario %></label>
    <label style="color: white"><%= tipo %></label>
</div>
