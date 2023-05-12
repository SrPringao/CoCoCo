package client.Chat;

public class Mensaje {
    private int IdMessage;
    private int IdChat;
    private int IdUser;
    private String Message;

    public Mensaje(int idMessage, int idChat, int idUser, String message) {
        this.IdMessage = idMessage;
        this.IdChat = idChat;
        this.IdUser = idUser;
        this.Message = message;
    }


    public int getIdMessage() {
        return IdMessage;
    }

    public void setIdMessage(int IdMessage) {
        this.IdMessage = IdMessage;
    }

    public int getIdChat() {
        return IdChat;
    }

    public void setIdChat(int IdChat) {
        this.IdChat = IdChat;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
