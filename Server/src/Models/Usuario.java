/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author franc
 */
public class Usuario {
        public int id;
    public String nombre;
    public String Password;
    public int Estatus;
    
    public Usuario()
    {
        id = 0;
        nombre = "";
        Password = "";
        Estatus = 0;
    }

    public Usuario(String nombre, String Password, int Estatus) 
    {
        this.id = id;
        this.nombre = nombre;
        this.Password = Password;
        this.Estatus = 0;
    }
}
