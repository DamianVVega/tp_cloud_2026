<%-- 
    Document   : AgregarClientes
    Created on : 28 jun. 2025, 12:42:07
    Author     : Damian0
--%>

<div class="modal" id="modal">
                <div class="modal-content">
                    <span id="closeBtn" class="close" onclick="cerrar()">X</span>
                     <form action="clientescontrolador" method="post">
                         <h1>Formulario Clientes</h1>
                         <label>Nombre</label>
                         <input type="text" name="txtnombre"><br>
                         <label>Apellido</label>
                          <input type="text" name="txtapellido"><br>
                         <label>CI</label>
                          <input type="number" name="txtdni"><br>
                         <label>Telefono</label>
                         <input type="number" name="txttelefono"><br>
                         <button type="submit"  name="accion" value="guardar" class="btn btn-primary">Guardar</button>
                        </form>
                </div>
            </div>
