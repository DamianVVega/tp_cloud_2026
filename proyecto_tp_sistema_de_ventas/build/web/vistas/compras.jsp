<%@page import="java.util.List"%>
<%@page import="modelos.compramodelo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/vistas/complementos/header.jsp" />
<%
    String usuario = (String) session.getAttribute("usuario");
    String tipo = (String) session.getAttribute("tipo");
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");
        return;
    }
%>
<div class="div3" style="overflow:auto;">
    <div style="width:100%; padding:16px 28px;">
        <div style="display:flex; align-items:center; justify-content:space-between; margin-bottom:18px;">
            <h1 style="font-size:26px; font-weight:700; color:#1a3a6a; margin:0;">
                <i class="bi bi-cart3" style="margin-right:8px;"></i>Listado de Compras
            </h1>
            <form action="compracontrolador" method="POST" style="margin:0;">
                <button name="accion" value="nuevo" class="btn btn-primary">
                    <i class="bi bi-plus-circle"></i> Nueva Compra
                </button>
            </form>
        </div>
        <%
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null && !mensaje.isEmpty()) {
        %>
        <div style="padding:10px; margin-bottom:16px; background-color:#d4edda; color:#155724;
                    border:1px solid #c3e6cb; border-radius:6px;">
            <strong><%= mensaje %></strong>
        </div>
        <% } %>
        <%
            compramodelo modelo = new compramodelo();
            List<compramodelo> lista = modelo.listar();
        %>
        <table class="table table-bordered table-hover bg-white">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Fecha</th>
                    <th>Condición</th>
                    <th>Estado</th>
                    <th>Proveedor</th>
                    <th>Usuario</th>
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
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/vistas/complementos/footer.jsp" />