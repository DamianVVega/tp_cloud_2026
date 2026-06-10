/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Damian0
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
public class cierremodelo {
    private String fecha, hora, monto, idapertura;
    // Se agregan los getters and setters

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getIdapertura() {
        return idapertura;
    }

    public void setIdapertura(String idapertura) {
        this.idapertura = idapertura;
    }
    
    //Declararlas variables para la conexion
    Statement st;
    //Funcionalidades
    public void cerrar() {
        String sql = "INSERT INTO cierre(cie_fecha, cie_hora, cie_monto, idaperturas) VALUES (?, ?, ?, ?)";

        // Obtener fecha y hora actuales
        
        LocalDateTime ahora = LocalDateTime.now(ZoneOffset.of("-03:00"));

        String fechaActual = ahora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String horaActual = ahora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        try (Connection con = utilidades.conexion.enlace();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, fechaActual);
            ps.setString(2, horaActual);
            ps.setString(3, monto); // Usa setBigDecimal si lo estás tratando como número
            ps.setString(4, idapertura);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(cierremodelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void actualizarapertura(){
        String sql="update apertura set ape_estado='Cerrada' "
                + "where idaperturas='"+idapertura+"'";
        try {
            st=utilidades.conexion.sta();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(cierremodelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}