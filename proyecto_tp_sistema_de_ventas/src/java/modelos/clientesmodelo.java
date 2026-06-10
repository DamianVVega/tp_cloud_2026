
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
public class clientesmodelo {
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
    String sql = "INSERT INTO clientes (cli_nombre, cli_apellido, cli_ci, cli_telefono) VALUES (?, ?, ?, ?)";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, dni);      // si `cli_ci` es numérico, puede usar ps.setInt()
        ps.setString(4, telefono); // si es numérico, puede usar ps.setInt() también

        ps.executeUpdate();
        mensaje = "Cliente Guardado";

    } catch (SQLException ex) {
        Logger.getLogger(clientesmodelo.class.getName()).log(Level.SEVERE, "Error al guardar, descripción: ", ex);
    }
}

    //Funcion para mostrar los clientes
    public List<clientesmodelo> listar(){
        String sql="Select * from clientes";
        List<clientesmodelo> lista= new ArrayList<>();//Crear un vector
        
        try {
            //abrir la conexion
            st=utilidades.conexion.sta();//COnexion abierta y preparada
            rs=st.executeQuery(sql);
            while(rs.next()){
                clientesmodelo p1= new clientesmodelo();
                p1.setCodigo(rs.getString("idclientes"));
                p1.setNombre(rs.getString("cli_nombre"));
                p1.setApellido(rs.getString("cli_apellido"));
                p1.setDni(rs.getString("cli_ci"));
                p1.setTelefono(rs.getString("cli_telefono"));
                
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
    String sql = "DELETE FROM clientes WHERE idclientes = ?";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, codigo); // si `idclientes` es numérico, use `ps.setInt(1, Integer.parseInt(codigo));`
        ps.executeUpdate();
        mensaje = "CLIENTE ELIMINADO";

    } catch (SQLException ex) {
        // Verifica si el error se debe a una restricción de clave foránea
        if (ex.getMessage().toLowerCase().contains("foreign key")) {
            mensaje = "No se puede eliminar el cliente porque está relacionado con una venta.";
        } else {
            ex.printStackTrace(); // Mostrar otros errores en consola para depuración
            mensaje = "Error al intentar eliminar el cliente.";
        }
    }
}

    //Funcion para actualizar los clientes
    public void actualizar() {
    String sql = "UPDATE clientes SET cli_nombre = ?, cli_apellido = ?, cli_ci = ?, cli_telefono = ? WHERE idclientes = ?";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, dni);
        ps.setString(4, telefono);
        ps.setString(5, codigo); // si idclientes es INT, use: ps.setInt(5, Integer.parseInt(codigo));

        ps.executeUpdate();
        mensaje = "CLIENTE ACTUALIZADO";

    } catch (SQLException ex) {
        System.out.print("Error al actualizar: " + ex.getMessage());
    }
}

}
