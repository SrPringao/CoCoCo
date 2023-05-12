/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cococo.Models;

/**
 *
 * @author franc
 */
public class Usuario {
        public int Id;
    public String Nombre;
    public String Password;
    public String Ip;
    public int Estatus;
    
    public Usuario()
    {
        Id = 0;
        Nombre = "";
        Password = "";
        Ip = "0.0.0.0";
        Estatus = 1;
    }

    public Usuario(String Nombre, String Password, String Ip, int Estatus) 
    {
        this.Nombre = Nombre;
        this.Password = Password;
        this.Ip = Ip;
        this.Estatus = Estatus;
    }
}
