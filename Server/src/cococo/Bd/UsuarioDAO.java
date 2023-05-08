/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cococo.Bd;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import cococo.Models.Usuario;
import java.util.ArrayList;
import java.sql.ResultSet;

/*
    Se acostumbra crear una clase heredada de la clase que realiza la conexión
a la base de datos con el nombre de la tabla a la cual se le harán las opera-
ciones de editar, busar, etc.

Cada método emplea la instrucción 

    PreparedStatement ps; <- Se crea la referencia 
    ps = getConnection().prepareStatement("INSERT INTO persona (nombre, registro) values (?,?)");

    getConnection devuelve la conexion a la base de datos, la cual esta en 
la clase BD y se esta herendando a la clase personaDAO, este objeto 
posee un metodo que crea un on objeto que ejecuta un SQL que se escribe 
como parametro.

    La ejecución de la instruccion SQL se realiza con los métodos
    ps.executeUpdate() -> si la instruccion es de tipo insert, delete, update, 
                          devuelve el numero de filas afectadas.
    
    ps.executeUpdate() -> si la instruccion es de tipo select, devuelve un 
                        objeto de tipo ResultSet con los resultados.

*/
public class UsuarioDAO extends cococo.Bd.BD
{
    public UsuarioDAO()
    {
        super();
    }

    public boolean verificarUsuario(String username, String password) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                    "SELECT COUNT(*) as count FROM usuario WHERE Username = ? AND Password = ? AND Estatus = 0");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt("count");

            // si count es mayor a 0, significa que el usuario existe
            return count > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // si count es igual a 0, significa que el usuario no existe
        return false;
    }

    public boolean add(Usuario Usuario) 
    {
        try
        {
        PreparedStatement ps;
        ps = getConnection().prepareStatement("INSERT INTO usuario (Username, Password, Estatus) values (?,?,?)");
        /*
            a cada signo de interrogación se le asiga un numero coorespondiente
            a su posicion 
                    values ( ? , ? )
                             1   2
        
            ps.setString(numero de signo de interrogacion, valor); 
            ps.setInt(numero de signo de interrogacion, valor); 
            ps.setDouble(numero de signo de interrogacion, valor); 
        
            aqui tambien se escoje el tipo de dato a asignar.        
        */
        
        // Sustituye al primer signo de interrogacion, por el valor de persona.nombre
        ps.setString(1, Usuario.nombre); 
        
        // Sustituye al segundo signo de interrogacion, por el valor de persona.registro
        ps.setString(2, Usuario.Password); 
        
        ps.setInt(3, Usuario.Estatus); 
        
        return ps.executeUpdate()>0; //Ejecuta la instrucción SQL
        } 
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public ArrayList<Usuario> getOnlineUsr() {
        ArrayList<Usuario> resultados = new ArrayList();
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM usuario WHERE Estatus = 1 ");
            ResultSet rs;
            Usuario Usuario;
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario = new Usuario();
                Usuario.nombre = rs.getString("Username");
                Usuario.id = rs.getInt("id");
                Usuario.Password = rs.getString("Password");
                Usuario.Estatus = rs.getInt("Estatus");
                resultados.add(Usuario);
            }
        } catch (SQLException es) {
            System.out.println(es.getMessage());
        }
        return resultados;
    }

    /*
     * public boolean delete(int id)
     * {
     * int res = 0;
     * try
     * {
     * PreparedStatement ps =
     * getConnection().prepareStatement("DELETE FROM Persona where id = ?");
     * ps.setInt(1, id);
     * res = ps.executeUpdate();
     * }
     * catch (SQLException ex)
     * {
     * System.out.println(ex.getMessage());
     * }
     * return res>0;
     * }
     * public boolean update(Usuario u)
     * {
     * int res = 0;
     * try
     * {
     * PreparedStatement ps = getConnection()
     * .prepareStatement
     * ("UPDATE persona SET nombre = ?, registro = ? WHERE id = ?");
     * ps.setString(1, u.nombre);
     * ps.setInt(2, u.);
     * ps.setInt(3, u.id);
     * 
     * res = ps.executeUpdate();
     * }
     * catch (SQLException ex)
     * {
     * System.out.println(ex.getMessage());
     * }
     * return res>0;
     * }
     */
}
