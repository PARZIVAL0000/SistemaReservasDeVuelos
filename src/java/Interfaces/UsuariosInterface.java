
package Interfaces;
import java.util.List;
import Modelo.Usuarios;
/**
 *
 * @author denni
 */
public interface UsuariosInterface {
    public List<Usuarios> listarUsuarios();
    
    public boolean registrarUsuario(Usuarios usuario);
}
