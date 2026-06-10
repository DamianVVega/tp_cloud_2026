<%-- 
    Document   : venta
    Created on : 27 jun. 2025, 14:14:09
    Author     : Damian0
--%>
<%@page import="java.util.List"%> <!-- Importamos la clase List para poder manejar listas -->
<%@page import="modelos.ventamodelo"%> <!-- Importamos nuestro modelo de ventas para
acceder a los métodos de la base -->
<%@page contentType="text/html" pageEncoding="UTF-8"%> <!-- Indicamos que la página se
renderiza como HTML y usará UTF-8 -->
<jsp:include page="/vistas/complementos/header.jsp" />
<jsp:include page="/vistas/complementos/menu.jsp" />
 <body>
    
 <%-- Verificamos si hay sesión activa. Si no hay, redireccionamos al login 
 --%>
 <%
            String usuario= (String)session.getAttribute("usuario");
            String tipo = (String) session.getAttribute("tipo");
            if(usuario == null){
               response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");

                return;
            }
        %>
 
 <!-- CONTENIDO PRINCIPAL DE LA PÁGINA -->

<div class="div3" style="overflow: auto">  
            <h1>LISTADO DE VENTAS</h1>
            <%-- Mostramos un mensaje si existe (por ejemplo, venta guardada correctamente) --%>
            <%
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null && !mensaje.isEmpty()) {
            %>
            <div style="padding: 10px; margin: 10px 0; background-color: #d4edda; color: #155724;
           border: 1px solid #c3e6cb;">
            <strong><%= mensaje %></strong>
            </div>
            <%
            }
            %>
            <!-- Botón para crear una nueva venta -->
            <form action="ventacontrolador" method="POST">
                <button name="accion" value="nuevo" class="btn btn-primary">NUEVA VENTA</button>
            </form>
            <%-- Cargamos la lista de ventas desde el modelo --%>
            <%
            ventamodelo v = new ventamodelo(); // Creamos una instancia del modelo
            List<ventamodelo> lista = v.listar(); // Obtenemos la lista de ventas desde la base
            %>
            <!-- Tabla con la lista de ventas -->
            <table border="1"  class="table table-bordered table-striped table-hover">
            <thead >
            <tr>
            <th>ID</th>
            <th>FECHA</th>
            <th>CONDICIÓN</th>
            <th>ESTADO</th>
            <th>CLIENTE</th>
            <th>USUARIO</th>
            <th>ACCIONES</th>
            </tr>
            </thead>
            <tbody>
            <%-- Recorremos la lista y mostramos cada venta --%>
            <% for (ventamodelo i : lista) { %>
            <tr>
            <td><%=i.getId() %></td>
            <td><%= i.getFecha() %></td>
            <td><%= i.getCondicion() %></td>
            <td><%= i.getEstado() %></td>
            <td><%= i.getCliente() %></td>
            <td><%= i.getUsuario() %></td>
            <td>
            <!-- Botón para imprimir una venta -->
            <form action="ventacontrolador" method="POST" target="_blank">
            <input type="hidden" name="txtid" value="<%= i.getId() %>">
            <%  int monto =  v.calcularTotalVenta(i.getId()); %>
            <input type="hidden" name="txtmontototal" value="<%= monto %>">
            <button name="accion" value="imprimir" class="btn btn-secondary">IMPRIMIR</button>
            </form>
            </td>
            </tr>
            <% } %>
            </tbody>
            </table>
 </div>

 <!-- PIE DE PÁGINA CON INFO DEL USUARIO -->

 <div class="div4" style="display: flex; flex-direction: column">
         <label style="color: white"><%= usuario %></label>
            <label style="color: white"><%= tipo %></label>
     </div>

 </body>
</html