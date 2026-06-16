package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.usuariomodelo;

/**
 * Controlador de Login
 * Maneja la autenticación de usuarios mediante POST.
 * Redirige al index si el login es exitoso, o vuelve al login si falla.
 */
@WebServlet(name = "logincontrolador", urlPatterns = {"/logincontrolador"})
public class logincontrolador extends HttpServlet {

    /**
     * Procesa requests GET y POST.
     * En este caso no se usa directamente, la lógica está en doPost.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Maneja HTTP GET.
     * Delega a processRequest (sin lógica adicional).
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Maneja HTTP POST.
     * Recibe el formulario de login, detecta la acción y procesa el login.
     * Parámetros esperados del formulario:
     *   - accion: identifica qué botón fue presionado ("btniniciar")
     *   - txtusuario: nombre de usuario ingresado
     *   - txtclave: contraseña ingresada
     */
    /**
        * Maneja las solicitudes POST para el módulo de LOGIN.
        * Valida las credenciales del usuario y, si son correctas,
        * inicia la sesión y redirige al menú principal.
        * En caso contrario, regresa al login con un mensaje de error.
        */
       @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {

           processRequest(request, response);

           // Obtenemos la acción enviada desde el formulario
           String accion = request.getParameter("accion");

           // Vista a la que redirigiremos al finalizar el proceso
           String pagina = "";

           // Creamos el modelo de usuario para manejar la lógica de autenticación
           usuariomodelo lo = new usuariomodelo();

           if (accion.equals("btniniciar")) {
               // --- INICIO DE SESIÓN ---
               try {
                   // Asignamos las credenciales ingresadas por el usuario
                   lo.setUsuario(request.getParameter("txtusuario"));
                   lo.setClave(request.getParameter("txtclave"));

                   // Verificamos si las credenciales son válidas en la base de datos
                   if (lo.iniciar().equals("si")) {
                       // --- CREDENCIALES CORRECTAS ---
                       // Redirigimos al menú principal
                       pagina = "index.jsp";

                       // Iniciamos la sesión y guardamos los datos del usuario logueado
                       HttpSession session = request.getSession();
                       session.setAttribute("usuario", lo.getUsuario()); // Nombre de usuario
                       session.setAttribute("codigo", lo.getCodigo());   // Código/ID del usuario
                       session.setAttribute("tipo", lo.getTipo());       // Tipo/rol del usuario

                   } else {
                       // --- CREDENCIALES INCORRECTAS ---
                       // Regresamos al login con un mensaje de error
                       pagina = "/vistas/login.jsp";
                       request.setAttribute("mensaje", "no");
                   }

               } catch (SQLException ex) {
                   // Error al intentar validar las credenciales en la base de datos
                   Logger.getLogger(logincontrolador.class.getName())
                         .log(Level.SEVERE, "Error al intentar iniciar sesión", ex);
               }
           }

           // Redirigimos a la vista correspondiente
           request.getRequestDispatcher(pagina).forward(request, response);
       }
    /**
     * Procesa la lógica de autenticación del usuario.
     * - Obtiene usuario y clave del formulario
     * - Consulta al modelo si las credenciales son válidas
     * - Si es válido: guarda datos en sesión y redirige al index
     * - Si no es válido: envía mensaje de error y vuelve al login
     *
     * @param request objeto HTTP con los parámetros del formulario
     * @param lo instancia del modelo de usuario
     * @return String con la página destino
     */
    private String procesarLogin(HttpServletRequest request, usuariomodelo lo) {
        String pagina = "";

        try {
            // Asigna usuario y clave al modelo desde los parámetros del formulario
            lo.setUsuario(request.getParameter("txtusuario"));
            lo.setClave(request.getParameter("txtclave"));

            // Consulta al modelo si las credenciales son correctas
            if (lo.iniciar().equals("si")) {

                // Login exitoso: guarda datos del usuario en la sesión
                HttpSession session = request.getSession();
                session.setAttribute("usuario", lo.getUsuario()); // nombre de usuario
                session.setAttribute("codigo", lo.getCodigo());   // código/ID del usuario
                session.setAttribute("tipo", lo.getTipo());       // tipo/rol del usuario

                // Redirige al menú principal
                pagina = "index.jsp";

            } else {
                // Login fallido: envía mensaje de error a la vista
                request.setAttribute("mensaje", "no");

                // Vuelve a la página de login
                pagina = "/vistas/login.jsp";
            }

        } catch (SQLException ex) {
            Logger.getLogger(logincontrolador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pagina;
    }

    /**
     * Descripción corta del servlet.
     */
    @Override
    public String getServletInfo() {
        return "Controlador de autenticación de usuarios";
    }
}