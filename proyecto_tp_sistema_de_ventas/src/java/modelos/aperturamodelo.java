/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Damian0
 */
public class aperturamodelo {
    private String idapertura,fecha, hora,monto, idusuario, mensaje;
    Statement st;
    ResultSet rs;

    public String getIdapertura() {
        return idapertura;
    }

    public void setIdapertura(String idapertura) {
        this.idapertura = idapertura;
    }
    
    
    
//Se crean los getters and settersç
   

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }
    //Funcionalidades
    public void guardar() throws SQLException {
        String sql = "INSERT INTO apertura(ape_fecha, ape_hora, ape_monto, idusuarios, ape_estado) "
                   + "VALUES (?, ?, ?, ?, ?)";

        // Obtener fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now(ZoneOffset.of("-03:00"));

        String fechaActual = ahora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String horaActual = ahora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        try (Connection con = utilidades.conexion.enlace();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, fechaActual);
            ps.setString(2, horaActual);
            ps.setString(3, monto); // Usa setBigDecimal si estás manejando decimales
            ps.setString(4, idusuario);
            ps.setString(5, "Abierto");

            ps.executeUpdate();
            mensaje = "Caja Abierta";

        } catch (SQLException ex) {
            System.out.println("Error al guardar apertura: " + ex.getMessage());
            throw ex;
        }
    }

    //Verificar si la  caja esta abierta o cerrada
    public String verificar(){
        String sql= "SELECT * FROM apertura where idusuarios='"+ idusuario +"' and ape_estado='Abierto'";
        String mensaje="";
        try{
            st=utilidades.conexion.sta();
            rs=st.executeQuery(sql);
            rs.next();
            if(rs.getRow()==0){
            mensaje="abrir";
            }else{
                mensaje="cerrar";
                idapertura= rs.getString("idaperturas");
            }
        }catch(SQLException ex){
            System.out.print(ex);
        }
        return mensaje;
        
    
    }
}