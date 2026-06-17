/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

/**
 *
 * @author Damian0
 */
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
public class conexion {
   static Connection conn;
   static Statement st;
   //Creamos los parametros de conexion a la base de datos
   //static String bd="baratito";
   static String user=System.getenv("DATASOURCE_USERNAME");
   static String pass=System.getenv("DATASOURCE_PASSWORD");
   static String url=System.getenv("DATASOURCE_URL");
   //Metodo para abrir la conexion
   public static Connection enlace() throws SQLException{
       try{
           //Intentamos abrir la conexion
           Class.forName("com.mysql.cj.jdbc.Driver");
           conn=DriverManager.getConnection(url, user, pass);
       }catch(Exception e){
           System.out.println("Error: "+e);
       }
       return conn;
   } 
   //Metodo para preparar la conexion
   public static Statement sta() throws SQLException{
       conn=enlace();
       st=conn.createStatement();
       return st;
   }
}










