/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Damia
 */
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.ResultSet;//Para la variable donde se guardan los resultados del statement
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
public class proveedoresmodelo {
    private String codigo, nombre,ruc,telefono, correo,mensaje;
    //Variables para los metodos
    ResultSet rs;
    Statement st;
    //Setters and getters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    //Funcion para guardar los productos
    public void guardar() {
    String sql = "INSERT INTO proveedores (prov_nombre, prov_ruc, prov_telefono, prov_correo) VALUES (?, ?, ?, ?)";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombre);
        ps.setString(2, ruc);
        ps.setString(3, telefono);
        ps.setString(4, correo);

        ps.executeUpdate();
        mensaje = "Proveedor Guardado";

    } catch (SQLException ex) {
        System.out.print(ex);
    }
}

    //Funcion para eliminar un producto
   public void eliminar(String codigo) {
    String sql = "DELETE FROM proveedores WHERE idproveedores = ?";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, codigo);
        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            mensaje = "PROVEEDOR ELIMINADO";
        } else {
            mensaje = "No se encontró proveedor con ese código";
        }

    } catch (SQLException ex) {
        // Verifica si el error se debe a una restricción de clave foránea
        if (ex.getMessage().toLowerCase().contains("foreign key")) {
            mensaje = "No se puede eliminar el proveedor porque está relacionado con una compra.";
        } else {
            ex.printStackTrace(); // Mostrar otros errores en consola para depuración
            mensaje = "Error al intentar eliminar el proveedor.";
        }
    }
}

    //Funcion para actualizar los productos
   public void actualizar() {
    String sql = "UPDATE proveedores SET prov_nombre = ?, prov_ruc = ?, prov_telefono = ?, prov_correo = ? WHERE idproveedores = ?";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombre);
        ps.setString(2, ruc);
        ps.setString(3, telefono);
        ps.setString(4, correo);
        ps.setString(5, codigo);

        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            mensaje = "PROVEEDOR ACTUALIZADO";
        } else {
            mensaje = "No se encontró proveedor con ese código";
        }

    } catch (SQLException ex) {
        System.out.print(ex);
    }
}

    

    //Funcion para mostrar los productos
    public List<proveedoresmodelo> listar(){
        String sql="Select * from proveedores";
        List<proveedoresmodelo> lista= new ArrayList<>();//Crear un vector
        
        try {
            //abrir la conexion
            st=utilidades.conexion.sta();//COnexion abierta y preparada
            rs=st.executeQuery(sql);
            while(rs.next()){
                proveedoresmodelo p1= new proveedoresmodelo();
                p1.setCodigo(rs.getString("idproveedores"));
                p1.setNombre(rs.getString("prov_nombre"));
                p1.setRuc(rs.getString("prov_ruc"));
                p1.setTelefono(rs.getString("prov_telefono"));
                p1.setCorreo(rs.getString("prov_correo"));
                lista.add(p1);
            }
            st.close();
            rs.close();
            
            
        } catch (SQLException ex) {
            System.out.print(ex);
        }
        return lista;
    }
}
