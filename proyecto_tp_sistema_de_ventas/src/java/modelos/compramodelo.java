
package modelos;

/**
 *
 * @author Damian0
 */

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class compramodelo {
    private String id, fecha, condicion, estado, proveedor, usuario;

    Statement st;
    ResultSet rs;

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getCondicion() { return condicion; }
    public void setCondicion(String condicion) { this.condicion = condicion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    // Listar compras
    public List<compramodelo> listar() {
        List<compramodelo> lista = new ArrayList<>();
        String sql = "SELECT c.idcompras, c.com_fecha, c.com_condicion, c.com_estado, p.prov_nombre, u.usuario AS usuario "
                + "FROM compras c "
                + "JOIN proveedores p ON c.idproveedores = p.idproveedores "
                + "JOIN usuarios u ON c.idusuarios = u.idusuarios";

        try {
            st = utilidades.conexion.sta();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                compramodelo c = new compramodelo();
                c.setId(rs.getString("idcompras"));
                c.setFecha(rs.getString("com_fecha"));
                c.setCondicion(rs.getString("com_condicion"));
                c.setEstado(rs.getString("com_estado"));
                c.setProveedor(rs.getString("prov_nombre"));
                c.setUsuario(rs.getString("usuario"));
                lista.add(c);
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(compramodelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    // Listar proveedores
public List<String[]> listarProveedores() {
    List<String[]> lista = new ArrayList<>();
    String sql = "SELECT idproveedores, prov_nombre, prov_ruc, prov_correo, prov_telefono FROM proveedores";

    try {
        st = utilidades.conexion.sta();
        rs = st.executeQuery(sql);
        while (rs.next()) {
            String[] proveedor = new String[5];
            proveedor[0] = rs.getString("idproveedores");
            proveedor[1] = rs.getString("prov_nombre");
            proveedor[2] = rs.getString("prov_ruc");
            proveedor[3] = rs.getString("prov_correo");
            proveedor[4] = rs.getString("prov_telefono");
            lista.add(proveedor);
        }
        st.close();
        rs.close();
    } catch (SQLException ex) {
        Logger.getLogger(compramodelo.class.getName()).log(Level.SEVERE, null, ex);
    }

    return lista;
}


    // Listar productos adaptado
    public List<String[]> listarProductos() {
        List<String[]> lista = new ArrayList<>();
        String sql = "SELECT idproductos, pro_nombre, pro_costo, pro_iva FROM productos";

        try {
            st = utilidades.conexion.sta();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String[] producto = new String[4];
                producto[0] = rs.getString("idproductos");
                producto[1] = rs.getString("pro_nombre");
                producto[2] = rs.getString("pro_costo"); // Se usa el costo para compras
                producto[3] = rs.getString("pro_iva") != null ? rs.getString("pro_iva") : "EXENTA";
                lista.add(producto);
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(compramodelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    // Guardar cabecera
    // Guardar cabecera con PreparedStatement
public String guardarCabecera() {
    String idGenerado = "";
    String sql = "INSERT INTO compras (com_fecha, com_condicion, com_estado, idproveedores, idusuarios) "
               + "VALUES (?, ?, ?, ?, ?)";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, fecha);
        ps.setString(2, condicion);
        ps.setString(3, estado);
        ps.setString(4, proveedor);
        ps.setString(5, usuario);
        ps.executeUpdate();

        // Obtener el último ID insertado (versión segura)
        ResultSet rs = ps.executeQuery("SELECT MAX(idcompras) AS id FROM compras");
        if (rs.next()) {
            idGenerado = rs.getString("id");
        }
        rs.close();

    } catch (SQLException ex) {
        Logger.getLogger(compramodelo.class.getName()).log(Level.SEVERE, null, ex);
    }

    return idGenerado;
}


    // Guardar detalle
    // Guardar detalle con PreparedStatement
public String guardarDetalle(String idcompra, String idproducto, String costo, String cantidad) {
    String mensaje = "Detalle registrado correctamente";
    String sql = "INSERT INTO detallecompras (idcompras, idproductos, det_cantidad, det_precio) "
               + "VALUES (?, ?, ?, ?)";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, idcompra);
        ps.setString(2, idproducto);
        ps.setString(3, cantidad);
        ps.setString(4, costo);
        ps.executeUpdate();

    } catch (SQLException ex) {
        mensaje = "Error al registrar detalle: " + ex.getMessage();
        Logger.getLogger(compramodelo.class.getName()).log(Level.SEVERE, null, ex);
    }

    return mensaje;
}


    // Calcular total de la compra
    public int calcularTotalCompra(String idcompra) {
        int total = 0;
        String sql = "SELECT det_cantidad, det_precio FROM detallecompras WHERE idcompras = ?";

        try (PreparedStatement ps = utilidades.conexion.enlace().prepareStatement(sql)) {
            ps.setString(1, idcompra);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int cantidad = rs.getInt("det_cantidad");
                int precio = rs.getInt("det_precio");
                total += cantidad * precio;
            }

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(compramodelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return total;
    }
}
