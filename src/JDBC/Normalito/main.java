package JDBC.Normalito;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        //insertar un usuario
     /*   usuarioDAO.InsertarUsuario("Diego","DGgarcia855@gmail.com");
        usuarioDAO.InsertarUsuario("Brayan", "brayanmaldonado0805@gmail.com");
        usuarioDAO.InsertarUsuario("Pedro", "pedrogomez.campuslands@gmail.com");
        usuarioDAO.InsertarUsuario("Jose", "Joseperez@gmail.com");
*/
        List<String> listaUsuarios = usuarioDAO.obtenerUsuarios();
        for (String usuario : listaUsuarios) {
            System.out.println(usuario);
        }
        usuarioDAO.actualizarUsuario(3,"Pedro","brayanmaldonado0805@gmail.com");
    }
}
