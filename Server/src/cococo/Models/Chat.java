/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cococo.Models;

/**
 *
 * @author franc
 */
public class Chat {

    public int IdMessage;
    public int IdChat;
    public int IdUser;
    public String Message;
    
     public Chat() {

    }

    public Chat( int idChat, int idUser, String message) {
        this.IdChat = idChat;
        this.IdUser = idUser;
        this.Message = message;
    }
}
