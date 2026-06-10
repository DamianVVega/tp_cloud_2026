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
public class detalleventa {
    private String idProducto;
    private String cantidad;
    private String precio;

    // Constructor
    public  detalleventa(String idProducto, String cantidad, String precio) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    //getter and setters

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
}