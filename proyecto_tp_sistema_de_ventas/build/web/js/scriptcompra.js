// ==========================
// FUNCIONES DEL MODAL PROVEEDOR
// ==========================
function abrirModalProveedor() {
    document.getElementById('modalProveedor').style.display = 'block';
}

function cerrarModalProveedor() {
    document.getElementById('modalProveedor').style.display = 'none';
}

function seleccionarProveedor(id, nombreCompleto) {
    document.getElementById('idproveedor').value = id;
    document.getElementById('nombreproveedor').value = nombreCompleto;
    cerrarModalProveedor();
}

// ==========================
// FUNCIONES DEL MODAL PRODUCTO
// ==========================
function abrirModalProducto() {
    document.getElementById('modalProducto').style.display = 'block';
}

function cerrarModalProducto() {
    document.getElementById('modalProducto').style.display = 'none';
}

function seleccionarProducto(cod, nom, costo, iva) {
    document.getElementById('codigoProducto').value = cod;
    document.getElementById('nombreProducto').value = nom;
    document.getElementById('precioProducto').value = costo;
    document.getElementById('ivaProducto').value = iva;
    cerrarModalProducto();
}

// ==========================
// AGREGAR PRODUCTO A LA TABLA DETALLE
// ==========================
function agregarProducto() {
    const tabla = document.getElementById('tablaDetalle').getElementsByTagName('tbody')[0];
    const codigo = document.getElementById('codigoProducto').value;
    const nombre = document.getElementById('nombreProducto').value;
    const costo = parseFloat(document.getElementById('precioProducto').value);
const cantidad = parseFloat(document.getElementById('cantidadProducto').value);

    const iva = document.getElementById('ivaProducto').value;

    if (!codigo || cantidad < 1) {
        alert('Falta seleccionar producto o cantidad inválida');
        return;
    }

    let exenta = 0, iva5 = 0, iva10 = 0;
    let subtotal = costo * cantidad;

    if (iva === 'EXENTA')
        exenta = subtotal;
    else if (iva === '5')
        iva5 = subtotal;
    else if (iva === '10')
        iva10 = subtotal;

    const fila = tabla.insertRow();
    fila.innerHTML = `
        <td style="display:none;" class="idproducto">${codigo}</td>
        <td>${cantidad}</td>
        <td>${nombre}</td>
        <td>${costo}</td>
        <td>${exenta}</td>
        <td>${iva5}</td>
        <td>${iva10}</td>
        <td><button class='eliminar-btn' onclick='eliminarFila(this)'>Eliminar</button></td>
    `;
}

// ==========================
// ELIMINAR UNA FILA DE LA TABLA DETALLE
// ==========================
function eliminarFila(boton) {
    const fila = boton.closest('tr');
    fila.remove();
}

// ==========================
// PREPARAR DATOS PARA ENVIAR AL SERVLET
// ==========================
function prepararCompra() {
    let filas = document.querySelectorAll('#tablaDetalle tbody tr');
    let detalle = [];

    filas.forEach(fila => {
        let columnas = fila.querySelectorAll('td');
        if (columnas.length >= 7) {
            let idproducto = columnas[0].innerText;
            let cantidad = columnas[1].innerText;
            let costo = columnas[3].innerText;
            let exenta = columnas[4].innerText;
            let iva5 = columnas[5].innerText;
            let iva10 = columnas[6].innerText;

            detalle.push({
                idproducto: idproducto,
                cantidad: cantidad,
                costo: costo,
                exenta: exenta,
                iva5: iva5,
                iva10: iva10
            });
        }
    });

    if (detalle.length === 0) {
        alert("Debe agregar al menos un producto.");
        return false;
    }

    document.getElementById('jsonDetalleCompra').value = JSON.stringify(detalle);

    const selectCondicion = document.getElementById('selectCondicion');
    if (selectCondicion) {
        document.getElementById('hiddenCondicion').value = selectCondicion.value;
    }

    const idproveedor = document.getElementById('idproveedor').value;
    document.getElementById('hiddenProveedor').value = idproveedor;

    return true;
}



