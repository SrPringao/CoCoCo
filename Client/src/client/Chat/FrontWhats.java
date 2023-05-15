/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client.Chat;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class FrontWhats {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "Juan", "192.168.1.1"));
        usuarios.add(new Usuario(2, "Pedro", "192.168.1.2"));
        usuarios.add(new Usuario(3, "Maria", "192.168.1.3"));
        usuarios.add(new Usuario(4, "Jose", "192.168.1.4"));
        usuarios.add(new Usuario(5, "Ana", "192.168.1.5"));

        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }

        int id = 1;

        UsuariosActivos chat = new UsuariosActivos(usuarios, id);
    }

}
