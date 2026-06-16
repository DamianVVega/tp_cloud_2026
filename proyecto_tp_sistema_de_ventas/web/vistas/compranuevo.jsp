  <%-- 
    Document   : compranuevo
    Created on : 1 jul. 2025, 21:03:47
    Author     : Damian0
--%>
<%@page import="modelos.compramodelo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/vistas/complementos/header.jsp" />

<%
    String usuario = (String) session.getAttribute("usuario");
    String tipo = (String) session.getAttribute("tipo");
    String codigo = (String) session.getAttribute("codigo");
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");
        return;
    }
%>

<div class="div3" style="overflow:auto;">
    <div class="contenedor-principal container bg-light p-4 rounded shadow-sm">

        <div class="div-cabecera mb-4">
            <h4 class="mb-3">Cabecera de la Compra</h4>
            <div class="row g-3">
                <div class="col-md-3">
                    <label class="form-label">Fecha:</label>
                    <input type="text" id="txtfecha-visible" value="<%= java.time.LocalDate.now() %>" class="form-control" readonly>
                </div>
                <div class="col-md-3">
                    <label class="form-label">Condición:</label>
                    <select id="selectCondicion" class="form-select">
                        <option value="Contado">Contado</option>
                        <option value="Credito">Crédito</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <label class="form-label">Estado:</label>
                    <input type="text" value="Pendiente" class="form-control" readonly>
                </div>
                <div class="col-md-3">
                    <label class="form-label">Usuario:</label>
                    <input type="text" value="<%= codigo %>" class="form-control" readonly>
                </div>
            </div>
        </div>

        <div class="div-cliente mb-4">
            <h4>Proveedor</h4>
            <input type="hidden" id="idproveedor">
            <div class="input-group">
                <input type="text" id="nombreproveedor" class="form-control" readonly>
                <button type="button" class="btn btn-outline-primary" onclick="abrirModalProveedor()">Buscar Proveedores</button>
            </div>
        </div>

        <div class="div-productos mb-4">
            <h4>Detalle de Productos</h4>
            <div class="row g-2 align-items-end producto-formulario mb-3">
                <div class="col-md-2">
                    <label class="form-label">Código:</label>
                    <input type="text" id="codigoProducto" class="form-control" readonly>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-outline-primary mt-4" onclick="abrirModalProducto()">Buscar producto</button>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Nombre:</label>
                    <input type="text" id="nombreProducto" class="form-control" readonly>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Costo:</label>
                    <input type="text" id="precioProducto" class="form-control" readonly>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Cantidad:</label>
                    <input type="number" id="cantidadProducto" class="form-control" min="1" value="1">
                </div>
                <input type="hidden" id="ivaProducto">
                <div class="col-md-2">
                    <button type="button" class="btn btn-success mt-4" onclick="agregarProducto()">Agregar</button>
                </div>
            </div>

            <div class="table-responsive">
                <table id="tablaDetalle" class="table table-bordered table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>Cantidad</th>
                            <th>Nombre</th>
                            <th>Precio Unitario</th>
                            <th>Exenta</th>
                            <th>5%</th>
                            <th>10%</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>

        <form action="compracontrolador" method="POST" onsubmit="return prepararCompra()" class="text-center mt-4 mb-3">
            <input type="hidden" name="txtfecha" value="<%= java.time.LocalDate.now().toString() %>">
            <input type="hidden" name="txtcondicion" id="hiddenCondicion">
            <input type="hidden" name="txtestado" value="Pendiente">
            <input type="hidden" name="txtusuario" value="<%= codigo %>">
            <input type="hidden" name="idproveedor" id="hiddenProveedor">
            <input type="hidden" name="jsonDetalle" id="jsonDetalleCompra">
            <button type="submit" name="accion" value="guardarcompra" class="btn btn-primary" style="margin-bottom:30px;">
                GUARDAR COMPRA
            </button>
        </form>

    </div>

    <!-- MODAL PROVEEDOR -->
    <div id="modalProveedor" class="modal">
        <div class="modal-content p-4 rounded shadow bg-white">
            <span class="close float-end" onclick="cerrarModalProveedor()" style="cursor:pointer;">&times;</span>
            <h3 class="mb-3">Buscar Proveedor</h3>
            <table class="table table-bordered table-hover">
                <thead class="table-secondary">
                    <tr><th>Nombre</th><th>Ruc</th><th>CI</th><th>Acción</th></tr>
                </thead>
                <tbody>
                <%
                    compramodelo v = new compramodelo();
                    List<String[]> listaProveedores = v.listarProveedores();
                    for (String[] prov : listaProveedores) {
                %>
                    <tr>
                        <td><%= prov[1] %></td>
                        <td><%= prov[2] %></td>
                        <td><%= prov[3] %></td>
                        <td>
                            <button type="button" class="btn btn-sm btn-primary"
                                onclick='seleccionarProveedor("<%= prov[0] %>", "<%= prov[1] %>")'>
                                Seleccionar
                            </button>
                        </td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <!-- MODAL PRODUCTO -->
    <div id="modalProducto" class="modal">
        <div class="modal-content p-4 rounded shadow bg-white">
            <span class="close float-end" onclick="cerrarModalProducto()" style="cursor:pointer;">&times;</span>
            <h3 class="mb-3">Buscar Producto</h3>
            <table class="table table-bordered table-hover">
                <thead class="table-secondary">
                    <tr><th>Nombre</th><th>Costo</th><th>IVA</th><th>Acción</th></tr>
                </thead>
                <tbody>
                <%
                    List<String[]> listaProductos = v.listarProductos();
                    for (String[] p : listaProductos) {
                %>
                    <tr>
                        <td><%= p[1] %></td>
                        <td><%= p[2] %></td>
                        <td><%= p[3] %></td>
                        <td>
                            <button type="button" class="btn btn-sm btn-success"
                                onclick="seleccionarProducto('<%= p[0] %>', '<%= p[1] %>', '<%= p[2] %>', '<%= p[3] %>')">
                                Seleccionar
                            </button>
                        </td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>

</div>

<script src="<%= request.getContextPath() %>/js/scriptcompra.js"></script>
<jsp:include page="/vistas/complementos/footer.jsp" />