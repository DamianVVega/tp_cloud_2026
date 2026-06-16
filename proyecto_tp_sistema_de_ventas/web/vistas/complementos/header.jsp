<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Supermercado Baratito</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styleventa.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }

        body {
            font-family: 'Inter', sans-serif;
            background-color: #f0f4fa;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .topbar {
            background-color: #0d2d5e;
            display: grid;
            grid-template-columns: 1fr auto 1fr;
            align-items: center;
            padding: 0 32px;
            height: 90px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.3);
            position: sticky;
            top: 0;
            z-index: 200;
        }

        .topbar-left {
            display: flex;
            flex-direction: column;
            justify-content: center;
            gap: 6px;
        }

        .topbar-usuario {
            color: #ffffff;
            font-size: 26px;
            font-weight: 700;
            display: flex;
            align-items: center;
            gap: 9px;
        }

        .topbar-usuario i { font-size: 30px; color: #5b8ec4; }
        .topbar-usuario span { color: #90b8e0; font-weight: 400; font-size: 22px; }

        .topbar-reloj {
            color: #6a9ec4;
            font-size: 20px;
            font-family: 'Courier New', monospace;
            display: flex;
            align-items: center;
            gap: 8px;
            padding-left: 33px;
        }

        .topbar-reloj i { font-size: 19px; }

        .topbar-center {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 14px;
            color: #ffffff;
            font-size: 30px;
            font-weight: 700;
            letter-spacing: 0.5px;
            white-space: nowrap;
        }

        .topbar-center img { width: 50px; height: 50px; object-fit: contain; }

        .topbar-right {
            display: flex;
            justify-content: flex-end;
            align-items: center;
        }

        .btn-cerrar-sesion {
            background: transparent;
            border: 1.5px solid rgba(255,255,255,0.25);
            color: #ffffff;
            font-family: 'Inter', sans-serif;
            font-size: 14px;
            font-weight: 500;
            padding: 10px 20px;
            border-radius: 7px;
            cursor: pointer;
            transition: background 0.2s, border-color 0.2s, color 0.2s;
            display: flex;
            align-items: center;
            gap: 8px;
            white-space: nowrap;
        }

        .btn-cerrar-sesion:hover {
            background: rgba(220,60,60,0.25);
            border-color: rgba(220,80,80,0.6);
            color: #ffcccc;
        }

        .navbar-main {
            background-color: #1a4a8a;
            height: 54px;
            position: sticky;
            top: 90px;
            z-index: 199;
            border-bottom: 1px solid rgba(255,255,255,0.08);
            overflow: hidden;
            flex-shrink: 0;
        }

        .navbar-main form {
            display: flex !important;
            align-items: center !important;
            justify-content: space-between !important;
            height: 54px !important;
            padding: 0 24px !important;
            margin: 0 !important;
            width: 100% !important;
        }

        .navbar-izq { display: flex; align-items: center; }
        .navbar-der { display: flex; align-items: center; gap: 10px; }

        .nav-btn {
            background: transparent !important;
            border: none !important;
            border-bottom: 2px solid transparent !important;
            border-radius: 0 !important;
            color: #b8d4f0 !important;
            font-family: 'Inter', sans-serif !important;
            font-size: 15px !important;
            font-weight: 500 !important;
            padding: 0 16px !important;
            height: 54px !important;
            cursor: pointer !important;
            display: inline-flex !important;
            align-items: center !important;
            gap: 7px !important;
            white-space: nowrap !important;
            transition: background 0.15s, color 0.15s, border-color 0.15s !important;
            box-shadow: none !important;
        }

        .nav-btn i { font-size: 17px; color: #5b8ec4; }

        .nav-btn:hover {
            background: rgba(255,255,255,0.1) !important;
            color: #ffffff !important;
            border-bottom-color: #5ba8e0 !important;
        }

        .nav-btn:hover i { color: #90c8f0; }

        .nav-divider-v {
            width: 1px;
            height: 26px;
            background: rgba(255,255,255,0.15);
            margin: 0 8px;
        }

        .btn-caja {
            font-family: 'Inter', sans-serif !important;
            font-size: 13px !important;
            font-weight: 500 !important;
            padding: 6px 16px !important;
            border-radius: 6px !important;
            cursor: pointer !important;
            display: inline-flex !important;
            align-items: center !important;
            gap: 7px !important;
            white-space: nowrap !important;
            height: 36px !important;
            transition: background 0.2s, border-color 0.2s !important;
            box-shadow: none !important;
        }

        .btn-caja-abrir {
            background: transparent !important;
            color: #7de8b0 !important;
            border: 1px solid rgba(77,204,138,0.4) !important;
        }

        .btn-caja-abrir i { font-size: 15px; color: #4dcc8a; }

        .btn-caja-abrir:hover {
            background: rgba(77,204,138,0.15) !important;
            border-color: rgba(77,204,138,0.7) !important;
            color: #aaf0cc !important;
        }

        .btn-caja-cerrar {
            background: transparent !important;
            color: #f0a0a0 !important;
            border: 1px solid rgba(220,80,80,0.4) !important;
        }

        .btn-caja-cerrar i { font-size: 15px; color: #e06060; }

        .btn-caja-cerrar:hover {
            background: rgba(200,60,60,0.2) !important;
            border-color: rgba(220,80,80,0.7) !important;
            color: #ffcccc !important;
        }

        #mensaje-alerta {
            display: none;
            background-color: #1a56a0;
            color: white;
            border-radius: 7px;
            z-index: 9999;
            max-width: 90%;
            word-wrap: break-word;
            font-size: 14px;
            margin: 12px auto;
            text-align: center;
            padding: 10px 20px;
            box-shadow: 0 3px 8px rgba(0,0,0,0.2);
        }
    </style>
</head>
<body>

    <div class="topbar">
        <div class="topbar-left">
            <div class="topbar-usuario">
                <i class="bi bi-person-circle"></i>
                <span>Bienvenido,</span>
                <strong><%= session.getAttribute("usuario") %></strong>
            </div>
            <div class="topbar-reloj">
                <i class="bi bi-calendar3"></i>
                <span id="fecha-top"></span>
                <span style="opacity:0.4; margin:0 4px">|</span>
                <i class="bi bi-clock"></i>
                <span id="hora-top">00:00:00</span>
            </div>
        </div>
        <div class="topbar-center">
            <img src="<%= request.getContextPath() %>/img/icono_supermercado.png" alt="Logo">
            Supermercado Baratito
        </div>
        <div class="topbar-right">
            <form action="menucontrolador" method="post" style="margin:0;">
                <button type="submit" class="btn-cerrar-sesion" name="accion" value="cerrarsesion">
                    <i class="bi bi-box-arrow-right"></i> Cerrar sesión
                </button>
            </form>
        </div>
    </div>

    <div class="navbar-main">
        <jsp:include page="menu.jsp" />
    </div>