
package modelos;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.ResultSet;//Para la variable donde se guardan los resultados del statement
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;

/**
 *
 * @author Damian0
 */

public class productosmodelo {
    private String  codigo,nombre, precio, cantidad, iva,costo;
    Statement st; //Abre y prepara la conexion
    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }
    
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }
   
    ResultSet rs; //Contiene los resultados del select
    //Funcionalidades
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    //Funcion para guardar los productos
   public void guardar() {
    String sql = "INSERT INTO productos ( pro_nombre, pro_precio, pro_cantidad, pro_iva, pro_costo) "
               + "VALUES (?, ?, ?, ?, ?)";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {
      
        ps.setString(1, nombre);       // 'nombre' String
        ps.setString(2, precio);       // 'precio' double o float
        ps.setString(3, cantidad);        // 'cantidad' int
        ps.setString(4, iva);          // 'iva' double o float
        ps.setString(5, costo);        // 'costo' double o float

        ps.executeUpdate();
        mensaje = "Producto Guardado";

    } catch (SQLException ex) {
        System.out.print(ex);
    }
}

    //Funcion para eliminar un producto
   public void eliminar(String codigo) {
    String sql = "DELETE FROM productos WHERE idproductos = ?";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, codigo);
        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            mensaje = "PRODUCTO ELIMINADO";
        } else {
            mensaje = "No se encontró producto con ese código";
        }

    } catch (SQLException ex) {
        // Verifica si el error se debe a una restricción de clave foránea
        if (ex.getMessage().toLowerCase().contains("foreign key")) {
            mensaje = "No se puede eliminar el producto porque está relacionado con una venta.";
        } else {
            ex.printStackTrace(); // Mostrar otros errores en consola para depuración
            mensaje = "Error al intentar eliminar el producto.";
        }
    }
}

    //Funcion para actualizar los productos
   public void actualizar() {
    String sql = "UPDATE productos SET pro_nombre = ?, pro_precio = ?, pro_cantidad = ?, pro_iva = ?, pro_costo = ? WHERE idproductos = ?";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nombre);     // asumiendo String
        ps.setString(2, precio);     // asumiendo double o float
        ps.setString(3, cantidad);      // asumiendo int
        ps.setString(4, iva);        // asumiendo double o float
        ps.setString(5, costo);      // asumiendo double o float
        ps.setString(6, codigo);     // idproductos

        int filasAfectadas = ps.executeUpdate();
        if (filasAfectadas > 0) {
            mensaje = "PRODUCTO ACTUALIZADO";
        } else {
            mensaje = "No se encontró producto con ese código";
        }

    } catch (SQLException ex) {
        System.out.print(ex);
    }
}

    

    //Funcion para mostrar los productos
    public List<productosmodelo> listar(){
        String sql="Select * from productos";
        List<productosmodelo> lista= new ArrayList<>();//Crear un vector
        
        try {
            //abrir la conexion
            st=utilidades.conexion.sta();//COnexion abierta y preparada
            rs=st.executeQuery(sql);
            while(rs.next()){
                productosmodelo p1= new productosmodelo();
                p1.setCodigo(rs.getString("idproductos"));
                p1.setNombre(rs.getString("pro_nombre"));
                p1.setPrecio(rs.getString("pro_precio"));
                p1.setCantidad(rs.getString("pro_cantidad"));
                p1.setIva(rs.getString("pro_iva"));
                p1.setCosto(rs.getString("pro_costo"));
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
    
