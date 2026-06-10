<%-- 
    Document   : EditarProductos
    Created on : 28 jun. 2025, 12:22:57
    Author     : Damian0
--%>
  <div id="modalEditar" class="modal">
            <div class="modal-content">
                <form action="productoscontrolador" method="POST">
                        <span class="close" onclick="cerrarEditar()">&times;</span>
                        <input type="hidden" name="txtcodigo" id="editCodigo">
                        <label>NOMBRE</label>
                        <input type="text" name="txtnombre" id="editNombre"><br>
                        <label>PRECIO</label>
                        <input type="number" name="txtprecio" id="editPrecio"><br>
                        <label>CANTIDAD</label>
                        <input type="number" name="txtcantidad"
                       id="editCantidad"><br>
                        <label>IVA</label>
                        <input type="text" name="txtiva" id="editIva"><br>
                        <label>COSTO</label>
                        <input type="number" name="txtcosto" id="editCosto"><br>
                        <button name="accion"
                       value="actualizar">ACTUALIZAR</button>
                </form>
            </div>  
             </div> 
