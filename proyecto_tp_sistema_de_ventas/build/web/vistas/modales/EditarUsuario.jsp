<%-- 
    Document   : EditarUsuario
    Created on : 27 jun. 2025, 20:52:03
    Author     : Damian0
--%>

<%@page import="java.util.List"%>
<%@page import="modelos.usuariomodelo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
                            usuariomodelo um = new usuariomodelo();
                            List<usuariomodelo> listaPersonales = um.listarPersonales();
                        %>
    <div id="modalEditar" class="modal">
            <div class="modal-content">
                <form action="usuarioscontrolador" method="post">
                         <span id="closeBtn" class="close" onclick="cerrarEditar()">X</span>
                         <h1>Formulario Usuarios</h1>
                         <input type="hidden" name="txtcodigo" id="editCodigo">
                        <label>Nombre</label>
                        <input type="text" name="txtnombre" id="editNombre">
                        <label>Nueva Contraseña</label>
                        <input type="text" name="txtclave" id="editContraseña">
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
                        <button type="submit"  name="accion" value="modificarUsuario">Guardar</button>
                       
            </form>
            </div>  
             </div> 