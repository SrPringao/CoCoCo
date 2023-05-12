/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author franc
 */
public class Chat {

    public int idMessage;
    public int idChat;
    public int idUser;
    public String message;

    public Chat() {

    }

    public Chat(int idChat, int idUser, String message) {
        this.idChat = idChat;
        this.idUser = idUser;
        this.message = message;
    }

}
