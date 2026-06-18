<%-- 
    Document   : venta
    Created on : 27 jun. 2025, 14:14:09
    Author     : Damian0
--%>
<%@page import="java.util.List"%>
<%@page import="modelos.ventamodelo"%>
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
                <i class="bi bi-receipt" style="margin-right:8px;"></i>Listado de Ventas
            </h1>
            <form action="ventacontrolador" method="POST" style="margin:0;">
                <button name="accion" value="nuevo" class="btn btn-primary">
                    <i class="bi bi-plus-circle"></i> Nueva Venta
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
            ventamodelo v = new ventamodelo();
            List<ventamodelo> lista = v.listar();
        %>
        <table class="table table-bordered table-hover bg-white">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Fecha</th>
                    <th>Condición</th>
                    <th>Estado</th>
                    <th>Cliente</th>
                    <th>Usuario</th>
                    <th style="text-align:center;">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% for (ventamodelo i : lista) { %>
                <tr>
                    <td><%= i.getId() %></td>
                    <td><%= i.getFecha() %></td>
                    <td><%= i.getCondicion() %></td>
                    <td><%= i.getEstado() %></td>
                    <td><%= i.getCliente() %></td>
                    <td><%= i.getUsuario() %></td>
                    <td style="text-align:center;">
                        <form action="ventacontrolador" method="POST" style="display:inline;">
                            <input type="hidden" name="txtid" value="<%= i.getId() %>">
                            <% int monto = v.calcularTotalVenta(i.getId()); %>
                            <input type="hidden" name="txtmontototal" value="<%= monto %>">
                            <button name="accion" value="imprimir" class="btn btn-secondary btn-sm">
                                <i class="bi bi-printer"></i> Imprimir
                            </button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/vistas/complementos/footer.jsp" />