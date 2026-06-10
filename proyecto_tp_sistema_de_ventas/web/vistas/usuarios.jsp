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
<jsp:include page="/vistas/modales/AgregarUsuario.jsp" />
<jsp:include page="/vistas/modales/EditarUsuario.jsp" />

<div class="div3" style="overflow: auto">
    <%
            String usuario= (String)session.getAttribute("usuario");
            if(usuario == null){
               response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");

                return;
            }
    %>
    <%
    usuariomodelo ul = (usuariomodelo) request.getAttribute("mensaje");
    if (ul != null && ul.getMensaje() != null && !ul.getMensaje().equals("buscar")) {
    %>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var mensaje = "<%= ul.getMensaje().replace("\"", "\\\"").replace("\n", "\\n") %>";
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
        <h1>Usuarios</h1>
    </div>
 
            <div class="container">
                <div class="cabecera-usuario">
                      <h1>Informacion de los Usuarios</h1>
                      <form action="usuarioscontrolador" method="post" target="_blank">
                          <button  type="submit" name="accion" value="informe">Imprimir</button>
                      </form>
                <label> <h3>Filtro por Nombre:</h3> </label> 
                <form action="usuarioscontrolador" method="post">
                    <input  style="width: 300px;margin-bottom: 10px" name="txtbuscar" type="text" placeholder="Ingrese un nombre de usuario">
                    <button type="submit" name="accion" value="buscar">Buscar</button>
                </form>
                
                </div>
               
                   <table class="table">
                          <thead>
                            <tr>
                              <th scope="col">Codigo</th>
                              <th scope="col">Nombre</th>
                              <th scope="col">Contraseña</th>
                              <th scope="col">Tipo</th>
                              <th scope="col">Estado</th>
                              <th scope="col">Personal Relacionado</th>
                              <th scope="col"></th>
                              <th scope="col"></th>
                            </tr>
                          </thead>
                          <tbody>
                            <%
                                //Codigo para mostrar los productos en la vista
                               List<usuariomodelo> usuarios = (List<usuariomodelo>) request.getAttribute("listaUsuarios");
                                if (usuarios == null) {
                                    usuariomodelo usu = new usuariomodelo();
                                    usuarios = usu.listar();
                                }

                                for(usuariomodelo  i:usuarios){%>
                                <tr>
                                    
                                    <td ><%= i.getCodigo() %></td>
                                    <td><%= i.getUsuario()%></td>
                                    <td><%= i.getClave() %></td>
                                    <td><%= i.getTipo() %></td>
                                    <td><%= i.getEstado() %></td>
                                    <td><%= i.getPersonal() %></td>
                                    <td><button onclick="abrirEditarUsuarios('<%= i.getCodigo() %>', '<%= i.getUsuario()%>', 
                                                    '<%= i.getClave()%>', '<%= i.getTipo()%>', '<%= i.getPersonal()%>')" 
                                                    class="btn btn-primary">Modificar</button></td>
                                        <td> <form action="<%= request.getContextPath() %>/usuarioscontrolador" method="post">
                                             <input type="hidden" name="txtcodigo" value="<%= i.getCodigo() %>">
                                            <button class="btn btn-danger" value="eliminar" name="accion">Eliminar</button>
                                            
                                            </form>
                                        </td>
                                </tr>
                                <% }%>
                                
                          </tbody>
                        </table>
            </div>
</div>
<div class="div4">
    <button class="btn btn-primary" onclick="abrir()">Agregar Nuevo Usuario</button>
    
</div>
<script src="<%= request.getContextPath() %>/js/scripts.js"></script>

<!--  <script src="<%= request.getContextPath() %>/js/scripts.js"></script>-->