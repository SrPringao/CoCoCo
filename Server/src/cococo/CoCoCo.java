package cococo;
import cococo.Bd.UsuarioDAO;
import cococo.Models.Usuario;
import java.util.ArrayList;

public class CoCoCo {

    public static void main(String[] args) {
        
        
        Server server = new Server(1234);
        server.start();
    }
    
}
