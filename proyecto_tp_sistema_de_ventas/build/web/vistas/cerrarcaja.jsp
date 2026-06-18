<%-- 
    Document   : cerrarcaja
    Created on : 27 jun. 2025, 14:12:52
    Author     : Damian0
--%>

<%@page import="modelos.aperturamodelo"%>
<%@page import="modelos.cierremodelo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/vistas/complementos/header.jsp" />
<jsp:include page="/vistas/complementos/menu.jsp" />

<div class="div3">
    <%
            String usuario= (String)session.getAttribute("usuario");
            if(usuario == null){
               response.sendRedirect(request.getContextPath() + "/vistas/login.jsp");

                return;
            }
        %>
            <%
                //Se instancia con el modelo para saber que caja hay que cerrar
                aperturamodelo ape= new  aperturamodelo();
               //Segun  el usuarioque inicio sesion
                String idusu=(String) session.getAttribute("codigo");
                //
                ape.setIdusuario(idusu);
                 String estado= ape.verificar();
            %>

    <div class="titulomodulo"> 
        <h1>Usuarios</h1>
    </div>
 
           <div class="container mt-4">
    <div class="card shadow border-danger">
        <div class="card-header bg-danger text-white">
            <h4 class="mb-0">Cierre de Caja</h4>
        </div>
        <div class="card-body">
            <form action="cierrecontrolador" method="post">
               
                <div class="mb-3">
                    <label class="form-label">Monto</label>
                    <input type="number" name="txtmonto" class="form-control" placeholder="Ingrese monto de cierre">
                </div>
                <div class="mb-3">
                    <label class="form-label">ID de Apertura</label>
                    <input type="text" name="txtapertura" value="<%= ape.getIdapertura() %>" class="form-control" readonly>
                </div>
                <div class="text-center">
                    <button type="submit" name="accion" value="btncerrar" class="btn btn-danger w-50">Cerrar Caja</button>
                </div>
            </form>
        </div>
    </div>
</div>

</div>
<jsp:include page="/vistas/complementos/footer.jsp" />
<script src="<%= request.getContextPath() %>/js/scripts.js"></script>

<!--  <script src="<%= request.getContextPath() %>/js/scripts.js"></script>-->