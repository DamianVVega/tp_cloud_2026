<%-- 
    Document   : clientes
    Created on : 27 jun. 2025, 14:10:38
    Author     : Damian0
--%>

<%@page import="modelos.clientesmodelo"%>
<%@page import="modelos.usuariomodelo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/vistas/complementos/header.jsp" />
<jsp:include page="/vistas/complementos/menu.jsp" />
<jsp:include page="/vistas/modales/AgregarClientes.jsp" />
<jsp:include page="/vistas/modales/EditarClientes.jsp" />

<div class="div3" style="overflow: auto">
    <%
            String usuario= (String)session.getAttribute("usuario");
            if(usuario == null){
               response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");

                return;
            }
        %>
    <%
    clientesmodelo c1 = (clientesmodelo) request.getAttribute("mensaje");
    if (c1 != null && c1.getMensaje() != null && !c1.getMensaje().equals("buscar")) {
%>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var mensaje = "<%= c1.getMensaje().replace("\"", "\\\"").replace("\n", "\\n") %>";
            var div = document.getElementById("mensaje-alerta");
            var span = document.getElementById("mensaje-texto");
            span.innerText = mensaje;
            div.style.display = "block";

            setTimeout(function() {
                div.style.display = "none";
            }, 3000); // se oculta luego de 3 segundos
        });
    </script>
<%
    }
%>

    <div class="titulomodulo"> 
        <h1>Clientes</h1>
    </div>
 
            <div class="container">
               <h1>Informacion de los Clientes</h1>
                   <table class="table">
                          <thead>
                            <tr>
                             
                              <th scope="col">Nombre</th>
                              <th scope="col">Apellido</th>
                              <th scope="col">CI</th>
                              <th scope="col">Telefono</th>
                              <th scope="col"></th>
                              <th scope="col"></th>
                              
                            </tr>
                          </thead>
                          <tbody>
                            <%
                                //Codigo para mostrar los productos en la vista
                                clientesmodelo cli= new clientesmodelo();
                                List<clientesmodelo> clientes=cli.listar();
                                for(clientesmodelo  i:clientes){%>
                                <tr>
                                  
                                    <td><%= i.getNombre() %></td>
                                    <td><%= i.getApellido() %></td>
                                    <td><%= i.getDni() %></td>
                                    <td><%= i.getTelefono() %></td>
                                  
                                    <td>
                                        <button onclick="abrirEditarClientes('<%= i.getCodigo() %>', '<%= i.getNombre() %>', 
                                                    '<%= i.getApellido() %>', '<%= i.getDni() %>', '<%= i.getTelefono() %>')" 
                                                    class="btn btn-primary">Modificar</button>
                                            </td>
                                        <td> <form action="<%= request.getContextPath() %>/clientescontrolador" method="post">
                                             <input type="hidden" name="txtcodigo" value="<%= i.getCodigo() %>">
                                            <button class="btn btn-danger" value="eliminar" name="accion">Eliminar</button>
                                            </form></td>
                                    
                                </tr>
                                <% }%>
                          </tbody>
                        </table>
            </div>
</div>
<div class="div4">
    <button class="btn btn-primary" onclick="abrir()">Agregar Nuevo Cliente</button>
    <form action="<%= request.getContextPath() %>/clientescontrolador" method="post" target="_blank">
        <button class="btn btn-secondary" value="informe" name="accion">Imprimir</button>
    </form>
    
</div>
<script src="<%= request.getContextPath() %>/js/scripts.js"></script>

<!--  <script src="<%= request.getContextPath() %>/js/scripts.js"></script>-->