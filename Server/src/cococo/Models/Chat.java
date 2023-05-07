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

    private int idMessage;
    private int idChat;
    private int idUser;
    private int message;

    public Chat(int idMessage, int idChat, int idUser, int message) {
        this.idMessage = idMessage;
        this.idChat = idChat;
        this.idUser = idUser;
        this.message = message;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public int getIdChat() {
        return idChat;
    }

    public void setIdChat(int idChat) {
        this.idChat = idChat;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}
