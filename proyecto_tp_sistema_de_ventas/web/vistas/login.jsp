<%-- 
    Document   : login
    Created on : 26 jun. 2025, 16:54:42
    Author     : Damian0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="es">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css">
    <!-- FontAwesome CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/all.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/uf-style.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:ital,wght@0,100..700;1,100..700&display=swap" rel="stylesheet">
    <title>Inicio de Sesion</title>
  </head>
  <body>
    <div class="uf-form-signin">
      <div class="text-center">
        <h2 class="titulo-empresa">Supermecado Baratito</h2>
        <a href="https://uifresh.net/"><img src="<%= request.getContextPath() %>/img/icono_supermercado.png" alt="" width="100" height="100"></a>
      <h1 class="text-white h3">Inicio de Sesion</h1>
      </div>
      <form class="mt-4" method="post" action="<%= request.getContextPath() %>/logincontrolador">
        <div class="input-group uf-input-group input-group-lg mb-3">
          <span class="input-group-text fa fa-user"></span>
          <input type="text" class="form-control" name="txtusuario" placeholder="Nombre de Usuario">
        </div>
        <div class="input-group uf-input-group input-group-lg mb-3">
          <span class="input-group-text fa fa-lock"></span>
          <input type="password" class="form-control" name="txtclave" placeholder="Contraseña">
        </div>
        
        <div class="d-grid mb-4">
            <button type="submit" name="accion" value="btniniciar" class="btn uf-btn-primary btn-lg">Iniciar</button>
        </div>
        
        
        
      </form>
         <%
    String mensaje = (String) request.getAttribute("mensaje");
    Integer contador = (Integer) session.getAttribute("contador");

    if (contador == null) {
        contador = 0;
    }

    if (mensaje != null) {
        contador++;
        session.setAttribute("contador", contador);

        if (contador >= 3) {
%>
            <style>
                .modal {
                    position: fixed;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                    background-color: rgba(0, 0, 0, 0.5);
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    z-index: 1000;
                }
                .modal-content {
                   background: #000046;  /* fallback for old browsers */
                    background: -webkit-linear-gradient(to right, #1CB5E0, #000046);  /* Chrome 10-25, Safari 5.1-6 */
                    background: linear-gradient(to right, #1CB5E0, #000046); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

                    padding: 30px;
                    border-radius: 10px;
                    text-align: center;
                    font-family: Arial, sans-serif;
                    font-weight: bolder;
                    color: white;
                    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
                }
            </style>

            <div class="modal">
                <div class="modal-content">
                    <h2>Demasiados intentos</h2>
                    <p>Espere <span id="contador">10</span> segundos antes de volver a intentar.</p>
                </div>
            </div>

            <script>
                let segundos = 5;
                const span = document.getElementById("contador");
                const intervalo = setInterval(() => {
                    segundos--;
                    span.textContent = segundos;
                    if (segundos <= 0) {
                        clearInterval(intervalo);
                        window.location.href = "<%= request.getContextPath() %>/vistas/login.jsp";
                    }
                }, 1000);
            </script>
<%
            // Reiniciar el contador después del bloqueo, si desea
            session.setAttribute("contador", 0);
        } else {
            out.print("<p style='color:black;'>Datos incorrectos. Intento " + contador + " de 3.</p>");
        }
    }
%>
    </div>

    <!-- JavaScript -->

    <!-- Separate Popper and Bootstrap JS -->
    <script src="./assets/js/popper.min.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
  </body>
</html>
