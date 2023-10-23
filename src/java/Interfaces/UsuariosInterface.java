
package Interfaces;
import java.util.List;
import Modelo.Usuarios;
/**
 *
 * @author denni
 */
public interface UsuariosInterface {
    
    public List<Usuarios> listarUsuarios();
    public List<Usuarios> listarUsuario(int usuario_id);
    public boolean eliminarUsuario(int usuario_id);
    public boolean actualizarUsuario(Usuarios usuario);
    public boolean crearUsuario(Usuarios usuario);
}
