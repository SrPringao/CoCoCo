/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cococo.Bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BD 
{
    private Connection c;
    public BD() 
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            c = DriverManager.getConnection("jdbc:mysql://localhost/cococoBD","root","");
            System.out.println("Estoy dentro");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Driver no cargado: " + ex.getMessage());
        }
        catch (SQLException ex)
        {
            System.out.println("Error de conexion a la bd: " + ex.getMessage());
        }
    }
    
    public Connection getConnection()
    {
        return c;
    }
}
