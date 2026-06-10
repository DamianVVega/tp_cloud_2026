package modelos;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class usuariomodelo {
    private String 
            usuario, 
            clave, 
            mensaje, 
            tipo, 
            codigo,
            personal,
            estado;
    PreparedStatement pst;
    ResultSet rs;
    Statement st;
    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    // Función para iniciar sesión con consulta preparada
    public String iniciar() throws SQLException {
    String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ?";
    PreparedStatement ps = utilidades.conexion.enlace().prepareStatement(sql);

    // Convertir la clave ingresada en hash antes de comparar
    String claveHasheada = utilidades.seguridad.hashSHA256(clave);

    ps.setString(1, usuario);
    ps.setString(2, claveHasheada);

    rs = ps.executeQuery();
    if (rs.next()) {
        mensaje = "si";
        codigo = rs.getString("idusuarios");
        tipo = rs.getString("tipo");
    } else {
        mensaje = "no";
    }

    rs.close();
    ps.close();
    return mensaje;
}

    //Funcionalidades para el crud
    public void guardar() {
    String sql = "INSERT INTO usuarios (usuario, clave, tipo, estado, idpersonales) VALUES (?, ?, ?, ?, ?)";

    try {
        Connection con = utilidades.conexion.enlace();
        PreparedStatement ps = con.prepareStatement(sql);

        // Hashear la contraseña antes de guardar
        String claveHasheada = utilidades.seguridad.hashSHA256(clave);

        ps.setString(1, usuario);
        ps.setString(2, claveHasheada);
        ps.setString(3, tipo);
        ps.setString(4, "Activo");
        ps.setString(5, personal);

        ps.executeUpdate();
        mensaje = "Usuario Guardado";

        ps.close();
        con.close();

    } catch (SQLException ex) {
        mensaje = "Error al guardar usuario"; // <-- Línea agregada
        Logger.getLogger(usuariomodelo.class.getName()).log(Level.SEVERE, "Error al guardar, descripcion: ", ex);
    }
}

    //Funcion para mostrar los usuarios
    public List<usuariomodelo> listar(){
        String sql="SELECT * \n" +
"FROM usuarios u\n" +
"INNER JOIN personales p ON u.idpersonales = p.idpersonales; ";
        List<usuariomodelo> lista= new ArrayList<>();//Crear un vector
        
        try {
            //abrir la conexion
            st=utilidades.conexion.sta();//COnexion abierta y preparada
            rs=st.executeQuery(sql);
            while(rs.next()){
                usuariomodelo p1= new usuariomodelo();
                p1.setCodigo(rs.getString("idusuarios"));
                p1.setUsuario(rs.getString("usuario"));
                p1.setClave(rs.getString("clave"));
                p1.setTipo(rs.getString("tipo"));
                p1.setEstado(rs.getString("estado"));
                p1.setPersonal(rs.getString("per_nombre"));
                lista.add(p1);
            }
            st.close();
            rs.close();
            
            
        } catch (SQLException ex) {
            System.out.print(ex);
        }
        return lista;
    }
    //Funcion para eliminar un cliente
   public void eliminar(String codigo) {
    String sql = "DELETE FROM usuarios WHERE idusuarios = ?";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, codigo);
        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            mensaje = "USUARIO ELIMINADO";
        } else {
            mensaje = "No se encontró usuario con ese código";
        }

    } catch (SQLException ex) {
        // Verifica si el error se debe a una restricción de clave foránea
        if (ex.getMessage().toLowerCase().contains("foreign key")) {
            mensaje = "No se puede eliminar el usuario porque está relacionado con otros registros..";
        } else {
            ex.printStackTrace(); // Mostrar otros errores en consola para depuración
            mensaje = "Error al intentar eliminar el usuario.";
        }
    }
}

    //Listar personales
    public List<usuariomodelo> listarPersonales() {
    String sql = "SELECT idpersonales, per_nombre FROM personales";
    List<usuariomodelo> lista = new ArrayList<>();

    try {
        st = utilidades.conexion.sta();
        rs = st.executeQuery(sql);
        while (rs.next()) {
            usuariomodelo p = new usuariomodelo();
            p.setCodigo(rs.getString("idpersonales")); // usarás esto como valor
            p.setPersonal(rs.getString("per_nombre")); // mostrarás esto en el combo
            lista.add(p);
        }
        rs.close();
        st.close();
    } catch (SQLException e) {
        System.out.println(e);
    }

    return lista;
}
 //Funcion para actualizar los usuarios
   public void actualizar() {
    String sql ="UPDATE `usuarios` SET `usuario`=?,`clave`=?,`tipo`=?,`idpersonales`=? where idusuarios= ?" ;


    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {
        // Hashear la clave antes de modificar
        String claveHasheada = utilidades.seguridad.hashSHA256(clave);
        ps.setString(1, usuario);     
        ps.setString(2, claveHasheada);     
        ps.setString(3, tipo);      
        ps.setString(4, personal);
        ps.setString(5, codigo);   
        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas > 0) {
            mensaje = "USUARIO ACTUALIZADO";
        } else {
            mensaje = "No se encontró un usuario con ese código";
        }

    } catch (SQLException ex) {
        System.out.print(ex);
    }
    }
   //Metodo para filtrar por nombre de usuario
   public List<usuariomodelo> buscarPorNombre(String nombre) {
    String sql = "SELECT * FROM usuarios u " +
                 "INNER JOIN personales p ON u.idpersonales = p.idpersonales " +
                 "WHERE u.usuario LIKE ?";
    List<usuariomodelo> lista = new ArrayList<>();

    try {
        PreparedStatement ps = utilidades.conexion.enlace().prepareStatement(sql);
        ps.setString(1, "%" + nombre + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            usuariomodelo p1 = new usuariomodelo();
            p1.setCodigo(rs.getString("idusuarios"));
            p1.setUsuario(rs.getString("usuario"));
            p1.setClave(rs.getString("clave"));
            p1.setTipo(rs.getString("tipo"));
            p1.setEstado(rs.getString("estado"));
            p1.setPersonal(rs.getString("per_nombre"));
            lista.add(p1);
        }

        rs.close();
        ps.close();
    } catch (SQLException ex) {
        System.out.print(ex);
    }
    mensaje="buscar";
    return lista;
}

}
