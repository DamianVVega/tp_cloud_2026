<%-- 
    Document   : AgregarProductos
    Created on : 28 jun. 2025, 12:22:43
    Author     : Damian0
--%>
  <div class="modal" id="modal">
                <div class="modal-content">
                     <form action="productoscontrolador" method="post">
                         <span id="closeBtn" class="close" onclick="cerrar()">X</span>
                         <h1>Formulario Productos</h1>
                        <label>Nombre</label>
                        <input type="text" name="txtnombre">
                        <label>Precio</label>
                        <input type="number" name="txtprecio">
                        <label>Cantidad</label>
                        <input type="number" name="txtcantidad">
                        <label>Costo</label>
                        <input type="number" name="txtcosto">
                        <label>Iva</label>
                        <select name="txtiva" required>
                            <option value="">Seleccione IVA</option>
                            <option value="5">5%</option>
                            <option value="10">10%</option>
                            <option value="EXENTA">Exenta</option>
                        </select>

                        <button type="submit"  name="accion" value="guardar">Guardar</button>
                       
            </form>
                </div>
          
            </div>
