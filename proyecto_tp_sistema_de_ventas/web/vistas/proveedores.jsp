<%-- 
    Document   : proveedores
    Created on : 27 jun. 2025, 14:09:39
    Author     : Damian0
--%>



<%@page import="modelos.proveedoresmodelo"%>
<%@page import="modelos.clientesmodelo"%>
<%@page import="modelos.usuariomodelo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/vistas/complementos/header.jsp" />
<jsp:include page="/vistas/complementos/menu.jsp" />
<jsp:include page="/vistas/modales/AgregarProveedores.jsp" />
<jsp:include page="/vistas/modales/EditarProveedores.jsp" />

<div class="div3" style="overflow: auto">
    <%
            String usuario= (String)session.getAttribute("usuario");
            if(usuario == null){
               response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");

                return;
            }
        %>
    <%
    proveedoresmodelo p1 = (proveedoresmodelo) request.getAttribute("mensaje");
    if (p1 != null && p1.getMensaje() != null && !p1.getMensaje().equals("buscar")) {
%>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var mensaje = "<%= p1.getMensaje().replace("\"", "\\\"").replace("\n", "\\n") %>";
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
        <h1>Proveedores</h1>
    </div>
 
            <div class="container">
               <h1>Informacion de los Proveedores</h1>
                   <table class="table">
                          <thead>
                            <tr>
                             
                              <th scope="col">Nombre</th>
                              <th scope="col">Ruc</th>
                              <th scope="col">Telefono</th>
                              <th scope="col">Correo</th>
                              <th scope="col"></th>
                              <th scope="col"></th>
                              
                            </tr>
                          </thead>
                          <tbody>
                            <%
                                //Codigo para mostrar los productos en la vista
                                proveedoresmodelo cli= new proveedoresmodelo();
                                List<proveedoresmodelo> proveedores=cli.listar();
                                for(proveedoresmodelo  i:proveedores){%>
                                <tr>
                                  
                                    <td><%= i.getNombre() %></td>
                                    <td><%= i.getRuc() %></td>
                                    <td><%= i.getTelefono() %></td>
                                    <td><%= i.getCorreo() %></td>
                                  
                                    <td>
                                        <button onclick="abrirEditarProveedores('<%= i.getCodigo() %>', '<%= i.getNombre() %>', 
                                                    '<%= i.getRuc() %>', '<%= i.getTelefono() %>', '<%= i.getCorreo() %>')" 
                                                    class="btn btn-primary">Modificar</button>
                                            </td>
                                        <td> <form action="<%= request.getContextPath() %>/proveedorescontrolador" method="post">
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
    <button class="btn btn-primary" onclick="abrir()">Agregar Nuevo Proveedor</button>
    <form action="<%= request.getContextPath() %>/proveedorescontrolador" method="post" target="_blank">
        <button class="btn btn-secondary" value="informe" name="accion">Imprimir</button>
    </form>
</div>
<script src="<%= request.getContextPath() %>/js/scripts.js"></script>

<!--  <script src="<%= request.getContextPath() %>/js/scripts.js"></script>-->