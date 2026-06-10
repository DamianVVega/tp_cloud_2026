<%-- 
    Document   : menu
    Created on : 27 jun. 2025, 14:03:08
    Author     : Damian0
--%>

<%@page import="modelos.productosmodelo"%>
<div class="div2">
     <%@ page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter, java.time.ZoneOffset" %>
    <%
    LocalDateTime ahora = LocalDateTime.now(ZoneOffset.of("-03:00"));
    String fecha = ahora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String hora = ahora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
%>

        <form action="<%= request.getContextPath() %>/menucontrolador" method="post" class="menu">
           
                <button name="accion" value="producto">Productos</button>
                <button name="accion" value="cliente">Clientes</button>
                <button name="accion" value="proveedor">Proveedores</button>
                <button name="accion" value="personal">Personales</button>
                <button name="accion" value="usuario">Usuarios</button>
                <button name="accion" value="abrirC">Abrir Caja</button>
                <button name="accion" value="cerrarC">Cerrar Caja</button>
                <button name="accion" value="venta">Ventas</button>
                <button name="accion" value="compra">Compras</button>
                 <div class="fecha-hora">
                       <span id="fecha"><%= fecha %></span> |
                       <span id="hora-texto">00:00:00</span>
               </div>
                 <input type="text" name="txtcodusu" value="<%= session.getAttribute("codigo") %>" hidden>
        </form>
        <label id="mensaje-alerta" 
       style="display:none;
              background-color:#4CAF50; 
              color:white; 
              border-radius:5px; 
              z-index:9999; 
              max-width: 90%; 
              word-wrap: break-word; 
              white-space: pre-wrap;
              font-size: 16px;
              margin: 20px auto;
              text-align: center;
              box-shadow: 0 4px 6px rgba(0,0,0,0.2);">
    <span id="mensaje-texto"></span>
</label>



                 
               
                 
<style>
    .fecha-hora {
       
        background-color: rgba(255, 255, 255, 0.9);
        font-weight: bold;
        color: #333;
        font-family: monospace;
        white-space: nowrap;
    }
</style>

<script>
    function actualizarHora() {
        const ahora = new Date();
        const opciones = {
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit',
            hour12: false,
            timeZone: 'America/Asuncion'
        };

        const horaFormateada = new Intl.DateTimeFormat('es-PY', opciones).format(ahora);
        document.getElementById("hora-texto").textContent = horaFormateada;
    }

    actualizarHora(); // Mostrar inmediatamente
    setInterval(actualizarHora, 1000); // Actualizar cada segundo
</script>
  </div>