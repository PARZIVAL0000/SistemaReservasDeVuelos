
package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Config.Conexion;
import Interfaces.Pasajero_ClaseInterface;
import Modelo.Pasajero_Clase;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author denni
 */
public class Pasajero_ClaseDAO implements Pasajero_ClaseInterface{
    private Connection conexion;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public Pasajero_ClaseDAO() throws ClassNotFoundException{
        Conexion cn = new Conexion();
        this.setConexion(cn.getConexion());
    }
    
    @Override
    public List<Pasajero_Clase> listarPasajerosClases() {
        try{
            List<Pasajero_Clase> registros = new ArrayList<>();
            String sql = "SELECT * FROM pasajero_clase";
            this.setPS(sql);
            this.setRS(this.getPS());
            
            while(this.getRS().next()){
                Pasajero_Clase pasajero = new Pasajero_Clase();
                pasajero.setClaseId(this.getRS().getInt("pasajero_clase_id"));
                pasajero.setPasajeroId(this.getRS().getInt("pasajeroId"));
                pasajero.setPasajero_clase_id(this.getRS().getInt("claseId"));
                
                registros.add(pasajero);
            }
            
            return registros;
        }catch(SQLException e){
            this.message(e);
            return null;
        }
    }

    @Override
    public List<Pasajero_Clase> listarPasajeroClase(int pasajeroClaseId) {
        try{
            List<Pasajero_Clase> registros = new ArrayList<>();
            String sql = "SELECT * FROM pasajero_clase WHERE pasajero_clase_id = ?";
            this.setPS(sql, pasajeroClaseId);
            this.setRS(this.getPS());
            
            while(this.getRS().next()){
                Pasajero_Clase pasajero = new Pasajero_Clase();
                pasajero.setClaseId(this.getRS().getInt("pasajero_clase_id"));
                pasajero.setPasajeroId(this.getRS().getInt("pasajeroId"));
                pasajero.setPasajero_clase_id(this.getRS().getInt("claseId"));
                
                registros.add(pasajero);
            }
            
            return registros;
        }catch(SQLException e){
            this.message(e);
            return null;
        }
    }

    @Override
    public boolean eliminarPasajeroClase(int pasajeroClaseId) {
        try{
            String sql = "DELETE FROM pasajero_clase WHERE pasajero_clase_id = ?";
            this.setPS(sql, pasajeroClaseId);
            this.setRS(this.getPS(),true);
            
            return true;
        }catch(SQLException e){
            this.message(e);
            return false;
        }
    }

    @Override
    public boolean actualizarPasajeroClase(Pasajero_Clase pasajeroClase) {
        try{
            String sql = "UPDATE pasajero_clase SET pasajeroId = ?, claseId = ? WHERE pasajero_clase_id = ?";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            ps.setInt(1, pasajeroClase.getPasajeroId());
            ps.setInt(2, pasajeroClase.getClaseId());
            ps.setInt(3, pasajeroClase.getPasajero_clase_id());
            this.setRS(ps,true);
            
            return true;
        }catch(SQLException e){
            this.message(e);
            return false;
        }
    }

    @Override
    public boolean crearPasajeroClase(List<Pasajero_Clase> pasajeroClase) {
        try{
            String sql = "INSERT INTO pasajero_clase(pasajeroId, claseId) VALUES(?, ?)";
            PreparedStatement ps = this.getConexion().prepareStatement(sql);
            
            for(Pasajero_Clase pc : pasajeroClase){
                ps.setInt(1, pc.getPasajeroId());
                ps.setInt(2, pc.getClaseId());
                this.setRS(ps,true);
            }
            
            return true;
        }catch(SQLException e){
            this.message(e);
            return false;
        }
    }
    
    
    /* Getter y Setter */
    
    public void setConexion(Connection cn){
        this.conexion = cn;
    }
    public Connection getConexion(){
        return conexion;
    }
    
    /* Preparacion y Resultado */
    public void message(SQLException e){
        System.out.println("***** ERROR EN LA CONEXION A LA BASE DE DATOS *****");
        System.out.println("* Mensaje: " + e.getMessage());
        System.out.println("* Error: " + e.getErrorCode());
        System.out.println("* Estado" + e.getSQLState());
    }
    
    public void setPS(String sql) throws SQLException{
        this.ps = this.getConexion().prepareStatement(sql);
    }
    
    public void setPS(String sql, int pasajeroId) throws SQLException{
        this.ps = this.getConexion().prepareStatement(sql);
        this.ps.setInt(1, pasajeroId);
    }
    
    public PreparedStatement getPS(){
        return ps;
    }
    
    public void setRS(PreparedStatement ps) throws SQLException{
        this.rs = ps.executeQuery();
    }
    
    public void setRS(PreparedStatement ps, boolean flag) throws SQLException{
        if(flag){
            ps.execute();
        }
    }
    
    public ResultSet getRS(){
        return rs;
    }

}
