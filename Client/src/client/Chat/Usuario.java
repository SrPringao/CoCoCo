package client.Chat;

public class Usuario {
    private int Id;
    private String Nombre;
    private String Ip;

    public Usuario(int id, String name, String ip) {
        this.Id = id;
        this.Nombre = name;
        this.Ip = ip;
    }

    // getters
    public int getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getIp() {
        return Ip;

    }

    // setters
    public void setId(int id) {
        this.Id = id;
    }

    public void setNombre(String name) {
        this.Nombre = name;
    }

    public void setIp(String ip) {
        this.Ip = ip;
    }

}
