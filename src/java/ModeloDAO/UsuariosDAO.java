package ModeloDAO;
import Config.Conexion;
import Modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Interfaces.UsuariosInterface;
/**
 *
 * @author denni
 */
public class UsuariosDAO implements UsuariosInterface{
    private Connection conexion;
    private PreparedStatement ps;
    private ResultSet rs;
    
    
    public UsuariosDAO() throws ClassNotFoundException{
        Conexion n = new Conexion();
        this.setConexion(n.getConexion());
    }
            
    
     @Override
    public List<Usuarios> listarUsuarios() {
        try{
            String sql = "SELECT * FROM usuarios";
            List<Usuarios> registros = new ArrayList<>();
            this.setPS(sql);
            this.setRS(this.getPS());
            
            while(this.getRS().next()){
                Usuarios usuario = new Usuarios(this.getRS().getInt("usuario_id"), this.getRS().getInt("pasajeroId"));
                registros.add(usuario);
            }
            
            return registros;
        }catch(SQLException e){
            this.establecerMensajeError(e);
            return null;
        }
    }

    @Override
    public List<Usuarios> listarUsuario(int usuario_id) {
        try{
            String sql = "SELECT * FROM usuarios WHERE usuario_id = ?";
            List<Usuarios> registros = new ArrayList<>();
            this.setPS(sql, usuario_id);
            this.setRS(this.getPS());
            
            while(this.getRS().next()){
                Usuarios usuario = new Usuarios(this.getRS().getInt("usuario_id"), this.getRS().getInt("pasajeroId"));
                registros.add(usuario);
            }
            
            return registros;
        }catch(SQLException e){
            this.establecerMensajeError(e);
            return null;
        }
    }

    @Override
    public boolean eliminarUsuario(int usuario_id) {
        try{
            String sql = "DELETE FROM usuarios WHERE usuario_id = ?";
            this.setPS(sql, usuario_id);
            this.setRS(this.getPS(),true);
            
            return true;
        }catch(SQLException e){
            this.establecerMensajeError(e);
            return false;
        }
    }

    @Override
    public boolean actualizarUsuario(Usuarios usuario) {
        try{
            String sql = "UPDATE usuarios SET pasajeroId = ? WHERE usuario_id = ?";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            ps.setInt(1, usuario.getPasajeroId());
            ps.setInt(2, usuario.getUsuarioId());
            ps.execute();
            
            
            return true;
        }catch(SQLException e){
            this.establecerMensajeError(e);
            return false;
        }
    }

    @Override
    public boolean crearUsuario(Usuarios usuario) {
        try{
            String sql = "INSERT INTO usuarios(pasajeroId) VALUES(?)";
            this.setPS(sql, usuario);
            this.setRS(this.getPS(),true);
            
            return true;
        }catch(SQLException e){
            this.establecerMensajeError(e);
            return false;
        }
    }

    
    
    /*Mensaje de error*/
    
    public void establecerMensajeError(SQLException e){
        System.out.println("***** ERROR EN NUESTRA BASE DE DATOS ******");
        System.out.println("* Mensaje: " + e.getMessage());
        System.out.println("* Error: " + e.getErrorCode());
        System.out.println("* Estado: " + e.getSQLState());
    }
    
    /*Getter y Setter */
    
    public void setConexion(Connection conexion){
        this.conexion = conexion;
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
    public void setPS(String sql) throws SQLException{
        this.ps = this.getConexion().prepareStatement(sql);
    }
    
    public void setPS(String sql, int parametroId) throws SQLException{
        this.ps = this.getConexion().prepareStatement(sql);
        this.ps.setInt(1, parametroId);
    }
    
    public void setPS(String sql, Usuarios usuario) throws SQLException{
        this.ps = this.getConexion().prepareStatement(sql);
        this.ps.setInt(1, usuario.getPasajeroId());
    }
    
    public PreparedStatement getPS(){
        return ps;
    }
    
    public void setRS(PreparedStatement ps) throws SQLException{
        this.rs = ps.executeQuery();
    }
    public void setRS(PreparedStatement ps, boolean flag) throws SQLException {
        if(flag){
            ps.execute();
        }
    }
    
    public ResultSet getRS(){
        return rs;
    }

    
}
