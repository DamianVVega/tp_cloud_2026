<%-- 
    Document   : AgregarProveedores
    Created on : 28 jun. 2025, 13:03:12
    Author     : Damian0
--%>

<div class="modal" id="modal">
                <div class="modal-content">
                    <span id="closeBtn" class="close" onclick="cerrar()">X</span>
                     <form action="proveedorescontrolador" method="post">
                         <h1>Formulario Personales</h1>
                         <label>Nombre</label>
                         <input type="text" name="txtnombre"><br>
                         <label>Ruc</label>
                          <input type="text" name="txtruc"><br>
                         <label>Telefono</label>
                          <input type="number" name="txttelefono"><br>
                         <label>Correo</label>
                         <input type="text" name="txtcorreo"><br>
                         <button type="submit"  name="accion" value="guardar" class="btn btn-primary">Guardar</button>
                        </form>
                </div>
            </div>