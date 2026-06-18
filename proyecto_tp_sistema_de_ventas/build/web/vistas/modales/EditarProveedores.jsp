<%-- 
    Document   : EditarProveedores
    Created on : 28 jun. 2025, 13:03:19
    Author     : Damian0
--%>

<div id="modalEditar" class="modal">
            <div class="modal-content">
                <form action="proveedorescontrolador" method="POST">
                        <span class="close" onclick="cerrarEditar()">&times;</span>
                        <input type="hidden" name="txtcodigo" id="editCodigo">
                        <label>NOMBRE</label>
                        <input type="text" name="txtnombre" id="editNombre"><br>
                        <label>RUC</label>
                        <input type="text" name="txtruc" id="editRuc"><br>
                        <label>TELEFONO</label>
                        <input type="number" name="txttelefono"
                       id="editTelefono"><br>
                        <label>CORREO</label>
                        <input type="text" name="txtcorreo" id="editCorreo"><br>
                        <button name="accion"
                                value="actualizar" class="btn btn-primary">ACTUALIZAR</button>
                </form>
            </div>  
             </div>     