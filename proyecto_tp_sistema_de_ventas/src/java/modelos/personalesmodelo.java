
package modelos;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
/**
 *
 * @author Damian
 */
public class personalesmodelo {
           private String codigo,nombre,apellido,telefono,dni,mensaje;
           Statement st;
           ResultSet rs;
    public String getNombre() {
        return nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    //Funcionalidades para el crud
    public void guardar() {
    String sql = "INSERT INTO personales (per_nombre, per_apellido, per_ci, per_telefono) VALUES (?, ?, ?, ?)";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, dni);
        ps.setString(4, telefono);

        ps.executeUpdate();
        mensaje = "Personal Guardado";

    } catch (SQLException ex) {
        Logger.getLogger(personalesmodelo.class.getName()).log(Level.SEVERE, "Error al guardar, descripcion: ", ex);
    }
}

    //Funcion para mostrar los clientes
    public List<personalesmodelo> listar(){
        String sql="Select * from personales";
        List<personalesmodelo> lista= new ArrayList<>();//Crear un vector
        
        try {
            //abrir la conexion
            st=utilidades.conexion.sta();//COnexion abierta y preparada
            rs=st.executeQuery(sql);
            while(rs.next()){
                personalesmodelo p1= new personalesmodelo();
                p1.setCodigo(rs.getString("idpersonales"));
                p1.setNombre(rs.getString("per_nombre"));
                p1.setApellido(rs.getString("per_apellido"));
                p1.setDni(rs.getString("per_ci"));
                p1.setTelefono(rs.getString("per_telefono"));
                
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
    String sql = "DELETE FROM personales WHERE idpersonales = ?";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, codigo);
        ps.executeUpdate();
        mensaje = "PERSONAL ELIMINADO";

    } catch (SQLException ex) {
        // Verifica si el error se debe a una restricción de clave foránea
        if (ex.getMessage().toLowerCase().contains("foreign key")) {
            mensaje = "No se puede eliminar el personal porque está relacionado con una venta o compra.";
        } else {
            ex.printStackTrace(); // Mostrar otros errores en consola para depuración
            mensaje = "Error al intentar eliminar el personal.";
        }
    }
}

    //Funcion para actualizar los clientes
   public void actualizar() {
    String sql = "UPDATE personales SET per_nombre = ?, per_apellido = ?, per_ci = ?, per_telefono = ? WHERE idpersonales = ?";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, dni);
        ps.setString(4, telefono);
        ps.setString(5, codigo);

        ps.executeUpdate();
        mensaje = "PERSONAL ACTUALIZADO";

    } catch (SQLException ex) {
        System.out.print(ex);
    }
}

}
