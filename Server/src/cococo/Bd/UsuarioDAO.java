/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cococo.Bd;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import cococo.Models.*;
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

    
    public boolean Login(String username,String password,String IP){
        int id = 0;
        try{
            PreparedStatement ps = getConnection().prepareStatement(
                "SELECT Id FROM usuario WHERE Username = ? AND Password = ? ");
            ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
                rs.next();
        id = rs.getInt("Id");
            System.out.println(id);

            if(id!=0){
                  try{
            PreparedStatement ds = getConnection()
                                .prepareStatement
                                ("UPDATE usuario SET IP = ?, Estatus = 1 WHERE id = ?");
            ds.setString(1, IP);
            ds.setInt(2, id);
            ds.executeUpdate();
            return true;
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
            }else{
                return false;
            }
        }catch(Exception e){
            e.getMessage();
            System.out.println(e);
        }
        
        return false;
    }
    
    public boolean addUser(Usuario Usuario) 
    {
        try{
            PreparedStatement pd = getConnection().prepareStatement(
                "SELECT COUNT(*) as count FROM usuario WHERE Username = ? AND Password = ?");
        pd.setString(1, Usuario.Nombre);
        pd.setString(2, Usuario.Password);
        ResultSet rs = pd.executeQuery();
        rs.next();
        int count = rs.getInt("count");
        if(count==0){
        try
        {
        PreparedStatement ps;
        ps = getConnection().prepareStatement("INSERT INTO usuario (Username, Password, IP,Estatus) values (?,?,?,?)");
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
        ps.setString(1, Usuario.Nombre); 
        
        // Sustituye al segundo signo de interrogacion, por el valor de persona.registro
        ps.setString(2, Usuario.Password); 
        
        ps.setString(3, Usuario.Ip);
        ps.setInt(4, Usuario.Estatus);
        
        return ps.executeUpdate()>0; //Ejecuta la instrucción SQL
        } 
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        }else{
            System.out.println("El usuario ya existe");
            return false;
        }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public ArrayList<Usuario> getOnlineUsr(String IP)
    {
        ArrayList<Usuario> resultados = new ArrayList();
        try 
        {
            PreparedStatement ps =  getConnection().prepareStatement("SELECT * FROM usuario WHERE Estatus = 1 AND IP != ? ");
            ps.setString(1, IP);
            ResultSet rs;
            Usuario Usuario;
            rs = ps.executeQuery(); 

            while (rs.next())
            {
                Usuario = new Usuario();
                Usuario.Nombre = rs.getString("Username");         
                Usuario.Id = rs.getInt("id");
                Usuario.Ip = rs.getString("IP");
                resultados.add(Usuario);
            }            
        }
        catch(SQLException es)
        {
            System.out.println(es.getMessage());
        }
        return resultados;
    }
    
     public boolean addMensaje(Chat chat) {
        try {
            PreparedStatement ps;
            ps = getConnection().prepareStatement("INSERT INTO chat (Id_Chat, Id_User, Message) values (?,?,?)");
            /*
             * a cada signo de interrogación se le asiga un numero coorespondiente
             * a su posicion
             * values ( ? , ? )
             * 1 2
             * 
             * ps.setString(numero de signo de interrogacion, valor);
             * ps.setInt(numero de signo de interrogacion, valor);
             * ps.setDouble(numero de signo de interrogacion, valor);
             * 
             * aqui tambien se escoje el tipo de dato a asignar.
             */

            // Sustituye al primer signo de interrogacion, por el valor de persona.nombre
            ps.setInt(1, chat.IdChat);

            // Sustituye al segundo signo de interrogacion, por el valor de persona.registro
            ps.setInt(2, chat.IdUser);

            ps.setString(3, chat.Message);

            return ps.executeUpdate() > 0; // Ejecuta la instrucción SQL
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
     
    public ArrayList<Chat> MensajesChat(int id){
        ArrayList<Chat> Mensajes = new ArrayList();
        try{
             PreparedStatement ps =  getConnection().prepareStatement("SELECT * FROM Chat WHERE Id_Chat = ? ORDER BY Id_Chat ASC ");
            ps.setInt(1, id);
            ResultSet rs;
            rs = ps.executeQuery(); 
            
            while (rs.next())
            {
                Chat Mensaje = new Chat();
                Mensaje.IdChat = rs.getInt("Id_Chat");         
                Mensaje.IdUser = rs.getInt("Id_User");
                Mensaje.Message = rs.getString("Message");
                Mensajes.add(Mensaje);
            }            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return Mensajes;
    }
    
    /*public boolean delete(int id)
    {
        int res = 0;
        try 
        {
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM Persona where id = ?");
            ps.setInt(1, id);
            res = ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return res>0;
    }
    public boolean update(Usuario u)
    {
        int res = 0;
        try 
        {
            PreparedStatement ps = getConnection()
                                .prepareStatement
                                ("UPDATE persona SET nombre = ?, registro = ? WHERE id = ?");
            ps.setString(1, u.nombre);
            ps.setInt(2, u.);
            ps.setInt(3, u.id);            
            
            res = ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return res>0;
    }*/
}
