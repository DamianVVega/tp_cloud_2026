  <%-- 
    Document   : compranuevo
    Created on : 1 jul. 2025, 21:03:47
    Author     : Damian0
--%>
<%@page import="modelos.compramodelo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Esta línea indica que la respuesta enviada por el servidor es HTML
 y que se usará codificación UTF-8 (necesaria para caracteres como ñ, tildes, etc.) -->

<%@page import="java.util.List" %>
<!-- Importamos la clase List del paquete java.util, que nos permite manejar listas de objetos,
 en este caso una lista de ventas, productos o clientes -->
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <!-- Esta etiqueta meta también define la codificación para los navegadores más antiguos -->
 <link rel="stylesheet" href="css/estilo.css"/>
 <!-- Cargamos el archivo CSS general que define los estilos comunes de la web -->
 <link rel="stylesheet" href="css/styleventa.css"/>
 <!-- Cargamos un segundo CSS que contiene estilos específicos para esta página de ventas -->
 <title>NUEVA COMPRA</title>
 <!-- Este es el título que aparece en la pestaña del navegador -->
</head>
<body>
<%
 // Recuperamos los datos de sesión para verificar si el usuario está logueado
 String usuario = (String) session.getAttribute("usuario");
 String tipo = (String) session.getAttribute("tipo");
 String codigo = (String) session.getAttribute("codigo");
 //Si no hay sesión activa, se redirige al login. Esto protege la página contra acceso directo sin autenticación

 if (usuario == null) {
 response.sendRedirect("login.jsp");
 return; // Se detiene la ejecución para evitar que cargue el resto de la página
 }
%>
<!-- CABECERA CON LOGO Y MENÚ DE NAVEGACIÓN -->
<jsp:include page="/vistas/complementos/header.jsp" />
<jsp:include page="/vistas/complementos/menu.jsp" />
<!-- CONTENIDO PRINCIPAL DE LA PÁGINA -->
<div class="div3" style="overflow: auto"> 
    
    <!-- CONTENEDOR PRINCIPAL -->
    <div class="contenedor-principal container bg-light p-4 rounded shadow-sm">
    
        <!-- CABECERA -->
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
    
        <!-- DETALLE DE PRODUCTOS -->
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
    
            <!-- TABLA DETALLE -->
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
    
        <!-- FORMULARIO PARA GUARDAR LA COMPRA   -->
        <form action="compracontrolador" method="POST" onsubmit="return prepararCompra()" class="text-center mt-4 mb-3">

            <input type="hidden" name="txtfecha" value="<%= java.time.LocalDate.now().toString() %>">
            <input type="hidden" name="txtcondicion" id="hiddenCondicion">
            <input type="hidden" name="txtestado" value="Pendiente">
            <input type="hidden" name="txtusuario" value="<%= codigo %>">
            <input type="hidden" name="idproveedor" id="hiddenProveedor">
            <input type="hidden" name="jsonDetalle" id="jsonDetalleCompra">
            <button type="submit" name="accion" value="guardarcompra" class="btn btn-primary" style="margin-bottom: 30px">
                GUARDAR COMPRA
            </button>
        </form>

    </div> <!-- Cierre de contenedor-principal -->

    <!-- MODAL Proveedor-->
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
                compramodelo v = new compramodelo(); // Instanciamos el modelo
                List<String[]> listaProveedores = v.listarProveedores();
                for (String[] prov : listaProveedores) {
                %>
                    <tr>
                        <td><%= prov[1] %></td>
                        <td><%= prov[2] %></td>
                        <td><%= prov[3] %></td>
                        <td>
                            <button type="button" class="btn btn-sm btn-primary"
                                    onclick='seleccionarProveedor("<%= prov[0] %>", "<%= prov[1]%>")'>Seleccionar</button>
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

</div> <!-- Cierre de div3 -->



<!-- PIE DE PÁGINA: se muestra información del usuario conectado -->
 <div class="div4" style="display: flex; flex-direction: column">
         <label style="color: white"><%= usuario %></label>
            <label style="color: white"><%= tipo %></label>
     </div>
<!-- Cargamos el archivo JS que contiene funciones como abrir/cerrar modal y prepararVenta() -->
<script src="js/scriptcompra.js"></script>
</body>
</html>