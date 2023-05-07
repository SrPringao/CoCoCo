/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cococo;
import Server.UsuarioDAO;
import cococo.Models.Usuario;
import java.util.ArrayList;
/**
 *
 * @author franc
 */
public class CoCoCo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here\
     // TODO code application logic here
        Usuario usuario;
        UsuarioDAO UsuarioDAO = new UsuarioDAO();
        
         usuario = new Usuario( "Franco Alexandro Aldrete de la mora","sisinono",0);
         UsuarioDAO.add(usuario);
         
         
          ArrayList <Usuario> datos;        
         datos = UsuarioDAO.getALL();        
         for (Usuario item : datos)
            System.out.println(item.id + " - " + item.nombre + " - " + item.Password );
        
    }
    
}
