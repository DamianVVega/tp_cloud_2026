<%-- 
    Document   : login
    Created on : 26 jun. 2025, 16:54:42
    Author     : Damian0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="es">
  <head>
    <!-- Meta tags requeridos para responsividad y encoding -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- Bootstrap CSS: framework de estilos y componentes -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css">
    
    <!-- FontAwesome: iconos para los inputs (usuario, candado) -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/all.min.css">
    
    <!-- Estilos personalizados del proyecto -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/uf-style.css">
    
    <!-- Fuente Roboto Mono desde Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:ital,wght@0,100..700;1,100..700&display=swap" rel="stylesheet">
    
    <title>Inicio de Sesion</title>

    <style>
        /* === BODY === */
        body {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(135deg, #000046, #1CB5E0);
            font-family: 'Roboto Mono', monospace;
        }

        /* === CONTENEDOR DEL FORMULARIO === */
        .uf-form-signin {
            background: rgba(255, 255, 255, 0.05);
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.15);
            border-radius: 20px;
            padding: 40px 35px;
            width: 100%;
            max-width: 420px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.4);
        }

        /* === TÍTULO === */
        .titulo-empresa {
            color: #ffffff;
            font-size: 1.4rem;
            font-weight: 700;
            letter-spacing: 1px;
            margin-bottom: 8px;
            text-shadow: 0 2px 8px rgba(0,0,0,0.4);
        }

        /* === INPUTS === */
        .uf-input-group .form-control {
            background: rgba(255, 255, 255, 0.08);
            border: 1px solid rgba(255, 255, 255, 0.2);
            border-left: none;
            color: #ffffff;
            border-radius: 0 10px 10px 0;
            transition: border 0.3s ease, box-shadow 0.3s ease;
        }

        .uf-input-group .form-control::placeholder {
            color: rgba(255, 255, 255, 0.45);
        }

        .uf-input-group .form-control:focus {
            background: rgba(255, 255, 255, 0.12);
            border-color: #1CB5E0;
            box-shadow: 0 0 10px rgba(28, 181, 224, 0.4);
            color: #ffffff;
            outline: none;
        }

        /* === ICONO DEL INPUT === */
        .uf-input-group .input-group-text {
            background: rgba(255, 255, 255, 0.08);
            border: 1px solid rgba(255, 255, 255, 0.2);
            border-right: none;
            color: #1CB5E0;
            border-radius: 10px 0 0 10px;
        }

        /* === BOTÓN INICIAR === */
        .uf-btn-primary {
            background: linear-gradient(to right, #1CB5E0, #000046);
            border: none;
            border-radius: 10px;
            color: #ffffff;
            font-weight: 600;
            letter-spacing: 1px;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }

        .uf-btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(28, 181, 224, 0.5);
            color: #ffffff;
        }

        .uf-btn-primary:active {
            transform: translateY(0);
        }

        /* === IMAGEN === */
        .uf-form-signin img {
            filter: drop-shadow(0 4px 10px rgba(28, 181, 224, 0.4));
            margin: 10px 0;
        }

        /* === MODAL DE BLOQUEO === */
        .modal-bloqueo {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.6);
            display: flex;
            justify-content: center;
            align-items: center;
            z-index: 1000;
        }

        .modal-bloqueo-content {
            background: linear-gradient(to right, #1CB5E0, #000046);
            padding: 40px 30px;
            border-radius: 16px;
            text-align: center;
            font-family: 'Roboto Mono', monospace;
            font-weight: bold;
            color: white;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.5);
            max-width: 340px;
            width: 90%;
        }

        .modal-bloqueo-content h2 {
            font-size: 1.4rem;
            margin-bottom: 12px;
        }

        /* === MENSAJE DE ERROR === */
        .mensaje-error {
            color: #ff6b6b;
            font-size: 0.85rem;
            text-align: center;
            margin-top: 8px;
        }
    </style>
  </head>

  <body>
    <!-- Contenedor principal del formulario de login -->
    <div class="uf-form-signin">

      <!-- Encabezado: logo y título -->
      <div class="text-center mb-3">
        <h2 class="titulo-empresa">Supermercado Baratito</h2>
        <a href="https://uifresh.net/">
            <img src="<%= request.getContextPath() %>/img/icono_supermercado.png" alt="Logo" width="90" height="90">
        </a>
        <h1 class="text-white h4 mt-2">Inicio de Sesión</h1>
      </div>

      <!-- 
        Formulario de login.
        Envía los datos por POST al servlet logincontrolador.
        Parámetros: txtusuario, txtclave, accion (btniniciar)
      -->
      <form class="mt-3" method="post" action="<%= request.getContextPath() %>/logincontrolador">

        <!-- Campo de usuario con ícono FontAwesome -->
        <div class="input-group uf-input-group input-group-lg mb-3">
          <span class="input-group-text fa fa-user"></span>
          <input type="text" class="form-control" name="txtusuario" placeholder="Nombre de Usuario">
        </div>

        <!-- Campo de contraseña con ícono FontAwesome -->
        <div class="input-group uf-input-group input-group-lg mb-3">
          <span class="input-group-text fa fa-lock"></span>
          <input type="password" class="form-control" name="txtclave" placeholder="Contraseña">
        </div>

        <!-- Botón de submit, envía accion=btniniciar al controlador -->
        <div class="d-grid mb-3">
            <button type="submit" name="accion" value="btniniciar" class="btn uf-btn-primary btn-lg">
                Iniciar Sesión
            </button>
        </div>

      </form>

      <%
        /*
         * Bloque de control de intentos fallidos.
         * - Obtiene el atributo "mensaje" enviado desde el controlador cuando el login falla
         * - Mantiene un contador de intentos en la sesión
         * - Si el contador llega a 3: muestra un modal de bloqueo con countdown de 5 segundos
         * - Si es menor a 3: muestra un mensaje de error con el número de intento actual
         */
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
                <!-- Modal de bloqueo: aparece tras 3 intentos fallidos -->
                <div class="modal-bloqueo">
                    <div class="modal-bloqueo-content">
                        <h2>⚠️ Demasiados intentos</h2>
                        <p>Esperá <span id="contador">5</span> segundos antes de volver a intentar.</p>
                    </div>
                </div>

                <script>
                    /*
                     * Countdown de 5 segundos.
                     * Al llegar a 0 redirige al login y resetea el contador de sesión.
                     */
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
                // Resetea el contador de intentos tras el bloqueo
                session.setAttribute("contador", 0);
            } else {
                // Muestra mensaje de intento fallido con el número actual
                out.print("<p class='mensaje-error'>Datos incorrectos. Intento " + contador + " de 3.</p>");
            }
        }
      %>

    </div>

    <!-- Bootstrap JS -->
    <script src="./assets/js/popper.min.js"></script>
    <script src="./assets/js/bootstrap.min.js"></script>
  </body>
</html>