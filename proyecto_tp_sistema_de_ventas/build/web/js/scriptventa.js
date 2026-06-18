// ==========================
// FUNCIONES DEL MODAL CLIENTE
// ==========================
// Abre el modal del cliente, mostrando el formulario con la lista de clientes
function abrirModal() {
 document.getElementById('modalCliente').style.display = 'block';
}
// Cierra el modal de clientes
function cerrarModal() {
 document.getElementById('modalCliente').style.display = 'none';
}
// Cuando el usuario hace clic en "Seleccionar" en el modal, esta función carga los datos elegidos
function seleccionarCliente(id, nombreCompleto) {
 // Se guarda el ID en un campo oculto
 document.getElementById('idcliente').value = id;
 // Se muestra el nombre del cliente en el input visible
 document.getElementById('nombrecliente').value = nombreCompleto;
 // Cerramos el modal
 cerrarModal();
}

// ==========================
// FUNCIONES DEL MODAL PRODUCTO
// ==========================
// Abre el modal de productos
function abrirModalProducto() {
 document.getElementById('modalProducto').style.display = 'block';
}
// Cierra el modal de productos
function cerrarModalProducto() {
 document.getElementById('modalProducto').style.display = 'none';
}
// Carga los datos del producto seleccionado al formulario
function seleccionarProducto(cod, nom, precio, iva) {
 document.getElementById('codigoProducto').value = cod;
 document.getElementById('nombreProducto').value = nom;
 document.getElementById('precioProducto').value = precio;
 document.getElementById('ivaProducto').value = iva;
 // Cerramos el modal de producto
 cerrarModalProducto();
}
// ==========================
// AGREGAR PRODUCTO A LA TABLA DETALLE
// ==========================

function agregarProducto() {
    const tabla = document.getElementById('tablaDetalle').getElementsByTagName('tbody')[0];

    const codigo = document.getElementById('codigoProducto').value;
    const nombre = document.getElementById('nombreProducto').value;
    const precio = parseInt(document.getElementById('precioProducto').value);
    const cantidad = parseInt(document.getElementById('cantidadProducto').value);
    const iva = document.getElementById('ivaProducto').value;

    if (!codigo || cantidad < 1) {
        alert('Falta seleccionar producto o cantidad inválida');
        return;
    }

    const subtotal = precio * cantidad;

    let exenta = 0, iva5 = 0, iva10 = 0;

    if (iva === 'EXENTA') {
        exenta = subtotal;
    } else if (iva === '5') {
        iva5 = Math.floor(subtotal / 21);
    } else if (iva === '10') {
        iva10 = Math.floor(subtotal / 11);
    }

    // Eliminar fila total si ya existe
    const filas = tabla.querySelectorAll('tr.fila-total');
    filas.forEach(fila => fila.remove());

    // Agregar nueva fila del producto
    const fila = tabla.insertRow();
    fila.innerHTML = `
        <td style="display:none;" class="idproducto">${codigo}</td>
        <td>${cantidad}</td>
        <td>${nombre}</td>
        <td>${precio}</td>
        <td>${iva === 'EXENTA' ? subtotal : 0}</td>
        <td>${iva === '5' ? iva5 : 0}</td>
        <td>${iva === '10' ? iva10 : 0}</td>
        <td>${subtotal}</td>
        <td><button class='eliminar-btn' onclick='eliminarFila(this)'>Eliminar</button></td>
    `;

    // Recalcular total general
    let totalGeneral = 0;
    for (let i = 0; i < tabla.rows.length; i++) {
        const celdaSubtotal = tabla.rows[i].cells[7];
        if (celdaSubtotal) {
            totalGeneral += parseInt(celdaSubtotal.innerText) || 0;
        }
    }

    // Insertar fila total al final
    const filaTotal = tabla.insertRow();
    filaTotal.classList.add('fila-total');
    filaTotal.innerHTML = `
        <td colspan="7" style="text-align:right;"><strong>Total:</strong></td>
        <td><strong>${totalGeneral}</strong></td>
        <td></td>
    `;
}


// ==========================
// ELIMINAR UNA FILA DE LA TABLA DETALLE
// ==========================
function eliminarFila(boton) {
    // Obtener la fila del botón y eliminarla
    const fila = boton.closest('tr');
    const tabla = fila.parentNode; // tbody

    fila.remove();

    // Eliminar cualquier fila de total existente
    const filasTotales = tabla.querySelectorAll('tr.fila-total');
    filasTotales.forEach(f => f.remove());

    // Recalcular el total general
    let totalGeneral = 0;
    for (let i = 0; i < tabla.rows.length; i++) {
        const celdaSubtotal = tabla.rows[i].cells[7]; // columna de subtotal
        if (celdaSubtotal) {
            totalGeneral += parseInt(celdaSubtotal.innerText) || 0;
        }
    }

    // Si aún quedan productos, agregar nueva fila de total
    if (tabla.rows.length > 0) {
        const filaTotal = tabla.insertRow();
        filaTotal.classList.add('fila-total');
        filaTotal.innerHTML = `
            <td colspan="7" style="text-align:right;"><strong>Total:</strong></td>
            <td><strong>${totalGeneral}</strong></td>
            <td></td>
        `;
    }
}

// ==========================
// PREPARAR DATOS PARA ENVIAR AL SERVLET
// ==========================

function prepararVenta() {
 // Obtenemos todas las filas del detalle de productos
 let filas = document.querySelectorAll('#tablaDetalle tbody tr');
 let detalle = []; // Lista que almacenará cada producto como objeto JSON
 // Recorremos cada fila y extraemos sus datos
 filas.forEach(fila => {
 let columnas = fila.querySelectorAll('td');
 // Verificamos que la fila tenga las columnas esperadas
 if (columnas.length >= 7) {
 let idproducto = columnas[0].innerText; // oculto
 let cantidad = columnas[1].innerText;
 let precio = columnas[3].innerText;
 let exenta = columnas[4].innerText;
 let iva5 = columnas[5].innerText;
 let iva10 = columnas[6].innerText;
 // Agregamos el producto al arreglo "detalle" como objeto
 detalle.push({
 idproducto: idproducto,
 cantidad: cantidad,
 precio: precio,
 exenta: exenta,
 iva5: iva5,
 iva10: iva10
 });
 }

 });
 // Si no se agregó ningún producto, mostramos alerta y cancelamos el envío
 if (detalle.length === 0) {
 alert("Debe agregar al menos un producto.");
 return false;
 }
 // Convertimos el array de objetos en una cadena JSON y lo guardamos en un input oculto
 document.getElementById('jsonDetalleVenta').value = JSON.stringify(detalle);
 // También pasamos la condición de pago del select visible al input oculto
 const selectCondicion = document.getElementById('selectCondicion');
 if (selectCondicion) {
 document.getElementById('hiddenCondicion').value = selectCondicion.value;
 }
 // Copiamos también el ID del cliente seleccionado al input oculto
 const idcliente = document.getElementById('idcliente').value;
 document.getElementById('hiddenCliente').value = idcliente;
 // Si todo está bien, se puede enviar el formulario
 return true;
}

