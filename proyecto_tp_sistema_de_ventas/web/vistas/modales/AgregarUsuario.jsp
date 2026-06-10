<%-- 
    Document   : AgregarUsuario
    Created on : 27 jun. 2025, 16:25:50
    Author     : Damian0
--%>

<%@page import="java.util.List"%>
<%@page import="modelos.usuariomodelo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal" id="modal">
                <div class="modal-content">
                     <form action="usuarioscontrolador" method="post">
                         <span id="closeBtn" class="close" onclick="cerrar()">X</span>
                         <h1>Formulario Usuarios</h1>
                            <%
                            usuariomodelo um = new usuariomodelo();
                            List<usuariomodelo> listaPersonales = um.listarPersonales();
                        %>
                        <label>Nombre</label>
                        <input type="text" name="txtnombre">
                        <label>Contraseña</label>
                        <input type="text" name="txtclave">
                        <label>Tipo</label>
                        <select name="txttipo" id="rol">
                            <option value="administrador">Administrador</option>
                            <option value="normal">Normal</option>
                        </select>
                        <label for="idpersonal">Seleccionar personal:</label>
                                <select name="txtpersonal" id="idpersonal">
                                    <%
                                        for (usuariomodelo per : listaPersonales) {
                                    %>
                                        <option value="<%= per.getCodigo() %>"><%= per.getPersonal() %></option>
                                    <%
                                        }
                                    %>
                                </select>
                        <button type="submit"  name="accion" value="guardar">Guardar</button>
                       
            </form>
                </div>
          
            </div>
