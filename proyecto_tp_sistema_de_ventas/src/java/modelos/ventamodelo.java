package modelos;
// Este archivo pertenece al paquete "modelo", donde agrupamos las clases
// que se encargan de representar y gestionar los datos de la aplicación.
// Importamos las clases necesarias para trabajar con la base de datos y listas
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.Connection;
// Esta clase representa el modelo de una venta en el sistema
public class ventamodelo {
 // Atributos que almacenan los datos de una venta
 private String id, fecha, condicion, estado, cliente, usuario;
 // Objetos para ejecutar consultas y recorrer resultados desde la base de datos
 Statement st;
 ResultSet rs;
 // Getters y setters: nos permiten acceder o modificar los atributos privados de forma controlada
 public String getId() {
 return id;
 }
 public void setId(String id) {
 this.id = id;
 }
 public String getFecha() {
 return fecha;
 }
 public void setFecha(String fecha) {
 this.fecha = fecha;
 }
 public String getCondicion() {
 return condicion;
 }
 public void setCondicion(String condicion) {
 this.condicion = condicion;
 }
 public String getEstado() {
 return estado;
 }
 public void setEstado(String estado) {
 this.estado = estado;
 }
 public String getCliente() {
 return cliente;
 }
 public void setCliente(String cliente) {
 this.cliente = cliente;
 }
 public String getUsuario() {
 return usuario;
 }
 public void setUsuario(String usuario) {
 this.usuario = usuario;
 }
 // Método para obtener la lista de ventas con datos combinados de cliente y usuario
 public List<ventamodelo> listar() {
 List<ventamodelo> lista = new ArrayList<>();

 // Consulta SQL que une varias tablas para mostrar datos más completos
 String sql = "SELECT v.idventas, v.ven_fecha, v.ven_condicion, v.ven_estado, "
 + "CONCAT(c.cli_nombre, ' ', c.cli_apellido) AS cliente, "
 + "u.usuario AS usuario "
 + "FROM ventas v "
 + "JOIN clientes c ON v.idclientes = c.idclientes "
 + "JOIN usuarios u ON v.idusuarios = u.idusuarios";


 try {
 // Obtenemos la conexión activa desde la clase de utilidades
 st = utilidades.conexion.sta();
 rs = st.executeQuery(sql); // Ejecutamos la consulta SQL
 // Recorremos los resultados y vamos creando objetos de tipo ventamodelo
 while (rs.next()) {
 ventamodelo v = new ventamodelo();
 v.setId(rs.getString("idventas"));
 v.setFecha(rs.getString("ven_fecha"));
 v.setCondicion(rs.getString("ven_condicion"));
 v.setEstado(rs.getString("ven_estado"));
 v.setCliente(rs.getString("cliente"));
 v.setUsuario(rs.getString("usuario"));
 lista.add(v); // Agregamos la venta a la lista
 }
 // Cerramos la conexión y liberamos recursos
 st.close();
 rs.close();
 } catch (SQLException ex) {
 // En caso de error, lo registramos en el log del sistema
 Logger.getLogger(ventamodelo.class.getName()).log(Level.SEVERE, null, ex);
 }
 return lista; // Devolvemos la lista de ventas
 }
 // Método para obtener los datos básicos de todos los clientes (para el modal de selección)

 public List<String[]> listarClientes() {
 List<String[]> lista = new ArrayList<>();
 String sql = "SELECT idclientes, cli_nombre, cli_apellido, cli_ci FROM clientes";
 try {
 st = utilidades.conexion.sta();
 rs = st.executeQuery(sql);
 while (rs.next()) {
 // Usamos un arreglo de String para guardar los datos de cada cliente
 String[] cliente = new String[4];
 cliente[0] = rs.getString("idclientes");
 cliente[1] = rs.getString("cli_nombre");
 cliente[2] = rs.getString("cli_apellido");
 cliente[3] = rs.getString("cli_ci");
 lista.add(cliente);
 }
 st.close();
 rs.close();
 } catch (SQLException ex) {
 Logger.getLogger(ventamodelo.class.getName()).log(Level.SEVERE, null, ex);
 }
 return lista;
 }
 // Método para obtener los datos básicos de productos disponibles (para el modal)
 public List<String[]> listarProductos() {
 List<String[]> lista = new ArrayList<>();
 String sql = "SELECT idproductos, pro_nombre, pro_precio, pro_iva FROM productos";
 try {
 st = utilidades.conexion.sta();
 rs = st.executeQuery(sql);
 while (rs.next()) {
 // Cada producto se guarda en un arreglo de 4 elementos
 String[] producto = new String[4];
 producto[0] = rs.getString("idproductos");
 producto[1] = rs.getString("pro_nombre");
 producto[2] = rs.getString("pro_precio");
 producto[3] = rs.getString("pro_iva");
 lista.add(producto);
 }
 st.close();
 rs.close();
 } catch (SQLException ex) {
 Logger.getLogger(ventamodelo.class.getName()).log(Level.SEVERE, null, ex);
 }
 return lista;
 }
 // Método para guardar la cabecera (datos generales) de una nueva venta
// Guardar cabecera de la venta con PreparedStatement
public String guardarCabecera() {
    String idGenerado = "";
    String sql = "INSERT INTO ventas (ven_fecha, ven_condicion, ven_estado, idclientes, idusuarios) "
               + "VALUES (?, ?, ?, ?, ?)";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, fecha);
        ps.setString(2, condicion);
        ps.setString(3, estado);
        ps.setString(4, cliente);
        ps.setString(5, usuario);
        ps.executeUpdate();

        // Obtener el ID generado (puede adaptarse si se quiere usar getGeneratedKeys)
        ResultSet rs = con.createStatement().executeQuery("SELECT MAX(idventas) AS id FROM ventas");
        if (rs.next()) {
            idGenerado = rs.getString("id");
        }
        rs.close();

    } catch (SQLException ex) {
        Logger.getLogger(ventamodelo.class.getName()).log(Level.SEVERE, null, ex);
    }

    return idGenerado;
}

 // Método para guardar el detalle (los productos) de una venta
// Guardar detalle de la venta con PreparedStatement
public String guardarDetalle(String idventa, String idproducto, String precio, String cantidad) {
    String mensaje = "Detalle registrado correctamente";
    String sql = "INSERT INTO detalleventas (idventas, idproductos, det_cantidad, det_precio) "
               + "VALUES (?, ?, ?, ?)";

    try (Connection con = utilidades.conexion.enlace();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, idventa);
        ps.setString(2, idproducto);
        ps.setString(3, cantidad);
        ps.setString(4, precio);
        ps.executeUpdate();

    } catch (SQLException ex) {
        if (ex.getMessage().contains("No hay suficiente stock")) {
            mensaje = "Error: no hay suficiente stock para el producto con ID: " + idproducto;
        } else {
            mensaje = "Error al registrar detalle: " + ex.getMessage();
        }
        Logger.getLogger(ventamodelo.class.getName()).log(Level.SEVERE, null, ex);
    }

    return mensaje;
}

public String guardarVentaConDetalle(List<detalleventa> detalles) {
    String mensaje = "Venta registrada correctamente";
    Connection con = null;
    PreparedStatement pstCabecera = null;
    ResultSet rs = null;
    String idGenerado = "";

    try {
        con = utilidades.conexion.enlace(); // Obtener conexión
        con.setAutoCommit(false); //  INICIA TRANSACCIÓN

        // 1. Insertar cabecera
        String sqlCabecera = "INSERT INTO ventas (ven_fecha, ven_condicion, ven_estado, idclientes,idusuarios)"
                + " VALUES (?, ?, ?, ?, ?)";
        pstCabecera = con.prepareStatement(sqlCabecera, Statement.RETURN_GENERATED_KEYS);
        pstCabecera.setString(1, fecha);
        pstCabecera.setString(2, condicion);
        pstCabecera.setString(3, estado);
        pstCabecera.setString(4, cliente);
        pstCabecera.setString(5, usuario);
        pstCabecera.executeUpdate();

        rs = pstCabecera.getGeneratedKeys();
        if (rs.next()) {
            idGenerado = rs.getString(1);
        }

        // 2. Insertar los detalles
        for (detalleventa detalle : detalles) {
            String sqlDetalle = "INSERT INTO detalleventas (idventas, idproductos, det_cantidad, det_precio) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement pstDetalle = con.prepareStatement(sqlDetalle);
            pstDetalle.setString(1, idGenerado);
            pstDetalle.setString(2, detalle.getIdProducto());
            pstDetalle.setString(3, detalle.getCantidad());
            pstDetalle.setString(4, detalle.getPrecio());
            pstDetalle.executeUpdate();
        }

        con.commit(); // ✅ Confirmar si todo salió bien

            } catch (SQLException ex) {
                mensaje = "Error al registrar venta: " + ex.getMessage();
                try {
                    if (con != null) {
                        con.rollback(); // ❌ Revertir si algo falla
                    }
                } catch (SQLException e) {
                    mensaje += " (rollback falló: " + e.getMessage() + ")";
                }
                Logger.getLogger(ventamodelo.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstCabecera != null) pstCabecera.close();
                    if (con != null) con.setAutoCommit(true);
                } catch (SQLException e) {
                    Logger.getLogger(ventamodelo.class.getName()).log(Level.SEVERE, null, e);
                }
            }

                return mensaje;
            }
//Metodo para traer el total entre la cantidad solicitada y el precio del producto
public int calcularTotalVenta(String idventa) {
    int total = 0;
    String sql = "SELECT det_cantidad, det_precio FROM detalleventas WHERE idventas = ?";

    try (PreparedStatement ps = utilidades.conexion.enlace().prepareStatement(sql)) {
        ps.setString(1, idventa);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int cantidad = rs.getInt("det_cantidad");
            int precio = rs.getInt("det_precio");
            total += cantidad * precio;
        }

        rs.close();
    } catch (SQLException ex) {
        Logger.getLogger(ventamodelo.class.getName()).log(Level.SEVERE, null, ex);
    }

    return total;
}

}

