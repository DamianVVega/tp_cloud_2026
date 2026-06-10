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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        // Obtiene la acción enviada desde el formulario
        String accion = request.getParameter("accion");

        // Página destino tras procesar la acción
        String pagina = "";

        // Instancia del modelo de usuario para acceder a la lógica de negocio
        usuariomodelo lo = new usuariomodelo();

        // Verifica si la acción es iniciar sesión
        if (accion.equals("btniniciar")) {
            pagina = procesarLogin(request, lo);
        }

        // Redirige a la página correspondiente según el resultado del login
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