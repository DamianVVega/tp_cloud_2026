
<%@page import="modelos.productosmodelo"%>
<%-- 
    Document   : usuarios
    Created on : 27 jun. 2025, 14:10:38
    Author     : Damian0
--%>

<%@page import="modelos.usuariomodelo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="/vistas/complementos/header.jsp" />
<jsp:include page="/vistas/complementos/menu.jsp" />
<jsp:include page="/vistas/modales/AgregarProductos.jsp" />
<jsp:include page="/vistas/modales/EditarProductos.jsp" />

<div class="div3" style="overflow: auto">
    <%
            String usuario= (String)session.getAttribute("usuario");
            if(usuario == null){
               response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");

                return;
            }
        %>
    <%
    productosmodelo pro1 = (productosmodelo) request.getAttribute("mensaje");
    if (pro1 != null && pro1.getMensaje() != null && !pro1.getMensaje().equals("buscar")) {
%>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var mensaje = "<%= pro1.getMensaje().replace("\"", "\\\"").replace("\n", "\\n") %>";
            var div = document.getElementById("mensaje-alerta");
            var span = document.getElementById("mensaje-texto");
            span.innerText = mensaje;
            div.style.display = "block";

            setTimeout(function() {
                div.style.display = "none";
            }, 3000);
        });
    </script>
<%
    }
%>


    <div class="titulomodulo"> 
        <h1>Productos</h1>
    </div>
 
            <div class="container">
                 <h1>Informacion de los Productos</h1>
                   <table class="table">
                          <thead>
                            <tr>
                              <th scope="col">Codigo</th>
                              <th scope="col">Nombre</th>
                              <th scope="col">Precio</th>
                              <th scope="col">Cantidad</th>
                              <th scope="col">Iva</th>
                              <th scope="col">Costo</th>
                              <th scope="col"></th>
                              <th scope="col"></th>
                              <th scope="col"></th>
                            </tr>
                          </thead>
                          <tbody>
                             
                            <%
                                //Codigo para mostrar los productos en la vista
                                productosmodelo pro= new productosmodelo();
                                List<productosmodelo> productos=pro.listar();
                                for(productosmodelo  i:productos){%>
                                <tr>
                                    
                                    <td ><%= i.getCodigo() %></td>
                                    <td><%= i.getNombre() %></td>
                                    <td><%= i.getPrecio() %></td>
                                    <td><%= i.getCantidad() %></td>
                                    <td><%= i.getIva() %></td>
                                    <td><%= i.getCosto() %></td>
                                    <td>
                                        <button onclick="abrirEditarProductos('<%= i.getCodigo() %>',
                                        '<%= i.getNombre() %>', '<%= i.getPrecio() %>', '<%= i.getCantidad()
                                        %>', '<%= i.getIva() %>', '<%= i.getCosto() %>')" class="btn btn-primary">Modificar
                                        </button></td>
                                    <td> <form action="<%= request.getContextPath() %>/productoscontrolador" method="post">
                                            <input type="hidden" name="txtcodigo" value="<%= i.getCodigo() %>">
                                            <button class="btn btn-danger" value="eliminar" name="accion">Eliminar</button>
                                         </form>
                                    </td>
                                    <td>
                                        
                                    </td>
                                    
                                </tr>
                                <% }%>
                                
                          </tbody>
                        </table>
            </div>
</div>
<div class="div4">
    <button class="btn btn-primary" onclick="abrir()">Agregar Nuevo Producto</button>
    
    <form action="<%= request.getContextPath() %>/productoscontrolador" method="post" target="_blank">
        <button class="btn btn-secondary" value="informe" name="accion">Imprimir</button>
    </form>
    
</div>
<script src="<%= request.getContextPath() %>/js/scripts.js"></script>

<!--  <script src="<%= request.getContextPath() %>/js/scripts.js"></script>-->