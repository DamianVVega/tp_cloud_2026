<%-- 
    Document   : menu.jsp
    Created on : 27 jun. 2025, 14:03:08
    Author     : Damian0
--%>
<%@page import="modelos.productosmodelo"%>
<%@ page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter, java.time.ZoneOffset" %>
<%
    LocalDateTime ahora = LocalDateTime.now(ZoneOffset.of("-03:00"));
    String fecha = ahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
%>

<form action="<%= request.getContextPath() %>/menucontrolador" method="post">
    <input type="hidden" name="txtcodusu" value="<%= session.getAttribute("codigo") %>">

    <div class="navbar-izq">
        <button class="nav-btn" name="accion" value="producto">
            <i class="bi bi-box-seam"></i> Productos
        </button>
        <button class="nav-btn" name="accion" value="cliente">
            <i class="bi bi-people"></i> Clientes
        </button>
        <button class="nav-btn" name="accion" value="proveedor">
            <i class="bi bi-truck"></i> Proveedores
        </button>
        <button class="nav-btn" name="accion" value="personal">
            <i class="bi bi-person-badge"></i> Personal
        </button>
        <button class="nav-btn" name="accion" value="usuario">
            <i class="bi bi-shield-lock"></i> Usuarios
        </button>
        <div class="nav-divider-v"></div>
        <button class="nav-btn" name="accion" value="venta">
            <i class="bi bi-receipt"></i> Ventas
        </button>
        <button class="nav-btn" name="accion" value="compra">
            <i class="bi bi-cart3"></i> Compras
        </button>
    </div>

    <div class="navbar-der">
        <button class="btn-caja btn-caja-abrir" name="accion" value="abrirC">
            <i class="bi bi-unlock-fill"></i> Abrir caja
        </button>
        <button class="btn-caja btn-caja-cerrar" name="accion" value="cerrarC">
            <i class="bi bi-lock-fill"></i> Cerrar caja
        </button>
    </div>
</form>

<label id="mensaje-alerta">
    <span id="mensaje-texto"></span>
</label>

<script>
    function actualizarHora() {
        const ahora = new Date();
        const opHora = { hour:'2-digit', minute:'2-digit', second:'2-digit', hour12:false, timeZone:'America/Asuncion' };
        const opFecha = { day:'2-digit', month:'2-digit', year:'numeric', timeZone:'America/Asuncion' };
        const elHora = document.getElementById("hora-top");
        const elFecha = document.getElementById("fecha-top");
        if (elHora) elHora.textContent = new Intl.DateTimeFormat('es-PY', opHora).format(ahora);
        if (elFecha) elFecha.textContent = new Intl.DateTimeFormat('es-PY', opFecha).format(ahora);
    }
    actualizarHora();
    setInterval(actualizarHora, 1000);
</script>