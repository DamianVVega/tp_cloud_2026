/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function abrir(){
document.getElementById("modal").style.display="block";    
    
}
function cerrar(){
document.getElementById("modal").style.display="none";    
    
}
function abrirEditarProductos(codigo='', nombre='', precio='', cantidad='', iva='', costo='') {
 document.getElementById("editCodigo").value = codigo;
 document.getElementById("editNombre").value = nombre;
 document.getElementById("editPrecio").value = precio;
 document.getElementById("editCantidad").value = cantidad;
 document.getElementById("editIva").value = iva;
 document.getElementById("editCosto").value = costo;
 document.getElementById("modalEditar").style.display = "block";
}
function abrirEditarClientes(codigo='', nombre='', apellido='', ci='', telefono='') {
 document.getElementById("editCodigo").value = codigo;
 document.getElementById("editNombre").value = nombre;
 document.getElementById("editApellido").value = apellido;
 document.getElementById("editDni").value = ci;
 document.getElementById("editTelefono").value = telefono;
 document.getElementById("modalEditar").style.display = "block";
}
function abrirEditarPersonales(codigo='', nombre='', apellido='', ci='', telefono='') {
 document.getElementById("editCodigo").value = codigo;
 document.getElementById("editNombre").value = nombre;
 document.getElementById("editApellido").value = apellido;
 document.getElementById("editDni").value = ci;
 document.getElementById("editTelefono").value = telefono;
 document.getElementById("modalEditar").style.display = "block";
}
function abrirEditarProveedores(codigo='', nombre='', ruc='', telefono='', correo='') {
 document.getElementById("editCodigo").value = codigo;
 document.getElementById("editNombre").value = nombre;
 document.getElementById("editRuc").value = ruc;
 document.getElementById("editTelefono").value = telefono;
 document.getElementById("editCorreo").value = correo;
 
 document.getElementById("modalEditar").style.display = "block";
}
function abrirEditarUsuarios(codigo = '', nombre = '', contraseña = '', tipo = '', idpersonal = '') {
    document.getElementById("editCodigo").value = codigo;
    document.getElementById("editNombre").value = nombre;
    document.getElementById("editContraseña").value = contraseña;

    // Seleccionar el tipo de usuario
    document.getElementById("rol").value = tipo;

    // Seleccionar el personal por su ID
    document.getElementById("idpersonal").value = idpersonal;

    // Mostrar el modal
    document.getElementById("modalEditar").style.display = "block";
}
function abrirEditarUsuarios(codigo = '', nombre = '',tipo = '', idpersonal = '') {
    document.getElementById("editCodigo").value = codigo;
    document.getElementById("editNombre").value = nombre;
    

    // Seleccionar tipo y personal
    document.getElementById("rol").value = tipo;
    document.getElementById("idpersonal").value = idpersonal;

    document.getElementById("modalEditar").style.display = "block";
}


function cerrarEditar(){
document.getElementById("modalEditar").style.display="none";    
    
}





