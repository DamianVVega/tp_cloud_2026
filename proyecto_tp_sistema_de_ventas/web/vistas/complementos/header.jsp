<%-- 
    Document   : header.jsp
    Created on : 26 jun. 2025, 16:43:51
    Author     : Damian0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Principal</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styleventa.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:ital,wght@0,100..700;1,100..700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="parent">
        <div class="div1">
            <!-- Recibe el atributo desde el login -->
<h2  style="color: white; position: absolute; top: 10px; left: 20px;">     Bienvenido <%= session.getAttribute("usuario") %>    </h2>

            <h1 class="titulo1">
                Supermercado Baratito
            </h1>
            <img src="<%= request.getContextPath() %>/img/icono_supermercado.png" alt="icono_supermercado" width="50px" height="50px">
            <form action="menucontrolador" method="post">
                <button type="submit" class="btn-cerrar-sesion" name="accion" value="cerrarsesion">Cerrar Sesion</button>
                
            </form>
            
           
            
        </div>
       
       