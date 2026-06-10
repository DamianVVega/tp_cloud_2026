<%-- 
    Document   : personales
    Created on : 28 jun. 2025, 13:22:33
    Author     : Damian0
--%>

<%@page import="modelos.personalesmodelo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/vistas/complementos/header.jsp" />
<jsp:include page="/vistas/complementos/menu.jsp" />
<jsp:include page="/vistas/modales/AgregarPersonales.jsp" />
<jsp:include page="/vistas/modales/EditarPersonales.jsp" />

<div class="div3" style="overflow: auto">
    <%
            String usuario= (String)session.getAttribute("usuario");
            if(usuario == null){
               response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");

                return;
            }
        %>
    <%
    personalesmodelo per1 = (personalesmodelo) request.getAttribute("mensaje");
    if (per1 != null && per1.getMensaje() != null && !per1.getMensaje().equals("buscar")) {
%>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var mensaje = "<%= per1.getMensaje().replace("\"", "\\\"").replace("\n", "\\n") %>";
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
        <h1>Productos</h1>
    </div>
 
            <div class="container">
                 <h1>Informacion de los Personales</h1>
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
                                personalesmodelo cli= new personalesmodelo();
                                List<personalesmodelo> personales=cli.listar();
                                for(personalesmodelo  i:personales){%>
                                <tr>
                                  
                                    <td><%= i.getNombre() %></td>
                                    <td><%= i.getApellido() %></td>
                                    <td><%= i.getDni() %></td>
                                    <td><%= i.getTelefono() %></td>
                                  
                                    <td>
                                        <button onclick="abrirEditarPersonales('<%= i.getCodigo() %>', '<%= i.getNombre() %>', 
                                                    '<%= i.getApellido() %>', '<%= i.getDni() %>', '<%= i.getTelefono() %>')" 
                                                    class="btn btn-primary">Modificar</button>
                                            </td>
                                        <td> <form action="<%= request.getContextPath() %>/personalescontrolador" method="post">
                                             <input type="hidden" name="txtcodigo" value="<%= i.getCodigo() %>">
                                            <button class="btn btn-danger" value="eliminar" name="accion" >Eliminar</button>
                                            </form></td>
                                    
                                </tr>
                                <% }%>
                          </tbody>
                        </table>
            </div>
</div>
<div class="div4">
    <button class="btn btn-primary" onclick="abrir()">Agregar Nuevo Personal</button>
    <form action="<%= request.getContextPath() %>/personalescontrolador" method="post" target="_blank">
        <button class="btn btn-secondary" value="informe" name="accion">Imprimir</button>
    </form>
</div>
<script src="<%= request.getContextPath() %>/js/scripts.js"></script>

<!--  <script src="<%= request.getContextPath() %>/js/scripts.js"></script>-->