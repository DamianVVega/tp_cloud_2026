<%-- 
    Document   : EditarClientes
    Created on : 28 jun. 2025, 12:42:16
    Author     : Damian0
--%>
 <div id="modalEditar" class="modal">
            <div class="modal-content">
                <form action="clientescontrolador" method="POST">
                        <span class="close" onclick="cerrarEditar()">&times;</span>
                        <input type="hidden" name="txtcodigo" id="editCodigo">
                        <label>NOMBRE</label>
                        <input type="text" name="txtnombre" id="editNombre"><br>
                        <label>APELLIDO</label>
                        <input type="text" name="txtapellido" id="editApellido"><br>
                        <label>DNI</label>
                        <input type="number" name="txtdni"
                       id="editDni"><br>
                        <label>Telefono</label>
                        <input type="text" name="txttelefono" id="editTelefono"><br>
                        <button name="accion"
                                value="actualizar" class="btn btn-primary">ACTUALIZAR</button>
                </form>
            </div>  
             </div> 